package com.ssj.mysqldemo.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MyWebSocketClient {
    final static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        String serverUrl = "ws://localhost:5551/ws";
        startClient(serverUrl);

        log.info("DONE {}", new Date().getTime());
    }

    private static void startClient(String serverUrl) {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new StringMessageConverter());
        StompSession session = null;
        try {
            session = stompClient.connect(serverUrl, new StompSessionHandlerAdapter() {
                @Override
                public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                    log.info("连接服务器success");
                    session.subscribe("/topic", new StompFrameHandler() {
                        @Override
                        public Type getPayloadType(StompHeaders headers) {
                            return String.class;
                        }
                        @Override
                        public void handleFrame(StompHeaders headers, Object payload) {
                            log.info("收到服务器广播: {}", payload);
                        }
                    });
                }
            }).get();
            String sessionId = session.getSessionId();
//            TextMessage textMessage = new TextMessage("\"[欸 -> server]\",");
//            Map<String, Object> map = Collections.singletonMap("replyChannel", "/topic");
//            Message<TextMessage> message = MessageBuilder.createMessage(textMessage, new MessageHeaders(map));
//            session.send("/app/queue", message);
            StompHeaders headers = new StompHeaders();
            headers.add(StompHeaders.DESTINATION, "/app/queue");
            headers.add("replyChannel", "/app/queue");
            session.send(headers, "[啊 -> server]");
            log.info("发送消息成功 {}", sessionId);
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException | ExecutionException e) {
            log.info("Error: ", e);
        } finally {
            log.info("finally");
            if (session != null)
                session.disconnect();
        }
    }
}