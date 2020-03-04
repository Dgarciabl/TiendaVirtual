package UImain.Opciones.Admin;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.*;
import java.util.Scanner;
public class OpcionA�adirOpcionInvitado implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner in = new Scanner(System.in);
			Main.full.lanzarMenu();
			System.out.println("Selecione el indice de la opcion que desea a�adir");
			int op=in.nextInt();
			Main.menu[2].a�adirOpcion(Main.full.opciones.get(op));
		}else {
			System.out.println("El tipo de usuario no es valido");
		}
	}
	@Override
	public String toString() {
		return "A�adir opcion invitado";
	}

}
