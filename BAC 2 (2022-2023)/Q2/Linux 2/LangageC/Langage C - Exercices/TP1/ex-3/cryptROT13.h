#ifndef _CRYPTROT13_H_   // ou:  #if ! defined _CRYPTROT13_H_
#define _CRYPTROT13_H_

/**
 * Crypte une chaîne de caractères avec ROT13
 * PRE: s : chaîne à crypter
 * RES: renvoie une nouvelle chaîne contenant la chaîne s cryptée en ROT13; 
 *      la chaîne ayant été allouée dynamiquement, elle doit être libérée
 */
char* cryptROT13 (char * s);

/**
 * Décrypte une chaîne de caractères cryptée avec la fonction cryptROT13()
 * PRE: s : chaîne à décrypter
 * RES: renvoie une nouvelle chaîne contenant la chaîne s décryptée en ROT13;
 *      la chaîne ayant été allouée dynamiquement, elle doit être libérée
 */
char* decryptROT13 (char * s);

#endif   // _CRYPTROT13_H_
