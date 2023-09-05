/*
 * halma.h
 *
 *  Created on: 17 giu 2017
 *      Author: marco
 */

#ifndef HALMA_H_
#define HALMA_H_

#define NUM_RIGHE 8
#define NUM_COLONNE 8

typedef struct {
	int esci;		// vale 1 se bisogna uscire dal gioco, 0 altrimenti
	int termina;	// vale 1 se bisogna tornare al menù, 0 altrimenti
	int salva;		// vale 1 se il giocatore ha deciso di salvare la partita, 0 altrimenti
	int mossa;		// vale 1 se il giocatore ha effettuato la mossa, 0 altrimenti
	int seleziona;	// vale 1 se il giocatore ha selezionato una pedina, 0 altrimenti
	int ok;			// vale 1 se il giocatore  ha deciso di terminare il turno, 0 altrimenti
} controlli;

typedef struct {
	char nome[21];
	int vittorie;
} giocatore;


void visualizza_menu();
void visualizza_informazioni();
void nuova_partita(int *,char *,char scacchiera[][NUM_COLONNE]);
void gioca_partita(char scacchiera[][NUM_COLONNE], int*, char *, controlli *);
int controllo_tipo_input(char input[]);
void esecuzione_input_seleziona(controlli *, char input[], int, int *, char scacchiera[][NUM_COLONNE], char*);
void visualizza_scacchiera(int, char, char scacchiera[][NUM_COLONNE]);
void esecuzione_input_mossa(char input[], int, int*, char scacchiera[][NUM_COLONNE], controlli *, int *, char *, int *, char input_seleziona[]);
int controllo_coordinate(char coordinate[]);
void cambio_giocatore(int *,char *);
int controllo_nome(char nome[]);
void visualizza_regole();
int controllo_mossa(int,int,int,int,controlli, char scacchiera[][NUM_COLONNE]);
int controllo_vittoria(char, char scacchiera[][NUM_COLONNE]);
int controllo_pedine30(int turno,char pedina,char scacchiera[][NUM_COLONNE]);
int posizione_nome(char nome[]);
int salvataggio_nome(int, char nome[]);
int continua_partita(int *, char *, char scacchiera[][NUM_COLONNE]);
void visualizza_palmares();
void salva_partita(int, char, char scacchiera[][NUM_COLONNE]);

#endif /* HALMA_H_ */
