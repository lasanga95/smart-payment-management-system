import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Lasanga Madushan on 3/12/2017.
 */
public class Database {
    public static ArrayList<String> get(long id, String table) throws Exception{
        final long var1=id;
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT FIRST_NAME,LAST_NAME,Balance FROM "+table+" WHERE id='"+var1+"'");

            ResultSet result = statement.executeQuery();

            ArrayList<String> array = new ArrayList<String>();
            while (result.next()) {
                System.out.print(result.getString("FIRST_NAME"));
                System.out.print(" ");
                System.out.print(result.getString("LAST_NAME"));
                System.out.print(" ");
                System.out.println(result.getString("Balance"));

                array.add(result.getString("FIRST_NAME"));
                array.add(result.getString("LAST_NAME"));
                array.add(result.getString("Balance"));
            }
            System.out.println("All records have been selected!");
            return array;
        }catch (Exception e){
            System.out.println("Database.get: "+e);
        }
        return null;
    }

    public static void updateMoney(long id, double money, String table) throws Exception{
        final long var1 = id;
        final double var2 = money;
        try {
            Connection con = getConnection();
            PreparedStatement update = con.prepareStatement("UPDATE "+table+" set Balance='"+var2+"' WHERE id='"+var1+"'");
            update.executeUpdate();
        }catch (Exception e){
            System.out.println("Database.update: "+e);
        }
        finally {
            System.out.println("Database updated successfully.");
        }
    }

    public static void post() throws Exception{
        final String var1 = "Lasanga";
        final String var2 = "Madushan";
        try {
            Connection con = getConnection();
            PreparedStatement post = con.prepareStatement("INSERT INTO mytable (FIRST_NAME, LAST_NAME) VALUES ('"+var1+"', '"+var2+"')");
            post.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Insert Completed.");
        }
    }

    public static void creatTable() throws Exception{
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS customer ( id INT NOT NULL, FIRST_NAME VARCHAR(20) NOT NULL, LAST_NAME VARCHAR(20), Balance FLOAT DEFAULT 50, PRIMARY KEY (id) );");
            create.executeUpdate();

        }catch (Exception e){System.out.println(e);}
        finally {
            System.out.println("Functon complete.");
        }
    }

    public static Connection getConnection() throws Exception{
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/accounts";
            String username ="user";
            String password = "welcome";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");
            return conn;
        }catch (Exception e){
            System.out.println("Database.getConnection: "+e);
        }
        return null;
    }
}
