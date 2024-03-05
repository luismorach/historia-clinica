package consultas;

import conexion.ConexionBD;
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

    public static Connection cn = ConexionBD.conexion();
    static PreparedStatement ps;

    public static boolean registrar(Sentencias uc) {
        String sql = Sentencias.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, uc.getIdPaciente());
            ps.setString(2, uc.getT_a());
            ps.setString(3, uc.getTemperatura());
            ps.setString(4, uc.getDescripcion());
            ps.setString(5, uc.getReceta());
            ps.setString(6, uc.getFecha());
            ps.setString(7, uc.getHora());
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
            ps.setInt(1, uc.getIdPaciente());
            ps.setString(2, uc.getT_a());
            ps.setString(3, uc.getTemperatura());
            ps.setString(4, uc.getDescripcion());
            ps.setString(5, uc.getReceta());
            ps.setString(6, uc.getFecha());
            ps.setString(7, uc.getHora());
            ps.setInt(8, uc.getId());
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
        String SQL = "SELECT MAX(id) FROM consultas";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getInt(1);
            }

            if (c == 0) {
                consultas.Registrar.lblID.setText("1");
            } else {
                consultas.Registrar.lblID.setText(String.valueOf(c + 1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean verificaNumControl(String n_control) {
        boolean existe = false;
        String SQL = "SELECT n_control FROM ficha_identificacion WHERE n_control = "
                + n_control;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return existe;
    }

    public static int extraerIDPaciente(String n_control) {
        int id = 0;
        String SQL = "SELECT id FROM ficha_identificacion WHERE n_control = "
                + n_control;
                System.out.println(SQL);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt(1);
            }
            System.out.println(id);
            return id;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }

    public static String extraerNombre(String n_control) {
        String nombre = "";
        String SQL = "SELECT nombre FROM ficha_identificacion WHERE n_control = "
                + n_control;
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

    public static String extraerEdad(String n_control) {
        String nombre = "";
        String SQL = "SELECT edad FROM ficha_identificacion WHERE n_control = "
                + n_control;
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

    public static void listar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) principal.Principal.tablaConsultas.getModel();

        int contador = 0;
        
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR;
        } else {
            sql = "SELECT * FROM ficha_identificacion, consultas WHERE ("
                    + "consultas.id LIKE'"+busca+"%' OR ficha_identificacion.n_control LIKE'"+busca+"%' OR "
                    + "ficha_identificacion.nombre LIKE'"+busca+"%' OR ficha_identificacion.carrera LIKE'"+busca+"%' OR "
                    + "consultas.fecha LIKE'"+busca+"%' OR consultas.hora LIKE'"+busca+"%'"
                    + ") AND ficha_identificacion.id = consultas.id_paciente";
        }
        String datos[] = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                contador++;
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("n_control");
                datos[2] = rs.getString("nombre");
                datos[3] = rs.getString("carrera");
                datos[4] = rs.getString("fecha");
                datos[5] = rs.getString("hora"); 
                modelo.addRow(datos);
            }

            if(contador == 0 && !busca.equals("")){
                datos[3] = "Sin resultados...";
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void selecionaFila(String num_control) {
        for (int i = 0; i < principal.Principal.tablaConsultas.getRowCount(); i++) {
            if (num_control.equals(principal.Principal.tablaConsultas.getValueAt(i, 0))) {
                principal.Principal.tablaConsultas.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    public static void extraerDatos(consultas.Editar editar, String id) {

        String SQL = "SELECT * FROM ficha_identificacion, consultas WHERE "
                + "ficha_identificacion.id = consultas.id_paciente AND consultas.id = "+id;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
               Variables.NO_CONTROL = rs.getString("ficha_identificacion.n_control");
               Variables.FECHA_CONSULTA = rs.getString("consultas.fecha");
               Variables.HORA_CONSULTA = rs.getString("consultas.hora");
               Variables.NOMBRE = rs.getString("ficha_identificacion.nombre");
               Variables.T_A = rs.getString("consultas.t_a");
               Variables.EDAD = rs.getString("ficha_identificacion.edad");
               Variables.TEMPERATURA = rs.getString("consultas.temperatura");
               Variables.DESCRIPCION = rs.getString("consultas.descripcion");
               Variables.RECETA = rs.getString("consultas.receta");
            }
            editar.muestraDatos();
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void extraerDatosReceta(consultas.VerDatos ver, String id) {

        String SQL = "SELECT * FROM ficha_identificacion, consultas WHERE "
                + "ficha_identificacion.id = consultas.id_paciente AND consultas.id = "+id;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
               Variables.NO_CONTROL = rs.getString("n_control");
               Variables.FECHA_CONSULTA = rs.getString("fecha");
               Variables.HORA_CONSULTA = rs.getString("hora");
               Variables.NOMBRE = rs.getString("nombre");
               Variables.T_A = rs.getString("t_a");
               Variables.EDAD = rs.getString("edad");
               Variables.TEMPERATURA = rs.getString("temperatura");
               Variables.DESCRIPCION = rs.getString("descripcion");
               Variables.RECETA = rs.getString("receta");
            }
            ver.muestraDatos();
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
