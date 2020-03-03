package UImain.Opciones.Admin;
import UImain.*;
import java.util.*;

import gestorAplicacion.Administrador.Producto;

public class OpcionBuscarProducto implements OpcionDeMenu {
	
	
	public void ejecutar() {
		Scanner Ing=new Scanner(System.in);
		System.out.println("Ingrese el nombre del producto");
		String k=Ing.next();
		int j =Main.inventario.RealizarBusqueda(k);
		if (j!=-1) {
			System.out.println("Se ha encotrado el siguiente producto:");
			Producto Prod=Main.productos.get(j);
			System.out.println("Nombre: "+ Prod.getNombre());System.out.println("Descripcion: "+Prod.getDescripcion());
			System.out.println("Precio de compra: "+Prod.getPrecioCompra()); System.out.println("Precio de venta: "+ Prod.getPrecioVenta());
		}
		else {
			System.out.println("No se ha encontrado ningun producto");
			
		}
	}
	
	public String toString() {
		return "Busqueda por nombre";
	}

}
