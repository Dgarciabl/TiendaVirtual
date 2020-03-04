package UImain.Opciones.Invitado;

import java.util.*;

import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Administrador.Categoria;
import gestorAplicacion.Administrador.Producto;
import gestorAplicacion.Usuario.*;
import UImain.*;
public class OpcionBusqueda implements OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner Ing=new Scanner(System.in);
		System.out.println("Categorias:");
		ArrayList<Categoria> Lis=Main.categorias;
		for (int i=0;i<Lis.size();i++) {
			Categoria Te=Lis.get(i);
			System.out.println(i+":"+ Te.getNombre());
		}
		
		System.out.println("Escriba el indice de la categoria");
		int s=Ing.nextInt();
		try {
		ArrayList<Producto> Reg=Main.inventario.RealizarBusqueda(s);
		Persona Per=Main.usuario;
		
		if (Per instanceof Administrador) {
			if (Reg.size()!=0) {
				System.out.println("Se han encontrado los siguientes productos");
				for (int m=0;m<Reg.size();m++) {
					Producto h=Reg.get(m);
					System.out.println(m+1 + ") "+h.toString());System.out.println("Precio de compra: "+h.getPrecioCompra());  System.out.println("--------------");
				}
			}
			else {
				System.out.println("No se han encontrado porductos");
			}
	}
		else {
			if (Reg.size()!=0) {
				System.out.println("Se han encontrado los siguientes productos");
				for (int m=0;m<Reg.size();m++) {
					Producto h=Reg.get(m);
					System.out.println(m+1 + ") "+h.toString()); System.out.println("--------------");
				}
			}
			else {
				System.out.println("No se han encontrado porductos");
			}
			
		}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("El indice escrito no existe en la lista");
		}
		//Ing.close();

	}
	@Override
	public String toString(){
		return "Busqueda por categoria";
	}

}
