package UImain.Opciones.Invitado;

import UImain.OpcionDeMenu;
import UImain.Main;
import java.util.*;

import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Administrador.Producto;
import gestorAplicacion.Usuario.Persona;

public class OpcionBuscarProducto implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner Ing= new Scanner(System.in);
		System.out.println("Ingrese el nombre del producto:");
		
			String k=Ing.next();
			int j=Main.inventario.RealizarBusqueda(k);
			Persona Per=Main.usuario;
			if (Per instanceof Administrador) {
				if (j!=-1) {
					System.out.println("Se ha encotrado el siguiente producto:");
					Producto Prod=Main.productos.get(j);
					System.out.println(Prod.toString()); System.out.println("Precio de compra: "+Prod.getPrecioCompra());
				}
				else {
					System.out.println("No se ha encontrado ningun producto");
					}	
			}
			else  {
				if (j!=-1) {
					System.out.println("Se ha encotrado el siguiente producto:");
					Producto Prod=Main.productos.get(j);
					System.out.println(Prod.toString());
				}
				else {
					System.out.println("No se ha encontrado ningun producto");
					}			
			}
			//Ing.close();

		}
	@Override
	public String toString(){
		return "Busqueda por nombre";
	}

}
