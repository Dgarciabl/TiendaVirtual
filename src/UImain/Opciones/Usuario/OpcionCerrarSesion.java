package UImain.Opciones.Usuario;

import UImain.Main;
import UImain.OpcionDeMenu;

public class OpcionCerrarSesion implements OpcionDeMenu {
	@Override
	public void ejecutar() {
		Main.usuario=null;
		Main.nivel=2;
	}
	@Override
	public String toString() {
		return "Cerrar Sesion";
	}

}
