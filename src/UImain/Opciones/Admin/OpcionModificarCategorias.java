package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionModificar;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarCategorias implements OpcionModificar {
	Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		
		if(Main.usuario instanceof Administrador) {
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
				System.out.println("Que categoria desea añadir?");
				for(int j=0;j<(Main.categorias.size());j++) {
					System.out.println(j + ". "+Main.categorias.get(j).getNombre());
				}
				int z = input.nextInt();
				admon.addCategoriaProducto(x, Main.categorias.get(z));
			}else {
				System.out.println("El usuario no es valido");
			}
		}
	}
	
	@Override
	public String toString() {
		return "Modificar Categorias";
	}

}
