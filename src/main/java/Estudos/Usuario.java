package Estudos;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void deletar(Usuario usuario){

        String sql = "DELETE FROM usuario WHERE ID = ?";

        Conexao conexaoBD = new Conexao();
        Connection conn = conexaoBD.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();

            System.out.println("Usuário deletado com sucesso");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public void salvar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, cpf, nascimento) VALUES (?, ?, ?)";

        Conexao conexaoBD = new Conexao();
        Connection conn = conexaoBD.conectar();

        if (conn == null) {
            System.out.println("Erro: conexão nula");
            return;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());

            stmt.setDate(3, new java.sql.Date(usuario.getNascimento().getTime()));

            stmt.executeUpdate();

            System.out.println("Usuário salvo com sucesso!");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }
}
