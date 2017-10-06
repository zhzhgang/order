package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-06 23:32
 */
@Repository
public interface OrderDetailDao {

    List<OrderDetail> findByOrderId(String orderId);
}
