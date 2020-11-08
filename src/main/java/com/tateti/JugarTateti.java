package com.tateti;

import JuegoTateti.JugarTatetiInterface;
import Tablero.Jugador;
import Tablero.Posicion;
import Tablero.Resultado;
import Tablero.TableroTateti;

public class JugarTateti implements JugarTatetiInterface{

	private static final int VALOR_MENOR = -999;
	private static final int VALOR_MAYOR = 999;
	private static final int NIVEL_INICIAL = 0;

	public Posicion unaJugadaTateti(TableroTateti tablero) {
		Posicion mejorPosicion = new Posicion();
		int mejorValor = VALOR_MENOR;
		for(int posX = 0; posX < 3; posX++) {
			for(int posY = 0; posY< 3; posY++) {
				if (tablero.getValueAt(posX,posY) == Jugador.SIN_JUGADOR) {
					MarcarPosicion(tablero, posX, posY, Jugador.JUGADOR_DOS);
					int valorActual = Tateti(tablero, Jugador.JUGADOR_UNO, NIVEL_INICIAL);
					if (valorActual > mejorValor) {
						mejorPosicion.posX = posX;
						mejorPosicion.posY = posY;
						mejorValor = valorActual;
					}
					DesmarcarTablero(tablero, posX, posY);
				}
			}
		}
		return mejorPosicion;
	}


	private int Tateti(TableroTateti tablero, Jugador jugador, int nivel)
	{
		if(tablero.getResult() != Resultado.SIN_GANADOR){
			return resultadoTablero(tablero, nivel);
		}
		int valor = asignarValorInicial(jugador);
		for(int posX = 0; posX < 3; posX++) {
			for(int posY = 0; posY< 3; posY++) {
				if (tablero.getValueAt(posX, posY) == Jugador.SIN_JUGADOR) {
					MarcarPosicion(tablero, posX, posY , jugador);
					if (jugador == Jugador.JUGADOR_UNO){
						valor = Math.min(valor, Tateti(tablero, Jugador.JUGADOR_DOS, nivel+1));
					}else{
						valor = Math.max(valor, Tateti(tablero, Jugador.JUGADOR_UNO, nivel+1));
					}
					DesmarcarTablero(tablero, posX, posY);
				}
			}
		}
		return valor;
	}

	private int asignarValorInicial(Jugador jugador) {
		return jugador == Jugador.JUGADOR_UNO ? VALOR_MAYOR : VALOR_MENOR;
	}

	private int resultadoTablero(TableroTateti tablero, int nivel) {
		switch(tablero.getResult()) {
			case EMPATE:
				return 0;
			case GANO_JUGADOR1:
				return -10+nivel;
			case GANO_JUGADOR2:
				return 10-nivel;
		}
		return 0;
	}

	private void DesmarcarTablero(TableroTateti tablero, int posX, int posY) {
		tablero.setValueAt(posX, posY, Jugador.SIN_JUGADOR);
	}

	private void MarcarPosicion(TableroTateti tablero, int posX, int posY, Jugador jugador) {
		tablero.setValueAt(posX, posY, jugador);
	}
}
