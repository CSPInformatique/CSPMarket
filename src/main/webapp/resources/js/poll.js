window.PollList = Backbone.Collection.extend({
	url: function(){
		return ctx + "/poll";
	}
});

window.PollsMenuView = Backbone.View.extend({
	el : ".pollsMenu-container",
    
    initialize : function() {
    	this.collection.fetch();
        this.template = _.template($("#pollsMenu-template").html());
        
        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
    },
    	
    render : function(){
    	var polls = this.collection.toJSON();
    	
    	var renderedContent = this.template({stocks : polls});
        $(this.el).html(renderedContent);
        
        for(var poll in polls){
        	$(".pollsMenu-container ." + poll.id).click(function(){
        		location.href = ctx + "/poll/" / poll.id;
        	});
        }
        
        return this;
    }
});