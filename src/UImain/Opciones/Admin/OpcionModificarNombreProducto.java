package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarNombreProducto implements OpcionModificar {
	Scanner input = new Scanner(System.in);
	@Override
	public void ejecutar() {
		
		Administrador admon = (Administrador)Main.usuario;
		for(int i=0; i<Main.productos.size(); i++){
			System.out.println(i+". " + Main.productos.get(i).getNombre());
		}
		System.out.println("Que producto desea modificar?");
		int x = input.nextInt();
		System.out.println("Por cual nombre desea remplazarlo?");
		String str1 = input.next();
		admon.modificarNombreProducto(x, str1);
	}
	public String toString() {
		return "MODIFICAR NOMBRE PRODUCTO";
	}

}
