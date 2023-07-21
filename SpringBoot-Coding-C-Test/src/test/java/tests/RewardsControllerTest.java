package tests;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ctest.controller.RewardsController;
import ctest.model.Rewards;
import ctest.model.TransactionDetails;
import ctest.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {RewardsController.class, TransactionRepository.class})
public class RewardsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void testCalculateRewards() throws Exception {
        List<TransactionDetails> transactions = Arrays.asList(
                new TransactionDetails(1L, BigDecimal.valueOf(120), LocalDate.of(2023, 7, 1)),
                new TransactionDetails(1L, BigDecimal.valueOf(50), LocalDate.of(2023, 7, 15)),
                new TransactionDetails(2L, BigDecimal.valueOf(80), LocalDate.of(2023, 7, 5))
        );

        // Mock the repository response
        when(transactionRepository.saveAll(any())).thenReturn(transactions);

        String requestJson = objectMapper.writeValueAsString(transactions);

        MvcResult mvcResult = mockMvc.perform(post("/rewards/calculatePoints")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();

        List<Rewards> rewards = objectMapper.readValue(responseJson, new TypeReference<List<Rewards>>() {});
        assertNotNull(rewards);
        assertTrue(rewards.size()==2);


        Rewards customer1Rewards = rewards.stream()
                .filter(reward -> reward.getCustomerId() == 1L)
                .findFirst()
                .orElse(null);
       assertNotNull(customer1Rewards);
       assertTrue(customer1Rewards.getPoints()==115);


       Rewards customer2Rewards = rewards.stream()
                .filter(reward -> reward.getCustomerId() == 2L)
                .findFirst()
                .orElse(null);
        assertNotNull(customer2Rewards);
        assertTrue(customer2Rewards.getPoints()==20);
    }
}
