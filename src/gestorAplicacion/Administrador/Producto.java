package gestorAplicacion.Administrador;
import java.util.*;

public class Producto {
	
	//Atributos
	private String nombre;
	private String descripcion;
	private ArrayList<Categoria> categorias;
	private int precioVenta;
	private int precioCompra;
	private float calificacionProducto;
	
	//Constructores
	public Producto(String n,String d, int pv, int pc){
		this.nombre=n; this.descripcion=d; this.categorias=cat;
		this.precioVenta=pv; this.precioCompra=pc; this.calificacionProducto=cp;
	}
	
	//Getters
	public String getNombre() {return this.nombre;}
	
	public String getDescripcion() {return this.descripcion;}
	
	public ArrayList<Categoria> getCategoria() {return this.categorias;}
	
	public int getPrecioVenta() {return this.precioVenta;}
	
	public int getPrecioCompra() {return this.precioCompra;}
	
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
