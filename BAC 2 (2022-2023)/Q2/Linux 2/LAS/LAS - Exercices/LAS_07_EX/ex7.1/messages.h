#ifndef _MESSAGES_H_
#define _MESSAGES_H_

#define SERVER_PORT 9501
#define SERVER_IP "127.0.0.1" /* localhost */
#define MAX_PSEUDO 256

// #define INSCRIPTION_REQUEST 10
// #define INSCRIPTION_OK 11
// #define INSCRIPTION_KO 12

typedef enum
{
  INSCRIPTION_REQUEST = 10,
  INSCRIPTION_OK = 11,
  INSCRIPTION_KO = 12
} Code;

/* struct message used between server and client */
typedef struct
{
  char messageText[MAX_PSEUDO];
  int code;
} StructMessage;
#endif
