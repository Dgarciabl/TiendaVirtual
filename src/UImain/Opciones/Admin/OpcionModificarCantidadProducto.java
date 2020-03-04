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
			if(x<Main.inventario.getInventario().size()) {
				System.out.println("Cantidad Actual:");
				System.out.println(Main.inventario.getInventario().get(x).getCantidad());
				System.out.println("Cual es la nueva cantidad?");
				int y = input.nextInt();
				if(y>0) {
					admon.modificarCantidadProducto(x, y);
				}else {
					System.out.println("Debe ingresar una cantidad mayor que cero");
				}

			}else {
				System.out.println("Ingrese un numero valido");
			}
			

		}else {
			System.out.println("El usuario es invalido");
		}
	}
	@Override
	public String toString() {
		return "Modificar Cantidad de Producto";
	}

}
