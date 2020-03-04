package UImain.Opciones.Admin;

import java.util.Scanner;

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
		int x = input.nextInt();
		if(Main.productos.get(x).getCategoria().size()>0) {
			System.out.println(Persona.mostrarCategorias());
			System.out.println("Que categoria del producto desea eliminar?");
			int c = input.nextInt();
			//admon.delCategoriaProducto(x, Main.productos.get(x).getCategoriaIndividual(c));
			admon.delCategoriaProducto(x, Main.categorias.get(c));
		}else{
			System.out.println("El producto no cuenta con categorias");
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
