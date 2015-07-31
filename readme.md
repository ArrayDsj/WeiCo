# WeiCo
一个简单的GUI版聊天工具
使用到了 GUI 多线程 IO操作 异常处理 正则表达式等知识


1. client 包
    - Client.java  客户端类
    - ClientFrame.java 主窗体
    - ClientJpanel.java 面板

2. exception 包
    - MessageException.java 自定义异常类 

3. server 包
    - Server.java 服务端类
    - ProcessMsgThread.java  服务端的多线程实现

4. test 包
    - TestMain.java 测试类


## 07.30 版本 0.0.1

- 实现了本机交互;
- 能传输二进制数据,但是由于是在文本域中显示,暂时只能收发文本信息;
- 界面丑陋;
- 只能一对一传输数据
- 未添加快捷键
- 服务器使用多线程

## version 0.0.0

- 添加IP地址下拉列表 同过选择不同的IP地址连接到不同的局域网主机
- 添加自动回复功能
- 添加快捷键




