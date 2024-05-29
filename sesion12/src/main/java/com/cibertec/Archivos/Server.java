package com.cibertec.Archivos;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    
    private static final int PORT = 13;

    public Server(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("1 >> Server started on port " + PORT);
            while (true) {
                System.out.println("2 >> Waiting for client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("3 >> New client connected: " + clientSocket.getInetAddress().getHostAddress());
                //Generar nombre del archivo
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss") ;
                String nombreArchivo = "foto_"+sdf.format(new Date())+".png";
                String rutaArchivo = "D:\\server\\"+nombreArchivo;
                File file = new  File(rutaArchivo);


                //Flujo de entrada y salida
                FileOutputStream fos = new FileOutputStream(file);
                DataInputStream entrada = new DataInputStream(clientSocket.getInputStream());

                byte[] buffer = new byte[1024];
                int count;
                while((count  = entrada.read(buffer))> 0){
                    fos.write(buffer,0,count);
                }
                fos.close();
                System.out.println("Archivo creado "+ file.getAbsolutePath());

                System.out.println(" 4 >> Client Complete");

                clientSocket.close();
                System.out.println("5 >>  Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }

}