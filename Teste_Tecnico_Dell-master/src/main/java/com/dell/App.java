package com.dell;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws CsvException, IOException, InterruptedException {

        Scanner in = new Scanner(System.in);
        PrintToConsole print = new PrintToConsole();

        boolean exec = true;
        String chave;
        while (exec) {

            clear();
            // interface simples para usuario escolher qual consulta deseja
            System.out.println("Digite o numero referente a consulta");
            System.out.println("1 - Consultar por nome");
            System.out.println("2 - Consultar por codigo de barras");
            System.out.println("3 - Gerar grafico por classificacao");
            System.out.println("    ** consultar dados da LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS)/2020");
            System.out.println("0 - Sair");

            //usuario digita uma opcao e retornamos com a formatação presente no PrintToConsole
            int key = in.nextInt();
            switch (key) {
                case 1:
                    System.out.printf("Digite o nome do medicamento\n");
                    in.nextLine();
                    chave = in.nextLine();
                    print.printConsulta1(chave);
                    break;
                case 2:
                    System.out.printf("Digite o codigo de barras (dever conter 13 numeros)\n");
                    in.nextLine();
                    chave = in.nextLine();
                    print.printConsulta2(chave);
                    break;
                case 3:
                    print.printConsulta3();
                    in.nextLine();
                    break;
                case 0:
                    exec = false;
                default:
                    break;
            }

            System.out.println("\n**Presione Enter para continuar**");
            in.nextLine();
        }
    }

    //apenas perfumaria - só serve para limpar o console
    public static void clear() throws InterruptedException, IOException {
        // Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }
}
