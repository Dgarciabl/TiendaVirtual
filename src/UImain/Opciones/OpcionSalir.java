package UImain.Opciones;

import UImain.OpcionDeMenu;

public class OpcionSalir implements OpcionDeMenu {
	//Abstract methods
	@Override
	public void ejecutar() {
		//guardar base de datos
		System.exit(0);
	}
	@Override
	public String toString() {
		return "Salir?";
	}
}
