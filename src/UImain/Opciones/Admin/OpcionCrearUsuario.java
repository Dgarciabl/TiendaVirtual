package UImain.Opciones.Admin;

import UImain.OpcionDeMenu;
import java.util.*;
import UImain.Main;
import gestorAplicacion.Administrador.*;
public class OpcionCrearUsuario implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner in=new Scanner(System.in);
		if(Main.usuario instanceof Administrador) {
			Administrador admon=(Administrador)Main.usuario;
			System.out.println("Nombre del cliente");
			String nombre=in.next();
			if (Main.isNumeric(nombre)) {
				System.out.println("El nombre no puede ser un numero");
			}
			else {
			System.out.println("Genero f/m");
			String input=in.next();
			if (input.equals("f") || input.equals("m")) {							
				boolean genero;
				if (input.equals("f")) {
					genero=false;
				}else {
					genero=true;
				}
				System.out.println("edad");
				try {
				int edad=in.nextInt();
				System.out.println("Nombre de usuario deseado");
				String usuario=in.next();
				if (Main.isNumeric(usuario)) {
					System.out.println("El usuario no puede ser un numero");
				}
				else {
				System.out.println("Digite la contraseña");
				String contraseña=in.next();
				System.out.println("Escriba una pregunta de seguridad");
				String pregunta=in.next();
				if (Main.isNumeric(pregunta)) {
					System.out.println("La pregunta no puede ser un numero");
				}
				else {
					System.out.println("digite la respuesta a la pregunta anterior");
					String respuesta=in.next();
					if (Main.isNumeric(respuesta)) {
						System.out.println("La respuesta no puede ser un numero");
					}
					else {			
						admon.CrearUsuario(nombre,genero,edad,usuario,contraseña,pregunta,respuesta);
						}
						}
					}
				}
				
				catch(InputMismatchException e) {
					System.out.println("Error en el input, intente nuevamente");
				}
			}
			
			else {
				System.out.println("El genero es incorrecto");
			
			}
			}
		}
		//in.close();
	}
	@Override
	public String toString() {
		return "Crear Usuarios";
	}

}
