package org.egov.vendorregistory.web.model.location;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.egov.vendorregistory.web.model.AuditDetails;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Representation of a address. Indiavidual APIs may choose to extend from this
 * using allOf if more details needed to be added in their case.
 */
//@Schema(description = "Representation of a address. Indiavidual APIs may choose to extend from this using allOf if more details needed to be added in their case. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-01-06T05:34:12.238Z[GMT]")
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
@ToString
@Builder
public class Address {

	@JsonProperty("tenantId")
	private String tenantId = null;

	@JsonProperty("doorNo")
	private String doorNo = null;

	@JsonProperty("plotNo")
	private String plotNo = null;

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("landmark")
	private String landmark = null;

	@JsonProperty("city")
	private String city = null;

	@JsonProperty("district")
	private String district = null;

	@JsonProperty("region")
	private String region = null;

	@JsonProperty("state")
	private String state = null;

	@JsonProperty("country")
	private String country = null;

	@JsonProperty("pincode")
	private String pincode = null;

	@JsonProperty("additionalDetails")
	private Object additionalDetails = null;

	@JsonProperty("buildingName")
	private String buildingName = null;

	@JsonProperty("street")
	private String street = null;

	@JsonProperty("locality")
	private Boundary locality = null;

	@JsonProperty("geoLocation")
	private GeoLocation geoLocation = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;

	public Address auditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
		return this;
	}

	/**
	 * Get auditDetails
	 * 
	 * @return auditDetails
	 **/

	@Valid
	public AuditDetails getAuditDetails() {
		return auditDetails;
	}

	public void setAuditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
	}

	public Address tenantId(String tenantId) {
		this.tenantId = tenantId;
		return this;
	}

	/**
	 * Unique Identifier of the tenant to which user primarily belongs
	 * 
	 * @return tenantId
	 **/
//	@Schema(required = true, description = "Unique Identifier of the tenant to which user primarily belongs")
	@NotNull

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Address doorNo(String doorNo) {
		this.doorNo = doorNo;
		return this;
	}

	/**
	 * House number or door number.
	 * 
	 * @return doorNo
	 **/
	// @Schema(description = "House number or door number.")
	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	/**
	 * Plot number of the house.
	 * 
	 * @return plotNo
	 **/
	// @Schema(description = "Plot number of the house.")
	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public Address id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * System generated id for the address
	 * 
	 * @return id
	 **/
	// @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "System
	// generated id for the address")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Address landmark(String landmark) {
		this.landmark = landmark;
		return this;
	}

	/**
	 * additional landmark to help locate the address
	 * 
	 * @return landmark
	 **/
	// @Schema(description = "additional landmark to help locate the address")
	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Address city(String city) {
		this.city = city;
		return this;
	}

	/**
	 * City of the address. Can be represented by the tenantid itself
	 * 
	 * @return city
	 **/
	// @Schema(description = "City of the address. Can be represented by the
	// tenantid itself")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Address district(String district) {
		this.district = district;
		return this;
	}

	/**
	 * The district in which the property is located
	 * 
	 * @return district
	 **/
	// @Schema(description = "The district in which the property is located")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Address region(String region) {
		this.region = region;
		return this;
	}

	/**
	 * The Region in which the property is located
	 * 
	 * @return region
	 **/
	// @Schema(description = "The Region in which the property is located")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Address state(String state) {
		this.state = state;
		return this;
	}

	/**
	 * The State in which the property is located
	 * 
	 * @return state
	 **/
	// @Schema(description = "The State in which the property is located")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Address country(String country) {
		this.country = country;
		return this;
	}

	/**
	 * The country in which the property is located
	 * 
	 * @return country
	 **/
	// @Schema(description = "The country in which the property is located")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address pincode(String pincode) {
		this.pincode = pincode;
		return this;
	}

	/**
	 * PIN code of the address. Indian pincodes will usually be all numbers.
	 * 
	 * @return pincode
	 **/
	// @Schema(description = "PIN code of the address. Indian pincodes will usually
	// be all numbers.")
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Address additionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
		return this;
	}

	/**
	 * more address detail as may be needed
	 * 
	 * @return additionalDetails
	 **/
	// @Schema(description = "more address detail as may be needed")
	public Object getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(Object additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public Address buildingName(String buildingName) {
		this.buildingName = buildingName;
		return this;
	}

	/**
	 * Name of the building
	 * 
	 * @return buildingName
	 **/
	// @Schema(description = "Name of the building")
	@Size(min = 2, max = 64)
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Address street(String street) {
		this.street = street;
		return this;
	}

	/**
	 * Street Name
	 * 
	 * @return street
	 **/
	// @Schema(description = "Street Name")
	@Size(min = 2, max = 64)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Address locality(Boundary locality) {
		this.locality = locality;
		return this;
	}

	/**
	 * Get locality
	 * 
	 * @return locality
	 **/

	@Valid
	@NonNull
	public Boundary getLocality() {
		return locality;
	}

	public void setLocality(Boundary locality) {
		this.locality = locality;
	}

	/**
	 * Get geoLocation
	 * 
	 * @return geoLocation
	 **/

	@Valid
	@NonNull
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Address address = (Address) o;
		return Objects.equals(this.tenantId, address.tenantId) && Objects.equals(this.doorNo, address.doorNo)
				&& Objects.equals(this.plotNo, address.plotNo) && Objects.equals(this.id, address.id)
				&& Objects.equals(this.landmark, address.landmark) && Objects.equals(this.city, address.city)
				&& Objects.equals(this.district, address.district) && Objects.equals(this.region, address.region)
				&& Objects.equals(this.state, address.state) && Objects.equals(this.country, address.country)
				&& Objects.equals(this.pincode, address.pincode)
				&& Objects.equals(this.additionalDetails, address.additionalDetails)
				&& Objects.equals(this.buildingName, address.buildingName)
				&& Objects.equals(this.street, address.street) && Objects.equals(this.locality, address.locality)
				&& Objects.equals(this.geoLocation, address.geoLocation) && Objects.equals(this.auditDetails, address.auditDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenantId, doorNo, plotNo, id, landmark, city, district, region, state, country, pincode,
				additionalDetails, buildingName, street, locality, geoLocation,auditDetails);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Address {\n");

		sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
		sb.append("    doorNo: ").append(toIndentedString(doorNo)).append("\n");
		sb.append("    plotNo: ").append(toIndentedString(plotNo)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    landmark: ").append(toIndentedString(landmark)).append("\n");
		sb.append("    city: ").append(toIndentedString(city)).append("\n");
		sb.append("    district: ").append(toIndentedString(district)).append("\n");
		sb.append("    region: ").append(toIndentedString(region)).append("\n");
		sb.append("    state: ").append(toIndentedString(state)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    pincode: ").append(toIndentedString(pincode)).append("\n");
		sb.append("    additionalDetails: ").append(toIndentedString(additionalDetails)).append("\n");
		sb.append("    buildingName: ").append(toIndentedString(buildingName)).append("\n");
		sb.append("    street: ").append(toIndentedString(street)).append("\n");
		sb.append("    locality: ").append(toIndentedString(locality)).append("\n");
		sb.append("    geoLocation: ").append(toIndentedString(geoLocation)).append("\n");
		sb.append("    auditDetails: ").append(toIndentedString(auditDetails)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
