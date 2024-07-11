#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#include "ipc_conf.h"
#include "utils_v2.h"

//*****************************************************************************
// USAGE
//*****************************************************************************
void checkUsage (int argc, char *argv[]) {
  if (argc != 2 || (!strcmp(argv[1],"-c") && !strcmp(argv[1],"-d"))) {
    printf("Usage:\n");
    printf("       %s -c   to create IPCs\n", argv[0]);
    printf("       %s -d   to destroy IPCs\n", argv[0]);
    exit(EXIT_FAILURE);
  }
}

//******************************************************************************
// MAIN FUNCTION
//******************************************************************************

int main (int argc, char *argv[]) {
  checkUsage(argc,argv);
  
  if (!strcmp(argv[1],"-c")) {
    // IPC creation and initialization
    sshmget(LLN_SHM_KEY, 2 * sizeof(int), IPC_CREAT | IPC_EXCL | PERM);
    sem_create(LLN_SEM_KEY, 1, IPC_CREAT | IPC_EXCL | PERM, 1);   
    printf("IPCs created.\n");
  } else {  // argv[1] = "-d"
    // IPC destruction
    printf("Destroying IPCs...\n");
    int shm_id = sshmget(LLN_SHM_KEY, 2 * sizeof(int), 0);
    sshmdelete(shm_id);
  
    int sem_id = sem_get(LLN_SEM_KEY, 2);
    sem_delete(sem_id);

    printf("IPCs freed.\n");
  }
  
  exit(EXIT_SUCCESS);
}
