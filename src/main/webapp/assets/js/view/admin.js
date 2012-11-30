var ProductsListView = Backbone.View.extend({
    template: _.template('<tr><td><%= product.id %></td><td><%= product.name %></td><td><%= product.creationDate %></td></tr>'),
    render: function() {
        var myTemplate = this.template;

        var data = '<table class="table table-hover"><tr><th>id</th><th>Name</th><th>Creation Date</th></tr>';
        this.model.each(function(product) {
            data += myTemplate({product: product.toJSON() });
        });

        data += '</table>';
        $(this.el).html(data);


        return this;


    }

});

var ProductCreationView = Backbone.View.extend({
    initialize  : function() {
        this.template = _.template($('#addProductForm').html());

    } ,

    render : function() {
        $(this.el).html(this.template());
    }

});


var MainView = Backbone.View.extend({
    initialize  : function() {
        var el = $('#container');

        this.productsList = new ProductsListView({el:el,model:this.model});
        this.productCreation = new ProductCreationView({el:el});


    } ,
    events:{
        'click #addProduct' : 'addProduct',
        'click #backListProduct' : "backListProduct",
        'submit #formProduct' : "addNewProduct"
    } ,
    render: function() {
        this.productsList.render();
        return this;
    }, addProduct : function() {
        this.productCreation.render();
    },
    backListProduct: function() {
        this.productsList.render();
    },
    addNewProduct: function(e) {
        e.preventDefault();
        var myView = this;
        var product = new Product($('#formProduct').serializeJSON());
        product.save(null, {success : function() {
            console.log('a');
            console.log(myView);
            myView.model.add(product);
            myView.backListProduct();
        }});

    }

});