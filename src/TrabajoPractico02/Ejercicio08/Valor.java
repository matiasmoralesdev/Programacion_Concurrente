/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico02.Ejercicio08;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Valor {

    private int total;

    public Valor(int tot) {
        this.total = tot;

    }

    public void setTotal(int tot) {
        
            this.total = tot;
        
    }

    public int getTotal() {
        return this.total;
    }
}
