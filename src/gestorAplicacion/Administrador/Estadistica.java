package gestorAplicacion.Administrador;
import java.util.ArrayList;

public class Estadistica {
	private float gananciaNeta;
	private float gananciaHombres;
	private float gananciaMujeres;
	private float gananciaPorObjeto;
	double VerGananciaReal() {
		return gananciaNeta;
	}
	double VerGananciaHombre() {
		return gananciaHombres;
	}
	double VerGananciaMujeres() {
		return gananciaMujeres;
	}
	double VerGananciaPorObjeto() {
		return gananciaPorObjeto;
	}
	ArrayList<Float> VerEstadisticas(){
		ArrayList<Float> estadisticas = new ArrayList<Float>();
		estadisticas.add(gananciaNeta);
		estadisticas.add(gananciaHombres);
		estadisticas.add(gananciaMujeres);
		estadisticas.add(gananciaPorObjeto);
		return estadisticas;
	} 
}
