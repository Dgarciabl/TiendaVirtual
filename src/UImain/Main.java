package UImain;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
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
		//montarDB();
		launch();
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
	
	public static void CargarDB() {
		inventario=new Inventario();
		productos=new ArrayList<Producto>();
		categorias=new ArrayList<Categoria>();
		Gson g=new Gson();
		Inventario i;
		Detalle d;
		Producto p;
		Categoria c;
		//Productos
		try {
			i = g.fromJson(new FileReader("src\\\\BaseDatos\\ProductosDB.txt"), Inventario.class);
			ArrayList<Detalle> arr=i.getInventario();
			for (int s=0;s<arr.size();s++) {
				p=arr.get(s).getProducto(); productos.add(p);
				
			}
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Inventario
		try {
			i=g.fromJson(new FileReader("src\\\\BaseDatos\\InventarioDB.txt"), Inventario.class);
			inventario=i;
		}
		catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		//Categorias
		try {
			Type tipoCat=new TypeToken<ArrayList<Categoria>>() {}.getType();			
			categorias=g.fromJson(new FileReader("src\\\\BaseDatos\\CategoriasDB.txt"), tipoCat);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Usuarios(Pospuesto)
		
		
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
	public static void inicio() {
		//cargarUsuarios();
		//cargarProductos();
		//cargarCategorias();
		//cargarInventario();
		CargarDB();
	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Stage principal=new Stage();
		principal.setTitle("Tienda Virtual");
		Scene inicio=Invitado();
		principal.setScene(inicio);
		principal.show();
	}
	public Scene Inicial() {
		
		
		
		
		return new Scene(new Button());
	}
	public Scene Invitado() {
		Scene invitado;
		BorderPane principal=new BorderPane();
		//Top
		Menu archivo=new Menu("Archivo");
		Menu consultas=new Menu("Procesos y Consultas");
		Menu ayuda=new Menu("Ayuda");
		MenuBar menu=new MenuBar(archivo,consultas,ayuda);
		principal.setTop(menu);
		//Center
		
		//Setting the scene
		invitado=new Scene(principal, 250,250);
		return invitado;
	}
	public Scene Usuario() {
		Scene usuario;
		BorderPane principal=new BorderPane();
		//Top
		Menu archivo=new Menu("Archivo");
		Menu consultas=new Menu("Procesos y Consultas");
		Menu ayuda=new Menu("Ayuda");
		MenuBar menu=new MenuBar(archivo,consultas,ayuda);
		principal.setTop(menu);
		//Center
		
		//Setting the scene
		usuario=new Scene(principal, 250,250);
		return usuario;
	}
	public Scene Administrador() {
		Scene administrador;
		BorderPane principal=new BorderPane();
		//Top
		Menu archivo=new Menu("Archivo");
		Menu consultas=new Menu("Procesos y Consultas");
		Menu ayuda=new Menu("Ayuda");
		MenuBar menu=new MenuBar(archivo,consultas,ayuda);
		principal.setTop(menu);
		//Center
		
		//Setting the scene
		administrador=new Scene(principal, 250,250);
		return administrador;
	}
}
