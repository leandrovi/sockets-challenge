/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author leandro
 */
public class Printers {
    private static Printers uniqueInstance;

    private static List<String> Printer1 = Collections.synchronizedList(new ArrayList<>());    
    private static List<String> Printer2 = Collections.synchronizedList(new ArrayList<>());    
    private static List<String> Printer3 = Collections.synchronizedList(new ArrayList<>());    
    private static List<String> Printer4 = Collections.synchronizedList(new ArrayList<>());    
    private static List<String> Printer5 = Collections.synchronizedList(new ArrayList<>());
    
    private static List<Socket> Clients = Collections.synchronizedList(new ArrayList<>());
    
    private static boolean Print = true;
    
    private Printers() {}
    
    public static synchronized Printers getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Printers();
        }
        return uniqueInstance;
    }
    
    public void AddMessageToPrinter(String message) {
        int[] quantities = { 
            Printer1.size(),
            Printer2.size(),
            Printer3.size(),
            Printer4.size(),
            Printer5.size()
        };
        
        int smaller = 0;
        int Printer = 0;
        
        for (int index = 0; index < 5; index++) {
            if (quantities[index] <= smaller) {
                smaller = quantities[index];
                Printer = index;
            }
        }
        
        if (Printer == 0) {
            Printer1.add(message);
        } else if (Printer == 1) {
            Printer2.add(message);
        } else if (Printer == 2) {
            Printer3.add(message);
        } else if (Printer == 3) {
            Printer4.add(message);
        } else {
             Printer5.add(message);
        }
    }
    
    public void StopPrinting() throws IOException {
        Print = false;
        
        for(Socket x : Clients){
            x.close();
        }
    }
    
    public boolean GetPrint() {
        return Print;
    }
    
    public List<String> GetPrinterMessage(int printer) {
        if (printer == 1) {
            return Printer1;
        } else if (printer == 2) {
            return Printer2;
        } else if (printer == 3) {
            return Printer3;
        } else if (printer == 4) {
            return Printer4;
        } else {
            return Printer5;
        }
    }
    
    public void InsertClients(Socket client) {
        Clients.add(client);
    }
}
