<html xmlns="http://www.w3.org/1999/html">

    <#include "../common/header.ftl">

    <body>
        <div id="wrapper" class="toggled">
            <#--边栏-->
            <#include "../common/nav.ftl">

            <#--主要内容区域-->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>订单编号</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list pageInfo.list as orderMaster>
                                <tr>
                                    <td>${orderMaster.orderId}</td>
                                    <td>${orderMaster.buyerName}</td>
                                    <td>${orderMaster.buyerPhone}</td>
                                    <td>${orderMaster.buyerAddress}</td>
                                    <td>${orderMaster.orderAmount}</td>
                                    <td>${orderMaster.getOrderStatusEnum()}</td>
                                    <td>${orderMaster.getPayStatusEnum()}</td>
                                    <td>${orderMaster.ctime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                    <td><a href="/sell/seller/order/detail?orderId=${orderMaster.orderId}">详情</a></td>
                                    <td>
                                        <#if orderMaster.orderStatus == 0 >
                                            <a href="/sell/seller/order/cancel?orderId=${orderMaster.orderId}">取消</a>
                                        </#if>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>

                        <#--分页-->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">

                            <#if pageInfo.isFirstPage>
                                <li class="disabled"><a href="#">上一页</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>
                            </#if>

                            <#list 1..pageInfo.pages as page>
                                <#if pageInfo.pageNum == page>
                                    <li class="disabled"><a href="#">${page}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${page}&size=${pageInfo.pageSize}">${page}</a></li>
                                </#if>
                            </#list>

                            <#if pageInfo.isLastPage>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                            </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>

</html>