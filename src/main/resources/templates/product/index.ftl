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
                        <form role="form"  method="post" action="/sell/seller/product/save">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" name="productName" class="form-control" value="${(productInfo.productName)!''}" />
                            </div>

                            <div class="form-group">
                                <label>价格</label>
                                <input type="text" name="productPrice" class="form-control" value="${(productInfo.productPrice)!''}" />
                            </div>

                            <div class="form-group">
                                <label>库存</label>
                                <input type="number" name="productStock" class="form-control" value="${(productInfo.productStock)!''}" />
                            </div>

                            <div class="form-group">
                                <label>描述</label>
                                <input type="text" name="productDesc" class="form-control" value="${(productInfo.productDesc)!''}" />
                            </div>

                            <div class="form-group">
                                <label>图片</label>
                                <img src="${(productInfo.productIcon)!''}" width="100" height="100">
                                <input type="text" name="productIcon" class="form-control" value="${(productInfo.productIcon)!''}" />
                            </div>

                            <div class="form-group">
                                <label>类目</label>
                                <select name="categoryType" class="form-control">
                                    <#list productCategoryList as productCategory>
                                        <option value="productCategory.categoryType"
                                                <#if (productInfo.categoryType)?? && productInfo.categoryType = productCategory.categoryType>
                                                    selected
                                                </#if>
                                            >${productCategory.categoryName}
                                        </option>
                                    </#list>
                                </select>
                            </div>
                            <input type="text" name="productId" value="${(productInfo.productId)!''}" hidden>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>

</html>