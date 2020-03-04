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
	public static void cargarUsuarios() {
		Usuarios=new ArrayList<Persona>();
		Administrador admon=new Administrador("superadmin", true, 0, "root", "root", "root", "root");
		Usuarios.add(admon);
		Administrador admon2 = new Administrador("Jorge", true, 54, "jordi", "soloyolase", "NombrePadre", "Javier");
		Usuarios.add(admon2);
		Administrador admon3 = new Administrador("Daniela", false, 28, "dani", "dan","NombreMadre", "Johana");
		Usuarios.add(admon3);
		Usuario user=new Usuario("superuser", true, 0, "user", "user", "user", "user");
		Usuarios.add(user);
		Usuario user2 = new Usuario("Pablo",true,19, "holasoypablo","bat123", "NombrePadre", "Jorge");
	}
	public static void cargarProductos() {
		productos=new ArrayList<Producto>();
		productos.add(new Producto("Leche","1 Litro",3500,3000));
		productos.add(new Producto("Yogur","1.2 Litros",3500,3000));
		productos.add(new Producto("Almendras","Enteras con cascara 250gr",8900,3000));
		productos.add(new Producto("Frijoles","500gr",3500,3000));
		productos.add(new Producto("Lentejas","400ml",3500,3000));
		productos.add(new Producto("Portatil","Ryzen 7",2000000,1450000));
		productos.add(new Producto("Celular","Mejor calida Precio del mercado",1200000,650000));
		productos.add(new Producto("Horno","Haceb",350000,260000));
		productos.add(new Producto("Licuadora","Oster",75000,35000));
	}
	public static void cargarInventario() {
		inventario=new Inventario();
		for (int i=0;i<productos.size();i++) {
			inventario.AddInventario(new Detalle(productos.get(i),(int)(Math.random() * (100 - 0 + 1) + 0)));
		}
	}
	public static void cargarCategorias() {
		categorias=new ArrayList<Categoria>();
		categorias.add(new Categoria("Lacteos","Derivados de la leche"));
		categorias.add(new Categoria("Granos","Semillas entera"));
		categorias.add(new Categoria("Enlatados","Empacados para durar"));
		categorias.add(new Categoria("Tecnologia","El avance es el futuro"));
		categorias.add(new Categoria("Electrodomesticos","Todo para el hogar"));
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
	public static void cargarMenu() {
		menu[2]=new MenuDeConsola();
		menu[1]=new MenuDeConsola();
		menu[0]=new MenuDeConsola();
		//menu invitado
		menu[2].añadirOpcion(new OpcionInicioSesion());
		menu[2].añadirOpcion(new OpcionMostrarProductos());
		menu[2].añadirOpcion(new OpcionMostrarInventario());
		menu[2].añadirOpcion(new OpcionMostrarCategorias());
		menu[2].añadirOpcion(new OpcionBuscarProducto());
		menu[2].añadirOpcion(new OpcionBusqueda());
		menu[2].añadirOpcion(new OpcionRecuperacion());
		menu[2].añadirOpcion(new OpcionSalir());
		//menu usuario
		menu[1].añadirOpcion(new OpcionVerPerfil());
		menu[1].añadirOpcion(new OpcionMostrarProductos());
		menu[1].añadirOpcion(new OpcionMostrarInventario());
		menu[1].añadirOpcion(new OpcionMostrarCategorias());
		menu[1].añadirOpcion(new OpcionBuscarProducto());
		menu[1].añadirOpcion(new OpcionBusqueda());
		menu[1].añadirOpcion(new OpcionCambiarContraseña());
		menu[1].añadirOpcion(new OpcionMostrarCarro());
		menu[1].añadirOpcion(new OpcionAñadirCarro());
		menu[1].añadirOpcion(new OpcionEliminarProductoCarro());
		menu[1].añadirOpcion(new OpcionComprar());
		menu[1].añadirOpcion(new OpcionAñadirSaldo());
		menu[1].añadirOpcion(new OpcionCerrarSesion());
		menu[1].añadirOpcion(new OpcionSalir());
		//menu administrador
		menu[0].añadirOpcion(new OpcionMostrarProductos());
		menu[0].añadirOpcion(new OpcionMostrarInventario());
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
		//  mostrar
		full.añadirOpcion(new OpcionMostrarProductos());
		full.añadirOpcion(new OpcionMostrarInventario());
		full.añadirOpcion(new OpcionMostrarCategorias());
		full.añadirOpcion(new OpcionMostrarUsuarios());
		full.añadirOpcion(new OpcionMostrarCarro());
		//  buscar
		full.añadirOpcion(new OpcionBuscarProducto());
		full.añadirOpcion(new OpcionBusqueda());
		//  Crear
		full.añadirOpcion(new OpcionCrearUsuario());
		full.añadirOpcion(new OpcionCrearCategorias());
		full.añadirOpcion(new OpcionCrearProductos());
		full.añadirOpcion(new OpcionCrearExistencias());
		//  modificar
			//  Producto
		full.añadirOpcion(new OpcionModificarNombreProducto());
		full.añadirOpcion(new OpcionModificarDescripcionProducto());
		full.añadirOpcion(new OpcionModificarPrecioVentaProducto());
		full.añadirOpcion(new OpcionModificarPrecioCompraProducto());
		full.añadirOpcion(new OpcionAñadirCategoriasProducto());
		full.añadirOpcion(new OpcionEliminarCategoriasProducto());
			// carro
		full.añadirOpcion(new OpcionAñadirCarro());
		full.añadirOpcion(new OpcionEliminarProductoCarro());
			//Categoria
		full.añadirOpcion(new OpcionModificarNombreCategoria());
		full.añadirOpcion(new OpcionModificarDescripcionCategoria());
			//Inventario
		full.añadirOpcion(new OpcionModificarCantidadProducto());
			//Opciones
		full.añadirOpcion(new OpcionAñadirOpcionInvitado());
		full.añadirOpcion(new OpcionAñadirOpcionUsuario());
		full.añadirOpcion(new OpcionAñadirOpcionAdministrador());
		//Eliminar
		full.añadirOpcion(new OpcionEliminarOpcionInvitado());
		full.añadirOpcion(new OpcionEliminarOpcionUsuario());
		full.añadirOpcion(new OpcionEliminarOpcionAdministrador());
		full.añadirOpcion(new OpcionEliminarUsuario());
		full.añadirOpcion(new OpcionEliminarProducto());
		full.añadirOpcion(new OpcionEliminarCategoria());
		full.añadirOpcion(new OpcionEliminarDetalle());
		//otros
		full.añadirOpcion(new OpcionComprar());
		full.añadirOpcion(new OpcionInicioSesion());
		full.añadirOpcion(new OpcionVerPerfil());
		full.añadirOpcion(new OpcionCambiarContraseña());
		full.añadirOpcion(new OpcionRecuperacion());
		full.añadirOpcion(new OpcionCerrarSesion());
		full.añadirOpcion(new OpcionSalir());
	}
}