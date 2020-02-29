package gestorAplicacion.Usuario;
import gestorAplicacion.Administrador.Inventario;
import gestorAplicacion.Administrador.Detalle;
import java.util.*;

public class Carro extends Inventario {
	//Attributes
	private double subTotal;
	private int numObjetos;
	//Constructors
	Carro(ArrayList<Detalle> d, double s, int i){
		super (d);
		this.subTotal=s;
		this.numObjetos=i;		
	}
	public Carro(){
		
	}
	//Getters
	public double getSubTotal() {
		return this.subTotal;
	}
	public int getNumObjetos() {
		return this.numObjetos;
	}
	//Setters
	void AddProducto(Detalle d) {
		inventario.add(d);
		subTotal+=(d.getCantidad()*d.getProducto().getPrecioVenta());
	}
	void AddProducto(Detalle d, int i) {
		inventario.add(i, d);
		subTotal+=(d.getCantidad()*d.getProducto().getPrecioVenta());
	}
	void DelProducto(Detalle d) {
		inventario.remove(d);
		subTotal-=(d.getCantidad()*d.getProducto().getPrecioVenta());
	}
	void DelProducto(int i) {
		subTotal-=(inventario.get(i).getCantidad()*inventario.get(i).getProducto().getPrecioVenta());
		inventario.remove(i);
	}
	public void actualizar() {
		this.numObjetos=inventario.size();
		for(int i=0;i<numObjetos;i++) {
			subTotal+=(inventario.get(i).getCantidad()*inventario.get(i).getProducto().getPrecioVenta());
		}
	}
	//Concrete Methods (EN ESPERA)
	public String GenerarFactura() {
		
	}
	@Override
	public String toString() {
		String s=new String();
		for (int i=0; i<numObjetos;i++) {
			s+=("#"+i+ " . "+inventario.get(i).getProducto().getNombre()+" , Cantidad: "+inventario.get(i).getCantidad()+" , Precio: "+inventario.get(i).getProducto().getPrecioVenta()+" /n");
		}
		s+=("Total: "+ this.subTotal);
		return s;
	}
	
}
