package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarNombreCategoria implements OpcionDeMenu {
	Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		
		if(Main.usuario instanceof Administrador) {
			Administrador admon = (Administrador)Main.usuario;
			for(int i=0; i<Main.categorias.size(); i++){
				System.out.println(i+". " + Main.categorias.get(i).getNombre());
			}
			System.out.println("Que categoria desea modificar?");
			int x = input.nextInt();
			System.out.println("Por cual nombre desea remplazarlo?");
			String str1 = input.next();
			admon.modificarNombreCategoria(x, str1);
		}else {
			System.out.println("No valido para usuario");
		}

	}
	
	@Override
	public String toString() {
		return "Modificar Nombre Categoria";
	}


}
