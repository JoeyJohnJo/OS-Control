import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    Os metodos sao estaticos para que nao ocorra multiplas tentativas de conexao com o banco de dados
    Todos os queries vao usar a mesma conexao, mudando apenas o comando de SQL
 */
class ServerConnection
{
    private static Connection connection; // Criado para conectar no banco de dados

    // Dados. Mudar para conectar em outros bancos de dados
    private String url = "jdbc:sqlite:lib/bancodedados.sbd.db";

    //Tentativa de conexao ao banco de dados
    //INVOCAR APENAS NO MAIN PARA NAO CRIAR VARIAS CONEXOES
    private void connect() throws SQLException{
        connection = DriverManager.getConnection(url);
        System.out.println("Connectado com banco de dados SQLite");
        // print to console for testing purposes
    }

    //Invocar este metodo em outras classes para executar comandos SELECT no banco de dados
    //a String command e onde vai o codigo de SQL
    static ResultSet createQueryStatement(String command) throws SQLException{
        Statement statement = connection.createStatement();
        return statement.executeQuery(command);
    }

    //Invocar este metodo em outras classes para executar comandos INSERT/UPDATE/DELETE no banco de dados
    static void SQLStatement(String command) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(command);
    }

    ServerConnection() {
        try{
            connect();
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}