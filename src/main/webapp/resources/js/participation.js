window.Participation = Backbone.Model.extend({
	idAttribute: "id",
	url : function() {
		return ctx + "/poll/" + this.pollId + "/participation";
	}
});