package com.bocquiz;

import static org.mockito.BDDMockito.*;

import com.bocquiz.model.StationReading;
import com.bocquiz.repository.StationReadingRepository;
import com.bocquiz.service.StationReadingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(MockitoExtension.class)
public class MockMVC_ServiceTest {

    @InjectMocks
    private StationReadingService service;
 
    @Mock
    private StationReadingRepository repository;

    private StationReading generateTestReading() {
        return new StationReading(125L, "BoC", "ON", new Date(2020, 8, 28), null, -35.2f, 28.1f); 
    }

    private List<StationReading> generateListOfReading() {
        var result = new ArrayList<StationReading>();
        result.add(generateTestReading());
        return result;
    }

    @Test
    public void checkRequestForAll() throws Exception {
        var inputData = generateListOfReading();

        given(repository.findAll())
            .willReturn(inputData);

        var expected = service.findAll();

        Assert.assertEquals(expected, inputData);
    }

    @Test
    public void jsonRequestTest() throws Exception {
        var inputData = generateListOfReading();

        given(repository.findByStationName("BoC"))
            .willReturn(inputData);

        var expected = service.findByStationName("BoC");

        Assert.assertEquals(expected, inputData);
	}
    
    @Test
    public void dateRangeTest_after() throws Exception {
        var inputData = generateListOfReading();

        given(repository.findByDateAfter(new Date(2020, 8, 28)))
            .willReturn(inputData);

        var expected = service.findByDateAfter(new Date(2020, 8, 28));

        Assert.assertEquals(expected, inputData);
	}
    
    @Test
    public void dateRangeTest_before() throws Exception {
        var inputData = generateListOfReading();

        given(repository.findByDateBefore(new Date(2020, 8, 28)))
            .willReturn(inputData);

        var expected = service.findByDateBefore(new Date(2020, 8, 28));

        Assert.assertEquals(expected, inputData);
	}
    
    @Test
    public void dateRangeTest_between() throws Exception {
        var inputData = generateListOfReading();

        given(repository.findByDateRange(new Date(2020, 8, 29), new Date(2020, 8, 31)))
            .willReturn(inputData);

        var expected = service.findByDateRange(new Date(2020, 8, 29), new Date(2020, 8, 31));

        Assert.assertEquals(expected, inputData);
	}
}