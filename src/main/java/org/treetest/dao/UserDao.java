package org.treetest.dao;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.treetest.model.User;

@SuppressWarnings("restriction")
@Resource
public interface UserDao extends JpaRepository<User, Long> {

}
