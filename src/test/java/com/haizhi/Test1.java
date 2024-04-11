package com.haizhi;



import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aliyuncs.http.PositionType.Path;

public class Test1 {
    private static final String PATH = "D:\\python\\test.py";
    private static Logger log = LoggerFactory.getLogger(Test1.class);

    // String audioFilePath=
    @Test
    public void testMethod1() throws IOException, InterruptedException {
        final ProcessBuilder processBuilder = new ProcessBuilder("python", PATH);
        processBuilder.redirectErrorStream(true);
        final Process process = processBuilder.start();
        final BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s = null;
        while ((s = in.readLine()) != null) {
            System.out.println(s);
        }

        final int exitCode = process.waitFor();
        System.out.println(exitCode == 0);
    }

    @Test
    public void testMethod2() {
        final String line = "python " + PATH;
        final CommandLine cmdLine = CommandLine.parse(line);
        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            final PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
            final DefaultExecutor executor = new DefaultExecutor();
            executor.setStreamHandler(streamHandler);
            final int exitCode = executor.execute(cmdLine);

            log.info("调用Python脚本的执行结果: {}.", exitCode == 0 ? "成功" : "失败");
            log.info(baos.toString().trim());
        } catch (final IOException e) {
            log.error("调用Python脚本出错", e);
        }
    }

    @Test
    public void test() {
        System.out.println("Start");
        // python脚本的绝对路径，在windows中用"\\"分隔，在Linux中用"/"分隔


        // 传入python脚本的参数为”111“
        String[] args1 = new String[]{"python", PATH, "111"};

        try {
            // 执行Python文件，并传入参数
            Process process = Runtime.getRuntime().exec(args1);
            // 获取Python输出字符串作为输入流被Java读取
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String actionStr = in.readLine();
            if (actionStr != null) {
                System.out.println(actionStr);
            }

            in.close();
            process.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("End");
    }

    @Test
    public void haizhi() {
        try {
            String audioFilePath = "D:\\python\\1.wav"; // 替换为你的音频文件路径
            java.nio.file.Path tempFile = Files.createTempFile(null, ".wav");

            // 将音频文件内容读取为字节数组
            byte[] audioBytes = Files.readAllBytes(Paths.get(audioFilePath));

            // 将字节数组写入临时文件
            Files.write(tempFile, audioBytes);

            // Python脚本现在应该能接受文件路径作为参数
            String pythonScriptPath = "D:\\python\\haizhi.py";
            String tempFilePath = tempFile.toAbsolutePath().toString();

            // 构建ProcessBuilder，传入Python解释器、脚本路径和临时文件路径
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, tempFilePath);
            Process process = processBuilder.start();

            // 获取脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 确保临时文件在使用完毕后删除
            tempFile.toFile().delete();

            // 等待脚本执行完成
            int exitCode = process.waitFor();
            System.out.println("Python script executed with exit code " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}



