package pacientes.ficha_identificacion;

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
            ps.setString(1, uc.getN_control());
            ps.setString(2, uc.getCarrera());
            ps.setString(3, uc.getNombre());
            ps.setString(4, uc.getGenero());
            ps.setString(5, uc.getEdad());
            ps.setString(6, uc.getFecha_nacimiento());
            ps.setString(7, uc.getLugar_nacimiento());
            ps.setString(8, uc.getLugar_origen());
            ps.setString(9, uc.getDomicilio());
            ps.setString(10, uc.getEstado_civil());
            ps.setString(11, uc.getReligion());
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
            ps.setString(1, uc.getN_control());
            ps.setString(2, uc.getCarrera());
            ps.setString(3, uc.getNombre());
            ps.setString(4, uc.getGenero());
            ps.setString(5, uc.getEdad());
            ps.setString(6, uc.getFecha_nacimiento());
            ps.setString(7, uc.getLugar_nacimiento());
            ps.setString(8, uc.getLugar_origen());
            ps.setString(9, uc.getDomicilio());
            ps.setString(10, uc.getEstado_civil());
            ps.setString(11, uc.getReligion());
            ps.setInt(12, uc.getId_paciente());
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
            ps.setInt(1, uc.getId_paciente());
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

    public static void extraerID() {
        int c = 0;
        String SQL = "SELECT MAX(id) FROM ficha_identificacion";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getInt(1);
            }

            if (c == 0) {
                pacientes.Registrar.lblID.setText("1");
            } else {
                pacientes.Registrar.lblID.setText(String.valueOf(c + 1));
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

    public static void idPaciente(pacientes.Editar editar, String n_control) {

        String SQL = "SELECT * FROM ficha_identificacion, atecedentes_familiares, atecedentes_no_patologicos, atecedentes_guineco_obstetricos, "
                + "antecedentes_patologicos, signos_vitales_somatometria, inspeccion_general "
                + "WHERE "
                + "ficha_identificacion.id = atecedentes_familiares.id AND ficha_identificacion.id = atecedentes_no_patologicos.id AND "
                + "ficha_identificacion.id = atecedentes_guineco_obstetricos.id AND ficha_identificacion.id = antecedentes_patologicos.id AND "
                + "ficha_identificacion.id = signos_vitales_somatometria.id AND ficha_identificacion.id = inspeccion_general.id AND ficha_identificacion.id =" + n_control;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                rs.getInt(1);
                pacientes.Variables.NO_CONTROL = rs.getString(2);
                pacientes.Variables.CARRERA = rs.getString(3);
                pacientes.Variables.NOMBRES = rs.getString(4);
                pacientes.Variables.GENERO = rs.getString(5);
                pacientes.Variables.EDAD = rs.getString(6);
                pacientes.Variables.FECHA = rs.getString(7);
                pacientes.Variables.LUGAR_NAC = rs.getString(8);
                pacientes.Variables.LUGAR_ORIGEN = rs.getString(9);
                pacientes.Variables.DOMICILIO = rs.getString(10);
                pacientes.Variables.EST_CIVIL = rs.getString(11);
                pacientes.Variables.RELIGION = rs.getString(12);

                pacientes.Variables.H_FAMILIAR = rs.getString(14);

                pacientes.Variables.ALIMINTACION = rs.getString(16);
                pacientes.Variables.HABITACION = rs.getString(17);
                pacientes.Variables.HABITOS = rs.getString(18);

                pacientes.Variables.MENARCA = rs.getString(20);
                pacientes.Variables.IVSA = rs.getString(21);
                pacientes.Variables.N_PAREJAS = rs.getString(22);
                pacientes.Variables.EMBARAZOS = rs.getString(23);
                pacientes.Variables.MET_ANTI = rs.getString(24);

                pacientes.Variables.ENF_INFECT = rs.getString(26);
                pacientes.Variables.CRO_DEG = rs.getString(27);
                pacientes.Variables.ALERGICOS = rs.getString(28);
                pacientes.Variables.QUIRURJICOS = rs.getString(29);
                pacientes.Variables.TRAUMA = rs.getString(30);
                pacientes.Variables.TRANSFUCIONES = rs.getString(31);
                pacientes.Variables.CONVULSIONES = rs.getString(32);
                pacientes.Variables.ADICCIONES = rs.getString(33);
                pacientes.Variables.HOSPITALIZACIONES = rs.getString(34);

                pacientes.Variables.PULSO = rs.getString(36);
                pacientes.Variables.PRESION = rs.getString(37);
                pacientes.Variables.TEMPERATURA = rs.getString(38);
                pacientes.Variables.FREC_CARD = rs.getString(39);
                pacientes.Variables.PESO = rs.getString(40);
                pacientes.Variables.TALLA = rs.getString(41);
                pacientes.Variables.MASA_CORP = rs.getString(42);
                pacientes.Variables.TIPO_SANGRE = rs.getString(43);

                pacientes.Variables.GENERAL = rs.getString(45);
            }
            editar.muestraDatos();
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static int idPacient(String n_control) {
//        int c = 0;
//
//        String SQL = "SELECT * FROM ficha_identificacion WHERE n_control=" + n_control;
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(SQL);
//            if (rs.next()) {
//                c = rs.getInt(1);
//            }
//            return c;
//        } catch (SQLException ex) {
//            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return c;
//    }

    public static void listar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) principal.Principal.tablaPacientes.getModel();

        int contador = 0;
        
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR;
        } else {
            sql = "SELECT * FROM ficha_identificacion WHERE (id LIKE'" + busca + "%' OR n_control LIKE'" + busca + "%' OR "
                    + "carrera LIKE'" + busca + "%' OR nombre LIKE'" + busca + "%' OR "
                    + "genero LIKE'" + busca + "%' OR edad LIKE'" + busca + "%')";
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
                datos[3] = rs.getString("genero");
                datos[4] = rs.getString("edad");
                datos[5] = rs.getString("carrera");
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
        for (int i = 0; i < principal.Principal.tablaPacientes.getRowCount(); i++) {
            if (num_control.equals(principal.Principal.tablaPacientes.getValueAt(i, 0))) {
                principal.Principal.tablaPacientes.setRowSelectionInterval(i, i);
                break;
            }
        }
    }
    
    public static void verDatos(pacientes.VerDatos ver, String n_control) {

        String SQL = "SELECT * FROM ficha_identificacion, atecedentes_familiares, atecedentes_no_patologicos, atecedentes_guineco_obstetricos, "
                + "antecedentes_patologicos, signos_vitales_somatometria, inspeccion_general "
                + "WHERE "
                + "ficha_identificacion.id = atecedentes_familiares.id AND ficha_identificacion.id = atecedentes_no_patologicos.id AND "
                + "ficha_identificacion.id = atecedentes_guineco_obstetricos.id AND ficha_identificacion.id = antecedentes_patologicos.id AND "
                + "ficha_identificacion.id = signos_vitales_somatometria.id AND ficha_identificacion.id = inspeccion_general.id AND n_control=" + n_control;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                rs.getInt(1);
                pacientes.Variables.NO_CONTROL = rs.getString(2);
                pacientes.Variables.CARRERA = rs.getString(3);
                pacientes.Variables.NOMBRES = rs.getString(4);
                pacientes.Variables.GENERO = rs.getString(5);
                pacientes.Variables.EDAD = rs.getString(6);
                pacientes.Variables.FECHA = rs.getString(7);
                pacientes.Variables.LUGAR_NAC = rs.getString(8);
                pacientes.Variables.LUGAR_ORIGEN = rs.getString(9);
                pacientes.Variables.DOMICILIO = rs.getString(10);
                pacientes.Variables.EST_CIVIL = rs.getString(11);
                pacientes.Variables.RELIGION = rs.getString(12);

                pacientes.Variables.H_FAMILIAR = rs.getString(14);

                pacientes.Variables.ALIMINTACION = rs.getString(16);
                pacientes.Variables.HABITACION = rs.getString(17);
                pacientes.Variables.HABITOS = rs.getString(18);

                pacientes.Variables.MENARCA = rs.getString(20);
                pacientes.Variables.IVSA = rs.getString(21);
                pacientes.Variables.N_PAREJAS = rs.getString(22);
                pacientes.Variables.EMBARAZOS = rs.getString(23);
                pacientes.Variables.MET_ANTI = rs.getString(24);

                pacientes.Variables.ENF_INFECT = rs.getString(26);
                pacientes.Variables.CRO_DEG = rs.getString(27);
                pacientes.Variables.ALERGICOS = rs.getString(28);
                pacientes.Variables.QUIRURJICOS = rs.getString(29);
                pacientes.Variables.TRAUMA = rs.getString(30);
                pacientes.Variables.TRANSFUCIONES = rs.getString(31);
                pacientes.Variables.CONVULSIONES = rs.getString(32);
                pacientes.Variables.ADICCIONES = rs.getString(33);
                pacientes.Variables.HOSPITALIZACIONES = rs.getString(34);

                pacientes.Variables.PULSO = rs.getString(36);
                pacientes.Variables.PRESION = rs.getString(37);
                pacientes.Variables.TEMPERATURA = rs.getString(38);
                pacientes.Variables.FREC_CARD = rs.getString(39);
                pacientes.Variables.PESO = rs.getString(40);
                pacientes.Variables.TALLA = rs.getString(41);
                pacientes.Variables.MASA_CORP = rs.getString(42);
                pacientes.Variables.TIPO_SANGRE = rs.getString(43);

                pacientes.Variables.GENERAL = rs.getString(45);
            }
            ver.muestraDatos();
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
