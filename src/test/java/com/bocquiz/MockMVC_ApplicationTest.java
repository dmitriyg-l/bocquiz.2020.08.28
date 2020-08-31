package com.bocquiz;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.*;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMVC_ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homePageCheckForHeader() throws Exception {
        this.mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<td>EAST POINT (AUT)</td>")));
	}

    @Test
    public void jsonRequestTest() throws Exception {
        this.mockMvc.perform(get("/station/CHEMAINUS"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string("[{\"Province\":\"BC\",\"FormattedDate\":\"01-04-2018\",\"MeanTemp\":15.1,\"HighestMonthlyTemp\":26.5,\"LowestMonthlyTemp\":7.0}]"));
	}

    @Test
    public void dateRangeTest_after() throws Exception {
        this.mockMvc.perform(get("/?startDate=2018-01-31"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<td>HALKIRK AGCM</td>")));
	}

    @Test
    public void dateRangeTest_before() throws Exception {
        this.mockMvc.perform(get("/?endDate=2017-01-03"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<td>FT STEELE DANDY CRK</td>")));
	}

    @Test
    public void dateRangeTest_between() throws Exception {
        this.mockMvc.perform(get("/?startDate=2018-01-03&endDate=2018-01-04"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<td>SAINT-MICHEL-DES-SAINTS</td>")));
	}
}