package org.myCountriesApplication.core.domain;

import java.util.Arrays;

public class CountryInfo {
    private String commonName;
    private String officialName;
    private String countryCode;
    private String region;
    private Border[] borders;

    public CountryInfo() {
    }

    public CountryInfo(String commonName) {
        this.commonName = commonName;
    }

    public CountryInfo(String commonName, String officialName, String countryCode, String region, Border[] borders) {


        this.commonName = commonName;
        this.officialName = officialName;
        this.countryCode = countryCode;
        this.region = region;
        this.borders = borders;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Border[] getBorders() {
        return borders;
    }

    public void setBorders(Border[] borders) {
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "CountryInfo{" + "commonName='" + commonName + '\'' + ", officialName='" + officialName + '\'' + ", countryCode='" + countryCode + '\'' + ", region='" + region + '\'' + ", borders=" + Arrays.toString(borders) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryInfo that)) return false;

        if (!getCommonName().equals(that.getCommonName())) return false;
        if (!getOfficialName().equals(that.getOfficialName())) return false;
        if (!getCountryCode().equals(that.getCountryCode())) return false;
        return getRegion().equals(that.getRegion());
    }

    @Override
    public int hashCode() {
        int result = getCommonName().hashCode();
        result = 31 * result + getOfficialName().hashCode();
        result = 31 * result + getCountryCode().hashCode();
        result = 31 * result + getRegion().hashCode();
        return result;
    }

}
