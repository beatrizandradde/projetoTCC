package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {
 
    protected Connection conexao;
 
    public AbstractDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/projetocemiterio", "root", "root");
        } catch (SQLException e) {
            System.out.println("N�o foi poss�vel conectar ao banco de dados: \n\t" + e);
        }
    }
 
    public void fechar() {
        try {
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao finalizar conex�o com banco de dados: \n\t" + e);
        }
    }
 
}