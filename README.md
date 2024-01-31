# Backend-for-Frontend

## 1. 安装指南

### 1.1 配置 hosts 文件

为了顺利进行开发，您需要预先配置系统的 `hosts` 文件，在 windows 系统中，该文件通常位于`C:\Windows\System32\drivers\etc`
请按照以下格式添加或修改内容：
```
127.0.0.1 local.atnibam.space
{NacosIP} nacos.bff-dev.com
{RedisIP} redis.bff-dev.com
```
请注意将 `{NacosIP}` 和 `{RedisIP}` 替换为实际的 Nacos 服务器和 Redis 服务器的 IP 地址。