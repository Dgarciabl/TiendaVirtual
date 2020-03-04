package UImain.Opciones.Admin;

import java.util.Scanner;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import UImain.Opciones.Invitado.*;

public class OpcionEliminarOpcionInvitado implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner in = new Scanner(System.in);
			Main.menu[2].lanzarMenu();
			System.out.println("Selecione el indice de la opcion que desea añadir");
			int op=in.nextInt();
			if (Main.menu[2].opciones.get(op) instanceof OpcionInicioSesion || Main.menu[2].opciones.get(op) instanceof OpcionSalir) {
				System.out.println("Estas opciones no se pueden modificar");
			}else {
				Main.menu[2].eliminarOpcion(Main.menu[2].opciones.get(op));
			}
		}else {
			System.out.println("El tipo de usuario no es valido");
		}
	}
	@Override
	public String toString() {
		return "Eliminar opcion Invitado";
	}
}
