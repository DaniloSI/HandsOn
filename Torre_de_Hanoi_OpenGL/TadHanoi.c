#include <stdio.h>
#include <stdlib.h>
#include "TadHanoi.h"


#define init_larg 0.001
#define ALTURA_DISCO 0.015

#define AZUL 1
#define VERMELHO 2
#define AMARELO 3
#define VERDE 4
#define ROSA 5
#define LARANJA 6
#define ROXO 7
#define AZULM 8



struct disk{
  float cor_rgb[3];
  float dim[4];
  struct disk *next;
  int info;
};

struct pin{
  float dim[4];
  Disk* discos;
  int id, size;
};


Disk* create_disk(int number, int cor){
  float R, G, B;

  Disk* new = malloc(sizeof(Disk));
  new->dim[0] = ALTURA_DISCO;
  new->dim[1] = init_larg+((float)number/60.0);

  new->next = NULL;

  new->info = number;

  cor = cor%8;

  if(cor == AZUL){
    R = 0;
    G = 0;
    B = 255;
  }
  else if(cor == VERMELHO){
    R = 255;
    G = 0;
    B = 0;
  }

  else if(cor == AMARELO){
    R = 255;
    G = 255;
    B = 0;
  }

  else if(cor == VERDE){
    R = 0;
    G = 255;
    B = 0;
  }

  else if(cor == ROSA){
    R = 255;
    G = 0;
    B = 255;
  }

  else if(cor == LARANJA){
    R = 255;
    G = 170;
    B = 0;
  }

  else if(cor == ROXO){
    R = 150;
    G = 0;
    B = 150;
  }

  else if(cor == AZULM){
    R = 0;
    G = 0;
    B = 170;
  }

  new->cor_rgb[0] = R;
  new->cor_rgb[1] = G;
  new->cor_rgb[2] = B;

  return new;
}



Pin* create_pin(int id, int q_disk){
  Pin* new = malloc(sizeof(Pin));
  new->id = id;

  new->dim[0] = 0.70;
  new->dim[1] = 0.003;
  new->dim[3] = 0.05;
  if(id == 1) new->dim[2] = 0.15;
  else if(id == 2) new->dim[2] = 0.45;
  else new->dim[2] = 0.75;

  new->discos = NULL;
  new->size = 0;

  return new;
}


void push(Pin* pino, Disk* disco){
  if(pino->size == 0){
    pino->discos = disco;
    disco->dim[2] = pino->dim[2]-((float)disco->dim[1]/2.0)+pino->dim[1]/2.0;
    disco->dim[3] = pino->dim[3]-((int)pino->size*ALTURA_DISCO);
    pino->size++;
  }
  else{
    disco->next = pino->discos;
    pino->discos = disco;
    disco->dim[2] = pino->dim[2]-((float)disco->dim[1]/2.0)+pino->dim[1]/2.0;
    disco->dim[3] = pino->dim[3]+(pino->size*ALTURA_DISCO);
    pino->size++;
  }
}



Disk* pop(Pin* pino){
  Disk* temp;
  temp = pino->discos;

  pino->discos = pino->discos->next;
  temp->next = NULL;

  pino->size--;

  return temp;
}



int peek(Disk* disco){
  return disco->info;
}


float AL(Pin* pino){
  return pino->dim[0];
}

float LA(Pin* pino){
  return pino->dim[1];
}

float X(Pin* pino){
  return pino->dim[2];
}

float Y(Pin* pino){
  return pino->dim[3];
}

float AL_D(Disk* disco){
  return disco->dim[0];
}

float LA_D(Disk* disco){
  return disco->dim[1];
}

float X_D(Disk* disco){
  return disco->dim[2];
}

float Y_D(Disk* disco){
  return disco->dim[3];
}


float get_r(Disk* disco){
  return disco->cor_rgb[0];
}

float get_g(Disk* disco){
  return disco->cor_rgb[1];
}

float get_b(Disk* disco){
  return disco->cor_rgb[2];
}


Disk* get_e(Pin* pino, int pos){
  Disk* temp = pino->discos;
  int i;

  for(i = 0 ; i < pos && temp != NULL; temp = temp->next, i++);

  return temp;
}
