import java.rmi.*;
import java.rmi.registry.*;

/**
 * RMI服务器端程序
 * 功能：创建注册中心，绑定远程对象，提供RMI服务
 */
public class SomeServer {
    private static final int PORT = 2500;
    private static final String SERVICE_NAME = "helloService";
    
    public static void main(String[] args) {
        try {
            System.out.println("========================================");
            System.out.println("   RMI服务器启动中...");
            System.out.println("========================================");
            
            // 创建远程对象
            System.out.println("正在创建远程对象...");
            SomeInterface remoteObject = new SomeImpl();
            
            // 创建RMI注册中心（监听端口2500）
            System.out.println("正在创建RMI注册中心，端口: " + PORT);
            Registry registry = LocateRegistry.createRegistry(PORT);
            
            // 绑定远程对象到注册中心
            System.out.println("正在绑定远程对象到注册中心...");
            registry.bind(SERVICE_NAME, remoteObject);
            
            System.out.println("========================================");
            System.out.println("   RMI服务启动成功！");
            System.out.println("========================================");
            System.out.println("注册中心端口: " + PORT);
            System.out.println("服务名称: " + SERVICE_NAME);
            System.out.println("服务地址: rmi://localhost:" + PORT + "/" + SERVICE_NAME);
            System.out.println("----------------------------------------");
            System.out.println("按Ctrl+C停止服务器");
            System.out.println("========================================\n");
            
            System.out.println("等待客户端调用...\n");
            
            // 保持服务器运行
            while (true) {
                Thread.sleep(1000);
            }
            
        } catch (AlreadyBoundException e) {
            System.err.println("错误：服务名称已被绑定！");
            System.err.println("解决方案：更换服务名称或重启注册中心");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("服务器异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
