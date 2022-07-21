package com.allaboutspring.demo.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
}

/*
Once we extend CrudRepository we get some methods such as findById(), findAllById(), delete(), save()
*/