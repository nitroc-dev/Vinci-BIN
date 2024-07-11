#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "utils_v2.h"

//***************************************************************************
// MAIN
//***************************************************************************

int main(int argc, char *argv[]) {
  ssigaction(SIGHUP, SIG_IGN);
  execv(argv[1], argv+1);
  perror("ERROR EXECV");
  exit(EXIT_FAILURE);
}

/*

EXEMPLE D'APPEL:

Ping exécuté en background:

    ./a.out /usr/bin/ping google.com &

Fermeture du terminal. Puis vérification de la persistance 
du processus ping dans un autre terminal:

    ps -ef | grep "/usr/bin/ping"

Terminaison du processus ping:

    ps -ef | grep "/usr/bin/ping"   # identification du PID de ping
    kill ping_PID

Terminaison en une commande (attention: tue tous vos processus "ping"!):

    pkill ping
OU 
    pgrep ping | xargs kill

*/
