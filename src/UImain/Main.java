package UImain;
import java.io.*;
import java.nio.file.Files;

import com.google.gson.*;
import java.util.*;
import gestorAplicacion.Usuario.*;
import gestorAplicacion.Administrador.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {
	public static ArrayList<Persona> Usuarios;
	public static Inventario inventario;
	public static ArrayList<Categoria> categorias;
	public static ArrayList<Producto> productos;
	public static Estadistica estadisticos;
	public static int nivel=2;
	public static Persona usuario;
	public static void main(String[] args){
		inicio();
		montarDB();
		//while(true) {
		//	launch();
		//}
	}
	public static void montarDB() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		Gson gson = builder.create();
		File BaseDatos= new File("src\\\\BaseDatos");
		//Inventario
		File inventarioDB = new File(BaseDatos, "InventarioDB.txt");
		try {
			if(inventarioDB.exists()) {
				PrintWriter inventarioWriter = new PrintWriter(inventarioDB);
				inventarioWriter.println(gson.toJson(inventario));
				inventarioWriter.close();
			}else {
				inventarioDB.createNewFile();
				PrintWriter inventarioWriter = new PrintWriter(inventarioDB);
				inventarioWriter.println(gson.toJson(inventario));
				inventarioWriter.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo encontrar el archivo de Categorias");
		} catch (IOException e) {
				System.out.println("No se pudo crear el archivo de Inventario");
		}
		//Categorias
		File categoriasDB = new File(BaseDatos, "CategoriasDB.txt");
		try {
			if(categoriasDB.exists()) {
				PrintWriter categoriasWriter = new PrintWriter(categoriasDB);
				categoriasWriter.println(gson.toJson(categorias));
				categoriasWriter.close();
			}else {
				categoriasDB.createNewFile();
				PrintWriter categoriasWriter = new PrintWriter(categoriasDB);
				categoriasWriter.println(gson.toJson(categorias));
				categoriasWriter.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo encontrar el archivo de Categorias");
		} catch (IOException e) {
				System.out.println("No se pudo crear el archivo de Inventario");
		}
		//Productos
		File productosDB = new File(BaseDatos, "ProductosDB.txt");
		try {
			if(productosDB.exists()) {
				PrintWriter productosWriter = new PrintWriter(productosDB);
				productosWriter.println(gson.toJson(inventario));
				productosWriter.close();
			}else {
				productosDB.createNewFile();
				PrintWriter productosWriter = new PrintWriter(productosDB);
				productosWriter.println(gson.toJson(inventario));
				productosWriter.close();
			}	
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo encontrar el archivo de Productos");
		} catch (IOException e) {
				System.out.println("No se pudo crear el archivo de Inventario");
		}
		//Usuarios
		File usuariosDB = new File(BaseDatos, "UsuariosDB.txt");
		try {
			if(usuariosDB.exists()) {
				PrintWriter usuariosWriter = new PrintWriter(usuariosDB);
				usuariosWriter.println(gson.toJson(Usuarios));
				usuariosWriter.close();
			}else {
				usuariosDB.createNewFile();
				PrintWriter usuariosWriter = new PrintWriter(usuariosDB);
				usuariosWriter.println(gson.toJson(Usuarios));
				usuariosWriter.close();
			}		
		} catch (FileNotFoundException e) {
				System.out.println("No se pudo encontrar el archivo de Categorias");
			} catch (IOException e) {
				System.out.println("No se pudo crear el archivo de Inventario");
		}
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
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Stage principal=new Stage();
		principal.setTitle("Tienda Virtual");
		Scene inicio=new Scene(new Label("Hola Mundo!"),500,500);
		principal.setScene(inicio);
		principal.show();
	}
	
	public static void inicio() {
		cargarUsuarios();
		cargarProductos();
		cargarCategorias();
		cargarInventario();
	}
	
}
