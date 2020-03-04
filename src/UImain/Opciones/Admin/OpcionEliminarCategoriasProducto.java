package UImain.Opciones.Admin;

import java.util.*;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Usuario.Persona;

public class OpcionEliminarCategoriasProducto implements OpcionDeMenu {
	
	public void ejecutar() {
	Scanner input = new Scanner(System.in);
	if(Main.usuario instanceof Administrador) {
		Administrador admon = (Administrador)Main.usuario;
		for(int i=0; i<Main.productos.size(); i++){
			System.out.println(i+". " + Main.productos.get(i).getNombre());
		}
		System.out.println("Que producto desea modificar?");
		try {
		int x = input.nextInt();
		if(Main.productos.get(x).getCategoria().size()>0) {
			Persona.mostrarCategorias();
			System.out.println("Que categoria del producto desea eliminar?");
			int c = input.nextInt();
			//admon.delCategoriaProducto(x, Main.productos.get(x).getCategoriaIndividual(c));
			admon.delCategoriaProducto(x, Main.categorias.get(c));
		
		}else{
			System.out.println("El producto no cuenta con categorias");
		}
		}
		catch (InputMismatchException w) {
			System.out.println("Error en el input, intente nuevamente");
		}
		catch (IndexOutOfBoundsException s) {
			System.out.println("El indice no esta en la lista");
		}
		
	}else {
		System.out.println("El usuario no es valido");
	}
}
	@Override
	public String toString() {
		return "Eliminar categoria de Producto";
	}

}
