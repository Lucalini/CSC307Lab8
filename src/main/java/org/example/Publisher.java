package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will send the player's position to the other player
 * and publish game events using MQTT protocol.
 *
 * @author Bryce Uota
 */
public class Publisher {
    private String broker = "tcp://test.mosquitto.org:1883";
    private String topic = "CSC307Pong";
    private String clientId;
    private MqttClient client;
    private List<Subscriber> subscribers = new ArrayList<>();

    /**
     * Creates a new Publisher with a unique client ID.
     *
     * @param clientId Unique identifier for this client
     */
    public Publisher(String clientId) {
        this.clientId = clientId;
        try {
            this.client = new MqttClient(broker, clientId);
        } catch (MqttException e) {
            System.err.println("Error creating MQTT client: " + e.getMessage());
        }
    }

    /**
     * Connects to the MQTT broker.
     *
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        try {
            client.connect();
            System.out.println("Connected to broker: " + broker);
            return true;
        } catch (MqttException e) {
            System.err.println("Error connecting to broker: " + e.getMessage());
            return false;
        }
    }

    /**
     * Publishes player position to other players.
     *
     * @param player The player identifier (e.g., "Player1")
     * @param x The x position of the player's paddle
     * @param y The y position of the player's paddle
     */
    public void publishPosition(String player, int x, int y) {
        try {
            String content = player + "," + x + "," + y;
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(2); // Ensures message is delivered exactly once

            if (client.isConnected()) {
                client.publish(topic + "/position", message);
                System.out.println("Position published: " + content);
            }
        } catch (MqttException e) {
            System.err.println("Error publishing position: " + e.getMessage());
        }
    }

    /**
     * Publishes score updates.
     *
     * @param player The player who scored
     * @param newScore The updated score
     */
    public void publishScoreChange(String player, int newScore) {
        try {
            String content = player + "," + newScore;
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(2);

            if (client.isConnected()) {
                client.publish(topic + "/score", message);
                System.out.println("Score published: " + content);
            }

            // Also notify local subscribers
            GameEvent event = new GameEvent("SCORE_CHANGE", player, newScore);
            notifySubscribers(event);
        } catch (MqttException e) {
            System.err.println("Error publishing score: " + e.getMessage());
        }
    }

    /**
     * Publishes chat messages between players.
     *
     * @param playerName The name of the player sending the message
     * @param message The chat message content
     */
    public void publishChatMessage(String playerName, String message) {
        try {
            String content = playerName + ": " + message;
            MqttMessage mqttMessage = new MqttMessage(content.getBytes());
            mqttMessage.setQos(1); // At least once delivery for chat messages

            if (client.isConnected()) {
                client.publish(topic + "/chat", mqttMessage);
                System.out.println("Chat message published: " + content);
            }

            // Also notify local subscribers about the chat message
            GameEvent event = new GameEvent("CHAT_MESSAGE", playerName, message);
            notifySubscribers(event);
        } catch (MqttException e) {
            System.err.println("Error publishing chat message: " + e.getMessage());
        }
    }

    /**
     * Disconnects from the MQTT broker.
     */
    public void disconnect() {
        try {
            if (client.isConnected()) {
                client.disconnect();
                System.out.println("Disconnected from broker");
            }
        } catch (MqttException e) {
            System.err.println("Error disconnecting: " + e.getMessage());
        }
    }

    /**
     * Registers a subscriber to receive notifications.
     *
     * @param subscriber The subscriber to register
     */
    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    /**
     * Unregisters a subscriber from receiving notifications.
     *
     * @param subscriber The subscriber to unregister
     */
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Notifies all subscribers about a game event.
     *
     * @param event The game event to notify subscribers about
     */
    public void notifySubscribers(GameEvent event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(event);
        }
    }
}