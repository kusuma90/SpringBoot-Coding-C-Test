package ctest;

import ctest.model.CustomerDetails;
import ctest.model.TransactionDetails;
import ctest.repository.CustomerRepository;
import ctest.repository.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class RewardsMainApp {
   public  static  void main(String args[]){
      ConfigurableApplicationContext context = SpringApplication.run(RewardsMainApp.class, args);

      TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);
      CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

      // Create sample customers
      CustomerDetails customer1 = new CustomerDetails();
      customer1.setCustomerName("John Doe");
      customerRepository.save(customer1);

      CustomerDetails customer2 = new CustomerDetails();
      customer2.setCustomerName("Jane Smith");
      customerRepository.save(customer2);

      // Create sample transactions
      TransactionDetails transaction1 = new TransactionDetails();
      transaction1.setCustomerId(customer1.getId());
      transaction1.setTransactionAmount(BigDecimal.valueOf(120));
      transaction1.setTransactionDate(LocalDate.of(2023, 7, 1));
      transactionRepository.save(transaction1);

      TransactionDetails transaction2 = new TransactionDetails();
      transaction2.setCustomerId(customer1.getId());
      transaction2.setTransactionAmount(BigDecimal.valueOf(50));
      transaction2.setTransactionDate(LocalDate.of(2023, 7, 15));
      transactionRepository.save(transaction2);

      TransactionDetails transaction3 = new TransactionDetails();
      transaction3.setCustomerId(customer2.getId());
      transaction3.setTransactionAmount(BigDecimal.valueOf(80));
      transaction3.setTransactionDate(LocalDate.of(2023, 7, 5));
      transactionRepository.save(transaction3);
   }
}
