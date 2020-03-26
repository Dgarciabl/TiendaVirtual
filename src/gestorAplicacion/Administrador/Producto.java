package gestorAplicacion.Administrador;
import java.util.*;

public class Producto {
	
	//Atributos
	private String nombre;
	private String descripcion;
	private Categoria categorias;
	private double precioVenta;
	private double precioCompra;
	
	//Constructores
	public Producto(String n,String d, double pv, double pc){
		this.nombre=n; this.descripcion=d;
		this.precioVenta=pv; this.precioCompra=pc;
		categorias=new Categoria("Ninguna","Nada que ver aqui");
	}
	public Producto(String n,String d, double pv, double pc, Categoria cat){
		this.nombre=n; this.descripcion=d;
		this.precioVenta=pv; this.precioCompra=pc;
		categorias=cat;
	}
	//Getters
	public String getNombre() {return this.nombre;}
	
	public String getDescripcion() {return this.descripcion;}
	
	public Categoria getCategoria() {return this.categorias;}
	
	public double getPrecioVenta() {return this.precioVenta;}
	
	public double getPrecioCompra() {return this.precioCompra;}
	
	//Setters
	void setNombre(String s) {this.nombre=s;}
	
	void setDescripcion(String s) {this.descripcion=s;}
	
	void setPrecioVenta(double i) {this.precioVenta=i;}
	
	void setPrecioCompra(double i) {this.precioCompra=i;}
	
	void setCategoria(Categoria categoria) {this.categorias=categoria;}
	@Override
	public String toString() {
		String s= new String();
		s=s+"Nombre: "+this.nombre+"\n"+"Descripcion:\n"+this.descripcion+"\nCategorias:"+this.categorias.getNombre()+"\nPrecio: "+this.precioVenta+"\n";
		return s;
	}
	public String toString2() {
		String s= new String();
		s=s+"Nombre: "+this.nombre+"\n"+"Descripcion:\n"+
		this.descripcion+"\nCategorias:"+this.categorias.getNombre()+"\nPrecio de Compra: "+this.precioCompra+"\nPrecio de Venta: "+this.precioVenta+"\n";

		return s;
	}
	

}
