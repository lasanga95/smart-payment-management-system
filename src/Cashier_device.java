import java.util.ArrayList;

/**
 * Created by Lasanga Madushan on 3/10/2017.
 */
public class Cashier_device {
    private int id;
    private Shop shop;
    private  int shopId;
    private Serial serial;
    Cashier_device(int id, Shop shop) {
        this.id = id;
        this.shop=shop;
        shopId=shop.getId();
        serial=new Serial("3");
    }
    public void acceptCash(int itemId) throws Exception {
        try {
            long cdId=0;
            System.out.println("Tap the card");
            while (cdId==0) {
                cdId = serial.read();
            }
            System.out.println(cdId);
            ArrayList<String> userDetails = Database.get(cdId, "customer");
            ArrayList<String> shopDetails = Database.get(shopId, "Shops");
            double shopMoney = Double.parseDouble(shopDetails.get(2));
            double userMoney = Double.parseDouble(userDetails.get(2));
            System.out.printf("Initial Balance Rs: %.2f \n", userMoney);
            double price = shop.getPrice(itemId);

            if (price <= userMoney) {
                userMoney -= price;
                shopMoney += price;
                Database.updateMoney(cdId, (userMoney),"customer");
                Database.updateMoney(shopId, (shopMoney),"Shops");
                System.out.printf(userDetails.get(0) + " " + userDetails.get(1) + " have Rs: %.2f \n", userMoney);
            } else {
                System.out.println("You don't have enough money!");
            }
        }catch (Exception e){
            System.out.println("Cashier_device.acceptCash: "+e);
        }
    }
}
