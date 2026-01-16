import java.io.*;
import java.net.*;

/**
 * HTTP Socket客户端程序
 * 功能：使用Socket API实现简单的HTTP客户端，发送GET请求并接收响应
 */
public class HttpSocketClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final String REQUEST_PATH = "/";
    
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            System.out.println("========================================");
            System.out.println("   HTTP Socket 客户端");
            System.out.println("========================================");
            System.out.println("目标服务器: " + SERVER_HOST + ":" + SERVER_PORT);
            System.out.println("请求路径: " + REQUEST_PATH);
            System.out.println("----------------------------------------\n");
            
            // 创建Socket连接
            System.out.println("正在连接服务器...");
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("连接成功！\n");
            
            // 获取输出流，发送HTTP请求
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // 构建HTTP GET请求
            System.out.println("========================================");
            System.out.println("   发送HTTP请求");
            System.out.println("========================================");
            String httpRequest = "GET " + REQUEST_PATH + " HTTP/1.0\r\n" +
                               "Host: " + SERVER_HOST + "\r\n" +
                               "Connection: close\r\n" +
                               "\r\n";
            
            System.out.println(httpRequest);
            out.print(httpRequest);
            out.flush();
            
            // 接收HTTP响应
            System.out.println("========================================");
            System.out.println("   接收HTTP响应");
            System.out.println("========================================\n");
            
            String line;
            int lineCount = 0;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                lineCount++;
                
                // 限制输出行数，避免输出过多
                if (lineCount > 100) {
                    System.out.println("\n... （响应内容过长，已省略部分内容）...");
                    break;
                }
            }
            
            System.out.println("\n========================================");
            System.out.println("   HTTP响应接收完成");
            System.out.println("========================================");
            System.out.println("共接收 " + lineCount + " 行数据");
            
        } catch (UnknownHostException e) {
            System.err.println("错误：无法找到主机 " + SERVER_HOST);
            System.err.println("解决方案：检查主机名是否正确");
        } catch (ConnectException e) {
            System.err.println("错误：连接被拒绝！");
            System.err.println("解决方案：确认Tomcat服务器已启动，端口" + SERVER_PORT + "可访问");
        } catch (IOException e) {
            System.err.println("IO异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
                System.out.println("\n连接已关闭");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
