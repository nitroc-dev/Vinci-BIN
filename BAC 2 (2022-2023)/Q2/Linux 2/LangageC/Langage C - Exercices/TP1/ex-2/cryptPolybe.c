#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>

#include "cryptPolybe.h"

static char square[8][8] =
{{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'},
 {'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'},
 {'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X'},
 {'Y', 'Z', '0', '1', '2', '3', '4', '5'},
 {'6', '7', '8', '9', ' ', '!', '"', '#'},
 {'$', '%', '&', '\'', '(', ')', '*', '+'},
 {',', '-', '.', '/', ':', ';', '<', '='},
 {'>', '?', '@', '[', '\\', ']', '^', '_'}};

char* encryptPolybe (char* msg) {
    char* crypted = (char*) malloc(((2*strlen(msg))+1) * sizeof(char));
    if (crypted == NULL) {
        printf("Erreur lors de l'allocation du message crypte\n");
        return NULL;
    }
    char* ptr = crypted;
    bool found = false;
    for (char* c = msg; *c != '\0'; c++) {
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                if (*c == square[a][b]) {
                    *ptr = a + 1 + '0';
                    ptr++;
                    *ptr = b + 1 + '0';
                    ptr++;
                    found = true;
                    break;
                }
            }
            if (found == true) {
                found = false;
                break;
            }
        }
    }
    *ptr = '\0';
    return crypted;
}

char* decryptPolybe (char* msg) {
    char* crypted = (char*) malloc(((strlen(msg)/2) + 1) * sizeof(char));
    if (crypted == NULL) {
        printf("Erreur lors de l'allocation du message crypte\n");
        return NULL;
    }
    char* ptr = crypted;
    char* c = msg;
    while (*c != '\0') {
        int a = *c - '0' - 1;
        c++;
        int b = *c - '0' - 1;
        c++;
        *ptr = square[a][b];
        ptr++;
    }
    *ptr = '\0';
    return crypted;
}
