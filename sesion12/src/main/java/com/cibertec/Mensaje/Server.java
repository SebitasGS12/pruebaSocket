package com.cibertec.Mensaje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 13;
    public Server(){
        System.out.println("1... Server construido");
        try (ServerSocket serverSocket = new ServerSocket(PORT);){
            
            System.out.println("2. Esperando a cliente");

            while (true) {
                
                Socket clienSocket = serverSocket.accept();
                System.out.println("3. Conexion Cliente aceptado");
                //Flujo de datos
                BufferedReader entrada = 
                    new BufferedReader(new InputStreamReader(
                        clienSocket.getInputStream()
                    ));

                PrintWriter salida = new PrintWriter(
                    clienSocket.getOutputStream(),true);

                String mensajeString = entrada.readLine();
                String precio = "";

                switch (mensajeString) {
                    case "PLATEA": precio = "250 PEN";break;
                    case "VIP": precio = "150 PEN";break;
                    case "PLATINIUM": precio = "2250 PEN";break;
                }

                salida.println(precio);

                System.out.println("4 >> final for client...");
                clienSocket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}