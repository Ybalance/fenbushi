import java.io.*;
import java.net.*;

/**
 * Echo客户端程序（TCP）
 * 功能：连接服务器，发送测试消息，接收并显示回声响应
 */
public class EchoClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9999;
    
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader userInput = null;
        
        try {
            System.out.println("========================================");
            System.out.println("   Echo客户端启动");
            System.out.println("========================================");
            
            // 创建Socket连接服务器
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("已连接到服务器: " + SERVER_HOST + ":" + SERVER_PORT);
            System.out.println("----------------------------------------\n");
            
            // 获取输入输出流
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            userInput = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("请输入要发送的消息（输入 'bye' 退出）：");
            
            String message;
            while (true) {
                // 读取用户输入
                System.out.print("> ");
                message = userInput.readLine();
                
                if (message == null || message.trim().isEmpty()) {
                    continue;
                }
                
                // 发送消息到服务器
                out.println(message);
                System.out.println("已发送: " + message);
                
                // 接收服务器响应
                String response = in.readLine();
                System.out.println("服务器响应: " + response);
                System.out.println("----------------------------------------");
                
                // 如果发送"bye"，则退出
                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }
            
            System.out.println("\n连接已关闭");
            
        } catch (IOException e) {
            System.err.println("客户端异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (userInput != null) userInput.close();
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
