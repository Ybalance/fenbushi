import java.net.*;

/**
 * 数据报消息封装类
 * 功能：封装客户端消息内容和地址信息
 */
public class DatagramMessage {
    private String message;
    private InetAddress address;
    private int port;
    
    /**
     * 构造函数
     * @param message 消息内容
     * @param address 客户端地址
     * @param port 客户端端口
     */
    public DatagramMessage(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }
    
    /**
     * 获取消息内容
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * 获取客户端地址
     */
    public InetAddress getAddress() {
        return address;
    }
    
    /**
     * 获取客户端端口
     */
    public int getPort() {
        return port;
    }
    
    @Override
    public String toString() {
        return "DatagramMessage{" +
                "message='" + message + '\'' +
                ", address=" + address.getHostAddress() +
                ", port=" + port +
                '}';
    }
}
