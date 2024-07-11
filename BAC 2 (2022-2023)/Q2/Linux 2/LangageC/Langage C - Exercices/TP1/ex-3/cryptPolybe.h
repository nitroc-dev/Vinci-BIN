#ifndef _CRYPTPOLYBE_H_
#define _CRYPTPOLYBE_H_

/**
 * Crypte une chaîne de caractères avec le carré de Polybe
 * PRE: s : chaîne à crypter
 * RES: renvoie une nouvelle chaîne contenant la chaîne s cryptée avec le carré de Polybe; 
 *      la chaîne ayant été allouée dynamiquement, elle doit être libérée
 */
char* cryptPolybe (char * s);

/**
 * Décrypte une chaîne de caractères cryptée avec la fonction cryptPolybe()
 * PRE: s : chaîne à décrypter
 * RES: renvoie une nouvelle chaîne contenant la chaîne s décryptée avec le carré de Polybe;
 *      la chaîne ayant été allouée dynamiquement, elle doit être libérée
 */
char* decryptPolybe (char * s);

#endif   // _CRYPTPOLYBE_H_
