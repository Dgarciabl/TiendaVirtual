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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {
	//App data
	public static ArrayList<Persona> Usuarios;
	public static Inventario inventario;
	public static ArrayList<Categoria> categorias;
	public static ArrayList<Producto> productos;
	public static Estadistica estadisticos;
	public static int nivel=2;
	public static Persona usuario;
	//Graphic interfaces
	Stage mainStage;
	
	
	//Main
	public static void main(String[] args){
		inicio();
		//montarDB();
		launch();
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
	//Database
	public static void inicio() {
		//cargarUsuarios();
		CargarDB();
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
		
		//preprocessing
		ArrayList<Usuario> usrs=new ArrayList<Usuario>();
		ArrayList<Administrador> admins=new ArrayList<Administrador>();
		
		for (int i=0;i<Usuarios.size();i++) {
			if(Usuarios.get(i) instanceof Usuario) {
				usrs.add((Usuario)Usuarios.get(i));
			}else {
				admins.add((Administrador)Usuarios.get(i));
			}
		}
		
		//Usuarios
		File usuariosDB = new File(BaseDatos, "UsuariosDB.txt");
		try {
			if(usuariosDB.exists()) {
				PrintWriter usuariosWriter = new PrintWriter(usuariosDB);
				usuariosWriter.println(gson.toJson(usrs));
				usuariosWriter.close();
			}else {
				usuariosDB.createNewFile();
				PrintWriter usuariosWriter = new PrintWriter(usuariosDB);
				usuariosWriter.println(gson.toJson(usrs));
				usuariosWriter.close();
			}		
		} catch (FileNotFoundException e) {
				System.out.println("No se pudo encontrar el archivo de Categorias");
			} catch (IOException e) {
				System.out.println("No se pudo crear el archivo de Inventario");
		}
		//Administradores
		File AdministradoresDB = new File(BaseDatos, "AdministradoresDB.txt");
		try {
			if(AdministradoresDB.exists()) {
				PrintWriter AdministradoresWriter = new PrintWriter(AdministradoresDB);
				AdministradoresWriter.println(gson.toJson(admins));
				AdministradoresWriter.close();
			}else {
				AdministradoresDB.createNewFile();
				PrintWriter AdministradoresWriter = new PrintWriter(AdministradoresDB);
				AdministradoresWriter.println(gson.toJson(admins));
				AdministradoresWriter.close();
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
		Usuarios=new ArrayList<Persona>();
		Gson g=new Gson();
		Inventario i;
		//Productos
		try {
			Type tipoPro=new TypeToken<ArrayList<Producto>>() {}.getType();
			productos= g.fromJson(new FileReader("src\\BaseDatos\\ProductosDB.txt"), tipoPro);
							
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Inventario
		try {
			i=g.fromJson(new FileReader("src\\BaseDatos\\InventarioDB.txt"), Inventario.class);
			inventario=i;
		}
		catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		//Categorias
		try {
			Type tipoCat=new TypeToken<ArrayList<Categoria>>() {}.getType();			
			categorias=g.fromJson(new FileReader("src\\BaseDatos\\CategoriasDB.txt"), tipoCat);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Usuarios
		try {
			ArrayList<Usuario> usuTemp=new ArrayList<Usuario>();		
			Type tipoUsu=new TypeToken<ArrayList<Usuario>>() {}.getType();
		
			usuTemp=g.fromJson(new FileReader("src\\BaseDatos\\UsuariosDB.txt"), tipoUsu);			
			Usuarios.addAll(usuTemp);

		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ArrayList<Administrador>admTemp=new ArrayList<Administrador>();
			Type tipoAdm=new TypeToken<ArrayList<Administrador>>() {}.getType();
			
			admTemp=g.fromJson(new FileReader("src\\BaseDatos\\AdministradoresDB.txt"), tipoAdm);
			Usuarios.addAll(admTemp);
			usuario=Usuarios.get(0);
		}
		catch(JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
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
		Usuarios.add(user);
		//usuario=user2;
		usuario=admon;
	}
	//Utilities
	public static boolean isNumeric(String s) {
		try {
			Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	//Interfaces Graficas
		//Scenes
	public Scene Inicial() {
		
		
		
		
		return new Scene(new Button());
	}
	public Scene Invitado() {
		Scene invitado;
		BorderPane principal=new BorderPane();
		mainStage.setTitle("Invitado");
		//Top
		principal.setTop(menuInvitado());
		//center
		principal.setCenter(archivo(usuario));
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
		//Panes
			//menus
	public static MenuBar menuInvitado() {
		MenuBar menu;
		Menu archivo=new Menu("Archivo");
		//Consultas
		Menu consultasInvitado = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem BuscarProducto = new MenuItem("Busqueda por nombre");
		MenuItem BuscarCategoria = new MenuItem("Busqueda por Categorias");
		busqueda.getItems().addAll(BuscarProducto,BuscarCategoria);
		MenuItem MostrarCategorias = new MenuItem("Mostrar Categorias");
		MenuItem MostrarInventario = new MenuItem("Mostrar Inventario");
		consultasInvitado.getItems().addAll(busqueda,MostrarCategorias,MostrarInventario);
		//Ayuda		
		Menu ayuda=new Menu("Ayuda");
		menu=new MenuBar(archivo,consultasInvitado,ayuda);
		return (menu);
	}
	public static MenuBar menuUsuario() {
		MenuBar menu;
		Menu archivo=new Menu("Archivo");
		//Consultas
		Menu consultasInvitado = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem BuscarProducto = new MenuItem("Busqueda por nombre");
		MenuItem BuscarCategoria = new MenuItem("Busqueda por Categorias");
		busqueda.getItems().addAll(BuscarProducto,BuscarCategoria);
		MenuItem MostrarCategorias = new MenuItem("Mostrar Categorias");
		MenuItem MostrarInventario = new MenuItem("Mostrar Inventario");
		
		/** completar
		 * 
		 */
		
		
		consultasInvitado.getItems().addAll(busqueda,MostrarCategorias,MostrarInventario);
		//Ayuda		
		Menu ayuda=new Menu("Ayuda");
		menu=new MenuBar(archivo,consultasInvitado,ayuda);
		return (menu);
	}
	public static MenuBar menuAdiministrador() {
		MenuBar menu;
		Menu archivo=new Menu("Archivo");
		//Consultas
		Menu consultasInvitado = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem BuscarProducto = new MenuItem("Busqueda por nombre");
		MenuItem BuscarCategoria = new MenuItem("Busqueda por Categorias");
		busqueda.getItems().addAll(BuscarProducto,BuscarCategoria);
		MenuItem MostrarCategorias = new MenuItem("Mostrar Categorias");
		MenuItem MostrarInventario = new MenuItem("Mostrar Inventario");
		
		/** completar
		 * 
		 */
		
		
		consultasInvitado.getItems().addAll(busqueda,MostrarCategorias,MostrarInventario);
		//Ayuda		
		Menu ayuda=new Menu("Ayuda");
		menu=new MenuBar(archivo,consultasInvitado,ayuda);
		return (menu);
	}
			//Archivo
	public static VBox archivo(Persona pers) {
		String[] categorias;
		String[] valores;
		VBox archivo=new VBox();
		FieldPane presentacion;
		GridPane Botones=new GridPane();
		Label titulo=new Label();
		Button salir=new Button("Salir");
		Button editar=new Button("Editar");
		Button añadirSaldo=new Button("Añadir Saldo");
		if(pers==null) {
			titulo.setText("Perfil de Invitado");
			categorias=new String[1];
			valores=new String[1];
			categorias[0]="Tienda Virtual";
			valores[0]="Version 1";
			boolean[] habilitado=new boolean[1];
			for(int i=0;i<1;i++) {habilitado[i]=false;}
			presentacion=new FieldPane("Inforación", categorias, "Publica", valores, habilitado);
			Botones.add(salir, 0, 0);
		}else if(pers instanceof Usuario) {
			titulo.setText("Perfil de Usuario");
			categorias=new String[4];
			valores=new String[4];
			categorias[0]="Nombre: ";
			categorias[1]="Edad: ";
			categorias[2]="Genero: ";
			categorias[3]="Saldo: ";
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
			presentacion=new FieldPane("Inforación", categorias, "Personal", valores, habilitado);
			Botones.add(salir, 2, 0);
			Botones.add(editar, 1, 0);
			Botones.add(añadirSaldo, 0, 0);
		}else if (pers instanceof Administrador) {
			titulo.setText("Perfil de Administrador");
			categorias=new String[3];
			valores=new String[3];
			categorias[0]="Nombre: ";
			categorias[1]="Edad: ";
			categorias[2]="Genero: ";
			valores[0]=pers.getNombre();
			valores[1]=String.valueOf(pers.getEdad());
			if(pers.getGenero()) {
				valores[2]="Masculino";
			}else {
				valores[2]="Femenino";
			}
			boolean[] habilitado=new boolean[3];
			for(int i=0;i<3;i++) {habilitado[i]=false;}
			presentacion=new FieldPane("Inforación", categorias, "Personal", valores, habilitado);
			Botones.add(salir, 1, 0);
			Botones.add(editar, 0, 0);
		}else {
			titulo.setText("Esto es un Error");
			categorias=new String[1];
			valores=new String[1];
			categorias[0]="Por Favor Contactar al Administrador";
			valores[0]="Version 1";
			boolean[] habilitado=new boolean[1];
			for(int i=0;i<1;i++) {habilitado[i]=false;}
			presentacion=new FieldPane("Inforación", categorias, "Publica", valores,habilitado);
			Botones.add(salir, 0, 0);
		}
		titulo.setPadding(new Insets(5));
		Botones.setAlignment(Pos.CENTER);
		Botones.setPadding(new Insets(8,8,8,8));
		Botones.setHgap(5);
		archivo.getChildren().addAll(titulo,presentacion.getChild(),Botones);
		archivo.setAlignment(Pos.TOP_CENTER);
		return archivo;
	}
			//Consultas
				//Busqueda
	public static VBox BuscarNombre() {
		VBox principal=new VBox();
		String[] categorias= {"Nombre del Producto:"};
		FieldPane buscador=new FieldPane("Busqueda por",categorias,"nombre", null, null);
		buscador.getChild().setAlignment(Pos.TOP_CENTER);
		buscador.getChild().setPadding(new Insets(5));
		Button buscar=new Button("Buscar");
		buscar.setAlignment(Pos.CENTER);
		principal.getChildren().addAll(buscador,buscar);
		return principal;
	}
	public static VBox BuscarCategoria() {
		VBox principal=new VBox();
		Label titulo=new Label("Seleccione la Categoria:");
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setPadding(new Insets(5));
		GridPane buscador=new GridPane();
		buscador.setAlignment(Pos.TOP_CENTER);
		buscador.setPadding(new Insets(5));
		buscador.add(new Label("Seleccione la Categoria"), 0, 0);
		
		Button buscar=new Button("Buscar");
		buscar.setAlignment(Pos.CENTER);
		principal.getChildren().addAll(titulo,buscador,buscar);
		return principal;
	}
				//Mostrar
	/** completar
	 * 
	 */
				//Carro
	/** completar
	 * 
	 */
				
	
}
