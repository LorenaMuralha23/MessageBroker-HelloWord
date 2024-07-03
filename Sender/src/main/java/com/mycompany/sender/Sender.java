package com.mycompany.sender;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    private final static String QUEUE_NAME = "hello";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        //É a classe fornecida pelo RabbitMQ que configura os parâmetros de conexão, como host, porta, credenciais, etc.
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        //cria uma nova conexão com o RabbitMQ com base nas configurações definidas na ConnectionFactory. A conexão é estabelecida quando este método é chamado.
        try (Connection connection = factory.newConnection()){
            // cria um novo canal de comunicação dentro da conexão estabelecida com o RabbitMQ.
            Channel channel = connection.createChannel();
            
            /*Canais são unidades de comunicação dentro da conexão, utilizados para realizar a maior parte das operações com o RabbitMQ
            , como enviar e receber mensagens, declarar filas, etc.*/
            
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello, World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Message sended!");
        }
    }
}
