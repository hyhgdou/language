package com.haizhi.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;

import com.haizhi.pojo.GroupChat;
import com.haizhi.service.GroupChatService;
import com.haizhi.service.impl.GroupChatServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket服务
 */
@ServerEndpoint(value = "/groupChatServer/{username}")///{avatar}
@Component
public class WebSocketGroupServer implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(WebSocketGroupServer.class);
    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    public static final Map<String, Object> userMap = new ConcurrentHashMap<>();

   @Autowired
    GroupChatServiceImpl groupChatServiceImpl;

    static GroupChatServiceImpl staticGroupChatServiceImpl;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {//, @PathParam("avatar") String avatar
        sessionMap.put(session.getId(), session);
        Dict user = Dict.create().set("name", URLUtil.decode(Base64.decodeStr(username)));//.set("avatar", URLUtil.decode(Base64.decodeStr(avatar))
        userMap.put(session.getId(), user);
        log.info("userMap: {}", userMap);
        log.info("[onOpen] 有新用户加入，session={}, 当前在线人数为：{}", session.getId(), sessionMap.size());
        sendAllMessage(JSONUtil.toJsonStr(Dict.create().set("users", userMap.values())));  // 后台发送消息给所有的客户端
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        userMap.remove(session.getId());
        log.info("[onClose] 有一连接关闭，session={}, 当前在线人数为：{}", session.getId(), sessionMap.size());
        sendAllMessage(JSONUtil.toJsonStr(Dict.create().set("users", userMap.values())));  // 后台发送消息给所有的客户端
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session fromSession) {
        log.info("服务端收到消息:{}", message);
        GroupChat groupChat = JSONUtil.toBean(message, GroupChat.class);
        groupChat.setTime(DateUtil.now());
        // 存储数据到数据库
        staticGroupChatServiceImpl.add(groupChat);
        String jsonStr = JSONUtil.toJsonStr(groupChat);  // 处理后的消息体
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

       staticGroupChatServiceImpl=groupChatServiceImpl;
    }
}
