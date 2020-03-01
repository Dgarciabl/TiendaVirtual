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
	public void crearProducto(String name, String description, int oPrice, int sPrice) {
		Producto productI = new Producto(name, description, oPrice, sPrice);	
		Main.productos.add(productI);
	}
	
	public void crearCategoria(String name, String description) {
		Categoria categoryI = new Categoria(name, description);
		Main.categorias.add(categoryI);
	}
	
	public void añadirDetalle(Producto obj1, int amount) {
		Detalle detailI = new Detalle(obj1,amount);
		Main.inventario.AddInventario(detailI);
	}
	  //Modify
// Metodo modificar para cada atributo, 
	//Modificar Producto
	public void modificarNombreProducto(int index, String name){
		Main.productos.get(index).setNombre(name);	
	}
	public void modificarDescripcionProducto(int index, String description) {
		Main.productos.get(index).setDescripcion(description);
	}
	public void addCategoriaProducto(int index, Categoria cat1){
		Main.productos.get(index).getCategoria().add(cat1);
	}
	public void delCategoriaProducto(int index, Categoria cat1){
		Main.productos.get(index).getCategoria().remove(cat1);
	}
	public void modificarPrecioCompra(int index, int price) {
		Main.productos.get(index).setPrecioCompra(price);
	}
	public void modificarPrecioVenta(int index, int price) {
		Main.productos.get(index).setPrecioVenta(price);
	}
	public void resetCalificacion(int index) {
		Main.productos.get(index).Calificar(5);
	}
	
	//Modificar Categoria
	public void modificarNombre(int index, String name) {
		Main.categorias.get(index).setNombre(name);
	}
	public void modificarDescripcion(int index, String description) {
		Main.categorias.get(index).setDescripcion(description);
	}
	
	//Modificar Inventario
	
	public void addProductoInventario(int index, Detalle detail){
		Main.inventario.AddInventario(detail);
	}
	public void delProductoInventario(int index, Detalle detail) {
		Main.inventario.DelInventario(detail);
	}
	public void modificarProductoInventario(Detalle detail, int amount) {
		Main.inventario.getInventario().get(Main.inventario.getInventario().indexOf(detail)).setCantidad(amount);
	}
	 //Eliminate
	public void eliminarProducto(int i){
		Main.productos.remove(i);
		
	}
	public void eliminarCategoria(int i){
		Main.categorias.remove(i);
		
	}
	public void eliminarDetalle(int i) {
		Main.inventario.DelInventario(i);
		
	}
	  //Statistics
	public void verEstadisticas() {
		
	}
}