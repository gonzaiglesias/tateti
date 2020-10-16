import JuegoTateti.JugarTatetiInterface;
import Tablero.Jugador;
import Tablero.Posicion;
import Tablero.Resultado;
import Tablero.TableroTateti;

import java.util.Scanner;

public class MainTateti {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			TableroTateti tablero = new TableroTateti();
			JugarTatetiInterface jugar = new JugarTateti();
			
			
			boolean cortarCiclo = false;
			while (tablero.getResult() == Resultado.SIN_GANADOR && cortarCiclo ==false ) {
				   System.out.println("Tablero Antes de Jugar Adversario");
				   tablero.printTablero();	
				   Posicion posAdversario = getPosicionPorPantalla();
			       while (tablero.getValueAt(posAdversario.posX, posAdversario.posY) != Jugador.SIN_JUGADOR) {
						System.out.println("Posici�n inv�lida, ya se encuentra ocupada");
						posAdversario = getPosicionPorPantalla();
					 }
			       
						
			       tablero.setValueAt(posAdversario.posX, posAdversario.posY, Jugador.JUGADOR_UNO); 
			       
			      if (tablero.getResult() == Resultado.SIN_GANADOR) {
							Posicion posMaquina = jugar.unaJugadaTateti(tablero);
							if (tablero.getValueAt(posMaquina.posX, posMaquina.posY ) == Jugador.SIN_JUGADOR) {
							     tablero.setValueAt(posMaquina.posX, posMaquina.posY, Jugador.JUGADOR_DOS);
							}else{
								System.out.println("Resultado inv�lido del algoritmo por arrojar una casilla que ya esta ocupada");
								cortarCiclo = true;
							}
			        }else {
			        	cortarCiclo = true;
			        }
			        
			}		
		
            System.out.println("Tablero Final");		
			tablero.printTablero();
	}
	

	public static Posicion getPosicionPorPantalla() {
		Posicion pos = new Posicion();
		int x = -1;
		int y = -1;

        Scanner teclado = new Scanner(System.in);
        while (x == -1) {
            System.out.print("Introduzca coordenada en X: ");
            x = teclado.nextInt();
            if (x < 0 || x >2) {
            	System.out.print("El valor de X debe estar entre 0 y 2, por favor, reintente nuevamente");
            	x= -1;                	
            }
        }

        while (y == -1) {
            System.out.print("Introduzca coordenada en Y: ");
            y = teclado.nextInt();
            if (y < 0 || y >2) {
            	System.out.print("El valor de Y debe estar entre 0 y 2, por favor, reintente nuevamente");
            	y= -1;                	
            }
        }
        
       
		pos.posX = x;
		pos.posY = y;
		return pos;
	}

}
