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
	public static MenuDeConsola full=new  MenuDeConsola();
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
		menu[1].añadirOpcion(new OpcionVerPerfil());
		menu[1].añadirOpcion(new OpcionMostrarProductos());
		menu[1].añadirOpcion(new OpcionMostrarCategorias());
		menu[1].añadirOpcion(new OpcionBuscarProducto());
		menu[1].añadirOpcion(new OpcionBusqueda());
		menu[1].añadirOpcion(new OpcionCambiarContraseña());
		menu[1].añadirOpcion(new OpcionMostrarCarro());
		menu[1].añadirOpcion(new OpcionEliminarProductoCarro());
		menu[1].añadirOpcion(new OpcionComprar());
		menu[1].añadirOpcion(new OpcionCerrarSesion());
		menu[1].añadirOpcion(new OpcionSalir());
		//menu administrador
		menu[0].añadirOpcion(new OpcionMostrarProductos());
		menu[0].añadirOpcion(new OpcionMostrarCategorias());
		menu[0].añadirOpcion(new OpcionMostrarUsuarios());
		menu[0].añadirOpcion(new OpcionBuscarProducto());
		menu[0].añadirOpcion(new OpcionBusqueda());
		menu[0].añadirOpcion(new OpcionCrearUsuario());
		menu[0].añadirOpcion(new OpcionCrearCategorias());
		menu[0].añadirOpcion(new OpcionCrearProductos());
		menu[0].añadirOpcion(new OpcionCrearExistencias());
		menu[0].añadirOpcion(new OpcionAñadirOpcionInvitado());
		menu[0].añadirOpcion(new OpcionAñadirOpcionUsuario());
		menu[0].añadirOpcion(new OpcionAñadirOpcionAdministrador());
		menu[0].añadirOpcion(new OpcionModificarNombreProducto());
		menu[0].añadirOpcion(new OpcionModificarDescripcionProducto());
		menu[0].añadirOpcion(new OpcionModificarPrecioVentaProducto());
		menu[0].añadirOpcion(new OpcionModificarPrecioCompraProducto());
		menu[0].añadirOpcion(new OpcionModificarCantidadProducto());
		menu[0].añadirOpcion(new OpcionAñadirCategoriasProducto());
		menu[0].añadirOpcion(new OpcionEliminarCategoriasProducto());
		menu[0].añadirOpcion(new OpcionModificarNombreCategoria());
		menu[0].añadirOpcion(new OpcionModificarDescripcionCategoria());
		menu[0].añadirOpcion(new OpcionEliminarOpcionInvitado());
		menu[0].añadirOpcion(new OpcionEliminarOpcionUsuario());
		menu[0].añadirOpcion(new OpcionEliminarOpcionAdministrador());
		menu[0].añadirOpcion(new OpcionEliminarUsuario());
		menu[0].añadirOpcion(new OpcionEliminarProducto());
		menu[0].añadirOpcion(new OpcionEliminarCategoria());
		menu[0].añadirOpcion(new OpcionEliminarDetalle());
		menu[0].añadirOpcion(new OpcionVerPerfil());
		menu[0].añadirOpcion(new OpcionCambiarContraseña());
		menu[0].añadirOpcion(new OpcionCerrarSesion());
		menu[0].añadirOpcion(new OpcionSalir());
		//full
		full.añadirOpcion(new OpcionInicioSesion());
		full.añadirOpcion(new OpcionMostrarProductos());
		full.añadirOpcion(new OpcionMostrarCategorias());
		full.añadirOpcion(new OpcionMostrarUsuarios());
		full.añadirOpcion(new OpcionBuscarProducto());
		full.añadirOpcion(new OpcionBusqueda());
		full.añadirOpcion(new OpcionMostrarCarro());
		full.añadirOpcion(new OpcionEliminarProductoCarro());
		full.añadirOpcion(new OpcionComprar());
		full.añadirOpcion(new OpcionCrearUsuario());
		full.añadirOpcion(new OpcionCrearCategorias());
		full.añadirOpcion(new OpcionCrearProductos());
		full.añadirOpcion(new OpcionCrearExistencias());
		full.añadirOpcion(new OpcionAñadirOpcionInvitado());
		full.añadirOpcion(new OpcionAñadirOpcionUsuario());
		full.añadirOpcion(new OpcionAñadirOpcionAdministrador());
		full.añadirOpcion(new OpcionModificarNombreProducto());
		full.añadirOpcion(new OpcionModificarDescripcionProducto());
		full.añadirOpcion(new OpcionModificarPrecioVentaProducto());
		full.añadirOpcion(new OpcionModificarPrecioCompraProducto());
		full.añadirOpcion(new OpcionModificarCantidadProducto());
		full.añadirOpcion(new OpcionAñadirCategoriasProducto());
		full.añadirOpcion(new OpcionEliminarCategoriasProducto());
		full.añadirOpcion(new OpcionModificarNombreCategoria());
		full.añadirOpcion(new OpcionModificarDescripcionCategoria());
		full.añadirOpcion(new OpcionEliminarOpcionInvitado());
		full.añadirOpcion(new OpcionEliminarOpcionUsuario());
		full.añadirOpcion(new OpcionEliminarOpcionAdministrador());
		full.añadirOpcion(new OpcionEliminarUsuario());
		full.añadirOpcion(new OpcionEliminarProducto());
		full.añadirOpcion(new OpcionEliminarCategoria());
		full.añadirOpcion(new OpcionEliminarDetalle());
		full.añadirOpcion(new OpcionVerPerfil());
		full.añadirOpcion(new OpcionCambiarContraseña());
		full.añadirOpcion(new OpcionRecuperacion());
		full.añadirOpcion(new OpcionCerrarSesion());
		full.añadirOpcion(new OpcionSalir());
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
	public static boolean isNumeric(String s) {
		try {
			Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}