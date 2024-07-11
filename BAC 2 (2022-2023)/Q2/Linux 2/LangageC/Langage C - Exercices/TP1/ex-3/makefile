CFLAGS=-std=c11 -pedantic -Wvla -Wall -Werror -D_DEFAULT_SOURCE -g

# Edition de liens
secretAgent: secretAgent.o cryptROT13.o cryptPolybe.o cryptMorse.o utils_v1.o
	cc $(CFLAGS) -o secretAgent secretAgent.o cryptROT13.o cryptPolybe.o cryptMorse.o utils_v1.o

# Compilation
secretAgent.o : secretAgent.c cryptROT13.h cryptPolybe.h cryptMorse.h utils_v1.h
	cc $(CFLAGS) -c secretAgent.c

cryptROT13.o: cryptROT13.c cryptROT13.h 
	cc $(CFLAGS) -c cryptROT13.c

cryptPolybe.o: cryptPolybe.c cryptPolybe.h 
	cc $(CFLAGS) -c cryptPolybe.c

cryptMorse.o: cryptMorse.c cryptMorse.h 
	cc $(CFLAGS) -c cryptMorse.c

utils_v1.o: utils_v1.c utils_v1.h
	cc $(CFLAGS) -c utils_v1.c


# Nettoyage
clean: 
	rm -f *.o
	rm -f secretAgent
