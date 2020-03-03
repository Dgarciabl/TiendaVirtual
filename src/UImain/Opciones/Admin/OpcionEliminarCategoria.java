package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionEliminar;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionEliminarCategoria implements OpcionEliminar {
	Scanner input = new Scanner(System.in);
	@Override
	public void ejecutar() {
		Administrador admon = (Administrador)Main.usuario;
		for(int i=0; i<Main.categorias.size();i++) {
			System.out.println(i+ ". "+Main.categorias.get(i).getNombre());
		}
		System.out.println("Que categoria desea eliminar?");
		int x = input.nextInt();
		System.out.println("La categoria: "+Main.categorias.get(x).getNombre()+" Sera completamente eliminada, esta seguro? S/N");
		String str1 = input.next();
		if(str1.equals("S")) {
			admon.eliminarCategoria(x);
		}else {
			System.out.println("Operacion Cancelada");
		}

	}
	public String toString() {
		return "ELIMINAR CATEGORIA";
	}

}
