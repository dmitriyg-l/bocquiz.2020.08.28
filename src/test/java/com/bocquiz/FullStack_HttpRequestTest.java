package com.bocquiz;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FullStack_HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void homePageCheckForHeader() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("<h2>Climate Summary</h2>");
    }

    @Test
    public void jsonRequestTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/station/CHEMAINUS",
                String.class)).contains("[{\"Province\":\"BC\",\"FormattedDate\":\"01-04-2018\",\"MeanTemp\":15.1,\"HighestMonthlyTemp\":26.5,\"LowestMonthlyTemp\":7.0}]");
	}

    @Test
    public void dateRangeTest_after() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/?startDate=2018-01-31",
            String.class)).contains("<td>HALKIRK AGCM</td>");
    }

    @Test
    public void dateRangeTest_before() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/?endDate=2017-01-03",
                String.class)).contains("<td>FT STEELE DANDY CRK</td>");
    }

    @Test
    public void dateRangeTest_between() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/?startDate=2018-01-03&endDate=2018-01-04",
            String.class)).contains("<td>SAINT-MICHEL-DES-SAINTS</td>");
    }
}