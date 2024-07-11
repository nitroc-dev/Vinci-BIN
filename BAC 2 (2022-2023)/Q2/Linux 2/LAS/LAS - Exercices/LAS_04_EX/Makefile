CFLAGS=-std=c11 -pedantic -Wall -Wvla -Werror -Wno-unused-variable -D_DEFAULT_SOURCE

ALL= 4.1a 4.1b 4.1c 4.2 4.3 4.4

all: $(ALL)

4.1a: 4.1a.c utils_v2.o
	gcc $(CFLAGS) -o 4.1a 4.1a.c utils_v2.o

4.1b: 4.1b.c utils_v2.o
	gcc $(CFLAGS) -o 4.1b 4.1b.c utils_v2.o

4.1c: 4.1c.c utils_v2.o
	gcc $(CFLAGS) -o 4.1c 4.1c.c utils_v2.o

4.2: 4.2.c utils_v2.o
	gcc $(CFLAGS) -o 4.2 4.2.c utils_v2.o

4.3: 4.3.c utils_v2.o
	gcc $(CFLAGS) -o 4.3 4.3.c utils_v2.o

4.4: 4.4.c utils_v2.o
	gcc $(CFLAGS) -o 4.4 4.4.c utils_v2.o

utils_v2.o: utils_v2.c utils_v2.h
	gcc $(CFLAGS) -c utils_v2.c

clean:
	rm -f *.o
	rm -f $(ALL)
