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
		categorias=new ArrayList<Categoria>();
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
	
	void setPrecioVenta(double i) {this.precioVenta=i;}
	
	void setPrecioCompra(double i) {this.precioCompra=i;}
	
	
	//Metodos Concretos (EN ESPERA)
	void AgregarCategoria(Categoria cat) {
		
	}
	
	void EliminarCategoria() {
		
	}
	public void Calificar(float C) {
		
	}
	@Override
	public String toString() {
		String s= new String();
		s=s+"Nombre: "+this.nombre+"\n"+"Descripcion:\n"+
		this.descripcion+"\n"+"Precio: "+this.precioVenta+"\n";
		if(!this.categorias.isEmpty()) {
			s=s+"Categorias:\n";
			for (int i =0;i<categorias.size();i++) {
				s=s+"   "+categorias.get(i).getNombre()+"\n";
			}
		}
		return s;
	}
	public String toString2() {
		String s= new String();
		s=s+"Nombre: "+this.nombre+"\n"+"Descripcion:\n"+
		this.descripcion+"\n"+"Precio de Compra: "+this.precioCompra+"Precio de Venta: "+this.precioVenta+"\n";
		if(!this.categorias.isEmpty()) {
			s=s+"Categorias:\n";
			for (int i =0;i<categorias.size();i++) {
				s=s+"   "+categorias.get(i).getNombre()+"\n";
			}
		}
		return s;
	}
	

}
