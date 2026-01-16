import java.rmi.*;

/**
 * RMI客户端程序
 * 功能：查找远程对象，调用远程方法
 */
public class SomeClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 2500;
    private static final String SERVICE_NAME = "helloService";
    
    public static void main(String[] args) {
        try {
            System.out.println("========================================");
            System.out.println("   RMI客户端启动");
            System.out.println("========================================");
            
            // 构建RMI服务地址
            String serviceUrl = "rmi://" + SERVER_HOST + ":" + SERVER_PORT + "/" + SERVICE_NAME;
            System.out.println("正在连接RMI服务...");
            System.out.println("服务地址: " + serviceUrl);
            System.out.println("----------------------------------------\n");
            
            // 查找远程对象
            SomeInterface remoteObject = (SomeInterface) Naming.lookup(serviceUrl);
            System.out.println("成功获取远程对象引用！\n");
            
            // 调用远程方法1：sayHello
            System.out.println("========================================");
            System.out.println("   测试1：调用 sayHello() 方法");
            System.out.println("========================================");
            String name = "张三";
            System.out.println("调用: sayHello(\"" + name + "\")");
            String greeting = remoteObject.sayHello(name);
            System.out.println("返回结果: " + greeting);
            System.out.println("----------------------------------------\n");
            
            // 调用远程方法2：add
            System.out.println("========================================");
            System.out.println("   测试2：调用 add() 方法");
            System.out.println("========================================");
            int a = 25;
            int b = 17;
            System.out.println("调用: add(" + a + ", " + b + ")");
            int sum = remoteObject.add(a, b);
            System.out.println("返回结果: " + a + " + " + b + " = " + sum);
            System.out.println("----------------------------------------\n");
            
            // 调用远程方法3：getServerTime
            System.out.println("========================================");
            System.out.println("   测试3：调用 getServerTime() 方法");
            System.out.println("========================================");
            System.out.println("调用: getServerTime()");
            String serverTime = remoteObject.getServerTime();
            System.out.println("返回结果: " + serverTime);
            System.out.println("----------------------------------------\n");
            
            System.out.println("========================================");
            System.out.println("   所有远程调用测试完成！");
            System.out.println("========================================");
            
        } catch (NotBoundException e) {
            System.err.println("错误：未找到指定的服务！");
            System.err.println("解决方案：检查服务名称是否正确，确认服务器已启动");
            e.printStackTrace();
        } catch (java.net.MalformedURLException e) {
            System.err.println("错误：RMI服务地址格式错误！");
            e.printStackTrace();
        } catch (RemoteException e) {
            System.err.println("错误：远程调用异常！");
            System.err.println("解决方案：检查网络连接，确认服务器正在运行");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("客户端异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
