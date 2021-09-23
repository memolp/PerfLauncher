# -*- coding:utf-8 -*-

"""
  压测的基础场景，用于定义一系列的接口
"""
# java 类
from org.jeff.test import HTTPClient


class SampleHttpScene(HTTPClient):
    """
    基础场景
    """
    def __init__(self, uid):
        """
        @sig public HTTPClient(int uid)
        :param uid: 用户id
        """
        super(SampleHttpScene, self).__init__(uid)

    def OnConcurrence(self, count):
        """
        @sig public void OnConcurrence(int count) throws Exception
        并发执行调用
        :param count:
        :return:
        """
        self.Connect("www.baidu.com", 80, 1)

    def OnInit(self):
        """
        @sig public void OnInit() throws Exception
        uid创建后调用
        :return:
        """
        self.SetInitCompleted(True)
        self.SetCurrentCompleted(True)

    def OnDisconnect(self, sock_id):
        """
        @sig public void OnDisconnect(int sockId) throws Exception
        网络链接断开调用
        :param sock_id: 调用uid.connect时传入的sock_id
        :return:
        """
        pass

    def OnConnected(self, sock_id):
        """
        @sig public void OnConnected(int sockId) throws Exception
        网络链接完成
        :param sock_id: 调用uid.connect时传入的sock_id
        :return:
        """
        self.StartTranslation("http")
        self.Send("GET / HTTP/1.0\r\nWHO: qingf\r\nWHY:java-https-test\r\nConnection: Keep-Alive\r\nContent-Length:0\r\n\r\n", sock_id)
        

    def OnTimer(self, timestamp):
        """
        @sig public void OnTimer(long timestamp) throws Exception
        定时器
        :param timestamp:
        :return:
        """
        pass
        
    def OnMessage(self, sock_id, packet, timestamp):
        """
        @sig public void OnMessage(int sockId, Object data, long timestamp) throws Exception
        网络协议调用
        """
        self.EndTranslation("http")
        self.CloseServerID(sock_id)

    def OnDestory(self):
        """
        @sig public void OnDestory()
        压测退出
        :return:
        """
        super(SampleHttpScene, self).OnDestory()

    def OnReset(self):
        """
        @sig public void OnReset()
        重置数据
        """
        super(SampleHttpScene, self).OnReset()


# 这个是固定的方法，用于创建这个用户对象
def CreateScene(uid):
    return SampleHttpScene(uid)
