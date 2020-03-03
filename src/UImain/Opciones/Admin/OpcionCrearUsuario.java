package UImain.Opciones.Admin;

import UImain.OpcionDeMenu;
import java.util.Scanner;
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
			System.out.println("Genero f/m");
			String input=in.next();
			boolean genero;
			if (input.equals("f")) {
				genero=false;
			}else {
				genero=true;
			}
			System.out.println("edad");
			int edad=in.nextInt();
			System.out.println("Nombre de usuario deseado");
			String usuario=in.next();
			System.out.println("Digite la contraseña");
			String contraseña=in.next();
			System.out.println("Escriba una pregunta de seguridad");
			String pregunta=in.next();
			System.out.println("digite la respuesta a la pregunta anterior");
			String respuesta=in.next();
			admon.CrearUsuario(nombre,genero,edad,usuario,contraseña,pregunta,respuesta);
		}
		in.close();
	}
	@Override
	public String toString() {
		return "Crear Usuarios";
	}

}
