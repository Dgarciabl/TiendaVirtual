package Administrador;

public class Detalle {
	private Producto producto;
	private int cantidad;
	
	public Producto GetProducto() {
		return this.producto;
	}
	
	void SetProducto(Producto k) {
		this.producto=k;
	}
	
	public int GetCantidad() {
		return this.cantidad;
	}
	
	void SetCantidad(int i) {
		this.cantidad=i;
	}

}
