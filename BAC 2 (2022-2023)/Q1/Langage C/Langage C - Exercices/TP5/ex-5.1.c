#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main(int argc, char *argv[]) {
    int nbrMots = argc - 1;

    char** tableau = malloc(nbrMots * sizeof(char*));
    if (tableau == NULL) {
        perror("Erreur d'allocation mémoire");
        return 1;
    }

    for (int i = 0; i < nbrMots; ++i) {
        tableau[i] = (char*) malloc((strlen(argv[i + 1]) + 1) * sizeof(char));
        if (tableau[i] == NULL) {
            perror("Erreur d'allocation mémoire");
            return 1;
        }

        strcpy(tableau[i], argv[i + 1]);

        for (int j = 0; j < strlen(tableau[i]); ++j) {
            tableau[i][j] = toupper(tableau[i][j]);
        }
    }

    for (int i = 0; i < nbrMots; ++i) {
        printf("%s ", tableau[i]);
    }

    printf("\n");
    for (int i = 0; i < nbrMots; ++i) {
        printf("%s ", argv[i + 1]);
    }

    for (int i = 0; i < nbrMots; ++i) {
        free(tableau[i]);
    }
    free(tableau);
}
