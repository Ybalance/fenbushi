#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
简单的HTTP测试服务器
用于测试HTTP Socket客户端
"""

import http.server
import socketserver

PORT = 8080

class MyHTTPRequestHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html; charset=utf-8')
        self.end_headers()
        
        html_content = f"""<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>HTTP测试服务器</title>
    <style>
        body {{
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 50px;
            text-align: center;
        }}
        .container {{
            background: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            padding: 40px;
            max-width: 600px;
            margin: 0 auto;
        }}
        h1 {{ margin: 0 0 20px 0; }}
        .info {{ background: rgba(0, 0, 0, 0.2); padding: 15px; border-radius: 8px; margin-top: 20px; }}
    </style>
</head>
<body>
    <div class="container">
        <h1>✅ HTTP测试服务器运行中</h1>
        <p>这是一个用于测试HTTP Socket客户端的简单服务器</p>
        <div class="info">
            <p><strong>端口:</strong> 8080</p>
            <p><strong>路径:</strong> {self.path}</p>
            <p><strong>HTTP方法:</strong> GET</p>
        </div>
        <p style="margin-top: 30px;">🎉 如果你能看到这个页面，说明HTTP服务器正常运行！</p>
    </div>
</body>
</html>"""
        
        self.wfile.write(html_content.encode('utf-8'))

print("=" * 50)
print("   简单HTTP测试服务器")
print("=" * 50)
print(f"服务器地址: http://localhost:{PORT}")
print("按 Ctrl+C 停止服务器")
print("=" * 50)

with socketserver.TCPServer(("", PORT), MyHTTPRequestHandler) as httpd:
    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        print("\n服务器已停止")
