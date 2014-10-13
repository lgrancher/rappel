package com.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long>
{
	@Query("select user from User user where user.name like :name")
	public User findByName(@Param("name") String name);
}
