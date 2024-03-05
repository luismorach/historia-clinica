package pacientes.antecedentes_patologicos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luis mora
 */
public class Opciones {

   
    public static Connection cn = ConexionBD.conexion();
    static PreparedStatement ps;

    public static boolean registrar(Sentencias uc) {
        String sql = Sentencias.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getEnfermedades_infecto());
            ps.setString(2, uc.getCronico_degenerativas());
            ps.setString(3, uc.getAlergicos());
            ps.setString(4, uc.getQuirurgicos());
            ps.setString(5, uc.getTraumatologicos());
            ps.setString(6, uc.getTransfuciones());
            ps.setString(7, uc.getConvulsiones());
            ps.setString(8, uc.getAdicciones());
            ps.setString(9, uc.getHospitalizaciones());
            ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(sql);
        return false;
    }

    public static boolean actualizar(Sentencias uc) {
        String sql = Sentencias.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getEnfermedades_infecto());
            ps.setString(2, uc.getCronico_degenerativas());
            ps.setString(3, uc.getAlergicos());
            ps.setString(4, uc.getQuirurgicos());
            ps.setString(5, uc.getTraumatologicos());
            ps.setString(6, uc.getTransfuciones());
            ps.setString(7, uc.getConvulsiones());
            ps.setString(8, uc.getAdicciones());
            ps.setString(9, uc.getHospitalizaciones());
            ps.setInt(10, uc.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return false;
    }

    public static boolean eliminar(Sentencias uc) {
        String sql = Sentencias.ELIMINAR;

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, uc.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return false;
    }

    public static boolean eliminarTodo() {
        String sql = Sentencias.ELIMINAR_TODO;

        try {
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return false;
    }
}
