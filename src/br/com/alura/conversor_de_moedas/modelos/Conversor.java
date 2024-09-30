package br.com.alura.conversor_de_moedas.modelos;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conversor {
    private String moedaBase;
    private String moedaDestino;
    private List<String> historicoConversoes;

    Gson gson = new Gson();

    public Conversor(String moedaBase, String moedaDestino) {
        this.moedaBase = moedaBase;
        this.moedaDestino = moedaDestino;
        this.historicoConversoes = new ArrayList<>();
    }

    public void converterMoeda(double peso) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String endereco = "https://v6.exchangerate-api.com/v6/ad77a962143cb1901c350d5f/pair/"
                + this.moedaBase + "/"
                + this.moedaDestino;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        MoedaOmdb moedaObjeto = gson.fromJson(json, MoedaOmdb.class);
        Moeda moeda = new Moeda(moedaObjeto.conversion_rate(), peso);
        double moedaConvertida = moeda.conversor();
        String feddback = peso +
                " [" +
                this.moedaBase +
                "] convertido/s em [" +
                this.moedaDestino +
                "] corresponde ao valor final de =>> " +
                moedaConvertida;
        System.out.println(feddback);

        LocalDate data = LocalDate.now();

        String novaConversao = data + ": " + peso + " " + this.moedaBase + " convertido para " + moedaConvertida + " " + this.moedaDestino + "\n";

        try (FileWriter arquivo = new FileWriter("conversoes.txt", true)) {
            arquivo.append(novaConversao);
        }
    }

    public void verHistoricoConversoes() {
        try {
            FileReader fileReader = new FileReader("conversoes.txt"); // Abre o arquivo para leitura
            BufferedReader bufferedReader = new BufferedReader(fileReader); // Torna a leitura mais eficiente

            String linha;
            while ((linha = bufferedReader.readLine()) != null) { // Lê linha por linha até o final do arquivo
                System.out.println(linha);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
