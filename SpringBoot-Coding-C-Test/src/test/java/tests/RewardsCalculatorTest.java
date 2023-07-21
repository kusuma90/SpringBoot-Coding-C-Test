package tests;
import ctest.calculator.RewardsCalculator;

import ctest.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {RewardsCalculator.class, TransactionRepository.class})
public class RewardsCalculatorTest {

    @Test
    public void testCalculateRewardPoints() {
        // Test case 1: Amount below $50
        BigDecimal amount1 = BigDecimal.valueOf(30);
        int expectedRewards1 = 0;
        assertEquals(expectedRewards1, RewardsCalculator.calculateRewardPoints(amount1));

        // Test case 2: Amount between $50 and $100
        BigDecimal amount2 = BigDecimal.valueOf(75);
        int expectedRewards2 = 25;
        assertEquals(expectedRewards2, RewardsCalculator.calculateRewardPoints(amount2));

        // Test case 3: Amount above $100
        BigDecimal amount3 = BigDecimal.valueOf(120);
        int expectedRewards3 = 90;
        assertEquals(expectedRewards3, RewardsCalculator.calculateRewardPoints(amount3));

    }
}
