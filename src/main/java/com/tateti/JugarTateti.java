import JuegoTateti.JugarTatetiInterface;
import Tablero.Posicion;
import Tablero.TableroTateti;

public class JugarTateti implements JugarTatetiInterface{

	public Posicion unaJugadaTateti(TableroTateti tablero) {
		//Ac� se debe implementar el algoritmo que devuelve la mejor jugada para la
		//maquina
		
		
		//	POSICION HARDCODE PARA QUE COMPILE -- REEMPLAZAR POR ALGORITMO!!!
		Posicion pos = new Posicion();
		pos.posX = 2;
		pos.posY = 2;
		
		return pos;
	}
}
