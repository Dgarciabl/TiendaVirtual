package UImain;
import java.io.*;
import java.lang.reflect.Type;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.*;
import java.util.function.Consumer;
import gestorAplicacion.Usuario.*;
import gestorAplicacion.Administrador.*;
import gestorAplicacion.Exepciones.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main extends Application {
	//App data
	public static int hojaActual;
	public static int imActual;
	public static int indice;
	public static int indice2;
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
		usuario = null;
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
	public static boolean isNumericDouble(String s) {
		try {
			Double.parseDouble(s);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	public static FieldPane productor(int j) {
		String[] criterios= {"Descripcion:","Categoria","Precio de compra:","Precio de venta:"};
		String[] valores=new String[4];
		valores[0]=productos.get(j).getDescripcion();
		valores[1]=productos.get(j).getCategoria().getNombre();
		valores[2]=Double.toString(productos.get(j).getPrecioCompra());
		valores[3]=Double.toString(productos.get(j).getPrecioVenta());
		boolean[] habilitado=new boolean[4];
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
		String[] Criterios= {"Descripcion:","Categoria","Precio:","Unidades disponibles:"};
		String[] Valores=new String[4];
		Valores[0]=inventario.getInventario(j).getProducto().getDescripcion();
		Valores[1]=inventario.getInventario(j).getProducto().getCategoria().getNombre();
		Valores[2]=Double.toString(inventario.getInventario(j).getProducto().getPrecioVenta());
		Valores[3]=Integer.toString(inventario.getInventario(j).getCantidad());
		boolean[] Hab=new boolean[4]; for (int l=0;l<Hab.length;l++) {Hab[l]=false;}
		FieldPane res=new FieldPane("",Criterios,"",Valores,Hab);
		res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return res;
		}
		else {
			String[] Criterios= {"Nombre:","Descripcion:","Categoria","Precio de compra:","Precio de venta:","Unidades disponibles:"};
			String[] Valores=new String[6];
			Valores[0]=inventario.getInventario(j).getProducto().getNombre();
			Valores[1]=inventario.getInventario(j).getProducto().getDescripcion();
			Valores[2]=inventario.getInventario(j).getProducto().getCategoria().getNombre();
			Valores[3]=Double.toString(inventario.getInventario(j).getProducto().getPrecioCompra());
			Valores[4]=Double.toString(inventario.getInventario(j).getProducto().getPrecioVenta());
			Valores[5]=Integer.toString(inventario.getInventario(j).getCantidad());
			boolean[] Hab=new boolean[6]; for (int l=0;l<Hab.length;l++) {Hab[l]=false;}
			FieldPane res=new FieldPane("",Criterios,"",Valores,Hab);
			res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			return res;
		}
	}
	public static FieldPane inventateEstaNoCat(int j) {
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
	public static FieldPane stosMortales(int j) {
		String[] Criterios= {"Nombre;","Edad:","Genero:","Nick:","Saldo:", "Pregunta recuperacion"};
		String[] Valores=new String[6];
		Valores[0]=Usuarios.get(j).getNombre();
		Valores[1]=Integer.toString(Usuarios.get(j).getEdad());
		if (Usuarios.get(j).getGenero()==true) {
			Valores[2]= "Masculino";
		}
		else {
			Valores[2]="Femenino";
		}
		Valores[3]=Usuarios.get(j).getUsuario();
		if (Usuarios.get(j) instanceof Usuario) {
			Usuario usut=(Usuario) Usuarios.get(j);
			Valores[4]=Double.toString((usut.getSaldo()));
		}
		else {
			Valores[4]="No tiene";
		}
		Valores[5]=Usuarios.get(j).getPregunta();
		boolean[] Hab=new boolean[6]; for (int i=0;i<Hab.length;i++) {Hab[i]=false;}
		FieldPane res=new FieldPane("",Criterios,"",Valores,Hab);
		res.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return res;
	}
	public static boolean InicioSesion(String usu, String key) throws FalloInicioSesion{
		Persona temp;
		for (int i=0;i<Usuarios.size();i++) {
			temp=Usuarios.get(i);
			if (usu.equals(temp.getUsuario())){
				if (temp.comprobarContrase�a(key)) {
					usuario = temp;
					if(usuario instanceof Administrador) {
						nivel = 0;
					}else {
						nivel = 1;
					}
					return true;
				}else {
					throw new FalloInicioSesion();
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
//Abajo
		Label[] hojaVida = new Label[4];
		//Texto
		hojaVida[0] = new Label( "Juan Pablo Buitrago D�az: 19 a�os \nAmantes de los juegos y salir con buena compa��a, \nEstudiante de tercer semestre ingenieria en sistemas");
		hojaVida[1] = new Label("David Andres Cano Gonzalez 19 a�os \nEstudiante de ingenieria de sistemas de 3er semestre \nBusca enfocarse en el campo de desarrollo de videojuegos");
		hojaVida[2] = new Label("Julian Fern�ndez Montoya \nEstudiante de Ingenieria de Sistemas de tercer semestre \nPractica nataci�n y le gusta aprender cosas nuevas");
		hojaVida[3] = new Label("David Garc�a Bland�n\nFecha Nacimiento: 08-05-1997(DD-MM-AAAA)\nEstudia Actualmente: Universidad Nacional de Colombia sede Medellin\ncursa: Ingenieria de Sistemas, 3er Semestre\nCurso el Bachillerato: Colegio Jorge Robledo, Colegio Fontan.\nAspiraciones: Ser investigador en temas de Inteligencia Artificial");
		hojaActual = 0;
		//Imagenes
		ImageView[] fotos = new ImageView[4];
		try {
			Image buitragoI = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\buitrago.jpg"));
			ImageView buitragoImg = new ImageView(buitragoI);
			buitragoImg.setFitHeight(130);
			buitragoImg.setFitWidth(100);
			fotos[0] = buitragoImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("Error");
			info.setTitle("No se pudo encontrar la imagen");
			info.setContentText("");
			info.show();
		}
		try {
			Image canoI = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\cano.jpg"));
			ImageView canoImg = new ImageView(canoI);
			canoImg.setFitHeight(130);
			canoImg.setFitWidth(100);
			fotos[1] = canoImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("Error");
			info.setTitle("No se pudo encontrar la imagen");
			info.setContentText("");
			info.show();
		}
		try {
			Image julianI = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\julian.jpg"));
			ImageView julianImg = new ImageView(julianI);
			julianImg.setFitHeight(130);
			julianImg.setFitWidth(100);
			fotos[2] = julianImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("Error");
			info.setTitle("No se pudo encontrar la imagen");
			info.setContentText("");
			info.show();
		}
		try {
			Image davidI = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\david.jpg"));
			ImageView davidImg = new ImageView(davidI);
			davidImg.setFitHeight(130);
			davidImg.setFitWidth(100);
			fotos[3] = davidImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("Error");
			info.setTitle("No se pudo encontrar la imagen");
			info.setContentText("");
			info.show();
		}
		HBox[] descripciones = new HBox[4];
		HBox buitrago = new HBox();
		buitrago.getChildren().addAll(fotos[0],hojaVida[0]);
		descripciones[0] = buitrago;
		HBox cano = new HBox();
		cano.getChildren().addAll(fotos[1],hojaVida[1]);
		descripciones[1] = cano;
		HBox julian = new HBox();
		julian.getChildren().addAll(fotos[2],hojaVida[2]);
		descripciones[2] = julian;
		HBox david = new HBox();
		david.getChildren().addAll(fotos[3],hojaVida[3]);
		descripciones[3] = david;
		mainPane.setBottom(descripciones[0]);
		hojaVida[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setBottom(descripciones[1]);
			}
		});
		hojaVida[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setBottom(descripciones[2]);
			}
		});
		hojaVida[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setBottom(descripciones[3]);
			}
		});
		hojaVida[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setBottom(descripciones[0]);
			}
		});
//Derecha
		VBox login = new VBox();
		Label title = new Label("");
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(5));
		String[] campos = new String [2];
		campos[0] = "Usuario";
		campos[1] = "Contrase�a";
		String [] empty = new String[2];
		
		FieldPane columnas = new FieldPane("",campos, "Iniciar Sesion", empty, null);
		GridPane botones = new GridPane();
		Button iniciarS = new Button("Iniciar Sesion");
		iniciarS.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String usu = columnas.getValue(0);
				String password = columnas.getValue(1);
				try {
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
				}catch(FalloInicioSesion fallo) {
					Alert info = new Alert(AlertType.ERROR);
					info.setHeaderText(fallo.getMessage());
					info.setTitle("Usuario o Contrase�a Invalido");
					info.show();
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
//Bienvenida
		VBox imms[] = new VBox[5];
		Label bienvenida = new Label("Bienvenido\na la\ntienda virtual");
		bienvenida.setTextAlignment(TextAlignment.CENTER);
		bienvenida.setFont(new Font("Arial",18));
		bienvenida.setTextFill(Color.BLUE);
		bienvenida.setMaxWidth(Double.MAX_VALUE);
		ImageView[] imagenes = new ImageView[5];
		try {
			Image uno = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\1.png"));
			ImageView unoImg = new ImageView(uno);
			unoImg.setFitHeight(130);
			unoImg.setFitWidth(100);
			imagenes[0] = unoImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("No se pudo encontrar la imagen");
			info.setTitle("Error");
			info.setContentText("");
			info.show();
		}
		try {
			Image dos = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\2.jpg"));
			ImageView dosImg = new ImageView(dos);
			dosImg.setFitHeight(130);
			dosImg.setFitWidth(100);
			imagenes[1] = dosImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("No se pudo encontrar la imagen");
			info.setTitle("Error");
			info.setContentText("");
			info.show();
		}
		try {
			Image tres = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\3.jpg"));
			ImageView tresImg = new ImageView(tres);
			tresImg.setFitHeight(130);
			tresImg.setFitWidth(100);
			imagenes[2] = tresImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("No se pudo encontrar la imagen");
			info.setTitle("Error");
			info.setContentText("");
			info.show();
		}
		try {
			Image cuatro = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\4.jpg"));
			ImageView cuatroImg = new ImageView(cuatro);
			cuatroImg.setFitHeight(130);
			cuatroImg.setFitWidth(100);
			imagenes[3] = cuatroImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("No se pudo encontrar la imagen");
			info.setTitle("Error");
			info.setContentText("");
			info.show();
		}
		try {
			Image cinco = new Image(new FileInputStream(System.getProperty("user.dir") + "\\src\\BaseDatos\\5.jpg"));
			ImageView cincoImg = new ImageView(cinco);
			cincoImg.setFitHeight(130);
			cincoImg.setFitWidth(100);
			imagenes[4] = cincoImg;
		} catch (FileNotFoundException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setHeaderText("No se pudo encontrar la imagen");
			info.setTitle("Error");
			info.setContentText("");
			info.show();
		}
		imms[0] = new VBox();
		imms[0].getChildren().addAll(bienvenida,imagenes[0]);
		imms[1] = new VBox();
		imms[1].getChildren().addAll(bienvenida,imagenes[1]);
		imms[2] = new VBox();
		imms[2].getChildren().addAll(bienvenida,imagenes[2]);
		imms[3] = new VBox();
		imms[3].getChildren().addAll(bienvenida,imagenes[3]);
		imms[4] = new VBox();
		imms[4].getChildren().addAll(bienvenida,imagenes[4]);
		mainPane.setLeft(imms[4]);
		imagenes[0].setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setLeft(imms[1]);
			}
		});
		imagenes[1].setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setLeft(imms[2]);
			}
		});
		imagenes[2].setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setLeft(imms[3]);
			}
		});
		imagenes[3].setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setLeft(imms[4]);
			}
		});
		imagenes[4].setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				mainPane.setLeft(imms[0]);
			}
		});
//General		
		mainPane.setRight(login);
		mainPane.setTop(menuPrincipal());
		Scene principal = new Scene(mainPane,400,400);
		sceneInicial = principal;
		
	}
	
	public static MenuBar menuPrincipal() {
		MenuBar mainMenu;
		Menu descripcion = new Menu("Descripci�n");
		MenuItem sistema = new Menu("Sistema");
		sistema.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Sistema");
				info.setHeaderText("Descripci�n del sistema");
				info.setContentText("El an�lisis que hicimos estuvo enfocado al modelo de una tienda virtual \nun sistema en el que un usuario pudiera buscar y ver productos \npara posteriormente comprarlos y as� mismo \ndeb�a existir un administrador con capacidad de manejar los productos y las opciones del usuario \nDebido a que se necesita un usuario y una cuenta para ingresar en el sistema \ntambi�n dise�amos una cuenta de invitado \nquien podr�a ver las caracter�sticas generales del sistema \npara luego acceder a ellas por medio de una cuenta de usuario.");
				info.show();
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
		Menu ayuda = new Menu("Ayuda");
		MenuItem contacto = new MenuItem("Contacto");
		contacto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Informaci�n");
				info.setHeaderText("Contacto");
				info.setContentText("Juan Pablo Buitrago Diaz: \njubuitrago@unal.edu.co \nDavid Andres Cano Gonzales: \ndcanogo@unal.edu.co \nJulian Esteban Fern�ndez Montoya: \njfernandezmo@unal.edu.co \nDavid Garcia Blandon: \ndgarciabl@unal.edu.co");
				info.show();	
			}
		});
		MenuItem recuperar = new MenuItem("Recuperar contrase�a");
		recuperar.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				TextInputDialog confirmacion=new TextInputDialog();
				confirmacion.setTitle("Opciones de recuperaci�n");
				confirmacion.setHeaderText("Recuperar contrase�a");
				confirmacion.setContentText("Usuario:");
				Optional<String> respuesta=confirmacion.showAndWait();
				respuesta.ifPresent(new Consumer<String>() {
					@Override
					public void accept(String t){
						try {
							String usName = respuesta.get();
							if(isNumeric(usName)) {
								throw new InputError();
							}
							boolean encontrado=false;
							for(int i=0; i<Usuarios.size();i++) {
								if(Usuarios.get(i).getUsuario().equals(usName)) {
									int user=i;
									TextInputDialog confirmacion=new TextInputDialog();
			        				confirmacion.setTitle("Opciones de recuperaci�n");
			        				confirmacion.setHeaderText("Pregunta de recuperaci�n: "+Usuarios.get(i).getPregunta());
			        				confirmacion.setContentText("Respuesta:");
			        				Optional<String> respuesta=confirmacion.showAndWait();
			        				respuesta.ifPresent(new Consumer<String>() {
										@Override
										public void accept(String t) {
											try {
												String answer = respuesta.get();
												if(answer==null || answer.isEmpty()) {
													throw new FormularioIncompletoError();
												}
												if(Usuarios.get(user).comprobarRespuesta(answer)) {
													TextInputDialog nuevaContrase�a=new TextInputDialog();
													nuevaContrase�a.setTitle("Recuperacion Exitosa");
													nuevaContrase�a.setHeaderText("La respuesta es correcta");
													nuevaContrase�a.setContentText("Nueva Contrase�a:");
							        				Optional<String> contrase�a=nuevaContrase�a.showAndWait();
							        				respuesta.ifPresent(new Consumer<String>() {
														@Override
														public void accept(String t) {
															String key = contrase�a.get();
															try {
																if(key==null || key.isEmpty()) {
																	throw new FormularioIncompletoError();
																}
																Usuarios.get(user).recuperarContrase�a(answer,key);
															}catch(FormularioIncompletoError e) {
																Alert erronea=new Alert(AlertType.ERROR);
																erronea.setHeaderText(e.getMessage());
																erronea.setContentText("Ingrese una contrase�a");
																erronea.show();
															}
															
														}
							        				});
												}else {
													Alert erronea=new Alert(AlertType.ERROR);
													erronea.setHeaderText("Respuesta Incorrecta");
													erronea.setContentText("Intente mas Tarde");
													erronea.show();
												}
											}catch(FormularioIncompletoError e) {
												Alert erronea=new Alert(AlertType.ERROR);
												erronea.setHeaderText(e.getMessage());
												erronea.setContentText("Ingrese una contrase�a");
												erronea.show();
											}
											
										}
			        				});
			        				encontrado=true;
								}
							}
							if (!encontrado) {
								Alert erronea=new Alert(AlertType.ERROR);
								erronea.setHeaderText("Usuario no Encontrado");
								erronea.setContentText("Intente mas Tarde");
								erronea.show();
							}
						}catch(InputError err) {
							Alert erronea=new Alert(AlertType.ERROR);
							erronea.setHeaderText(err.getMessage());
							erronea.setContentText("Input no puede ser numerico");
							erronea.show();
						}
						
					}
					
				});
			}
		});
		
		ayuda.getItems().addAll(contacto,recuperar);
		mainMenu = new MenuBar(descripcion,ayuda,salir);
		return mainMenu;
	}
		
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
				usuario = null;
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
				usuario = null;
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
				usuario = null;
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
		MenuItem mostrarCategorias = new MenuItem("Mostrar categorias");
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
				mainStage.setScene(sceneInicial);
				usuario = null;
			}
		});
		Button editar=new Button("Editar");
		Button a�adirSaldo=new Button("A�adir Saldo");
		Button Ccontrase�a=new Button ("Cambiar Contrase�a");
		Ccontrase�a.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				TextInputDialog con=new TextInputDialog();
				con.setTitle("Cambiar Contrase�a");
				con.setHeaderText("Digite su contrase�a actual");
				con.setContentText("Contrase�a actual");
				Optional<String>res=con.showAndWait();
				res.ifPresent(new Consumer<String>() {
					public void accept (String u) {
						if (usuario.comprobarContrase�a(res.get())==false) {
							Alert a=new Alert(AlertType.ERROR);
							a.setTitle("Error");
							a.setHeaderText("Contrase�a incorrecta");
							a.show();
						}
						else {
							TextInputDialog act=new TextInputDialog();
							act.setTitle("Cambiar Contrase�a");
							act.setHeaderText("Digite la nueva contrase�a");
							act.setContentText("Nueva contrase�a");
							Optional<String>res2=act.showAndWait();
							res2.ifPresent(new Consumer<String>() {
								public void accept(String e) {
									try {
										if (res2.get().equals("")) {
											throw new FormularioIncompletoError();
										}
										else {
									usuario.setContrase�a(res.get(), res2.get());
									Alert a=new Alert(AlertType.CONFIRMATION);
									a.setTitle("Cambiar Contrase�a");
									a.setHeaderText("Contrtase�a cambiada correctamente");
									a.show();
									archivo();
										}
									}
									catch (FormularioIncompletoError error) {
										Alert fallo=new Alert(AlertType.ERROR);
										fallo.setHeaderText(error.getMessage());
										fallo.setTitle("Fallo en inputs");
										fallo.show();
										archivo();
									}
								}
							});
						}
					}
				});
			}
		});
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
			presentacion=new FieldPane("Informaci�n", categorias, "Personal", valores, habilitado);
			a�adirSaldo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					TextInputDialog confirmacion=new TextInputDialog();
					confirmacion.setTitle("Confirmar Adicion");
					confirmacion.setHeaderText("Confirmar la cantidad a a�adir");
					confirmacion.setContentText("Cantidad:");
					Optional<String> respuesta=confirmacion.showAndWait();
					respuesta.ifPresent(new Consumer<String>() {
			            @Override 
			            public void accept(String user) {
			            	String saldo = respuesta.get();
							if(isNumeric(saldo)) {
								int saldoI = Integer.valueOf(saldo);
								if(saldoI>0){
									((Usuario) usuario).actualizarSaldo(saldoI);
									archivo();
								}else {
									Alert info = new Alert(AlertType.ERROR);
									info.setHeaderText("Debe Ingresar un valor mayor a cero");
									info.setTitle("Error");
									info.setContentText("");
									info.show();
								}
							}else {
								Alert info = new Alert(AlertType.ERROR);
								info.setHeaderText("Error");
								info.setTitle("Debe Ingresar numeros en este campo");
								info.setContentText("");
								info.show();
							}
			            	
			            }
			            });
					}
			});
			Botones.add(salir, 3, 0);
			Botones.add(editar, 0, 0);
			Botones.add(a�adirSaldo, 1, 0);
			Botones.add(Ccontrase�a, 2, 0);
		}else if (usuario instanceof Administrador) {
			titulo.setText("Perfil de Administrador");
			categorias=new String[] {"Nombre:","Edad:","Genero:"};
			if(usuario.getGenero()) {
				valores=new String[] {usuario.getNombre(),String.valueOf(usuario.getEdad()),"Masculino"};
			}else {
				valores=new String[] {usuario.getNombre(),String.valueOf(usuario.getEdad()),"Femenino"};
			}
			boolean[] habilitado=new boolean[] {false,false,false};
			presentacion=new FieldPane("Informaci�n", categorias, "Personal", valores, habilitado);
			Botones.add(salir, 2, 0);
			Botones.add(Ccontrase�a, 1, 0);
			Botones.add(editar, 0, 0);
		}else {
			titulo.setText("Perfil de Invitado");
			presentacion=new FieldPane("Informaci�n", new String[] {"Tienda Virtual",}, "Publica", new String[] {"Version 1"}, new boolean[] {false});
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
							archivo();
						}else if (presentacion.getValue(2).equals("Femenino")) {
							usuario.setGenero(false);
							archivo();
						}
						else {
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
							Button a�adir=new Button("A�adir al Carro");
							a�adir.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									TextInputDialog confirmacion=new TextInputDialog();
									confirmacion.setTitle("Confirmar Adicion");
									confirmacion.setHeaderText("Confirmar la cantidad a a�adir");
									confirmacion.setContentText("Cantidad:");
									Optional<String> respuesta=confirmacion.showAndWait();
									respuesta.ifPresent(new Consumer<String>() {
							            @Override public void accept(String user) {
							            	try {
							            	if (respuesta.get().equals("")) {
							            		throw new FormularioIncompletoError();
							            	}
							            	else if (isNumeric(respuesta.get())==false) {
							            		throw new InputError();
							            	}
							            	else {
							                ((Usuario) usuario).getCarro().AddInventario(new Detalle(inventario.getInventario(j).getProducto(),Integer.valueOf(respuesta.get())));
							                ((Usuario) usuario).getCarro().actualizar();
							            	}
							            	}
							            	catch (InputError error) {
												Alert fallo=new Alert(AlertType.ERROR);
												fallo.setHeaderText(error.getMessage());
												fallo.setTitle("Fallo en inputs");
												fallo.show();
												BuscarNombre();
							            	}
							            	catch(FormularioIncompletoError error2){
												Alert fallo=new Alert(AlertType.ERROR);
												fallo.setHeaderText(error2.getMessage());
												fallo.setTitle("Fallo en inputs");
												fallo.show();
												BuscarNombre();
											}
							            }
							        });
								}
							});
							resultado.add(a�adir, 1, 5);
							principal.setBottom(new VBox(resultado,mensaje));
						}else if(usuario instanceof Administrador) {
							Button elimExist=new Button("Eliminar del Inventario");
							Button eliminar=new Button("Eliminar producto");
							resultado.add(elimExist, 1, 5);
							resultado.add(eliminar, 2, 5);
							principal.setBottom(new VBox(resultado,mensaje));
							elimExist.setOnAction(new EventHandler<ActionEvent>() {
								public void handle (ActionEvent e) {
									Alert ale=new Alert(AlertType.CONFIRMATION);
									ale.setTitle("Eliminar inventario");
									ale.setHeaderText("�Eliminar producto del inventario?");
									Optional<ButtonType> res=ale.showAndWait();
									if (res.get()==ButtonType.OK) {
										inventario.DelInventario(j);
									}
								}
							});
							eliminar.setOnAction(new EventHandler<ActionEvent>() {
								public void handle (ActionEvent e) {
									Alert ale=new Alert(AlertType.CONFIRMATION);
									ale.setTitle("Eliminar Producto");
									ale.setHeaderText("�Eliminar producto?");
									Optional<ButtonType> res=ale.showAndWait();
									if (res.get()==ButtonType.OK) {
										inventario.DelInventario(j);
										productos.remove(j);
									}
								}
							});
						}
					}else {
						mensaje.setText("Producto no Encontrado");
						mensaje.setAlignment(Pos.TOP_CENTER);
						principal.setBottom(mensaje);
					}	
				}catch(FormularioIncompletoError error){
					Alert fallo=new Alert(AlertType.ERROR);
					fallo.setHeaderText(error.getMessage());
					fallo.setTitle("Fallo en inputs");
					fallo.show();
					BuscarNombre();
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
		ListView listaprod=new ListView();
		String[]categoriasT=new String[categorias.size()];
		for (int i=0;i<categoriasT.length;i++) {categoriasT[i]=categorias.get(i).getNombre();}
		Label titulo=new Label("Seleccione la Categoria:");
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setPadding(new Insets(5));
		GridPane buscador=new GridPane();
		buscador.setAlignment(Pos.TOP_CENTER);
		buscador.setPadding(new Insets(5));
		buscador.add(new Label("Seleccione la Categoria"), 0, 0);
		Button buscar=new Button("Buscar"); buscar.setAlignment(Pos.CENTER);
		buscar.setDisable(true); buscar.setVisible(false);
		Button a�adir=new Button("A�adir al carro"); a�adir.setAlignment(Pos.CENTER);
		a�adir.setDisable(true); a�adir.setVisible(false);
		ComboBox cats=new ComboBox(FXCollections.observableArrayList(categoriasT));
		cats.setPromptText("Categorias");
		
		cats.valueProperty().addListener(new ChangeListener<String>() {
			public void changed (ObservableValue s1,String s2,String s3) {
				a�adir.setDisable(true); a�adir.setVisible(false);
				listaprod.getItems().removeAll(listaprod.getItems());
				int ind=cats.getSelectionModel().getSelectedIndex();
				buscar.setDisable(false); buscar.setVisible(true);
				buscar.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {					
						ArrayList<Producto> pt=inventario.RealizarBusqueda(ind);
						listaprod.getItems().addAll(pt);
						principal.getChildren().set(3,listaprod);
						listaprod.setOnMouseClicked(new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								if (usuario instanceof Usuario) {
								int t=listaprod.getSelectionModel().getSelectedIndex();
								a�adir.setDisable(false); a�adir.setVisible(true);
								a�adir.setOnAction(new EventHandler<ActionEvent>() {
									public void handle (ActionEvent e) {
										TextInputDialog confirmacion=new TextInputDialog();
										confirmacion.setTitle("Confirmar Adicion");
										confirmacion.setHeaderText("Confirmar la cantidad a a�adir");
										confirmacion.setContentText("Cantidad:");
										Optional<String> respuesta=confirmacion.showAndWait();
										respuesta.ifPresent(new Consumer<String>() {
								            @Override public void accept(String user) {
								            	try {
								            		if (respuesta.get().equals("")) {
								            			throw new FormularioIncompletoError();
								            		}
								            		else if (isNumeric(respuesta.get())==false) {
								            			throw new InputError();
								            		}
								            		else {
								                ((Usuario) usuario).getCarro().AddProducto(new Detalle(pt.get(t),Integer.valueOf(respuesta.get())));
								                int bis=inventario.RealizarBusqueda(pt.get(t).getNombre());
								                inventario.getInventario(bis).restarCantidad(Integer.valueOf(respuesta.get()));
								                BuscarCategoria();
								            		}
								            	}
								            	catch (InputError error) {
													Alert fallo=new Alert(AlertType.ERROR);
													fallo.setHeaderText(error.getMessage());
													fallo.setTitle("Fallo en inputs");
													fallo.show();
													BuscarCategoria();
								            	}
								            	catch (FormularioIncompletoError error) {
													Alert fallo=new Alert(AlertType.ERROR);
													fallo.setHeaderText(error.getMessage());
													fallo.setTitle("Fallo en inputs");
													fallo.show();
													BuscarCategoria();
								            	}
								            }
								        });
									}
								});
								}
							}
						});
					}
				});
			}
		});
		principal.getChildren().addAll(titulo,cats,buscar,listaprod,a�adir);
		principal.setAlignment(Pos.TOP_CENTER);
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
			Button a�adir=new Button("A�adir al carro");
			g.add(a�adir, 1, 2);
			a�adir.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent e) {
					int j=listainv.getSelectionModel().getSelectedIndex();
					TextInputDialog confirmacion=new TextInputDialog();
					confirmacion.setTitle("Confirmar Adicion");
					confirmacion.setHeaderText("Confirmar la cantidad a a�adir");
					confirmacion.setContentText("Cantidad:");
					Optional<String> respuesta=confirmacion.showAndWait();
					respuesta.ifPresent(new Consumer<String>() {
			            @Override public void accept(String user) {
			            	try {
			            		if(isNumeric(respuesta.get())==false) {
			            			throw new InputError();
			            		}
			            		else {
			            			((Usuario) usuario).getCarro().AddProducto(new Detalle(inventario.getInventario().get(j).getProducto(),Integer.valueOf(respuesta.get())));
			            			inventario.getInventario().get(j).restarCantidad(Integer.valueOf(respuesta.get()));
			            			mostrarInventario();
			            		}
			            	}
			            	catch(InputError error) {
								Alert fallo=new Alert(AlertType.ERROR);
								fallo.setHeaderText(error.getMessage());
								fallo.setTitle("Fallo en inputs");
								fallo.show();
								mostrarInventario();
			            	}
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
							FieldPane l=inventateEstaNoCat(s);
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
											try {
												Administrador adm=(Administrador) usuario;
												Inventario invT=inventario;
												invT.getInventario().remove(s);
												if (l.getValue(0).equals("") || l.getValue(1).equals("") || l.getValue(2).equals("") || l.getValue(3).equals("") || l.getValue(4).equals("")) {
													throw new FormularioIncompletoError();
												}
												if (invT.RealizarBusqueda(l.getValue(0))!=-1) {
													throw new NombreDuplicado();
												}
												if (isNumeric(l.getValue(0))==true || isNumeric(l.getValue(1))==true) {
												throw new InputError();
												}
												else {
												adm.modificarNombreProducto(s,l.getValue(0));
												adm.modificarDescripcionProducto(s, l.getValue(1));
												if (isNumericDouble(l.getValue(2))==false || isNumericDouble(l.getValue(3))==false || isNumeric(l.getValue(4))==false) {
													throw new InputError();
												}
												else {
													adm.modificarPrecioCompra(s, Double.parseDouble(l.getValue(2)));
													adm.modificarPrecioVenta(s, Double.parseDouble(l.getValue(3)));
													adm.modificarCantidadProducto(s, Integer.parseInt(l.getValue(4)));
													mostrarInventario();
													}
												}
											}
											catch (InputError error) {
												Alert fallo=new Alert(AlertType.ERROR);
												fallo.setHeaderText(error.getMessage());
												fallo.setTitle("Fallo en inputs");
												fallo.show();
												mostrarInventario();
											}
											catch (FormularioIncompletoError error) {
												Alert fallo=new Alert(AlertType.ERROR);
												fallo.setHeaderText(error.getMessage());
												fallo.setTitle("Fallo en inputs");
												fallo.show();
												mostrarInventario();
											}
											catch (NombreDuplicado error) {
												Alert fallo=new Alert(AlertType.ERROR);
												fallo.setHeaderText(error.getMessage());
												fallo.setTitle("Nombre Duplicado");
												fallo.show();
												mostrarInventario();
											}
										
										}
									});
								}
							});
							b2.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {
									Alert a=new Alert(AlertType.CONFIRMATION);
									a.setTitle("Eliminar producto");
									a.setHeaderText("�Eliminar el producto del inventario?");
									Optional<ButtonType>res=a.showAndWait();
									if (res.get()==ButtonType.OK) {
										inventario.DelInventario(s);
										mostrarInventario();
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
			Button buscat=new Button("Busqueda por categoria");
		g.add(buscat, 1, 2);
		buscat.setOnAction(new EventHandler<ActionEvent>() {
			public void handle (ActionEvent e) {
				BuscarCategoria();
			}
		});
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
											try {
											Administrador adm=(Administrador) usuario;
											if (l.getValue(0).equals("") || l.getValue(1).equals("")) {
												throw new FormularioIncompletoError();
											}
											if (isNumeric(l.getValue(0))==true || isNumeric(l.getValue(1))==false) {
												throw new InputError();
											}
											adm.modificarNombreCategoria(s, l.getValue(0));
											adm.modificarDescripcionCategoria(s, l.getValue(1));
											mostrarCategorias();
											}
											catch (InputError error) {
												Alert fallo=new Alert(AlertType.ERROR);
												fallo.setHeaderText(error.getMessage());
												fallo.setTitle("Fallo en inputs");
												fallo.show();
												mostrarCategorias();											
											}
											catch (FormularioIncompletoError error) {
												Alert fallo=new Alert(AlertType.ERROR);
												fallo.setHeaderText(error.getMessage());
												fallo.setTitle("Fallo en inputs");
												fallo.show();
												mostrarCategorias();
											}
										
										}
									});
								}
							});
							b2.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent e) {
									Alert a=new Alert(AlertType.CONFIRMATION);
									a.setTitle("Eliminar producto");
									a.setHeaderText("�Eliminar la categoria?");
									Optional<ButtonType>res=a.showAndWait();
									if (res.get()==ButtonType.OK) {
										categorias.remove(s);
										mostrarCategorias();
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
				GridPane bott=new GridPane();
				bott.getChildren().removeAll(bott.getChildren());
				int s=listaprod.getSelectionModel().getSelectedIndex();
				FieldPane f=productor(s);
				g.add(f.getChild(), 1, 1,2,1);
				Button opciones=new Button("Opciones");
				Button a�adirexi=new Button("A�adir existencias");
				a�adirexi.setDisable(true); a�adirexi.setVisible(false);
				bott.add(opciones, 0, 0);
				if (inventario.RealizarBusqueda(productos.get(s).getNombre())==-1) {
					a�adirexi.setDisable(false); a�adirexi.setVisible(true);
					a�adirexi.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							Producto pidi=productos.get(s);
							TextInputDialog texto=new TextInputDialog();
							texto.setTitle("A�adir existencia");
							texto.setHeaderText("�Cuantas unidades desea a�adir?");
							texto.setContentText("Unidades:");
							Optional<String> res=texto.showAndWait();
							res.ifPresent(new Consumer<String>() {
								public void accept(String sid) {
									try {
									if (isNumeric(res.get())==false) {
										throw new InputError();
									}
									else {
										inventario.AddInventario(new Detalle(productos.get(s),Integer.parseInt(res.get())));
										mostrarProductos();
									}
									}
									catch (InputError error) {
										Alert fallo=new Alert(AlertType.ERROR);
										fallo.setHeaderText(error.getMessage());
										fallo.setTitle("Fallo en inputs");
										fallo.show();
										mostrarProductos();
									}
								}
							});
						}
					});
					bott.add(a�adirexi, 1, 0);
				}
				g.add(bott, 1, 2);
				g.impl_updatePeer();
				opciones.setOnAction(new EventHandler<ActionEvent>() {
					public void handle (ActionEvent e) {
						opciones.setDisable(true); opciones.setVisible(false);
						HBox info=new HBox();
						info.getChildren().removeAll(info.getChildren());
						FieldPane l=productorNom(s);
						Button b1=new Button("Editar"); Button b2=new Button("Eliminar"); Button b3=new Button("Modifcar categoria"); Button b4=new Button("Cancelar");
						info.getChildren().addAll(l.getChild(),b1,b2,b3,b4);
						g.add(info, 0, 3,2,1);info.setMaxWidth(Double.MAX_VALUE);
						b1.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								boolean[]Des= {true,true,true,true};
								l.setHabilitado(Des);
								Button save=new Button("Guardar cambios");
								info.getChildren().add(save);
								save.setOnAction(new EventHandler<ActionEvent>() {
									public void handle (ActionEvent e) {
										try {
											ArrayList<Producto>prodT=productos;
											prodT.remove(s);
											Administrador adm=(Administrador) usuario;
										if (l.getValue(0).equals("") || l.getValue(1).equals("") || l.getValue(2).equals("") || l.getValue(3).equals("")) {
											throw new FormularioIncompletoError();
										}
										for (int lel=0;lel<prodT.size();lel++) {
											if (prodT.get(lel).getNombre().equals(l.getValue(0))) {
												throw new NombreDuplicado();
											}
										}
										if (isNumeric(l.getValue(0))==true || isNumeric(l.getValue(1))==true) {
											throw new InputError();
										}
										else {
											adm.modificarNombreProducto(s,l.getValue(0));
											adm.modificarDescripcionProducto(s, l.getValue(1));
											if (isNumericDouble(l.getValue(2))==false || isNumericDouble(l.getValue(3))==false) {
												throw new InputError();
											}
											else {
												adm.modificarPrecioCompra(s, Double.parseDouble(l.getValue(2)));
												adm.modificarPrecioVenta(s, Double.parseDouble(l.getValue(3)));
												mostrarProductos();
												}
											}
										}
										catch (InputError error) {
											Alert fallo=new Alert(AlertType.ERROR);
											fallo.setHeaderText(error.getMessage());
											fallo.setTitle("Fallo en inputs");
											fallo.show();
											mostrarProductos();
										}
										catch (FormularioIncompletoError error) {
											Alert fallo=new Alert(AlertType.ERROR);
											fallo.setHeaderText(error.getMessage());
											fallo.setTitle("Fallo en inputs");
											fallo.show();
											mostrarProductos();
										}
										catch (NombreDuplicado error) {
											Alert fallo=new Alert(AlertType.ERROR);
											fallo.setHeaderText(error.getMessage());
											fallo.setTitle("Nombre duplicado");
											fallo.show();
											mostrarProductos();
										}
									
									}
								});
							}
						});
						b2.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								Alert a=new Alert(AlertType.CONFIRMATION);
								a.setTitle("Eliminar producto");
								a.setHeaderText("�Eliminar el producto?");
								Optional<ButtonType>res=a.showAndWait();
								if (res.get()==ButtonType.OK) {
									String nom=productos.get(s).getNombre();
									int w=inventario.RealizarBusqueda(nom);
									productos.remove(s); inventario.DelInventario(w);
								}
							}
						});
						b3.setOnAction(new EventHandler<ActionEvent>() {
							public void handle (ActionEvent e) {
								prod.getChildren().removeAll(prod.getChildren());
								info.getChildren().removeAll(info.getChildren());
								String[]categoriasT=new String[categorias.size()];
								for (int i=0;i<categoriasT.length;i++) {categoriasT[i]=categorias.get(i).getNombre();}
								ComboBox cats=new ComboBox(FXCollections.observableArrayList(categoriasT));
								cats.setPromptText("Categorias");
								
								Button save2=new Button ("Guardar");
								Button cancel=new Button ("cancelar");
								save2.setDisable(true); save2.setVisible(false);
								prod.getChildren().add(info);
								info.getChildren().addAll(cats,save2,cancel);
								cancel.setOnAction(new EventHandler<ActionEvent>() {
									public void handle (ActionEvent e) {
										mostrarProductos();
									}
								});
								cats.valueProperty().addListener(new ChangeListener<String>() {
									public void changed (ObservableValue s1,String s2,String s3) {
										int pi=cats.getSelectionModel().getSelectedIndex();
										save2.setDisable(false); save2.setVisible(true);
										save2.setOnAction(new EventHandler<ActionEvent>() {
											public void handle(ActionEvent e) {
												Administrador admT=(Administrador) usuario;
												admT.addCategoriaProducto(s, categorias.get(pi));
												mostrarProductos();
											}
										});
									}
								});
							}
						});
						b4.setOnAction(new EventHandler <ActionEvent>() {
							public void handle (ActionEvent e) {
								mostrarProductos();
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
		
		g.add(new Label("Productos a�adidos:"), 0, 0); g.add(new Label("Subtotal:"), 1, 0);
		g.add(carro,0, 1); g.add(f.getChild(), 1, 1,2,1);
		if (usut.getCarro().getInventario().size()>0) {
		Button Comprar=new Button("Comprar");
		g.add(Comprar, 1, 2);
		Comprar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert a =new Alert(AlertType.CONFIRMATION);
				a.setTitle("Comprar");
				a.setHeaderText(usut.getCarro().GenerarFactura());
				a.setContentText("�Finalizar compra?");
				Optional<ButtonType> res=a.showAndWait();
				if (res.get()==ButtonType.OK) {
					boolean b=usut.comprar();
					if (b==true) {
						Alert ex=new Alert(AlertType.INFORMATION);
						ex.setTitle("Compra exitosa");
						ex.setHeaderText("�Exito en la compra!");
						ex.show();
						MostrarCarro();
					}
					else {
						Alert err=new Alert(AlertType.ERROR);
						err.setTitle("Error");
						err.setHeaderText("�Saldo insuficiente!");
						err.show();
						
					}
				}
			}
		});
		}
		car.getChildren().addAll(lis1,g);
		g.setAlignment(Pos.CENTER);
		car.setAlignment(Pos.TOP_CENTER);
		
		carro.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				int j=carro.getSelectionModel().getSelectedIndex();
				if (usut.getCarro().getInventario().size()>0) {
				Button eliminar= new Button("eliminar del carro");
				g.add(eliminar, 2, 2);
				eliminar.setOnAction(new EventHandler <ActionEvent>() {
					public void handle(ActionEvent e) {
						Alert a=new Alert(AlertType.CONFIRMATION);
						a.setTitle("Eliminar producto");
						a.setHeaderText("�Eliminar el producto?");
						Optional<ButtonType>res=a.showAndWait();
						if (res.get()==ButtonType.OK) {
							usut.getCarro().DelProducto(j);
							MostrarCarro();

						}

					}
				});
				}
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
						a.setHeaderText("�Eliminar el usuario?");
						Optional<ButtonType>res=a.showAndWait();
						if (res.get()==ButtonType.OK) {
							if (Usuarios.get(s).equals(usuario)) {
								Alert b=new Alert(AlertType.ERROR);
								b.setTitle("Error");
								b.setHeaderText("�No puedes eliminarte a ti mismo!");
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
		String [] campos = new String[6];
		campos[0] = "Nombre";
		campos[1] = "Descripcion";
		campos[2] = "Precio original";
		campos[3] = "Precio de venta";
		campos[4] = "Categoria";
		campos[5] = "Cantidad";
		String [] empty = new String[6];
		
		FieldPane columnas = new FieldPane(" ",campos, " ", empty, null);
		columnas.getChild().getChildren().remove(columnas.getBox(4));
		String[] categ = new String[categorias.size()];
		for(int i = 0; i<categorias.size(); i++) {
			categ[i]= categorias.get(i).getNombre();
		}

		ComboBox cats=new ComboBox(FXCollections.observableArrayList(categ));
		cats.setValue("Ninguno");
		cats.setPromptText("Categorias");
		columnas.getChild().add(cats, 1, 5);
		GridPane botones = new GridPane();
		Button salir = new Button("Salir");
		salir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				archivo();
			}
		});
		Button crear = new Button("Crear");
		crear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert al = new Alert(AlertType.INFORMATION);
				try {
					if(columnas.getValue(2)==null || columnas.getValue(2).isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(columnas.getValue(3)==null || columnas.getValue(3).isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(columnas.getValue(5)==null || columnas.getValue(5).isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					String nombre = columnas.getValue(0);
					String descripcion = columnas.getValue(1);
					int oPrice = Integer.valueOf(columnas.getValue(2));
					int sPrice = Integer.valueOf(columnas.getValue(3));
					int cantidad = Integer.valueOf(columnas.getValue(5));

					for(int i=0;i<productos.size();i++) {
						if(productos.get(i).getNombre().equals(nombre)) {
							throw  new NombreDuplicado();

						}
					}
					if(nombre==null || nombre.isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(descripcion==null || descripcion.isEmpty()) {
						throw  new FormularioIncompletoError();
					}					
					Producto productoCreado = new Producto(nombre,descripcion,oPrice,sPrice,categorias.get(indice));
					productos.add(productoCreado);	
					Detalle detalleCreado = new Detalle(productos.get(indice2),cantidad);
					inventario.AddInventario(detalleCreado);
					al.setAlertType(AlertType.INFORMATION);
					al.setHeaderText("Producto creado");
					al.setTitle("Informaci�n");
					
				}catch(FormularioIncompletoError e2) {
					al.setAlertType(AlertType.ERROR);
					al.setHeaderText(e2.getMessage());
					al.setTitle("Formulario Incompleto");
				}catch (NumberFormatException e1){
					try {
						throw new InputError();
					}catch(InputError e4) {
						al.setAlertType(AlertType.ERROR);
						al.setHeaderText(e4.getMessage());
						al.setTitle("Input Error");
					}
				}catch(NombreDuplicado e3) {
					al.setAlertType(AlertType.ERROR);
					al.setHeaderText(e3.getMessage());
					al.setTitle("Nombre duplicado");
				}
				al.show();

				for(int i=0; i<6;i++) {
					columnas.getBox(i).clear();
				}
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
				archivo();
			}
		});
		Button crear = new Button("Crear");
		crear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert al = new Alert(AlertType.INFORMATION);
				try {
					String nombre = columnas.getValue(0);
					String descripcion = columnas.getValue(1);
					for(int i=0;i<productos.size();i++) {
						if(categorias.get(i).getNombre().equals(nombre)) {
							throw  new NombreDuplicado();
						}
					}
					if(nombre==null || nombre.isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(isNumeric(nombre)) {
						throw new InputError();
					}
					if(isNumeric(descripcion)) {
						throw new InputError();
					}
					if(descripcion ==null || descripcion.isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					Categoria categoriaCreada = new Categoria(nombre,descripcion);
					categorias.add(categoriaCreada);
					al.setHeaderText("Categoria creada");
					al.setTitle("Informaci�n");

				}catch(FormularioIncompletoError e2) {
					al.setAlertType(AlertType.ERROR);
					al.setHeaderText(e2.getMessage());
					al.setTitle("Formulario Incompleto");
				}catch (InputError e1){
					al.setAlertType(AlertType.ERROR);
					al.setHeaderText(e1.getMessage());
					al.setTitle("Input Error");
				}catch(NombreDuplicado e3) {
					al.setAlertType(AlertType.ERROR);
					al.setHeaderText(e3.getMessage());
					al.setTitle("Nombre duplicado");
				}
				al.show();
				for(int i=0; i<2;i++) {
					columnas.getBox(i).clear();
				}
			}
		});
		botones.add(salir, 0, 0);
		botones.add(crear, 1, 0);
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
		campos[6] = "Respuesta";
		campos[7] = "Saldo";
		
		String [] empty = new String[8];
		
		FieldPane columnas = new FieldPane(" ",campos, " ", empty, null);
		GridPane botones = new GridPane();
		Button salir = new Button("Salir");
		salir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				archivo();
			}
		});
		Button crear = new Button("Crear");
		crear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert al = new Alert(AlertType.INFORMATION);
				try {
					if(columnas.getValue(1)==null || columnas.getValue(1).isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(columnas.getValue(7)==null || columnas.getValue(7).isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					
					String nombre = columnas.getValue(0);
					int edad = Integer.valueOf(columnas.getValue(1));
					boolean genero;
					if(columnas.getValue(2).equals("m")){
						genero = true;
					}else {
						genero = false;
					}
					String usuario = columnas.getValue(3);
					String contrasena = columnas.getValue(3);
					String question = columnas.getValue(4);
					String answer = columnas.getValue(5);
					int saldo = Integer.valueOf(columnas.getValue(7));
					for(int i=0;i<Usuarios.size();i++) {
						if(Usuarios.get(i).getNombre().equals(nombre)) {
							throw  new NombreDuplicado();
						}
					}
					if(nombre==null || nombre.isEmpty()) {
						throw  new FormularioIncompletoError();
					}

					if(columnas.getValue(2)==null || columnas.getValue(2).isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(usuario==null || usuario.isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(contrasena==null || contrasena.isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(question==null || question.isEmpty()) {
						throw  new FormularioIncompletoError();
					}
					if(answer==null || answer.isEmpty()) {
						throw  new FormularioIncompletoError();
					}

					Usuario usuariocreado = new Usuario(nombre,genero,edad,usuario,contrasena,question,answer,saldo);
					Usuarios.add(usuariocreado);
					al.setHeaderText("Usuario creado");
					al.setTitle("Informaci�n");
				}catch(FormularioIncompletoError e2) {
					al.setAlertType(AlertType.ERROR);
					al.setHeaderText(e2.getMessage());
					al.setTitle("Formulario Incompleto");
				}catch (NumberFormatException e1){
					try {
						throw new InputError();
					}catch(InputError e4) {
						al.setAlertType(AlertType.ERROR);
						al.setHeaderText(e4.getMessage());
						al.setTitle("Input Error");
					}
				}catch(NombreDuplicado e3) {
					al.setAlertType(AlertType.ERROR);
					al.setHeaderText(e3.getMessage());
					al.setTitle("Nombre duplicado");
				}
				al.show();
				for(int i=0; i<=7;i++) {
					columnas.getBox(i).clear();
				}
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
