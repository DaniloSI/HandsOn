#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <GL/glut.h>
#include <GL/gl.h>
#include <GL/freeglut.h>

#ifndef TADHANOI_H
#define TADHANOI_H


struct disk;
typedef struct disk Disk;

struct pin;
typedef struct pin Pin;

Disk* create_disk(int number, int cor);

Pin* create_pin(int id, int q_disk);

void push(Pin* pino, Disk* disco);

Disk* pop(Pin* pino);

int peek(Disk* disco);

float AL(Pin* pino);

float LA(Pin* pino);

float X(Pin* pino);

float Y(Pin* pino);

float AL_D(Disk* disco);

float LA_D(Disk* disco);

float X_D(Disk* disco);

float Y_D(Disk* disco);

float get_r(Disk* disco);

float get_g(Disk* disco);

float get_b(Disk* disco);

Disk* get_e(Pin* pino, int pos);

#endif
