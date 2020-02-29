package gestorAplicacion.Administrador;
import java.util.*;

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
	void AddInventario(Detalle d) {
		inventario.add(d);
	}
	void AddInventario(Detalle d, int i) {
		inventario.add(i, d);
	}
	void DellInventario(Detalle d) {
		inventario.remove(d);
	}
	void DellInventario(int i) {
		inventario.remove(i);
	}
	public Detalle RealizarBusqueda(String n) {
		
	}
	public Detalle RealizarBusqueda(Categoria cat) {
		
	}
}
