package gestorAplicacion.Administrador;
import UImain.Main;

import gestorAplicacion.Usuario.Persona;
import java.util.*;

public class Administrador extends Persona {
	//Doesn't have attributes
	//Constructors
	public Administrador(String nombre, boolean genero, int edad, String usuario, String contraseña, String pregunta, String respuesta) {
		super(nombre, genero, edad, usuario, contraseña, pregunta, respuesta);
	}
	public Administrador(String usuario, String contraseña, String pregunta, String respuesta) {
		super(usuario, contraseña, pregunta, respuesta);
	}
	//Abstract Methods
	@Override
	public void mostrarInventario() {
		System.out.println(Main.inventario);
	}
	@Override
	public void mostrarCategorias() {
		ArrayList<Categoria> Cat=Main.categorias;
		System.out.println("Lista de categorias:");
		for (int i=0;i<Cat.size();i++) {
			Categoria Temp=Cat.get(i);
			System.out.println(Temp.getNombre());
			System.out.println(Temp.getDescripcion());
			System.out.println("---------------");
		}
		
	}
	@Override
	public void mostrarProductos() {
		ArrayList<Producto> Temp=Main.productos;
		for (int i=0;i<Temp.size();i++) {
			Producto Temp2=Temp.get(i);
			System.out.println("Nombre: " + Temp2.getNombre());
			System.out.println("Descripcion: " + Temp2.getDescripcion());
			System.out.println("Precio de compra:" + Temp2.getPrecioCompra());
			System.out.println("Precio de venta: " + Temp2.getPrecioVenta());
			System.out.println("--------------------------");
		}
		
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
	public void añadirDetalle() {
	
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
