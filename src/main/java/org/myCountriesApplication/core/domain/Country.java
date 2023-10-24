package org.myCountriesApplication.core.domain;

public class Country {
    private String countryCode;

    private String name;

    public Country() {
    }

    public Country(String countryCode, String name) {
        this.countryCode = countryCode;
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" + "countryCode='" + countryCode + '\'' + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;

        if (getCountryCode() != null ? !getCountryCode().equals(country.getCountryCode()) : country.getCountryCode() != null)
            return false;
        return getName() != null ? getName().equals(country.getName()) : country.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCountryCode() != null ? getCountryCode().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

}
