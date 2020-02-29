package gestorAplicacion.Administrador;
import java.util.*;

public class Inventario {
	//Atributos
	private ArrayList<Detalle> Inventario;
	
	//Constructores
	public Inventario(ArrayList<Detalle> a){
		this.Inventario=a;
	}
	
	public Inventario(){
		this.Inventario=null;
	}

	
	//Getters
	public ArrayList<Detalle> GetInventario(){return this.Inventario;}
	
	public Detalle GetInventario(int i) {return Inventario.get(i);}
	
	//Metodos Concretos (EN ESPERA)
	void AddInventario(Detalle d) {
		Inventario.add(d);
	}
	void AddInventario(Detalle d, int i) {
		Inventario.add(i, d);
	}
	void DellInventario(Detalle d) {
		Inventario.remove(d);
	}
	void DellInventario(int i) {
		Inventario.remove(i);
	}
	public Detalle RealizarBusqueda(String n) {
		
	}
	public Detalle RealizarBusqueda(Categoria cat) {
		
	}
}
