#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define SYSCALLS 0

static const char* FNAME = "output.bin";
static const int   NB_MAX= 5000000;

/* 
Performance test
write syscall vs fwrite (bufferisation)

Change #DEFINE SYCALLS 
#define SYSCALLS 0 -> write used
#define SYSCALLS 1 -> fwrite used

*/

int main(int argc, char** argv) {

#if SYSCALLS
	int out = open(FNAME, O_WRONLY | O_TRUNC | O_CREAT);
	if (out < 0) 
#else 
	FILE* out = fopen(FNAME, "w");
	if (!out)
 #endif
	{
		perror("houston on a un probleme");
		exit(42);
	}


	for (int i = 0; i < NB_MAX; i++) {
#if SYSCALLS	
		int written = write(out, &i, sizeof(int));
		if (written != sizeof(int))
#else
		int written = fwrite(&i, sizeof(int), 1, out);
		if (written != 1)	
#endif
		{
			perror("houston on a un autre probleme");
			exit(24);
		}
	
	}

#if SYSCALLS
	close(out);
#else
	fclose(out);
#endif
}
