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
	
	int setCantidad(int i) {
		if (i>=0) {
			this.cantidad=i;
		}
	int restarCantidad(int amount){
		if((this.cantidad - amount)>0) {
			return (this.getCantidad()-amount);
		}else {
			throw new ProductoAgotado();
		}
	}
	}
class ProductoAgotado extends Exception{
	public ProductoAgotado() {
		super("La cantidad de productos pedida es superior a la existente");
	}
}
	
}
