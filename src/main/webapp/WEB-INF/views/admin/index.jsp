<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring mvc test</title>
    <link href="<spring:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <script src="<spring:url value="/resources/js/jquery-1.8.3.js"/>"></script>
    <script src="<spring:url value="/resources/js/underscore.js"/>"></script>
    <script src="<spring:url value="/resources/js/backbone.js"/>"></script>
    <script src="<spring:url value="/resources/js/common.js"/>"></script>
    <script src="<spring:url value="/resources/js/model/product.js"/>"></script>
    <script src="<spring:url value="/resources/js/view/admin.js"/>"></script>


</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Products</a></li>
</ul>
<div class="container-fluid" id="main">
    <div class="row-fluid">
        <div id="menu">
            <div class="btn-group">
                <button class="btn" id="addProduct">add</button>
            </div>
        </div>
        <div id="container" class="container"></div>
    </div>
</div>
<script type="text/template" id="addProductForm">
    <div>
        <fieldset>
            <legend>Product to add</legend>
            <form id="formProduct" class="form-horizontal">
                <div class="control-group">
                    <label class="control-label" for="Name">Name</label>

                    <div class="controls"><input type="text" name="name" placeholder="name"/></div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="Description">Description</label>

                    <div class="controls"><textarea name="description" rows="5"></textarea></div>
                </div>
                <div class="control-group">
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary" id="addNewProduct">add</button>
                        <button type="button" class="btn" id="backListProduct">back</button>
                    </div>
                </div>

            </form>
        </fieldset>
    </div>
</script>
</body>

<script>
    $(document).ready(function () {
        var list = new Products();
        list.fetch({success :  function() {
            var mainView = new MainView({el:${'main'},model:list});
            mainView.render();
        }});

    });

</script>
</html>