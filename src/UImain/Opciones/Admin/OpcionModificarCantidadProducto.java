package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarCantidadProducto implements OpcionDeMenu {

	
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner input = new Scanner(System.in);
			Administrador admon = (Administrador)Main.usuario;
			for(int i=0; i<Main.inventario.getInventario().size(); i++){
				System.out.println(i+". " + Main.inventario.getInventario().get(i).getProducto().getNombre());
			}
			System.out.println("Que producto desea modificar?");
			int x = input.nextInt();
			System.out.println("Cantidad Actual:/n");
			System.out.println(Main.inventario.getInventario().get(x).getCantidad());
			System.out.println("Cual es la nueva cantidad?");
			int y = input.nextInt();
			admon.modificarCantidadProducto(x, y);
			input.close();
		}else {
			System.out.println("El usuario es invalido");
		}
	}
	@Override
	public String toString() {
		return "Modificar Cantidad Producto";
	}

}
