import java.net.*;
import java.io.*;

/**
 * UDP服务器Socket封装类
 * 功能：监听端口，接收客户端请求
 */
public class MyServerDatagramSocket {
    private DatagramSocket socket;
    private int port;
    
    /**
     * 构造函数
     * @param port 监听端口
     */
    public MyServerDatagramSocket(int port) throws SocketException {
        this.port = port;
        this.socket = new DatagramSocket(port);
        System.out.println("UDP服务器启动，监听端口: " + port);
    }
    
    /**
     * 接收客户端请求
     * @return DatagramMessage对象，包含消息内容和客户端地址
     */
    public DatagramMessage receiveMessage() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        
        String message = new String(packet.getData(), 0, packet.getLength());
        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();
        
        System.out.println("收到客户端请求 [" + clientAddress.getHostAddress() + ":" + clientPort + "]: " + message);
        
        return new DatagramMessage(message, clientAddress, clientPort);
    }
    
    /**
     * 发送响应到客户端
     * @param response 响应消息
     * @param clientAddress 客户端地址
     * @param clientPort 客户端端口
     */
    public void sendMessage(String response, InetAddress clientAddress, int clientPort) throws IOException {
        byte[] buffer = response.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
        socket.send(packet);
        System.out.println("已发送响应到客户端 [" + clientAddress.getHostAddress() + ":" + clientPort + "]: " + response);
    }
    
    /**
     * 关闭Socket连接
     */
    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            System.out.println("服务器Socket已关闭");
        }
    }
}
