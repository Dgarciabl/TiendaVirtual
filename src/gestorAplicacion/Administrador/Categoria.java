package gestorAplicacion.Administrador;

public class Categoria {
	//Atributos
	private String nombre;
	private String descripcion;
	
	//Constructores
	public Categoria(String Nom,String Des){
		this.nombre=Nom;
		this.descripcion=Des;
	}
	
	//Getters
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	//Setters
	void setDescripcion(String d) {
		this.descripcion=d;
	}
		
	void setNombre(String d) {
		this.nombre=d;
	}
	public boolean equalsto(Categoria cat) {
		if(cat.getNombre().equals(this.nombre)&&cat.getDescripcion().equals(this.descripcion)) {
			return true;
		}else {return false;}
	}
	
	@Override
	public String toString() {
		String s=new String();
		s=s+"Nombre: "+this.nombre+"\n"+"Descripcion:\n"+this.descripcion;
		return s;
	}
}
