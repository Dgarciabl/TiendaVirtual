package gestorAplicacion.Administrador;

import gestorAplicacion.Usuario.Persona;

public class Administrador extends Persona {
	//Doesn't have attributes
	//Constructors
	public Administrador(String nombre, boolean genero, int edad, String usuario, String contrase�a, String pregunta, String respuesta) {
		super(nombre, genero, edad, usuario, contrase�a, pregunta, respuesta);
	}
	public Administrador(String usuario, String contrase�a, String pregunta, String respuesta) {
		super(usuario, contrase�a, pregunta, respuesta);
	}
	//Abstract Methods
	@Override
	public void mostrarInventario() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mostrarCategorias() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mostrarProductos() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void busqueda(String nombreProducto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void busqueda(int indexCategoria) {
		// TODO Auto-generated method stub
		
	}
	//Methods
	  //Create
	public void crearProducto() {
		
	}
	public void crearCategoria() {
		
	}
	public void a�adirDetalle() {
	
	}
	  //Modify
	public void modificarProducto() {
		
	}
	public void modificarCategoria() {
		
	}
	public void modificarInventario() {
		
	}
	  //Eliminate
	public void eliminarProducto() {
		
	}
	public void eliminarCategoria() {
		
	}
	public void eliminarDetalle() {
		
	}
	  //Statistics
	public void verEstadisticas() {
		
	}
}
