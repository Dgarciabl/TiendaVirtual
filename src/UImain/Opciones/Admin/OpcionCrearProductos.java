package UImain.Opciones.Admin;
import java.util.Scanner;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
public class OpcionCrearProductos implements OpcionDeMenu {
	private Scanner in = new Scanner(System.in);
	@Override
	public void ejecutar() {
		Administrador admon=(Administrador)Main.usuario;
		System.out.println("Nombre del Producto:");
		String nombre=in.next();
		System.out.println("Descripcion del Producto:");
		String descripcion=in.next();
		System.out.println("Precio original:");
		double precioCompra=in.nextDouble();
		System.out.println("Precio de Venta:");
		double precioVenta=in.nextDouble();
		admon.crearProducto(nombre, descripcion, precioCompra, precioVenta);
	}
	@Override
	public String toString() {
		return "Crear Productos";
	}
}
