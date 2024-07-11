#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int* prime_numbers (int n, int* sz);

int main (int argc, char *argv[]) {
    int n = 100;
    int sz = 0;
    int* primes = prime_numbers(n, &sz);
    for (int i = 0; i < sz; i++) {
        printf("%d ", primes[i]);
    }
}

int* prime_numbers (int n, int* sz) {
    int* primes = NULL;
    primes = malloc(n * sizeof(int));
    *sz = 0;
    for (int i = 2; i <= n; i++) {
        bool is_prime = true;
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                is_prime = false;
                break;
            }
        }
        if (is_prime) {
            primes[*sz] = i;
            *sz += 1;
        }
    }
    return primes;
}
