import java.util.ArrayList;
import java.util.HashMap;

public class TradingEngine {

    static class Order {
        int id;
        int userId;
        String type;
        double price;
        int amount;

        public Order(int id, int userId, String type, double price, int amout) {
            this.id = id;
            this.userId = userId;
            this.type = type;
            this.price = price;
            this.amount = amout;
        }
    }

    private static HashMap<Double, ArrayList<Order>> buyBook = new HashMap<>();
    private static HashMap<Double, ArrayList<Order>> sellBook = new HashMap<>();
    private static HashMap<Integer, Double> userBalances = new HashMap<>();
    private static final FeeCalculator feeCalculator = new FeeCalculator();


    public static void main(String[] args) {

        userBalances.put(1, 100000.0);
        userBalances.put(2, 100000.0);
        startOrderGenerator();
    }

    public static void startOrderGenerator() {
        new Thread(() -> {
            int orderId = 1;
            while (true) {
                try {
                    int userId = (Math.random() > 0.5) ? 1 : 2;
                    String type = (Math.random() > 0.5) ? "BUY" : "SELL";
                    double price = 70000.0 + (Math.random() * 10000.0);
                    int amount = 1 + (int) (Math.random() * 5);

                    Order newOrder = new Order(orderId, userId, type, price, amount);
                    System.out.printf("\r[GENERATOR] User %d created %s order: %d BTC @ %.2f EUR\n",
                            userId, type, amount, price);
                    if (checkBalance(newOrder)) {
                        addOrdertoBook(newOrder);
                        matchOrders();
                    }
                    orderId++;
                    Thread.sleep(1500);

                } catch (Exception e) {
                    break;
                }
            }
        }).start();
    }

    public static void addOrdertoBook(Order order) {
        if (order.type.equals("BUY")) {
            if (!buyBook.containsKey(order.price)) {
                buyBook.put(order.price, new ArrayList<>());
            }
            buyBook.get(order.price).add(order);
        } else {
            if (!sellBook.containsKey(order.price)) {
                sellBook.put(order.price, new ArrayList<>());
            }
            sellBook.get(order.price).add(order);
        }
        System.out.println("[BOOK] Order added to the book successfully.");
    }

    public static boolean checkBalance(Order order) {
        if (order.type.equals("SELL")) {
            return true;
        } else {
            double totalCost = order.price * order.amount;
            double currentBalance = userBalances.get(order.userId);
            if (currentBalance < totalCost) {
                System.out.printf("[REJECTED] User %d needs %.2f EUR, but has only %.2f EUR\n", order.userId, totalCost, currentBalance);
                return false;
            }
        }
        return true;
    }

    public static void matchOrders() {
        if (buyBook.isEmpty() || sellBook.isEmpty()) {
            return;
        }
        double maxBuyPrice = 0.0;
        double minSellPrice  = Double.MAX_VALUE;
        for (double price : buyBook.keySet()) {
            if (price > maxBuyPrice) {
                maxBuyPrice = price;
            }
        }
        for (double price : sellBook.keySet()) {
            if (price < minSellPrice) {
                minSellPrice = price;
            }
        }
        if (maxBuyPrice < minSellPrice) {
            return;
        }

        Order buyer = buyBook.get(maxBuyPrice).get(0);
        Order seller = sellBook.get(minSellPrice).get(0);
        double dealCost = buyer.price * buyer.amount;
        userBalances.put(buyer.userId, userBalances.get(buyer.userId) - dealCost);
        userBalances.put(seller.userId, userBalances.get(seller.userId) + dealCost);
        buyBook.get(maxBuyPrice).remove(0);
        sellBook.get(minSellPrice).remove(0);
        if (buyBook.get(maxBuyPrice).isEmpty()) {
            buyBook.remove(maxBuyPrice);
        }
        if (sellBook.get(minSellPrice).isEmpty()) {
            sellBook.remove(minSellPrice);
        }
        System.out.printf("[TRADE SUCCESS] User %d bought %d BTC from User %d @ %.2f EUR\n",
                buyer.userId, seller.amount, seller.userId, seller.price);

        feeCalculator.calculateAndRecordFee(buyer.userId, dealCost);
        feeCalculator.calculateAndRecordFee(seller.userId, dealCost);
        System.out.printf("[FEE RECORDED] User %d total fees: %.2f EUR | User %d total fees: %.2f EUR\n",
                buyer.userId, feeCalculator.getTotalFeesByUser(buyer.userId),
                seller.userId, feeCalculator.getTotalFeesByUser(seller.userId));
    }
}