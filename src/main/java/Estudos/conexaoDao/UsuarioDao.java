package Estudos.conexaoDao;

import Estudos.Usuario;

import java.sql.*;

public class UsuarioDao {


    public Usuario buscar(int id) {

        String sql = "SELECT * FROM USUARIO WHERE ID = ?";

        Conexao conexaoBD = new Conexao();
        Connection conn = conexaoBD.conectar();

        Usuario usuario = null;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"),rs.getString("nome"),rs.getString("cpf"),rs.getDate("nascimento"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return usuario;
    }


    public void atualizar(Usuario usuario){
        String sql = "UPDATE USUARIO SET nome = ?, cpf = ?, nascimento = ? WHERE ID = ?";

        Conexao conexaoBD = new Conexao();
        Connection conn = conexaoBD.conectar();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, (Date) usuario.getNascimento());
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();

            System.out.println("Usuário atualizado com sucesso");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }

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


    public Integer salvar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, cpf, nascimento) VALUES (?, ?, ?)";

        Conexao conexaoBD = new Conexao();
        Connection conn = conexaoBD.conectar();

        Integer idGerado = null;

        try {
            PreparedStatement stmt =
                    conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, new java.sql.Date(usuario.getNascimento().getTime()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                idGerado = rs.getInt(1);
            }

            rs.close();
            stmt.close();
            conn.close();

            System.out.println("Usuário salvo com sucesso! ID: " + idGerado);

        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }

        return idGerado;
    }

    public void criarTabela(){

        try {

            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();

            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "cpf VARCHAR(14) NOT NULL, " +
                    "nascimento DATE NOT NULL" +
                    ")";

            stmt.executeUpdate(sql);
            System.out.println("Tabela criada com sucesso!");

            conn.close();
        } catch (Exception e) {

            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }

    }

}
