#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define NB_CHAR 254
#define NB_COFFRES 10
#define TAILLE_ID 5

int main (int argc, char *argv[]) {

    int taillePhys = NB_COFFRES;
    int tailleLog = 0;
    char** coffres = (char**) malloc(taillePhys * sizeof(char*));
    if (coffres == NULL) {
        perror("erreur d'alloc");
        exit(1);
    }

    char ligne [NB_CHAR+2];
    while (fgets(ligne, NB_CHAR+2, stdin) != NULL) {
        ligne[strlen(ligne)-1] = '\0';

        bool contains = false;
        int i = 0;
        while (i < tailleLog && !contains) {
            if (strncmp(ligne, coffres[i], TAILLE_ID) == 0) {
                contains = true;
            } else {
                i++;
            }
        }

        if (!contains) {
            if (tailleLog == taillePhys) {
                taillePhys *= 2;
                if ((coffres = (char**) realloc(coffres, taillePhys * sizeof(char*))) == NULL) {
                    perror("erreur d'alloc");
                    exit(1);
                }
            }

            if ((coffres[tailleLog] = (char*) malloc((strlen(ligne)+1) * sizeof(char))) == NULL) {
                perror("erreur d'alloc");
                exit(1);
            }

            strcpy(coffres[tailleLog], ligne);
            tailleLog++;
        } else {

            size_t tailleChaine = strlen(coffres[i]) + strlen(ligne+TAILLE_ID) + 1;
            if ((coffres[i] = (char*) realloc(coffres[i], tailleChaine * sizeof(char))) == NULL) {
                perror("erreur d'alloc");
                exit(1);
            }

            strcat(coffres[i], ligne+TAILLE_ID);
        }
    }

    printf("\nCOFFRES-FORTS:\n\n");
    for (int i = 0 ; i < tailleLog; i++) {
        printf("%s\n", coffres[i]);
    }

    for (int i = 0 ; i < tailleLog; i++)
        free(coffres[i]);
    free(coffres);

    exit(EXIT_SUCCESS);
}
