package com.example.model.entity;

public class oldService {
	
	private Integer serviceId;
	
    private String serviceLocation;
    
    private String serviceSubject;
    
    private String serviceName;
    
    private String serviceUrl;
    
    private Integer levelId;
    
    private String sort;

    public oldService() {
    	
    }
    
	public oldService(Integer serviceId, String serviceLocation, String serviceSubject, String serviceName,
			String serviceUrl, Integer levelId, String sort) {
		this.serviceId = serviceId;
		this.serviceLocation = serviceLocation;
		this.serviceSubject = serviceSubject;
		this.serviceName = serviceName;
		this.serviceUrl = serviceUrl;
		this.levelId = levelId;
		this.sort = sort;
	}
    
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public String getServiceSubject() {
		return serviceSubject;
	}

	public void setServiceSubject(String serviceSubject) {
		this.serviceSubject = serviceSubject;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceLocation=" + serviceLocation + ", serviceSubject="
				+ serviceSubject + ", serviceName=" + serviceName + ", serviceUrl=" + serviceUrl + ", levelId="
				+ levelId + ", sort=" + sort + "]";
	}

}
