package org.general;

import org.eclipse.paho.client.mqttv3.*;

/**
 * This class
 *
 * @author lucalini
 * @version 1.0
 */
public class Subscriber implements MqttCallback {

    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "cal-poly/csc/309";
    private final static String CLIENT_ID = "jgs-subscriber";

    public static void main(String[] args) {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            Subscriber subscriber = new Subscriber();
            client.setCallback(subscriber);
            client.connect();
            System.out.println("Connected to BROKER: " + BROKER);
            client.subscribe(TOPIC);
            System.out.println("Subscribed to TOPIC: " + TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost: " + throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        System.out.println("Message arrived. Topic: " + s +
                " Message: " + new String(mqttMessage.getPayload()));
//        Repository.getInstance().updateValue();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
    }

}
