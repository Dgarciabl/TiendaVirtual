package gestorAplicacion.Exepciones;

public class ErrorAplicacion extends Exception {
	public ErrorAplicacion(String complemento) {
		super("Manejo de errores de la aplicacion:"+complemento);
	}
}
