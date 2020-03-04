package UImain.Opciones.Admin;

import java.util.Scanner;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;

public class OpcionEliminarCategoriasProducto implements OpcionDeMenu {
	
	public void ejecutar() {
	Scanner input = new Scanner(System.in);
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
			System.out.println("Que categoria del producto desea eliminar?");
			int c = input.nextInt();
			admon.delCategoriaProducto(x, Main.productos.get(x).getCategoriaIndividual(y));
			
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
				admon.addCategoriaProducto(x, Main.categorias.get(z));
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
		return "Añadir Categoria a Producto";
	}

}
