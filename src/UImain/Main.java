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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {
	Stage mainStage;
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
				productosWriter.println(gson.toJson(productos));
				productosWriter.close();
			}else {
				productosDB.createNewFile();
				PrintWriter productosWriter = new PrintWriter(productosDB);
				productosWriter.println(gson.toJson(productos));
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
		mainStage=new Stage();
		mainStage.setTitle("Tienda Virtual");
		Scene inicio=Invitado();
		mainStage.setScene(inicio);
		mainStage.show();
	}
	public Scene Inicial() {
		
		
		
		
		return new Scene(new Button());
	}
	public Scene Invitado() {
		Scene invitado;
		BorderPane principal=new BorderPane();
		mainStage.setTitle("Invitado");
		//Top
		Menu archivo=new Menu("Archivo");
			//Consultas
		Menu consultasInvitado = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem BuscarProducto = new MenuItem("Busqueda por nombre");
		BuscarProducto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Scene busqueda;
				FieldPane buscar=new FieldPane(" ", new String[] {"Nombre del Producto: "}, " ", null, null);
				Button buscador=new Button("Buscar");
				buscador.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						TextField s=(TextField)(buscar.getChild().getChildren().get(1));
						int j=Main.inventario.RealizarBusqueda(s.getText());
						
					}
				
				});
				VBox central=new VBox();
				
				
				busqueda=new Scene(central,400,400);
			}
		
		});
		MenuItem BuscarCategoria = new MenuItem("Busqueda por Categorias");
		busqueda.getItems().addAll(BuscarProducto,BuscarCategoria);
		MenuItem MostrarCategorias = new MenuItem("Mostrar Categorias");
		MenuItem MostrarInventario = new MenuItem("Mostrar Inventario");
		consultasInvitado.getItems().addAll(busqueda,MostrarCategorias,MostrarInventario);
			//Ayuda		
		Menu ayuda=new Menu("Ayuda");
		MenuBar menu=new MenuBar(archivo,consultasInvitado,ayuda);
		principal.setTop(menu);
			//Center
		
		//Setting the scene
		invitado=new Scene(principal, 400,400);
		return invitado;
	}
	public Scene Usuario(Usuario usu) {
		Scene usuario;
		BorderPane principal=new BorderPane();
		mainStage.setTitle(usu.getNombre());
		//Top
		Menu archivo=new Menu("Archivo");
		Menu consultasUsuario = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem buscarProducto = new MenuItem("Busqueda por nombre");
		MenuItem Busqueda = new MenuItem("Busqueda por Categorias");
		busqueda.getItems().addAll(buscarProducto,Busqueda);
		MenuItem mostrarCategorias = new MenuItem("Mostrar Categorias");
		MenuItem mostrarInventario = new MenuItem("Mostrar Inventario");
		MenuItem mostrarCarro = new MenuItem("Mostrar productos del Carro");
		MenuItem addCarro = new MenuItem("Añadir productos al carro");
		MenuItem delCarro = new MenuItem("Quitar producto del carro");
		Menu opcionCarro = new Menu("Carro");
		opcionCarro.getItems().addAll(mostrarCarro,addCarro,delCarro);
		MenuItem Comprar = new MenuItem("Comprar");
		MenuItem addSaldo = new MenuItem("Añadir Saldo");		
		MenuItem setContrasena = new MenuItem("Cambiar Contraseña");
		MenuItem verPerfil = new MenuItem("Ver perfil");
		MenuItem cerrarSesion = new MenuItem("Cerrar Sesion");
		MenuItem opcionSalir = new MenuItem("Salir");
		Menu Perfil = new Menu("Perfil");
		Perfil.getItems().addAll(verPerfil,setContrasena,cerrarSesion,opcionSalir);
		consultasUsuario.getItems().addAll(busqueda,mostrarCategorias,mostrarInventario,opcionCarro,Comprar,addSaldo,Perfil);
		
		Menu ayuda=new Menu("Ayuda");
		
		
		MenuBar menu=new MenuBar(archivo,consultasUsuario,ayuda);
		principal.setTop(menu);
		
		//Center
		
		//Setting the scene
		usuario=new Scene(principal, 400,400);
		return usuario;
	}
	public Scene Administrador(Administrador admon) {
		Scene administrador;
		BorderPane principal=new BorderPane();
		mainStage.setTitle(admon.getNombre());
		//Top
		Menu archivo=new Menu("Archivo");
		Menu consultasAdmin = new Menu("Procesos y Consultas");
		//Producto		;
		Menu setProducto = new Menu("Modificar Producto");
		MenuItem crearProducto = new MenuItem("Crear Producto");
		MenuItem delProducto = new MenuItem("Eliminar Producto");
		MenuItem addCategorias = new MenuItem("Añadir categorias");
		MenuItem delCategorias = new MenuItem("Eliminar categorias");
		MenuItem setNombre = new MenuItem("Cambiar nombre");
		MenuItem setDescripcion = new MenuItem("Cambiar descripción");
		MenuItem setPrecioCompra = new MenuItem("Cambiar precio de compra");
		MenuItem setPrecioVenta = new MenuItem("Cambiar precio de Venta");
		MenuItem setCantidad = new MenuItem("Modificar Cantidad de Existencias");
		setProducto.getItems().addAll(crearProducto,delProducto,addCategorias,delCategorias,setNombre,setDescripcion,setPrecioCompra,setPrecioVenta,setCantidad);
		//Categorias 
		Menu setCategorias = new Menu("Modificar Categorias");
		MenuItem crearCategoria = new MenuItem("Crear Categorias");
		
		Menu ayuda=new Menu("Ayuda");
		MenuBar menu=new MenuBar(archivo,consultasAdmin,ayuda);
		principal.setTop(menu);
		//Center
		
		//Setting the scene
		administrador=new Scene(principal, 400,400);
		return administrador;
	}
	public static VBox archivo(Persona pers) {
		VBox archivo=new VBox();
		if(pers==null) {
			
		}else if(pers instanceof Usuario) {
			String[] categorias=new String[4];
			categorias[0]="Nombre: ";
			categorias[1]="Edad: ";
			categorias[2]="Genero: ";
			categorias[3]="Saldo: ";
			String[] valores=new String[4];
			valores[0]=pers.getNombre();
			valores[1]=String.valueOf(pers.getEdad());
			if(pers.getGenero()) {
				valores[2]="Masculino";
			}else {
				valores[2]="Femenino";
			}
			valores[3]=String.valueOf(((Usuario)pers).getSaldo());
			boolean[] habilitado=new boolean[4];
			for(int i=0;i<4;i++) {habilitado[i]=false;}
			FieldPane presentacion=new FieldPane("Inforación", categorias, "Personal", valores, habilitado);
		}else if (pers instanceof Administrador) {
			String[] categorias=new String[3];
			categorias[0]="Nombre: ";
			categorias[1]="Edad: ";
			categorias[2]="Genero: ";
			String[] valores=new String[3];
			valores[0]=pers.getNombre();
			valores[1]=String.valueOf(pers.getEdad());
			if(pers.getGenero()) {
				valores[2]="Masculino";
			}else {
				valores[2]="Femenino";
			}
			boolean[] habilitado=new boolean[3];
			for(int i=0;i<4;i++) {habilitado[i]=false;}
			FieldPane presentacion=new FieldPane("Inforación", categorias, "Personal", valores, habilitado);
		}
		
		return archivo;
	}
}
