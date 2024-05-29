package com.cibertec.BBDD;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    
    private static final int PORT = 13;

    public Server(){
        System.out.println("1 >> [ini] Server constructor");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("2 >> waiting for client...");                
            while(true){
                Socket clienSocket = serverSocket.accept();
                System.out.println("3 >> accepted client connection...");
                
                //Flujo de datos de entrada y salida
                try (ObjectInputStream entrada = new ObjectInputStream(clienSocket.getInputStream());){
                    
                    Imagen imagen = (Imagen) entrada.readObject();
                    Conexion.insertarImagen(imagen);
                    //Generar nombre del archivo
                    String rutaArchivo = "D:\\server\\" + imagen.getNombre();
                    File file = new File(rutaArchivo);
                    System.out.println("Archivo creado: " + file.getAbsolutePath());
                    FileOutputStream fos = new FileOutputStream(file);
                    //Recibiendo archivo
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = entrada.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    
                    fos.close();
                
                    System.out.println("Archivo recibido: " + file.getAbsolutePath());
                    System.out.println("4 >> final for client...");
                    clienSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        new Server();   
    }
}