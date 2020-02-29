package gestorAplicacion.Usuario;

public class Usuario extends Persona {
	//Attributes
	private double saldo;
	private Carro carroCompra;
	//Constructors
	public Usuario(String nombre, boolean genero, int edad, String usuario, String contrase�a, String pregunta, String respuesta, int saldo) {
		super(nombre, genero, edad, usuario, contrase�a, pregunta, respuesta);
		this.saldo=saldo;
	}
	public Usuario(String nombre, boolean genero, int edad, String usuario, String contrase�a, String pregunta, String respuesta) {
		super(nombre, genero, edad, usuario, contrase�a, pregunta, respuesta);
		this.saldo=0;
	}
	public Usuario(String usuario, String contrase�a, String pregunta, String respuesta, int saldo) {
		super(usuario,contrase�a, pregunta, respuesta);
		this.saldo=saldo;
	}
	public Usuario(String usuario, String contrase�a, String pregunta, String respuesta) {
		super(usuario,contrase�a, pregunta, respuesta);
	}
	//Getters
	public double getSaldo() {
		return this.saldo;
	}
	public Carro getCarro() {
		return this.getCarro();
	}
	public String getCarroToString() {
		return this.carroCompra.toString();
	}
	public double getSubtotal() {
		return this.carroCompra.getSubTotal();
	}
	//Setters
	public void actualizarSaldo(double modificacion) {
		this.saldo+=modificacion;
	}
	public void a�adirProducto(int indexInventario) {
		
	}
	public void eliminarProducto(int indexCarro) {
		
	}
	//Abstract Methods implementation 
	@Override
	public void mostrarInventario() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mostrarCategorias() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void busqueda(String nombreProducto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void busqueda(int indexCategoria) {
		// TODO Auto-generated method stub
		
	}
	//Methods
	public void comprar() {
		
	}	
}
