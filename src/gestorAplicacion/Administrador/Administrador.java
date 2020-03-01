package gestorAplicacion.Administrador;

import java.util.ArrayList;

import UImain.Main;
import gestorAplicacion.Usuario.Persona;

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
	public void mostrarCategorias() {
		ArrayList<Categoria> Temp=Main.categorias;
		System.out.println("-------------------------------------");
		System.out.println("Categorias:");
		System.out.println("-------------------------------------");
		for (int i=0; i<Temp.size();i++) {
			System.out.println(i+1 +")"+ Temp.get(i).getNombre());
			System.out.println("Descripcion:");
			System.out.println(Temp.get(i).getDescripcion());
			System.out.println("-------------------------------------");
		}
	}
	@Override
	public void mostrarProductos() {
		ArrayList<Detalle> Temp=Main.inventario.getInventario();
		System.out.println("-------------------------------------");
		System.out.println("Inventario:");
		System.out.println("-------------------------------------");
		for (int i=0; i<Temp.size();i++) {
			System.out.println(i+1 +")"+ Temp.get(i).getProducto().getNombre());
			System.out.println("Descripcion:");
			System.out.println(Temp.get(i).getProducto().getDescripcion());
			System.out.println("Precio de Venta: "+Temp.get(i).getProducto().getPrecioVenta());
			System.out.println("Precio de compra: "+Temp.get(i).getProducto().getPrecioCompra());
			System.out.println("Unidades Disponibles: "+Temp.get(i).getCantidad());
			System.out.println("Categorias:");
			for (int j=0;j<Temp.get(i).getProducto().getCategoria().size();j++) {
				System.out.println("     "+Temp.get(i).getProducto().getCategoria().get(j));
			}
			System.out.println("-------------------------------------");
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