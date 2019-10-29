# 短网址服务

#### 运行条件
安装有jdk1.8及以上版本、redis、mysql

mysql 数据库创建脚本：`short_url.sql` 文件

#### 配置项

您可以直接修改`application-dev.properties`文件中的配置项，也可以新建一个
以修改`application-dev.properties`文件为例(使用了`short_url.sql`脚本文件创建的数据库表)
您需要修改以下选项
````
spring.datasource.username=db-username
spring.datasource.password=db-password
spring.datasource.url=jdbc:mysql://db-url:3306/short_url?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.redis.host=redis-host
spring.redis.password=redis-passord
const.domain=http://localhost:9170/
const.key=u.apitest.link

spring.datasource.username 为mysql数据库的用户名
spring.datasource.password 为mysql数据库的连接密码
spring.datasource.url 为mysql数据库的连接地址
spring.redis.host 为redis数据库的连接地址
spring.redis.password 为redis数据库的连接密码
const.domain 为api请求返回短连接结果时的domain地址，例如为 localhost:8080 时，api返回的短连接为 http://localhost:8080/abcdef 
const.key 为api请求时使用的key，因为api是明文调用，实际使用意义不大
````

#### 使用方法，请求接口

````
apiurl: {host}/generateShort
method: POST
http-header: Content-Type:application/json
params type: JSON
params example:
{
	"url":"https://www.baidu.com",//需要访问的网址
	"key":"u.apitest.link"//授权key，暂时固定为此key
}
result example:
{
	"msg": "success",
	"code": 200,
	"data": "http://u.apitest.link/waMDC1"
}
````

#### 其他信息
您可以直接使用[http://u.apitest.link/generateShort](http://u.apitest.link/generateShort)来请求您的短连接，但这里并不保证能长久使用。

该服务器资源目前的到期时间为`2020-10-28`,部署于[腾讯云](https://cloud.tencent.com/act/double11/reserve?spread_hash_key=oNHy0e "双十一优惠")北京节点。

在此服务器资源到期前，您的短连接内容将可以长久使用。

如果您恰巧需要服务器资源，以下有常用服务器商的直达连接：

[阿里云](https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=9hyzg1lj "最高￥2000云产品通用代金券")

[腾讯云](https://cloud.tencent.com/act/cps/redirect?redirect=1040&cps_key=e25a38fea3b39d5c401b0bb397df39e2&from=console "新用户3折")


