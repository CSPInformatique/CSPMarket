SELECT count(participation.id) FROM PARTICIPATION where participation.id in (
	select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = 19
);

SELECT 
	question.label,
	questionOption.label,
	count(questionAnswer.id) / participantCount.count * 100
FROM
	questionAnswer,
	question,
	questionOption,
	(SELECT participation.id as participantId FROM PARTICIPATION where participation.id in (
		select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = 19
	)) participant,
	(SELECT count(participation.id) as count FROM PARTICIPATION where participation.id in (
		select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = 19
	)) participantCount
WHERE
	question.id = questionOption.questionId AND
	questionAnswer.questionId = question.id AND
	questionAnswer.questionOptionId = questionOption.id AND
	questionAnswer.participationId = participant.participantId
GROUP BY 
	question.label,
	questionOption.label;

SELECT table_schema                                        "DB Name", 
   Round(Sum(data_length + index_length) / 1024 / 1024, 1) "DB Size in MB" 
FROM   information_schema.tables 
GROUP  BY table_schema;