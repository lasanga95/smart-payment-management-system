import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * Created by Lasanga Madushan on 3/10/2017.
 */
public class Shop {
    private String name;
    private int id;
    private Database database;
    Shop(String name, int id){
        this.name=name;
        this.id=id;
    }
    public int getId(){return id;}

    public Connection getDatabaseConnection(){
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/shopDatabase";
            String username ="user";
            String password = "welcome";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");
            return conn;
        }catch (Exception e){
            System.out.println("shopDatabase.getConnection: "+e);
        }
        return null;
    }
    public double getPrice(int id) {
        final int var1=id;
        try {
            Connection con = getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement("SELECT Price FROM Items WHERE id='"+var1+"'");

            ResultSet result = statement.executeQuery();
            result.next();
            return Double.parseDouble(result.getString("Price"));
        }catch (Exception e){
            System.out.println("sDatabase.get: "+e);
            return 0;
        }
    }
}
