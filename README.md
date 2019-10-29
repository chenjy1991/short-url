# 短网址服务

#### how to user

````
apiurl: {host}/generateShort
method: POST
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

