package UImain.Opciones;

import UImain.Main;
import UImain.OpcionDeMenu;

public class OpcionMostrarCategorias implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Main.usuario.mostrarCategorias();
	}

}
