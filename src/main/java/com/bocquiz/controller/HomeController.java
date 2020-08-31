package com.bocquiz.controller;

import com.bocquiz.model.StationReading;
import com.bocquiz.model.StationReadingWithFormatting;
import com.bocquiz.service.IStationReadingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.*;
import java.util.*;

@Controller
public class HomeController {
  final static Logger logger = LoggerFactory.getLogger(HomeController.class);
  final static DateFormat loggingDateFormat = new SimpleDateFormat("MMM dd, yyyy");
  final static DateFormat paramDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Autowired
  IStationReadingService stationReadingService;

  @GetMapping("/")
  public ModelAndView Index(Model model,
     @RequestParam(required = false) String startDate,
     @RequestParam(required = false) String endDate) {
    logger.info("-> Requesting Readings - startDate='" + startDate + "' and endDate='" + endDate + "'");

    List<StationReading> readings;
    String filterSummary;

    Date startDateAsDate = null;
    Date endDateAsDate = null;
    if (startDate != null && !startDate.trim().isEmpty()) {
      try {
        startDateAsDate = paramDateFormat.parse(startDate);
      } catch (ParseException e) {
          Map<String, Object> params = new HashMap<>();
          params.put("errorOccured", true);
          params.put("errorMessage", "Error: Invalid format for Start Date");
          params.put("filterSummary", "error");
          params.put("startDate", startDate);
          params.put("endDate", endDate);
          params.put("readings", null);

          return new ModelAndView("listReadings", params);
      }
    }

    if (endDate != null && !endDate.trim().isEmpty()) {
      try {
        endDateAsDate = paramDateFormat.parse(endDate);
      } catch (ParseException e) {
          Map<String, Object> params = new HashMap<>();
          params.put("errorOccured", true);
          params.put("errorMessage", "Error: Invalid format for End Date");
          params.put("filterSummary", "error");
          params.put("startDate", startDate);
          params.put("endDate", endDate);
          params.put("readings", null);

          return new ModelAndView("listReadings", params);
      }
    }
    
    if (startDateAsDate == null) {
      if (endDateAsDate == null) {
        logger.info("-> Requesting Readings - ALL");
        filterSummary = "show all";
        readings = stationReadingService.findAll();
      }
      else {
        logger.info("-> Requesting Readings - BEFORE " + loggingDateFormat.format(endDateAsDate));
        filterSummary = "all before " + loggingDateFormat.format(endDateAsDate);
        readings = stationReadingService.findByDateBefore(endDateAsDate);
      }
    } else {
      if (endDateAsDate == null) {
        logger.info("-> Requesting Readings - AFTER " + loggingDateFormat.format(startDateAsDate));
        filterSummary = "all after " + loggingDateFormat.format(startDateAsDate);
        readings = stationReadingService.findByDateAfter(startDateAsDate);
      }
      else {
        if (endDateAsDate.compareTo(startDateAsDate) < 0) {
          Map<String, Object> params = new HashMap<>();
          params.put("errorOccured", true);
          params.put("errorMessage", "Error: Start Date is before End Date");
          params.put("filterSummary", "error");
          params.put("startDate", startDate);
          params.put("endDate", endDate);
          params.put("readings", null);

          return new ModelAndView("listReadings", params);
        }

        logger.info("-> Requesting Readings - BETWEEN " + loggingDateFormat.format(startDateAsDate) + " and " + loggingDateFormat.format(endDateAsDate));
        filterSummary = "all between " + loggingDateFormat.format(startDateAsDate) + " and " + loggingDateFormat.format(endDateAsDate);
        readings = stationReadingService.findByDateRange(startDateAsDate, endDateAsDate);
      }
    }

    Map<String, Object> params = new HashMap<>();
    params.put("errorOccured", false);
    params.put("filterSummary", filterSummary);
    params.put("startDate", startDate);
    params.put("endDate", endDate);
    params.put("readings", readings);

    return new ModelAndView("listReadings", params);
  }

  @RequestMapping(value = "/station/{stationName}", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public List<StationReadingWithFormatting> StationDetails(@PathVariable String stationName) {
    logger.info("-> Requesting Station details for <" + stationName + ">");

    List<StationReading> readings = stationReadingService.findByStationName(stationName);

    List<StationReadingWithFormatting> readingsFormatted = new ArrayList<StationReadingWithFormatting>();
    if (readings != null) {
      DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
      for (StationReading entry:readings)
        readingsFormatted.add(new StationReadingWithFormatting(entry.getProvince(), dateFormat.format(entry.getDate()), entry.getMeanTemp(), entry.getHighestMonthlyTemp(), entry.getLowestMonthlyTemp()));
    }
    
    return readingsFormatted;
  }

  @RequestMapping("/pageWithError")
  public void pageWithError() {
      throw new RuntimeException("test exception");
  }
}