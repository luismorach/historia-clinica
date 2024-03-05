/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import conexion.ConexionBD;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import necesario.RSScrollBar;

/**
 *
 * @author Luis mora
 */
public class Funciones {

    static ConexionBD cc = new ConexionBD();
    public static Connection cn = ConexionBD.conexion();
    static PreparedStatement ps;

    public static void inicioEdicionTablas() {
        //Tabla PACIENTES
        principal.Principal.scrollPaciente.getViewport().setBackground(Color.white);
        principal.Principal.scrollPaciente.getHorizontalScrollBar().setUI(new RSScrollBar());
        principal.Principal.tablaPacientes.setCursor(new Cursor(12));

        //Tabla CONSULTAS
        principal.Principal.scrollConsultas.getViewport().setBackground(Color.white);
        principal.Principal.scrollConsultas.getHorizontalScrollBar().setUI(new RSScrollBar());
        principal.Principal.tablaConsultas.setCursor(new Cursor(12));

        //Tabla USUARIOS
        principal.Principal.scrollUsuarios.getViewport().setBackground(Color.white);
        principal.Principal.scrollUsuarios.getHorizontalScrollBar().setUI(new RSScrollBar());
        principal.Principal.tablaUsuarios.setCursor(new Cursor(12));

    }
    
    public static String verificarUsuario(String usuario) {
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
    
    public static String verificaPassword(String usuario) {
        String existe = "";
        String SQL = "SELECT password FROM usuarios WHERE usuario = '"+ usuario+"'";
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
    
    public static String obtenerTipoUsuario(String usuario) {
        String existe = "";
        String SQL = "SELECT tipo_us FROM usuarios WHERE usuario = '"+ usuario+"'";
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
    
    public static String obtenerNombre(String usuario) {
        String existe = "";
        String SQL = "SELECT nombre FROM usuarios WHERE usuario = '"+ usuario+"'";
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
    
    public static String obtenerID(String usuario) {
        String existe = "";
        String SQL = "SELECT id FROM usuarios WHERE usuario = '"+ usuario+"'";
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
    
    public static String obtenerUsuario(String id) {
        String existe = "";
        String SQL = "SELECT usuario FROM usuarios WHERE id = "+ id;
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
    
    public static boolean actualizarUs(String id, String usNu) {
        String sql = "UPDATE usuarios SET usuario = ? WHERE id = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, usNu);
            ps.setString(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return false;
    }
    
    public static boolean actualizarPass(String id, String pass) {
        String sql = "UPDATE usuarios SET password = ? WHERE id = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return false;
    }
    
}
