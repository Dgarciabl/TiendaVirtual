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
			try {
			int x = input.nextInt();
			System.out.println("La categoria: "+Main.categorias.get(x).getNombre()+" Sera completamente eliminada, esta seguro? S/N");
			String str1 = input.next();
			if (str1.equals("S") || str1.equals("N")) {
				//input.close();
				if(str1.equals("S")) {
					admon.eliminarCategoria(x);
				}else {
					System.out.println("Operacion Cancelada");
				}
				}
			else {
				System.out.println("Error al escribir confirmacion");
			}
			}
			catch (InputMismatchException e) {
				System.out.println("Error en input, intente nuevamente");
			}
			catch (IndexOutOfBoundsException s) {
				System.out.println("la categoria no existe");
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
