package UImain;

import gestorAplicacion.Usuario.*;
import java.util.*;
public class OpcionA�adirSaldo implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner in = new Scanner(System.in);
		if(Main.usuario instanceof Usuario) {
			Usuario user=(Usuario)Main.usuario;
			System.out.println("Que cantidad desea a�adir");
			double sal=in.nextDouble();
			if(sal>0) {
				user.actualizarSaldo(sal);
			}else {
				System.out.println("no es un monto valido");
			}
		}else {
			System.out.println("No es usuario valido");
		}
	}
	@Override
	public String toString() {
		return "A�adir saldo";
	}
}
