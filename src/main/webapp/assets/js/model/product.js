var Product = Backbone.Model.extend({
    url : function() {
        return "rs/product"
    }
});


var Products = Backbone.Collection.extend({
    model: Product,
    url : function() {
        return "rs/products"
    }
});









