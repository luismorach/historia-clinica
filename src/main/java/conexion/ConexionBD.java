package conexion;

import java.sql.*;


/**
 *
 * @author Luis mora
 */

public class ConexionBD {

    static Connection conect = null;

    public static Connection conexion() {
        try {
            if (conect == null) {
                // Class.forName("com.mysql.jdbc.Driver");
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
                conect = DriverManager.getConnection("jdbc:sqlite:base.db");
                System.out.println("conexion creada");
            }
            return conect;
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la conexion", e);
        }

    }

    static class MiShDwnHook extends Thread {
 
        // justo antes de finalizar el programa la JVM invocara
        // a este metodo donde podemos cerrar la conexion
        @Override
        public void run() {
            try {
                // Connection conexion = BaseDeDatos.getConexion();
                conect.close();
                System.out.println("conexion cerrada");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void Tablas() {

        String sql = "CREATE TABLE IF NOT EXISTS antecedentes_patologicos (\n"
                + " id Integer PRIMARY KEY NOT NULL,\n"
                + " enfermedades_infecto TEXT NOT NULL,\n"
                + " cronico_degenerativas TEXT NOT NULL,\n"
                + " alergicos TEXT NOT NULL,\n"
                + " quirurgicos TEXT NOT NULL,\n"
                + " traumatologicos TEXT NOT NULL,\n"
                + " transfuciones TEXT NOT NULL,\n"
                + " convulsiones TEXT NOT NULL,\n"
                + " adicciones TEXT NOT NULL,\n"
                + " hospitalizaciones TEXT NOT NULL );";

        String sql1 = "CREATE TABLE IF NOT EXISTS atecedentes_familiares (\n" +
                "  id Integer PRIMARY KEY NOT NULL,\n" +
                "  heredo_familiar TEXT NOT NULL );";

        String sql2 = "CREATE TABLE IF NOT EXISTS atecedentes_guineco_obstetricos (\n"
                + " id Integer PRIMARY KEY NOT NULL, \n"
                + " menarca TEXT NOT NULL,\n"
                + " ivsa TEXT NOT NULL,\n"
                + " numero_parejas INTEGER NOT NULL,\n"
                + " embarazos INTEGER NOT NULL,\n"
                + " metodo_anticonceptivo TEXT NOT NULL );";

        String sql3 = "CREATE TABLE IF NOT EXISTS atecedentes_no_patologicos (\n"
                + " id Integer PRIMARY KEY NOT NULL, \n"
                + " alimentacion TEXT NOT NULL,\n"
                + " habitacion TEXT NOT NULL,\n"
                + " habitos_higienicos TEXT NOT NULL );";

        String sql4 = "CREATE TABLE IF NOT EXISTS consultas(\n"
                + " id Integer PRIMARY KEY NOT NULL,\n"
                + " id_paciente Integer NOT NULL,\n"
                + " t_a TEXT NOT NULL,\n"
                + " temperatura TEXT NOT NULL,\n"
                + " descripcion TEXT NOT NULL,\n"
                + " receta TEXT NOT NULL, \n"
                + " fecha TEXT NOT NULL, \n"
                + " hora TEXT NOT NULL);";
        /*
         * + " alimentacion TEXT NOT NULL,\n"
         * + " habitacion TEXT NOT NULL,\n"
         * + " habitos_higienicos TEXT NOT NULL);";
         */

        String sql5 = "CREATE TABLE IF NOT EXISTS ficha_identificacion(\n"
                + " id Integer PRIMARY KEY NOT NULL, \n"
                + " n_control INTEGER NOT NULL, \n"
                + " carrera TEXT NOT NULL,\n"
                + " nombre TEXT NOT NULL,\n"
                + " genero TEXT NOT NULL,\n"
                + " edad TEXT NOT NULL,\n"
                + " fecha_nacimiento DATE NOT NULL,\n"
                + " lugar_nacimiento TEXT NOT NULL,\n"
                + " lugar_origen TEXT NOT NULL,\n"
                + " domicilio TEXT NOT NULL,\n"
                + " estado_civil TEXT NOT NULL,\n"
                + " religion TEXT NOT NULL);";

        String sql6 = "CREATE TABLE IF NOT EXISTS inspeccion_general(\n"
                + " id Integer PRIMARY KEY NOT NULL, \n"
                + " general TEXT NOT NULL);";

        String sql7 = "CREATE TABLE IF NOT EXISTS signos_vitales_somatometria(\n"
                + " id Integer PRIMARY KEY NOT NULL, \n"
                + " pulso TEXT NOT NULL, \n"
                + " presion TEXT NOT NULL,\n"
                + " temperatura TEXT NOT NULL,\n"
                + " frecuencia_respiratoria TEXT NOT NULL,\n"
                + " peso TEXT NOT NULL,\n"
                + " talla TEXT NOT NULL,\n"
                + " indice_masa_corporal TEXT NOT NULL,\n"
                + " tipo_sangre TEXT NOT NULL);";

        String sql8 = "CREATE TABLE IF NOT EXISTS usuarios(\n"
                + " id Integer PRIMARY KEY NOT NULL, \n"
                + " nombre TEXT NOT NULL, \n"
                + " usuario TEXT NOT NULL,\n"
                + " password TEXT NOT NULL,\n"
                + " tipo_us TEXT NOT NULL);";

        try {
            Connection conn = conexion();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            stmt.execute(sql5);
            stmt.execute(sql6);
            stmt.execute(sql7);
            stmt.execute(sql8);
            System.out.println("tablas Funcionando.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    //
    // public Connection Desconectar() {
    // try {
    // conect.close();
    // } catch (SQLException e) {
    // JOptionPane.showMessageDialog(null, "Error en la conexión" + e);
    // }
    // return conect;
    // }
}
