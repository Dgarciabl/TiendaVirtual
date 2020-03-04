package UImain.Opciones.Admin;

import java.util.*;
import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Administrador.Categoria;

public class OpcionAñadirCategoriasProducto implements OpcionDeMenu {
	Scanner input = new Scanner(System.in);
	
	@Override
	public void ejecutar() {
		
		if(Main.usuario instanceof Administrador) {
			Administrador admon = (Administrador)Main.usuario;
			
			System.out.println("Que producto desea modificar?");
			for(int i=0; i<Main.productos.size(); i++){
				System.out.println(i+". " + Main.productos.get(i).getNombre());
			}

			int x = input.nextInt();
			
			if(Main.productos.get(x).getCategoria().size()>0) {
				System.out.println("Categorias del producto :");
				for(int i=0;i<Main.productos.get(x).getCategoria().size(); i++) {
					System.out.println(i + ". "+(Main.productos.get(x).getCategoria().get(i).getNombre()));
					}
				System.out.println("Que categoria desea añadir?");
				for(int j=0;j<(Main.categorias.size());j++) {
					System.out.println(j + ". "+Main.categorias.get(j).getNombre());
				}
				int z = input.nextInt();
				Boolean boli= false;		
				for(int k=0; k<Main.productos.get(x).getCategoria().size();k++){
					Categoria kate = Main.productos.get(x).getCategoria().get(k);
					for(int l=0;l<Main.categorias.size();l++) {
						Categoria kaki = Main.categorias.get(l);
						if(kaki.equals(kate)) {
							boli=true;
						}
					}
				}
				if(boli) {
					System.out.println("La categoria ya existe");
				}else {
					admon.addCategoriaProducto(x, Main.categorias.get(z));
				}
			}
			else{
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
	@Override
	public String toString() {
		return "Añadir Categoria a Producto";
	}
}
