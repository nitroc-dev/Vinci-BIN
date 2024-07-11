#ifndef _CRYPTMORSE_H_
#define _CRYPTMORSE_H_

/**
 * Crypte une chaîne de caractères en morse
 * PRE: s : chaîne à crypter
 * RES: renvoie une nouvelle chaîne contenant la chaîne s cryptée en morse; 
 *      la chaîne ayant été allouée dynamiquement, elle doit être libérée
 */
char* cryptMorse (char * s);

/**
 * Décrypte une chaîne de caractères cryptée avec la fonction cryptMorse()
 * PRE: s : chaîne à décrypter
 * RES: renvoie une nouvelle chaîne contenant la chaîne s décryptée du morse;
 *      la chaîne ayant été allouée dynamiquement, elle doit être libérée
 */
char* decryptMorse (char * s);

#endif   // _CRYPTMORSE_H_
