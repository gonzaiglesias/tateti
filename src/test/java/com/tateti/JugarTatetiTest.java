package com.tateti;

import JuegoTateti.JugarTatetiInterface;
import Tablero.Jugador;
import Tablero.Posicion;
import Tablero.Resultado;
import Tablero.TableroTateti;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JugarTatetiTest {

    @Test
    public  void maquinaConDosHrzDeberiaGanar(){
        //Given
        TableroTateti tablero = new TableroTateti();
        JugarTatetiInterface jugar = new JugarTateti();
        tablero.setValueAt(1, 0, Jugador.JUGADOR_DOS);
        tablero.setValueAt(1, 1, Jugador.JUGADOR_DOS);
        //When
        Posicion posMaquina = jugar.unaJugadaTateti(tablero);
        tablero.setValueAt(posMaquina.posX, posMaquina.posY, Jugador.JUGADOR_DOS);
        //Then
        assertEquals(Resultado.GANO_JUGADOR2, tablero.getResult());
    }

    @Test
    public  void maquinaConDosVerticalDeberiaGanar(){
        //Given
        TableroTateti tablero = new TableroTateti();
        JugarTatetiInterface jugar = new JugarTateti();
        tablero.setValueAt(2, 0, Jugador.JUGADOR_DOS);
        tablero.setValueAt(2, 1, Jugador.JUGADOR_DOS);
        //When
        Posicion posMaquina = jugar.unaJugadaTateti(tablero);
        tablero.setValueAt(posMaquina.posX, posMaquina.posY, Jugador.JUGADOR_DOS);
        //Then
        assertEquals(Resultado.GANO_JUGADOR2, tablero.getResult());
    }

    @Test
    public  void maquinaConDosDiagonalDeberiaGanar(){
        //Given
        TableroTateti tablero = new TableroTateti();
        JugarTatetiInterface jugar = new JugarTateti();
        tablero.setValueAt(0, 0, Jugador.JUGADOR_DOS);
        tablero.setValueAt(1, 1, Jugador.JUGADOR_DOS);
        //When
        Posicion posMaquina = jugar.unaJugadaTateti(tablero);
        tablero.setValueAt(posMaquina.posX, posMaquina.posY, Jugador.JUGADOR_DOS);
        //Then
        assertEquals(Resultado.GANO_JUGADOR2, tablero.getResult());
    }

    @Test
    public  void jugConDosHrzYMaquinaPuedeGanarDeberiaGanarMaquina(){
        //Given
        TableroTateti tablero = new TableroTateti();
        JugarTatetiInterface jugar = new JugarTateti();
        tablero.setValueAt(1, 0, Jugador.JUGADOR_UNO);
        tablero.setValueAt(1, 1, Jugador.JUGADOR_UNO);
        tablero.setValueAt(2, 0, Jugador.JUGADOR_DOS);
        tablero.setValueAt(2, 1, Jugador.JUGADOR_DOS);
        //When
        Posicion posMaquina = jugar.unaJugadaTateti(tablero);
        tablero.setValueAt(posMaquina.posX, posMaquina.posY, Jugador.JUGADOR_DOS);
        //Then
        assertEquals(Resultado.GANO_JUGADOR2, tablero.getResult());
    }

    @Test
    public  void jugConDosHrzYMaquinaNoPuedeGanarMaquinaDeberiaBloquear(){
        //Given
        TableroTateti tablero = new TableroTateti();
        JugarTatetiInterface jugar = new JugarTateti();
        tablero.setValueAt(1, 0, Jugador.JUGADOR_UNO);
        tablero.setValueAt(1, 1, Jugador.JUGADOR_UNO);
        tablero.setValueAt(0, 0, Jugador.JUGADOR_DOS);
        tablero.printTablero();
        //When
        Posicion posMaquina = jugar.unaJugadaTateti(tablero);
        tablero.setValueAt(posMaquina.posX, posMaquina.posY, Jugador.JUGADOR_DOS);
        //Then
        assertEquals(Jugador.JUGADOR_DOS, tablero.getValueAt(1, 2));
    }
    @Test
    public  void jugConDosVerticalYMaquinaNoPuedeGanarMaquinaDeberiaBloquear(){
        //Given
        TableroTateti tablero = new TableroTateti();
        JugarTatetiInterface jugar = new JugarTateti();
        tablero.setValueAt(0, 1, Jugador.JUGADOR_UNO);
        tablero.setValueAt(1, 1, Jugador.JUGADOR_UNO);
        tablero.setValueAt(0, 0, Jugador.JUGADOR_DOS);
        //When
        Posicion posMaquina = jugar.unaJugadaTateti(tablero);
        tablero.setValueAt(posMaquina.posX, posMaquina.posY, Jugador.JUGADOR_DOS);
        //Then
        assertEquals(Jugador.JUGADOR_DOS, tablero.getValueAt(2, 1));
    }
}
