package com.consumer.consumer.service;

import com.consumer.consumer.models.MarketData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@EnableKafka
@Service
public class KafkaMarketDataConsumer {

    @Autowired
    private Jedis jedis;

    @Autowired
    private ObjectMapper objectMapper;

    // private static final String REDIS_KEY_PREFIX = "market_data_";

    @KafkaListener(topics = "track_orders", groupId = "track_orders_id")
    public void listen(String message) {
        try {
            // Assume the message is a JSON string with {ticker, askPrice, bidPrice, volume}
            MarketData marketData = objectMapper.readValue(message, MarketData.class);
            
            // Store the market data in Redis using the ticker as the key
            jedis.set(marketData.getTicker(), message);
            System.out.println("Data saved in Redis for ticker: " + marketData.getTicker());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
