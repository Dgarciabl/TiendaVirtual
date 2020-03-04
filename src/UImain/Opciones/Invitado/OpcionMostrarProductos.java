package UImain.Opciones.Invitado;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Usuario.Usuario;

public class OpcionMostrarProductos implements OpcionDeMenu {
	@Override
	public void ejecutar() {
		Main.usuario.mostrarProductos();
	}
	@Override
	public String toString(){
		return "Mostrar Productos";
	}
}
