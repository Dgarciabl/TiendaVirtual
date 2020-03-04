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
		menu[2].a�adirOpcion(new OpcionInicioSesion());
		menu[2].a�adirOpcion(new OpcionMostrarProductos());
		menu[2].a�adirOpcion(new OpcionMostrarCategorias());
		menu[2].a�adirOpcion(new OpcionBuscarProducto());
		menu[2].a�adirOpcion(new OpcionBusqueda());
		menu[2].a�adirOpcion(new OpcionRecuperacion());
		menu[2].a�adirOpcion(new OpcionSalir());
		//menu usuario
		menu[1].a�adirOpcion(new OpcionVerPerfil());
		menu[1].a�adirOpcion(new OpcionMostrarProductos());
		menu[1].a�adirOpcion(new OpcionMostrarCategorias());
		menu[1].a�adirOpcion(new OpcionBuscarProducto());
		menu[1].a�adirOpcion(new OpcionBusqueda());
		menu[1].a�adirOpcion(new OpcionCambiarContrase�a());
		menu[1].a�adirOpcion(new OpcionMostrarCarro());
		menu[1].a�adirOpcion(new OpcionEliminarProductoCarro());
		menu[1].a�adirOpcion(new OpcionComprar());
		menu[1].a�adirOpcion(new OpcionCerrarSesion());
		menu[1].a�adirOpcion(new OpcionSalir());
		//menu administrador
		menu[0].a�adirOpcion(new OpcionMostrarProductos());
		menu[0].a�adirOpcion(new OpcionMostrarCategorias());
		menu[0].a�adirOpcion(new OpcionMostrarUsuarios());
		menu[0].a�adirOpcion(new OpcionBuscarProducto());
		menu[0].a�adirOpcion(new OpcionBusqueda());
		menu[0].a�adirOpcion(new OpcionCrearUsuario());
		menu[0].a�adirOpcion(new OpcionCrearCategorias());
		menu[0].a�adirOpcion(new OpcionCrearProductos());
		menu[0].a�adirOpcion(new OpcionCrearExistencias());
		menu[0].a�adirOpcion(new OpcionA�adirOpcionInvitado());
		menu[0].a�adirOpcion(new OpcionA�adirOpcionUsuario());
		menu[0].a�adirOpcion(new OpcionA�adirOpcionAdministrador());
		menu[0].a�adirOpcion(new OpcionModificarNombreProducto());
		menu[0].a�adirOpcion(new OpcionModificarDescripcionProducto());
		menu[0].a�adirOpcion(new OpcionModificarPrecioVentaProducto());
		menu[0].a�adirOpcion(new OpcionModificarPrecioCompraProducto());
		menu[0].a�adirOpcion(new OpcionModificarCantidadProducto());
		menu[0].a�adirOpcion(new OpcionA�adirCategoriasProducto());
		menu[0].a�adirOpcion(new OpcionEliminarCategoriasProducto());
		menu[0].a�adirOpcion(new OpcionModificarNombreCategoria());
		menu[0].a�adirOpcion(new OpcionModificarDescripcionCategoria());
		menu[0].a�adirOpcion(new OpcionEliminarOpcionInvitado());
		menu[0].a�adirOpcion(new OpcionEliminarOpcionUsuario());
		menu[0].a�adirOpcion(new OpcionEliminarOpcionAdministrador());
		menu[0].a�adirOpcion(new OpcionEliminarUsuario());
		menu[0].a�adirOpcion(new OpcionEliminarProducto());
		menu[0].a�adirOpcion(new OpcionEliminarCategoria());
		menu[0].a�adirOpcion(new OpcionEliminarDetalle());
		menu[0].a�adirOpcion(new OpcionVerPerfil());
		menu[0].a�adirOpcion(new OpcionCambiarContrase�a());
		menu[0].a�adirOpcion(new OpcionCerrarSesion());
		menu[0].a�adirOpcion(new OpcionSalir());
		//full
		full.a�adirOpcion(new OpcionInicioSesion());
		full.a�adirOpcion(new OpcionMostrarProductos());
		full.a�adirOpcion(new OpcionMostrarCategorias());
		full.a�adirOpcion(new OpcionMostrarUsuarios());
		full.a�adirOpcion(new OpcionBuscarProducto());
		full.a�adirOpcion(new OpcionBusqueda());
		full.a�adirOpcion(new OpcionMostrarCarro());
		full.a�adirOpcion(new OpcionEliminarProductoCarro());
		full.a�adirOpcion(new OpcionComprar());
		full.a�adirOpcion(new OpcionCrearUsuario());
		full.a�adirOpcion(new OpcionCrearCategorias());
		full.a�adirOpcion(new OpcionCrearProductos());
		full.a�adirOpcion(new OpcionCrearExistencias());
		full.a�adirOpcion(new OpcionA�adirOpcionInvitado());
		full.a�adirOpcion(new OpcionA�adirOpcionUsuario());
		full.a�adirOpcion(new OpcionA�adirOpcionAdministrador());
		full.a�adirOpcion(new OpcionModificarNombreProducto());
		full.a�adirOpcion(new OpcionModificarDescripcionProducto());
		full.a�adirOpcion(new OpcionModificarPrecioVentaProducto());
		full.a�adirOpcion(new OpcionModificarPrecioCompraProducto());
		full.a�adirOpcion(new OpcionModificarCantidadProducto());
		full.a�adirOpcion(new OpcionA�adirCategoriasProducto());
		full.a�adirOpcion(new OpcionEliminarCategoriasProducto());
		full.a�adirOpcion(new OpcionModificarNombreCategoria());
		full.a�adirOpcion(new OpcionModificarDescripcionCategoria());
		full.a�adirOpcion(new OpcionEliminarOpcionInvitado());
		full.a�adirOpcion(new OpcionEliminarOpcionUsuario());
		full.a�adirOpcion(new OpcionEliminarOpcionAdministrador());
		full.a�adirOpcion(new OpcionEliminarUsuario());
		full.a�adirOpcion(new OpcionEliminarProducto());
		full.a�adirOpcion(new OpcionEliminarCategoria());
		full.a�adirOpcion(new OpcionEliminarDetalle());
		full.a�adirOpcion(new OpcionVerPerfil());
		full.a�adirOpcion(new OpcionCambiarContrase�a());
		full.a�adirOpcion(new OpcionRecuperacion());
		full.a�adirOpcion(new OpcionCerrarSesion());
		full.a�adirOpcion(new OpcionSalir());
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