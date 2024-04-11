package com.haizhi;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class aitest {
    public static void main(String[] args) {
        String url_recognition = " http://28373e95.r19.cpolar.top/all";// http://28373e95.r19.cpolar.top
        String url_score = " http://28373e95.r19.cpolar.top/score";
        String filePath = "D:\\python\\1.wav";

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

          System.out.println(response_score.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

