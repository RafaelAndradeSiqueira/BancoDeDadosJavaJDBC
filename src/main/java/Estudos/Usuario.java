package Estudos;

import java.util.Date;

public class Usuario {

    private Integer id;
    private String nome;
    private String cpf;
    private Date nascimento;

    public Usuario(Integer id, String nome, String cpf, Date nascimento) {
        this.setId(id);
        this.setNome(nome);
        this.setCpf(cpf);
        this.setNascimento(nascimento);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

}
