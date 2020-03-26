package gestorAplicacion.Exepciones;

public class FormularioIncompletoError extends ExcepcionUsuario {
	public FormularioIncompletoError() {
		super();  
	}
	public String toString() {
		return("Formulario incompleto");	
	}
}
