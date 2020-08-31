package com.bocquiz.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stationreadings")
public class StationReading {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String stationname;
  private String province;
  private Date date;
  private Float meantemp;
  private Float highestmonthlytemp;
  private Float lowestmonthlytemp;

  public StationReading() { }

  public StationReading(Long id, String stationname, String province, Date date, Float meantemp, Float highestmonthlytemp, Float lowestmonthlytemp) {
    this.id = id;
    this.stationname = stationname;
    this.province = province;
    this.date = date;
    this.meantemp = meantemp;
    this.highestmonthlytemp = highestmonthlytemp;
    this.lowestmonthlytemp = lowestmonthlytemp;
  }

  public Long getId() {
    return id;
  }

  public String getStationName() {
    return stationname;
  }

  public void setStationName(String value) {
    this.stationname = value;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String value) {
    this.province = value;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date value) {
    this.date = value;
  }

  public Float getMeanTemp() {
    return meantemp;
  }

  public void setMeanTemp(Float value) {
    this.meantemp = value;
  }

  public Float getHighestMonthlyTemp() {
    return highestmonthlytemp;
  }

  public void setHighestMonthlyTemp(Float value) {
    this.highestmonthlytemp = value;
  }

  public Float getLowestMonthlyTemp() {
    return lowestmonthlytemp;
  }

  public void setLowestMonthlyTemp(Float value) {
    this.lowestmonthlytemp = value;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + Objects.hashCode(this.id);
    hash = 79 * hash + Objects.hashCode(this.stationname);
    hash = 79 * hash + Objects.hashCode(this.province);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;

    final StationReading other = (StationReading) obj;
    if (!Objects.equals(this.id, other.id))
      return false;
    if (!Objects.equals(this.stationname, other.stationname))
      return false;
    if (!Objects.equals(this.province, other.province))
      return false;
    if (!Objects.equals(this.date, other.date))
      return false;
    if (!Objects.equals(this.meantemp, other.meantemp))
      return false;
    if (!Objects.equals(this.highestmonthlytemp, other.highestmonthlytemp))
      return false;
    return Objects.equals(this.lowestmonthlytemp, other.lowestmonthlytemp);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("StationReading {");
    sb.append("id=").append(id);
    sb.append("Station Name='").append(stationname).append('\'');
    sb.append(", Province='").append(province).append('\'');
    sb.append('}');
    return sb.toString();
  }
}