#include <stdio.h>
#include <stdlib.h>

#include "cryptPolybe.h"
#include "utils_v1.h"

int main(int argc, char const *argv[])
{
    char *str = (char*) malloc(sizeof(char) * 255);
    printf("Enter a string to encrypt: ");
    str = readLine();
    printf("Encrypted string: %s", encryptPolybe(str));
    return 0;
}