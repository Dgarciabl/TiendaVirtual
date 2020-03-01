package UImain;
import java.util.*;
import gestorAplicacion.Usuario.*;
import gestorAplicacion.Administrador.*;
import UImain.*;
import UImain.Opciones.*;
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
		while(true) {
			menu[nivel].lanzarMenu();
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
}
