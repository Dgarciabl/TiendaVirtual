package UImain.Opciones.Invitado;

import UImain.OpcionDeMenu;
import UImain.Main;
import java.util.*;
import gestorAplicacion.Administrador.Producto;

public class OpcionBuscarProducto implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner Ing= new Scanner(System.in);
		System.out.println("Ingrese el nombre del producto:");
		
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
	@Override
	public String toString(){
		return "Busqueda por nombre";
	}

}
