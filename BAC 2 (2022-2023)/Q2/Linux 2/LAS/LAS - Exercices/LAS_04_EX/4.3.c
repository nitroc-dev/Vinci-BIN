#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>   // usage toupper()
#include <signal.h>
#include <string.h>
#include "utils_v2.h"

#define BUFFERSIZE 80

//***************************************************************************
// SIGNAL HANDLER
//***************************************************************************

static const char *const SENTENCE = "--> Signal SIGPIPE recu par le pere: "\
                     "impossible de transmettre des donnees sur le pipe\n";

void sighandler (int sig) {
   const size_t SZ = strlen(SENTENCE);
   write(1, SENTENCE, SZ);
   _exit(128+sig);  // cf. https://tldp.org/LDP/abs/html/exitcodes.html
}

//***************************************************************************
// CHILD CODE
//***************************************************************************

void upper (char * str) {
   for (int i=0; str[i] != '\0';i++)
      str[i] = toupper(str[i]);
}

void run (void *args) {
   // FILS //
   int *pipefd = args;

   // configuration pipe
   sclose(pipefd[1]);  // fermeture du pipe en écriture
   sclose(pipefd[0]);  // fermeture du pipe en lecture ==> SIGPIPE si écriture sur pipefd[1]!
   
   int i=0;
   while (1) {
      i++;
   }
}

//***************************************************************************
// MAIN
//***************************************************************************

int main (int argc, char ** argv)
{
   int nbChar;

   // crétion pipe
   int pipefd[2];
   spipe(pipefd);

   fork_and_run1(run, pipefd);

   // PARENT //
   char line[BUFFERSIZE+1];

   // armement du handler de SIGPIPE
   ssigaction(SIGPIPE, sighandler);
   
   // configuration pipe
   sclose(pipefd[0]);

   printf("Entrez des chaines de caracteres (Ctrl-D pour terminer):\n");
   while((nbChar = read(0,line,BUFFERSIZE))) {
      checkCond(nbChar<0,"read parent entrée standard");
      printf("Ecriture de la chaine dans le pipe\n");
      nwrite(pipefd[1],line,nbChar);    // écriture sur pipe sans lecteur ==> SIGPIPE et write renvoie -1
   }
}

/*
    Standard exit codes:  https://tldp.org/LDP/abs/html/exitcodes.html
    
    echo $? --> 141 
    
    141 = 128 + n, fatal error on signal n, où n=13 signifie le signal SIGPIPE
*/