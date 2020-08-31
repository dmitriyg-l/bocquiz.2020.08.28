package com.bocquiz.model;

public class StationReadingWithFormatting {
  public String Province;
  public String FormattedDate;
  public Float MeanTemp;
  public Float HighestMonthlyTemp;
  public Float LowestMonthlyTemp;

  public StationReadingWithFormatting(String province, String formattedDate, Float meantemp, Float highestmonthlytemp, Float lowestmonthlytemp) {
    this.Province = province;
    this.FormattedDate = formattedDate;
    this.MeanTemp = meantemp;
    this.HighestMonthlyTemp = highestmonthlytemp;
    this.LowestMonthlyTemp = lowestmonthlytemp;
  }
}