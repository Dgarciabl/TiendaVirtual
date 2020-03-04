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
			us1.reducirSaldo(us1.getCarro().getSubTotal());
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
