package Estudos;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {

        // tabela criada
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {
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

        //insercao e remocao de usuarios

        LocalDate dataLocal = LocalDate.parse("2025-07-05");
        java.util.Date data = java.util.Date.from(
                dataLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        Usuario rafael = new Usuario(1,"Rafael","999.999.999-99",data);
        rafael.salvar(rafael);
        rafael.deletar(rafael);


    }
}