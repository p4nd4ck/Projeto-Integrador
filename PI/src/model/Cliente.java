package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String contato;
    private String endereco;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    private List<Quitacao> quitacoes;
    private List<Divida> dividas;
    private double dividaOuSaldo;

    public Cliente() {
    }

    public Cliente(int id, String nome, String contato, String endereco, String cpf, String rg, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.quitacoes = new ArrayList<>();
        this.dividas = new ArrayList<>();
        this.dividaOuSaldo = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Quitacao> getQuitacoes() {
        return quitacoes;
    }

    public void setQuitacoes(List<Quitacao> quitacoes) {
        this.quitacoes = quitacoes;
    }

    public List<Divida> getDividas() {
        return dividas;
    }

    public void setDividas(List<Divida> dividas) {
        this.dividas = dividas;
    }

    public double getDividaOuSaldo() {
        return dividaOuSaldo;
    }

    public void setDividaOuSaldo(double dividaOuSaldo) {
        this.dividaOuSaldo = dividaOuSaldo;
    }
    
}
