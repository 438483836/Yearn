package com.yearn.life.dao;

import com.yearn.life.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Vincent on 2018-10-15
 */
@Repository
@Transactional
public interface PersonDAO extends JpaRepository<Person, Integer> {

    @Modifying
    @Query(value = "insert into t_person(name,sex,age) values(?1,?2,?3)",nativeQuery = true)
    int insertPerson(String name, String sex, int age);

}
