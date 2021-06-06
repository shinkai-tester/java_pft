package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;

public class TestBase {

  protected GeoIPServiceSoap geoIPServiceSoap() {
    return new GeoIPService().getGeoIPServiceSoap12();
  }

  protected String getCountryAndState(String ip) {
    return geoIPServiceSoap().getIpLocation(ip);
  }

  protected String expectedCountryAndState(String country, String state) {
    return String.format("<GeoIP><Country>%s</Country><State>%s</State></GeoIP>", country, state);
  }

  protected String getCountryNameByISO(String iso) {
    return geoIPServiceSoap().getCountryNameByISO2(iso);
  }

  protected String expectedCountry(String country) {
    return String.format("<GeoIP><Country>%s</Country></GeoIP>", country);
  }

  protected String getISObyCountryName(String countryName) {
    return geoIPServiceSoap().getCountryISO2ByName(countryName);
  }

}
