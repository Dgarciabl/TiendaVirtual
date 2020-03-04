package gestorAplicacion.Usuario;
import java.util.*;

import gestorAplicacion.Administrador.Detalle;
import gestorAplicacion.Administrador.Categoria;
import gestorAplicacion.Administrador.Producto;
import UImain.*;

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
	public void actualizarSaldo(double aumento) {
		this.saldo+=aumento;
	}
	public void reducirSaldo(double valorAPagar) {
		this.saldo-=valorAPagar;
	}
	public void añadirProducto(int indexInventario) {
		
	}
	public void eliminarProducto(int indexCarro) {
		
	}
	//Abstract Methods implementation 
	@Override
	public void mostrarCategorias() {
		ArrayList<Categoria> Temp=Main.categorias;
		System.out.println("-------------------------------------");
		System.out.println("Categorias:");
		System.out.println("-------------------------------------");
		for (int i=0; i<Temp.size();i++) {
			System.out.println(i+1 +")"+ Temp.get(i).toString());
			System.out.println("-------------------------------------");
		}
	}
	@Override
	public void mostrarProductos() {
		ArrayList<Producto> Temp=Main.productos;
		System.out.println("-------------------------------------");
		System.out.println("Productos:");
		System.out.println("-------------------------------------");
		System.out.println("Tamanos "+Main.productos.size());
		for (int i=0; i<Temp.size();i++) {
			System.out.println(i +")"+ Temp.get(i).getNombre());

		}				
	}
	@Override
	public int busqueda(String nombreProducto) {
		return this.carroCompra.RealizarBusqueda(nombreProducto);
	}
	@Override
	public ArrayList<Producto> busqueda(int indexCategoria) {
		return this.carroCompra.RealizarBusqueda(indexCategoria);
	}
	//Methods
	public void comprar() {
		
	}	
	
	@Override
	public String toString() {
		String s = new String();
		s="Perfil: Usuario Registrado/nNombre: "+this.getNombre()+"/nEdad"+this.getEdad()+"/nGenero: ";
		if (getGenero()) {
			s=s+"Masculino/n";
		}
		s=s+"Usuario: "+this.getUsuario()+"/nSaldo: "+this.saldo;
		return s;
	}
}
