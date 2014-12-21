package org.treetest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_group_assoc")
public class UserGroupAssociation {
	@Id
	@GeneratedValue
	private long id;
	
	private long userId;
	
	private long groupId;

	public long getUserId() {
		return userId;
	}

	@Column
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Column
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
