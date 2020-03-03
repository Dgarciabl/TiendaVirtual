package UImain.Opciones.Usuario;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Usuario.Usuario;

public class OpcionMostrarCarro implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Usuario) {
			Usuario us1 = (Usuario)Main.usuario;
			System.out.println(us1.getCarroToString());
		}else {
			System.out.println("No es usuario valido");
		}

	}
	
	@Override
	public String toString() {
		return "Ver Carro";
	}

}
