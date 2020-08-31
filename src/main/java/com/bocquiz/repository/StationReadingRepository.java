package com.bocquiz.repository;

import com.bocquiz.model.StationReading;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationReadingRepository extends CrudRepository<StationReading, Long> {
  @Query("select s from StationReading s where s.date <= ?1")
  List<StationReading> findByDateBefore(Date endDate);

  @Query("select s from StationReading s where s.date >= ?1")
  List<StationReading> findByDateAfter(Date startDate);

  @Query("select s from StationReading s where s.date between ?1 and ?2")
  List<StationReading> findByDateRange(Date startDate, Date endDate);
    
  @Query("select s from StationReading s where s.stationname = ?1")
  List<StationReading> findByStationName(String stationName);
}