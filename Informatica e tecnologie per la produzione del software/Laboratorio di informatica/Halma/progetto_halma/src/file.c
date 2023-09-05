/*
 * file.c
 *
 *  Created on: 17 giu 2017
 *      Autori: Marco Casamassima
 *      Descrizione: Libreria contenente funzioni che operano su file
 */


#include <stdio.h>
#include "file.h"
#include <stdlib.h>

void svuota_file_testo(char nome_file[]) {
	/*  Funzione: svuota_file
	 * 	Input:	nome_file
	 * 			Significato: nome del file da svuotare
	 * 			Tipo: array di caratteri
	 * 	Output: //
	 * 	Descrizione: elimina il file con il nome inserito in input e ne crea un altro con lo stesso nome
	 * 	Autori: Marco Casamassima
	 * 	Revisione: 12/06/2017
	 */
	FILE *f;

	remove(nome_file);

	if((f = fopen(nome_file, "w")) == NULL ) {
		printf("Errore nella creazione del file.\n"
			   "Uscita dal programma.\n");
		system("PAUSE");
		exit(1);
	}

	fclose(f);
}

