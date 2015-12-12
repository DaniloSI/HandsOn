#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>
#include <GL/gl.h>
#include <GL/freeglut.h>
#include "TadHanoi.h"

#ifndef TOH_H
#define TOH_H

#define T_A 1
#define T_B 2
#define T_C 3


void solve_toh(Pin* A, Pin* B, Pin* C, int the_amount);

void insert_disks(Pin* tower, int amount);

void solution_hanoi(int amount_disk);

void retangulo(float Altura, float Largura, float desloc_x, float desloc_y);

void print_game(Pin* A, Pin* B, Pin* C);

void print_pino(Pin* pino);

void print_disk(Disk* disco);

void keyboard(unsigned char key, int x, int y);

void display(void);

#endif
