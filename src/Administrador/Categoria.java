package Administrador;

public class Categoria {
	//Atributos
	private String nombre;
	private String descripcion;
	
	//Constructores
	Categoria(String Nom,String Des){
		this.nombre=Nom;
		this.descripcion=Des;
	}
	
	//Getters
	public String GetDescripcion() {
		return this.descripcion;
	}
	
	public String GetNombre() {
		return this.nombre;
	}
	
	//Setters
	void SetDescripcion(String d) {
		this.descripcion=d;
	}
		
	void SetNombre(String d) {
		this.nombre=d;
	}

}
