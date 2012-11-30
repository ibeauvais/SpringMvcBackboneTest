<!DOCTYPE html>
<html>
<head>
    <title>Spring mvc test</title>
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <script src="resources/js/jquery-1.8.3.js"></script>
    <script src="resources/js/underscore.js"></script>
    <script src="resources/js/backbone.js"></script>
    <script src="resources/js/product.js"></script>


</head>
<body>
<form class="form-search" id="search">
    <fieldset>
        <legend>Search</legend>
        <label>Search Company</label>
        <input type="text" class="input-large search-query" placeholder="type here" id="inputSearchProduct">
        <button type="submit" class="btn" id="btnSearchProduct">Search</button>
    </fieldset>
</form>
   <div id="container"></div>
</body>
 <script>
        $(document).ready(function () {
        bindSearch();
});

    </script>
</html>