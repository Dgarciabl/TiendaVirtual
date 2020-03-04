package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarDescripcionProducto implements OpcionDeMenu {
	private Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Administrador admon = (Administrador)Main.usuario;
			for(int i=0; i<Main.productos.size(); i++){
				System.out.println(i+". " + Main.productos.get(i).getNombre());
			}
			System.out.println("Que producto desea modificar?");
			int x = input.nextInt();
			System.out.println("Descripcion:");
			System.out.println(Main.productos.get(x).getDescripcion());
			System.out.println("Por cual desea remplazarla?");
			String str1 = input.next();
			admon.modificarDescripcionProducto(x, str1);
		}else {
			System.out.println("No valido para Usuario");
		}
	}
	
	@Override
	public String toString() {
		return "Modificar Descripcion Producto";
	}
}
