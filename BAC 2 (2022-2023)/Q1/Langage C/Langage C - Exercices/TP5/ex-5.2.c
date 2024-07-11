#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_WORD 27

int main(int argc, char *argv[]) {
    int nbrMots = argc - 1;

    printf("Arguments: ");
    for (int i = 1; i < argc; i++) {
        printf("\n- %s", argv[i]);
    }

    bool motPresent = false;
    int nbrAbsent = 0;
    printf("\nQuel est le mot à rechercher ? : ");
    char motRecherche[MAX_WORD + 2];

    while (fgets(motRecherche, MAX_WORD+2, stdin) != NULL) {
        int taille = strlen(motRecherche)-1;

        if (motRecherche[taille] != '\n') {
            printf("Erreur: le mot entré est trop long.\n");
            while (fgets(motRecherche, MAX_WORD+2, stdin) && motRecherche[strlen(motRecherche)-1] != '\n') ;
            continue;
        }

        motRecherche[taille] = '\0';
        printf("\t'%s'\tlongueur = %d\n",motRecherche, (int)strlen(motRecherche));

        motPresent = false;
        for (int i=1;i<=nbrMots;i++) {
            if (!strcasecmp(motRecherche, argv[i])){
                motPresent = !motPresent;
                break;
            }
        }
        if (motPresent) {
            printf("'%s' est present dans la table\n", motRecherche);
        } else {
            printf("'%s' est absent de la table\n", motRecherche);
            nbrAbsent++;
        }
    }

    // affichage des résultats
    printf("\n%d mots ont ete lus et n'etaient pas dans la table\n",nbrAbsent);
}
