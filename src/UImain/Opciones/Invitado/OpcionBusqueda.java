package UImain.Opciones.Invitado;

import java.util.*;

import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Producto;
import UImain.*;
public class OpcionBusqueda implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner Ing=new Scanner(System.in);
		System.out.println("Escriba el nombre de la categoria");
		String l=Ing.next();
		int s=Main.categorias.indexOf(l);
		ArrayList<Producto> Reg=Main.inventario.RealizarBusqueda(s);
		if (Reg.size()!=0) {
			System.out.println("Se han encontrado los siguientes productos");
			for (int m=0;m<Reg.size();m++) {
				Producto h=Reg.get(m);
				System.out.println("Nombre: "+h.getNombre()); System.out.println("Descripcion: "+h.getDescripcion());
				System.out.println("Precio: "+h.getPrecioVenta()); System.out.println("--------------");
			}
		}
		else {
			System.out.println("No se han encontrado porductos");
		}

	}
	@Override
	public String toString(){
		return "busqueda por categoria";
	}

}
