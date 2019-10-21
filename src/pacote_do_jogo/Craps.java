/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote_do_jogo;

import java.util.Random;

/**
 *
 * @author Regis_Maximo
 */
public class Craps {
    // cria um gerador de números aleatórios para uso no método rollDice
    private Random randomNumbers = new Random();
    
    //enumeração que representa o status do jogo
    private enum Status { CONTINUE, WON, LOST };
    
    // constantes que representam lançamentos comuns dos dados
    private final static int SNAKE_EYES = 2;
    private final static int TREY = 3;
    private final static int SEVEN = 7;
    private final static int YO_LEVEN = 11;
    private final static int BOX_CARS = 12;
    
    // joga uma partida de craps
    public void play() {
        int myPoint = 0; // pontos se não ganhar ou perder na 1° rolagem
        Status gameStatus; // pode conter CONTINUE, WON ou LOST
        
        int sumOfDice = rollDice(); // primeira rolagem dos dados
        
        // determina o status do jogo e a pontuação com base no primeiro lançamento
        switch(sumOfDice) {
            case SEVEN: // ganha com 7 no primeiro lançamento
            case YO_LEVEN: // ganha com 11 no primeiro lançamento
                gameStatus = Status.WON;
                break;
            case SNAKE_EYES: // perde com 2 no primeiro lançamento
            case TREY: // perde com 3 no primeiro lançamento
            case BOX_CARS: // perde com 12 no primeiro lançamento
                gameStatus = Status.LOST;
                break;
            default: // não ganhou e nem perdeu, portanto registra a pontuação
                gameStatus = Status.CONTINUE; // jogo não terminou
                myPoint = sumOfDice; // obtém a pontuação e informa
                System.out.printf("Point is %d\n", myPoint);
                break; // opcional no final do switch
        } // switch final
        
        // enquanto o jogo não oestiver completo
        while(gameStatus == Status.CONTINUE) { // nem WON nem LOST
            sumOfDice = rollDice(); // lança os dados novamente
            
            //determina o status do jogo
            if(sumOfDice == myPoint) // vitória porr pontuação
                gameStatus = Status.WON;
            else
                if(sumOfDice == SEVEN) // perde obtendo sete antes de atingir pontução
                    gameStatus = Status.LOST;
        } // fim do while
        
        // exibe uma mensagem se ganhou ou perdeu
        if(gameStatus == Status.WON)
            System.out.println("Player Wins");
        else
            System.out.println("Player Loses");
    } // fim do método play
    
    // lança os dados, calcula a soma e exibe os resultados
    public int rollDice() {
        // seleciona valores aleatórios dos dados
        int die1 = 1 + randomNumbers.nextInt(6);
        int die2 = 1 + randomNumbers.nextInt(6);
        
        int sum = die1 + die2; // soma dos valores dos dados
        
        // exibe os resultados desse lançamento
        System.out.printf("Player Rolled %d + %d = %d\n", die1, die2, sum);
        
        return sum; // retorna a soma dos dados
    } // fim do método rollDice
} // fim da classe craps
