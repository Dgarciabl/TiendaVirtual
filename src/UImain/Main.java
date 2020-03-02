package UImain;
import java.util.*;
import gestorAplicacion.Usuario.*;
import gestorAplicacion.Administrador.*;
import UImain.*;
import UImain.Opciones.*;
import UImain.Opciones.*;
import UImain.Opciones.Invitado.*;
import UImain.Opciones.Usuario.*;
import UImain.Opciones.Admin.*;
public class Main {
	public static ArrayList<Persona> Usuarios;
	public static Inventario inventario;
	public static ArrayList<Categoria> categorias;
	public static ArrayList<Producto> productos;
	public static Estadistica estadisticos;
	public static int nivel=2;
	public static Persona usuario;
	public static MenuDeConsola[] menu= new MenuDeConsola[3];
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		cargarMenu();
		while(true) {
			menu[nivel].lanzarMenu();
			int op=in.nextInt();
			menu[nivel].opciones.get(op);
		}
	}
	public void montarDB() {
		//Usuarios;
		//inventario;
		//categorias;
		//productos;
		//estadisticos;
		//menu;
	}
	public static void cargarMenu() {
		menu[2]=new MenuDeConsola();
		menu[1]=new MenuDeConsola();
		menu[0]=new MenuDeConsola();
		menu[2].a�adirOpcion(new OpcionIncioSesion());
		menu[2].a�adirOpcion(new OpcionMostrarProductos());
		menu[2].a�adirOpcion(new OpcionMostrarCategorias());
		menu[2].a�adirOpcion(new OpcionBuscarProducto());
		menu[2].a�adirOpcion(new OpcionBusqueda());
		menu[2].a�adirOpcion(new OpcionRecuperacion());
		menu[2].a�adirOpcion(new OpcionSalir());
	}
}
