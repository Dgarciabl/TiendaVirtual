package UImain;
import java.util.*;
import gestorAplicacion.Usuario.*;
import gestorAplicacion.Administrador.*;
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
		Inicio();
		while(true) {
			menu[nivel].lanzarMenu();
			int op=in.nextInt();
			menu[nivel].opciones.get(op).ejecutar();
			if(op==1001001) {
				break;
			}
		}
		in.close();
	}
	public static void Inicio() {
		cargarMenu();
		cargarUsuarios();
		cargarProductos();
		cargarCategorias();
		cargarInventario();
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
		//menu invitado
		menu[2].añadirOpcion(new OpcionInicioSesion());
		menu[2].añadirOpcion(new OpcionMostrarProductos());
		menu[2].añadirOpcion(new OpcionMostrarCategorias());
		menu[2].añadirOpcion(new OpcionBuscarProducto());
		menu[2].añadirOpcion(new OpcionBusqueda());
		menu[2].añadirOpcion(new OpcionRecuperacion());
		menu[2].añadirOpcion(new OpcionSalir());
		//menu usuario
		menu[1].añadirOpcion(new OpcionMostrarProductos());
		menu[1].añadirOpcion(new OpcionMostrarCategorias());
		menu[1].añadirOpcion(new OpcionBuscarProducto());
		menu[1].añadirOpcion(new OpcionBusqueda());
		menu[1].añadirOpcion(new OpcionCerrarSesion());
		menu[1].añadirOpcion(new OpcionSalir());
		//menu administrador
		menu[0].añadirOpcion(new OpcionMostrarProductos());
		menu[0].añadirOpcion(new OpcionMostrarCategorias());
		menu[0].añadirOpcion(new OpcionBuscarProducto());
		menu[0].añadirOpcion(new OpcionBusqueda());
		menu[0].añadirOpcion(new OpcionCrearCategorias());
		menu[0].añadirOpcion(new OpcionCrearProductos());
		menu[0].añadirOpcion(new OpcionCrearExistencias());
		menu[0].añadirOpcion(new OpcionModificarNombreProducto());
		menu[0].añadirOpcion(new OpcionModificarDescripcionProducto());
		menu[0].añadirOpcion(new OpcionModificarPrecioVentaProducto());
		menu[0].añadirOpcion(new OpcionModificarPrecioCompraProducto());
		menu[0].añadirOpcion(new OpcionModificarCantidadProducto());
		menu[0].añadirOpcion(new OpcionModificarCategorias());
		menu[0].añadirOpcion(new OpcionModificarNombreCategoria());
		menu[0].añadirOpcion(new OpcionModificarDescripcionCategoria());
		menu[0].añadirOpcion(new OpcionEliminarProducto());
		menu[0].añadirOpcion(new OpcionEliminarCategoria());
		menu[0].añadirOpcion(new OpcionEliminarDetalle());
		menu[0].añadirOpcion(new OpcionCerrarSesion());
		menu[0].añadirOpcion(new OpcionSalir());
	}
	public static void cargarUsuarios() {
		Usuarios=new ArrayList<Persona>();
		Administrador admon=new Administrador("superadmin", true, 0, "root", "root", "root", "root");
		Usuarios.add(admon);
	}
	public static void cargarProductos() {
		productos=new ArrayList<Producto>();
	}
	public static void cargarInventario() {
		inventario=new Inventario();
	}
	public static void cargarCategorias() {
		categorias=new ArrayList<Categoria>();
	}
}