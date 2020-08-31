package com.bocquiz.service;

import com.bocquiz.model.StationReading;
import com.bocquiz.repository.StationReadingRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationReadingService implements IStationReadingService {

  @Autowired
  private StationReadingRepository repository;

  @Override
  public List<StationReading> findAll() {
    return (List<StationReading>)repository.findAll();
  }

  @Override
  public List<StationReading> findByDateBefore(Date endDate) {
      return repository.findByDateBefore(endDate);
  }

  @Override
  public List<StationReading> findByDateAfter(Date startDate) {
      return repository.findByDateAfter(startDate);
  }

  @Override
  public List<StationReading> findByDateRange(Date startDate, Date endDate) {
      return repository.findByDateRange(startDate, endDate);
  }

  @Override
  public List<StationReading> findByStationName(String stationName) {
      return repository.findByStationName(stationName);
  }
}