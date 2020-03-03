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
			System.out.println("Que roducto desea eliminar?");
			int x = input.nextInt();
			System.out.println("El producto: "+Main.inventario.getInventario().get(x).getProducto().getNombre()+" Sera completamente eliminado, esta seguro? S/N");
			String str1 = input.next();
			input.close();
			if(str1.equals("S")) {
				admon.eliminarDetalle(x);
			}else {
				System.out.println("Operacion Cancelada");
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
