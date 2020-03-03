package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarPrecioCompraProducto implements OpcionDeMenu {
	Scanner input = new Scanner(System.in);
	@Override
	public void ejecutar() {
		
		
		if(Main.usuario instanceof Administrador) {
			Administrador admon = (Administrador)Main.usuario;
			for(int i=0; i<Main.productos.size(); i++){
				System.out.println(i+". " + Main.productos.get(i).getNombre());
			}
			System.out.println("Que producto desea modificar?");
			int x = input.nextInt();
			System.out.println("Precio original:/n");
			System.out.println(Main.productos.get(x).getPrecioCompra());
			System.out.println("/nPor cual desea remplazarlo?");
			double y = input.nextDouble();
			admon.modificarPrecioCompra(x, y);
		}else {
			System.out.println("No valido para usuario");
		}

	}
	
	@Override
	public String toString() {
		return "Modificar Precio de Compra";
	}
}
