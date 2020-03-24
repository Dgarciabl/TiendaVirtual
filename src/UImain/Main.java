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
		usuario=Usuarios.get(3);
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
		Usuario user2 = new Usuario("Pablo",true,19, "holasoypablo","bat123", "NombrePadre", "Mauricio");
		Usuarios.add(user);
		//usuario=user2;
		//usuario=admon;
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
		//principal.setCenter(crearProducto());
		//principal.setCenter(MostrarCarro());
		//principal.setCenter(mostrarInventario());
		//Setting the scene
		invitado=new Scene(principal, 400,400);
		return invitado;
	}
	public Scene Usuario(Usuario usu) {
		Scene usuario;
		BorderPane principal=new BorderPane();
		mainStage.setTitle(usu.getNombre());
		principal.setTop(menuUsuario());
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

		principal.setTop(menuAdministrador());
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
		Menu consultasUsuario = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem buscarProducto = new MenuItem("Busqueda por nombre");
		MenuItem busquedaCategorias = new MenuItem("Busqueda por Categorias");
		busqueda.getItems().addAll(buscarProducto,busquedaCategorias);
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
		
		//Ayuda		
		Menu ayuda=new Menu("Ayuda");
		menu=new MenuBar(archivo,consultasUsuario,ayuda);
		return (menu);
	}
	public static MenuBar menuAdministrador() {
		Menu archivo = new Menu("Archivo");
		Menu consultasAdmin = new Menu("Procesos y Consultas");
		//Producto
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
		MenuItem delCategoria = new MenuItem("Eliminar Categorias");
		MenuItem setDescripcionCategoria = new MenuItem("Cambiar descripción");
		MenuItem setNombreCategoria = new MenuItem("Cambiar nombre");
		setCategorias.getItems().addAll(crearCategoria, delCategoria, setDescripcionCategoria, setNombreCategoria);
		//Usuarios
		Menu setUsuarios = new Menu("Usuarios");
		MenuItem addUsuario = new MenuItem("Crear usuario");
		MenuItem delUsuario = new MenuItem("Eliminar usuario");
		Menu setOpciones = new Menu("Modificar Opciones");
		Menu opcionAdmin = new Menu("Administrador");
		MenuItem addOpcionAdmin = new MenuItem("Agregar opcion");
		MenuItem delOpcionAdmin = new MenuItem("Eliminar pcion");
		opcionAdmin.getItems().addAll(addOpcionAdmin,delOpcionAdmin);
		Menu opcionUsuario = new Menu("Usuario");
		MenuItem addOpcionUsuario = new MenuItem("Agregar opcion");
		MenuItem delOpcionUsuario = new MenuItem("Eliminar opcion");
		opcionAdmin.getItems().addAll(addOpcionUsuario,delOpcionUsuario);
		Menu opcionInvitado = new Menu("Invitado");
		MenuItem addOpcionInvitado = new MenuItem("Agregar opcion");
		MenuItem delOpcionInvitado = new MenuItem("Eliminar opcion");
		opcionAdmin.getItems().addAll(addOpcionInvitado,delOpcionInvitado);
		
		setOpciones.getItems().addAll(opcionAdmin,opcionUsuario,opcionInvitado);
		setUsuarios.getItems().addAll(addUsuario,delUsuario,setOpciones);
		consultasAdmin.getItems().addAll(setProducto,setCategorias,setUsuarios);
		
		
		Menu ayuda=new Menu("Ayuda");
		MenuBar menu=new MenuBar(archivo,consultasAdmin,ayuda);
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
			presentacion=new FieldPane("Información", categorias, "Publica", valores, habilitado);
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
			presentacion=new FieldPane("Información", categorias, "Personal", valores, habilitado);
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
			presentacion=new FieldPane("Información", categorias, "Personal", valores, habilitado);
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
			presentacion=new FieldPane("Información", categorias, "Publica", valores,habilitado);
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
	public static VBox mostrarInventario() {
		VBox inv=new VBox();
		Label lis1=new Label("Mostrar Inventario"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		//TextField t: Muestra Inventario
		//TextField b: Muestra Descripcion de producto y cantidad
		TextField t=new TextField("ListaProd"); TextField b=new TextField("Descripcion");
		t.setPrefHeight(100); b.setPrefHeight(100);
		
		t.setPadding(new Insets(5)); b.setPadding(new Insets(5));
		g.add(new Label("Inventario:"), 0, 0); g.add(new Label("Descripcion y cantidad:"), 1, 0);
		g.add(t,0, 1); g.add(b, 1, 1,2,1);
		g.add(new Button("Editar inventario(Admin)"), 1, 2);
		inv.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		inv.setAlignment(Pos.TOP_CENTER);
		
		return inv;
	}
	public static VBox mostrarCategorias() {
		VBox cat=new VBox();
		
		Label lis1=new Label("Mostrar Categorias"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		//TextField t: Muestra la ArrayList categorias
		//TextField b: Muestra descripcion de la categoria
		TextField t=new TextField("ListaCat"); TextField b=new TextField("Descripcion");
		t.setPrefHeight(100); b.setPrefHeight(100);
		
		t.setPadding(new Insets(5)); b.setPadding(new Insets(5));
		g.add(new Label("Lista de de categorias:"), 0, 0); g.add(new Label("Descripcion:"), 1, 0);
		g.add(t,0, 1); g.add(b, 1, 1);
		g.add(new Button("Busqueda por categoria"), 1, 2);
		cat.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		cat.setAlignment(Pos.TOP_CENTER);
				
		return cat;
	}
	public static VBox mostrarProductos() {
		//casi seguro esta esta mala porque nunca diferencio bien Producto e inventario :v
		VBox prod=new VBox();
		
		Label lis1=new Label("Mostrar Categorias"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		//TextField t: Muestra la ArrayList Productos
		//TextField b: Muestra descripcion del producto
		TextField t=new TextField("ListaProd"); TextField b=new TextField("Descripcion");
		t.setPrefHeight(100); b.setPrefHeight(100);
		
		t.setPadding(new Insets(5)); b.setPadding(new Insets(5));
		g.add(new Label("Lista de de productos:"), 0, 0); g.add(new Label("Descripcion:"), 1, 0);
		g.add(t,0, 1); g.add(b, 1, 1);
		g.add(new Button("Añadir al carro"), 1, 2);
		prod.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		prod.setAlignment(Pos.TOP_CENTER);
		
		return prod;
	}
				//Carro
	public static VBox MostrarCarro() {
		VBox car=new VBox();
		
		Label lis1=new Label("Carro"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		//TextField t: Muestra la ArrayList carro del usuario
		//Textfield b: Muestra la cantidad de productos y el subtotal
		TextField t=new TextField("ListaProdCar"); TextField b=new TextField("Items y Subtotal:");
		t.setPrefHeight(100); b.setPrefHeight(50);
		
		t.setPadding(new Insets(5)); b.setPadding(new Insets(5));
		g.add(new Label("Productos añadidos:"), 0, 0); g.add(new Label("Subtotal:"), 1, 0);
		g.add(t,0, 1); g.add(b, 1, 1);
		g.add(new Button("Comprar"), 1, 2);
		car.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		car.setAlignment(Pos.TOP_CENTER);	
		
		return car;
	}
				//crear
	public static VBox crearProducto() {
		
		VBox crearProducto = new VBox();
		Label titulo = new Label("Crear producto");
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setPadding(new Insets(5));
		String [] campos = new String[5];
		campos[0] = "Nombre";
		campos[1] = "Descripcion";
		campos[2] = "Precio original";
		campos[3] = "Precio de venta";
		campos[4] = "Categorias";
		String [] empty = new String[5];
		
		FieldPane columnas = new FieldPane(" ",campos, " ", empty, null);
		GridPane botones = new GridPane();
		Button salir = new Button("Salir");
		botones.add(salir, 0, 0);
		botones.setPadding(new Insets(8,8,8,8));
		botones.setHgap(5);
		botones.setAlignment(Pos.TOP_CENTER);
		crearProducto.getChildren().addAll(titulo,columnas.getChild(),botones);
		crearProducto.setAlignment(Pos.TOP_CENTER);
		
		return crearProducto;
	}
	public static VBox crearCategoria() {
		VBox crearCategoria = new VBox();
		Label title = new Label("Crear Categoria");
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(5));
		String[] campos = new String [2];
		campos[0] = "Nombre";
		campos[1] = "Descripcion";
		String [] empty = new String[2];
		
		FieldPane columnas = new FieldPane(" ",campos, " ", empty, null);
		GridPane botones = new GridPane();
		Button salir = new Button("Salir");
		botones.add(salir, 0, 0);
		botones.setPadding(new Insets(8,8,8,8));
		botones.setHgap(5);
		botones.setAlignment(Pos.TOP_CENTER);
		crearCategoria.getChildren().addAll(title,columnas.getChild(),botones);
		crearCategoria.setAlignment(Pos.TOP_CENTER);
		
		return crearCategoria();
		
	}
	

	
	
	
	
	/** completar
	 * 
	 */
				
	
}
