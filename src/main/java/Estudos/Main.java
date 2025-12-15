package Estudos;

import Estudos.conexaoDao.Conexao;
import Estudos.conexaoDao.UsuarioDao;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        UsuarioDao dao = new UsuarioDao();
        dao.criarTabela();

        LocalDate dataLocal = LocalDate.parse("1999-07-05");
        Date nascimento = Date.from(
                dataLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        Usuario usuario = new Usuario(
                null,
                "Rafael",
                "999.999.999-99",
                nascimento
        );

        Integer idGerado = dao.salvar(usuario);


        Usuario usuarioBuscado = dao.buscar(idGerado);

        if (usuarioBuscado != null) {
            System.out.println("Usuário encontrado: " + usuarioBuscado.getNome());
        } else {
            System.out.println("Usuário não encontrado");
        }

        if (usuarioBuscado != null) {
            Usuario usuarioAtualizado = new Usuario(
                    usuarioBuscado.getId(),
                    "Rafael Andrade",
                    "111.111.111-11",
                    usuarioBuscado.getNascimento()
            );

            dao.atualizar(usuarioAtualizado);
        }


        if (usuarioBuscado != null) {
            dao.deletar(usuarioBuscado);
        }
    }
}
