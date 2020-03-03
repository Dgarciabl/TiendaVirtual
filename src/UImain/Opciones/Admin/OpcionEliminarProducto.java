package UImain.Opciones.Admin;

import java.util.*;

import UImain.Main;
import UImain.OpcionEliminar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionEliminarProducto implements OpcionEliminar {
	Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Administrador admon = (Administrador)Main.usuario;
			for(int i=0; i<Main.productos.size();i++) {
				System.out.println(i+ ". "+Main.productos.get(i).getNombre());
			}
			System.out.println("Que producto desea eliminar?");
			int x = input.nextInt();
			System.out.println("El producto: "+Main.productos.get(x).getNombre()+" Sera completamente eliminado, esta seguro? S/N");
			String str1 = input.next();
			if(str1.equals("S")) {
				admon.eliminarProducto(x);
			}else {
				System.out.println("Operacion Cancelada");
			}
		}else {
			System.out.println("Usuario Invalido");
		}
	}
	
	@Override
	public String toString() {
		return "Eliminar ProductoO";
	}

}