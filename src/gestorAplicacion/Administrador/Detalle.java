package gestorAplicacion.Administrador;

public class Detalle {
	
	//Atributos
	private Producto producto;
	private int cantidad;
	
	//Constructores
	public Detalle(Producto p, int c){
		this.producto=p;
		this.cantidad=c;
	}
	
	//Getters
	public Producto getProducto() {
		return this.producto;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	//Setters
	void setProducto(Producto k) {
		this.producto=k;
	}
	
	void setCantidad(int i) {
		this.cantidad=i;
	}

}
