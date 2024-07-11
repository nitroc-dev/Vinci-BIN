#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "cryptMorse.h"

#define MAX_LEN  5   // 4 (longueur maximale du code morse d'une lettre) + 1 (un espace)

static char* lettres_morse[] =
{  /* A */ ".-",
   /* B */ "-...",
   /* C */ "-.-.",
   /* D */ "-..",
   /* E */ ".",
   /* F */ "..-.",
   /* G */ "--.",
   /* H */ "....",
   /* I */ "..",
   /* J */ ".---",
   /* K */ "-.-",
   /* L */ ".-..",
   /* M */ "--",
   /* N */ "-.",
   /* O */ "---",
   /* P */ ".--.",
   /* Q */ "--.-",
   /* R */ ".-.",
   /* S */ "...",
   /* T */ "-",
   /* U */ "..-",
   /* V */ "...-",
   /* W */ ".--",
   /* X */ "-..-",
   /* Y */ "-.--",
   /* Z */ "--..",
   NULL};
   

static int spaceCount (char* s) {
   int cnt = 0;
   for (char* ptr=s; *ptr!='\0'; ptr++)
      if (*ptr == ' ')
         cnt++;
   return cnt;
}

/*
static int spaceOffset (char* s) {
   for (int i=0; i<strlen(s); i++)
      if (s[i] == ' ')
         return i;
   return -1;
}
*/
   
char* cryptMorse (char* msg) {
   int sz = strlen(msg);
   int spaces = spaceCount (msg);
   int tailleMax = MAX_LEN*sz + spaces*2;  // longueur maximale du code morse correspondant à la chaîne msg
   char* res = (char*) malloc((tailleMax + 1) * sizeof(char));
   if (!res) {perror("Morse crypt"); exit(15);}

   int l;
   char* code;
   int i=0;
   res[0] = '\0';
   char* resPtr = res;
   while (i < sz) {
      if (msg[i] == ' ') {
         // traitement d'un espace
         strcpy(resPtr, "   ");
         resPtr += 3;
      } else {
         // traitement d'une lettre
         l = toupper(msg[i]) - 'A';
         code = lettres_morse[l];
         strcpy(resPtr, code);
         resPtr += strlen(code);
         strcpy(resPtr, " ");
         resPtr++;
      }
      i++;
   }
   return res;
}

// version utilisant strtok()  (cf. chapitre 5 du syllabus)
char* decryptMorse (char* msg) {
   int sz = strlen(msg);
   char* res = (char*) malloc(sz * sizeof(char));  // le code morse est plus long que la phrase correspondante
   if (!res) {perror("Morse decrypt"); exit(16);}

   int indice = 0;
   char* ptr = msg;
   char* token;
   while ((token = strtok(ptr, " ")) != NULL){
      for (int i=0; i<26; i++)
         if (strcmp(token, lettres_morse[i]) == 0) {
            res[indice] = 'A' + i;
            indice++;
            break;
         }
      ptr = NULL;
   }
   res[indice] = '\0';
   return res;
}

// version traitant la chaîne caractère par caractère
/*
char* decryptMorse (char* msg) {
   char code[MAX_LEN];
   int sz = strlen(msg);
   char* res = (char*) malloc(sz * sizeof(char));  // le code morse est plus long que la phrase correspondante
   if (!res) {perror("Morse decrypt"); exit(16);}

   int indice = 0;
   char* ptr = msg;
   while (*ptr != '\0') {
      if (*ptr == ' ') {
         if (*(ptr+1) == ' ') {
            // 3 espaces en morse se traduisent par 1 espace 
            res[indice] = ' ';
            indice++;
            ptr += 2;  // 1er espace déjà traité
         } else {
            // 1 espace unique en morse est un séparateur de lettre --> pas de traduction
            ptr++;
         }
      } else {
         // traitement d'un code morse
         int offset = spaceOffset(ptr);
         if (offset == -1) {  // fin de la chaîne msg
            strcpy(code, ptr);
            ptr += strlen(ptr);
         }
         else {
            strncpy(code, ptr, offset);
            code[offset] = '\0';
            ptr += offset+1;
         }

         int i = 0;
         while (lettres_morse[i] != NULL) {
            if (strcmp(code, lettres_morse[i]) == 0) {
               res[indice] = i + 'A';
               indice++;
               break;
            }
            i++;
         }
      }
   }
   res[indice] = '\0';
   return res;
}
*/
