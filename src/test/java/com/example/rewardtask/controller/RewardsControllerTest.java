package com.example.rewardtask.controller;

import com.example.rewardtask.model.Reward;
import com.example.rewardtask.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RewardsControllerTest {

    @Mock
    private RewardsHandler rewardsHandler;
    @Mock
    private CustomerRepository customerRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldReturnReward() throws Exception {
        int customerId = 1;
        Reward reward = new Reward(1, 100, 50, 100, 250); // create a Reward object with your desired properties

        when(rewardsHandler.handleGetRewards(customerId)).thenReturn(reward);

        mockMvc.perform(get("/customers/{customerId}/rewards", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundWhenCustomerMissing() throws Exception {
        int customerId = 5;

        when(customerRepository.findCustomerById(customerId)).thenReturn(null);

        mockMvc.perform(get("/customers/{customerId}/rewards", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}