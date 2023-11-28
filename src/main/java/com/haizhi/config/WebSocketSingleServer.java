package com.haizhi.config;


import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.haizhi.pojo.SingleChat;
import com.haizhi.service.SingleChatService;
import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//webSocket服务 单人聊天
@Component
@ServerEndpoint(value = "/singleChatServer")
public class WebSocketSingleServer implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(WebSocketSingleServer.class);
    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @Resource
    SingleChatService singleChatService;

    static SingleChatService staticSingleChatService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        log.info("[onOpen] 新建连接，session={}, 当前在线人数为：{}", session.getId(), sessionMap.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        log.info("[onClose] 有一连接关闭，session={}, 当前在线人数为：{}", session.getId(), sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session fromSession) {
        log.info("服务端收到消息:{}", message);
        SingleChat singleChat = JSONUtil.toBean(message, SingleChat.class);
        singleChat.setTime(DateUtil.now());
        // 存储数据到数据库
        staticSingleChatService.add(singleChat);
        String jsonStr = JSONUtil.toJsonStr(singleChat);  // 处理后的消息体
        this.sendAllMessage(jsonStr);
        log.info("[onMessage] 发送消息：{}", jsonStr);
    }

    @OnError
    public void onError(Session session, Throwable error) {

        log.error("[onError] 发生错误", error);
    }

    /**
     * 服务端发送消息给除了自己的其他客户端
     */
    private void sendMessage(Session fromSession, String message) {
        sessionMap.values().forEach(session -> {
            if (fromSession != session) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    log.error("服务端发送消息给客户端异常", e);
                }
            }
        });
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        staticSingleChatService = singleChatService;
    }


}
