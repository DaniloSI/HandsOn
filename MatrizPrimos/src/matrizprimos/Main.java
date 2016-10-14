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
        
        matriz.setQuantidadeThreads(4);
        System.out.println("Quantidade de Threads: " + matriz.getQuantidadeThreads());
        
        Random random = new Random();
        for(int linha = 0 ; linha < 20000 ; linha ++) {
            for(int coluna = 0 ; coluna < 20000 ; coluna ++) {
                matriz.setValor(linha, coluna, /*984037*/ random.nextInt(100000));
            }
        }
        
        matriz.setValor(0, 0, 1);
        matriz.setValor(0, 1, 5);
        
        matriz.setMacroBloco(5, 5);
        System.out.println("Quantidade total de Macroblocos: " + matriz.getQuantidadeMacrobloco());
        
        //matriz.printMatriz();
        
        System.out.println("Quantidade de primos encontrados: " + matriz.calculaPrimos() );
        
    }
    
}
