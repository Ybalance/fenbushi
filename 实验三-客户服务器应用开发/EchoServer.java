import java.io.*;
import java.net.*;

/**
 * Echo服务器端程序（TCP）
 * 功能：监听9999端口，接收客户端消息并原样返回
 */
public class EchoServer {
    private static final int PORT = 9999;
    
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        
        try {
            System.out.println("========================================");
            System.out.println("   Echo服务器启动中...");
            System.out.println("========================================");
            
            // 创建ServerSocket监听端口9999
            serverSocket = new ServerSocket(PORT);
            System.out.println("TCP服务器启动，监听端口: " + PORT);
            System.out.println("服务器就绪，等待客户端连接...");
            System.out.println("按Ctrl+C停止服务器");
            System.out.println("========================================\n");
            
            // 循环接受客户端连接
            while (true) {
                // 等待客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端已连接: " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                
                // 为每个客户端创建新线程处理
                new Thread(new ClientHandler(clientSocket)).start();
            }
            
        } catch (IOException e) {
            System.err.println("服务器异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("服务器Socket已关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 客户端处理线程
     */
    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
        
        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            
            try {
                // 获取输入输出流
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String clientAddress = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
                
                // 读取客户端消息
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("收到客户端 [" + clientAddress + "] 消息: " + message);
                    
                    // 如果客户端发送"bye"，则断开连接
                    if (message.equalsIgnoreCase("bye")) {
                        out.println("再见！");
                        System.out.println("客户端 [" + clientAddress + "] 断开连接");
                        break;
                    }
                    
                    // 原样返回消息（Echo）
                    out.println("Echo: " + message);
                    System.out.println("已回复客户端 [" + clientAddress + "]: Echo: " + message);
                }
                
            } catch (IOException e) {
                System.err.println("处理客户端请求异常: " + e.getMessage());
            } finally {
                // 关闭资源
                try {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    if (clientSocket != null) clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("----------------------------------------\n");
            }
        }
    }
}
