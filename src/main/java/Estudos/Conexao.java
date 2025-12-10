package Estudos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private String url = "jdbc:mysql://localhost:3306/testes";
    private String usuario = "root";
    private String senha = "root";
    public Connection conexao = null;


    public Connection conectar() {

        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conexao;
    }
}
