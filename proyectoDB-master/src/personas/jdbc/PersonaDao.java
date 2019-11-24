package personas.jdbc;
import java.sql.SQLException;
import java.util.List;
import personas.dto.PersonaDTO;

/**
 * Interfaz DAO que trabaja en conjunto con DTO.
 * el patrón DAO propone separar por completo la lógica de negocio de la lógica para acceder a los datos,
 * de esta forma, el DAO proporcionará los métodos necesarios para insertar, actualizar, borrar y consultar
 * la información; por otra parte, la capa de negocio solo se preocupa por lógica de negocio 
 * y utiliza el DAO para interactuar con la fuente de datos.
 * *
 * CRUD(Create, Read, Update, Delete)
 * Se debe crear una clase concreta para implementar el codigo asociado al metodo
 * @version 1.0.0
 *
 * @author Figueras, Galarza, Gutierrez
 */
public interface PersonaDao {
    public int insert (PersonaDTO persona) throws SQLException;
    public int update (PersonaDTO persona) throws SQLException;
    public int delete (PersonaDTO persona) throws SQLException;
    public List <PersonaDTO> select() throws SQLException;
    
}
