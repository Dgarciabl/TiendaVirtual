package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarPrecioCompraProducto implements OpcionModificar {
	Scanner input = new Scanner(System.in);
	@Override
	public void ejecutar() {
		
		Administrador admon = (Administrador)Main.usuario;
		for(int i=0; i<Main.productos.size(); i++){
			System.out.println(i+". "+Main.productos.get(i) + "precio: "+Main.productos.get(i).getPrecioCompra());
		}
		System.out.println("Que precio desea modificar?");
		int x = input.nextInt();
		System.out.println("Por cual desea remplazarlo?");
		double y = input.nextDouble();
		admon.modificarPrecioCompra(x, y);;
	}
	public String toString() {
		return "MODIFICAR PRECIO DE COMPRA";
	}
}
