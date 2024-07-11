#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    int n = 10000000;
    FILE *fp;
    clock_t start, end;
    double cpu_time_used;

    fp = fopen("numbers.bin", "wb");
    if (fp == NULL) {
        printf("Erreur lors de l'ouverture du fichier.\n");
        exit(1);
    }

    start = clock();
    for (int i = 0; i < n; i++) {
        fwrite(&i, sizeof(int), 1, fp);
    }
    end = clock();

    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Temps d'execution : %f secondes\n", cpu_time_used);

    fclose(fp);
    return 0;
}
