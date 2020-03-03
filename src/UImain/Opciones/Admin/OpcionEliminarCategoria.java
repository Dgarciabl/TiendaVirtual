package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionEliminarCategoria implements OpcionDeMenu {
	
	
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Administrador admon = (Administrador)Main.usuario;
			Scanner input = new Scanner(System.in);
			for(int i=0; i<Main.categorias.size();i++) {
				System.out.println(i+ ". "+Main.categorias.get(i).getNombre());
			}
			System.out.println("Que categoria desea eliminar?");
			int x = input.nextInt();
			System.out.println("La categoria: "+Main.categorias.get(x).getNombre()+" Sera completamente eliminada, esta seguro? S/N");
			String str1 = input.next();
			input.close();
			if(str1.equals("S")) {
				admon.eliminarCategoria(x);
			}else {
				System.out.println("Operacion Cancelada");
			}
		}else {
			System.out.println("Usuario Invalido");
		}
		
	}
	@Override
	public String toString() {
		return "Eliminar Categoria";
	}

}
