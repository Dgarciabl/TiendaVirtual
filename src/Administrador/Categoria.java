package Administrador;

public class Categoria {
	private String nombre;
	private String descripcion;
	
	
	public String GetDescripcion() {
		return this.descripcion;
	}
	
	void SetDescripcion(String d) {
		this.descripcion=d;
	}
	
	public String GetNombre() {
		return this.nombre;
	}
	
	void SetNombre(String d) {
		this.nombre=d;
	}

}
