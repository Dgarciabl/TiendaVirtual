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
	public void actualizarSaldo(double modificacion) {
		this.saldo+=modificacion;
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
			System.out.println(i+1 +")"+ Temp.get(i).getNombre());
			System.out.println("Descripcion:");
			System.out.println(Temp.get(i).getDescripcion());
			System.out.println("-------------------------------------");
		}
		
		
		
	}
	@Override
	public void mostrarProductos() {
		ArrayList<Detalle> Temp=Main.inventario.getInventario();
		System.out.println("-------------------------------------");
		System.out.println("Productos:");
		System.out.println("-------------------------------------");
		for (int i=0; i<Temp.size();i++) {
			System.out.println(i+1 +")"+ Temp.get(i).getProducto().getNombre());
			System.out.println("Descripcion:");
			System.out.println(Temp.get(i).getProducto().getDescripcion());
			System.out.println("Precio: "+Temp.get(i).getProducto().getPrecioVenta());
			System.out.println("Unidades Disponibles: "+Temp.get(i).getCantidad());
			System.out.println("Categorias:");
			for (int j=0;j<Temp.get(i).getProducto().getCategoria().size();j++) {
				System.out.println("     "+Temp.get(i).getProducto().getCategoria().get(j));
			}
			System.out.println("-------------------------------------");
		}				
	}
	@Override
	public int busqueda(String nombreProducto) {
		ArrayList<Producto> Lis=Main.productos;
		for (int i=0;i<Lis.size();i++) {
			Producto Temp=Lis.get(i);
			if(nombreProducto.equals(Temp)) {
				return i;
				
			}
		}
		return -1;
	}
	@Override
	public ArrayList<Producto> busqueda(int indexCategoria) {
		Categoria Ind=Main.categorias.get(indexCategoria);
		ArrayList<Producto> Lis=Main.productos; ArrayList<Producto> Regreso=new ArrayList<Producto>();
		
		for (int i=0;i<Lis.size();i++) {
			Producto Temp=Lis.get(i);
			ArrayList<Categoria> Temp2=Temp.getCategoria();
			for (int j=0;j<Temp2.size();j++) {
				Categoria Cat=Temp2.get(j);
				if (Ind.equals(Cat)) {
					Regreso.add(Temp);
				}
			}
		}
		return Regreso;
	}
	//Methods
	public void comprar() {
		
	}	
}
