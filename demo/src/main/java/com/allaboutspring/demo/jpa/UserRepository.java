package com.allaboutspring.demo.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
}

/*
Once we extend CrudRepository we get some methods such as findById(), findAllById(), delete(), save()

An alternative way is to use @RepositoryDefinition and then we dont have to extend the CrudRepository

We get methods that follow the below convention
find(First[count])By[property expression][comparison operator][ordering operator]
*/