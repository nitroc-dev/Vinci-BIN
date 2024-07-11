#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>

#include "utils_v2.h"

#define KEY 369
#define PERM 0666

//******************************************************************************
//CHILD HANDLER
//******************************************************************************
void child_handler () {
  // CREATE SHARED MEMORY
  int shm_id = sshmget(KEY, sizeof(int), IPC_CREAT | PERM);
  int* z = sshmat(shm_id);

  printf("IN CHILD: shared mem value: *z = %d\n", *z);
  *z = 987654321;

  sshmdt(z);
}

//******************************************************************************
//MAIN FUNCTION
//******************************************************************************
int main (int argc, char *argv[]) {
  pid_t cpid = fork_and_run0(&child_handler);
  
  swaitpid(cpid, NULL, 0);  // make sure child finishes before accessing shared memory
  
  // GET SHARED MEMORY 
  int shm_id = sshmget(KEY, sizeof(int), 0);
  int* z = sshmat(shm_id);
  
  printf("IN PARENT: shared mem value: *z = %d\n", *z);
  
  sshmdt(z);

  //COMMENT OR UNCOMMENT
  sshmdelete(shm_id);
}




 