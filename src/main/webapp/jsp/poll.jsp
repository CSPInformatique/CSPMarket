<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	var pollQuestionsList;
	var questionIndex = -1;
	var participation;
	var questionAnswerList;
	
	var initPage = function(){
		pollQuestionsList = new PollQuestionsList();
		participation = new Participation();
		participation.pollId = parseInt("${pollId}");
		
		pollQuestionsList.pollId = participation.pollId;
		new QuestionListView({collection : pollQuestionsList});
		pollQuestionsList.fetch({success: function(){
			if(pollQuestionsList.length > 0){
				participation.fetch({
					success: function(){
						++questionIndex;
						displayQuestion();
					},
					error: displayQuestion
				});
			}
		}});
		
		$("button.next, button.finish").click(displayNextQuestion);
		$("button.previous").click(displayPreviousQuestion);
	};
	
	var displayParticipationForm = function(){
		var template = _.template($("#participation-template").html());
		$(".question-container").html(template({participation : participation.toJSON()}));
	};
	
	var displayNextQuestion = function(){		
		if(questionIndex < pollQuestionsList.toJSON().length){
			if(questionIndex == -1){
				
				var gender = $("input[name='gender']:checked").val();
				var age = $(".age").val();
				var activity = $(".activity").val();
				var zipCode = $(".zipCode").val();
				
				if(	gender != null && gender != "" && 
					age != null && age != "" && 
					activity != null && activity != "" && 
					zipCode != null && zipCode != ""
				){
					participation.set("gender", gender);
					participation.set("age", age);
					participation.set("activity", activity);
					participation.set("zipCode", zipCode);

					participation.save(
						null, {
							success: function(){
								++questionIndex;
								displayQuestion();						
							},
							error: function(a, b, c){
								console.log("error");
							}
						}
					);
				}else{
					// Display warning message.
					$(".question-container .alert").show();
				}
			}else{
				var oldQuestion = pollQuestionsList.toJSON()[questionIndex];
				
				// Deleting old answers.
				for(var i = 0; i < questionAnswerList.length; i++){
					var questionAnswer = questionAnswerList.models[i];
					
					questionAnswer.pollId = participation.pollId;
					questionAnswer.questionId = oldQuestion.id;
					
					questionAnswer.destroy();
				}
				
				// Reteiving the answers.
				var selectedAnswersInput = $("input[name='answer']:checked");
				selectedAnswersInput.each(function(index, element){
					var questionAnswer = new QuestionAnswer();
					questionAnswer.pollId = participation.pollId;
					questionAnswer.questionId = oldQuestion.id;
					

					questionAnswer.set("question", {id: oldQuestion.id});
					questionAnswer.set("questionOption", {id :parseInt($(this).attr("data-id"))}); 
					
					var textInput = $(".option." + $(this).attr("data-id") + " input[type='text']");
					if(textInput.size() > 0){
						questionAnswer.set("content", $.trim(textInput.val()));
					}
					
					// Saving the answer.
					questionAnswer.save();
				});
				
				if(selectedAnswersInput.size() > 0){
					++questionIndex;
					
					// Displaying the new question;
					displayQuestion();
				}else{
					// Display warning message.
					$("fieldset .alert").show();
				}
			}
		}
	};

	var displayPreviousQuestion = function(){
		if(questionIndex > -1){
			--questionIndex;
			displayQuestion();
		}
	};
	
	var displayQuestion = function(){
		$("fieldset .alert").hide();
		
		if(questionIndex == -1){
			// Display participation form.
			displayParticipationForm();
			
			$("button.previous").hide();
			$("button.next").show();
			$("button.finish").hide();
			
			$(".questionList-container li").removeClass("active");
			$(".identification").addClass("active");
		}else if(questionIndex == pollQuestionsList.length){
			// Display confirmation.
			$(".questions").hide();
			
			$(".confirmation").show();
		}else{
			var question = pollQuestionsList.toJSON()[questionIndex];
			
			// Display current question.
			new QuestionView({model: question});
			$(".identification").removeClass("active");
						
			// Retreiving answered questions and displaying them.
			questionAnswerList = new QuestionAnswersList();
			questionAnswerList.pollId = participation.pollId;
			questionAnswerList.questionId = question.id;
			questionAnswerList.fetch({
				success: function(){
					for(var element in questionAnswerList.toJSON()){
						var answer = questionAnswerList.toJSON()[element];
						$("input.questionOption." + answer.questionOption.index).prop("checked", true);
						
						if(answer.content != null && answer.content != ""){
							$(".option." + answer.questionOption.id + " input[type='text']").val(answer.content);
						}
						
					}
				}
			});
			
			$("button.previous").show();
			if(questionIndex == pollQuestionsList.toJSON().length -1){
				$("button.next").hide();
				$("button.finish").show();
			}else{
				$("button.next").show();
				$("button.finish").hide();
			}
		}
	};
</script>

<div class="container-fluid">
	<div class="row-fluid questions">
		<div class="span2">
			<div class="well sidebar-nav">
				<ul class="nav nav-list questionList-container">
					
				</ul>
			</div>
		</div>
		<div class="span10 ">
	    	<div class="question-container"></div>
	    	<hr/>
	    	<button type="button" class="btn btn-primary pull-left previous hide">Précédent</button>
			<button type="button" class="btn btn-primary pull-right next">Suivant</button>
			<button type="button" class="btn btn-primary pull-right finish">Terminer</button>
		</div>
	</div>
	
	<div class="hide confirmation text-center">
		<h2 class="text-success">Questionnaire complété !</h2>
		<p class="text-success">Merci pour votre participation.</p>
	</div>
</div>

<script type="text/template" id="participation-template">
	<h2>La Dimension Fantastique</h2>

	<div class="alert alert-info">
		Ce questionnaire est une étude de marché pour la création d'une librairie spécialisée dans le centre de Paris. 
		Si vous habitez dans le bassin parisien, votre avis nous intéresse, 
		et peut-être ce projet vous intéressera-t-il aussi.
	</div>

	<div class="alert hide">
  		<strong>Attention!</strong> Merci de compléter vos informations.
	</div>



	<fieldset>
		<legend>Sexe</legend>
		<div>
			<input type="radio" name="gender" value="male" 
				<@	if(participation.gender == "male"){	@>
					checked
				<@	} @>
			/>
			<span>Homme</span>
		</div>
		<div>
			<input type="radio" name="gender" value="female" 
				<@	if(participation.gender == "female"){	@>
					checked
				<@	} @>
			/>
			<span>Femme</span>
		</div>
	</fieldset>
	<fieldset>
		<legend>Age</legend>
		<input type="text" class="age" size="3" value="<@= participation.age @>">
	</fieldset>

	<fieldset>
		<legend>Activité professionelle</legend>
		<select class="activity">
			<option value="Agriculteurs exploitants">Agriculteurs exploitants</option>
			<option value="Artisans, commerçants et chefs d'entreprise">Artisans, commerçants et chefs d'entreprise</option>
			<option value="Cadres et professions intellectuelles supérieures">Cadres et professions intellectuelles supérieures</option>
			<option value="Professions Intermédiaires">Professions Intermédiaires</option>
			<option value="Employés">Employés</option>
			<option value="Ouvriers">Ouvriers</option>
			<option value="Retraités">Retraités</option>
			<option value="Autres personnes sans activité professionnelle">Autres personnes sans activité professionnelle</option>
		</select>
	</fieldset>

	<fieldset>
		<legend>Code postal</legend>
		<input type="text" class="zipCode" size="5" value="<@= participation.zipCode @>">
	</fieldset>
</script>

<script type="text/template" id="questionList-template">
	<li class="nav-header">Questions</li>
	<li class="active identification"><a>Identification</a></li>
	<@ _.each(questions, function(question) { @>
		<li class="<@= question.index @>"><a>Question <@= question.index @></a</li>
	<@}); @>
</script>

<script type="text/template" id="question-template">
	<h2>Question <@= question.index @></h2>
	<fieldset>
		<legend><@= question.label @></legend>
		<div class="alert hide">
  			<strong>Attention!</strong> Merci de sélectionner au moins un choix de réponse.
		</div>
		<@	var inputType = "checkbox"; @>
		<@	if(question.type == "OPTIONS"){
				inputType = "radio";
			}else{
		 @>
			<div class="multipleChoices label label-info">Plusieurs réponses possibles</div></br></br>
		<@	}	
	
		_.each(question.questionOptions, function(questionOption) { @>
				<div class="option <@= questionOption.id @>">
					<input 
						name="answer" 
						type="<@= inputType @>" 
						class="questionOption <@=questionOption.index @>" 
						value="<@= questionOption.label @>"
						data-id="<@=questionOption.id @>"
						data-type="<@= questionOption.type @>"
					/>
					<span><@= questionOption.label @></span>
					<@	if(questionOption.type == "OPEN"){	@>
						<input type="text" class="input-large" />
					<@	}	@>
				</div>
		<@	}); @>
	</fieldset>
</script>
