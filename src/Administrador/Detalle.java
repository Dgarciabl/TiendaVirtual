package Administrador;

public class Detalle {
	
	//Atributos
	private Producto producto;
	private int cantidad;
	
	//Constructores
	Detalle(Producto p, int c){
		this.producto=p;
		this.cantidad=c;
	}
	
	//Getters
	public Producto GetProducto() {
		return this.producto;
	}
	
	public int GetCantidad() {
		return this.cantidad;
	}
	
	//Setters
	void SetProducto(Producto k) {
		this.producto=k;
	}
	
	void SetCantidad(int i) {
		this.cantidad=i;
	}

}
