package UImain.Opciones.Invitado;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Usuario.Usuario;

public class OpcionMostrarProductos implements OpcionDeMenu {
	@Override
	public void ejecutar() {
		if(Main.usuario!=null) {
			System.out.println(Main.inventario.toString());
		}else {
			System.out.println("-------------------------------------\nProductos:\n-------------------------------------\n"+Main.inventario.toString());
		}
	}
	@Override
	public String toString(){
		return "Mostrar Productos";
	}
}
