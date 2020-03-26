package gestorAplicacion.Administrador;

import java.util.ArrayList;

import UImain.*;
import gestorAplicacion.Usuario.*;

public class Administrador extends Persona {
	//Doesn't have attributes
	//Constructors
	public Administrador(String nombre, boolean genero, int edad, String usuario, String contraseña, String pregunta, String respuesta) {
		super(nombre, genero, edad, usuario, contraseña, pregunta, respuesta);
	}
	@Override
	public String mostrarProductos() {
		String s=new String();
		s="-------------------------------------\nProductos:\n-------------------------------------\n";
		s=s+Main.inventario.toString2();
		return s;
	}
	//Abstract Methods
	@Override
	public int busqueda(String nombreProducto) {
		return Main.inventario.RealizarBusqueda(nombreProducto);
	}
	@Override
	public ArrayList<Producto> busqueda(int indexCategoria) {
		return Main.inventario.RealizarBusqueda(indexCategoria);
	}
	
	//Methods
	//Create
	public void crearProducto(String name, String description, double oPrice, double sPrice) {
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
		Main.inventario.getInventario(index).getProducto().setNombre(name);
	}
	public void modificarDescripcionProducto(int index, String description) {
		Main.productos.get(index).setDescripcion(description);
		Main.inventario.getInventario(index).getProducto().setDescripcion(description);
	}
	public void addCategoriaProducto(int index, Categoria cat1){
		Main.productos.get(index).getCategoria().add(cat1);
		Main.inventario.getInventario(index).getProducto().getCategoria().add(cat1);
	}
	public void delCategoriaProducto(int index, Categoria cat1){
		Main.productos.get(index).getCategoria().remove(cat1);
	}
	public void modificarPrecioCompra(int index, double price) {
		Main.productos.get(index).setPrecioCompra(price);
		Main.inventario.getInventario(index).getProducto().setPrecioCompra(price);
	}
	public void modificarPrecioVenta(int index, double price) {
		Main.productos.get(index).setPrecioVenta(price);
		Main.inventario.getInventario(index).getProducto().setPrecioVenta(price);
	}
	//Modificar Categoria
	public void modificarNombreCategoria(int index, String name) {
		Main.categorias.get(index).setNombre(name);
	}
	public void modificarDescripcionCategoria(int index, String description) {
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
	public void modificarCantidadProducto(int index, int amount){
		Main.inventario.getInventario().get(index).setCantidad(amount);
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
	
	public void CrearUsuario(String nombre, boolean genero, int edad, String usuario, String contraseña, String pregunta,String respuesta) {
		Main.Usuarios.add(new Usuario(nombre,genero,edad,usuario,contraseña,pregunta,respuesta));
	}
	
	public String toString() {
		String s = new String();
		s="Perfil: Administrador\nNombre: "+this.getNombre()+"\nEdad: "+this.getEdad()+"\nGenero: ";
		if (getGenero()) {
			s=s+"Masculino\n";
		}else {
			s=s+"Femenino\n";
		}
		s=s+"Usuario: "+this.getUsuario();
		return s;
	}
}