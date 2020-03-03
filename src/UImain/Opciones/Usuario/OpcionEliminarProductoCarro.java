package UImain.Opciones.Usuario;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Usuario.Usuario;

public class OpcionEliminarProductoCarro implements OpcionDeMenu {
	Scanner input = new Scanner(System.in);
	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Usuario) {
			Usuario us1 = (Usuario)Main.usuario;
			for(int i=0; i<us1.getCarro().getInventario().size(); i++) {
				System.out.println(i + ". "+us1.getCarro().getInventario().get(i));
			}
			System.out.println("Que producto desea eliminar del carro?");
			int x = input.nextInt();
			System.out.println("Esta seguro que desea eliminar "+us1.getCarro().getInventario().get(x)+" del carro? S/N");
			String str1 = input.next();
			if(str1.equals("S")) {
				us1.eliminarProducto(x);
			}else {
				System.out.println("Accion cancelada");
			}
		}else {
			System.out.println("No valido para usuario");
		}

	}
	
	@Override
	public String toString() {
		return "Eliminar Produco Carro";
	}

}
