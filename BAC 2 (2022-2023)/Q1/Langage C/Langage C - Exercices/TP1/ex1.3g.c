#include <stdio.h>

int main () {
    int car;  // int et pas char car comparaison avec EOF=-1 renvoyé par getchar()
    
    printf("Entrez des nombres composés d'un seul chiffre.\n");

    while ((car = getchar()) != EOF) {  // lecture du premier caractère de la ligne
        if (car == '\n')  // traitement du passage à la ligne
            continue;

        int nbre = car - '0';  // récupération de la valeur entière
        int fact = 1;
        int i = 2; 
        while (i <= nbre) {
            fact *= i;
            i++;
        }
        printf("%d! = %d\n", nbre, fact);
    }
}