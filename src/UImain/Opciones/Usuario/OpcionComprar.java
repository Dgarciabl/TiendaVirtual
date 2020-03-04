package UImain.Opciones.Usuario;

import java.util.*;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Usuario.Usuario;

public class OpcionComprar implements OpcionDeMenu {
	Scanner input = new Scanner(System.in);
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Usuario) {
			
			Usuario us1 = (Usuario)Main.usuario;
			System.out.println(us1.getCarroToString());
			for(int i=0; i<us1.getCarro().getInventario().size();i++) {
				
				if((Main.inventario.getInventario().get(Main.inventario.getInventario().indexOf(us1.getCarro().getInventario().get(i)))).getCantidad() == 0){
					
					System.out.println("Se agotaron las existencias del Producto: "+ us1.getCarro().getInventario().get(i).getProducto().getNombre());
				}else if(Main.inventario.getInventario().get(Main.inventario.getInventario().indexOf(us1.getCarro().getInventario().get(i))).getCantidad() > 0){
					//Restar cantidad de inventario
					Main.inventario.getInventario().get(us1.getCarro().getInventario().indexOf(us1.getCarro().getInventario().get(i))).restarCantidad(us1.getCarro().getInventario().get(i).getCantidad());
					//Restar subtotal
					us1.reducirSaldo(us1.getCarro().getSubTotal());
;
					
				}
			}
			// Sacar Factura,,
			System.out.println(us1.getCarro().GenerarFactura());
			
			
		}else {
			System.out.println("No valido para usuario");
		}

	}
	public String toString() {
		return "Opcion Comprar";
	}

}
