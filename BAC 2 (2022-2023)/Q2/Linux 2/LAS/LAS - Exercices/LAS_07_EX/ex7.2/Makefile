CC=gcc

CCFLAGS=-D_DEFAULT_SOURCE -D_XOPEN_SOURCE -D_BSD_SOURCE -std=c11 -pedantic -Wvla -Wall -Werror

ALL= client

all: $(ALL)

client : utils_v2.o client.o
	$(CC) $(CCFLAGS) -o client client.o utils_v2.o 

client.o: utils_v2.h client.c
	$(CC) $(CCFLAGS) -c client.c

utils_v2.o: utils_v2.c utils_v2.h
	$(CC) $(CCFLAGS) -c utils_v2.c

clean:
	rm -f *.o
	rm -f  *.html
	rm -f $(ALL)	
