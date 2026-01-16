<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会话数据接收结果</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: "Microsoft YaHei", Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            padding: 40px;
            max-width: 600px;
            width: 100%;
        }
        
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 10px;
            font-size: 28px;
        }
        
        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
            font-size: 14px;
        }
        
        .success-icon {
            text-align: center;
            font-size: 60px;
            margin-bottom: 20px;
        }
        
        .result-box {
            background: #f0f4ff;
            border-radius: 10px;
            padding: 25px;
            margin-bottom: 20px;
        }
        
        .result-item {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            border-bottom: 1px solid #e1e8ed;
        }
        
        .result-item:last-child {
            border-bottom: none;
        }
        
        .result-label {
            font-weight: bold;
            color: #555;
            font-size: 15px;
        }
        
        .result-value {
            color: #667eea;
            font-weight: bold;
            font-size: 15px;
            word-break: break-all;
        }
        
        .highlight-box {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        
        .highlight-box h2 {
            font-size: 18px;
            margin-bottom: 10px;
        }
        
        .highlight-box .session-id {
            font-size: 24px;
            font-weight: bold;
            font-family: "Consolas", monospace;
            letter-spacing: 2px;
        }
        
        .info-section {
            background: #e8f5e9;
            border-left: 4px solid #4caf50;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        
        .info-section h3 {
            color: #2e7d32;
            margin-bottom: 10px;
            font-size: 16px;
        }
        
        .info-section p {
            color: #555;
            font-size: 14px;
            line-height: 1.6;
            margin-bottom: 8px;
        }
        
        .back-button {
            display: block;
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        
        .back-button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }
        
        .footer {
            margin-top: 25px;
            text-align: center;
            color: #999;
            font-size: 12px;
        }
        
        code {
            background: #f5f5f5;
            padding: 2px 6px;
            border-radius: 3px;
            color: #d63384;
            font-family: "Consolas", monospace;
            font-size: 13px;
        }
    </style>
</head>
<body>
    <%
        // 设置请求编码为UTF-8
        request.setCharacterEncoding("UTF-8");
        
        // 获取表单提交的数据
        String username = request.getParameter("username");
        String message = request.getParameter("message");
        String sessionId = request.getParameter("sessionId");
        
        // 获取当前时间
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new java.util.Date());
    %>
    
    <div class="container">
        <div class="success-icon">✅</div>
        <h1>会话数据接收成功</h1>
        <p class="subtitle">实验七 - JSP页面接收表单数据</p>
        
        <div class="highlight-box">
            <h2>🔑 会话ID（隐式传递）</h2>
            <div class="session-id"><%= sessionId != null ? sessionId : "未接收到会话ID" %></div>
        </div>
        
        <div class="result-box">
            <div class="result-item">
                <span class="result-label">📝 用户名：</span>
                <span class="result-value"><%= username != null ? username : "未提供" %></span>
            </div>
            <div class="result-item">
                <span class="result-label">💬 消息内容：</span>
                <span class="result-value"><%= message != null ? message : "未提供" %></span>
            </div>
            <div class="result-item">
                <span class="result-label">⏰ 接收时间：</span>
                <span class="result-value"><%= currentTime %></span>
            </div>
            <div class="result-item">
                <span class="result-label">🌐 客户端IP：</span>
                <span class="result-value"><%= request.getRemoteAddr() %></span>
            </div>
            <div class="result-item">
                <span class="result-label">🔧 请求方法：</span>
                <span class="result-value"><%= request.getMethod() %></span>
            </div>
        </div>
        
        <div class="info-section">
            <h3>✨ 实验验证成功</h3>
            <p><strong>隐式表单域传递验证：</strong></p>
            <p>✓ 隐式表单域 <code>sessionId</code> 成功传递到服务器端</p>
            <p>✓ JSP通过 <code>request.getParameter("sessionId")</code> 成功获取会话ID</p>
            <p>✓ 会话数据在客户端不可见，但服务器端能够正常接收</p>
        </div>
        
        <a href="form.html" class="back-button">🔙 返回表单页面</a>
        
        <div class="footer">
            Windows环境 + JDK 8 + Tomcat 9 实验<br>
            实验完成时间: <%= currentTime %>
        </div>
    </div>
</body>
</html>
