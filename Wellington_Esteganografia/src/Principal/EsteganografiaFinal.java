/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Principal;

import Esteganografia.Visualizador;
import javax.swing.UIManager;

/**
 *
 * @author Wellington Marcolino
 */
public class EsteganografiaFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e);
        }
        new Visualizador().show();
    }    
}
