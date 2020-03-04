package UImain.Opciones.Admin;

import java.util.Scanner;

import UImain.Main;
import UImain.OpcionDeMenu;
import UImain.Opciones.Usuario.OpcionCerrarSesion;
import gestorAplicacion.Administrador.Administrador;

public class OpcionEliminarOpcionAdministrador implements OpcionDeMenu {
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner in = new Scanner(System.in);
			Main.menu[0].lanzarMenu();
			System.out.println("Selecione el indice de la opcion que desea a�adir");
			int op=in.nextInt();
			if (Main.menu[0].opciones.get(op) instanceof OpcionCerrarSesion || 
					Main.menu[0].opciones.get(op) instanceof OpcionA�adirOpcionAdministrador) {
				System.out.println("Estas opciones no se pueden modificar");
			}else {
				Main.menu[0].eliminarOpcion(Main.menu[0].opciones.get(op));
			}
		}else {
			System.out.println("El tipo de usuario no es valido");
		}
	}
	@Override
	public String toString() {
		return "Eliminar opcion usuario";

	}

}
