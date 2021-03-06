package gestorAplicacion.Administrador;
import java.util.*;

import UImain.Main;

public class Inventario {
	//Attributes
	protected ArrayList<Detalle> inventario;
	
	//Constructors
	public Inventario(ArrayList<Detalle> a){
		this.inventario=a;
	}
	public Inventario(){
		this.inventario=new ArrayList<Detalle>();
	}
	//Getters
	public ArrayList<Detalle> getInventario(){return this.inventario;}
	
	public Detalle getInventario(int i) {return inventario.get(i);}
	
	//Metodos Concretos (EN ESPERA)
	public void AddInventario(Detalle d) {
		inventario.add(d);
	}
	public void AddInventario(Detalle d, int i) {
		inventario.add(i, d);
	}
	public void DelInventario(Detalle d) {
		inventario.remove(d);
	}
	public void DelInventario(int i) {
		inventario.remove(i);
	}
	public int RealizarBusqueda(String n) {
		ArrayList<Detalle> Lis=inventario;
		for (int i=0;i<Lis.size();i++) {
			Detalle Temp=Lis.get(i);
			if(n.equals(Temp.getProducto().getNombre())) {
				return i;
				
			}
		}
		return -1;
	}
	public ArrayList<Producto> RealizarBusqueda(int indexCategoria) {
		Categoria Ind=Main.categorias.get(indexCategoria);
		ArrayList<Detalle> Lis=inventario; ArrayList<Producto> Regreso=new ArrayList<Producto>();
		for (int i=0;i<Lis.size();i++) {
			Detalle temp=Lis.get(i);
			if(temp.getProducto().getCategoria().equalsto(Ind)) {
				Regreso.add(temp.getProducto());
			}
		}
		return Regreso;
	}
	@Override
	public String toString() {
		 String s=new String();
		 for (int i=0;i<inventario.size();i++) {
			s=s+i+") "+ inventario.get(i).toString()+"\n-------------------------------------\n";
		 }
		 return s;
	 }
	public String toString2() {
		 String s=new String();
		 for (int i=0;i<inventario.size();i++) {
			s=s+i+") "+ inventario.get(i).toString2()+"\n-------------------------------------\n";
		 }
		 return s;
	 }
}
