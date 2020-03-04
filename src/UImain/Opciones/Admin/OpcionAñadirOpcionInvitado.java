package UImain.Opciones.Admin;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.*;

import java.util.ArrayList;
import java.util.Scanner;
public class OpcionA�adirOpcionInvitado implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.usuario instanceof Administrador) {
			Scanner in = new Scanner(System.in);
			boolean print;
			ArrayList<Integer> dic=new ArrayList<Integer>();
			int k=0;
			for (int i=0;i<Main.full.opciones.size();i++) {
				print=true;
				for (int j=0;j<Main.menu[2].opciones.size();j++) {
					if(Main.full.opciones.get(i).getClass()==Main.menu[2].opciones.get(j).getClass()) {
						print=false;
					}
				}
				if(print) {
					dic.add(i);
					System.out.println(k+") "+Main.full.opciones.get(i));
					k++;
				}
			}
			System.out.println("Selecione el indice de la opcion que desea a�adir");
			int op=in.nextInt();
			Main.menu[2].a�adirOpcion(Main.full.opciones.get(dic.get(op)));
		}else {
			System.out.println("El tipo de usuario no es valido");
		}
	}
	@Override
	public String toString() {
		return "A�adir opcion invitado";
	}

}
