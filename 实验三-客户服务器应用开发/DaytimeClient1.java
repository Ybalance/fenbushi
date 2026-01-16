import java.io.*;

/**
 * Daytime客户端程序（UDP）
 * 功能：连接服务器，发送DAYTIME请求，接收并显示服务器时间
 */
public class DaytimeClient1 {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888;
    
    public static void main(String[] args) {
        MyClientDatagramSocket clientSocket = null;
        
        try {
            System.out.println("========================================");
            System.out.println("   Daytime客户端启动");
            System.out.println("========================================");
            
            // 创建客户端Socket
            clientSocket = new MyClientDatagramSocket(SERVER_HOST, SERVER_PORT);
            System.out.println("已连接到服务器: " + SERVER_HOST + ":" + SERVER_PORT);
            System.out.println("----------------------------------------\n");
            
            // 发送DAYTIME请求
            String request = "DAYTIME";
            System.out.println("发送请求: " + request);
            clientSocket.sendMessage(request);
            
            // 接收服务器响应
            System.out.println("\n等待服务器响应...");
            String response = clientSocket.receiveMessage();
            
            // 显示结果
            System.out.println("\n========================================");
            System.out.println("   服务器响应");
            System.out.println("========================================");
            System.out.println(response);
            System.out.println("========================================\n");
            
        } catch (Exception e) {
            System.err.println("客户端异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}
