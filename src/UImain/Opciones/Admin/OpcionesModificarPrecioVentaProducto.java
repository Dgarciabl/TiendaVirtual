package UImain.Opciones.Admin;
import UImain.Main;
import java.util.*;
import UImain.Main;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Administrador.Producto;

public class OpcionesModificarPrecioVentaProducto implements OpcionModificar {
	private Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		
		Administrador admon = (Administrador)Main.usuario;
		for(int i=0; i<Main.productos.size(); i++){
			System.out.println(i+". "+Main.productos.get(i) + "precio: "+Main.productos.get(i).getPrecioVenta());
		}
		System.out.println("Que precio desea modificar?");
		int x = input.nextInt();
		System.out.println("Por que valor desea remplazarlo?");
		double y = input.nextDouble();
		admon.modificarPrecioVenta(x, y);
	}
	public String toString() {
		return "MODIFICAR PRECIO DE VENTA";
	}
}
