package UImain.Opciones;

import UImain.Main;
import UImain.OpcionDeMenu;

public class OpcionMostrarProductos implements OpcionDeMenu {
	@Override
	public void ejecutar() {
		Main.usuario.mostrarProductos();
	}
}
