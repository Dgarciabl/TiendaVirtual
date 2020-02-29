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
	Producto(String n,String d, ArrayList<Categoria> cat, int pv, int pc, float cp){
		this.nombre=n; this.descripcion=d; this.categorias=cat;
		this.precioVenta=pv; this.precioCompra=pc; this.calificacionProducto=cp;
	}
	
	//Getters
	public String GetNombre() {return this.nombre;}
	
	public String GetDescripcion() {return this.descripcion;}
	
	public ArrayList<Categoria> GetCategoria() {return this.categorias;}
	
	public int GetPrecioVenta() {return this.precioVenta;}
	
	public int GetPrecioCompra() {return this.precioCompra;}
	
	

}
