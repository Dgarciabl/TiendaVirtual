package UImain;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.*;
import java.util.function.Consumer;

import gestorAplicacion.Usuario.*;
import gestorAplicacion.Administrador.*;
import gestorAplicacion.Exepciones.FormularioIncompletoError;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
	//App data
	public static int hojaActual;
	public static ArrayList<Persona> Usuarios;
	public static Inventario inventario;
	public static ArrayList<Categoria> categorias;
	public static ArrayList<Producto> productos;
	public static Estadistica estadisticos;
	public static int nivel=2;
	public static Persona usuario;
	//Graphic interfaces
	static Stage mainStage;
		//Inicial
	static Scene sceneInicial;
	static BorderPane principalInicial;
		//Invitado
	static Scene sceneInvitado;
	static BorderPane principalInvitado;
		//Usuario
	static Scene sceneUsuario;
	static BorderPane principalUsuario;
		//Administrador
	static Scene sceneAdministrador;
	static BorderPane principalAdministrador;
	//Main
	public static void main(String[] args){
		inicio();
		launch();
		finalizar();
	}
	@Override
	public void start(Stage arg0) throws Exception {
		mainStage=new Stage();
		mainStage.setTitle("Tienda Virtual");
		Escenas();
		principal();
		mainStage.setScene(sceneInicial);
		mainStage.show();
	}
	//Database
	public static void inicio() {
		CargarDB();
	}
	public static void finalizar() {
		montarDB();
		System.exit(0);

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
	public static FieldPane productor(int j) {
		String[] criterios= {"Descripcion:","Precio de compra:","Precio de venta:"};
		String[] valores=new String[3];
		valores[0]=productos.get(j).getDescripcion();
		valores[1]=Double.toString(productos.get(j).getPrecioCompra());
		valores[2]=Double.toString(productos.get(j).getPrecioVenta());
		boolean[] habilitado=new boolean[3];
		for(int i=0;i<habilitado.length;i++) {habilitado[i]=false;}
		FieldPane resultado=new FieldPane("",criterios,"",valores,habilitado);
		resultado.getChild().setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return resultado;
	}
	public static FieldPane productorNom(int j) {
		String[] criterios= {"Nombre:","Descripcion:","Precio de compra:","Precio de venta:"};
		String[] valores=new String[4];
		valores[0]=productos.get(j).getNombre();
		valores[1]=productos.get(j).getDescripcion();
		valores[2]=Double.toString(productos.get(j).getPrecioCompra());
		valores[3]=Double.toString(productos.get(j).getPrecioVenta());
		boolean[] habilitado=new boolean[4];
		for(int i=0;i<habilitado.length;i++) {habilitado[i]=false;}
		FieldPane resultado=new FieldPane("",criterios,"",valores,habilitado);
		resultado.getChild().setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return resultado;
	}
	public static FieldPane categoricas(int j) {
		String[] Criterios= {"Descripcion:"};
		String[] Valores= new String[1];
		Valores[0]=categorias.get(j).getDescripcion();
		boolean[] habilitado=new boolean[1]; habilitado[0]=false;
		FieldPane res=new FieldPane("",Criterios,"",Valores,habilitado);
		res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return res;
	}
	public static FieldPane categoricasNom(int j) {
		String[] Criterios= {"Nombre:","Descripcion:"};
		String[] Valores= new String[2];
		Valores[0]=categorias.get(j).getNombre();
		Valores[1]=categorias.get(j).getDescripcion();
		boolean[] habilitado=new boolean[2]; habilitado[0]=false; habilitado[1]=false;
		FieldPane res=new FieldPane("",Criterios,"",Valores,habilitado);
		res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return res;
	}
	public static FieldPane inventateEsta (int j) {
		if (usuario==null || usuario instanceof Usuario ) {
		String[] Criterios= {"Descripcion:","Precio:","Unidades disponibles:"};
		String[] Valores=new String[3];
		Valores[0]=inventario.getInventario(j).getProducto().getDescripcion();
		Valores[1]=Double.toString(inventario.getInventario(j).getProducto().getPrecioVenta());
		Valores[2]=Integer.toString(inventario.getInventario(j).getCantidad());
		boolean[] Hab=new boolean[3]; for (int l=0;l<Hab.length;l++) {Hab[l]=false;}
		FieldPane res=new FieldPane("",Criterios,"",Valores,Hab);
		res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return res;
		}
		else {
			String[] Criterios= {"Nombre:","Descripcion:","Precio de compra:","Precio de venta:","Unidades disponibles:"};
			String[] Valores=new String[5];
			Valores[0]=inventario.getInventario(j).getProducto().getNombre();
			Valores[1]=inventario.getInventario(j).getProducto().getDescripcion();
			Valores[2]=Double.toString(inventario.getInventario(j).getProducto().getPrecioCompra());
			Valores[3]=Double.toString(inventario.getInventario(j).getProducto().getPrecioVenta());
			Valores[4]=Integer.toString(inventario.getInventario(j).getCantidad());
			boolean[] Hab=new boolean[5]; for (int l=0;l<Hab.length;l++) {Hab[l]=false;}
			FieldPane res=new FieldPane("",Criterios,"",Valores,Hab);
			res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			return res;
		}
	}
	public static FieldPane stosMortales(int j) {
		String[] Criterios= {"Nombre;","Edad:","Genero:","Nick:","Pregunta recuperacion"};
		String[] Valores=new String[5];
		Valores[0]=Usuarios.get(j).getNombre();
		Valores[1]=Integer.toString(Usuarios.get(j).getEdad());
		if (Usuarios.get(j).getGenero()==true) {
			Valores[2]= "Masculino";
		}
		else {
			Valores[2]="Femenino";
		}
		Valores[3]=Usuarios.get(j).getUsuario();
		Valores[4]=Usuarios.get(j).getPregunta();
		boolean[] Hab=new boolean[5]; for (int i=0;i<Hab.length;i++) {Hab[i]=false;}
		FieldPane res=new FieldPane("",Criterios,"",Valores,Hab);
		res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return res;
	}
	public static boolean InicioSesion(String usu, String key) {
		Persona temp;
		for (int i=0;i<Usuarios.size();i++) {
			temp=Usuarios.get(i);
			if (usu.equals(temp.getUsuario())){
				if (temp.comprobarContraseña(key)) {
					usuario = temp;
					if(usuario instanceof Administrador) {
						nivel = 0;
					}else {
						nivel = 1;
					}
					return true;
				}else {
					System.out.println("la contrasena es incorrecta");
				}
			}
		}
		return false;
	}

	
	//Interfaces Graficas
	//Scenes
	public void Escenas() {
		//Inicial
		principalInicial=new BorderPane();
		sceneInicial=new Scene(principalInicial, 400,400);
		//Invitado
		principalInvitado=new BorderPane();
		sceneInvitado=new Scene(principalInvitado, 400,400);
		principalInvitado.setTop(menuInvitado());
		//Usuario
		principalUsuario=new BorderPane();
		sceneUsuario=new Scene(principalUsuario, 400,400);
		principalUsuario.setTop(menuUsuario());
		//Administrador
		principalAdministrador=new BorderPane();
		sceneAdministrador=new Scene(principalAdministrador, 400,400);
		principalAdministrador.setTop(menuAdministrador());
	}
	public static void principal() {
		BorderPane mainPane = new BorderPane();
		//Derecha
		String[] hojaVida = new String[4];
		hojaVida[0] = "Cano";hojaVida[1] = "David"; hojaVida[2] = "Pablo"; hojaVida[3] = "Julian";
		hojaActual = 0;
		Label creadores = new Label(hojaVida[hojaActual]);
		creadores.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				
				if( hojaActual != 3){
					hojaActual +=1;
					creadores.setText(hojaVida[hojaActual]);
				}else {
					hojaActual = 0;
					creadores.setText(hojaVida[hojaActual]);
				}
			}
		});
		//Izquierda
		VBox login = new VBox();
		Label title = new Label("");
		
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(5));
		String[] campos = new String [2];
		campos[0] = "Usuario";
		campos[1] = "Contraseña";
		String [] empty = new String[2];
		
		FieldPane columnas = new FieldPane("",campos, "Iniciar Sesion", empty, null);
		GridPane botones = new GridPane();
		Button iniciarS = new Button("Iniciar Sesion");
		iniciarS.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String usu = columnas.getValue(0);
				String password = columnas.getValue(1);

					if (Main.isNumeric(usu)) {
						System.out.println("El usuario no puede ser un numero");
					}else if(InicioSesion(usu,password)) {
						archivo();
						if(usuario instanceof Usuario) {
							mainStage.setScene(sceneUsuario);
						}else if (usuario instanceof Administrador) {
							mainStage.setScene(sceneAdministrador);
						}
					}
			}
		});
		Button logInvitado = new Button("Entrar como invitado");
		logInvitado.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				archivo();
				mainStage.setScene(sceneInvitado);
			}
		});
		botones.add(iniciarS, 0, 0);
		botones.add(logInvitado, 1, 0);
		botones.setPadding(new Insets(8,8,8,8));
		botones.setHgap(5);
		botones.setAlignment(Pos.TOP_CENTER);
		login.getChildren().addAll(title,columnas.getChild(),botones);
		login.setAlignment(Pos.TOP_CENTER);
		
		Label bienvenida = new Label("\n \n \n    Bienvenido\n    a la \n    tienda virtual");
		bienvenida.setTextAlignment(TextAlignment.CENTER);
		bienvenida.setFont(new Font("Arial",20));
		bienvenida.setTextFill(Color.BLUE);
		bienvenida.setMaxWidth(Double.MAX_VALUE);
		mainPane.setRight(login);
		mainPane.setLeft(bienvenida);
		mainPane.setBottom(creadores);
		mainPane.setTop(menuPrincipal());
		Scene principal = new Scene(mainPane,400,400);
		sceneInicial = principal;
		
	}
	
	public static MenuBar menuPrincipal() {
		MenuBar mainMenu;
		Menu descripcion = new Menu("Descripción");
		MenuItem sistema = new Menu("Sistema");
		sistema.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Label systemDesd = new Label("");
			}
		});
		descripcion.getItems().addAll(sistema);
		Menu salir = new Menu("Salir");
		MenuItem opcion = new MenuItem("Salir");
		opcion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				finalizar();
			}	
		});
		salir.getItems().addAll(opcion);
		mainMenu = new MenuBar(descripcion,salir);
		return mainMenu;
	}
		//Panes
			//menus
	public static MenuBar menuInvitado() {
		MenuBar menu;
		//Archivo
		Menu archivoInvitado = new Menu("Archivo");
		MenuItem perfilInvitado = new MenuItem("Perfil");
		perfilInvitado.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				archivo();
			}
		});
		MenuItem logOutInvitado = new MenuItem("Cerrar Sesion");
		logOutInvitado.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.setScene(sceneInicial);
			}
		});
		archivoInvitado.getItems().addAll(perfilInvitado,logOutInvitado);
		//Consultas
		Menu consultasInvitado = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem BuscarProducto = new MenuItem("Busqueda por nombre");
		BuscarProducto.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent evento) {
				BuscarNombre();
			}
		});
		MenuItem BuscarCategoria = new MenuItem("Busqueda por Categorias");
		BuscarCategoria.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BuscarCategoria();	
			}
		});
		busqueda.getItems().addAll(BuscarProducto,BuscarCategoria);
		MenuItem mostrarCategorias = new MenuItem("Mostrar Categorias");
		mostrarCategorias.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mostrarCategorias();
			}
		});
		MenuItem mostrarInventario = new MenuItem("Mostrar Inventario");
		mostrarInventario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mostrarInventario();
			}
		});
		consultasInvitado.getItems().addAll(busqueda,mostrarCategorias,mostrarInventario);
		//Ayuda		
		Menu ayudaInvitado = new Menu("Ayuda");
		MenuItem aboutInvitado = new Menu("Acerca de");
		aboutInvitado.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ayuda();
			}
			
		});
		ayudaInvitado.getItems().addAll(aboutInvitado);
		menu=new MenuBar(archivoInvitado,consultasInvitado,ayudaInvitado);
		return (menu);
	}
	public static MenuBar menuUsuario() {
		MenuBar menu;
		//Archivo
		Menu archivoUsuario = new Menu("Archivo");
		MenuItem perfilUsuario = new MenuItem("Perfil");
		perfilUsuario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				archivo();
			}
		});
		MenuItem logOutUsuario = new MenuItem("Cerrar Sesion");
		logOutUsuario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.setScene(sceneInicial);
			}
		});
		archivoUsuario.getItems().addAll(perfilUsuario,logOutUsuario);
		//Consultas
		Menu consultasUsuario = new Menu("Procesos y Consultas");
		Menu busqueda = new Menu("Busqueda");
		MenuItem buscarProducto = new MenuItem("Busqueda por nombre");
		buscarProducto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BuscarNombre();				
			}
		});
		MenuItem busquedaCategorias = new MenuItem("Busqueda por categorias");
		busquedaCategorias.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BuscarCategoria();	
			}
		});
		busqueda.getItems().addAll(buscarProducto,busquedaCategorias);
		Menu mostrarExistencias = new Menu("Mostrar");
		MenuItem mostrarCategorias = new MenuItem("Mostrar categorias");
		mostrarCategorias.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mostrarCategorias();
			}
		});
		MenuItem mostrarInventario = new MenuItem("Mostrar inventario");
		mostrarInventario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mostrarInventario();
			}
		});
		MenuItem mostrarCarro = new MenuItem("Mostrar productos del carro");
		mostrarCarro.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MostrarCarro();
			}
		});
		mostrarExistencias.getItems().addAll(mostrarCarro,mostrarInventario,mostrarCategorias);
		consultasUsuario.getItems().addAll(mostrarExistencias,busqueda);
		
		//Ayuda		
		Menu ayudaUsuario = new Menu("Ayuda");
		MenuItem aboutUsuario = new Menu("Acerca de");
		aboutUsuario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ayuda();
			}
			
		});
		ayudaUsuario.getItems().addAll(aboutUsuario);
		menu=new MenuBar(archivoUsuario,consultasUsuario,ayudaUsuario);
		return (menu);
	}
	public static MenuBar menuAdministrador() {
		//Archivo
		Menu archivoAdmin = new Menu("Archivo");
		MenuItem perfilAdmin = new MenuItem("Perfil");
		perfilAdmin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				archivo();
			}
		});
		MenuItem logOutAdmin = new MenuItem("Cerrar Sesion");
		logOutAdmin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.setScene(sceneInicial);
			}
		});
		archivoAdmin.getItems().addAll(perfilAdmin,logOutAdmin);
		Menu consultasAdmin = new Menu("Procesos y Consultas");
		//Producto
		Menu mostrarExistencias = new Menu("Mostrar");
		MenuItem mostrarInventario = new MenuItem("Mostrar Inventario");
		mostrarInventario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mostrarInventario();
			}
		});
		MenuItem mostrarProductos = new MenuItem("Mostrar productos");
		mostrarProductos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mostrarProductos();
			}
		});
		MenuItem mostrarCategorias = new MenuItem("Mostar categorias");
		mostrarCategorias.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mostrarCategorias();
			}
		});
		MenuItem mostrarUsuarios = new MenuItem("Mostrar usuarios");
		mostrarUsuarios.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent e) {
				mostrarUsuarios();
			}
		});
		mostrarExistencias.getItems().addAll(mostrarInventario,mostrarProductos,mostrarCategorias,mostrarUsuarios);
		Menu busqueda = new Menu("Busqueda");
		MenuItem buscarProducto = new MenuItem("Busqueda por nombre");
		buscarProducto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BuscarNombre();				
			}
		});
		MenuItem busquedaCategorias = new MenuItem("Busqueda por categorias");
		busquedaCategorias.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BuscarCategoria();	
			}
		});
		busqueda.getItems().addAll(buscarProducto,busquedaCategorias);
		
		//Crear 
		
		Menu crearExistencias = new Menu("Crear");
		MenuItem crearUsuario = new MenuItem("Crear usuario");
		crearUsuario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				crearUsuario();
			}
		});
		MenuItem crearProducto = new MenuItem("Crear producto");
		crearProducto.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				crearProducto();
			}
		});
		MenuItem crearCategoria = new MenuItem("Crear categoria");
		crearCategoria.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				crearCategoria();
			}			
		});
		crearExistencias.getItems().addAll(crearUsuario,crearProducto,crearCategoria);
		
		consultasAdmin.getItems().addAll(mostrarExistencias, busqueda, crearExistencias);
		
		
		//Ayuda		
		Menu ayudaAdmin = new Menu("Ayuda");
		MenuItem aboutAdmin = new Menu("Acerca de");
		aboutAdmin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ayuda();
			}
			
		});
		ayudaAdmin.getItems().addAll(aboutAdmin);
		MenuBar menu=new MenuBar(archivoAdmin,consultasAdmin,ayudaAdmin);
		return (menu);
	}
			//Archivo
	public static void archivo() {
		String[] categorias;
		String[] valores;
		VBox archivo=new VBox();
		FieldPane presentacion;
		GridPane Botones=new GridPane();
		Label titulo=new Label();
		Button salir=new Button("Salir");
		salir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				finalizar();
			}
		});
		Button editar=new Button("Editar");
		Button añadirSaldo=new Button("Añadir Saldo");
		titulo.setPadding(new Insets(5));
		Botones.setAlignment(Pos.CENTER);
		Botones.setPadding(new Insets(8,8,8,8));
		Botones.setHgap(5);
		if(usuario instanceof Usuario) {
			titulo.setText("Perfil de Usuario");
			categorias=new String[] {"Nombre:","Edad:","Genero:","Saldo:"};
			if(usuario.getGenero()) {
				valores=new String[] {usuario.getNombre(),String.valueOf(usuario.getEdad()),"Masculino",String.valueOf(((Usuario)usuario).getSaldo())};
			}else {
				valores=new String[] {usuario.getNombre(),String.valueOf(usuario.getEdad()),"Femenino",String.valueOf(((Usuario)usuario).getSaldo())};
			}
			boolean[] habilitado=new boolean[] {false,false,false,false};
			presentacion=new FieldPane("Información", categorias, "Personal", valores, habilitado);
			Botones.add(salir, 2, 0);
			Botones.add(editar, 0, 0);
			Botones.add(añadirSaldo, 1, 0);
		}else if (usuario instanceof Administrador) {
			titulo.setText("Perfil de Administrador");
			categorias=new String[] {"Nombre:","Edad:","Genero:"};
			if(usuario.getGenero()) {
				valores=new String[] {usuario.getNombre(),String.valueOf(usuario.getEdad()),"Masculino"};
			}else {
				valores=new String[] {usuario.getNombre(),String.valueOf(usuario.getEdad()),"Femenino"};
			}
			boolean[] habilitado=new boolean[] {false,false,false};
			presentacion=new FieldPane("Información", categorias, "Personal", valores, habilitado);
			Botones.add(salir, 1, 0);
			Botones.add(editar, 0, 0);
		}else {
			titulo.setText("Perfil de Invitado");
			presentacion=new FieldPane("Información", new String[] {"Tienda Virtual",}, "Publica", new String[] {"Version 1"}, new boolean[] {false});
			Botones.add(salir, 0, 0);
		}
		archivo.getChildren().addAll(titulo,presentacion.getChild(),Botones);
		archivo.setAlignment(Pos.TOP_CENTER);
		editar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean[] desbloqueo;
				if (usuario instanceof Usuario) {
					desbloqueo =new boolean[]  {true,true,true,false};
				}else if(usuario instanceof Administrador) {
					desbloqueo =new boolean[]  {true,true,true};
				}else {
					desbloqueo =new boolean[]  {false};
				}
				presentacion.setHabilitado(desbloqueo);
				Button guardar=new Button("Guardar Cambios");
				Botones.getChildren().remove(editar);
				Botones.add(guardar,0,0);
				guardar.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						usuario.setNombre(presentacion.getValue(0));
						usuario.setEdad(Integer.parseInt(presentacion.getValue(1)));
						
						if(presentacion.getValue(2).equals("Masculino")) {
							usuario.setGenero(true);
						}else if (presentacion.getValue(2).equals("Femenino")) {
							usuario.setGenero(false);
						}else {
							//throws new GeneroNoValido();
						}
					}
				});
			}
		});
		if (usuario==null) {
			principalInvitado.setCenter(archivo);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(archivo);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(archivo);
		}
	}
			//Consultas
				//Busqueda
	public static void BuscarNombre() {
		BorderPane principal=new BorderPane();
		String[] categorias= {"Nombre del Producto:"};
		FieldPane buscador=new FieldPane("Busqueda por Nombre",categorias,"", null, null);
		buscador.getChild().setAlignment(Pos.TOP_CENTER);
		Button buscar=new Button("Buscar");
		buscador.getChild().add(buscar,1,3);
		principal.setCenter(buscador.getChild());
		buscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (((TextField)buscador.getChild().getChildren().get(3)).getText().contentEquals("")) {
						throw new FormularioIncompletoError();
					}
					Label mensaje=new Label();
					int j=Main.inventario.RealizarBusqueda(((TextField)buscador.getChild().getChildren().get(3)).getText());
					if (j!=-1) {
						mensaje.setText("Producto Encontrado");
						GridPane resultado=productor(j).getChild();
						if (usuario==null) {
							principal.setBottom(new VBox(resultado,mensaje));
						}else if(usuario instanceof Usuario) {
							Button añadir=new Button("Añadir al Carro");
							añadir.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									TextInputDialog confirmacion=new TextInputDialog();
									confirmacion.setTitle("Confirmar Adicion");
									confirmacion.setHeaderText("Confirmar la cantidad a añadir");
									confirmacion.setContentText("Cantidad:");
									Optional<String> respuesta=confirmacion.showAndWait();
									respuesta.ifPresent(new Consumer<String>() {
							            @Override public void accept(String user) {
							                ((Usuario) usuario).getCarro().AddInventario(new Detalle(inventario.getInventario(j).getProducto(),Integer.valueOf(respuesta.get())));
							                ((Usuario) usuario).getCarro().actualizar();
							            }
							        });
								}
							});
							resultado.add(añadir, 1, 5);
							principal.setBottom(new VBox(resultado,mensaje));
						}else if(usuario instanceof Administrador) {
							Button editar=new Button("Editar");
							Button elimExist=new Button("Eliminar del Inventario");
							Button eliminar=new Button("Eliminar");
							resultado.add(editar, 0, 5);
							resultado.add(elimExist, 1, 5);
							resultado.add(eliminar, 2, 5);
							principal.setBottom(new VBox(resultado,mensaje));
						}
					}else {
						mensaje.setText("Producto no Encontrado");
						mensaje.setAlignment(Pos.TOP_CENTER);
						principal.setBottom(mensaje);
					}	
				}catch(FormularioIncompletoError err){
					
				}
			}
		});
		
		
		
		if (usuario==null) {
			principalInvitado.setCenter(principal);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(principal);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(principal);
		}
	}	
	public static void BuscarCategoria() {
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
		if (usuario==null) {
			principalInvitado.setCenter(principal);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(principal);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(principal);
		}
	}
				//Mostrar
	public static void mostrarInventario() {
		VBox inv=new VBox();
		Label lis1=new Label("Mostrar Inventario"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		ListView listainv=new ListView();
		for (int k=0;k<inventario.getInventario().size();k++) {listainv.getItems().add(inventario.getInventario().get(k).getProducto().getNombre());}
		
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		g.add(new Label("Inventario:"), 0, 0); g.add(new Label("Descripcion y cantidad:"), 1, 0);
		g.add(listainv,0, 1);
		if (usuario instanceof Usuario) {
			Button añadir=new Button("Añadir al carro");
			g.add(añadir, 1, 2);
			añadir.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent e) {
					int j=listainv.getSelectionModel().getSelectedIndex();
					TextInputDialog confirmacion=new TextInputDialog();
					confirmacion.setTitle("Confirmar Adicion");
					confirmacion.setHeaderText("Confirmar la cantidad a añadir");
					confirmacion.setContentText("Cantidad:");
					Optional<String> respuesta=confirmacion.showAndWait();
					respuesta.ifPresent(new Consumer<String>() {
			            @Override public void accept(String user) {
			                ((Usuario) usuario).getCarro().AddProducto(new Detalle(inventario.getInventario().get(j).getProducto(),Integer.valueOf(respuesta.get())));
			                inventario.getInventario().get(j).restarCantidad(Integer.valueOf(respuesta.get()));
			                mostrarInventario();
			            }
			        });
				}
			});
			
		}

		inv.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		inv.setAlignment(Pos.TOP_CENTER);
		
		listainv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				int s=listainv.getSelectionModel().getSelectedIndex();
				FieldPane f=inventateEsta(s);
				g.add(f.getChild(), 1, 1);
				if (usuario instanceof Administrador) {
					Button editar=new Button("Opciones");
					g.add(editar, 1, 2);
					editar.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							HBox info=new HBox();
							FieldPane l=inventateEsta(s);
							Button b1=new Button("Editar"); Button b2=new Button("Eliminar");
							info.getChildren().addAll(l.getChild(),b1,b2);
							g.add(info, 0, 3,2,1);info.setMaxWidth(Double.MAX_VALUE);
							b1.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {
									boolean[]Des= {true,true,true,true,true};
									l.setHabilitado(Des);
									Button save=new Button("Guardar cambios");
									info.getChildren().add(save);
									save.setOnAction(new EventHandler<ActionEvent>() {
										public void handle (ActionEvent e) {
											Administrador adm=(Administrador) usuario;
											adm.modificarNombreProducto(s,l.getValue(0));
											adm.modificarDescripcionProducto(s, l.getValue(1));
											adm.modificarPrecioCompra(s, Double.parseDouble(l.getValue(2)));
											adm.modificarPrecioVenta(s, Double.parseDouble(l.getValue(3)));
											adm.modificarCantidadProducto(s, Integer.parseInt(l.getValue(4)));
											mostrarInventario();
										
										}
									});
								}
							});
							b2.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {
									Alert a=new Alert(AlertType.CONFIRMATION);
									a.setTitle("Eliminar producto");
									a.setHeaderText("¿Eliminar el producto del inventario?");
									Optional<ButtonType>res=a.showAndWait();
									if (res.get()==ButtonType.OK) {
										inventario.DelInventario(s);
										productos.remove(s);
									}
								}
							});
						}
					});
				}
			}
		});
		
		if (usuario==null) {
			principalInvitado.setCenter(inv);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(inv);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(inv);
		}
		
	}
	public static void mostrarCategorias() {
		VBox cat=new VBox();
		ListView listacat=new ListView();
		for (int k=0;k<categorias.size();k++) {listacat.getItems().add(categorias.get(k).getNombre());}
		Label lis1=new Label("Mostrar Categorias"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		g.add(new Label("Lista de categorias:"), 0, 0);
		g.add(listacat,0, 1); 
		if (usuario==null|| usuario instanceof Usuario) {
		g.add(new Button("Busqueda por categoria"), 1, 2);
		}
		cat.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.TOP_CENTER);
		cat.setAlignment(Pos.TOP_CENTER);
		
		listacat.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle (MouseEvent e) {
				int s=listacat.getSelectionModel().getSelectedIndex();
				FieldPane desc=categoricas(s);
				g.add(desc.getChild(), 1, 1);
				if (usuario instanceof Administrador) {
					Button editar=new Button("Opciones");
					g.add(editar, 1, 2);
					editar.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							HBox info=new HBox();
							FieldPane l=categoricasNom(s);
							Button b1=new Button("Editar"); Button b2=new Button("Eliminar");
							info.getChildren().addAll(l.getChild(),b1,b2);
							g.add(info, 0, 3,2,1);info.setMaxWidth(Double.MAX_VALUE);
							b1.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {
									boolean[]Des= {true,true};
									l.setHabilitado(Des);
									Button save=new Button("Guardar cambios");
									info.getChildren().add(save);
									save.setOnAction(new EventHandler<ActionEvent>() {
										public void handle (ActionEvent e) {
											Administrador adm=(Administrador) usuario;
											adm.modificarNombreCategoria(s, l.getValue(0));
											adm.modificarDescripcionCategoria(s, l.getValue(1));
											mostrarCategorias();
										
										}
									});
								}
							});
							b2.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {
									Alert a=new Alert(AlertType.CONFIRMATION);
									a.setTitle("Eliminar producto");
									a.setHeaderText("¿Eliminar la categoria?");
									Optional<ButtonType>res=a.showAndWait();
									if (res.get()==ButtonType.OK) {
										categorias.remove(s);
									}
								}
							});
						}
					});
				}
			}
		});
		
				
		if (usuario==null) {
			principalInvitado.setCenter(cat);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(cat);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(cat);
		}
		
	}
	public static void mostrarProductos() {
		VBox prod=new VBox();
		ListView listaprod=new ListView();
		for (int k=0;k<productos.size();k++) {listaprod.getItems().add(productos.get(k).getNombre());}
		
		Label lis1=new Label("Mostrar Productos"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		g.add(new Label("Lista de de productos:"), 0, 0); g.add(new Label("Descripcion:"), 1, 0);
		g.add(listaprod,0, 1);
		prod.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		prod.setAlignment(Pos.TOP_CENTER);
		
		listaprod.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				int s=listaprod.getSelectionModel().getSelectedIndex();
				FieldPane f=productor(s);
				g.add(f.getChild(), 1, 1);
				Button opciones=new Button("Opciones");
				g.add(opciones, 1, 2);
				opciones.setOnAction(new EventHandler<ActionEvent>() {
					public void handle (ActionEvent e) {
						HBox info=new HBox();
						FieldPane l=productorNom(s);
						Button b1=new Button("Editar"); Button b2=new Button("Eliminar");
						info.getChildren().addAll(l.getChild(),b1,b2);
						g.add(info, 0, 3,2,1);info.setMaxWidth(Double.MAX_VALUE);
						b1.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								boolean[]Des= {true,true,true,true};
								l.setHabilitado(Des);
								Button save=new Button("Guardar cambios");
								info.getChildren().add(save);
								save.setOnAction(new EventHandler<ActionEvent>() {
									public void handle (ActionEvent e) {
										Administrador adm=(Administrador) usuario;
										adm.modificarNombreProducto(s,l.getValue(0));
										adm.modificarDescripcionProducto(s, l.getValue(1));
										adm.modificarPrecioCompra(s, Double.parseDouble(l.getValue(2)));
										adm.modificarPrecioVenta(s, Double.parseDouble(l.getValue(3)));
										mostrarProductos();
									
									}
								});
							}
						});
						b2.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								Alert a=new Alert(AlertType.CONFIRMATION);
								a.setTitle("Eliminar producto");
								a.setHeaderText("¿Eliminar el producto?");
								Optional<ButtonType>res=a.showAndWait();
								if (res.get()==ButtonType.OK) {
									String nom=productos.get(s).getNombre();
									int w=inventario.RealizarBusqueda(nom);
									productos.remove(s); inventario.DelInventario(w);
								}
							}
						});
					}
				});
			}
		});
		
		if (usuario==null) {
			principalInvitado.setCenter(prod);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(prod);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(prod);
		}
		
	}
				//Carro
	public static void MostrarCarro() {
		VBox car=new VBox();
		ListView carro=new ListView();
		Usuario usut=(Usuario) usuario; carro.getItems().addAll(usut.getCarro().getInventario());
		String[] criterios= {"Total de objetos:","Subtotal:"};
		String[] Valores=new String[2]; Valores[0]=Integer.toString(usut.getCarro().getNumObjetos());
		Valores[1]=Double.toString(usut.getCarro().getSubTotal());
		boolean[] Habilitado=new boolean[2]; for (int l=0;l<Habilitado.length;l++) {Habilitado[l]=false;} 
		FieldPane f=new FieldPane("",criterios,"",Valores,Habilitado);
		
		Label lis1=new Label("Carro"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		
		GridPane g=new GridPane(); g.setHgap(5);
		
		g.add(new Label("Productos añadidos:"), 0, 0); g.add(new Label("Subtotal:"), 1, 0);
		g.add(carro,0, 1); g.add(f.getChild(), 1, 1,2,1);
		Button Comprar=new Button("Comprar");
		g.add(Comprar, 1, 2);
		car.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		car.setAlignment(Pos.TOP_CENTER);
		Comprar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert a =new Alert(AlertType.CONFIRMATION);
				a.setTitle("Comprar");
				a.setHeaderText(usut.getCarro().GenerarFactura());
				a.setContentText("¿Finalizar compra?");
				Optional<ButtonType> res=a.showAndWait();
				if (res.get()==ButtonType.OK) {
					boolean b=usut.comprar();
					if (b==true) {
						Alert ex=new Alert(AlertType.INFORMATION);
						ex.setTitle("Compra exitosa");
						ex.setHeaderText("¡Exito en la compra!");
						ex.show();
						MostrarCarro();
					}
					else {
						Alert err=new Alert(AlertType.ERROR);
						err.setTitle("Error");
						err.setHeaderText("¡Saldo insuficiente!");
						err.show();
						
					}
				}
			}
		});
		
		carro.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				int j=carro.getSelectionModel().getSelectedIndex();
				Button eliminar= new Button("eliminar del carro");
				g.add(eliminar, 2, 2);
				eliminar.setOnAction(new EventHandler <ActionEvent>() {
					public void handle(ActionEvent e) {
						Alert a=new Alert(AlertType.CONFIRMATION);
						a.setTitle("Eliminar producto");
						a.setHeaderText("¿Eliminar el producto?");
						Optional<ButtonType>res=a.showAndWait();
						if (res.get()==ButtonType.OK) {
							usut.getCarro().DelProducto(j);
							MostrarCarro();

						}

					}
				});
			}
		});

		if (usuario==null) {
			principalInvitado.setCenter(car);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(car);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(car);
		}
		
	}
	public static void mostrarUsuarios() {
		VBox usu=new VBox();
		ListView listausu=new ListView();
		for (int k=0;k<Usuarios.size();k++) {listausu.getItems().add(Usuarios.get(k).getUsuario());}
		
		Label lis1=new Label("Mostrar usuarios"); lis1.setAlignment(Pos.TOP_CENTER); lis1.setPadding(new Insets(5));
		GridPane g=new GridPane();
		g.add(new Label("Lista de usuarios"), 0, 0);
		g.add(listausu, 0, 1);
		usu.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.TOP_CENTER);
		usu.setAlignment(Pos.TOP_CENTER);
		
		listausu.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				int s=listausu.getSelectionModel().getSelectedIndex();
				FieldPane f=stosMortales(s);
				g.add(f.getChild(), 1, 1);
				Button opciones=new Button("Eliminar usuario");
				g.add(opciones, 1, 2);
				opciones.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent w){
						Alert a=new Alert(AlertType.CONFIRMATION);
						a.setTitle("Eliminar usuario");
						a.setHeaderText("¿Eliminar el usuario?");
						Optional<ButtonType>res=a.showAndWait();
						if (res.get()==ButtonType.OK) {
							if (Usuarios.get(s).equals(usuario)) {
								Alert b=new Alert(AlertType.ERROR);
								b.setTitle("Error");
								b.setHeaderText("¡No puedes eliminarte a ti mismo!");
								b.show();
								mostrarUsuarios();
							}
							else {
							Usuarios.remove(s);
							mostrarUsuarios();
							}

						}
					}
				});
			}
		});
		if (usuario==null) {
			principalInvitado.setCenter(usu);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(usu);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(usu);
		}		
		
	}
				//crear

	public static void crearProducto() {
		
		VBox crearProducto = new VBox();
		Label titulo = new Label("Crear producto");
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setPadding(new Insets(5));
		String [] campos = new String[5];
		campos[0] = "Nombre";
		campos[1] = "Descripcion";
		campos[2] = "Precio original";
		campos[3] = "Precio de venta";
		campos[4] = "Categoria";
		String [] empty = new String[5];
		
		FieldPane columnas = new FieldPane(" ",campos, " ", empty, null);
		columnas.getChild().getChildren().remove(columnas.getBox(4));
		String[] categ = new String[categorias.size()];
		for(int i = 0; i<categorias.size(); i++) {
			categ[i]= categorias.get(i).getNombre();
		}

		ComboBox cats=new ComboBox(FXCollections.observableArrayList(categ));
		cats.setValue("Ninguno");
		cats.setPromptText("Categorias");
		columnas.getChild().add(cats, 2, 5);
		GridPane botones = new GridPane();
		Button salir = new Button("Salir");
		salir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				finalizar();
			}
		});
		Button crear = new Button("Crear");
		crear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String nombre = columnas.getValue(0);
				String descripcion = columnas.getValue(1);
				int oPrice = Integer.valueOf(columnas.getValue(2));
				int sPrice = Integer.valueOf(columnas.getValue(3));
				Producto productoCreado = new Producto(nombre,descripcion,oPrice,sPrice);
				productos.add(productoCreado);
				Alert info = new Alert(AlertType.INFORMATION);
				info.setHeaderText("Producto creado");
				info.setTitle("Información");
				info.show();
			}
			
		});
		botones.add(crear, 1, 0);
		botones.add(salir,0,0);
		botones.setPadding(new Insets(8,8,8,8));
		botones.setHgap(5);
		botones.setAlignment(Pos.TOP_CENTER);
		crearProducto.getChildren().addAll(titulo,columnas.getChild(),botones);
		crearProducto.setAlignment(Pos.TOP_CENTER);
		
		if (usuario==null) {
			principalInvitado.setCenter(crearProducto);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(crearProducto);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(crearProducto);
		}
	}
	public static void crearCategoria() {
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
		salir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				finalizar();
			}
		});
		botones.add(salir, 0, 0);
		botones.setPadding(new Insets(8,8,8,8));
		botones.setHgap(5);
		botones.setAlignment(Pos.TOP_CENTER);
		crearCategoria.getChildren().addAll(title,columnas.getChild(),botones);
		crearCategoria.setAlignment(Pos.TOP_CENTER);
		
		if (usuario==null) {
			principalInvitado.setCenter(crearCategoria);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(crearCategoria);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(crearCategoria);
		}
		
	}
	public static void crearUsuario() {
		VBox crearUsuario = new VBox();
		Label title = new Label("Crear Usuario");
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(5));
		String[] campos = new String [8];
		campos[0] = "Nombre";
		campos[1] = "Edad";
		campos[2] = "Genero (m/f)";
		campos[3] = "Usuario";
		campos[4]  = "Contrasena";
		campos[5] = "Pregunta de recuperacion";
		campos[6] = "Resupuesta";
		campos[7] = "Saldo";
		
		String [] empty = new String[8];
		
		FieldPane columnas = new FieldPane(" ",campos, " ", empty, null);
		GridPane botones = new GridPane();
		Button salir = new Button("Salir");
		salir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				finalizar();
			}
		});
		Button crear = new Button("Crear");
		crear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String nombre = columnas.getValue(0);
				boolean genero;
				int edad = Integer.valueOf(columnas.getValue(1));
				if(columnas.getValue(2).equals("m")){
					genero = true;
				}else {
					genero = false;
				}
				String usuario = columnas.getValue(2);
				String contrasena = columnas.getValue(3);
				String question = columnas.getValue(4);
				String answer = columnas.getValue(5);
				int saldo = Integer.valueOf(columnas.getValue(7)); 
				Usuario usuariocreado = new Usuario(nombre,genero,edad,usuario,contrasena,question,answer,saldo);
				Usuarios.add(usuariocreado);
				Alert info = new Alert(AlertType.INFORMATION);
				info.setHeaderText("Producto creado");
				info.setTitle("Información");
				info.show();
			}
		});
		botones.add(salir, 0, 0);
		botones.add(crear, 1, 0);
		botones.setPadding(new Insets(8,8,8,8));
		botones.setHgap(5);
		botones.setAlignment(Pos.TOP_CENTER);
		crearUsuario.getChildren().addAll(title,columnas.getChild(),botones);
		crearUsuario.setAlignment(Pos.TOP_CENTER);
		
		if (usuario==null) {
			principalInvitado.setCenter(crearUsuario);
		}else if(usuario instanceof Usuario) {
			principalUsuario.setCenter(crearUsuario);
		}else if(usuario instanceof Administrador) {
			principalAdministrador.setCenter(crearUsuario);
		}
	}
	
	
	public static void ayuda() {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText("Desarrollado por");
		info.setTitle("Acerca de");
		info.setContentText("David Andres Cano \n David Garcia Blandon \n Juan Pablo Buitrago Dias \n Julian Esteban Fernandez Montoya");
		info.show();
	}
	
	/** completar
	 * 
	 */
}
