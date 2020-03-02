package UImain.Opciones.Admin;
import UImain.OpcionCreacion;
import UImain.Main;
import gestorAplicacion.Administrador.Producto;
import gestorAplicacion.Administrador.Inventario;
import gestorAplicacion.Administrador.Detalle;
import java.util.*;

public class OpcionCrearExistencias implements OpcionCreacion {

	public void ejecutar(){
		
		Scanner input = new Scanner(System.in);
		ArrayList<Producto> mostrar = new ArrayList<>();
		ArrayList<Integer> Indices = new ArrayList<>();
		int i = 0;
		
		for(Producto prod : Main.productos){
			i+=1;
			for(Detalle deta : Main.inventario.getInventario()){	
				if(!((deta.getProducto().getNombre()).equals(prod.getNombre()))){
					mostrar.add(prod);
					Indices.add(i);
				}
			}
		}
		
		for(int j=0; j<mostrar.size(); j+=1){
			System.out.println(Indices.get(j)+". "+mostrar.get(j).getNombre());
		}
		
		System.out.println("Escoja el indice del producto que desea a�adir:");
		int x = input.nextInt();
		System.out.println("Que cantidad desea a�adir");
		int y = input.nextInt();
		Detalle detail = new Detalle(Main.productos.get(Indices.get(x)), y);
		Main.inventario.getInventario().add(detail);
	
	}
}
