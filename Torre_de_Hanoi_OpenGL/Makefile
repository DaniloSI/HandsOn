
# Compila programas do curso de OpenGL no ambiente Linux
# Substitua nome_do_programa pelo nome do programa ...
# ...que se está trabalhando
#
# Antes das linhas que contém "gcc...", não pode haver espaço em
# branco e sim uma marca de tabulação
#
# Prof. Marco Antonio
#

# Variables
MESA = /usr/bin/Mesa-5.0
INCPATH = -I$(MESA)/include
LIBPATH = -L$(MESA)/lib
LIBS        = -lglut -lGLU -lGL -lm
CFLAGS  = $(INCPATH) -g
LFLAGS  = $(LIBPATH) $(LIBS)

# Main targets
toh: toh.o TadHanoi.o
	gcc -o hanoi toh.o TadHanoi.o $(LFLAGS)

# Source targets
toh.o: toh.c
	gcc $(LFLAGS) -c toh.c

TadHanoi.o: TadHanoi.c
	gcc $(LFLAGS) -c TadHanoi.c

clean:
	rm -f *.o
