package UImain.Opciones.Usuario;

import UImain.Main;
import UImain.OpcionDeMenu;

public class OpcionVerPerfil implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario != null) {
			System.out.println(Main.usuario.toString());
		}

	}
	@Override
	public String toString() {
		return "Ver perfil";
	}

}
