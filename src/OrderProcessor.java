import java.util.ArrayList;

public class OrderProcessor {

    static class Product {
        String name;
        String category;
        double price;

        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }
    }

    public static void main(String[] args) {

        ArrayList<Product> orderList = new ArrayList<>();
        orderList.add(new Product("Laptop", "Electronics", 1200.0));
        orderList.add(new Product("Smartphone", "Electronics", 800.0));
        orderList.add(new Product("T-Shirt", "Apparel", 25.0));
        orderList.add(new Product("Jeans", "Apparel", 60.0));

        System.out.println("[INFO] Order created. Total products in queue: " + orderList.size());

        double totalPrice = 0.0;
        for (Product p : orderList) {
            if (p.category.equals("Electronics")) {
                p.price = p.price * 0.9;
            }
            totalPrice += p.price;
        }

        System.out.printf("[SUCCESS] Final order price after discount: %.2f EUR\n", totalPrice);

    }
}
