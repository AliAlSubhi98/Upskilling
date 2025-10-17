package com.upskilling.smartdeploymonitor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
public class DemoControllerTest {

    @Autowired
    private DemoController demoController;

    @Test
    public void testHealthEndpoint() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
        
        mockMvc.perform(get("/demo/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void testInfoEndpoint() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
        
        mockMvc.perform(get("/demo/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.application").value("Smart Deploy Monitor"))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    public void testLogsEndpoint() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
        
        mockMvc.perform(get("/demo/logs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.random_value").exists())
                .andExpect(jsonPath("$.log_level").exists())
                .andExpect(jsonPath("$.timestamp").exists());
    }
}