package personas.jdbc;
import java.sql.*;

/**
 * clase Conexion con variables estaticas se definen datos estaticos ya que no se veran modificados.
 * @paramJDBC_DRIVER, JDBC_URL, JDBC_USER, JDBC_PASS (final). 
 *
 * @version 1.0.1
 * @author Figeuras, Galarza, Gutierrez
 */
public class Conexion {
    /**
     * Variables finales.
     */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * JDBC_DRIVER nos trae el driver JDBC utilizado para motor MySQL
     */
    private static final String JDBC_URL = "jdbc:mysql://localhost/proyectoDB";
    /**
     * URL local donde tenemos montada la BD
     */
    private static final String JDBC_USER = "gonzalo";
    /**
     * User MySQL
     */
    private static final String JDBC_PASS = "gonzalo";
    /**
     * Password MySQl
     */
    private static Driver driver = null;
    /**
     * Objeto tipo Driver 
     */
   
    /**
     * Clase del tipo sincronizada que devuelve la conexion teniendo en cuenta
     * las variables estaticas ya definidas 
     * se utiliza objeto del tipo DriverManager lleva el seguimiento de los controladores
     * disponibles y maneja las solicitudes de conexion entre controladores adecuados y 
     * la base de datos .
     * 
     * @return DriverManager.getConection
     * @throws SQLException  la excepcion captura el error del driver JDBC
     * podria ser una version obsoleta, que no cargado como libreria en el proyecto, etc
     */
    public static synchronized Connection getConnection() throws SQLException{
        if (driver == null){
            try{
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver)jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);/**Registrando el Driver */
            }catch(Exception e) {
                System.out.println("Fallo en la carga del driver JDBC");
                e.printStackTrace(); /**Imprime el trace de la excepcion */
            }
        }
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);/**Devolviendo la conexion */
    }
    /**
     * Conociendo que siempre se deben cerrar la conexiones , en los siguientes metodos cerramos todas 
     * las conexiones que pudieran quedar abiertas.
     */
    
    /**
     * 
     * metodo estatico que cierra el objeto del tipo ResultSet.
     * proporciona el acceso a los datos de estas filas mediante un conjunto de métodos get 
     * que permiten el acceso a las diferentes columnas de la filas.
     * @param rs del tipo ResultSet 
     * 
     * @exception java.sql.SQLException.
     */
    public static void close(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();/**Imprime el trace de la excepcion */
        }
    }
    /**
     * Metodo que cierra el objeto del tipo 
     * Una Prepared Statement es una sentencia SQL de base de datos precompilada 
     * Al estar precompilada, su ejecución será más rápida que una SQL normal, por lo que es 
     * adecuada cuando vamos a ejecutar la misma sentencia SQL (con distintos valores) muchas veces.
     * 
     * @exception java.sql.SQLException
     * @param PreparedStatement stmt 
     */
    public static void close (PreparedStatement stmt){
        try{
            if(stmt!=null){
                stmt.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();/**Imprime el trace de la excepcion */
        }
    }/**
     *  Metodo que cierra el objeto del tipo Connection
     *  El objeto Connection es el principal objeto utilizado 
     *  para proporcionar un vínculo entre las bases de datos y una aplicacion.
     *  @param Connection conn.
     *  @exception java.sql.SQLException.
     * 
     * 
     */
    public static void close (Connection conn){
        try{
            if(conn!=null){
                conn.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();/**Imprime el trace de la excepcion */
        }
    }
}
