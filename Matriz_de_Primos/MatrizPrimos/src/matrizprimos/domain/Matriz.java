/*
 * Matriz
 *
 * Versao: 1.0
 *
 * Data de criacao: 21/10/2016
 *
 */

package matrizprimos.domain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * Essa classe serve para armazenar uma matriz de numeros inteiros e calcular a quantidade de primos contidas nela.
 * Para realizar o calculo de numeros primos, pode-se utilizar mais de uma thread, ou seja, processamento concorrente.
 * Para utilizar o processamento concorrente, essa classe possui uma definicao de macroblocos que sao submatrizes da
 * matriz total. Cada thread fica responsavel por um macrobloco de cada vez.
 *
 * @author Danilo de Oliveira
 * @version 1.0
 */
public class Matriz {
    
    private int linhasMatriz, colunasMatriz;
    private int linhasMacroBloco, colunasMacroBloco;
    private int[][] matriz;
    private int quantidadeMacrobloco;
    private int macroBlocoCorrente;
    private int quantidadeThreads;
    private int quantidadePrimos;
    
    public Matriz(int linhas, int colunas) {
        linhasMatriz = linhas;
        colunasMatriz = colunas;
        macroBlocoCorrente = 1;
        quantidadeThreads = 1;
        
        matriz = new int[linhas][colunas];
    }

    /**
     *
     * @param quantidadeThreads Quantidade de threads que serão utilizadas
     */
    public void setQuantidadeThreads(int quantidadeThreads) {
        this.quantidadeThreads = quantidadeThreads;
    }

    public int getQuantidadeThreads() {
        return quantidadeThreads;
    }

    /**
     * Definição da dimensão que cada macrobloco da matriz terá.
     * @param linhas Tamanho das linhas do macrobloco.
     * @param colunas Tamanho das colunas do macrobloco.
     */
    public void setMacroBloco(int linhas, int colunas) {
        linhasMacroBloco = linhas;
        colunasMacroBloco = colunas;
        quantidadeMacrobloco = (colunasMatriz / colunas) * (linhasMatriz / linhas);
        quantidadeMacrobloco++;
    }


    /**
     * Imprime a matriz.
     */
    public void printMatriz() {
        for(int linha = 0 ; linha < linhasMatriz ; linha ++) {
            for(int coluna = 0 ; coluna < colunasMatriz ; coluna ++) {
                System.out.print(matriz[linha][coluna] + "   ");
            }
            System.out.println("");
        }
    }
    
    public void setValor(int linha, int coluna, int valor) {
        matriz[linha][coluna] = valor;
    }

    /**
     * Esse metodo serve para ler um valor em uma determinada posição da matriz.
     * @param identificadorMacrobloco É o número do macrobloco sendo entre 1 e n, tal que n é a quantidade de macroblocos.
     * @param linha É referente à linha do macrobloco e não da matriz.
     * @param coluna É referente à coluna do macrobloco e não da matriz.
     * @return valor É retornado o valor armazenado na matriz.
     */
    public int getValor(int identificadorMacrobloco, int linha, int coluna) {
        int linhaResultanteMatriz = ( (identificadorMacrobloco - 1) / (colunasMatriz / colunasMacroBloco) ) * linhasMacroBloco;
        int colunaResultanteMatriz = ( (identificadorMacrobloco - 1) % (colunasMatriz / colunasMacroBloco) ) * colunasMacroBloco;
        int valor = matriz[linhaResultanteMatriz + linha][colunaResultanteMatriz + coluna];

        return valor;
    }

    public int getLinhasMacroBloco() {
        return linhasMacroBloco;
    }

    public int getColunasMacroBloco() {
        return colunasMacroBloco;
    }

    /**
     * Esse método serve para uma Thread pegar um macrobloco para trabalhar nele. É sincronizado par aque não haja condição
     * de corrida.
     *
     * @return Retorna o identificador do macrobloco.
     */
    public synchronized int getMacroBloco() {
        int identificadorMacroBloco = macroBlocoCorrente;
        macroBlocoCorrente++;

        return identificadorMacroBloco;
    }
    
    public int getQuantidadeMacrobloco() {
        return quantidadeMacrobloco;
    }

    /**
     * É usado para uma Thread registrar a quantidade de primos que encontrou. O valor é somado para gerar o total.
     * @param valor É a quantidade de primos que uma Thread encontrou em um macrobloco.
     */
    public synchronized void setQuantidadePrimos(int valor) {
        quantidadePrimos += valor;
    }


    /**
     * Esse método serve para calcular a quantidade de primos na matriz toda.
     * @return Quantidade total de primos armazenados na matriz.
     * @throws InterruptedException
     */
    public int calculaPrimos() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        Iterator<Thread> iterator;
        
        for(int count = 0; count < quantidadeThreads; count++) {
            Thread thread = new ThreadCalculaPrimos(this);
            threads.add(thread); // Armazena cada Thread em um vetor.
            thread.start();
        }
        
        iterator = threads.iterator();
        
        while (iterator.hasNext()) {
            iterator.next().join(); // Espera todas as Threads terminarem e elas terminam quando não há mais macroblocos
        }                           // disponíveis. Aqui é um ponto de junção das Threads.
        
        return quantidadePrimos;
    }

}
