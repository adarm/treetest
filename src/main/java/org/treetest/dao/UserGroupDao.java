package org.treetest.dao;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.treetest.model.UserGroup;

@SuppressWarnings("restriction")
@Resource
public interface UserGroupDao extends JpaRepository<UserGroup, Long> {

}
