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
                        <form role="form"  method="post" action="/sell/seller/category/save">
                            <div class="form-group">
                                <label>类目编号</label>
                                <input type="number" name="categoryType" class="form-control" value="${(productCategory.categoryType)!''}" />
                            </div>

                            <div class="form-group">
                                <label>类目名称</label>
                                <input type="text" name="categoryName" class="form-control" value="${(productCategory.categoryName)!''}" />
                            </div>

                            <input type="text" name="categoryId" value="${(productCategory.categoryId)!''}" hidden>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>

</html>