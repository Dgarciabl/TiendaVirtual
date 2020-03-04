package UImain.Opciones.Admin;
import java.util.*;

import UImain.Main;
import UImain.OpcionDeMenu;
import gestorAplicacion.Administrador.Administrador;
import gestorAplicacion.Administrador.Detalle;
import gestorAplicacion.Administrador.Producto;
public class OpcionCrearProductos implements OpcionDeMenu {
	private Scanner in = new Scanner(System.in);
	@Override
	public void ejecutar() {
		Administrador admon=(Administrador)Main.usuario;
		System.out.println("Nombre del Producto:");
		String nombre=in.next();
		if (Main.isNumeric(nombre)) {
			System.out.println("El nombre no puede ser un numero");
		}
		else {
			System.out.println("Descripcion del Producto:");
			String descripcion=in.next();
			if (Main.isNumeric(descripcion)) {
				System.out.println("El nombre no puede ser un numero");
			}
			else {
		
				try {
					System.out.println("Precio original:");
					double precioCompra=in.nextDouble();
					System.out.println("Precio de Venta:");
					double precioVenta=in.nextDouble();
					System.out.println("Cantidad: ");
					int cantidad = in.nextInt();
					admon.crearProducto(nombre, descripcion, precioCompra, precioVenta);
					Producto prod1 = new Producto(nombre, descripcion, precioCompra, precioVenta);
					Detalle deta = new Detalle(prod1,cantidad);
					Main.inventario.getInventario().add(deta);
				}
				catch(InputMismatchException e) {
					System.out.println("Error de input, intente nuevamente");
				}
			}
		}
	}
	@Override
	public String toString() {
		return "Crear Productos";
	}
}
