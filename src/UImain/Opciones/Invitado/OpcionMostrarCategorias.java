package UImain.Opciones.Invitado;

import UImain.OpcionDeMenu;
import gestorAplicacion.Usuario.*;
public class OpcionMostrarCategorias implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Persona.mostrarCategorias();
	}
	@Override
	public String toString(){
		return "Mostrar Categorias";
	}
}
