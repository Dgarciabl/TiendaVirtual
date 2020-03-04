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
		if (i>=0) {
			this.cantidad=i;
		}
	}	
	 public void restarCantidad(int amount){
		if((this.cantidad - amount)>0) {
			this.cantidad = this.getCantidad()-amount;
		}else {
			System.out.println("La cantidad de productos pedida es superior a la existente");
		}
	}
	 @Override
	 public String toString() {
		 String s=new String();
		 s=this.producto.toString()+"\nCantidad: "+this.cantidad;
		 return s;
	 }
	 public String toString2() {
		 String s=new String();
		 s=this.producto.toString2()+"\nCantidad: "+this.cantidad;
		 return s;
	 }

}
