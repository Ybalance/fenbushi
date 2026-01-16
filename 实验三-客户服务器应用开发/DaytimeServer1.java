import java.io.*;

/**
 * Daytime服务器端程序（UDP）
 * 功能：监听8888端口，接收客户端请求，返回当前系统时间
 */
public class DaytimeServer1 {
    private static final int PORT = 8888;
    
    public static void main(String[] args) {
        MyServerDatagramSocket serverSocket = null;
        
        try {
            System.out.println("========================================");
            System.out.println("   Daytime服务器启动中...");
            System.out.println("========================================");
            
            // 创建服务器Socket，监听端口8888
            serverSocket = new MyServerDatagramSocket(PORT);
            
            System.out.println("服务器就绪，等待客户端请求...");
            System.out.println("按Ctrl+C停止服务器");
            System.out.println("========================================\n");
            
            // 循环接收客户端请求
            while (true) {
                // 接收客户端消息
                DatagramMessage clientMessage = serverSocket.receiveMessage();
                
                // 调用业务逻辑层处理请求
                String response = DaytimeHelper1.processDaytimeRequest(clientMessage.getMessage());
                
                // 发送响应到客户端
                serverSocket.sendMessage(response, clientMessage.getAddress(), clientMessage.getPort());
                
                System.out.println("----------------------------------------\n");
            }
            
        } catch (Exception e) {
            System.err.println("服务器异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
