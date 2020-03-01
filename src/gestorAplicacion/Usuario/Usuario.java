package gestorAplicacion.Usuario;
import java.util.*;
import gestorAplicacion.Administrador.Detalle;
import gestorAplicacion.Administrador.Categoria;

public class Usuario extends Persona {
	//Attributes
	private double saldo;
	private Carro carroCompra;
	//Constructors
	public Usuario(String nombre, boolean genero, int edad, String usuario, String contraseña, String pregunta, String respuesta, int saldo) {
		super(nombre, genero, edad, usuario, contraseña, pregunta, respuesta);
		this.saldo=saldo;
	}
	public Usuario(String nombre, boolean genero, int edad, String usuario, String contraseña, String pregunta, String respuesta) {
		super(nombre, genero, edad, usuario, contraseña, pregunta, respuesta);
		this.saldo=0;
	}
	public Usuario(String usuario, String contraseña, String pregunta, String respuesta, int saldo) {
		super(usuario,contraseña, pregunta, respuesta);
		this.saldo=saldo;
	}
	public Usuario(String usuario, String contraseña, String pregunta, String respuesta) {
		super(usuario,contraseña, pregunta, respuesta);
		this.saldo=0;
	}
	//Getters
	public double getSaldo() {
		return this.saldo;
	}
	public Carro getCarro() {
		return this.carroCompra;
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
	public void añadirProducto(int indexInventario) {
		
	}
	public void eliminarProducto(int indexCarro) {
		
	}
	//Abstract Methods implementation 
	@Override
	public void mostrarInventario() {
		ArrayList<Detalle> Temp=this.carroCompra.getInventario();
		for (int i=0; i<Temp.size();i++) {
			Detalle Temp2=Temp.get(i);
			System.out.println(i+1 +")"+ Temp2.getProducto().getNombre());
			System.out.println(Temp2.getCantidad());
			System.out.println("-------------------------------------");
		}
		
	}
	@Override
	public void mostrarCategorias() {
		ArrayList<Detalle> Temp=this.carroCompra.getInventario();
		for (int i=0; i<Temp.size();i++) {
			Detalle Temp2=Temp.get(i);
			System.out.println("Nombre de producto:");
			System.out.println(i+1 +")"+ Temp2.getProducto().getNombre());
			ArrayList<Categoria> Temp3=Temp2.getProducto().getCategoria();
			System.out.println("Categorias de: "+ Temp2.getProducto().getNombre());
			for (int j=0;i<Temp3.size();j++) {
				Categoria Temp4=Temp3.get(j);
				System.out.println(j+1 +")"+ Temp4.getNombre());
				System.out.println(Temp4.getDescripcion());
				System.out.println("---------------");
			}
			System.out.println("-------------------------------------");
		}
		
		
		
	}
	@Override
	public void mostrarProductos() {
		ArrayList<Detalle> Temp=this.carroCompra.getInventario();
		for (int i=0; i<Temp.size();i++) {
			Detalle Temp2=Temp.get(i);
			System.out.println(Temp2.getProducto().getNombre());
			System.out.println(Temp2.getProducto().getDescripcion());
			System.out.println(Temp2.getProducto().getPrecioVenta());
			System.out.println(Temp2.getCantidad());
			System.out.println("-------------------------------------");
		}				
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
