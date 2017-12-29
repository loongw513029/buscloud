数据库设计：MongoDb + MySql组合

MongoDb：
    1.CAN数据
    2.GPS数据
    3.设备状态数据
    4.CAN状态
    5.心跳数据

MySql:
    1.基础数据
    2.对于热数据的统计数据
    3.即时数据（保留最后一条）如，CAN状态统计即时数据，雷达即时数据，设备状态即时数据


网站结构：(SSM)
    接口: SpringBoot
    网站: SpringMvc
    数据持久层： MyBatis3 注解式 CDRU
    缓存: MongoDb/Redis
    前端：Extjs5 or bootstrap+Angualr4 未决定？

服务器：
    nginx代理
