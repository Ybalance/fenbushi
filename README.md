# Windows环境分布式系统实验完整指南

> **实验三 + 实验四 + 实验七 完整代码与报告**  
> 适用于：Windows 10/11 + JDK 8 + Tomcat 9 环境

---

## 📋 目录导航

- [实验包含内容](#实验包含内容)
- [快速开始](#快速开始)
- [实验三：客户/服务器应用开发](#实验三客户服务器应用开发)
- [实验四：RMI API](#实验四rmi-api)
- [实验七：Internet应用](#实验七internet应用)
- [常见问题排查](#常见问题排查)
- [文件结构说明](#文件结构说明)

---

## 🎯 实验包含内容

本实验包提供三个分布式系统实验的**完整代码、详细报告和问题排查手册**：

### ✅ 实验三：客户/服务器应用开发
- **技术栈：** Java Socket编程（UDP + TCP）
- **内容：**
  - UDP数据包Socket实现Daytime服务（获取服务器时间）
  - TCP流式Socket实现Echo服务（消息回声）
  - 三层架构设计（服务层、逻辑层、表示层）
- **文件数量：** 8个Java文件

### ✅ 实验四：RMI API
- **技术栈：** Java RMI远程方法调用
- **内容：**
  - 远程接口定义与实现
  - RMI注册中心使用（rmiregistry）
  - Stub文件生成（rmic）
  - 远程方法调用（sayHello、add、getServerTime）
- **文件数量：** 5个Java文件 + 1个批处理脚本

### ✅ 实验七：Internet应用
- **技术栈：** HTTP协议 + Tomcat + JSP
- **内容：**
  - 使用Socket API实现HTTP客户端
  - 隐式表单域传输会话数据
  - JSP接收和处理表单数据
- **文件数量：** 1个Java文件 + 2个Web文件（HTML + JSP）

### 📚 附加文档
- **实验报告：** 3篇详细实验报告（Markdown格式）
- **问题排查手册：** Windows环境常见问题解决方案
- **README：** 本使用指南

---

## 🚀 快速开始

### 环境要求

#### 必需软件：
- ✅ **JDK 8** （必须是1.8版本，实验四需要rmic工具）
- ✅ **Apache Tomcat 9** （实验七需要）
- ✅ **文本编辑器** （Notepad++、VS Code或IDE）

#### 系统要求：
- ✅ Windows 10 或 Windows 11
- ✅ 至少8GB内存
- ✅ 管理员权限（用于配置环境变量）

### 环境准备步骤

#### 1. 安装JDK 8

**下载：** 从Oracle官网下载JDK 8（或使用已有安装包）

**安装：** 默认安装到 `C:\Program Files\Java\jdk1.8.0_xxx`

**配置环境变量：**

1. 右键"此电脑" → "属性" → "高级系统设置" → "环境变量"
2. 在"系统变量"中新建：
   - 变量名：`JAVA_HOME`
   - 变量值：`C:\Program Files\Java\jdk1.8.0_xxx`（你的JDK安装路径）
3. 编辑"系统变量"中的`Path`，新增：
   - `%JAVA_HOME%\bin`
   - `%JAVA_HOME%\jre\bin`

**验证安装：**
```powershell
javac -version
# 应输出: javac 1.8.0_xxx

java -version
# 应输出: java version "1.8.0_xxx"

rmic -version
# 应输出: rmic version "1.8.0_xxx"
```

#### 2. 安装Tomcat 9（实验七需要）

**下载：** 从 [Apache Tomcat官网](https://tomcat.apache.org/) 下载Windows版本（zip格式）

**安装：** 解压到 `C:\apache-tomcat-9.0.x`

**验证安装：**
```powershell
# 启动Tomcat
cd C:\apache-tomcat-9.0.x\bin
startup.bat

# 浏览器访问
http://localhost:8080
# 应显示Tomcat欢迎页面
```

---

## 📘 实验三：客户/服务器应用开发

### 实验目标
使用Java Socket编程实现UDP和TCP通信程序。

### 文件位置
```
实验三-客户服务器应用开发/
├── MyClientDatagramSocket.java    # UDP客户端Socket封装
├── MyServerDatagramSocket.java    # UDP服务器Socket封装
├── DatagramMessage.java           # 数据报消息封装
├── DaytimeHelper1.java            # Daytime业务逻辑
├── DaytimeServer1.java            # Daytime服务器入口
├── DaytimeClient1.java            # Daytime客户端入口
├── EchoServer.java                # TCP Echo服务器
└── EchoClient.java                # TCP Echo客户端
```

### 快速运行

#### UDP Daytime程序（获取服务器时间）

**步骤1：编译所有文件**
```powershell
cd "实验三-客户服务器应用开发"
javac *.java
```

**步骤2：启动服务器**（新开cmd窗口1）
```powershell
java DaytimeServer1
# 应显示: UDP服务器启动，监听端口: 8888
```

**步骤3：启动客户端**（新开cmd窗口2）
```powershell
java DaytimeClient1
# 应显示当前服务器时间
```

**预期结果：**
```
========================================
   Daytime客户端启动
========================================
已连接到服务器: localhost:8888
发送请求: DAYTIME

服务器响应: 服务器时间: 2024-12-10 22:30:15
========================================
```

---

#### TCP Echo程序（消息回声）

**步骤1：启动服务器**（新开cmd窗口3）
```powershell
java EchoServer
# 应显示: TCP服务器启动，监听端口: 9999
```

**步骤2：启动客户端**（新开cmd窗口4）
```powershell
java EchoClient
```

**步骤3：交互测试**
```
请输入要发送的消息（输入 'bye' 退出）：
> Hello Server
服务器响应: Echo: Hello Server
> This is a test
服务器响应: Echo: This is a test
> bye
服务器响应: 再见！
```

### 常见问题

**问题：端口被占用**
```
java.net.BindException: Address already in use
```
**解决：**
```powershell
# 查找占用进程
netstat -ano | findstr 8888
# 结束进程
taskkill /PID <进程ID> /F
```

**更多问题：** 参考 `Windows问题排查手册.md`

---

## 📘 实验四：RMI API

### 实验目标
使用Java RMI实现远程方法调用，体验分布式计算。

### 文件位置
```
实验四-RMI_API/
├── server/
│   ├── SomeInterface.java         # 远程接口定义
│   ├── SomeImpl.java              # 远程接口实现
│   └── SomeServer.java            # RMI服务器
├── client/
│   ├── SomeInterface.java         # 远程接口（从server复制）
│   ├── SomeImpl_Stub.class        # Stub文件（从server复制）
│   └── SomeClient.java            # RMI客户端
└── 编译运行说明.bat                # 详细步骤说明
```

### 快速运行

#### 步骤1：编译服务器端代码

```powershell
cd "实验四-RMI_API\server"
javac *.java
```

#### 步骤2：生成Stub文件（重要！）

```powershell
rmic SomeImpl
# 应生成: SomeImpl_Stub.class
```

#### 步骤3：复制文件到客户端

```powershell
# 复制Stub文件
copy SomeImpl_Stub.class ..\client\
# 注意：SomeInterface.java已在client目录
```

#### 步骤4：编译客户端代码

```powershell
cd ..\client
javac *.java
```

#### 步骤5：启动RMI注册中心（窗口1）

```powershell
cd "实验四-RMI_API"
rmiregistry 2500
# 无输出是正常的，保持窗口打开
```

#### 步骤6：启动服务器（窗口2）

```powershell
cd server
java SomeServer
```

**预期输出：**
```
========================================
   RMI服务启动成功！
========================================
注册中心端口: 2500
服务名称: helloService
等待客户端调用...
```

#### 步骤7：启动客户端（窗口3）

```powershell
cd client
java SomeClient
```

**预期输出：**
```
测试1：调用 sayHello() 方法
返回结果: Hello, 张三! 欢迎使用RMI服务

测试2：调用 add() 方法
返回结果: 25 + 17 = 42

测试3：调用 getServerTime() 方法
返回结果: 2024-12-10 22:45:30

所有远程调用测试完成！
```

### 常见问题

**问题：ClassNotFoundException: SomeImpl_Stub**
```
解决：确保已执行 rmic SomeImpl，并将Stub文件复制到client目录
```

**问题：NotBoundException: helloService**
```
解决：确保启动顺序正确（rmiregistry → 服务器 → 客户端）
```

**问题：rmic命令找不到**
```
解决：必须使用JDK 8，JDK 9+已移除rmic工具
```

**更多问题：** 参考 `Windows问题排查手册.md`

---

## 📘 实验七：Internet应用

### 实验目标
理解HTTP协议，使用Socket实现HTTP客户端，学习表单数据传递。

### 文件位置
```
实验七-Internet应用/
├── SocketHTTP/
│   └── HttpSocketClient.java     # HTTP客户端程序
├── FormCookie/
│   ├── form.html                  # HTML表单页面
│   └── hiddenform.jsp             # JSP处理页面
└── 部署说明.txt                    # 详细部署步骤
```

### 快速运行

#### Part 1：HTTP Socket客户端

**步骤1：启动Tomcat**
```powershell
cd C:\apache-tomcat-9.0.x\bin
startup.bat
# 验证：浏览器访问 http://localhost:8080
```

**步骤2：编译并运行HTTP客户端**
```powershell
cd "实验七-Internet应用\SocketHTTP"
javac HttpSocketClient.java
java HttpSocketClient
```

**预期结果：**
显示Tomcat首页的完整HTTP响应（包括状态行、响应头、HTML源码）。

---

#### Part 2：隐式表单域传输会话数据

**步骤1：部署Web文件到Tomcat**
```powershell
# 复制form.html
copy "实验七-Internet应用\FormCookie\form.html" C:\apache-tomcat-9.0.x\webapps\ROOT\

# 复制hiddenform.jsp
copy "实验七-Internet应用\FormCookie\hiddenform.jsp" C:\apache-tomcat-9.0.x\webapps\ROOT\
```

**步骤2：重启Tomcat**
```powershell
cd C:\apache-tomcat-9.0.x\bin
shutdown.bat
startup.bat
```

**步骤3：浏览器访问**
```
http://localhost:8080/form.html
```

**步骤4：填写表单**
- 用户名：输入 "张三"
- 消息内容：输入 "测试消息"
- 点击"提交表单"

**步骤5：验证结果**

页面应跳转到 `hiddenform.jsp`，显示：
```
✅ 会话数据接收成功

🔑 会话ID（隐式传递）
winTest123

📝 用户名：张三
💬 消息内容：测试消息
```

### 常见问题

**问题：Tomcat启动失败 - 端口8080被占用**
```powershell
# 查找占用进程
netstat -ano | findstr :8080
# 结束进程
taskkill /PID <进程ID> /F
```

**问题：访问form.html显示404**
```
解决：确认文件在 webapps\ROOT 目录下，重启Tomcat
```

**问题：JSP页面中文乱码**
```
解决：
1. 用Notepad++将JSP文件转为UTF-8编码（无BOM）
2. 确认页面有 pageEncoding="UTF-8" 声明
3. 确认代码有 request.setCharacterEncoding("UTF-8")
```

**更多问题：** 参考 `Windows问题排查手册.md`

---

## 🔧 常见问题排查

### JDK环境问题

**问题：javac不是内部或外部命令**

**解决方案：**
1. 确认JDK已安装
2. 配置JAVA_HOME环境变量
3. 在Path中添加 `%JAVA_HOME%\bin`
4. 重新打开cmd窗口

**详细步骤：** 参考 `Windows问题排查手册.md` → 问题1.1

---

### 端口占用问题

**通用解决流程：**
```powershell
# 1. 查找占用端口的进程
netstat -ano | findstr <端口号>

# 2. 记录进程ID（最后一列）

# 3. 结束进程
taskkill /PID <进程ID> /F
```

**示例：**
```powershell
# 端口8888被占用
netstat -ano | findstr 8888
# 输出: TCP 0.0.0.0:8888 ... LISTENING 12345
taskkill /PID 12345 /F
```

---

### 防火墙拦截

**临时关闭防火墙测试：**
```powershell
# 以管理员身份运行PowerShell
Set-NetFirewallProfile -Profile Domain,Public,Private -Enabled False

# 测试完成后恢复
Set-NetFirewallProfile -Profile Domain,Public,Private -Enabled True
```

**添加防火墙规则：**
1. 打开"Windows Defender 防火墙"
2. 点击"高级设置"
3. "入站规则" → "新建规则"
4. 选择"端口"，输入端口号（8888、9999、2500、8080）
5. 允许连接，完成

---

## 📁 文件结构说明

```
Windows-分布式系统实验-实验三+实验四+实验七/
│
├── README.md                                    # 本使用指南
├── Windows问题排查手册.md                        # 常见问题解决方案
│
├── 实验三-客户服务器应用开发/
│   ├── MyClientDatagramSocket.java
│   ├── MyServerDatagramSocket.java
│   ├── DatagramMessage.java
│   ├── DaytimeHelper1.java
│   ├── DaytimeServer1.java
│   ├── DaytimeClient1.java
│   ├── EchoServer.java
│   └── EchoClient.java
│
├── 实验四-RMI_API/
│   ├── server/
│   │   ├── SomeInterface.java
│   │   ├── SomeImpl.java
│   │   └── SomeServer.java
│   ├── client/
│   │   ├── SomeInterface.java
│   │   └── SomeClient.java
│   └── 编译运行说明.bat
│
├── 实验七-Internet应用/
│   ├── SocketHTTP/
│   │   └── HttpSocketClient.java
│   ├── FormCookie/
│   │   ├── form.html
│   │   └── hiddenform.jsp
│   └── 部署说明.txt
│
└── 实验报告/
    ├── 实验三-客户服务器应用开发实验报告.md
    ├── 实验四-RMI_API实验报告.md
    └── 实验七-Internet应用实验报告.md
```

---

## 📝 实验报告使用说明

### 报告位置
`实验报告/` 目录下包含三篇详细实验报告（Markdown格式）。

### 报告内容

每篇报告包含：
1. **实验概述：** 实验信息、环境、目的
2. **实验原理：** 技术原理、架构设计、流程图
3. **实验步骤与结果：** 详细操作步骤、代码分析、运行结果
4. **问题与解决方案：** 常见问题排查
5. **实验总结：** 核心收获、不足分析、改进方向

### 如何使用报告

#### 方案1：在线查看（推荐）
使用Markdown阅读器（如Typora、VS Code）查看，支持格式化显示。

#### 方案2：转换为Word
使用Pandoc或在线工具将Markdown转换为Word格式：
```powershell
# 使用Pandoc（需先安装）
pandoc 实验三-客户服务器应用开发实验报告.md -o 实验三报告.docx
```

#### 方案3：打印PDF
在Markdown阅读器中"导出为PDF"或"打印"保存为PDF。

### 报告撰写建议

报告中标注了"截图位置"的地方，需要你运行程序后自行截图补充：
- **图1、图2...：** 按照标注位置截取实际运行结果
- **代码部分：** 已提供完整代码，可直接复制
- **问题分析：** 根据实际遇到的问题补充

---

## 🎓 学习路径建议

### 初学者路线

**Day 1：环境准备**
1. 安装JDK 8和Tomcat 9
2. 配置环境变量
3. 验证安装（javac、java、rmic、Tomcat）

**Day 2：实验三（Socket编程）**
1. 学习Socket基础知识
2. 运行UDP Daytime程序
3. 运行TCP Echo程序
4. 分析代码，理解三层架构

**Day 3：实验四（RMI）**
1. 学习RMI原理
2. 按步骤编译、部署、运行
3. 理解Stub/Skeleton机制
4. 尝试修改远程方法

**Day 4：实验七（HTTP + JSP）**
1. 学习HTTP协议基础
2. 运行HTTP Socket客户端
3. 部署Web应用到Tomcat
4. 理解隐式表单域原理

**Day 5：撰写报告**
1. 阅读提供的实验报告模板
2. 运行程序并截图
3. 补充个人实验心得
4. 整理问题与解决方案

### 进阶学习方向

1. **Socket编程进阶：**
   - 实现多线程服务器（线程池）
   - 添加心跳检测机制
   - 实现简单的聊天室

2. **RMI进阶：**
   - 添加异常处理和重试机制
   - 实现动态代理
   - 学习RMI + JNDI

3. **Web应用进阶：**
   - 学习Servlet技术
   - 使用JDBC连接数据库
   - 学习MVC设计模式
   - 实现用户登录注册功能

---

## ⚠️ 重要提示

### 必须遵守的规则

1. **JDK版本：** 实验四必须使用JDK 8（不能用JDK 9+）
2. **启动顺序：** RMI实验必须先启动rmiregistry，再启动服务器，最后启动客户端
3. **文件编码：** 所有Java/JSP文件必须保存为UTF-8编码（无BOM）
4. **端口占用：** 运行前检查端口是否被占用
5. **文件路径：** 注意区分服务器端和客户端文件位置

### 安全注意事项

1. **防火墙：** 实验时可能需要允许Java和Tomcat访问网络
2. **端口开放：** 仅在本地测试，不要暴露到公网
3. **代码安全：** 实验代码未实现安全认证，不要用于生产环境

---

## 📞 技术支持

### 遇到问题？

1. **优先查阅：** `Windows问题排查手册.md`
2. **查看报告：** 对应实验的实验报告中的"问题与解决方案"章节
3. **检查环境：** 使用文末的"环境检查清单"自查

### 常用资源

- **JDK文档：** https://docs.oracle.com/javase/8/docs/
- **Tomcat文档：** https://tomcat.apache.org/tomcat-9.0-doc/
- **Java教程：** https://www.runoob.com/java/

---

## ✅ 实验验收清单

### 实验三

- [ ] UDP Daytime服务器能正常返回时间
- [ ] UDP Daytime客户端能接收并显示时间
- [ ] TCP Echo服务器能原样返回消息
- [ ] TCP Echo客户端能发送并接收多条消息
- [ ] 代码注释完整
- [ ] 实验报告包含运行截图

### 实验四

- [ ] rmiregistry正常启动（端口2500）
- [ ] 服务器显示"RMI服务启动成功"
- [ ] 客户端成功调用所有远程方法（sayHello、add、getServerTime）
- [ ] Stub文件已生成并正确部署
- [ ] 理解RMI三层架构
- [ ] 实验报告包含三个窗口的截图

### 实验七

- [ ] HttpSocketClient成功接收HTTP响应
- [ ] form.html页面正常显示，样式美观
- [ ] 提交表单后能跳转到hiddenform.jsp
- [ ] JSP页面正确显示会话ID "winTest123"
- [ ] 用户名和消息内容正确传递
- [ ] 无中文乱码问题
- [ ] 实验报告包含浏览器访问截图

---

## 🎉 结语

本实验包提供了Windows环境下分布式系统实验的完整解决方案，从代码到报告，从问题排查到学习路径，力求让你轻松完成实验并深入理解分布式系统的核心概念。

祝你实验顺利！🚀

---

**更新时间：** 2024年12月10日  
**版本：** v1.0  
**适用环境：** Windows 10/11 + JDK 8 + Tomcat 9

**联系方式：** [请填写你的联系方式]  
**GitHub：** [可选：上传到GitHub后填写链接]
