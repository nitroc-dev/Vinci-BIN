#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "utils_v1.h"
#include "cryptROT13.h"
#include "cryptPolybe.h"
#include "cryptMorse.h"

void menu1 () {
   printf("1. Cryptage\n"\
          "2. Décryptage\n"\
          "Entrez votre choix (Ctrl-D pour terminer): ");
}

void menu2 () {
   printf("\n1. ROT13\n"\
          "2. Polybe\n"\
          "3. Morse\n"\
          "Entrez votre choix: ");
}

int main () {
   int c1,c2;
   char* output;

   menu1();
   while (scanf("%d",&c1) != EOF) {
      menu2();
      scanf("%d",&c2);
      getchar(); // lecture du passage à la ligne
      
      printf("\nEntrez la phrase à traiter (en MAJUSCULES):\n");
      colorOn(1,PURPLE_TEXT);
      char* msg = readLine();
      colorOff();
      
      switch (c1*10+c2) {
      case 11:
         output = cryptROT13(msg);
         break;
      case 12:
         output = cryptPolybe(msg);
         break;
      case 13:
         output = cryptMorse(msg);
         break;
      case 21:
         output = decryptROT13(msg);
         break;
      case 22:
         output = decryptPolybe(msg);
         break;
      case 23:
         output = decryptMorse(msg);
         break;
      }
   
      printf("\nRésultat:\n");
      printColor("%s\n\n", output, CYAN_TEXT);
   
      free(msg);
      free(output);

      menu1();
   }
      
   exit(EXIT_SUCCESS);      
}
