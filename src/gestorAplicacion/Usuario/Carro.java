package gestorAplicacion.Usuario;
import gestorAplicacion.Administrador.Inventario;
import gestorAplicacion.Administrador.Detalle;
import java.util.*;

public class Carro extends Inventario {
	//Atributos
	private double subtotal;
	private int numObjetos;
	
	//Constructores
	Carro(ArrayList<Detalle> d, double s, int i){
		super (d);
		this.subtotal=s;
		this.numObjetos=i;		
	}
	
	//Getters
	public double GetSubtotal() {
		return this.subtotal;
	}
	public int GetNumObjetos() {
		return this.numObjetos;
	}
	
	//Metodos Concretos (EN ESPERA)
	public String GenerarFactura() {
		
	}

}
