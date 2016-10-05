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
    private int linhasMB, colunasMB;
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
        this.linhasMB = linhas;
        this.colunasMB = colunas;
        quantidadeMacrobloco = (this.colunasMatriz / colunas) * (this.linhasMatriz / linhas);
        quantidadeMacrobloco++;
    }
    
    public void printMatriz() {
        for(int i = 0 ; i < this.linhasMatriz ; i ++) {
            for(int j = 0 ; j < this.colunasMatriz ; j ++) {
                System.out.print(matriz[i][j] + "   ");
            }
            System.out.println("");
        }
    }
    
    public void setValor(int i, int j, int valor) {
        matriz[i][j] = valor;
    }
    
    public int getValor(int mb, int i, int j) {
        int linha = ( (mb - 1) / (this.colunasMatriz / colunasMB) ) * linhasMB;
        int coluna = ( (mb - 1) % (this.colunasMatriz / colunasMB) ) * colunasMB;
        
        return matriz[linha + i][coluna + j];
    }

    public int getLinhasMB() {
        return linhasMB;
    }

    public int getColunasMB() {
        return colunasMB;
    }
    
    public synchronized int getMacroBloco() {
        int mb = this.macroBlocoCorrente;
        this.macroBlocoCorrente++;
        return mb;
    }
    
    public int getQuantidadeMacrobloco() {
        return this.quantidadeMacrobloco;
    }
    
    public synchronized void setQuantidadePrimos(int valor) {
        this.quantidadePrimos += valor;
    }
    
    public int calculaPrimos() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        Iterator<Thread> iterator;
        
        for(int i = 0 ; i < this.quantidadeThreads ; i++) {
            Thread thread = new ThreadCalculaPrimos(this);
            threads.add(thread);
            thread.start();
        }
        
        iterator = threads.iterator();
        
        while (iterator.hasNext()) {
            iterator.next().join();
        }
        
        return this.quantidadePrimos;
    }

}
