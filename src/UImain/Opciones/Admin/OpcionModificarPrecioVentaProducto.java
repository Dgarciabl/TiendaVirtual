package UImain.Opciones.Admin;
import UImain.Main;
import java.util.*;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarPrecioVentaProducto implements OpcionDeMenu {
	private Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Administrador admon = (Administrador)Main.usuario;
			for(int i=0; i<Main.productos.size(); i++){
				System.out.println(i+". " + Main.productos.get(i).getNombre());
			}
			System.out.println("Que producto desea modificar?");
			try {
			int x = input.nextInt();
			System.out.println("Precio original:");
			System.out.println(Main.productos.get(x).getPrecioVenta());
			System.out.println("Por que valor desea remplazarlo?");
			double y = input.nextDouble();
			admon.modificarPrecioVenta(x, y);
			}
			catch (InputMismatchException e) {
				System.out.println("Error en el input, intente nuevamente");
			}
			catch (IndexOutOfBoundsException s) {
				System.out.println("El indice no existe en la tabla");
			}
		}else {
			System.out.println("No valido para usuario");
		}

	}
	
	@Override
	public String toString() {
		return "Modificar Precio de Venta";
	}
}
