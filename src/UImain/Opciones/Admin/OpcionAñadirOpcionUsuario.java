package UImain.Opciones.Admin;

import java.util.Scanner;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionAñadirOpcionUsuario implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner in = new Scanner(System.in);
			Main.full.lanzarMenu();
			System.out.println("Selecione el indice de la opcion que desea añadir");
			int op=in.nextInt();
			Main.menu[1].añadirOpcion(Main.full.opciones.get(op));
		}else {
			System.out.println("El tipo de usuario no es valido");
		}
	}
	@Override
	public String toString() {
		return "Añadir opcion usuario";
	}
}
