package gestorAplicacion.Usuario;
import gestorAplicacion.Administrador.Categoria;
import gestorAplicacion.Administrador.Producto;
import java.util.*;

import UImain.Main;

public abstract class Persona {
	//Attributes
	private String nombre;
	private boolean genero;
	private int edad;
	private String usuario;
	private String contraseña;
	private String preguntaRecuperacion;
	private String respuestaRecuperacion;
	//Constructors(OK)
 	public Persona(String nombre, boolean genero, int edad, String usuario, String contraseña, String pregunta, String respuesta) {
		this.nombre=nombre;
		this.edad=edad;
		this.genero=genero;
		this.usuario=usuario;
		this.contraseña=contraseña;
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
	public void setContraseña(String contraseñaActual, String contraseñaNueva) {
		if(this.comprobarContraseña(contraseñaActual)) {
			this.contraseña=contraseñaNueva;
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
	//concrete methods(OK)
	public boolean comprobarContraseña(String contraseña) {
		if (this.contraseña.equals(contraseña)) {
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
	public void recuperarContraseña(String respuesta, String contraseñaNueva) {
		if (this.comprobarRespuesta(respuesta)) {
			this.contraseña=contraseñaNueva;
		}
	}
	public static void mostrarCategorias() {
		ArrayList<Categoria> Temp=Main.categorias;
		String s=new String();
		s="-------------------------------------\nCategorias:\n-------------------------------------\n";
		for (int i=0; i<Temp.size();i++) {
			s=(i)+") "+Temp.get(i).toString()+"\n-------------------------------------\n";
			System.out.println(s);
		}
	}
	
	//abstract methods(OK)
	public abstract String mostrarProductos();
	public abstract int busqueda(String nombreProducto);
	public abstract ArrayList<Producto> busqueda(int indexCategoria);
}