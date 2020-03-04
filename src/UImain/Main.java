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
		menu[2].a�adirOpcion(new OpcionInicioSesion());
		menu[2].a�adirOpcion(new OpcionMostrarProductos());
		menu[2].a�adirOpcion(new OpcionMostrarInventario());
		menu[2].a�adirOpcion(new OpcionMostrarCategorias());
		menu[2].a�adirOpcion(new OpcionBuscarProducto());
		menu[2].a�adirOpcion(new OpcionBusqueda());
		menu[2].a�adirOpcion(new OpcionRecuperacion());
		menu[2].a�adirOpcion(new OpcionSalir());
		//menu usuario
		menu[1].a�adirOpcion(new OpcionVerPerfil());
		menu[1].a�adirOpcion(new OpcionMostrarProductos());
		menu[1].a�adirOpcion(new OpcionMostrarInventario());
		menu[1].a�adirOpcion(new OpcionMostrarCategorias());
		menu[1].a�adirOpcion(new OpcionBuscarProducto());
		menu[1].a�adirOpcion(new OpcionBusqueda());
		menu[1].a�adirOpcion(new OpcionCambiarContrase�a());
		menu[1].a�adirOpcion(new OpcionMostrarCarro());
		menu[1].a�adirOpcion(new OpcionA�adirCarro());
		menu[1].a�adirOpcion(new OpcionEliminarProductoCarro());
		menu[1].a�adirOpcion(new OpcionComprar());
		menu[1].a�adirOpcion(new OpcionA�adirSaldo());
		menu[1].a�adirOpcion(new OpcionCerrarSesion());
		menu[1].a�adirOpcion(new OpcionSalir());
		//menu administrador
		menu[0].a�adirOpcion(new OpcionMostrarProductos());
		menu[0].a�adirOpcion(new OpcionMostrarInventario());
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
		//  mostrar
		full.a�adirOpcion(new OpcionMostrarProductos());
		full.a�adirOpcion(new OpcionMostrarInventario());
		full.a�adirOpcion(new OpcionMostrarCategorias());
		full.a�adirOpcion(new OpcionMostrarUsuarios());
		full.a�adirOpcion(new OpcionMostrarCarro());
		//  buscar
		full.a�adirOpcion(new OpcionBuscarProducto());
		full.a�adirOpcion(new OpcionBusqueda());
		//  Crear
		full.a�adirOpcion(new OpcionCrearUsuario());
		full.a�adirOpcion(new OpcionCrearCategorias());
		full.a�adirOpcion(new OpcionCrearProductos());
		full.a�adirOpcion(new OpcionCrearExistencias());
		//  modificar
			//  Producto
		full.a�adirOpcion(new OpcionModificarNombreProducto());
		full.a�adirOpcion(new OpcionModificarDescripcionProducto());
		full.a�adirOpcion(new OpcionModificarPrecioVentaProducto());
		full.a�adirOpcion(new OpcionModificarPrecioCompraProducto());
		full.a�adirOpcion(new OpcionA�adirCategoriasProducto());
		full.a�adirOpcion(new OpcionEliminarCategoriasProducto());
			// carro
		full.a�adirOpcion(new OpcionA�adirCarro());
		full.a�adirOpcion(new OpcionEliminarProductoCarro());
			//Categoria
		full.a�adirOpcion(new OpcionModificarNombreCategoria());
		full.a�adirOpcion(new OpcionModificarDescripcionCategoria());
			//Inventario
		full.a�adirOpcion(new OpcionModificarCantidadProducto());
			//Opciones
		full.a�adirOpcion(new OpcionA�adirOpcionInvitado());
		full.a�adirOpcion(new OpcionA�adirOpcionUsuario());
		full.a�adirOpcion(new OpcionA�adirOpcionAdministrador());
		//Eliminar
		full.a�adirOpcion(new OpcionEliminarOpcionInvitado());
		full.a�adirOpcion(new OpcionEliminarOpcionUsuario());
		full.a�adirOpcion(new OpcionEliminarOpcionAdministrador());
		full.a�adirOpcion(new OpcionEliminarUsuario());
		full.a�adirOpcion(new OpcionEliminarProducto());
		full.a�adirOpcion(new OpcionEliminarCategoria());
		full.a�adirOpcion(new OpcionEliminarDetalle());
		//otros
		full.a�adirOpcion(new OpcionComprar());
		full.a�adirOpcion(new OpcionInicioSesion());
		full.a�adirOpcion(new OpcionVerPerfil());
		full.a�adirOpcion(new OpcionCambiarContrase�a());
		full.a�adirOpcion(new OpcionRecuperacion());
		full.a�adirOpcion(new OpcionCerrarSesion());
		full.a�adirOpcion(new OpcionSalir());
	}
}