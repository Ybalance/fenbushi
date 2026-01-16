import java.rmi.*;
import java.rmi.server.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 远程接口实现类
 * 功能：实现远程接口中定义的所有方法
 * 注意：必须继承UnicastRemoteObject类
 */
public class SomeImpl extends UnicastRemoteObject implements SomeInterface {
    
    /**
     * 构造函数
     * @throws RemoteException RMI通信异常
     */
    public SomeImpl() throws RemoteException {
        super();
        System.out.println("远程对象已创建");
    }
    
    /**
     * 实现远程方法：问候
     */
    @Override
    public String sayHello(String name) throws RemoteException {
        String message = "Hello, " + name + "! 欢迎使用RMI服务";
        System.out.println("收到远程调用: sayHello(\"" + name + "\")");
        System.out.println("返回结果: " + message);
        return message;
    }
    
    /**
     * 实现远程方法：加法运算
     */
    @Override
    public int add(int a, int b) throws RemoteException {
        int result = a + b;
        System.out.println("收到远程调用: add(" + a + ", " + b + ")");
        System.out.println("返回结果: " + result);
        return result;
    }
    
    /**
     * 实现远程方法：获取服务器时间
     */
    @Override
    public String getServerTime() throws RemoteException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = formatter.format(new Date());
        System.out.println("收到远程调用: getServerTime()");
        System.out.println("返回结果: " + currentTime);
        return currentTime;
    }
}
