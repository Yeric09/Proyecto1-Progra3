/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicajuego;

/**
 *
 * @author david
 */

import Observer.Model;
import Observer.View;
import Observer.Controller;


public class PracticaJuego {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException  {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model,view);
        view.setVisible(true);
        model.start();
        
        
    }
    
}
