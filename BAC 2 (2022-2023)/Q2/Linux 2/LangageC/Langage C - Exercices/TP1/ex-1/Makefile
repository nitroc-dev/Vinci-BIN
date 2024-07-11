CFLAGS=-std=c11 -D_DEFAULT_SOURCE -pedantic -Wvla -Werror -Wall

all: encrypt decrypt

encrypt: encrypt.o crypt.o utils_v1.o
	cc $(CFLAGS) -o encrypt encrypt.o crypt.o utils_v1.o

decrypt: decrypt.o crypt.o utils_v1.o
	cc $(CFLAGS) -o decrypt decrypt.o crypt.o utils_v1.o

encrypt.o: encrypt.c
	cc $(CFLAGS) -c encrypt.c

decrypt.o: decrypt.c
	cc $(CFLAGS) -c decrypt.c

utils_v1.o: utils_v1.c utils_v1.h
	cc $(CFLAGS) -c utils_v1.c

crypt.o: crypt.c crypt.h
	cc $(CFLAGS) -c crypt.c

clean:
	rm *.o
	rm encrypt
	rm decrypt