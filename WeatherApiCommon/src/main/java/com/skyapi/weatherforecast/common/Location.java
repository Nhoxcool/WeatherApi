package com.skyapi.weatherforecast.common;

import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Locations")
public class Location {

	@Column(length = 12, nullable = false, unique = true)
	@Id
	@NotNull(message = "Location code cannot be null")
	@Length(min = 3, max = 12, message = "Location code must have 3-12 characters")
	private String code;
	
	@Column(length = 128, nullable = false)
	@JsonProperty("city_name")
	@NotNull(message = "City name cannot be null")
	@Length(min = 3, max = 128, message = "City name must have 3-128 characters")
	private String cityName;
	
	@Column(length = 128)
	@JsonProperty("region_name")
	@Length(min = 3, max = 128, message = "Region name must have 3-128 characters")
	private String regionName;
	
	@Column(length = 64, nullable = false)
	@JsonProperty("country_name")
	@NotNull(message = "Country name cannot be null")
	@Length(min = 3, max = 64, message = "Country name  must have 3-64 characters")
	private String countryName;
	
	@Column(length = 2, nullable = false)
	@JsonProperty("country_code")
	@NotNull(message = "Country code cannot be null")
	@Length(min = 2, max = 62, message = "Country code must have 2 characters")
	private String countryCode;
	
	private boolean enabled;
	
	//for delete method
	@JsonIgnore
	private boolean trashed;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isTrashed() {
		return trashed;
	}

	public void setTrashed(boolean trashed) {
		this.trashed = trashed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "Location [code=" + code + ", cityName=" + cityName + ", regionName=" + regionName + ", countryName="
				+ countryName + ", countryCode=" + countryCode + ", enabled=" + enabled + ", trashed=" + trashed + "]";
	}
	
	
	
}
