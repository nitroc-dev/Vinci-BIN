CFLAGS=-std=c11 -D_DEFAULT_SOURCE -pedantic -Wvla -Werror -Wall

all: encryptPolybe decryptPolybe

encryptPolybe: encryptPolybe.o cryptPolybe.o utils_v1.o
	cc $(CFLAGS) -o encryptPolybe encryptPolybe.o cryptPolybe.o utils_v1.o

decryptPolybe: decryptPolybe.o cryptPolybe.o utils_v1.o
	cc $(CFLAGS) -o decryptPolybe decryptPolybe.o cryptPolybe.o utils_v1.o

encryptPolybe.o: encryptPolybe.c
	cc $(CFLAGS) -c encryptPolybe.c

decryptPolybe.o: decryptPolybe.c
	cc $(CFLAGS) -c decryptPolybe.c

utils_v1.o: utils_v1.c utils_v1.h
	cc $(CFLAGS) -c utils_v1.c

cryptPolybe.o: cryptPolybe.c cryptPolybe.h
	cc $(CFLAGS) -c cryptPolybe.c

clean:
	rm *.o
	rm encryptPolybe
	rm decryptPolybe