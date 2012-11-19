<!DOCTYPE html>
<html>
<head>
    <title>Spring mvc test</title>
    <script src="/resources/js/jquery-1.8.3.js"></script>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/resources/js/product.js"></script>

    <script>
        $(document).ready(function () {
             $('#btnSearchProduct').click(function (){
                searchProduct();
             })
        });

    </script>
</head>
<body>
<form class="form-search">
    <fieldset>
        <legend>Search</legend>
        <label>Search Company</label>
        <input type="text" class="input-large search-query" placeholder="type here" id="inputSearchProduct">
        <button type="button" class="btn" id="btnSearchProduct">Search</button>
    </fieldset>
</form>

</body>
</html>