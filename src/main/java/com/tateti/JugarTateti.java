package com.tateti;

import JuegoTateti.JugarTatetiInterface;
import Tablero.Jugador;
import Tablero.Posicion;
import Tablero.Resultado;
import Tablero.TableroTateti;

public class JugarTateti implements JugarTatetiInterface{

	private static final int VALOR_MENOR = -999;
	private static final int VALOR_MAYOR = 999;

	public Posicion unaJugadaTateti(TableroTateti tablero) {
		Posicion posActual = new Posicion();
		Posicion mejorPosicion = new Posicion();
		int mejorValor = VALOR_MENOR;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j< 3; j++) {
				if (tablero.getValueAt(i,j) == Jugador.SIN_JUGADOR) {
					posActual.posX = i;
					posActual.posY = j;
					MarcarPosicion(tablero, posActual, Jugador.JUGADOR_DOS);
					int valorActual = Tateti(tablero, posActual, VALOR_MAYOR, Jugador.JUGADOR_UNO);
					if (valorActual > mejorValor) {
						mejorPosicion.posX = i;
						mejorPosicion.posY = j;
						mejorValor = valorActual;
					}
					DesmarcarTablero(tablero, i, j);
				}
			}
		}
		return mejorPosicion;
	}


	private int Tateti(TableroTateti tablero, Posicion pos, int valor, Jugador jugador)
	{
		if(tablero.getResult() != Resultado.SIN_GANADOR){
			return resultadoTablero(tablero);
		}

		Posicion posicionLibre = new Posicion();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j< 3; j++) {
				if (tablero.getValueAt(i, j) == Jugador.SIN_JUGADOR && (valor == VALOR_MAYOR || valor == VALOR_MENOR)) {
					posicionLibre.posX = i;
					posicionLibre.posY = j;
					MarcarPosicion(tablero, posicionLibre, jugador);
					if (jugador == Jugador.JUGADOR_UNO){
						valor = VALOR_MAYOR;
						valor = Math.min(valor, Tateti(tablero, posicionLibre, valor, Jugador.JUGADOR_DOS));
					}else{
						valor = VALOR_MENOR;
						valor = Math.max(valor, Tateti(tablero, posicionLibre, valor, Jugador.JUGADOR_UNO));
					}
					DesmarcarTablero(tablero, posicionLibre.posX, posicionLibre.posY);
				}
			}
		}
		return valor;
	}

	private int resultadoTablero(TableroTateti tablero) {
		switch(tablero.getResult()) {
			case EMPATE:
				return 0;
			case GANO_JUGADOR1:
				return -1;
			case GANO_JUGADOR2:
				return 1;
		}
		return 0;
	}

	private void DesmarcarTablero(TableroTateti tablero, int posX, int posY) {
		tablero.setValueAt(posX, posY, Jugador.SIN_JUGADOR);
	}

	private void MarcarPosicion(TableroTateti tablero, Posicion pos, Jugador jugador) {
		tablero.setValueAt(pos.posX, pos.posY, jugador);
	}
}
