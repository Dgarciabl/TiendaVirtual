package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionModificarCategorias implements OpcionDeMenu {
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

			
			if(Main.productos.get(x).getCategoria().size()>0) {
				System.out.println("Categorias:");
				for(int i=0;i<(Main.productos.get(x).getCategoria()).size(); i++) {
					System.out.println(i + ". "+(Main.productos.get(x).getCategoriaIndividual(i).getNombre()));
					}
				System.out.println("Desea o eliminar[0] o agregar[1] categorias?");
				int c = input.nextInt();
				if(c==0){
					System.out.println("Que categoria del producto desea eliminar?");
					int y = input.nextInt();
					admon.delCategoriaProducto(x, Main.productos.get(x).getCategoriaIndividual(y));
				}else {
						System.out.println("Que categoria desea añadir?");
						for(int j=0;j<(Main.categorias.size());j++) {
							System.out.println(j + ". "+Main.categorias.get(j).getNombre());
						}
						int z = input.nextInt();
						admon.addCategoriaProducto(x, Main.categorias.get(z));
					}
			}else{
				System.out.println("El producto no cuenta con categorias");
				System.out.println("Desea agregar una nueva categoria al producto? S/N");
				String str1 = input.next();
				if(str1.equals("S")) {
					System.out.println("Que categoria desea añadir?");
					for(int j=0;j<(Main.categorias.size());j++) {
						System.out.println(j + ". "+Main.categorias.get(j).getNombre());
					}
					int z = input.nextInt();
					int h=0;
					for(int k=0; k<Main.categorias.size();k++){
						if((Main.categorias.get(z).getNombre()).equals(Main.categorias.get(k).getNombre())){
							h+=1;
						}
					}
					if(h>1) {
						System.out.println("La categoria ya existe");
					}else {
						admon.addCategoriaProducto(x, Main.categorias.get(z));
					}
				}
				
			}
		}else {
				System.out.println("El usuario no es valido");
			}
		}
	
	//cuando se elimina un producto busco en el inventario y elimino el detalle
	// al eliminar una categoria elimino esa categoria de todos los productos que la tengan
	@Override
	public String toString() {
		return "Modificar Categorias";
	}

}
