package personas.dto;

/**
 * clase PersonaDTO crea POJO para ser manipulados entre capas de informacion
 * Los DTO son objetos simples que no deben contener lógica de negocio que requiera pruebas generales.
 * DTO = data transfer object .
 * @param id_persona(int), nombre y apellido (String)
 * @version 1.0.0
 *
 * @author Figueras, Galarza, Gutierrez
 */
public class PersonaDTO {
    private int id_persona;
    private String nombre, apellido;
    
    /**
     * Constructores de la clase
     * @param: primer constructor vacio
     * @param: segundo constructor con argumento : Id del tipo INT
     * 
     */
    public PersonaDTO(){}
    
    public PersonaDTO(int id_persona){
        this.id_persona = id_persona;
    }
    /** 
     * Getters / Setters
     */
    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    /**
     * toString() imprimiendo contenido del objeto de ser necesario...
     */
    public String toString() {
        return "PersonaDTO{" + "id_persona=" + id_persona + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }

    public class jLabel1 {

        public jLabel1() {
        }
    }
        
    
}
