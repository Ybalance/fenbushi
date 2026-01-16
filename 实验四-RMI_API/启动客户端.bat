@echo off
chcp 65001 >nul
echo ========================================
echo    RMI客户端启动
echo ========================================
echo.
echo 提示：
echo   - 请确保服务器已启动
echo   - 服务器地址: rmi://localhost:2500/helloService
echo.
pause

cd client
java SomeClient
cd ..

echo.
pause
