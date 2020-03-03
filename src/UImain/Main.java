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
		menu[2].a�adirOpcion(new OpcionInicioSesion());
		menu[2].a�adirOpcion(new OpcionMostrarProductos());
		menu[2].a�adirOpcion(new OpcionMostrarCategorias());
		menu[2].a�adirOpcion(new OpcionBuscarProducto());
		menu[2].a�adirOpcion(new OpcionBusqueda());
		menu[2].a�adirOpcion(new OpcionRecuperacion());
		menu[2].a�adirOpcion(new OpcionSalir());
		//menu usuario
		menu[1].a�adirOpcion(new OpcionMostrarProductos());
		menu[1].a�adirOpcion(new OpcionMostrarCategorias());
		menu[1].a�adirOpcion(new OpcionBuscarProducto());
		menu[1].a�adirOpcion(new OpcionBusqueda());
		menu[1].a�adirOpcion(new OpcionCerrarSesion());
		menu[1].a�adirOpcion(new OpcionSalir());
		//menu administrador
		menu[0].a�adirOpcion(new OpcionMostrarProductos());
		menu[0].a�adirOpcion(new OpcionMostrarCategorias());
		menu[0].a�adirOpcion(new OpcionBuscarProducto());
		menu[0].a�adirOpcion(new OpcionBusqueda());
		menu[0].a�adirOpcion(new OpcionCrearCategorias());
		menu[0].a�adirOpcion(new OpcionCrearProductos());
		menu[0].a�adirOpcion(new OpcionCrearExistencias());
		menu[0].a�adirOpcion(new OpcionModificarNombreProducto());
		menu[0].a�adirOpcion(new OpcionModificarDescripcionProducto());
		menu[0].a�adirOpcion(new OpcionModificarPrecioVentaProducto());
		menu[0].a�adirOpcion(new OpcionModificarPrecioCompraProducto());
		menu[0].a�adirOpcion(new OpcionModificarCantidadProducto());
		menu[0].a�adirOpcion(new OpcionModificarCategorias());
		menu[0].a�adirOpcion(new OpcionModificarNombreCategoria());
		menu[0].a�adirOpcion(new OpcionModificarDescripcionCategoria());
		menu[0].a�adirOpcion(new OpcionEliminarProducto());
		menu[0].a�adirOpcion(new OpcionEliminarCategoria());
		menu[0].a�adirOpcion(new OpcionEliminarDetalle());
		menu[0].a�adirOpcion(new OpcionCerrarSesion());
		menu[0].a�adirOpcion(new OpcionSalir());
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