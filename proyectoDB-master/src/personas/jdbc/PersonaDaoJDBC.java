package personas.jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import personas.dto.PersonaDTO;

/**
 *  Implementacion de la interfaz personaDAO.
 * @see personaDao.java
 * se realizan las operaciones basicas del CRUD
 * 
 * 
 * @author Figueras, Galarza, Gutierrez
 * @version 1.0.1
 */

public class PersonaDaoJDBC implements PersonaDao{
   
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO persona (nombre, apellido) VALUES(?,?)";
    /**Query para Insertar. */
    private final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";
    /**Query para Update. */
    private final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";
    /**Query para Delete. */
    private final String SQL_SELECT = "SELECT id_persona, nombre, apellido FROM persona";
    /**Query para Select. */
    
    /**
     * Constructores de la clase.
     * Vacio y con argumentos . Se pasa argumento objeto del tipo Connection
     * mantiene la conexion viva para todas las consultas
     * para poder hacer un commit o un rollback.
     * 
     * commit() se llega hasta este punto quiere decir que no se encontró ningún error 
     * y por tanto todas las instrucciones SQL serán confirmadas.
     * 
     * Si se produce alguna excepción, se hará un llamado rollback
     * este método deshace todos los cambios realizados en la transacción actual
     * y libera cualquier bloque en la base de datos que contenga en esos momentos este objeto.
     * 
     * 
     */
    public PersonaDaoJDBC(){}
    public PersonaDaoJDBC(Connection conn){
        this.userConn = conn;
     /**
      * en este punto es donde se puede hacer el commit o rollback
      */
    }
    
    
    /**
     * Implementando metodo INSERT
     * @param persona
     * @return rows (columna) del tipo INT
     * @throws java.sql.SQLException 
     */
    @Override
    public int insert(PersonaDTO persona) throws SQLException {
      Connection conn = null;
      PreparedStatement stmt = null;
      int rows = 0;
      try{
          conn = (this.userConn!=null) ? this.userConn: Conexion.getConnection();
          stmt =conn.prepareStatement(SQL_INSERT);
          int index = 1;
          stmt.setString(index++, persona.getNombre());
          stmt.setString(index, persona.getApellido());
          System.out.println("Ejecutando Query: " + SQL_INSERT);
          rows = stmt.executeUpdate();
          System.out.println("Registros afectados: "+rows);
      } finally {
          Conexion.close(stmt);
          if(this.userConn == null){
                /**
                 * cierre de la conexion.
                 */
              Conexion.close(conn);
          }
      }
      return rows;
    }

    /**
     * Implementando metodo UPDATE
     * @param persona
     * @return rows (columna) del tipo INT
     * @throws java.sql.SQLException 
     */
    
    @Override
    public int update(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = (this.userConn!=null)? this.userConn:Conexion.getConnection();
            System.out.println("Ejecutando Query: " +SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++,persona.getNombre());
            stmt.setString(index++, persona.getApellido());
            stmt.setInt(index, persona.getId_persona());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: "+rows);
        }finally{
            Conexion.close(stmt);
            if(this.userConn==null){
                  /**
                 * cierre de la conexion.
                 */
                Conexion.close(conn);
            }
        }
        return rows; 
    }

    /**
     * implementando metodo DELETE
     * @parampersona
     * @return rows (columnas del tipo INT)
     * @throws java.sql.SQLException 
     */
    @Override
    public int delete(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = (this.userConn!=null) ? this.userConn: Conexion.getConnection();
            System.out.println("Ejecutando Query: "+SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,persona.getId_persona());
            rows = stmt.executeUpdate();
            System.out.println("Registros Eliminados: "+ rows);
        }finally{
            Conexion.close(stmt);
            if(this.userConn!=null){
                  /**
                 * cierre de la conexion.
                 */
                Conexion.close(conn);
            }
        }
        return rows;
    }

    /**
     * Implementando metodo LIST<>
     * @return Personas <> del tipo PersonasDTO
     * @throws java.sql.SQLException 
     */
    @Override
    public List<PersonaDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO personaDTO=null;
        List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
        try{
            conn = (this.userConn!=null)? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                /**
                 * mientras exista uno mas...
                 */
                int idPersonaTemp = rs.getInt(1);
                String nombreTemp = rs.getString(2);
                String apellidoTemp = rs.getString(3);
                personaDTO = new PersonaDTO();
                personaDTO.setId_persona(idPersonaTemp);
                personaDTO.setNombre(nombreTemp);
                personaDTO.setApellido(apellidoTemp);
                personas.add(personaDTO);
            }
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            if(this.userConn ==null){
                /**
                 * cierre de la conexion.
                 */
                Conexion.close(conn);
            }
        }
        return personas;
    }
    
    
}
