package UImain.Opciones.Admin;

import java.util.*;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionA�adirOpcionAdministrador implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner in = new Scanner(System.in);
			Main.full.lanzarMenu();
			System.out.println("Selecione el indice de la opcion que desea a�adir");
			try {
			int op=in.nextInt();
			Main.menu[0].a�adirOpcion(Main.full.opciones.get(op));
			}
			catch (InputMismatchException e) {
				System.out.println("Error en el input, intente nuevamente");
			}
			catch (IndexOutOfBoundsException s) {
				System.out.println("el indice no existe en la lista");
			}
		}else {
			System.out.println("El tipo de usuario no es valido");
		}
	}
	@Override
	public String toString() {
		return "A�adir opcion administrador";
	}

}
