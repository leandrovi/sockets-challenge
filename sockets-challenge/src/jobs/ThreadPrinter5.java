/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import entities.Printers;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandro
 */
public class ThreadPrinter5 extends Thread {

    @Override
    public void run() {
       
        while(
            Printers.getInstance().GetPrint() ||
            Printers.getInstance().GetPrinterMessage(5).size() > 0)
        {
            for (int x = 0; x < Printers.getInstance().GetPrinterMessage(5).size(); x++) {
                System.out.println("Printing in Printer #5 :" + Printers.getInstance().GetPrinterMessage(5).get(x));
                
                Printers.getInstance().GetPrinterMessage(5).remove(x);
            }
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadPrinter5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
