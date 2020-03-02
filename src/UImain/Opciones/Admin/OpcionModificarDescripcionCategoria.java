package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarDescripcionCategoria implements OpcionModificar {
	Scanner input = new Scanner(System.in);
	@Override
	public void ejecutar() {
		
		Administrador admon = (Administrador)Main.usuario;
		for(int i=0; i<Main.categorias.size(); i++){
			System.out.println(i+". " + Main.categorias.get(i).getNombre());
		}
		System.out.println("Que categoria desea modificar?");
		int x = input.nextInt();
		System.out.println("Descripcion:/n");
		System.out.println(Main.categorias.get(x).getDescripcion());
		System.out.println("/nPor cual desea remplazarla?");
		String str1 = input.next();
		admon.modificarDescripcionCategoria(x, str1);
	}
	public String toString() {
		return "MODIFICAR DESCRIPCION PRODUCTO";
	}

}
