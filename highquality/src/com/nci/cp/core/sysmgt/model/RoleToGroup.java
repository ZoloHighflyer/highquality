package com.nci.cp.core.sysmgt.model;

import com.nci.cp.core.model.AbstractEntity;
/**
 * @target  the object is to storage roletogroup info.
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-9-11 
 */
public class RoleToGroup extends AbstractEntity {
    protected Role role;
    protected Group group;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}

    
}
