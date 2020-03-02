package gestorAplicacion.Administrador;
import java.util.ArrayList;

public class Estadistica {
	private float gananciaNeta;
	private float gananciaHombres;
	private float gananciaMujeres;
	private float gananciaPorObjeto;
	private int N_ObjetosHombres;
	private int N_ObjetosMujeres;
	private int N_ObjetosTotal;
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
	int VerN_ObjetosHombres() {
		return N_ObjetosHombres;
	}
	int VerN_ObjetosMujeres() {
		return N_ObjetosMujeres;
	}
	int VerN_ObjetosTotal() {
		return N_ObjetosTotal;
	}
	ArrayList<Float> VerEstadisticas(){
		ArrayList<Float> estadisticas = new ArrayList<Float>();
		estadisticas.add(gananciaNeta);
		estadisticas.add(gananciaHombres);
		estadisticas.add(gananciaMujeres);
		estadisticas.add(gananciaPorObjeto);
		return estadisticas;
	} 
	public void Actualizar(Producto prod, boolean genero) {//true hombre, false mujeres
		
	}  //true hombre, false mujeres
	
}
