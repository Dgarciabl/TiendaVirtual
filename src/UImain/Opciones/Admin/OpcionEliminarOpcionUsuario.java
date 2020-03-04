package UImain.Opciones.Admin;

import java.util.*;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import UImain.Opciones.Invitado.*;
import UImain.Opciones.Usuario.*;
import UImain.Opciones.Admin.*;

public class OpcionEliminarOpcionUsuario implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner in = new Scanner(System.in);
			Main.menu[1].lanzarMenu();
			System.out.println("Selecione el indice de la opcion que desea añadir");
			try {
			int op=in.nextInt();
			if (Main.menu[1].opciones.get(op) instanceof OpcionCerrarSesion) {
				System.out.println("Estas opciones no se pueden modificar");
			}else {
				Main.menu[1].eliminarOpcion(Main.menu[1].opciones.get(op));
			}
			}
			catch (InputMismatchException e) {
				System.out.println("Error en el input, intente nuevamente");
			}
			catch (IndexOutOfBoundsException s) {
				System.out.println("El indice no esta en la lista");
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
