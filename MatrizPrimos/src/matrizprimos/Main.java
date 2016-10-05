/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizprimos;

import java.util.Random;

/**
 *
 * @author danilo
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        Matriz matriz = new Matriz(20000, 20000);
        
        matriz.setQuantidadeThreads(1);
        
        Random random = new Random();
        for(int i = 0 ; i < 20000 ; i ++) {
            for(int j = 0 ; j < 20000 ; j ++) {
                matriz.setValor(i, j, /*984037*/ random.nextInt(10000));
            }
        }
        
        matriz.setValor(0, 0, 1);
        matriz.setValor(0, 1, 5);
        
        matriz.setMacroBloco(5, 5);
        
        
        //matriz.printMatriz();
        
        System.out.println( matriz.calculaPrimos() );
        
        
    }
    
}
