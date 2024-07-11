#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#define MAX 100

int* prime_numbers (int n, int* sz);
void first_prime_numbers (int* t, int sz);

int main() {
    int sz;
    int *t = prime_numbers(MAX, &sz);

    printf("There are %d prime numbers less than %d: \n", sz, MAX);
    for(int i = 0; i < sz; i++) {
        printf("\t[%d] %d\n", i+1, t[i]);
    }
    free(t);

    printf("The fisrt %d prime numbers are: \n", MAX);
    int pn[MAX];
    first_prime_numbers (pn, MAX);
    for(int i = 0; i != MAX; i++) {
        printf("\t[%d] %d\n", i+1, pn[i]);
    }
}

void init (bool* t, int sz, bool v) {
    for(int i = 0; i != sz; i++) {
        t[i] = v;
    }
}

int* prime_numbers (int n, int* sz) {
    bool *erato = malloc(n * sizeof(bool));
    if (erato == NULL) {
        return NULL;
    }

    init(erato, n, true);
    erato[0] = false;
    erato[1] = false;

    *sz = 0;
    for(int i = 2; i < n; i++) {
        if (erato[i]) {
            (*sz)++;
            for(int j = 2 * i; j < n; j += i) {
                erato[j] = false;
            }
        }
    }

    int *res = malloc(*sz * sizeof(int));
    if (res == NULL) {
        return NULL;
    }

    int j = 0;
    for(int i = 0; i < n; i++) {
        if (erato[i]) {
            res[j] = i;
            j++;
        }
    }

    free(erato);
    return res;
}

void first_prime_numbers (int* t, int sz) {
    int n = sz;
    int szr;
    int* pn = prime_numbers(n, &szr);

    while(pn != NULL && szr < sz) {
        n = n * 2;
        free(pn);
        pn = prime_numbers(n, &szr);
    }

    if (pn != NULL) {
        for(int i = 0; i < sz; i++) {
            t[i] = pn[i];
        }
        free(pn);
    }
}
