package gestorAplicacion.Administrador;

public class Estadistica {
	private Float gananciaNeta;
	private Float gananciaHombres;
	private Float gananciaMujeres;
	private Float gananciaPorObjeto;
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
}
