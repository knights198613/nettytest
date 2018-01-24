package com.jiangwei.nettytest.testone.threadpool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by weijiang
 * Date: 2018/1/24
 * DESC:
 */
public class Target {

    @JsonProperty(value = "TIMESTAMP")
    private int timestamp;
    @JsonProperty(value = "MOBILE_APP_ID")
    private int mobileAppId;
    @JsonProperty(value = "MOBILE_APP_VERSION_ID")
    private int mobileAppVersionId;
    @JsonProperty(value = "MANUFACTURER_ID")
    private int manufacturerId;
    @JsonProperty(value = "MANUFACTURER_MODEL_ID")
    private int manufacturerModelId;
    @JsonProperty(value = "OS_ID")
    private int osId;
    @JsonProperty(value = "OS_VERSION_ID")
    private int osVersionId;
    @JsonProperty(value = "COUNTRY_ID")
    private int countryId;
    @JsonProperty(value = "REGION_ID")
    private int regionId;
    @JsonProperty(value = "CARRIER_ID")
    private int carrierId;
    @JsonProperty(value = "CONNECT_TYPE_ID")
    private int connectTypeId;
    @JsonProperty(value = "CDN_ID")
    private int cdnId;
    @JsonProperty(value = "URI_ID")
    private long uriId;
    @JsonProperty(value = "HOST_ID")
    private int hostId;
    @JsonProperty(value = "HOST_IP")
    private int hostIp;
    @JsonProperty(value = "RESPONSE_TIME")
    private int responseTime;
    @JsonProperty(value = "DNS_TIME")
    private int dnsTime;
    @JsonProperty(value = "CONNECT_TIME")
    private int connectTime;
    @JsonProperty(value = "FIRST_PACKET_TIME")
    private int firstPacketTime;
    @JsonProperty(value = "SSL_TIME")
    private int sslTime;
    @JsonProperty(value = "BYTES")
    private int bytesTotal;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getMobileAppId() {
        return mobileAppId;
    }

    public void setMobileAppId(int mobileAppId) {
        this.mobileAppId = mobileAppId;
    }

    public int getMobileAppVersionId() {
        return mobileAppVersionId;
    }

    public void setMobileAppVersionId(int mobileAppVersionId) {
        this.mobileAppVersionId = mobileAppVersionId;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public int getManufacturerModelId() {
        return manufacturerModelId;
    }

    public void setManufacturerModelId(int manufacturerModelId) {
        this.manufacturerModelId = manufacturerModelId;
    }

    public int getOsId() {
        return osId;
    }

    public void setOsId(int osId) {
        this.osId = osId;
    }

    public int getOsVersionId() {
        return osVersionId;
    }

    public void setOsVersionId(int osVersionId) {
        this.osVersionId = osVersionId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }

    public int getConnectTypeId() {
        return connectTypeId;
    }

    public void setConnectTypeId(int connectTypeId) {
        this.connectTypeId = connectTypeId;
    }

    public int getCdnId() {
        return cdnId;
    }

    public void setCdnId(int cdnId) {
        this.cdnId = cdnId;
    }

    public long getUriId() {
        return uriId;
    }

    public void setUriId(long uriId) {
        this.uriId = uriId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getHostIp() {
        return hostIp;
    }

    public void setHostIp(int hostIp) {
        this.hostIp = hostIp;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getDnsTime() {
        return dnsTime;
    }

    public void setDnsTime(int dnsTime) {
        this.dnsTime = dnsTime;
    }

    public int getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(int connectTime) {
        this.connectTime = connectTime;
    }

    public int getFirstPacketTime() {
        return firstPacketTime;
    }

    public void setFirstPacketTime(int firstPacketTime) {
        this.firstPacketTime = firstPacketTime;
    }

    public int getSslTime() {
        return sslTime;
    }

    public void setSslTime(int sslTime) {
        this.sslTime = sslTime;
    }

    public int getBytesTotal() {
        return bytesTotal;
    }

    public void setBytesTotal(int bytesTotal) {
        this.bytesTotal = bytesTotal;
    }

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        String json = null;
        try {
            json = om.writeValueAsString(this);
        } catch (Exception e) {
        }
        return json;
    }
}
