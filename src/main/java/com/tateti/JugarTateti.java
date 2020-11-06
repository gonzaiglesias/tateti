package com.tateti;

import JuegoTateti.JugarTatetiInterface;
import Tablero.Jugador;
import Tablero.Posicion;
import Tablero.TableroTateti;

import java.util.Vector;

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
					posActual.posX = i;
					posActual.posY = j;
					DesmarcarTablero(tablero, posActual);
				}
			}
		}
		return mejorPosicion;
	}


	private int Tateti(TableroTateti tablero, Posicion pos, int valor, Jugador jugador)
	{
		switch(tablero.getResult()) {
			case EMPATE:
				return 0;
			case GANO_JUGADOR1:
				return -1;
			case GANO_JUGADOR2:
				return 1;
			default:
				break;
		}

		valor = jugador == Jugador.JUGADOR_UNO ? VALOR_MAYOR : VALOR_MENOR;
		int j1 = -1;
		int j2 = 1;


		Vector<Posicion> posicionesLibres = ObtenerPosicionesLibre(tablero);
		Posicion posicionLibre = new Posicion();

		while(posicionesLibres.size() > 0 && valor != j1 && valor != j2)
		{
			posicionLibre = TomarUnaPosicionLibre(posicionesLibres);
			MarcarPosicion(tablero, posicionLibre, jugador);

			if(jugador == Jugador.JUGADOR_UNO) {
				int valorResultado = Tateti(tablero, pos, valor, Jugador.JUGADOR_DOS);
				if(valor > valorResultado) {
					pos.posX = posicionLibre.posX;
					pos.posY = posicionLibre.posY;
					valor = valorResultado;
				}
			}
			else{
				int valorResultado = Tateti(tablero, pos, valor, Jugador.JUGADOR_UNO);
				if(valor < valorResultado) {
					pos.posX = posicionLibre.posX;
					pos.posY = posicionLibre.posY;
					valor = valorResultado;
				}
			}

			DesmarcarTablero(tablero, posicionLibre);
		}
		return valor;
	}

	private void DesmarcarTablero(TableroTateti tablero, Posicion pos)
	{
		tablero.setValueAt(pos.posX, pos.posY, Jugador.SIN_JUGADOR);
	}

	private Vector<Posicion> ObtenerPosicionesLibre(TableroTateti tablero)
	{
		Vector<Posicion> posiciones = new Vector<>();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j< 3; j++) {
				if(tablero.getValueAt(i, j) == Jugador.SIN_JUGADOR) {
					Posicion pos = new Posicion();
					pos.posX = i;
					pos.posY = j;
					posiciones.add(pos);
				}
			}
		}

		return posiciones;
	}

	private Posicion TomarUnaPosicionLibre(Vector<Posicion> posiciones)
	{
		Posicion pos = posiciones.get(0);
		posiciones.remove(0);

		return pos;
	}

	private void MarcarPosicion(TableroTateti tablero, Posicion pos, Jugador jugador)
	{
		tablero.setValueAt(pos.posX, pos.posY, jugador);
	}
}
