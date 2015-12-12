#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>
#include <GL/gl.h>
#include <GL/freeglut.h>
#include "TadHanoi.h"
#include "toh.h"
#include <unistd.h>

int SLEEP = 500000;
int QUANTIDADE_DE_DISCOS = 3;


void solve_toh(Pin* A, Pin* B, Pin* C, int the_amount){// Resolve recursivamente a toh (Tower of Hanoi).

  /*
    A, B e C são as três torres implementadas aqui como pilhas.
    the_amount é a quantidade de discos e nessa função será usado como o disco corrente, ou seja, o disco
    que está se deslocando de uma torre pra outra.

    Essa solução segue um percurso em pós-ordem em uma árvore binária de movimentos.
  */
  if(the_amount == 1){ // Caso base, chegou no disco 1.
    push(C, pop(A)); // Coloca em C.
    print_game(A, B, C);
    usleep(SLEEP);

  }

  else{
    solve_toh(A, C, B, the_amount-1); // Alterna as torres C e B. No caso base, o disco 1 poderá ir para B ou C.
    push(C, pop(A));

    print_game(A, B, C);
    usleep(SLEEP);
    solve_toh(B, A, C, the_amount-1);
  }
}

void insert_disks(Pin* tower, int amount){
  int cor = amount;
  while(amount > 0){
    push(tower, create_disk(amount, cor-(amount-1)));
    amount--;
  }
}



void solution_hanoi(int amount_disk){
  Pin* A;
  Pin* B;
  Pin* C;
  A = create_pin(T_A, amount_disk);
  B = create_pin(T_B, amount_disk);
  C = create_pin(T_C, amount_disk);

  insert_disks(A, amount_disk);

  print_game(A, B, C);
  usleep(SLEEP);

  solve_toh(A, B, C, amount_disk);

  print_game(A, B, C);

}



// ----------------------------------------- Interface Gráfica


void print_game(Pin* A, Pin* B, Pin* C){
  Disk* i;
  int count;

  glClear (GL_COLOR_BUFFER_BIT);

  glColor3f(0, 0, 0);
  retangulo(0.05, 1.0, 0.0, 0.0);

  print_pino(A);
  print_pino(B);
  print_pino(C);

  count = 0;
  while(i = get_e(A, count)){
    count++;
    print_disk(i);
  }

  count = 0;
  while(i = get_e(B, count)){
    count++;
    print_disk(i);
  }

  count = 0;
  while(i = get_e(C, count)){
    count++;
    print_disk(i);
  }

  glFlush();
}

void print_pino(Pin* pino){
  glColor3f(0, 0, 0);
  retangulo(AL(pino), LA(pino), X(pino), Y(pino));
}

void print_disk(Disk* disco){
  glColor3f(get_r(disco), get_g(disco), get_b(disco));
  retangulo(AL_D(disco), LA_D(disco), X_D(disco), Y_D(disco));
}



void keyboard(unsigned char key, int x, int y){
  if(key == 27) exit(0);
}

void display(void){
  solution_hanoi(QUANTIDADE_DE_DISCOS);
}


void retangulo(float Altura, float Largura, float desloc_x, float desloc_y){
   glBegin(GL_POLYGON);
    glVertex2f(desloc_x, desloc_y); // Inferior Esquerdo
    glVertex2f(desloc_x+Largura, desloc_y); // Inferior Direito
    glVertex2f(desloc_x+Largura, desloc_y+Altura); // Superior Direito
    glVertex2f(desloc_x, desloc_y+Altura); // Superior Esquerdo
   glEnd();
}


int main (int argc, char **argv) {

  if(argc >= 3) {
    QUANTIDADE_DE_DISCOS = atoi(argv[1]);
    SLEEP = atof(argv[2])*1000000;
  }
  else if(argc == 2) QUANTIDADE_DE_DISCOS = atoi(argv[1]);

  glutInit (&argc, argv); //initialize the program.

  glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB); //set up a basic display buffer (only singular for now)

  glutInitWindowSize (1366, 768); //set whe width and height of the window

  glutInitWindowPosition (100, 100); //set the position of the window

  glutCreateWindow("Meu primeiro programa com IG."); //set the caption for the window

  glClearColor(1, 1, 1, 1);

  glShadeModel(GL_FLAT);

  glOrtho(0, 1, 0, 1, -1, 1);

  glutDisplayFunc (display); //call the display function to draw our world

  glutKeyboardFunc(keyboard);

  glutMainLoop (); //initialize the OpenGL loop cycle

  return 0;
}
