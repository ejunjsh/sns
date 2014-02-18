package com.sky.sns.mongo.repository;

import org.bson.types.ObjectId;

import com.sky.sns.mongo.entity.Question;
import com.sky.sns.mongo.iRepository.IQuestionRepository;

public class QuestionRepository extends BaseRepository<Question,ObjectId> implements IQuestionRepository {

   

}
