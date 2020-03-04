package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionEliminarDetalle implements OpcionDeMenu {
	
	@Override
	public void ejecutar() {
		
		if(Main.usuario instanceof Administrador) {
			Scanner input = new Scanner(System.in);
			Administrador admon = (Administrador)Main.usuario;
			for(int i=0; i<Main.inventario.getInventario().size();i++) {
				System.out.println(i+ ". "+Main.inventario.getInventario().get(i).getProducto().getNombre());
			}
			System.out.println("Que producto desea eliminar?");
			try {
			int x = input.nextInt();
			System.out.println("El producto: "+Main.inventario.getInventario().get(x).getProducto().getNombre()+" Sera completamente eliminado, esta seguro? S/N");
			String str1 = input.next();
			if (str1.equals("S") || str1.equals("N")) {				
				//input.close();
				if(str1.equals("S")) {
					admon.eliminarDetalle(x);
				}else {
					System.out.println("Operacion Cancelada");
				}
				}
			else {
				System.out.println("Error en la confirmacion");
			}
			}
			catch (InputMismatchException e) {
				System.out.println("Error en input, intentelo nuevamente");
			}
			catch (IndexOutOfBoundsException s) {
				System.out.println("El detalle no existe");
			}
		}else {
			System.out.println("Usuario Invalido");
		}
	}
	
	@Override
	public String toString() {
		return "Eliminar Detalle";
	}

}
