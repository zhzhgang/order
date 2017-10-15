# API

## 一、买家端

### 1. 商品

#### 1.1 商品列表

```
GET /sell/buyer/product/list
```

参数

```
无
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "name": "热销榜",
            "type": 1,
            "foods": [
                {
                    "id": "123456",
                    "name": "皮蛋粥",
                    "price": 1.2,
                    "description": "好吃的皮蛋粥",
                    "icon": "http://xxx.com",
                }
            ]
        },
        {
            "name": "好吃的",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "慕斯蛋糕",
                    "price": 10.9,
                    "description": "美味爽口",
                    "icon": "http://xxx.com",
                }
            ]
        }
    ]
}
```


### 2. 订单

#### 2.1 创建订单

```
POST /sell/buyer/order/create
```

参数

```
{
	"name": "张三"
	"phone": "18868822111"
	"address": "慕课网总部"
	"openid": "ew3euwhd7sjw9diwkq" //用户的微信openid
	"items": [{
	    "productId": "1423113435324",
	    "productQuantity": 2 //购买数量
	}]
}
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}
```