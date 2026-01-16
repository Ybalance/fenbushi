@echo off
chcp 65001 >nul
echo ========================================
echo    RMI实验 - 编译运行说明
echo ========================================
echo.
echo 【第1步】编译服务端代码
echo ----------------------------------------
echo 进入 server 目录：
echo    cd server
echo.
echo 编译 Java 文件：
echo    javac *.java
echo.
echo 生成 stub 文件（JDK 8需要）：
echo    rmic SomeImpl
echo.
echo.
echo 【第2步】编译客户端代码
echo ----------------------------------------
echo 复制 stub 文件到 client 目录：
echo    copy server\SomeImpl_Stub.class client\
echo.
echo 进入 client 目录：
echo    cd client
echo.
echo 编译 Java 文件：
echo    javac *.java
echo.
echo.
echo 【第3步】启动服务（需要3个cmd窗口）
echo ----------------------------------------
echo 窗口1 - 启动RMI注册中心：
echo    rmiregistry 2500
echo    （保持此窗口打开）
echo.
echo 窗口2 - 启动服务器：
echo    cd server
echo    java SomeServer
echo    （等待显示"RMI服务启动成功"）
echo.
echo 窗口3 - 启动客户端：
echo    cd client
echo    java SomeClient
echo    （查看远程调用结果）
echo.
echo ========================================
echo    注意事项
echo ========================================
echo 1. 确保已安装JDK 8并配置环境变量
echo 2. 必须先启动 rmiregistry，再启动服务器
echo 3. 端口2500被占用时，修改端口号
echo 4. 所有窗口在同一目录下启动
echo ========================================
echo.
pause
