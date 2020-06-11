/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import entities.Printers;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author leandro
 */
public class ThreadClient extends Thread {
    private Socket client;

    public ThreadClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
        Printers.getInstance().InsertClients(client);
        
        try (InputStream stream = client.getInputStream()) {
            boolean active = true;
            
            while (client.isConnected()) {
                if (stream.available() != 0) {
                    byte[] data = new byte[stream.available()];
                    
                    stream.read(data);
                    
                    if (!new String(data).equals("exit")) {
                        Printers.getInstance().AddMessageToPrinter(new String(data));
                    } else {
                        client.close();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
