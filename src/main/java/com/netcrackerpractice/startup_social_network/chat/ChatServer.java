package com.netcrackerpractice.startup_social_network.chat;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.netcrackerpractice.startup_social_network.dto.MessageDTO;
import com.netcrackerpractice.startup_social_network.mapper.MessageMapper;
import com.netcrackerpractice.startup_social_network.security.JwtTokenProvider;
import com.netcrackerpractice.startup_social_network.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ChatServer {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageMapper messageMapper;

    @Value("${chat.server.hostname}")
    private String hostName;

    @Value("${chat.server.portNum}")
    private int portNum;

    private SocketIOServer socketIOServer;
    private Map<UUID, SocketIOClient> users;

    public ChatServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(10000);

        socketIOServer = new SocketIOServer(config);
        users = new HashMap<>();

        setupListeners();
        socketIOServer.start();
    }

    private void setupListeners() {
        socketIOServer.addConnectListener(socketIOClient -> {
            String token = socketIOClient.getHandshakeData().getSingleUrlParam("token");
            String userId = socketIOClient.getHandshakeData().getSingleUrlParam("userId");

            if (token != null && userId != null && jwtTokenProvider.validateToken(token)) {
                users.put(UUID.fromString(userId), socketIOClient);
            } else {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setMsg("You don't have enough access rights");
                messageDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));
                socketIOClient.sendEvent("access_denied", messageDTO);
                socketIOClient.disconnect();
            }
        });

        socketIOServer.addEventListener("new_message", MessageDTO.class, (socketIOClient, messageDTO, ackRequest) -> {
            if (users.get(messageDTO.getReceiverId()) != null) {
                users.get(messageDTO.getReceiverId()).sendEvent("new_message", messageDTO);
            }
            messageService.addMessage(messageMapper.messageDTOtoMessage(messageDTO));

            if (ackRequest.isAckRequested()) {
                ackRequest.sendAckData(1);
            }
        });

        socketIOServer.addDisconnectListener(socketIOClient -> {
            for (Map.Entry<UUID, SocketIOClient> entry : users.entrySet()) {
                if (entry.getValue() == socketIOClient) {
                    users.remove(entry.getKey());
                    entry.getValue().disconnect();
                    break;
                }
            }
        });
    }
}
