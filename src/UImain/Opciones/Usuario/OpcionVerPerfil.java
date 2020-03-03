package UImain.Opciones.Usuario;

import UImain.Main;
import UImain.OpcionDeMenu;

public class OpcionVerPerfil implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		System.out.println(Main.usuario.toString());

	}

}
