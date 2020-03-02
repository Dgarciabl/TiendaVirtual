package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarCantidadProducto implements OpcionModificar {
	Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		
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
	}
	public String toString() {
		return "MODIFICAR CANTIDAD PRODUCTO";
	}

}
