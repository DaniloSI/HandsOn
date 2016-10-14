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

    private Matriz matriz;

    public ThreadCalculaPrimos(Matriz matrizParam) {
        matriz = matrizParam;
    }

    @Override
    public void run(){
        int macrobloco;

        while ( (macrobloco = matriz.getMacroBloco()) < matriz.getQuantidadeMacrobloco() ) {
            int quantidadePrimo = 0;

            for(int linha = 0 ; linha < matriz.getLinhasMacroBloco() ; linha++) {
                for(int coluna = 0 ; coluna < matriz.getColunasMacroBloco() ; coluna++) {
                    if ( isPrimo( matriz.getValor(macrobloco, linha, coluna)) )
                        quantidadePrimo++;
                }
            }

            matriz.setQuantidadePrimos(quantidadePrimo);
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
