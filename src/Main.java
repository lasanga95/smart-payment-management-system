import java.util.Scanner;

/**
 * Created by Lasanga Madushan on 3/12/2017.
 */
public class Main {
    public static void main(String[] args) throws  Exception{
        Shop shop = new Shop("aShop", 1);
        Cashier_device cashier_device = new Cashier_device(141,shop);

        Scanner scan=new Scanner(System.in);
        System.out.print("Enter the item ID : ");
        int itemID = scan.nextInt();
        cashier_device.acceptCash(itemID);

    }
}
