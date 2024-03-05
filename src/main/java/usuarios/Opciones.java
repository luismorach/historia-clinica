package usuarios;

import conexion.ConexionBD;
import principal.Principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis mora
 */
public class Opciones {

    //static ConexionBD cc = new ConexionBD();
    public static Connection cn = ConexionBD.conexion();
    static PreparedStatement ps;

    public static boolean registrar(Sentencias uc) {
        String sql = Sentencias.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getNombre());
            ps.setString(2, uc.getUsuario());
            ps.setString(3, uc.getPassword());
            ps.setString(4, uc.getTipo_usuario());
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
            ps.setString(1, uc.getNombre());
            ps.setString(2, uc.getUsuario());
            ps.setString(3, uc.getPassword());
            ps.setString(4, uc.getTipo_usuario());
            ps.setInt(5, uc.getId());
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

    public static void extraerIDMax() {
        int c = 0;
        String SQL = "SELECT MAX(id) FROM usuarios";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getInt(1);
            }

            if (c == 0) {
                principal.Principal.lblID.setText("1");
            } else {
                principal.Principal.lblID.setText(String.valueOf(c + 1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int verificarUsuarios() {
        int c = 0;
        String SQL = "SELECT COUNT(*) FROM usuarios";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getInt(1);
            }
            
            return c;

        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public static String verificaUsuario(String usuario) {
        String existe = "";
        String SQL = "SELECT usuario FROM usuarios WHERE usuario = '"+ usuario+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getString(1);
            }           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return existe;
    }

    public static String extraerNombre(String id) {
        String nombre = "";
        String SQL = "SELECT nombre FROM usuarios WHERE id = "+ id;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                nombre = rs.getString(1);
            }
            return nombre;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return nombre;
    }
    
    public static String extraerPassword(String id) {
        String password = "";
        String SQL = "SELECT password FROM usuarios WHERE id = '"+id+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                password = rs.getString(1);
            }
            return password;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return password;
    }

    public static void listar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) principal.Principal.tablaUsuarios.getModel();

        int contador = 0;
        
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR;
        } else {
            sql = "SELECT * FROM usuarios WHERE (id LIKE'"+busca+"%' OR "
                    + "nombre LIKE'"+busca+"%' OR usuario LIKE'"+busca+"%' OR "
                    + "tipo_us LIKE'"+busca+"%') AND tipo_us != 'SUPER ADMIN'";
        }
        String datos[] = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                contador++;
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("usuario");
                datos[3] = rs.getString("tipo_us");
                modelo.addRow(datos);
            }

            if(contador == 0 && !busca.equals("")){
                datos[2] = "Sin resultados...";
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void selecionaFila(String num_control) {
        for (int i = 0; i < principal.Principal.tablaUsuarios.getRowCount(); i++) {
            if (num_control.equals(principal.Principal.tablaUsuarios.getValueAt(i, 0))) {
                principal.Principal.tablaUsuarios.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    public static void extraerDatos(principal.Principal principal, String id) {

        String SQL = "SELECT * FROM usuarios WHERE id = "+id;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
               Principal.txtNombre.setText(rs.getString(2));
               Principal.txtUser.setText(rs.getString(3));
               Principal.txtPass.setText(rs.getString(4));
               Principal.comboTipo.setSelectedItem(rs.getString(5));
               Principal.btnRegistrar.setText("GUARDAR");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
