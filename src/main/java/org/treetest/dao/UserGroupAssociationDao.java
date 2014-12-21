package org.treetest.dao;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.treetest.model.UserGroupAssociation;

@SuppressWarnings("restriction")
@Resource
public interface UserGroupAssociationDao extends
		JpaRepository<UserGroupAssociation, Long> {

}
