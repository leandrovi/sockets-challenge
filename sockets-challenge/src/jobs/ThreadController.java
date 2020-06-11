/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import entities.Printers;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandro
 */
public class ThreadController extends Thread {

    @Override
    public void run() {
        System.out.println(
            "**************************************************************\n" +
            "********* Hey Guys! Welcome to my printing server =) *********\n" +
            "****** Please, type '0' any moment to stop this server! ******\n" +
            "**************************************************************\n"
        );
        
        Scanner scan = new Scanner(System.in);

        ThreadPrinter1 printer1 = new ThreadPrinter1();
        ThreadPrinter2 printer2 = new ThreadPrinter2();
        ThreadPrinter3 printer3 = new ThreadPrinter3();
        ThreadPrinter4 printer4 = new ThreadPrinter4();
        ThreadPrinter5 printer5 = new ThreadPrinter5();

        printer1.start();
        printer2.start();
        printer3.start();
        printer4.start();
        printer5.start();
        
        while (!scan.nextLine().equals("0")) { }
        System.out.println("Stoping printing server, please wait...\n");
       
        try {
            Printers.getInstance().StopPrinting();
            
            printer1.join();
            printer2.join();
            printer3.join();
            printer4.join();
            printer5.join();
            
            Thread.sleep(5000);
            
            System.out.println("Server shut down! You can now close this program and restart it to test it again!\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThreadController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
