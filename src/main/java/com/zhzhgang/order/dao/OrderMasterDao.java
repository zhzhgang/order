package com.zhzhgang.order.dao;

import com.zhzhgang.order.domain.OrderMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangzhonggang
 * 2017-10-06 23:14
 */
@Repository
public interface OrderMasterDao {

    void save(OrderMaster orderMaster);

    List<OrderMaster> findByBuyerOpenId(String buyerOpenId);

}
