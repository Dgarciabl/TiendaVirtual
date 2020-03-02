package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarCategorias implements OpcionModificar {
	Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		
		Administrador admon = (Administrador)Main.usuario;
		
		for(int i=0; i<Main.productos.size(); i++){
			System.out.println(i+". " + Main.productos.get(i).getNombre());
		}
		System.out.println("Que producto desea modificar?");
		int x = input.nextInt();
		System.out.println("Categorias:/n");
		
		for(int i=0;i<(Main.productos.get(x).getCategoria()).size(); i++) {
			System.out.println(i + ". "+(Main.productos.get(x).getCategoriaIndividual(i).getNombre()));
		}
		
		System.out.println("Que categoria del producto desea eliminar?");
		int y = input.nextInt();
		admon.delCategoriaProducto(x, Main.productos.get(x).getCategoriaIndividual(y));
		
		System.out.println("Desea agregar una nueva categoria al producto? S/N");
		String str1 = input.next();
		if(str1.equals("S")) {
			
		}
		
	}
	public String toString() {
		return "MODIFICAR CATEGORIAS";
	}

}
