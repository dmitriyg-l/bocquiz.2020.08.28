package com.bocquiz.service;

import com.bocquiz.model.StationReading;
import java.util.Date;
import java.util.List;

public interface IStationReadingService {
  List<StationReading> findAll();

  List<StationReading> findByDateBefore(Date endDate);
  List<StationReading> findByDateAfter(Date startDate);
  List<StationReading> findByDateRange(Date startDate, Date endDate);

  List<StationReading> findByStationName(String stationName);
}