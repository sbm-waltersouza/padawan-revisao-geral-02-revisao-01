package com.example.subscriptions.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.example.subscriptions.model.Subscription;


@Repository
public class SubscriptionDAO {

    private final DataSource dataSource;

    public SubscriptionDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getLong("id"));
                subscription.setChannelName(rs.getString("channel_name"));
                subscription.setChannelUrl(rs.getString("channel_url"));
                subscription.setSubscribedDate(rs.getDate("subscribed_date").toLocalDate());
                subscriptions.add(subscription);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subscriptions;
    }

    public Subscription findById(Long id) {
        String sql = "SELECT * FROM subscriptions WHERE id = ?";
        Subscription subscription = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    subscription = new Subscription();
                    subscription.setId(rs.getLong("id"));
                    subscription.setChannelName(rs.getString("channel_name"));
                    subscription.setChannelUrl(rs.getString("channel_url"));
                    subscription.setSubscribedDate(rs.getDate("subscribed_date").toLocalDate());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subscription;
    }

    public void save(Subscription subscription) {
        String sql = "INSERT INTO subscriptions (channel_name, channel_url, subscribed_date) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, subscription.getChannelName());
            ps.setString(2, subscription.getChannelUrl());
            ps.setDate(3, Date.valueOf(subscription.getSubscribedDate()));
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    subscription.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(Long id, Subscription subscription) {
        String sql = "UPDATE subscriptions SET channel_name = ?, channel_url = ?, subscribed_date = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, subscription.getChannelName());
            ps.setString(2, subscription.getChannelUrl());
            ps.setDate(3, Date.valueOf(subscription.getSubscribedDate()));
            ps.setLong(4, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM subscriptions WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Subscription> search(String channelName, String channelUrl, LocalDate subscribedDate) {
        StringBuilder sql = new StringBuilder("SELECT * FROM subscriptions WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (channelName != null && !channelName.isEmpty()) {
            sql.append(" AND channel_name LIKE ?");
            params.add("%" + channelName + "%");
        }
        if (channelUrl != null && !channelUrl.isEmpty()) {
            sql.append(" AND channel_url = ?");
            params.add(channelUrl);
        }
        if (subscribedDate != null) {
            sql.append(" AND subscribed_date = ?");
            params.add(Date.valueOf(subscribedDate));
        }

        List<Subscription> results = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subscription subscription = new Subscription();
                    subscription.setId(rs.getLong("id"));
                    subscription.setChannelName(rs.getString("channel_name"));
                    subscription.setChannelUrl(rs.getString("channel_url"));
                    subscription.setSubscribedDate(rs.getDate("subscribed_date").toLocalDate());
                    results.add(subscription);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}
