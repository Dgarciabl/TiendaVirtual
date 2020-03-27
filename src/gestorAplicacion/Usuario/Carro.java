package gestorAplicacion.Usuario;
import gestorAplicacion.Administrador.Inventario;
import gestorAplicacion.Administrador.Detalle;
import java.util.*;

import UImain.Main;

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
		super();
	}
	//Getters
	public double getSubTotal() {
		return this.subTotal;
	}
	public int getNumObjetos() {
		return this.numObjetos;
	}
	//Setters
	public void AddProducto(Detalle d) {
		inventario.add(d);
		subTotal+=(d.getCantidad()*d.getProducto().getPrecioVenta());
		this.numObjetos=inventario.size();
	}
	public void AddProducto(Detalle d, int i) {
		inventario.add(i, d);
		subTotal+=(d.getCantidad()*d.getProducto().getPrecioVenta());
		this.numObjetos=inventario.size();
	}
	public void DelProducto(Detalle d) {
		inventario.remove(d);
		subTotal-=(d.getCantidad()*d.getProducto().getPrecioVenta());
		this.numObjetos=inventario.size();
	}
	public void DelProducto(int i) {
		subTotal-=(inventario.get(i).getCantidad()*inventario.get(i).getProducto().getPrecioVenta());
		inventario.remove(i);
		this.numObjetos=inventario.size();
	}
	public void actualizar() {
		this.numObjetos=inventario.size();
		for(int i=0;i<numObjetos;i++) {
			subTotal+=(inventario.get(i).getCantidad()*inventario.get(i).getProducto().getPrecioVenta());
		}
	}
	//Concrete Methods (EN ESPERA)
	public String GenerarFactura() {
		String str1 = new String();
		Usuario us1 = (Usuario)Main.usuario;
		double suma = 0;
		for(int i=0; i<numObjetos;i++) {

			str1+= i+") "+inventario.get(i).getProducto().toString();
			str1+= "\nPrecio Total: "+inventario.get(i).getProducto().getPrecioVenta()*inventario.get(i).getCantidad();
		}
		str1+="\nTotal: "+suma+"\nNuevo Saldo: "+(us1.getSaldo()-subTotal);
		
		return str1;
	}
	@Override
	public String toString() {
		String s=new String();
		for (int i=0; i<numObjetos;i++) {
			s+=i+ ") "+inventario.get(i).toString()+"\nCantidad: "+inventario.get(i).getCantidad()+"\n";
		}
		s+="Total: "+ this.subTotal;
		return s;
	}
	
}
