#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "crypt.h"
#include "utils_v1.h"

static char square[8][8] = {
  {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'},
  {'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'},
  {'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X'},
  {'Y', 'Z', '0', '1', '2', '3', '4', '5'},
  {'6', '7', '8', '9', ' ', '!', '"', '#'},
  {'$', '%', '&', '\'', '(', ')', '*', '+'},
  {',', '-', '.', '/', ':', ';', '<', '='},
  {'>', '?', '@', '[', '\\', ']', '^', '_'}
};

//************************************************************************
// MAP: tableau permettant de convertir un caractere ASCII en code Polibe
// ex:  map['B'] = 12
//************************************************************************
static int* createMap() {
   int* res = (int*) malloc(128 * sizeof(int));   // 128 = nombre de caractères du code ascii standard (7 bits)
    if (!res) {perror("Polybe map creation"); exit(15); }
    
    for(int i = 0; i != 8; i++) {
      for(int j = 0; j != 8; j++) {
         res[(int)square[i][j]] = (i + 1) * 10 + (j + 1);
      }
    }
    return res;
}

static int* map = NULL;
static int* getMap() {
    if (map == NULL) {
        map = createMap();
    }
    return map;
}

//*******************************************************************************
// MAP INVERSE: tableau permettant de traduire un code Polibe en caractère ASCII
// ex: mapInv[12] = 'B'
//*******************************************************************************
static int* createMapInv() {
   int* res = (int*) malloc((88+1) * sizeof(int));  // 88 = le plus grand code Polibe défini
    if (!res) {perror("Polybe map inverse creation"); exit(16); }
    
    for(int i = 0; i != 8; i++) {
      for(int j = 0; j != 8; j++) {
        res[(i + 1) * 10 + (j + 1)] = square[i][j];
      }
    }
    return res;
}

static int* mapInv = NULL;
static int* getMapInv() {
    if (mapInv == NULL) {
        mapInv = createMapInv();
    }
    return mapInv;
}

//******************************************************
// PUBLIC FUNCTIONS
//******************************************************
char* cryptPolybe(char* msg) {
    int* map = getMap();

    int sz = strlen(msg);
    char* res = (char*) malloc((2 * sz + 1) * sizeof(char));
    if (!res) {perror("Polybe crypt"); exit(17); }

    // version avec traitement de chaînes
    char* ptr = res;
    for(int i = 0; i != sz; i++) {
       sprintf(ptr,"%i", map[(int)msg[i]]);  // conversion du numéro de ligne en caractère
       ptr+=2;
    }

    /*
    // version avec traitement caractère par caractère
    for(int i = 0; i != sz; i++) {
       res[2 * i + 0] = '0' + map[(int)msg[i]] / 10;  // conversion du numéro de ligne en caractère
       res[2 * i + 1] = '0' + map[(int)msg[i]] % 10;  // conversion du numéro de colonne en caractère
    }
    res[2 * sz] = '\0';
    */
    
    free(map);
    return res;
}

char* decryptPolybe(char* msg) {
    int* mapInv = getMapInv();

    int sz = strlen(msg)+1;
    int sz3 = sz/2;
    char* res = (char*) malloc((sz3+1) * sizeof(char));
    if (!res) {perror("Polybe decrypt"); exit(18); }
    
    for (int i = 0; i != sz3; i++) {
        int code = (msg[2 * i] - '0') * 10;  // conversion d'un caractère en numéro de ligne * 10
        code += msg[2 * i + 1] - '0';        // conversion d'un caractère en numéro de colonne
        res[i] = mapInv[code];
    }
    res[sz3] = '\0';

    free(mapInv);
    return res;
}
