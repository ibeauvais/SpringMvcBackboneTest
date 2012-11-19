


function searchProduct(){

    var searchString=  $('#inputSearchProduct').val();

    $.ajax({
  url: '/rs/productSearch?searchString='+searchString,
  success: handlerSearchProduct,
  dataType: 'json'
});



    function handlerSearchProduct(data){

        console.log(data);
    }
}