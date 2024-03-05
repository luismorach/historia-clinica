package HerrBD;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luis mora
 */
public class RestoreMySQL {

    public static boolean restoreDB(String dbName, String dbUserName, String dbPassword, String source) {

        String[] comando = new String[]{"mysql.exe", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", " source " + source};
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(comando);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
