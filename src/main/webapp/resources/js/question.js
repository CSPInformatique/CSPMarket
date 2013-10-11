window.PollQuestionsList = Backbone.Collection.extend({
	comparator: function(question){
		return parseInt(question.get("index"));
	},
	
	url: function(){
		return ctx + "/poll/" + this.pollId + "/question";
	}
});

window.QuestionAnswer = Backbone.Model.extend({
	idAttribute: "id",
	url : function() {
		var base = ctx + "/poll/" + this.pollId + "/question/" + this.questionId + "/answer" ;
		if (this.isNew()) return base;
		return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id;
	}
});

window.QuestionAnswersList = Backbone.Collection.extend({
	comparator: function(questionAnswer){
		return parseInt(questionAnswer.get("index"));
	},
	model: QuestionAnswer,
	url : function() {
		return ctx + "/poll/" + this.pollId + "/question/" + this.questionId + "/answer";
	}
});

window.QuestionListView = Backbone.View.extend({	
	el : ".questionList-container",
  
    initialize : function() {
        this.template = _.template($("#questionList-template").html());
        
        /*--- binding ---*/
        _.bindAll(this, "render");
        this.collection.bind("change", this.render);
        this.collection.bind("add", this.render);
        this.collection.bind("remove", this.render);
        /*---------------*/
    },
    	
    render : function(){
    	var renderedContent = this.template({questions : this.collection.toJSON()});
    	
        $(this.el).html(renderedContent);
        
        return this;
    }
});

window.QuestionView = Backbone.View.extend({
	el : ".question-container",
    
    initialize : function() {
    	this.template = _.template($("#question-template").html());

    	this.render();
    },
    	
    render : function(){
    	var renderedContent = this.template({question : this.model});
        $(this.el).html(renderedContent);
        
        $(".questionList-container li").removeClass("active");
        $(".questionList-container li." + this.model.index).addClass("active");
        
        return this;
    }
});