@echo off
chcp 65001 >nul
echo ========================================
echo    现代RMI运行指南 (无需rmic)
echo    适用于 JDK 5+ (包括 JDK 25)
echo ========================================
echo.
echo 本脚本将按照以下步骤运行RMI实验：
echo.
echo [步骤1] 编译服务器端代码
echo [步骤2] 编译客户端代码
echo [步骤3] 启动RMI服务器（自动创建注册中心）
echo [步骤4] 启动RMI客户端（需要手动）
echo.
echo ========================================
echo    提示信息
echo ========================================
echo ✓ 现代RMI不需要rmic工具生成stub文件
echo ✓ JDK 5+自动使用动态代理机制
echo ✓ 服务器会自动创建RMI注册中心（端口2500）
echo ✓ 不需要手动运行rmiregistry命令
echo.
pause
cls

echo ========================================
echo    [步骤1] 编译服务器端代码
echo ========================================
cd server
echo 正在编译...
javac *.java
if %errorlevel% neq 0 (
    echo [错误] 服务器端编译失败！
    pause
    exit /b 1
)
echo [成功] 服务器端编译完成！
echo.
echo 生成的文件：
dir /b *.class
echo.
cd ..

echo ========================================
echo    [步骤2] 编译客户端代码
echo ========================================
cd client
echo 正在编译...
javac *.java
if %errorlevel% neq 0 (
    echo [错误] 客户端编译失败！
    pause
    exit /b 1
)
echo [成功] 客户端编译完成！
echo.
echo 生成的文件：
dir /b *.class
echo.
cd ..

echo ========================================
echo    编译完成总结
echo ========================================
echo ✓ 服务器端编译成功（server目录）
echo ✓ 客户端编译成功（client目录）
echo ✓ 无需生成stub文件（现代RMI自动处理）
echo.
echo ========================================
echo    [步骤3] 启动RMI服务器
echo ========================================
echo.
echo 即将启动服务器（自动创建注册中心）...
echo 服务器启动后，请保持此窗口打开
echo.
echo 提示：
echo   - 服务器将监听端口 2500
echo   - 按 Ctrl+C 可停止服务器
echo.
pause
cls

cd server
echo ========================================
echo    RMI服务器运行中...
echo ========================================
java SomeServer

cd ..
pause
