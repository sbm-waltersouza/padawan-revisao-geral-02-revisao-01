package com.example.subscriptions.model;

import java.time.LocalDate;

public class Subscription {

	private Long id;
	private String channelName;
	private String channelUrl;
	private LocalDate subscribedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public LocalDate getSubscribedDate() {
		return subscribedDate;
	}

	public void setSubscribedDate(LocalDate subscribedDate) {
		this.subscribedDate = subscribedDate;
	}

}
