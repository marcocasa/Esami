/*
 * stringhe.c
 *
 *  Created on: 17 giu 2017
 *      Author: marco
 */

#include <string.h>
#include <ctype.h>
#include "stringhe.h"


void tolower_stringa(char stringa[]) {
	/*  Funzione: tolower_stringa
		 * 	Input:	stringa
		 * 			Significato: nome della stringa su cui effettuare l'operazione
		 * 			Tipo: array di caratteri
		 * 	Output: //
		 * 	Descrizione: converte i caratteri della stringa da maiuscolo a minuscolo
		 * 	Autori: Marco Casamassima
		 * 	Revisione: 12/06/2017
		 */
	int i;

	for(i = 0; i < strlen(stringa); i++) {
		stringa[i] = tolower(stringa[i]);
	}
}
