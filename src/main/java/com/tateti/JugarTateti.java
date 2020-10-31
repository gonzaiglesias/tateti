package com.tateti;

import JuegoTateti.JugarTatetiInterface;
import Tablero.Jugador;
import Tablero.Posicion;
import Tablero.Resultado;
import Tablero.TableroTateti;

public class JugarTateti implements JugarTatetiInterface{

	private static final int MAX_VALUE = 999;
	private static final int MIN_VALUE = -999;

	public Posicion unaJugadaTateti(TableroTateti tablero) {

		Posicion bestMove = new Posicion();
		int bestValue = MIN_VALUE;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tablero.getValueAt(i,j) == Jugador.SIN_JUGADOR) {
					tablero.setValueAt(i,j,Jugador.JUGADOR_DOS);
					int moveValue = miniMax(tablero, false);
					tablero.setValueAt(i,j,Jugador.SIN_JUGADOR);
					if (moveValue > bestValue) {
						bestMove.posX = i;
						bestMove.posY = j;
						bestValue = moveValue;
					}
				}
			}
		}
		return bestMove;
	}

	private int miniMax(TableroTateti tablero, Boolean isMax) {

		if(tablero.getResult() != Resultado.SIN_GANADOR)
			if(tablero.getResult() == Resultado.GANO_JUGADOR1)
				return -1;
			else if(tablero.getResult() == Resultado.GANO_JUGADOR2) {
				return 1;
			}else{
				return 0;
			}

		if (isMax) {
			int bestScore = MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tablero.getValueAt(i,j) == Jugador.SIN_JUGADOR){
							tablero.setValueAt(i,j,Jugador.JUGADOR_DOS);
							bestScore = Math.max(bestScore, miniMax(tablero, false));
							tablero.setValueAt(i,j,Jugador.SIN_JUGADOR);
					}
				}
			}
			return bestScore;
		}else{
			int bestScore = MAX_VALUE;
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++) {
					if (tablero.getValueAt(i,j) == Jugador.SIN_JUGADOR) {
						tablero.setValueAt(i, j, Jugador.JUGADOR_UNO);
						bestScore = Math.min(bestScore, miniMax(tablero, true));
						tablero.setValueAt(i, j, Jugador.SIN_JUGADOR);
					}
				}
			}
			return bestScore;
		}
	}
}
