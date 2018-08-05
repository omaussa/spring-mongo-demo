package com.omaussa.mongodemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.omaussa.mongodemo.document.User;

public interface UserRepository extends MongoRepository<User, String> {
}
