#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <time.h>

int main() {
    int n = 10000000;
    int fd;
    clock_t start, end;
    double cpu_time_used;

    fd = open("numbers.bin", O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);
    if (fd == -1) {
        printf("Erreur lors de l'ouverture du fichier.\n");
        exit(1);
    }

    start = clock();
    for (int i = 0; i < n; i++) {
        write(fd, &i, sizeof(int));
    }
    end = clock();

    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Temps d'execution : %f secondes\n", cpu_time_used);

    close(fd);
    return 0;
}
