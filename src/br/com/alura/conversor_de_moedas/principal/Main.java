package br.com.alura.conversor_de_moedas.principal;

import br.com.alura.conversor_de_moedas.modelos.Conversor;
import br.com.alura.conversor_de_moedas.modelos.Moeda;
import br.com.alura.conversor_de_moedas.modelos.MoedaOmdb;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner read = new Scanner(System.in);
        String moedabase = "";
        String moedaDestino = "";
        Conversor conversor = null;
        int opcao = 0;
        while(opcao != 7) {
            System.out.println("**************************************************************");
            System.out.println("Seja bem vindo(a) ao conversor de moedas!");
            System.out.println("""
                1 - Dólar =>> Real Brasileiro
                2 - Real Brasileiro =>> Peso Argentino
                3 - Rial do Catar =>> Real Brasileiro
                4 - Euro =>> Real Brasileiro
                5 - Libra síria =>> Real Brasileiro
                6 - Bolívar Soberano venezuelano =>> Real Brasileiro
                7 - Sair
                **************************************************************
                """);
            try {
                System.out.println("Escolha uma opção válida:");
                opcao = read.nextInt();

                if(opcao == 7) {
                    break;
                }

                switch (opcao) {
                    case 1:
                        moedabase = "USD";
                        moedaDestino = "BRL";
                        break;
                    case 2:
                        moedabase = "BRL";
                        moedaDestino = "ARS";
                        break;
                    case 3:
                        moedabase = "QAR";
                        moedaDestino = "BRL";
                        break;
                    case 4:
                        moedabase = "EUR";
                        moedaDestino = "BRL";
                        break;
                    case 5:
                        moedabase = "SYP";
                        moedaDestino = "BRL";
                        break;
                    case 6:
                        moedabase = "VES";
                        moedaDestino = "BRL";
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }

                System.out.println("Digite o valor para conversão:");
                double valor = read.nextDouble();

                conversor = new Conversor(moedabase, moedaDestino);
                conversor.converterMoeda(valor);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("""
                Deseja ver o histórico de conversões:
                1 - Sim
                2 - Não""");
        int confirm = read.nextInt();
        
        if(confirm == 1) {
            conversor.verHistoricoConversoes();
        } else if (confirm == 2) {
            System.out.println("Programa finalizado!");
        } else {
            System.out.println("ERRO!");
        }
    }
}
