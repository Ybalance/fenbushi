import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Daytime业务逻辑处理类
 * 功能：处理daytime请求，返回当前系统时间
 */
public class DaytimeHelper1 {
    
    /**
     * 获取当前系统时间
     * @return 格式化的时间字符串
     */
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = formatter.format(new Date());
        System.out.println("生成时间信息: " + currentTime);
        return currentTime;
    }
    
    /**
     * 处理daytime请求
     * @param request 客户端请求内容
     * @return 响应消息
     */
    public static String processDaytimeRequest(String request) {
        System.out.println("处理Daytime请求: " + request);
        if (request != null && request.trim().equalsIgnoreCase("DAYTIME")) {
            return "服务器时间: " + getTime();
        } else {
            return "无效请求，请发送 'DAYTIME' 获取时间";
        }
    }
}
