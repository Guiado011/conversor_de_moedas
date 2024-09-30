package br.com.alura.conversor_de_moedas.modelos;

public class Moeda {
    private double valor;
    private double peso;

    public Moeda(double moedaOmdb, double peso) {
        this.valor = moedaOmdb;
        this.peso = peso;
    }

    public double conversor() {
        double moedaConvertida = this.peso * this.valor;
        return moedaConvertida;
    }

    @Override
    public String toString() {
        return  "valor = " + valor +
                ", peso = " + peso;
    }
}
