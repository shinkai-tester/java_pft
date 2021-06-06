package ru.stqa.pft.soap;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class GeoIpServiceTests extends TestBase{

  @Test
  public void testIP() {
    assertEquals(getCountryAndState("217.25.225.176"), expectedCountryAndState("RU", "86"));
  }

  @Test
  public void testInvalidIP() {

    String countryAndState = getCountryAndState("217.25.225.xxx");
    assertNotEquals(countryAndState, expectedCountryAndState("RU", "86"));
    assertEquals(countryAndState, expectedCountryAndState("US", "CA"));
  }

  @Test
  public void testCountryNameByISO() {
    assertEquals(getCountryNameByISO("RU"), expectedCountry("RUSSIAN FEDERATION"));
    assertEquals(getCountryNameByISO("JP"), expectedCountry("JAPAN"));
    assertEquals(getCountryNameByISO("CA"), expectedCountry("CANADA"));
  }

  @Test
  public void testInvalidCountryNameByISO() {
    assertNotEquals(getCountryNameByISO("RU"), expectedCountry("Russia"));
    assertEquals(getCountryNameByISO("BOO"), expectedCountry("UNITED STATES"));
  }

  @Test
  public void testISObyCountryName() {
    assertEquals(getISObyCountryName("RUSSIAN FEDERATION"), expectedCountry("RU"));
    assertEquals(getISObyCountryName("Russian Federation"), expectedCountry("RU"));
  }

  @Test
  public void testInvalidISObyCountryName() {
    assertNotEquals(getISObyCountryName("Russia"), expectedCountry("RU"));
    assertEquals(getISObyCountryName("Russia"), expectedCountry("US"));
  }
}
