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
		this.carroCompra=new Carro();
	}
	public Usuario(String nombre, boolean genero, int edad, String usuario, String contraseña, String pregunta, String respuesta) {
		super(nombre, genero, edad, usuario, contraseña, pregunta, respuesta);
		this.saldo=0;
		this.carroCompra=new Carro();
	}
	//Getters
	public double getSaldo() {
		return this.saldo;
	}
	public Carro getCarro() {
		return this.carroCompra;
	}
	public String getCarroToString() {
		String s=new String();
		s="-------------------------------------\nProductos en el Carro:\n-------------------------------------\n";
		s=s+(this.carroCompra.toString());
		return s;
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
	public String mostrarProductos() {
		String s=new String();
		s="-------------------------------------\nProductos:\n-------------------------------------\n";
		s=s+Main.inventario.toString();
		return s;
	}
	@Override
	public String toString() {
		String s = new String();
		s="Perfil: Usuario Registrado\nNombre: "+this.getNombre()+"\nEdad: "+this.getEdad()+"\nGenero: ";
		if (getGenero()) {
			s=s+"Masculino\n";
		}else {
			s=s+"Femenino\n";
		}
		s=s+"Usuario: "+this.getUsuario()+"\nSaldo: "+this.saldo;
		return s;
	}
}
