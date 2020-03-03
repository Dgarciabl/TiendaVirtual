package UImain.Opciones.Admin;

import UImain.OpcionDeMenu;
import java.util.Scanner;
import gestorAplicacion.Administrador.*;
import UImain.Main;
public class OpcionEliminarUsuario implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner in=new Scanner(System.in);
		if(Main.usuario instanceof Administrador) {
			Administrador admon=(Administrador)Main.usuario;
			for(int i=0;i<Main.Usuarios.size();i++) {
				System.out.println(i+") "+Main.Usuarios.get(i).getUsuario());
			}
			System.out.println("Seleccione el usuario a eliminar por su indice");
			int op=in.nextInt();
			System.out.println("Confirme su seleccion s/n");
			System.out.println("Usuario: "+Main.Usuarios.get(op).toString());
			String sel=in.next();
			if (sel.equals("s")) {
				Main.Usuarios.remove(op);
			}else {
				System.out.println("operacion abortada");
			}
		}else {
			System.out.println("usuario no tiene permiso");
		}
		in.close();
	}
	@Override
	public String toString() {
		return "Eliminar Usuario";
	}
}
