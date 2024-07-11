#include <stdio.h>

int max (int x, int y);


int main (int argc, char *argv[]) {
    int x = 5;
    int y = 10;
    int z = max(x, y);
    printf("The max of %d and %d is %d", x, y, z);
}

int max (int x, int y) {
    if (x > y) {
        return x;
    } else {
        return y;
    }
}
