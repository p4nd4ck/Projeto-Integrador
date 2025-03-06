package br.com.unifeob.model;
import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nome;
    private String contato;
    private String endereco;
    private String cpf;
    private String rg;
    private String dataNascimento;
    private double divida;
    private double saldoComLoja;  // Novo campo para armazenar saldo com a loja
    private ArrayList<Produto> produtos;
    private ArrayList<String> datasDivida;
    private ArrayList<Double> valoresQuitados;
    private ArrayList<String> datasQuitacao;

    public Cliente(int id, String nome, String contato, String endereco, String cpf, String rg, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.produtos = new ArrayList<>();
        this.datasDivida = new ArrayList<>();
        this.divida = 0.0;
        this.saldoComLoja = 0.0;  // Inicializa o saldo com a loja
        this.valoresQuitados = new ArrayList<>();
        this.datasQuitacao = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getContato() {
        return contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void adicionarDivida(Produto produto, String data) {
        this.produtos.add(produto);
        this.datasDivida.add(data);
        this.divida += produto.getValor();
    }

    public void quitarDivida(double valor, String dataQuitacao) {
        if (valor > divida) {
            saldoComLoja += (valor - divida);  // Calcula o saldo excedente e adiciona ao saldo com a loja
            divida = 0;  // A dívida é quitada
        } else {
            divida -= valor;
        }

        if (divida < 0) divida = 0;  // Impede que a dívida seja negativa
        this.valoresQuitados.add(valor);
        this.datasQuitacao.add(dataQuitacao);
    }

    public double getDivida() {
        return divida;
    }

    public double getSaldoComLoja() {
        return saldoComLoja;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<String> getDatasDivida() {
        return datasDivida;
    }

    public ArrayList<Double> getValoresQuitados() {
        return valoresQuitados;
    }

    public ArrayList<String> getDatasQuitacao() {
        return datasQuitacao;
    }
}
