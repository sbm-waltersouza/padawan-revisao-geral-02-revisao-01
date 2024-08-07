package com.example.subscriptions.model;

import java.time.LocalDate;

public class SubscriptionFilter {
    private String channelName;
    private String channelUrl;
    private LocalDate subscribedDateFrom;
    private LocalDate subscribedDateTo;

    // Getters e setters
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

    public LocalDate getSubscribedDateFrom() {
        return subscribedDateFrom;
    }

    public void setSubscribedDateFrom(LocalDate subscribedDateFrom) {
        this.subscribedDateFrom = subscribedDateFrom;
    }

    public LocalDate getSubscribedDateTo() {
        return subscribedDateTo;
    }

    public void setSubscribedDateTo(LocalDate subscribedDateTo) {
        this.subscribedDateTo = subscribedDateTo;
    }
}