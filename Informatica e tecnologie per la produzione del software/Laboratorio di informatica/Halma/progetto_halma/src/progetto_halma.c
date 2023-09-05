/*
 ============================================================================
 Name        : progetto_halma.c
 Author      : Andrea Braho, Marco Casamassima
 Description : Gioco halma
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "halma.h"
#include "stringhe.h"
#include "file.h"

int main() {
				/*  Funzione: main
				 * 	Input:	//
				 * 	Output: //
				 * 	Descrizione: modifica le dimensioni della console a 150x120. Acquisisce un input ed esegue la scelta.
				 * 				 Se l'utente inserisce 10 input errati consecutivi, il programma termina
				 * 	Autori: Marco Casamassima
				 * 	Revisione: 12/06/2017
				 */
	system("mode con:cols=150 lines=120"); // modifica le dimensioni della console a 150x120

	int cont_errori = 0; // indica il numero delle volte in cui l'utente ha inserito consecutivamente input non validi
	int turno;
	char pedina_giocatore;
	controlli check; // struct che colleziona i controlli
	char scacchiera[NUM_RIGHE][NUM_COLONNE];
	char scelta[2]; // scelta dell'utente nel menu'
	int check_partita; // vale 1 se c'è una partita salvata, 0 altrimenti

	check.esci = 0;

	do{
		visualizza_menu();
		printf("Inserire una scelta.\n"
			   "Premere invio per confermare.\n");
		scanf("%s", scelta);

		if(strcmp(scelta, "1") == 0) { // nuova partita
			system("cls");
			nuova_partita(&turno, &pedina_giocatore, scacchiera);
			gioca_partita(scacchiera, &turno, &pedina_giocatore, &check);
			cont_errori = 0;

			if(check.esci == 1)
				strcpy(scelta, "0");
		}
		else if(strcmp(scelta, "2") == 0) { // continua partita
				system("cls");
				check_partita = continua_partita(&turno, &pedina_giocatore, scacchiera);

				if(check_partita == 1) {
					gioca_partita(scacchiera, &turno, &pedina_giocatore, &check);
					cont_errori = 0;
				}
				else {
					cont_errori++;
					printf("Non c'e' una partita salvata.\n");
					system("PAUSE");
				}

				if(check.esci == 1)
					strcpy(scelta, "0");
			}
			else if(strcmp(scelta,"3") == 0) {	// regole
					system("cls");
					visualizza_regole();
					cont_errori = 0;
	    		  }
	    		  else if(strcmp(scelta,"4") == 0) {	// palmarès
 	    		  			system("cls");
	     		  			visualizza_palmares();
	    		  			cont_errori = 0;
				 		}
				 		else if(strcmp(scelta,"5") == 0) {	// informazioni
				 				system("cls");
				 				visualizza_informazioni();
				 				cont_errori = 0;
				 			 }
				 			 else if(strcmp(scelta,"0") != 0) { // scelta errata
				 				 	 	cont_errori++;
				 						printf("Hai inserito una scelta non presente nel menu'.\n"
				 							   "Le scelte sono: 1-2-3-4-5-0.\n");
				 						system("PAUSE");
				 			 	  }
		system("cls");
	}while(cont_errori < 10 && (strcmp(scelta, "0") != 0));

	if(cont_errori == 10)
		printf("Hai inserito troppe scelte errate.\n");

	printf("Uscita dal programma.\n");
	system("PAUSE");

}
