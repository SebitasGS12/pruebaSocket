package com.cibertec.BBDD;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {

	private static String URL="jdbc:mysql://localhost:3306/bd_imagen";
	private static String Usuario="root";
	private static String Clave="admin";

    public static Connection getConexion(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(URL,Usuario, Clave);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException ex) {
			System.out.println("Error >> Driver no Instalado!! " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("Error >> de conexión con la BD "+ex.getMessage());
		} catch(Exception ex) {
			System.out.println("Error >> general "+ex.getMessage());
		}
        return cn;
    }


    public static void insertarImagen(Imagen imagen){
        Connection con = getConexion();
        if (con != null) {
            String sql = "INSERT INTO imagen (nombre, tamano, archivo) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql);) {
                pst.setString(1, imagen.getNombre());
                pst.setInt(2, imagen.getTamano());
                pst.setBinaryStream(3 , new ByteArrayInputStream(imagen.getArchivo()),imagen.getArchivo().length);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Inserción exitosa");
                }
            } catch (Exception ex) {
                System.out.println("Error >> durante la inserción " + ex.getMessage());
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error >> cerrando conexión " + ex.getMessage());
                }
            }
        }


    }

    public static void main(String[] args) {
        Conexion.getConexion();
    }
}