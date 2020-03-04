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
		ArrayList<Producto> Lis=Main.productos;
		for (int i=0;i<Lis.size();i++) {
			Producto Temp=Lis.get(i);
			if(n.equals(Temp.getNombre())) {
				return i;
				
			}
		}
		return -1;
	}
	public ArrayList<Producto> RealizarBusqueda(int indexCategoria) {
		Categoria Ind=Main.categorias.get(indexCategoria);
		ArrayList<Producto> Lis=Main.productos; ArrayList<Producto> Regreso=new ArrayList<Producto>();
		
		for (int i=0;i<Lis.size();i++) {
			Producto Temp=Lis.get(i);
			ArrayList<Categoria> Temp2=Temp.getCategoria();
			for (int j=0;j<Temp2.size();j++) {
				Categoria Cat=Temp2.get(j);
				if (Ind.getNombre().equals(Cat.getNombre())) {
					Regreso.add(Temp);
				}
			}
		}
		return Regreso;
	}
}
