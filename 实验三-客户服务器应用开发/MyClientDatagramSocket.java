import java.net.*;
import java.io.*;

/**
 * UDP客户端Socket封装类
 * 功能：发送请求到服务器，接收服务器响应
 */
public class MyClientDatagramSocket {
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    
    /**
     * 构造函数
     * @param host 服务器地址
     * @param port 服务器端口
     */
    public MyClientDatagramSocket(String host, int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket();
        this.serverAddress = InetAddress.getByName(host);
        this.serverPort = port;
        // 设置超时时间为5秒
        this.socket.setSoTimeout(5000);
    }
    
    /**
     * 发送消息到服务器
     * @param message 要发送的消息
     */
    public void sendMessage(String message) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
        socket.send(packet);
        System.out.println("已发送消息到服务器: " + message);
    }
    
    /**
     * 接收服务器响应
     * @return 服务器返回的消息
     */
    public String receiveMessage() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String response = new String(packet.getData(), 0, packet.getLength());
        System.out.println("收到服务器响应: " + response);
        return response;
    }
    
    /**
     * 关闭Socket连接
     */
    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            System.out.println("客户端Socket已关闭");
        }
    }
}
