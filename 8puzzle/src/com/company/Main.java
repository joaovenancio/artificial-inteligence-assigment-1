package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean rodando = true;
        Scanner input = new Scanner(System.in);

        System.out.println("########################################################################");
        System.out.println("#                               8Puzzle                                #");
        System.out.println("########################################################################");


        while (rodando) {
            System.out.println("1 - Custo Uniforme (sem heurística)");
            System.out.println("2 - A* com uma heurística simples");
            System.out.println("3 - A* com a heurística mais precisa");
            System.out.println("4 - Sair do programa");
            System.out.println("Digite a opção desejada: ");
            int in = input.nextInt();
            switch (in) {
                case 1:
                    EightPuzzle.getInstance().resolveQ1(Node.testNode1);
                    break;

                case 2:
                    EightPuzzle.getInstance().resolveQ2(Node.testNode1);
                    break;

                case 3:
                    EightPuzzle.getInstance().resolveQ3(Node.testNode1);
                    break;

                case 4:
                    rodando = false;
                    break;

                    default:
                        System.out.println("Por favor, digite uma opção válida.");

            }

        }


    }
}
