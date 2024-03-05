package consultas;

/**
 *
 * @author Luis mora
 */
public class Sentencias {

    public static String LISTAR = "SELECT * FROM ficha_identificacion, consultas WHERE "
            + "ficha_identificacion.id = consultas.id_paciente";

    public static String REGISTRAR = "INSERT INTO consultas("
            + "id_paciente,"
            + "t_a,"
            + "temperatura,"
            + "descripcion,"
            + "receta,"
            + "fecha,"
            + "hora) "
            + "VALUES(?,?,?,?,?,?,?)";

    public static String ACTUALIZAR = "UPDATE consultas SET "
            + "id_paciente         = ?, "
            + "t_a         = ?, "
            + "temperatura            = ?, "
            + "descripcion         = ?, "
            + "receta            = ?, "
            + "fecha            = ?, "
            + "hora              = ? "
            + "WHERE id          = ?  ";

    public static String ELIMINAR = "DELETE FROM consultas WHERE id = ?";

    public static String ELIMINAR_TODO = "TRUNCATE TABLE consultas";

    private int id;
    private int idPaciente;
    private String t_a;
    private String temperatura;
    private String descripcion;
    private String receta;
    private String fecha;
    private String hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getT_a() {
        return t_a;
    }

    public void setT_a(String t_a) {
        this.t_a = t_a;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
