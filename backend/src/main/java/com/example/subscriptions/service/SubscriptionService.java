package com.example.subscriptions.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.subscriptions.dao.SubscriptionDAO;
import com.example.subscriptions.model.Subscription;

@Service
public class SubscriptionService {

    private final SubscriptionDAO subscriptionDAO;

    public SubscriptionService(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionDAO.findAll();
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionDAO.findById(id);
    }

    public void addSubscription(Subscription subscription) {
        subscriptionDAO.save(subscription);
    }

    public void updateSubscription(Long id, Subscription subscription) {
        subscriptionDAO.update(id, subscription);
    }

    public void deleteSubscription(Long id) {
        subscriptionDAO.deleteById(id);
    }

    public List<Subscription> searchSubscriptions(String channelName, String channelUrl, LocalDate subscribedDate) {
        return subscriptionDAO.search(channelName, channelUrl, subscribedDate);
    }
}
