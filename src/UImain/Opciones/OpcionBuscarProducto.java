package UImain.Opciones;

import UImain.OpcionDeMenu;
import UImain.Main;
import java.util.*;
import gestorAplicacion.Administrador.Producto;

public class OpcionBuscarProducto implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner Ing= new Scanner(System.in);
		System.out.println("¿Que desea hacer?");
		System.out.println("(1) Buscar por nombre");
		System.out.println("(2) Buscar por categoria");
		int i=Ing.nextInt();
		
		if (i==1) {
			System.out.println("Escriba el nombre del producto");
			String k=Ing.next();
			int j=Main.inventario.RealizarBusqueda(k);
			if (j!=-1) {
				System.out.println("Se ha encotrado el siguiente producto:");
				Producto Prod=Main.productos.get(j);
				System.out.println("Nombre: "+ Prod.getNombre());System.out.println("Descripcion: "+Prod.getDescripcion());
				System.out.println("Precio: "+Prod.getPrecioVenta());
			}
			else {
				System.out.println("No se ha encontrado ningun producto");
			}
		}
		else if (i==2) {
			System.out.println("Escriba el nombre de la categoria");
			String l=Ing.next();
			int s=Main.categorias.indexOf(l);
			ArrayList<Producto> Reg=Main.inventario.RealizarBusqueda(s);
			if (Reg.size()!=0) {
				System.out.println("Se han encontrado los siguientes productos");
				for (int m=0;i<Reg.size();i++) {
					Producto h=Reg.get(m);
					System.out.println("Nombre: "+h.getNombre()); System.out.println("Descripcion: "+h.getDescripcion());
					System.out.println("Precio: "+h.getPrecioVenta()); System.out.println("--------------");
				}
			}
			else {
				System.out.println("No se han encontrado porductos");
			}
			
		}

	}

}
