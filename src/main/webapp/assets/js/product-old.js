function searchProduct() {

    var searchString = $('#inputSearchProduct').val();

    $.ajax({
        url: '/rs/productSearch?searchString=' + searchString,
        success: handlerSearchProduct,
        dataType: 'json'
    });
}

function handlerSearchProduct(data) {

    console.log(data);
}


var Product = Backbone.Model.extend({
        defaults : {
            title : "empty product",
            content : "empty content",
            creationDate : new Date()
        },
        initialize
            :
            function () {
                console.log("Product creation")
            }
    }
)
    ;

var SearchProducts = Backbone.Collection.extend({

    model : Product,
    url : function() {
        return  "rs/productSearch?searchString=" + this.searchTerm;
    }
});

var templateSeach= "<% _.each(products, function(product) { %><li><%= product.id %> : <%= product.name %></li><% }); %>";


var test=  Backbone.View.extend({
    el : $("#search" ),
     events : {
    "submit": "search"
  }
    ,
    search : function(e){
        e.preventDefault();
        var results = new SearchProducts();
        results.searchTerm = $('#inputSearchProduct').val();
        new   SearchView(results).render();
    }



});

var SearchView = Backbone.View.extend({
    el : $('#container'),

    initialize : function() {
        alert(this.collection.url());
        this.collection.fetch();
            this.template = _.template(templateSeach);

        },
    render : function() {

            var renderedContent = this.template({products : this.collection.toJSON()});
        alert(renderedContent);
            $(this.el).html(renderedContent);
            return this;
        }


});


function bindSearch() {
   new test().render();
//        var results = new SearchProducts();
//        results.searchTerm = $('#inputSearchProduct').val();
//        results.fetch({
//            success: function(resp, status, xhr) {
//                alert(resp.toJSON());
//               $('#container').html(resp.toJSON());
//            }
//        });
//    });


}