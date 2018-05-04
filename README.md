####lizhen-shop-parent 是整个项目的父项目

>#####lizhen-shop-api 是所有项目的接口项目
>>######lizhen-shop-api-member 会员项目的接口项目
>#####lizhen-shop-eurekaserver 注册中心的项目
>#####lizhen-shop-common 基础项目，共同的业务逻辑会放在这
>>BaseRedisService --- redis的连接<br>
>>LogAspectServiceApi --- aop拦截所有请求打印日志<br>
>>BaseApiService --- response相应数据的封装<br>
>#####lizhen-shop-member 会员服务
>>登录，注册，token的登录
>>整合了swaager，作为项目的api接口文档,使用文档应该注意以下几点
>>1.选择swaager2不然中文不会出来，必须是在服务接口项目里面在可以，web项目无法使用fegin，原因暂时不知道
>#####lizhen-shop-message 消息服务
>>含有邮件的发送<br>
lombok----插件，用于get和set方法的生成，以及日志打印


       
     
      