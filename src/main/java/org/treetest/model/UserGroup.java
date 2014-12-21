package org.treetest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_group")
public class UserGroup extends Named {
	private long parentGroup;

	public long getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(long parentGroup) {
		this.parentGroup = parentGroup;
	}
}
