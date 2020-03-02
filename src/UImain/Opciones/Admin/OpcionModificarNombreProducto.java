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
			System.out.println(i+". "+Main.productos.get(i) + "precio: "+Main.productos.get(i).getNombre());
		}
		System.out.println("Que nombre desea modificar?");
		int x = input.nextInt();
		System.out.println("Por cual desea remplazarlo?");
		String str1 = input.next();
		admon.modificarNombre(x, str1);;
	}
	public String toString() {
		return "MODIFICAR NOMBRE";
	}

}
