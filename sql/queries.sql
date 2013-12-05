SELECT 
	count(participation.id) 
FROM 
	PARTICIPATION 
where 
	participation.id in (
		select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = (SELECT MAX(`index`) FROM question)
	) AND 
	SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60');

SELECT 
	question.label,
	questionOption.label,
	count(questionAnswer.id) / participantCount.count * 100
FROM
	questionAnswer,
	question,
	questionOption,
	participation,
	(SELECT participation.id as participantId FROM PARTICIPATION where participation.id in (
		select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = (SELECT MAX(`index`) FROM question)
	)) participant,
	(	SELECT 
			count(participation.id) as count 
		FROM 
			PARTICIPATION 
		where 
			participation.id in (
				select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = (SELECT MAX(`index`) FROM question)
			) AND 
			SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
	) participantCount 
WHERE
	question.id = questionOption.questionId AND
	questionAnswer.questionId = question.id AND
	questionAnswer.questionOptionId = questionOption.id AND
	questionAnswer.participationId = participant.participantId AND 
	participantId = participation.id AND
	SUBSTRING(participation.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
GROUP BY 
	question.label,
	questionOption.label;

SELECT 
	gender,
	count(PARTICIPATION.gender) / participantCount.count * 100
FROM 
	PARTICIPATION,
	(	SELECT 
			count(participation.id) as count 
		FROM 
			PARTICIPATION 
		where 
			participation.id in (
				select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = (SELECT MAX(`index`) FROM question)
			) AND 
			SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
	) participantCount 
	where 
	participation.id in (
		select 
			participationId 
		from 	
			questionAnswer, 
			question 
		WHERE 
			question.id = questionAnswer.questionId and 
			question.`index` = (SELECT MAX(`index`) FROM question)
	) AND 
	SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
GROUP BY
	gender;

SELECT 
	age,
	count(PARTICIPATION.age) / participantCount.count * 100
FROM 
	PARTICIPATION,
	(	SELECT 
			count(participation.id) as count 
		FROM 
			PARTICIPATION 
		where 
			participation.id in (
				select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = (SELECT MAX(`index`) FROM question)
			) AND 
			SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
	) participantCount 
	where 
	participation.id in (
		select 
			participationId 
		from 	
			questionAnswer, 
			question 
		WHERE 
			question.id = questionAnswer.questionId and 
			question.`index` = (SELECT MAX(`index`) FROM question)
	) AND 
	SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
GROUP BY
	age;

SELECT 
	activity,
	count(PARTICIPATION.activity) / participantCount.count * 100
FROM 
	PARTICIPATION,
	(	SELECT 
			count(participation.id) as count 
		FROM 
			PARTICIPATION 
		where 
			participation.id in (
				select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = (SELECT MAX(`index`) FROM question)
			) AND 
			SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
	) participantCount 
	where 
	participation.id in (
		select 
			participationId 
		from 	
			questionAnswer, 
			question 
		WHERE 
			question.id = questionAnswer.questionId and 
			question.`index` = (SELECT MAX(`index`) FROM question)
	) AND 
	SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
GROUP BY
	activity;

SELECT 
	zipCode,
	count(PARTICIPATION.zipCode) / participantCount.count * 100
FROM 
	PARTICIPATION,
	(	SELECT 
			count(participation.id) as count 
		FROM 
			PARTICIPATION 
		where 
			participation.id in (
				select participationId from questionAnswer, question WHERE question.id = questionAnswer.questionId and question.`index` = (SELECT MAX(`index`) FROM question)
			) AND 
			SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60')
	) participantCount 
	where 
	participation.id in (
		select 
			participationId 
		from 
			questionAnswer, 
			question 
		WHERE 
			question.id = questionAnswer.questionId and 
			question.`index` = (SELECT MAX(`index`) FROM question) 
	) AND 
	SUBSTRING(PARTICIPATION.zipCode, 1, 2) IN ('75', '77','78','91','92','94','95', '27', '45', '60') 
GROUP BY 
	zipCode;

SELECT content FROM questionanswer where content is not null;


SELECT table_schema                                        "DB Name", 
   Round(Sum(data_length + index_length) / 1024 / 1024, 1) "DB Size in MB" 
FROM   information_schema.tables 
GROUP  BY table_schema;