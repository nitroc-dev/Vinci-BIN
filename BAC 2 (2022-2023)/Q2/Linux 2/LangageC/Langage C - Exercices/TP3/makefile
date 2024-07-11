# Notation polonaise inverse avec pile en liste chaînée

CFLAGS=-std=c11 -pedantic -Wvla -Wall -Werror -D_DEFAULT_SOURCE

npi : npi.o pile.o utils_v1.o
	gcc $(CFLAGS) -o npi npi.o pile.o utils_v1.o

npi.o : pile.h npi.c
	gcc $(CFLAGS) -c npi.c

pile.o : pile.h pile.c
	gcc $(CFLAGS) -c pile.c	

utils_v1.o : utils_v1.h utils_v1.c
	gcc $(CFLAGS) -c utils_v1.c

clean :
	rm *.o
	rm npi
