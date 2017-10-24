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
                                <th>商品编号</th>
                                <th>商品名称</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list pageInfo.list as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img src="${productInfo.productIcon}" width="100" height="100"></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDesc}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.ctime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${productInfo.utime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                <td>
                                    <#if productInfo.productStatus == 0 >
                                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
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
                            <li><a href="/sell/seller/product/list?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>
                        </#if>

                        <#list 1..pageInfo.pages as page>
                            <#if pageInfo.pageNum == page>
                                <li class="disabled"><a href="#">${page}</a></li>
                            <#else>
                                <li><a href="/sell/seller/product/list?page=${page}&size=${pageInfo.pageSize}">${page}</a></li>
                            </#if>
                        </#list>

                        <#if pageInfo.isLastPage>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>

</html>