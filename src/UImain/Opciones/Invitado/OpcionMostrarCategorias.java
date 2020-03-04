package UImain.Opciones.Invitado;

import UImain.OpcionDeMenu;
import gestorAplicacion.Usuario.*;
public class OpcionMostrarCategorias implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		System.out.println(Persona.mostrarCategorias());
	}
	@Override
	public String toString(){
		return "Mostrar Categorias";
	}
}
