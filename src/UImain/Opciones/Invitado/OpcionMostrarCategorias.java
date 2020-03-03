package UImain.Opciones.Invitado;

import UImain.Main;
import UImain.OpcionDeMenu;

public class OpcionMostrarCategorias implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Main.usuario.mostrarCategorias();
	}
	@Override
	public String toString(){
		return "Mostrar Categorias";
	}
}
