package LibreriaCD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Javii
 */
public class Metodos {
    
    private Connection conexion = null;
    private ResultSet rs = null;
    
    public void connect(String url, String usuario, String contra) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/" + url, usuario, contra);
            System.out.println("conectado");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(String tabla, String datos) {
        PreparedStatement i;
        try {
            i = conexion.prepareStatement("insert into " + tabla + " values(" + datos + ")");
            i.execute();
            System.out.println("insertado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void delete(String tabla, int primaryKey) {
        PreparedStatement d;
        try {
            d = conexion.prepareStatement("delete from " + tabla + " where id=" + primaryKey);
            d.execute();
            System.out.println("eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void update(String tabla, String campo, String dato, int primaryKey) {
        PreparedStatement u;
        try {
            u = conexion.prepareStatement("update " + tabla + " set " + campo + "=" + dato + " where .id=" + primaryKey);
            u.execute();
            System.out.println("actualizado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public ResultSet select(String tabla, String campos) {
        Statement s;
        try {
            s = conexion.createStatement();
            rs = s.executeQuery("select " + campos + " from " + tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
     
     public void apagar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
