/*
 * halma.c
 *
 *  Modifica: 01/07/2017
 *      Autori: Marco Casamassima, Andrea Braho
 *      Descrizione: Libreria contenente funzioni per il gioco halma
 */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "halma.h"
#include "stringhe.h"
#include "file.h"

void visualizza_menu(){
		/*  Funzione: visualizza_menu
		 * 	Input:	//
		 * 	Output: //
		 * 	Descrizione: visualizza il titolo e il menù del gioco
		 * 	Autori: Andrea Braho
		 * 	Revisione: 12/06/2017
		 */

	printf("\t\t _______________________________________________________________________\n"
		   "\t\t| _____________________________________________________________________ |\n"
		   "\t\t||                                                                     ||\n"
		   "\t\t||                                HALMA                                ||\n"
		   "\t\t||_____________________________________________________________________||\n"
		   "\t\t|_______________________________________________________________________|\n\n\n");

	printf("1. Nuova partita\n");
	printf("2. Continua partita\n");
	printf("3. Regole\n");
	printf("4. Palmares\n");
	printf("5. Informazioni\n");
	printf("0. Esci \n\n");
}

void nuova_partita(int *turno,char *pedina_giocatore,char scacchiera[][NUM_COLONNE]){
			/*  Funzione: nuova_partita
			 * 	Input:	*turno
			 * 			Significato: turno corrente della partita
			 * 			Tipo: puntatore ad intero
			 *
			 * 			*pedina_giocatore
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: puntatore a carattere
			 *
			 * 			scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array bidimensionale di caratteri
			 * 	Output: //
			 * 	Descrizione: inizializza turno , pedina_giocatore e scacchiera (inserendo le pedine nelle loro posizioni iniziali)
			 * 	Autori: Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */
	*turno = 1;
	*pedina_giocatore = 'O';

    int i, j;
    int k; // indica il numero di simboli in meno per ogni riga

    // La scacchiera viene inizializzata con gli spazi vuoti
    for(i = 0; i < NUM_RIGHE; i++) {
    	for(j = 0; j < NUM_COLONNE; j++) {
    		scacchiera[i][j] = ' ';
		}
	}

	k = 0; 			/*
						ex: xxxx	0 simboli in meno k = 0
							xxx 	1 simbolo in meno k = 1
							xx		2 simboli in meno k = 2
							x		3 simboli in meno k = 3
					*/

	// Viene inserito il simbolo 'X' nelle posizioni iniziali
	for(i = 0; i < NUM_RIGHE/2; i++) {
    	for(j = 0; j < (NUM_COLONNE/2)-k; j++) {
		    	scacchiera[i][j] = 'X';
		}
		k++;
	}

	// Viene inserito il simbolo 'O' nelle posizioni iniziali
	k = 0;			/*
						ex:    o		3 simboli in meno k = 3
							  oo		2 simboli in meno k = 2
							 ooo		1 simbolo in meno k = 1
							oooo		0 simboli in meno k = 0
					*/
	for(i = NUM_RIGHE-1; i >= NUM_RIGHE/2; i--) {
		for(j = (NUM_COLONNE/2)+k; j < NUM_COLONNE; j++) {
			scacchiera[i][j] = 'O';
		}
		k++;
	}

}

void gioca_partita(char scacchiera[][NUM_COLONNE], int *turno, char *pedina_giocatore, controlli *check) {
			/*  Funzione: gioca_partita
			 * 	Input:	scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array bidimensionale di caratteri
			 *
			 * 			*turno
			 * 			Significato: turno corrente della partita
			 * 			Tipo: puntatore ad intero
			 *
			 * 			*pedina_giocatore
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: puntatore a carattere
			 *
			 * 			*check
			 * 			Significato: struttura che colleziona i vari controlli
			 * 		    Tipo: puntatore a controlli
			 *
			 * 	Output: //
			 * 	Descrizione: svuota il file che contiene l'eventuale partita salvata in precedenza, riceve in input le coordinate della pedina da muovere, le coordinate della casella su cui spostare la pedina e i comandi da eseguire.
			 * 				 Se si inseriscono 10 input non validi consecutivamente, la funzione assegnerà 1 a check.termina per ritornare al menu'e viene dichiarato vincitore l'avversario.
			 * 				 Alla fine, la funzione controlla se c'è un vincitore e, nel caso in cui ci sia, riceve in input il nome da salvare nel palmarès
			 * 	Autori: Marco Casamassima, Andrea Braho
			 * 	Revisione: 12/06/2017
			 */
	int vincitore; // vale 0 se non c'è nessun vincitore, 1 se il vincitore è il giocatore 1 e 2 se il vincitore è il giocatore 2
	int esci;	// vale 1 se il nome del vincitore è stato salvato, 0 altrimenti
	int posizione; // posizione del nome del vincitore nel palmares o -1 se il nome non è presente
	int tipo_input; // vale 1 se l'input è una coordinata accettabile, 2 se è un comando accettabile e 0 se non è un input accettabile
	int tipo_mossa; // vale 0 se è una mossa non valida o non è stata ancora fatta, 1 se la mossa effettuata è di tipo 1 e 2 se la mossa effettuata è di tipo 2
	int cont_errori; // conta gli input errati
	char input_seleziona[8]; // memorizza le coordinate della casella della pedina da spostare o un comando. 8 perchè è la lunghezza più alta tra i comandi (termina)
	char input_mossa[8]; // memorizza le coordinate della casella della pedina di arrivo della mossa o un comando. 8 perchè è la lunghezza più alta tra i comandi (termina)
	char nome_vincitore[21]; // memorizza il nome del vincitore
	int check_nome; // vale 0 se il nome è accettabile, 1 altrimenti


	svuota_file_testo("partita_salvata.txt");

	//inizializzazione dei controlli
	check->termina = 0;
	check->esci = 0;
	check->salva = 0;

	vincitore = 0; // ovvero non c'è alcun vincitore

	do {
			//inizializzazione dei controlli
			cont_errori = 0;
			check->mossa = 0;
			check->seleziona = 0;
			check->ok = 0;

			do { //inizio ciclo per il selezionamento della pedina
				system("cls");
				visualizza_scacchiera(*turno, *pedina_giocatore, scacchiera);


				printf("Seleziona una pedina o scegli un comando: ");
				scanf("%s", input_seleziona);

				tolower_stringa(input_seleziona); //conversione in minuscolo della stringa

				tipo_input = controllo_tipo_input(input_seleziona);	// controllo su che tipo è l'input. Vale 1 se sono coordinate, 2 se è un comando o 0 se è un input non valido

				esecuzione_input_seleziona(check, input_seleziona, tipo_input, &cont_errori, scacchiera, pedina_giocatore); // selezione della pedina o esecuzione del comando

				if(cont_errori == 10) { // se l'utente ha inserito 10 volte input errati
					check->termina = 1;

					system("cls");
					printf("Hai inserito troppe scelte errate.\n"
						   "Fine della partita.\n");
					system("PAUSE");

					if(*pedina_giocatore == 'O')
						vincitore = 2;
					else
						vincitore = 1;
				}
			} while(check->termina == 0 && check->esci == 0 && check->seleziona == 0); //fine ciclo per il selezionamento della pedina

			cont_errori = 0;
			tipo_mossa = 0;

			while(check->termina == 0 && check->esci == 0 && check->ok == 0) { //inizio ciclo per l'esecuzione della mossa
				system("cls");
				visualizza_scacchiera(*turno, *pedina_giocatore, scacchiera);

				input_seleziona[0] = toupper(input_seleziona[0]);
				printf("Casella di partenza: %s\n", input_seleziona);

				input_seleziona[0] = tolower(input_seleziona[0]);

				printf("Seleziona la casella di arrivo o scegli un comando: ");
				scanf("%s", input_mossa);

				tolower_stringa(input_mossa);

				tipo_input = controllo_tipo_input(input_mossa); //controllo se è una coordinata o un comando o un input non accettabile

				esecuzione_input_mossa(input_mossa, tipo_input, &cont_errori, scacchiera, check, turno, pedina_giocatore, &tipo_mossa, input_seleziona); //esecuzione del comando o della mossa



				if(cont_errori == 10) {
					check->termina = 1;

					system("cls");
					printf("Hai inserito troppe scelte errate.\n"
						   "Fine della partita.\n");
					system("PAUSE");

					if(*pedina_giocatore == 'O')
						vincitore = 2;
					else
						vincitore = 1;
				}
			}
				if((*turno) > 24) { // si possono portare tutte le proprie pedine nella zona di partenza dell'avversario solo dopo il 24esimo turno
					vincitore = controllo_vittoria(*pedina_giocatore, scacchiera);

					if((*turno) >= 30 && vincitore == 0) //controllo della posizione delle pedine dopo il turno 30
						vincitore = controllo_pedine30(*turno, *pedina_giocatore, scacchiera);
				}

				if(vincitore != 0)
					check->termina = 1;

	}while(check->termina == 0 && check->esci == 0);

	if(vincitore != 0) { // se c'è un vincitore

		esci = 0;

		do { //salvataggio del nome
			system("cls");
			if(vincitore == 1)
				printf("HA VINTO IL GIOCATORE 1!!!\n");
			else
				printf("HA VINTO IL GIOCATORE 2!!!\n");

			printf("Inserire il nome del vincitore: ");
			fflush(stdin);
			gets(nome_vincitore);

			check_nome = controllo_nome(nome_vincitore);
			if(check_nome == 1) {
				posizione = posizione_nome(nome_vincitore);
				system("cls");
				esci = salvataggio_nome(posizione, nome_vincitore);
			}
			else {
				system("cls");
				printf("Errore. Hai inserito un nome di oltre 20 caratteri o contenente spazi.\n");
				system("PAUSE");
				system("cls");
			}

		} while(esci != 1);
	}

}

int controllo_tipo_input(char input[]) {
			/*  Funzione: controllo_tipo_input
			 * 	Input:	input
			 * 			Significato: contiene gli eventuali comandi o coordinate
			 * 			Tipo: array di caratteri
			 *
			 * 	Output: controllo
			 * 			Significato: vale 0 se è un input non accettabile, 2 se l'input è un comando o 1 se l'input sono delle coordinate
			 * 			Tipo: intero
			 * 	Descrizione: controlla il tipo di input e assegna a controllo: 2 se input è un comando o 0 se è un input non accettabile
			 * 	Autori: Marco Casamassima, Andrea Braho
			 * 	Revisione: 12/06/2017
			 */

	int controllo;

	if(strlen(input) == 2 && (strcmp(input, "ok")) != 0) // controllo che distingue le coordinate dai comandi
		controllo = controllo_coordinate(input);
	else
		if(strcmp(input, "ok") == 0)
			controllo = 2;
		else
			if(strcmp(input, "regole") == 0)
				controllo = 2;
			else
				if(strcmp(input, "salva") == 0)
					controllo = 2;
				else
					if(strcmp(input, "termina") == 0)
						controllo = 2;
					else
						if(strcmp(input, "esci") == 0)
							controllo = 2;
						else
							controllo = 0;
return controllo;
}

void esecuzione_input_seleziona(controlli *check, char input[], int tipo_input, int *cont_errori, char scacchiera[][NUM_COLONNE], char *pedina) {
			/*  Funzione: esecuzione_input_mossa
			 * 	Input:  *check
			 * 			Significato: struttura che colleziona i vari controlli
			 * 		    Tipo: puntatore a controlli
			 *
			 * 			input
			 * 			Significato: coordinate della casella di partenza
			 * 			Tipo: array di caratteri
			 *
			 *			tipo_input
			 *			Significato: vale 0 se è un input non accettabile, 2 se l'input è un comando o 1 se l'input sono delle coordinate
			 * 			Tipo: intero
			 *
			 * 			*cont_errori
			 * 			Significato: numero di volte che l'utente ha inserito input errati
			 * 			Tipo: puntatore ad intero
			 *
			 * 			scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array di caratteri bidimensional
			 *
			 * 			*pedina
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: puntatore a carattere
			 *
			 * 	Output: //
			 * 	Descrizione: esegue la selezione della pedina o il comando. Se l'utente ha inserito il comando "ok" dopo una mossa, la funzione cambia il giocatore
			 * 	Autori: Marco Casamassima, Andrea Braho
			 * 	Revisione: 12/06/2017
			 */

	int i_partenza, j_partenza; // corrispondono rispettivamente alla seconda coordinata e alla prima coordinata convertite in posizione nella matrice

	if(tipo_input != 0) { // se l'input è un comando o sono delle coordinate

		if(tipo_input == 2) {	// se l'input è un comando

			if(strcmp(input, "ok") == 0) { // se l'input è "ok", incrementa gli errori perchè non si è effettuata una mossa
				*cont_errori=*cont_errori+1;
				printf("Effettuare prima la mossa.\n");
				system("PAUSE");
			}
			else
				if(strcmp(input, "regole") == 0)
					visualizza_regole();
				else
					 if(strcmp(input, "salva") == 0) {
					 	 	 check->salva = 1;
					 	 	 printf("La partita verra' salvata a fine turno.\n");
					 	 	 system("PAUSE");
					 }
					 else
						 if(strcmp(input, "termina") == 0)
							 check->termina = 1;
					     else
					    	 check->esci = 1;
		}
		else { // se l'input sono delle coordinate
			  // conversione delle coordinate in posizione nella matrice
				j_partenza = input[0] - 97;
				i_partenza = abs((input[1] - '0') - 8);

				if(scacchiera[i_partenza][j_partenza] == *pedina) { // se l'utente sta selezionando una propria pedina
					check->seleziona = 1;
				}
				else { // se l'utente non ha selezionato una propria pedina
						*cont_errori = *cont_errori+1;
						printf("Pedina non valida.\n");
						system("PAUSE");
				}
		}
	}
	else { // se è un input non accettabile
		printf("Coordinata o comando non valido.\n");
		*cont_errori = *cont_errori+1;
		system("PAUSE");
	}

}

void visualizza_scacchiera(int turno, char pedina, char scacchiera[][NUM_COLONNE]) {
			/*  Funzione: visualizza_scacchiera
			 * 	Input:	turno
			 * 			Significato: turno corrente della partita
			 * 			Tipo: intero
			 *
			 * 			pedina
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: carattere
			 *
			 * 			scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array bidimensionale di caratteri

			 * 	Output: //
			 * 	Descrizione: visualizza una matrice come se fosse una scacchiera e visualizza i comandi ammessi, il turno e il giocatore che deve muovere la pedina
			 * 	Autori: Andrea Braho, Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */

	int i, j;
	int ordinata = NUM_RIGHE; // necessaria per la visualizzazione della coordinata a sinistra della matrice

	// prima una riga in alto
	printf(" ");
	for(j = 0; j < NUM_COLONNE; j++)
	{
	    printf("----");
	}
	printf("-\n"); // per riempire un "buco" alla fine del capo della scacchiera

	// poi ciascuna riga
    for(i = 0; i < NUM_RIGHE; i++)
    {
    	printf("%d", ordinata);
    	for(j = 0; j < NUM_COLONNE; j++)
		{
		    printf("| %c ",scacchiera[i][j]);
		}
  		printf("|\n ");
  		if(i < NUM_RIGHE - 1)
  		{
    		for(j = 0; j < NUM_COLONNE; j++)
			{
		    	printf("|---");
			}


  			if(i == 0)
  				printf("|\t\t\t  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
    		// visualizzazione dei comandi affianco alla scacchiera
  			else {
  					if(i == 1)
  						printf("|\t\t\t |COMANDI:                                 |\n");
  					else {
  						if(i == 2)
  							printf("|\t\t\t |ok: termina il turno.                    |\n");
  						else {
  								if(i == 3)
  									printf("|\t\t\t |salva: salva la partita a fine turno.    |\n");
  								else {
  										if(i == 4)
  											printf("|\t\t\t |regole: visualizza le regole e i comandi.|\n");
  										else {
  												if(i == 5)
  													printf("|\t\t\t |termina: ritorna al menu'.               |\n");
  												else {
  														if(i == 6)
  															printf("|\t\t\t |esci: esci dal programma.                |\n");
  												}
  										}
  								}
  						}
  					}
  			}

		}
		ordinata--;

	}

	// e infine la base
	for(j = 0; j < NUM_COLONNE; j++)
	    printf("----");

	printf("-"); // per riempire un "buco" alla fine della base della scacchiera
	printf("\t\t\t  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
	printf("   A   B   C   D   E   F   G   H\n\n");

	printf("Turno %d.\n", turno);
	printf("Tocca al giocatore ");
	if(pedina == 'O')
		printf("1: O\n\n");
	else
		printf("2: X\n\n");

}

void esecuzione_input_mossa(char input[], int tipo_input, int *cont_errori, char scacchiera[][NUM_COLONNE], controlli *check, int *turno, char *pedina, int *tipo_mossa, char input_seleziona[]) {
		/*  Funzione: esecuzione_input_mossa
		 * 	Input:  input
		 * 			Significato: coordinate della casella di arrivo o comando
		 * 			Tipo: array di caratteri
		 *
		 *			tipo_input
		 *			Significato: indica il tipo di input. vale 0 se è un input non accettabile, 2 se l'input è un comando o 1 se l'input sono delle coordinate
		 * 			Tipo: intero
		 *
		 * 			*cont_errori
		 * 			Significato: numero di volte che l'utente ha inserito input errati
		 * 			Tipo: puntatore ad intero
		 *
		 * 			scacchiera
		 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
		 * 			Tipo: array bidimensionale di caratteri
		 *
		 * 			*check
		 * 			Significato: struttura che colleziona i vari controlli
		 * 		    Tipo: puntatore a controlli
		 *
		 * 		    *turno
		 * 			Significato: turno corrente della partita
		 * 			Tipo: puntatore ad intero
		 *
		 * 			*pedina
		 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
		 * 			Tipo: puntatore a carattere
		 *
		 * 			*tipo_mossa
		 * 			Significato: indica il tipo di mossa. Assume 0 se non è una mossa accettata, 1 se è una mossa di tipo 1 (la pedina si sposta di una casella) e 2 se è una mossa di tipo 2 (la pedina si sposta con un salto)
		 * 			Tipo: puntatore ad intero
		 *
		 * 			input_seleziona
		 * 			Significato: coordinate della casella di partenza
		 * 			Tipo: array di caratteri
		 *
		 * 	Output: //
		 * 	Descrizione: esegue la mossa o il comando. Se l'utente ha inserito il comando "ok" dopo una mossa, la funzione cambia il giocatore. Se è stata effettuata una mossa di tipo 2. Viene copiato input in input_seleziona
		 * 	Autori: Marco Casamassima, Andrea Braho
		 * 	Revisione: 12/06/2017
		 */
	int i_partenza; // indica l'ordinata delle coordinate di partenza inserite dall'utente
	int i_arrivo;	// indica l'ordinata delle coordinate di arrivo inserite dall'utente
	int j_partenza; // indica l'ascissa delle coordinate di partenza inserite dall'utente
	int j_arrivo;	// indica l'ascissa delle coordinate di arrivo inserite dall'utente

	if(tipo_input != 0) { // se input non è un input non accettabile
		if(tipo_input == 2) { // se input è un comando
			if(strcmp(input,"ok") == 0) {
				if(check->mossa == 0) { // se non è stata effettuata una mossa e si digita ok, viene visualizzato un errore errore
					*cont_errori=*cont_errori+1;
					printf("Effettuare prima la mossa.\n");
					system("PAUSE");
				}
				else { // altrimenti si cambia la pedina e si incrementa il turno
					check->ok = 1;
					cambio_giocatore(turno, pedina);

					if(check->salva == 1) { // se è stato digitato "salva", si salva la partita
						salva_partita(*turno, *pedina, scacchiera);
						printf("La partita e' stata salvata. Ritorno al menu'.\n");
						system("PAUSE");
						check->termina = 1;
					}
				}
			}
			else if(strcmp(input, "regole") == 0) {
					system("cls");
					visualizza_regole();
				  }
				 else if(strcmp(input, "salva") == 0) {
							check->salva = 1;
							printf("La partita verra' salvata a fine turno.\n");
							system("PAUSE");
						}
				   	  else if(strcmp(input, "termina") == 0)
								check->termina = 1;
						   else
								check->esci = 1;

		}
		else if(*tipo_mossa == 1) { // se è stata già effettuata una mossa di tipo 1
				*cont_errori=*cont_errori+1;
				printf("Mossa non consentita.\n");
				system("PAUSE");
			  }
			  else {  // se non è stata effettuata alcuna mossa o è stata effettuata una mossa di tipo 2
				  	  // vengono convertite le coordinate in posizioni sulla matrice
				  	  j_arrivo = input[0] - 'a';
				  	  i_arrivo = abs((input[1] - '0') - 8);
				  	  j_partenza = input_seleziona[0] - 'a';
				  	  i_partenza = abs((input_seleziona[1] - '0') - 8);
				  	  *tipo_mossa = controllo_mossa(i_partenza, j_partenza, i_arrivo, j_arrivo, *check, scacchiera); //si controlla che tipo di mossa l'utente vuole effettuare

				  	  if(*tipo_mossa == 0) { // se non è una mossa consentita
				  		  printf("Mossa non consentita.\n");
				  		  system("PAUSE");
				  		  *cont_errori=*cont_errori+1;
				  	  }
				  	  else if(scacchiera[i_arrivo][j_arrivo] == ' ') { // se la casella di arrivo è vuota
								scacchiera[i_arrivo][j_arrivo] = *pedina;	// posiziona la pedina sulla casella di arrivo
								scacchiera[i_partenza][j_partenza] = ' ';	// elimina la pedina dalla casella di partenza
								system("cls");
								visualizza_scacchiera(*turno, *pedina, scacchiera);
								printf("Mossa effettuata.\n");
								system("PAUSE");

								check->mossa = 1;

								if(*tipo_mossa == 2) {
									strcpy( input_seleziona, input);
								}
						 	}
				  	  	   else {
								printf("Mossa non consentita.\n"
							   		   "C'e' gia' una pedina.\n");
							   	*tipo_mossa = 0;

								*cont_errori = *cont_errori+1;
								system("PAUSE");
						  }
			  }
	}
	else { // se non è un comando o non sono coordinate
		*cont_errori = *cont_errori+1;
		printf("Errore nell'inserimento delle coordinate o comandi.\n");
		system("PAUSE");
	}
}

int controllo_coordinate(char coordinate[]){
			/*  Funzione: controllo_coordinate
			 * 	Input:  coordinate
			 * 			Significato: possibili coordinate immesse dall'utente
			 * 			Tipo: array di caratteri
			 *
			 * 	Output: coordinate_accettate
			 * 			Significato: vale 0 se coordinate contiene coordinate non accettabili, 1 se vett contiene coordinate accettabili
			 * 			Tipo: intero
			 * 	Descrizione: controlla se le coordinate si trovano nel range del campo di gioco (primo carattere tra "a" e "h" e secondo carattere tra "1" e "8"
			 * 	Autori: Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */

	int coordinate_accettate;

	if(coordinate[0] >= 'a' && coordinate[0] <= 'h' && coordinate[1] >= '1' && coordinate[1] <= '8')
		coordinate_accettate = 1;
	else
		coordinate_accettate = 0;

return coordinate_accettate;
}

void cambio_giocatore(int *turno,char *pedina){
			/*  Funzione: cambio_giocatore
			 * 	Input:	*turno
			 * 			Significato: turno corrente della partita
			 * 			Tipo: puntatore ad intero
			 *
			 * 			*pedina_giocatore
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
	 	 	 * 			Tipo: puntatore a carattere
	 	 	 *
			 *	Output: //
			 * 	Descrizione: incrementa il turno e cambia la pedina da "X" a "O" o da "O" a "X"
			 * 	Autori: Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */

	*turno = *turno+1;
	if(*pedina == 'O')
		*pedina = 'X';
	else
		*pedina = 'O';
}

void visualizza_regole() {
			/*  Funzione: visualizza_regole
			 * 	Input:	//
			 * 	Output: //
			 * 	Descrizione: visualizza le regole del gioco halma e come si gioca
			 * 	Autori: Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */
	printf("\t\t _______________________________________________________________________\n"
		   "\t\t| _____________________________________________________________________ |\n"
	       "\t\t||                                                                     ||\n"
		   "\t\t||                          REGOLE E COMANDI                           ||\n"
		   "\t\t||_____________________________________________________________________||\n"
		   "\t\t|_______________________________________________________________________|\n\n\n");

	printf("REGOLE:\n"
		   "Ogni giocatore ha a disposizione una sola mossa per ciascun turno di gioco.\n"
		   "La mossa puo' essere di due tipi:\n"
		   "a) il giocatore di turno muove una propria pedina di una sola casella in qualsiasi direzione (ortogonale o diagonale);\n"
		   "   la casella di arrivo deve essere libera;\n"
		   "b) il giocatore di turno muove una propria pedina facendola 'saltare' al di la' di una casella adiacente, occupata da\n"
		   "   un'altra pedina, come nel gioco della Dama.\n"
		   "La casella di partenza e la casella di arrivo di ogni singolo salto devono trovarsi sulla stessa linea.\n"
		   "La casella di arrivo deve essere libera; la pedina saltata, che puo' essere propria o avversaria, non viene rimossa dal tavoliere.\n"
		   "Se al termine del salto la pedina trova un'altra pedina da saltare, puo' proseguire nello stesso turno di gioco compiendo\n"
		   "un altro salto, e cosi' via, effettuando un salto multiplo.\n"
		   "La mossa a salti consecutivi puo' prevedere anche il cambiamento, tra un salto e l'altro, della direzione di marcia.\n"
		   "Il salto delle pedine non e' mai obbligatorio:\n"
		   "il giocatore puo' pertanto decidere di effettuare una mossa di tipo a) anche quando ha salti a disposizione, oppure puo' \n"
		   "interrompere in qualsiasi momento il salto multiplo, anche quando vi sono ulteriori salti possibili. La casella di partenza e\n"
		   "la casella di arrivo di ogni singolo salto devono trovarsi sulla stessa linea.\n"
		   "Non e' possibile combinare nello stesso turno di gioco una mossa di tipo a) ed una di tipo b).\n"
		   "In questo gioco non esiste la cattura.\n"
		   "Non e' ammesso 'passare': in ogni turno di gioco e' pertanto obbligatorio effettuare una mossa di tipo a) o di tipo b).\n"
		   "Sono possibili mosse o salti all'indietro\n\n"


		   "COMANDI:\n"
		   "Per muovere una propria pedina, bisogna digitare le coordinate della casella su cui\n"
		   "si trova, premere invio, digitare le coordinate della casella di arrivo, premere invio\n"
		   "digitare 'ok' per terminare il turno e premere invio.\n"
		   "Ex: C7 -> invio -> C8 -> invio -> ok -> invio\n"
		   "Nel caso in cui si voglia effettuare salti multipli, basta digitare una sola volta le\n"
		   "coordinate della casella su cui si trova la pedina, premere invio, digitare le\n"
		   "successive coordinate delle caselle di arrivo, (premendo invio dopo ogni salto), digitare 'ok' per\n"
		   "terminare il turno e premere invio\n"
		   "Ex: C7 -> invio -> C5 -> invio -> C2 -> invio -> ok -> invio.\n"
		   "ATTENZIONE: NON E' POSSIBILE ANNULLARE LA SELEZIONE DI UNA PEDINA, PERTANTO, SE LA PEDINA SELEZIONATA NON PUO' ESSERE SPOSTATA, \n"
		   "\t    IL GIOCATORE E' PREGATO DI CONTINUARE AD INSERIRE LE COORDINATE FINO A QUANDO NON VIENE DICHIARATO VINCITORE L'AVVERSARIO.\n\n");

	system("PAUSE");
}

int controllo_mossa(int i_partenza,int j_partenza ,int i_arrivo,int j_arrivo, controlli check, char scacchiera[][NUM_COLONNE]){
			/*  Funzione: controllo_mossa
			 * 	Input:	i_partenza
			 * 			Significato: indica la riga della casella di partenza
			 * 			Tipo: intero
			 *
			 * 			j_partenza
			 * 			Significato: indica la colonna della casella di partenza
			 * 			Tipo: intero
			 *
			 * 			i_arrivo
			 * 			Significato: indica la riga della casella di arrivo
			 * 			Tipo: intero
			 *
			 * 			j_arrivo
			 * 			Significato: indica la colonna della casella di arrivo
			 * 			Tipo: intero
			 *
			 *			check
			 * 			Significato: struttura che colleziona i vari controlli
			 * 		    Tipo: controlli
			 *
			 * 			scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array bidimensionale di caratteri
			 *
			 * 	Output: mossa
			 * 			Significato: indica il tipo di mossa. Assume 0 se non è una mossa accettata, 1 se è una mossa di tipo 1 (la pedina si sposta di una casella) e 2 se è una mossa di tipo 2 (la pedina si sposta con un salto)
			 * 			Tipo: intero
			 *
			 * 	Descrizione: calcola la distanza tra la riga di partenza e la riga di arrivo e tra la colonna di partenza e la colonna di arrivo per controllare che sia una mossa accettabile. Nel caso di una possibile mossa di tipo 2, si verifica che ci sia una pedina nella casella tra la casella di arrivo e quella di partenza
			 * 	Autori: Andrea Braho, Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */
	int i_distanza, j_distanza; // indicano rispettivamente la distanza tra i_partenza e i_arrivo e la distanza tra j_partenza e j_arrivo
	int mossa;
	int i_medio, j_medio; // indicano rispettivamente il punto medio di i_partenza e i_arrivo e il punto medio di j_partenza e j_arrivo



	i_distanza = abs(i_arrivo-i_partenza);
	j_distanza = abs(j_arrivo-j_partenza);

	i_medio = (i_arrivo + i_partenza)/2;
	j_medio = (j_arrivo + j_partenza)/2;

	mossa = 0;

	// potrebbe essere una mossa se la i_distanza e la j_distanza sono rispettivamente: 1 e 0, 1 e 1, 0 e 1, 2 e 0, 2 e 2 o 0 e 2
	if(check.mossa == 0){ // se non si ha ancora effettuato la mossa nel turno, sono accettabili entrambi i tipi di mosse

		if(i_distanza == 0){
			if (j_distanza == 2 && scacchiera[i_medio][j_medio] != ' ')
					mossa = 2;
			else{
				if(j_distanza == 1)
					mossa = 1;
			}
		}else{
			if(j_distanza == 0){
				if(i_distanza == 2 && scacchiera[i_medio][j_medio] != ' ')
						mossa = 2;
				else{
					if (i_distanza == 1)
						mossa = 1;
				}
			}else{
				if(i_distanza == 2 && j_distanza == 2 && scacchiera[i_medio][j_medio] != ' ')
						mossa = 2;
				else {
					if(i_distanza == 1 && j_distanza == 1)
						mossa = 1;
				}
			}
		}

	}else{ // se si ha già effettuato una mossa, è accettabile solo la mossa di tipo 2
		if(scacchiera[i_medio][j_medio] != ' ') {
			if(i_distanza == 0){
				if(j_distanza == 2)
					mossa = 2;
			}else{
				if(j_distanza == 0){
					if(i_distanza == 2)
						mossa = 2;
				}else{
					if(i_distanza == 2 && j_distanza == 2 )
						mossa = 2;
				}
			}
		}
	}

return mossa;
}

void visualizza_informazioni() {
			/*  Funzione: visualizza_informazioni
			 * 	Input:	//
			 * 	Output: //
			 * 	Descrizione: visualizza la storia del gioco Halma
			 * 	Autori: Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */

	printf("\t\t _______________________________________________________________________\n"
		   "\t\t| _____________________________________________________________________ |\n"
	       "\t\t||                                                                     ||\n"
		   "\t\t||                       INFORMAZIONI SUL GIOCO                        ||\n"
		   "\t\t||_____________________________________________________________________||\n"
		   "\t\t|_______________________________________________________________________|\n\n\n");

	printf("L'origine di questo gioco viene comunemente associata alla battaglia di Alma, combattuta il 20 settembre 1854 in Crimea. \n"
			"Un gioco col nome di Alma compare effettivamente in Inghilterra negli anni immediatamente successivi. Il gioco\n"
			"compare con la grafia attuale, con l'H posta all'inizio del nome, solo nel 1888. Per molto tempo Halma e' stato pertanto \n"
			"ritenuto un gioco nato nell'Inghilterra vittoriana. In realta' lo studioso dei giochi Bruce Whitehill ha recentemente\n"
			"scoperto la sua vera origine. Sembra infatti sia stato inventato tra il 1883 e il 1884 dal chirurgo plastico americano\n"
			"George Howard Monks (1853-1933), ispirandosi al gioco inglese Hoppity, che il fratello Robert aveva scoperto\n"
			"durante un viaggio in Gran Bretagna. Il nome Halma pare sia stato scelto dal matematico Thomas Hill (1818-1891), ispirandosi\n"
			"alla parola greca alma, che vuol dire 'salto'. Il gioco viene pubblicato per la prima volta da E. I. Horsman che\n"
			"successivamente vince una battaglia legale contro la Milton Bradley che aveva messo in commercio un gioco molto simile con\n"
			"il nome di Eckha. Il gioco, che esiste in versione per due e per quattro giocatori, ha dato origine a moltissime varianti,\n"
			"la piu' popolare delle quali e' senz'altro la Dama Cinese.\n\n");

	system("PAUSE");
}

int controllo_vittoria(char pedina, char scacchiera[][NUM_COLONNE]){
			/*  Funzione: controllo_vittoria
			 * 	Input:  pedina
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: carattere
			 *
			 *			scacchiera
			 *			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 *			Tipo: array bidimensionale di caratteri
			 *
			 * 			Output: vincitore
			 * 			Significato: vale 0 se non ha vinto nessuno, 1 se ha vinto il giocatore 1 e 2 se ha vinto il giocatore 2
			 * 			Tipo: intero
			 * 	Descrizione: controlla se un giocatore ha vinto (le sue pedine si trovano nella zona di partenza dell'avversario) e assegna a vincitore un valore
			 * 	NOTA: siccome prima di questa funzione, si cambia il turno, se tocca al giocatore 2, si controlla la pedina opposta, ovvero quella del turno precedente
			 * 	Autori: Marco Casamassima, Andrea Braho
			 * 	Revisione: 12/06/2017
			 */
	int vincitore, i, j;
	int k;	//indica il numero di simboli in meno per ogni riga

	k = 0;	/*
			ex:	OOOO	0 simboli in meno k = 0
				OOO		1 simbolo in meno k = 1
				OO		2 simboli in meno k = 2
				O		3 simboli in meno k = 3
			*/

	// controlla la pedina O
	if(pedina == 'X'){
		vincitore = 1;

		for(i = 0; i < NUM_RIGHE/2 && vincitore == 1; i++){
			for(j = 0; j < (NUM_COLONNE/2)-k && vincitore == 1; j++){
				if(scacchiera[i][j] != 'O')
					vincitore = 0;
			}
			k++;
		}

	}else { // controlla la pedina X
		vincitore = 2;

		for(i = NUM_RIGHE-1; i >= NUM_RIGHE/2 && vincitore == 2; i--){
			for(j = (NUM_COLONNE/2)+k; j < NUM_COLONNE && vincitore == 2; j++){
				if(scacchiera[i][j] != 'X')
					vincitore = 0;
			}
			k++;
		}

	}

return vincitore;
}

int controllo_pedine30(int turno,char pedina,char scacchiera[][NUM_COLONNE]){
			/*  Funzione: controllo_pedine30
			 * 	Input:	turno
			 * 			Significato: turno corrente della partita
			 * 			Tipo: intero
			 *
			 * 			pedina
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: carattere
			 *
			 * 			scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array bidimensionale di caratteri
			 *
			 * 	Output: vincitore
			 * 			Significato: indica chi ha vinto. Vale 0 se non ci sono vincitori, 1 se il vincitore è il giocatore 1 e 2 se il vincitore è il giocatore 2
			 * 			Tipo: intero
			 * 	Descrizione: controlla se ci sono pedine nella propria zona di partenza, controllando prima quelle dell'avversario e successivamente quelle del giocatore di quel turno. Se il turno è maggiore di 30, si controllano solo le pedine del giocatore del turno (quelle dell'altro giocatore sono state controllate nel turno precedente)
			 * 	Autori: Andrea Braho, Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */
	int	vincitore = 0;
	int	num_cicli;
	int i,j;
	int k; // indica il numero di simboli in meno per ogni riga

	// se ci si trova al 30esimo turno, si controllano entrambe le pedine (cambiando la pedina alla fine del primo ciclo), altrimenti si controllano solo le proprie pedine, saltando il primo ciclo
	for(num_cicli = 1; num_cicli <= 2; num_cicli++){

		if(turno > 30)
			num_cicli = 2;

		k = 0;			/*
						ex: xxxx	0 simboli in meno k = 0
							xxx 	1 simbolo in meno k = 1
							xx		2 simboli in meno k = 2
							x		3 simboli in meno k = 3
						*/

		if(pedina == 'O'){

			for(i = 0; i < NUM_RIGHE/2 && vincitore !=1 ; i++){
				for(j = 0; j < (NUM_COLONNE/2)-k && vincitore != 1; j++){
					if(scacchiera[i][j] == 'X')
						vincitore = 1;
				}
				k++;
			}

			pedina = 'X';

		}else{

			for(i = NUM_RIGHE-1; i >= NUM_RIGHE/2 && vincitore != 2; i--){
				for(j = (NUM_COLONNE/2)+k; j < NUM_COLONNE && vincitore !=2; j++){
					if(scacchiera[i][j] == 'O')
						vincitore = 2;
				}
				k++;
			}

			pedina = 'O';
		}
	}

return vincitore;
}

int posizione_nome(char nome[]) {
		/*  Funzione: posizione_nome
		 * 	Input:  nome
		 * 			Significato: nome del vincitore da salvare nel palmarès
		 * 			Tipo: array di caratteri
		 *
		 * 	Output: posizione
		 * 			Significato: vale -1 se il nome non compare nel palmarès, altrimenti contiene la posizione del nome nel palmarès
		 * 			Tipo: intero
		 * 	Descrizione: verifica che le coordinate siano nel range del campo di gioco
		 * 	Autori: Marco Casamassima
		 * 	Revisione: 12/06/2017
		 */

	giocatore vinc;	// struct di tipo giocatore necessaria per acquisire da file il nome e confrontarlo con nome
	int posizione = -1; //inizializzazione di posizione ad un valore diverso da qualsiasi posizione possibile
	FILE *f;
	int i = 0; // conta i nomi nel palmares
	if((f = fopen("palmares.dat", "rb")) != NULL) {
		while(fread(&vinc, sizeof(giocatore), 1, f) == 1 && posizione == -1) {
			if(strcmp(vinc.nome, nome) == 0)
				posizione = i;
			i++;
		}
		fclose(f);
	}
	else if((f = fopen("palmares.dat", "wb")) != NULL) // se il file palmares non esiste, lo crea
			fclose(f);
         else {
         	printf("Errore nel salvataggio del nome.\n"
			 	   "Chiusura del programma.\n");
         	system("PAUSE");
         	exit(1);
         }

return posizione;
}

int salvataggio_nome(int posizione, char nome[]) {
	/*  Funzione: esecuzione_input_mossa
			 * 	Input:  posizione
			 * 			Significato: indica in quale posizione (offset) si trova il nome del vincitore nel palmarès.
			 * 						 Se il nome non è presente nel palmares vale -1
			 * 			Tipo: intero
			 *
			 * 			nome
			 * 			Significato: nome del vincitore da salvare nel palmares
			 * 			Tipo: array di caratteri
			 *
			 * 	Output: esci
			 * 			Significato: indica se il nome è stato salvato o no. Vale 1 se è stato salvato, 0 altrimenti
			 * 	Descrizione: salva un nome nel file palmarès. Se il nome non è presente nel palmarès (e quindi posizione = -1),
			 * 				 vengono salvati alla fine del file il nome e 1 (per la vittoria), altrimenti si incrementano le vittorie
			 * 				 del giocatore con quel nome e si ordina il file in base alle vittorie, confrontando il numero di vittorie del giocatore
			 * 				 con il nome dato con quelli che si trovano nelle posizioni precedenti. Se il numero di vittorie è maggiore allora
			 * 				 si scambia e si continua, se il numero di vittorie è minore o si è raggiunta la prima posizione, ci si ferma
			 *
			 * 	Autori: Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */
	int esci;
	int flag; //flag per uscire prima dal ciclo. vale 1 se la vittoria del vincitore che si trova alla posizione precedente a quella del nome dato è maggiore, altrimenti 0
	FILE *f;
	char risposta;
	giocatore vinc; // struct di tipo giocatore per acquisire dal file il nome e le vittorie del giocatore con il nome dato
	giocatore vinc_prec; // struct di tipo giocatore per acquisire dal file il nome e le vittorie del giocatore che si trova alla posizione precedente al giocatore con il nome dato

	esci = 0;

	if((f = fopen("palmares.dat", "rb+")) == NULL) { // apri il file in lettura
		printf("\nErrore nel salvataggio del nome nel palmares.\n");
		esci = 1;
	}
	else  {
		if(posizione == -1) { // se il nome non è presente nel file, salvalo alla fine del file
			fseek(f, 0, SEEK_END); // posizionamento del buffer alla fine del file
			strcpy(vinc.nome, nome);
			vinc.vittorie = 1;
			fwrite(&vinc, sizeof(giocatore), 1, f);

			printf("Salvato.\n");
			system("PAUSE");
			esci = 1;
		}
		else { //se il nome è presente nel file, si richiede se il giocatore ha già giocato una partita con quel nome
			printf("Hai gia' vinto una partita con il nome %s?\n"
				   "1 per si. Qualsiasi altro carattere per no.\n"
				   "Premere invio per confermare la scelta.\n", nome);
			fflush(stdin);
			scanf("%c", &risposta);
			if(risposta == '1') { // se il giocatore ha già giocato una partita con quel nome
				esci = 1;

				fseek(f, posizione*(sizeof(giocatore)), SEEK_SET); // posizionamento del buffer alla posizione del giocatore con il nome dato
				fread(&vinc, sizeof(giocatore), 1, f);	// lettura dal file del vincitore

				vinc.vittorie++; // incremento delle vittorie del giocatore con il nome dato

				fseek(f, posizione*(sizeof(giocatore)), SEEK_SET); // riposizionamento del buffer alla posizione del giocatore con il nome dato
				fwrite(&vinc, sizeof(giocatore), 1, f);	// scrittura dal file del vincitore

				// ordinamento del file
				flag = 0;
				while(flag == 0 && posizione != 0) { // si confrontano le vittorie del giocatore con il nome dato con le vittorie del giocatore che si trova alla posizione precedente
					posizione--;
					fseek(f, posizione*(sizeof(giocatore)), SEEK_SET);
					fread(&vinc_prec, sizeof(giocatore), 1, f);

					if(vinc.vittorie > vinc_prec.vittorie) {
						// swap dei giocatori nel file
						fseek(f, posizione*(sizeof(giocatore)), SEEK_SET);
						fwrite(&vinc, sizeof(giocatore), 1, f);

						fseek(f, (posizione+1)*(sizeof(giocatore)), SEEK_SET);
						fwrite(&vinc_prec, sizeof(giocatore), 1, f);
					}
					else
						flag = 1;
				}
				printf("Salvato.\n");
				system("PAUSE");
			}
		}
			fclose(f);
	}
return esci;
}

int continua_partita(int *turno, char *pedina, char scacchiera[][NUM_COLONNE]) {
			/*  Funzione: continua_partita
			 * 	Input:  Input:	*turno
			 * 			Significato: turno corrente della partita
			 * 			Tipo: puntatore ad intero
			 *
			 * 			*pedina
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: puntatore a carattere
			 *
			 * 			scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array di caratteri bidimensionali
			 *
			 * 	Output: partita_salvata
			 * 			Significato: vale 0 se non c'è alcuna partita salvata (e quindi manca il file partita salvata) e 1 se c'è
			 * 			Tipo: intero
			 * 	Descrizione: se il file partita salvata non è vuoto, vengono acquisiti dal file la pedina, il turno e le pedine nella scacchiera
			 * 				 (nel file le pedine sono salvate come 1 per "O", 2 per "X" e 0 per " ")
			 * 	Autori: Marco Casamassima, Andrea Braho
			 * 	Revisione: 12/06/2017
			 */
	FILE *f;
	int partita_salvata, i, j;
	int numero; // indica se nella casella della matrice ci deve essere un " ", una O o un X, acquisendo rispettivamente 0, 1 e 2

	partita_salvata = 0;

	if((f = fopen("partita_salvata.txt", "r")) != NULL) {
		if(fscanf(f, "%c", pedina) != EOF)  { // se il file non è vuoto
			partita_salvata = 1;

			fscanf(f, "%d", turno);

			for(i = 0; i < NUM_RIGHE; i++) {
				for(j = 0; j < NUM_COLONNE; j++) {
					fscanf(f, "%d", &numero);
					if(numero == 1)
						scacchiera[i][j] = 'O';
					else if(numero == 2)
							scacchiera[i][j] = 'X';
						 else
					 		scacchiera[i][j] = ' ';
				}
			}
		}
	}

return partita_salvata;
}

void visualizza_palmares() {
			/*  Funzione: visualizza_palmares
			 * 	Input:  //
			 * 	Output: //
			 * 	Descrizione: visualizza la posizione, il nome e le vittorie dei vincitori che si trovano nel palmares. Se l'utente inserisce 1,
			 * 				 il file palmares viene svuotato, se non esiste viene creato
			 * 	Autori: Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */
	int posizione; // posizione in classifica nel palmarès
	char risposta[2];	// risposta dell'utente: 0 per uscire e 1 per azzerare il palmarès
	giocatore vincitore; // necessario per l'acquisizione del nome e delle vittorie nel palmarès
	int cont_errori = 0;

	FILE *f;

	if((f = fopen("palmares.dat", "rb")) != NULL){

		printf("\t\t _______________________________________________________________________\n"
			   "\t\t| _____________________________________________________________________ |\n"
		       "\t\t||                                                                     ||\n"
			   "\t\t||                              PALMARES                               ||\n"
			   "\t\t||_____________________________________________________________________||\n"
			   "\t\t|_______________________________________________________________________|\n\n\n");

		do {
			printf("\t\t        %-25s%-25s%-25s\n", "Posizione", "Nome", "Vittorie");
			posizione = 1;
			f = fopen("palmares.dat", "rb");
			while(fread(&vincitore, sizeof(vincitore), 1, f) == 1) {
				printf("\t\t        %-25d", posizione);
				printf("%-25s", vincitore.nome);
				printf("%-25d\n", vincitore.vittorie);

				posizione++;
			}
			fclose(f);

			puts("\n");

			printf("Digitare:\n"
		   		   "1. Per azzerare il palmares.\n"
		   		   "0. Per tornare al menu.'\n"
		   		   "Premere invio per confermare la scelta.\n");

			scanf("%s", risposta);

			if(strcmp(risposta, "1") == 0) {
				//azzeramento del palmarès
				remove("palmares.dat");
				if((f = fopen("palmares.dat", "wb" )) == NULL) {
								printf("Impossibile azzerare il palmares.\n"
									   "Uscita dal programma.\n");
								system("PAUSE");
								exit(1);
				}
				else {
					  			fclose(f);
					  			printf("Palmares azzerato.\n");
					  			system("PAUSE");
				}

			}
			else if(strcmp(risposta, "0") != 0) { //l'utente ha inserito una scelta diversa da 0 e 1
						cont_errori++;
					    printf("Hai inserito una scelta non presente.\n"
					    	   "Le scelte sono: 1-0.\n");
					    system("PAUSE");
				 }
			system("cls");

		}while(cont_errori < 10 && strcmp(risposta,"0") != 0);
	}
	else if((f = fopen("palmares.dat", "wb")) == NULL) { //se non è possibile azzerare il palmares, lo si crea
			printf("Impossibile creare il palmares.\n"
				   "Uscita dal programma.\n");
			system("PAUSE");
			exit(1);
		 }
		 else {
		 	fclose(f);
			printf("Creazione del palmares effettuata.\n");
			system("PAUSE");
		}
	if(cont_errori == 10) {
		printf("Hai inserito troppe scelte errate.\n"
			   "Ritorno al menu'.\n");
		system("PAUSE");
	}


}

void salva_partita(int turno, char pedina, char scacchiera[][NUM_COLONNE]) {
			/*  Funzione: salva_partita
			 * 	Input:  Input:	turno
			 * 			Significato: turno corrente della partita
			 * 			Tipo: intero
			 *
			 * 			pedina
			 * 			Significato: pedina del giocatore del turno corrente (O oppure X)
			 * 			Tipo: carattere
			 *
			 * 			scacchiera
			 * 			Significato: campo di gioco in cui saranno memorizzate le pedine dei giocatori
			 * 			Tipo: array bidimensionale di caratteri
			 * 	Output: //
			 * 	Descrizione: salva pedina, turno e scacchiera nel file partita_salvata. Gli elementi di scacchiera sono memorizzati come:
			 * 				 1 se l'elemento è "O", 2 se l'elemento è "X" e 0 se è " "
			 * 	Autori: Andrea Braho, Marco Casamassima
			 * 	Revisione: 12/06/2017
			 */
	int i, j;
	FILE *f;
	if((f = fopen("partita_salvata.txt", "w")) == NULL){
		printf("C'e' un errore nell'apertura del file della partita salvata.\n"
			   "Uscita dal programma.\n");
		exit(1);
	}
	else{
		fprintf(f,"%c", pedina);
		fprintf(f,"%d", turno);

		for(i = 0; i < NUM_RIGHE; i++) {
			for(j = 0; j < NUM_COLONNE; j++) {
				if(scacchiera[i][j] == 'O')
					fprintf(f," 1");
				else if(scacchiera[i][j] == 'X')
						fprintf(f," 2");
						else
							fprintf(f," 0");
			}
		}
	}
	fclose(f);
}

int controllo_nome(char nome[]){
			/*  Funzione: visualizza_menu
			 * 	Input:	nome
			 * 			Significato: stringa da salvare nel palmares
			 * 			Tipo: array di caratteri
			 *
			 * 	Output: accettato
			 * 			Significato: vale 1 se la stringa è ammessa, 0 altrimenti
			 * 			Tipo: intero
			 * 	Descrizione: controlla che nome abbia al più 20 caratteri e che non contenga spazi
			 * 	Autori: Andrea Braho
			 * 	Revisione: 12/06/2017
			 */
	int i;
	int accettato = 1;

	if (strlen(nome) > 20){
		accettato = 0;
	}

	for (i = 0; i < strlen(nome) &&  accettato == 1; i++){
			if(nome[i] == ' ')
				accettato = 0;
	}

return accettato;
}
