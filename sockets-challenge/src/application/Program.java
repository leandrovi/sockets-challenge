/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import jobs.ThreadClient;
import jobs.ThreadController;
import jobs.ThreadPrinter1;
import jobs.ThreadPrinter2;
import jobs.ThreadPrinter3;
import jobs.ThreadPrinter4;
import jobs.ThreadPrinter5;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author leandro
 */
public class Program {

    private static ServerSocket server;
    private static int port = 3333;
    
    public void StartsThread(){ }
    
    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        server = new ServerSocket(port);
        
        ThreadController controller = new ThreadController();
        controller.start();
        
        while (true) {
            Socket socket = server.accept();
            
            ThreadClient client = new ThreadClient(socket);
            
            client.start();
        }
    }
    
}
