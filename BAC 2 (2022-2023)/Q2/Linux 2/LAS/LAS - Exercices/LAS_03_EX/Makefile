CFLAGS=-std=c11 -pedantic -Wall -Wvla -Werror -D_DEFAULT_SOURCE

ALL= 3a pgmInscription createInscriptionRequests

all: $(ALL) 

3a: 3a.o utils_v2.o
	gcc $(CFLAGS) -o 3a 3a.o utils_v2.o

3a.o: 3a.c utils_v2.h 
	gcc $(CFLAGS) -c 3a.c

pgmInscription: utils_v2.o pgmInscription.o
	gcc $(CFLAGS) -o pgmInscription utils_v2.o pgmInscription.o

pgmInscription.o: pgmInscription.c inscriptionRequest.h
	gcc $(CFLAGS) -c pgmInscription.c

createInscriptionRequest: createInscriptionRequests.c inscriptionRequest.h
	gcc $(CFLAGS) -o createInscriptionRequests createInscriptionRequests.c

utils_v2.o: utils_v2.c utils_v2.h
	gcc $(CFLAGS) -c utils_v2.c 

# for dev purpose
clear:
	clear

clean:
	rm -f *.o
	rm -f DemandesInscriptions.bin
	rm -f $(ALL)

dev: clear all
	./createInscriptionRequests
	./pgmInscription < DemandesInscriptions.bin
