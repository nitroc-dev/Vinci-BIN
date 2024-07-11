#include <stdio.h>
#include <stdbool.h>

bool search (int* t, int sz, int x);

int main (int argc, char *argv[]) {
    int t[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int sz = sizeof(t) / sizeof(t[0]);
    int x = 5;
    bool found = search(t, sz, x);
    if (found) {
        printf("Found %d in the array", x);
    } else {
        printf("Did not find %d in the array", x);
    }
}

bool search (int* t, int sz, int x) {
    for (int i = 0; i < sz; i++) {
        if (t[i] == x) {
            return true;
        }
    }
    return false;
}
