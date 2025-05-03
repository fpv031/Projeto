package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;
        
        try {
            // Carrega as configurações do banco de dados a partir do arquivo db.properties
            Properties props = new Properties();
            FileInputStream inputStream = new FileInputStream("src/resources/db.properties");
            props.load(inputStream);
            
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            
            // Estabelece a conexão com o banco de dados MySQL
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
        
        return connection;
    }
}