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