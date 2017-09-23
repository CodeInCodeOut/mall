package com.dayuanit.pay.dto;

public class PayOrderUrlDTO {
	
	private String payOrderUrl;
	
	private Integer payId;
	
	private boolean success;
	
	private String message;
	
	
	public Integer getPayId() {
		return payId;
	}



	public void setPayId(Integer payId) {
		this.payId = payId;
	}


	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getPayOrderUrl() {
		return payOrderUrl;
	}


	public void setPayOrderUrl(String payOrderUrl) {
		this.payOrderUrl = payOrderUrl;
	}

}
