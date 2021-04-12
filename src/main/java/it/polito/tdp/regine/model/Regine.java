package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	private int N;
	private List<Integer> soluzione;
	
	public List<Integer> risolvi(int N){
		this.N=N;
		List<Integer> parziale = new ArrayList<Integer>();
		
		cerca(parziale,0);
		
		return this.soluzione;
	}
	
	/*
	 * Questa è la mia parte di ricorsione 
	 * il boolean serve per fermare la ricorsione dopo averne trovata 1 senza cercare tutte le altre
	 * cerca == true : trovato ; cerca == false : cerca ancora
	 */
	private boolean cerca(List<Integer>parziale, int livello) { // [0, 6, 4, 7]
		if(livello==N) {
			// caso terminale
			//System.out.println(parziale);
			this.soluzione = new ArrayList<Integer>(parziale);
			return true;
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la possa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				if(posValida(parziale,colonna)) {
					
					parziale.add(colonna); // [0, 6, 4, 7, XXX]
					boolean trovato = cerca(parziale,livello+1); // attraverso il booleann trovato appena trovo una sol mi fermo
					if(trovato) {
						return true;//cosi appena ho una soluzione mi fermo
					}
					parziale.remove(parziale.size()-1); //backtracking
				}
			}
			return false;
		}
	}
	
	/*
	 * vede se una possibile regina potrebbe essere messa in una determinata posizione 
	 */
	private boolean posValida(List<Integer> parziale, int colonna) {
		// TODO Auto-generated method stub
		int livello = parziale.size();
		//controlla se viene mangiata in verticale 
		if(parziale.contains(colonna)) {
			return false;
		}
		//le diagonali hanno la proprietà che riga + colonna di una casella = costante 
		//quindi per il controllo sulla diagonale devo fare il controllo  cosi :
		//devo confrontare la posizione (livello,colonna ) con (r,c) delle regine gia esistenti
		for(int r =0; r<livello ; r++) {
			int c = parziale.get(r);
			
			if(r+c == livello+colonna || r-c == livello-colonna) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
