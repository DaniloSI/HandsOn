/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizprimos;

import static java.lang.Math.sqrt;

/**
 *
 * @author danilo
 */
public class ThreadCalculaPrimos extends Thread {
    
    private Matriz m;
    
    public ThreadCalculaPrimos(Matriz m) {
        this.m = m;
    }
    
    
    @Override
    public void run(){
        int macrobloco;
        
        while ( (macrobloco = m.getMacroBloco()) < m.getQuantidadeMacrobloco() ) {
            int quantidadePrimo = 0;
            
            for(int i = 0 ; i < m.getLinhasMB() ; i++) {
                for(int j = 0 ; j < m.getColunasMB() ; j++) {
                    if ( isPrimo( m.getValor(macrobloco, i, j)) )
                        quantidadePrimo++;
                }
            }
            
            m.setQuantidadePrimos(quantidadePrimo);
        }
    }
    
    private boolean isPrimo(int valor) {
        if (valor <= 2)
            return true;
        
        for (int i = 2 ; i <= sqrt(valor) + 1 ; i++ )
            if (valor % i == 0) return false;
        
        
        
        return true;
    }
    
}
