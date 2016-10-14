/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizprimos;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author danilo
 */
public class Matriz {
    
    private int linhasMatriz, colunasMatriz;
    private int linhasMacroBloco, colunasMacroBloco;
    private int[][] matriz;
    private int quantidadeMacrobloco;
    private int macroBlocoCorrente = 1;
    private int quantidadeThreads = 1;
    private int quantidadePrimos;
    
    public Matriz(int linhas, int colunas) {
        this.linhasMatriz = linhas;
        this.colunasMatriz = colunas;
        
        matriz = new int[linhas][colunas];
    }

    public void setQuantidadeThreads(int quantidadeThreads) {
        this.quantidadeThreads = quantidadeThreads;
    }

    public int getQuantidadeThreads() {
        return quantidadeThreads;
    }
    
    public void setMacroBloco(int linhas, int colunas) {
        this.linhasMacroBloco = linhas;
        this.colunasMacroBloco = colunas;
        quantidadeMacrobloco = (colunasMatriz / colunas) * (linhasMatriz / linhas);
        quantidadeMacrobloco++;
    }
    
    public void printMatriz() {
        for(int linha = 0 ; linha < linhasMatriz ; linha ++) {
            for(int coluna = 0 ; coluna < colunasMatriz ; coluna ++) {
                System.out.print( matriz[linha][coluna] + "   " );
            }
            System.out.println( "" );
        }
    }
    
    public void setValor(int linha, int coluna, int valor) {
        matriz[linha][coluna] = valor;
    }
    
    public int getValor(int macroBloco, int paramLinha, int paramColuna) {
        int linha = ( (macroBloco - 1) / (colunasMatriz / colunasMacroBloco) ) * linhasMacroBloco;
        int coluna = ( (macroBlocob - 1) % (colunasMatriz / colunasMacroBloco) ) * colunasMacroBloco;
        
        return matriz[linha + paramLinha][coluna + paramColuna];
    }

    public int getLinhasMacroBloco() {
        return linhasMacroBloco;
    }

    public int getColunasMacroBloco() {
        return colunasMacroBloco;
    }
    
    public synchronized int getMacroBloco() {
        int macroBloco = macroBlocoCorrente;
        macroBlocoCorrente++;
        return macroBloco;
    }
    
    public int getQuantidadeMacrobloco() {
        return quantidadeMacrobloco;
    }
    
    public synchronized void setQuantidadePrimos(int valor) {
        quantidadePrimos += valor;
    }
    
    public int calculaPrimos() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        Iterator<Thread> iterator;
        
        for(int i = 0 ; i < quantidadeThreads ; i++) {
            Thread thread = new ThreadCalculaPrimos(this);
            threads.add(thread);
            thread.start();
        }
        
        iterator = threads.iterator();
        
        while (iterator.hasNext()) {
            iterator.next().join();
        }
        
        return quantidadePrimos;
    }

}
