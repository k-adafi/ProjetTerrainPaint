package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class Database {
	
	public static Connection ConnectDb() {
	        
	        Properties props = new Properties();
	        try (InputStream input = Database.class.getResourceAsStream("/dbconfig.properties")) {
	            
	            // Load the properties file
	            if (input == null) {
	                System.out.println("Désole, le fichier dbconfig.properties n'existe pas!");
	                return null;
	            }
	            props.load(input);
	
	            // Get the property values
	            String url = props.getProperty("db.url");
	            String user = props.getProperty("db.user");
	            String password = props.getProperty("db.password");
	
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection cn = (Connection) DriverManager.getConnection(url, user, password);
	            System.out.println("Connexion reussie");
	            return cn;
	            
	        } catch (IOException e) {
	            System.out.println("Erreur de lecture du fichier de configuration");
	            e.printStackTrace();
	            return null;
	            
	        } catch (ClassNotFoundException e) {
	            System.out.println("Échec de chargement du driver JDBC");
	            e.printStackTrace();
	            return null;
	            
	        } catch (SQLException e) {
	            System.out.println("Échec de connexion à la base de données");
	            e.printStackTrace();
	            return null;
	        }
	 }

}
