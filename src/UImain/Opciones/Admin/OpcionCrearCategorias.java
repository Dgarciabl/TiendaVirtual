package UImain.Opciones.Admin;
import UImain.Main;
import java.util.Scanner;
import UImain.OpcionCreacion;
import gestorAplicacion.Administrador.Administrador;

public class OpcionCrearCategorias implements OpcionCreacion {
	private Scanner in=new Scanner(System.in);
	@Override
	public void ejecutar() {
		Administrador admon=(Administrador)Main.usuario;
		System.out.println("Nombre de la Categoria:");
		String nombre=in.next();
		System.out.println("Descripcion de la Categoria:");
		String descripcion=in.next();
		admon.crearCategoria(nombre, descripcion);
	}
	@Override
	public String toString() {
		return "Crear Categorias";
	}

}
