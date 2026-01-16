import java.rmi.*;

/**
 * 远程接口定义（客户端副本）
 * 功能：定义可被远程调用的方法
 * 注意：必须与服务器端接口定义完全一致
 */
public interface SomeInterface extends Remote {
    
    /**
     * 远程方法：问候
     * @param name 用户名
     * @return 问候消息
     * @throws RemoteException RMI通信异常
     */
    String sayHello(String name) throws RemoteException;
    
    /**
     * 远程方法：加法运算
     * @param a 第一个数
     * @param b 第二个数
     * @return 计算结果
     * @throws RemoteException RMI通信异常
     */
    int add(int a, int b) throws RemoteException;
    
    /**
     * 远程方法：获取服务器时间
     * @return 服务器当前时间
     * @throws RemoteException RMI通信异常
     */
    String getServerTime() throws RemoteException;
}
