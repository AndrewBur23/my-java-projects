import java.util.HashMap;

public class FeeCalculator {
    private HashMap<Integer, Double> accamulatedFees = new HashMap<>();
    public void calculateAndRecordFee(int userId, double dealCost) {
        double currentCost = dealCost * 0.003;
        if (!accamulatedFees.containsKey(userId)) {
            accamulatedFees.put(userId, currentCost);
        } else {
            double oldFee = accamulatedFees.get(userId);
            double updatedFee = oldFee + currentCost;
            accamulatedFees.put(userId, updatedFee);

        }
    }

    public double getTotalFeesByUser(int userId) {
        if (!accamulatedFees.containsKey(userId)) {
            return 0.0;
        } else {
            return accamulatedFees.get(userId);
        }
    }
}
