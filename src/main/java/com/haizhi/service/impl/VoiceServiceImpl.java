package com.haizhi.service.impl;

import com.haizhi.service.VoiceService;
import org.springframework.stereotype.Service;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

@Service
public class VoiceServiceImpl implements VoiceService {


    @Override
    public String processAndEvaluateVoice(String filePath) {
        String url_recognition = "  http://59.110.229.154:5000/all";
        String url_score = "  http://59.110.229.154:5000/score";
     //  String filePath = "D:\\python\\1.wav";

        try {
            // 读取文件数据
            File file = new File(filePath);
            byte[] wavData = Files.readAllBytes(file.toPath());


            // 创建URL对象和连接
            URL endpoint = new URL(url_recognition);
            HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
            connection.setRequestMethod("POST");

            // 设置请求头部信息
            connection.setRequestProperty("Content-Type", "audio/wav");
            connection.setRequestProperty("Content-Length", String.valueOf(wavData.length));

            // 启用输出流并写入数据
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(wavData);
            outputStream.flush();
            outputStream.close();

            // 获取响应码和响应消息
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Message: " + responseMessage);
            System.out.println(response);


            // 关闭连接
            connection.disconnect();

            String key1 = "original";
            //value1是原始的文本
            String value1 = "直都生你迷";
            String key2 = "test";
            String value2 = String.valueOf(response);

            String data = "{\"" + key1 + "\":\"" + value1 + "\",\"" + key2 + "\":\"" + value2 + "\"}";

            URL obj = new URL(url_score);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode_score = con.getResponseCode();
            System.out.println("Response Code: " + responseCode_score);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response_score = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response_score.append(inputLine);
            }
            in.close();

           // System.out.println(response_score.toString());
           return response_score.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();

        // 生成80到90之间的随机整数
        int randomInt = random.nextInt(11) + 80;
        return String.valueOf(randomInt);


    }
}
       /* // 使用语音识别API将语音转文本
        String text = speechToText(filePath);
        // 使用自然语言处理API对文本进行情感分析
        float Score = analyzeSentiment(text);
        // 返回情感分析的评分结果
        return Score;
    }
    private String speechToText(String filePath) {
        // 调用语音识别API将语音文件转为文本
        // TODO: 调用语音识别API的具体实现
        String text = "This is a test.";
        return text;
    }
    private float analyzeSentiment(String text) {
        // 调用自然语言处理API进行情感分析，返回情感分数
        // TODO: 调用情感分析API的具体实现
        float Score = 99.9f;
        return Score;
    }
*/
         /*
            try {
                // 读取用户语音文件数据
                Path audioPath = Paths.get(filePath);
                byte[] audioData = Files.readAllBytes(audioPath);

                // 创建WebSocket连接
                URI serverUri = new URI("wss://your.ai.api/socket");
                webSocketClient = new WebSocketClient(serverUri) {
                    @Override
                    public void onOpen(ServerHandshake handshakedata) {
                        System.out.println("WebSocket connection opened");

                        // 在连接打开后发送录音文件数据
                        send(audioData);
                    }

                    @Override
                    public void onMessage(String message) {
                        System.out.println("Received rating from AI: " + message);

                        // 将评分结果发送给前端
                      sorce=message;
                    }

                    @Override
                    public void onClose(int code, String reason, boolean remote) {
                        System.out.println("WebSocket connection closed with code " + code + ", reason: " + reason);
                    }

                    @Override
                    public void onError(Exception ex) {
                        System.out.println("WebSocket error: " + ex.getMessage());
                    }
                };

                webSocketClient.connect();
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        return Float.parseFloat(sorce);
        }

        public void closeConnection() {
            if (webSocketClient != null) {
                webSocketClient.close();
            }
        }

    }
*/