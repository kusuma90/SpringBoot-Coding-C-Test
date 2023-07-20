package ctest.calculator;

import java.math.BigDecimal;

public class RewardsCalculator {
    public static int calculateRewardPoints(BigDecimal amount) {
        int points = 0;

        if (amount.compareTo(BigDecimal.valueOf(100)) > 0) {
            BigDecimal above100 = amount.subtract(BigDecimal.valueOf(100));
            points += above100.multiply(BigDecimal.valueOf(2)).intValue();
            amount = amount.subtract(above100);
        }

        if (amount.compareTo(BigDecimal.valueOf(50)) >= 0) {
            BigDecimal between50And100 = amount.subtract(BigDecimal.valueOf(50));
            points += between50And100.intValue();
        }

        return points;
    }
}
