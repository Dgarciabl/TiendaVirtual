package UImain.Opciones.Admin;

import UImain.OpcionDeMenu;
import java.util.Scanner;
import UImain.Main;
import gestorAplicacion.Administrador.*;
public class OpcionMostrarUsuarios implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner in=new Scanner(System.in);
		if(Main.usuario instanceof Administrador) {
			for (int i = 0;i<Main.Usuarios.size();i++) {
				System.out.println(Main.Usuarios.get(i).toString());
			}
		}else {
			System.out.println("El tipo de usuario es invalido");
		}
	}
	@Override
	public String toString() {
		return "Mostrar Usuarios";
	}

}
