package ctest.controller;

import ctest.calculator.RewardsCalculator;
import ctest.model.CustomerDetails;
import ctest.model.TransactionDetails;
import ctest.repository.CustomerRepository;
import ctest.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    public final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RewardsController(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/calculatePoints")
    public Map<Long, Map<YearMonth, Integer>> calculateRewards(@RequestBody List<TransactionDetails> transactions) {
        Map<Long, Map<YearMonth, Integer>> rewardsMap = new HashMap<>();

        for (TransactionDetails transaction : transactions) {
            //checking for valid customer
            CustomerDetails customer = customerRepository.findById(transaction.getCustomerId()).orElse(null);
            if (customer == null) {
                continue; // Skip transactions if customer is not valid customer
            }

            int rewards = RewardsCalculator.calculateRewardPoints(transaction.getTransactionAmount());
            YearMonth yearMonth = YearMonth.from(transaction.getTransactionDate());

            rewardsMap.computeIfAbsent(customer.getId(), k -> new HashMap<>())
                    .merge(yearMonth, rewards, Integer::sum);
        }

        return rewardsMap;
    }
}
