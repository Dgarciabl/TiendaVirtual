package UImain.Opciones.Admin;
import UImain.OpcionCreacion;
import UImain.Main;
import gestorAplicacion.Administrador.Producto;
import gestorAplicacion.Administrador.Inventario;
import gestorAplicacion.Administrador.Detalle;
import java.util.*;

public class OpcionCrearExistencias implements OpcionCreacion {

	public void ejecutar(){
//		buscar por cada producto en el inventario
//		los que estan en productos pero no en inventario,
//		y el admin escoge por indice y yo pregunto la cantidad y con esa cantidad 
//		modifico el un detalle con ese producto y esa cantidad.
		
		Scanner input = new Scanner(System.in);
		int i=0;
		
		for(Producto prod : Main.productos){
			
			for(Detalle deta : Main.inventario.getInventario()){	
				i+=1;
				if(deta.getProducto().getNombre() == prod.getNombre()){
					System.out.println(prod.getNombre()+"  "+i);
				}
			}			
		}
		System.out.println("Escoja el indice del producto que desea añadir:");
		int x = input.nextInt();
		System.out.println("Que cantidad desea añadir");
		int y = input.nextInt();
		
	}
}
