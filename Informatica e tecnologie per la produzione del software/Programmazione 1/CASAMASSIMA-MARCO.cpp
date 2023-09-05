// CASAMASSIMA MARCO 664138
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define NUM 30

// definizione del tipo biblioteca
typedef struct{
        char codice[6];
        char titolo[30];
        char anno[5];
        char cognome[30];
        char nome[30];
        char categoria[12];
        char presenza[3];
        char giornoP[3];
        char meseP[3];
        char annoP[5];
        char numero[10];
} biblioteca;
//-------------------------------

// prototipi
int caricaFile(biblioteca a[]);
void stampa(biblioteca a[], int num);
void stampaPrestito(biblioteca a[], int num);
void stampaCat(biblioteca a[], int num);
void ordina(biblioteca a[], int num);
void cornice();
//------------------------------------------

// inizio main
int main() {
    int scelta; // scelta dell'utente 
    int num; // numero dei libri
    biblioteca a[NUM];
    
    scelta = -1; // != 0 per entrare almeno una volta nel ciclo
    
    num = caricaFile(a);
    
    while(scelta != 0) {
                 system("cls");
                 cornice();
                 printf("Menu'\n\n"
                        "1: Visualizzare i dati.\n"
                        "2: Visualizzare i titoli che si trovano in prestito.\n"
                        "3: Visualizzare la categoria piu' rappresentata.\n"
                        "4: Ordinare i dati secondo l'anno di pubblicazione.\n"
                        "0: Uscire.\n\n\n\n\n"
                        "Inserire la scelta: ");
                 scanf("%d", &scelta);
                 system("cls");
                 cornice();
                 
                 switch(scelta) {
                                case 1: // visualizzazione dei dati
                                        stampa(a, num);
                                        break;
                                case 2: // visualizzazione del titolo dei libri che si trovano in prestito
                                        stampaPrestito(a, num);
                                        break;
                                case 3: // visualizzazione della categoria piu' rappresentata
                                        stampaCat(a, num);
                                        break;
                                case 4: // ordina i dati secondo l'anno di pubblicazione
                                        ordina(a, num);
                                        break;
                                case 0: // uscita
                                        printf("Fine del programma.\n");
                                        break;
                                default: // errore nella scelta
                                         printf("Errore nella scelta. Riprovare.\n");
                                         break;
                 }
                 puts("");
                system("PAUSE");
      }
      
return 0;
}
// --------------------------------------------------------------------------------------                 
                 
                        
                        
                        
                        
// funzione che legge dal file e scrive nella struct
int caricaFile(biblioteca a[]) {
    FILE *f; // puntatore al file
    int i;
    
    // apertura del file in lettura
    if((f = fopen ("biblioteca.txt", "r")) == NULL) {
          printf("Errore nell'apertura del file in caricaFile.\nUscita dal programma.\n");
          exit(1);
    }
    else
        printf("Apertura del file avvenuta con successo.\n");
    
    // trasferimento da file a struct    
    i = 0;
    while(!feof(f)) {
                   if((fscanf(f, "%s%s%s%s%s%s%s%s%s%s%s", a[i].codice, a[i].titolo, a[i].anno, a[i].cognome, a[i].nome, a[i].categoria, a[i].presenza, a[i].giornoP, a[i].meseP, a[i].annoP, a[i].numero)) !=EOF )
                                i++;
                                
    }
    fclose(f); // chiusura del file
    
return i; // ritorna al main il numero dei libri
}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
// procedura che visualizza i libri
void stampa(biblioteca a[], int num) {
     int i;
     printf("%-15s%-15s%-18s%-15s%-15s%-15s%-15s%-21s%s\n", "CODICE", "TITOLO", "PUBBLICAZIONE", "COGNOME", "NOME", "CATEGORIA", "PRESENZA", "DATA PRESTITO", "NUMERO DI TELEFONO");
     // visualizzazione dei dati
     for(i = 0; i < num; i++) {
           printf("------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
           printf("%-15s%-15s%-18s%-15s%-15s%-15s%-15s%-s-%-s-%-15s%s\n", a[i].codice, a[i].titolo, a[i].anno, a[i].cognome, a[i].nome, a[i].categoria, a[i].presenza, a[i].giornoP, a[i].meseP, a[i].annoP, a[i].numero);
     }
     printf("------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
return;
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// procedura che visualizza i titoli che si trovano in prestito
void stampaPrestito(biblioteca a[], int num) {
     int i;
     printf("I titoli che si trovano in prestito sono:\n");
     
     // ricerca e stampa dei titoli in prestito
     for(i = 0; i < num; i++) {
           if((strcmp(a[i].presenza, "NO")) == 0)
                                     printf("%s\n", a[i].titolo);
     }
return;
}
//---------------------------------------------------------------

//procedura per la visualizzazione della categoria piu' rappresentata
void stampaCat(biblioteca a[], int num) {
     int i;
     int b[4]; // array in cui si conta la presenza di un determinato genere: 0 per diritto, 1 per informatica, 2 per economia, 3 per fisica, 4 per altro
     int max; // variabile per la ricerca del massimo
     int j; // indice del massimo
    
     // inizializzazione dell'array b
     for(i = 0; i < 4; i++)
           b[i] = 0;
     
     // ricerca e incremento delle categorie 
     for(i = 0; i < num; i++) {
           if((strcmp(a[i].categoria, "DIRITTO")) == 0)
                                      b[0]++;
           else
               if((strcmp(a[i].categoria, "INFORMATICA")) == 0)
                                          b[1]++;
               else
                   if((strcmp(a[i].categoria, "ECONOMIA")) == 0)
                                              b[2]++;
                   else
                       if((strcmp(a[i].categoria, "FISICA")) == 0)
                                                  b[3]++;
                       else
                           b[4]++;
     }
     
     // stampa della categoria piu' rappresentata
     j = 0;
     max = b[0];
     printf("La categoria piu' rappresentata e': ");
     for(i = 1; i < 4; i++) {
           if(b[i] > max) {
                   max = b[i];
                   j = i;
           }
     }
     
     if(j == 0)
          printf("Diritto.\n");
     else
         if(j == 1) 
              printf("Informatica.\n");
         else
             if(j == 2)
                  printf("Economia.\n");
             else
                 if(j == 3)
                      printf("Fisica.\n");
                 else
                     printf("Altro.\n");
return;
}
//------------------------------------------------------------------

// procedura per ordinare i dati secondo l'anno di pubblicazione
void ordina(biblioteca a[], int num) {
     int i;
     int j;
     biblioteca temp; // variabile temporanea per l'ordinamento
     
     // ordinamento a bolle
     for(i = 1; i < num; i++) {
           for(j = 0; j < num - i; j++) {
                 if((strcmp(a[j].anno, a[j+1].anno)) > 0) {
                              // scambio
                              temp = a[j];
                              a[j] = a[j+1];
                              a[j+1] = temp;
                 }
           }
     }
     
     printf("Ordinamento effettuato.\n");
     
return;
}
//--------------------------------------------------------------

// procedura per la visualizzazione della cornice
void cornice() {
     printf("\t\t\t\t\t||||||||||||||||||||||||||||||||||||||||||||||||||||\n"
            "\t\t\t\t\t|||||||||||||||||||| BIBLIOTECA ||||||||||||||||||||\n"
            "\t\t\t\t\t||||||||||||||||||||||||||||||||||||||||||||||||||||\n\n\n");
return;
}
//----------------------------------------------------------------------------------
