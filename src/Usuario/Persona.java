package Usuario;

public abstract class Persona {
	//Attributes
	private String nombre;
	private boolean genero;
	private int edad;
	private String usuario;
	private String contrase�a;
	private String preguntaRecuperacion;
	private String respuestaRecuperacion;
	//Constructors(OK)
	public Persona(String nombre, boolean genero, int edad, String usuario, String contrase�a, String pregunta, String respuesta) {
		this.nombre=nombre;
		this.edad=edad;
		this.genero=genero;
		this.usuario=usuario;
		this.contrase�a=contrase�a;
		this.preguntaRecuperacion=pregunta;
		this.respuestaRecuperacion=respuesta;
	}
	//Setters(OK)
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setGenero(boolean genero) {
		this.genero=genero;
	}
	public void setEdad(int edad) {
		if (edad>10) {
			this.edad=edad;
		}
	}
	public void setUsuario(String usuario) {
		this.usuario=usuario;
	}
	public void setContrase�a(String contrase�aActual, String contrase�aNueva) {
		if(this.comprobarContrase�a(contrase�aActual)) {
			this.contrase�a=contrase�aNueva;
		}
	}
	//Getters(OK)
	public String getNombre() {
		return this.nombre;
	}
	public boolean getGenero() {
		return this.genero;
	}
	public int getEdad() {
		return this.edad;
	}
	public String getUsuario() {
		return this.usuario;
	}
	public String getPregunta() {
		return this.preguntaRecuperacion;
	}
	//concrete methods
	public boolean comprobarContrase�a(String contrase�a) {
		if (this.contrase�a.equals(contrase�a)) {
			return true;
		}
		return false;
	}
	public boolean comprobarRespuesta(String respuesta) {
		if(this.respuestaRecuperacion.equals(respuesta)) {
			return true;
		}
		return false;
	}
	public void recuperarContrase�a(String respuesta, String contrase�aNueva) {
		if (this.comprobarRespuesta(respuesta)) {
			this.contrase�a=contrase�aNueva;
		}
	}
	//abstract methods
	public abstract void mostrarInventario();
	public abstract void busqueda(String nombreProducto);
	public abstract void busqueda();
}
