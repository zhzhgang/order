-- 商品表
create table `product_info` (
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '单价',
	`product_stock` int not null comment '库存',
	`product_desc` varchar(64) comment '描述',
	`product_icon` varchar(512) comment '图片',
	`product_status` tinyint(3) not null default '0' comment '商品状态，0正常1下家',
	`category_type` int comment '类目编号',
	`status` char(1) not null default 'a' comment '状态，默认a有效',
	`ctime` timestamp not null default current_timestamp comment '创建时间',
	`utime` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`product_id`)	
) comment '商品表';

-- 类目表
create table `product_category` (
	`category_id` int not null auto_increment,
	`category_name` varchar(64) not null comment '类目名称',
	`category_type` int not null comment '类目编号',
	`status` char(1) not null default 'a' comment '状态，默认a有效',
	`ctime` timestamp not null default current_timestamp comment '创建时间',
	`utime` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`category_id`),
	unique key `uqe_category_type` (`category_type`)
) comment '类目表';

-- 订单主表
create table `order_master` (
	`order_id` varchar(32) not null,
	`buyer_name` varchar(64) not null comment '买家姓名',
	`buyer_phone` varchar(32) not null comment '买家电话',
	`buyer_address` varchar(128) not null comment '买家地址',
	`buyer_openid` varchar(64) not null comment '买家微信openid',
	`order_amount` decimal(8,2) not null comment '订单总金额',
	`order_status` tinyint(3) not null default '0' comment '订单状态，默认为0新下单',
	`pay_status` tinyint(3) not null default '0' comment '支付状态，默认为0未支付',
	`status` char(1) not null default 'a' comment '状态，默认a有效',
	`ctime` timestamp not null default current_timestamp comment '创建时间',
	`utime` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`order_id`),
	key `idx_buyer_openid` (`buyer_openid`)
) comment '订单主表';

-- 订单详情表
create table order_detail (
	`detail_id` varchar(32) not null,
	`order_id` varchar(32) not null,
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '商品价格',
	`product_quantity` int not null comment '商品数量',
	`product_icon` varchar(512) comment '商品小图',
	`status` char(1) not null default 'a' comment '状态，默认a有效',
	`ctime` timestamp not null default current_timestamp comment '创建时间',
	`utime` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`detail_id`),
	key `idx_order_id` (`order_id`)
) comment '订单详情表';

alter table `order_master` add index idx_order_master_ctime (`ctime`);

alter table `product_info` add index idx_product_info_ctime (`ctime`);

-- 卖家信息表
create table `seller_info` (
  `seller_id` varchar(32) not null,
  `username` varchar(32) not null,
  `password` varchar(32) not null,
  `openid` varchar(64) not null comment '微信 openid',
  `ctime` timestamp not null default current_timestamp comment '创建时间',
	`utime` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
  primary key (`seller_id`)
) comment '卖家信息表';