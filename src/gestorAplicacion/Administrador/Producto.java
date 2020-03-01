package gestorAplicacion.Administrador;
import java.util.*;

public class Producto {
	
	//Atributos
	private String nombre;
	private String descripcion;
	private ArrayList<Categoria> categorias;
	private double precioVenta;
	private double precioCompra;
	private float calificacionProducto;
	
	//Constructores
	public Producto(String n,String d, double pv, double pc){
		this.nombre=n; this.descripcion=d;
		this.precioVenta=pv; this.precioCompra=pc;
	}
	
	//Getters
	public String getNombre() {return this.nombre;}
	
	public String getDescripcion() {return this.descripcion;}
	
	public ArrayList<Categoria> getCategoria() {return this.categorias;}
	
	public double getPrecioVenta() {return this.precioVenta;}
	
	public double getPrecioCompra() {return this.precioCompra;}
	
	public float getCalificacion() {return this.calificacionProducto;}
	
	//Setters
	void setNombre(String s) {this.nombre=s;}
	
	void setDescripcion(String s) {this.descripcion=s;}
	
	void setPrecioVenta(int i) {this.precioVenta=i;}
	
	void setPrecioCompra(int i) {this.precioCompra=i;}
	
	
	//Metodos Concretos (EN ESPERA)
	void AgregarCategoria(Categoria cat) {
		
	}
	
	void EliminarCategoria() {
		
	}
	public void Calificar(float C) {
		
	}
	
	
	

}
