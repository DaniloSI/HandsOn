/*
 * Essa classe tem como objetivo calcular a quantidade de primos contida em um macrobloco de uma matriz.
 */
package matrizprimos.domain;

import static java.lang.Math.sqrt;

/**
 * Essa classe é calcula a quantidade de primos e pode ser inicializada como uma Thread.
 *
 * @author Danilo de Oliveira
 */
public class ThreadCalculaPrimos extends Thread {
    
    private Matriz matriz;
    
    public ThreadCalculaPrimos(Matriz matriz) {
        this.matriz = matriz;
    }
    
    @Override
    public void run(){
        int identificadorMacroBloco;
        
        while ( (identificadorMacroBloco = matriz.getMacroBloco()) < matriz.getQuantidadeMacrobloco() ) {
            int quantidadePrimo = 0;
            
            for(int linha = 0; linha < matriz.getLinhasMacroBloco(); linha++) {
                for(int coluna = 0; coluna < matriz.getColunasMacroBloco(); coluna++) {
                    if (isPrimo( matriz.getValor(identificadorMacroBloco, linha, coluna)))
                        quantidadePrimo++;
                }
            }
            
            matriz.setQuantidadePrimos(quantidadePrimo);
        }
    }

    /**
     * Recebe um valor e verifica se é primo
     * @param valor Valor que será verificado.
     * @return true se for primo e false se não for primo.
     */
    private boolean isPrimo(int valor) {
        boolean isPrimo = true;

        for (int divisorCorrente = 2 ; (divisorCorrente <= sqrt(valor) + 1 && isPrimo && valor > 2); divisorCorrente++ ) {
            if (valor % divisorCorrente == 0) {
                isPrimo = false;
            }
        }

        return isPrimo;
    }
    
}
