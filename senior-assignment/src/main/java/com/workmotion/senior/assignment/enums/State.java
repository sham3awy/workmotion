package com.workmotion.senior.assignment.enums;

public enum State {
	ADDED("ADDED"), 
	IN_CHECK("IN-CHECK"), 
	APPROVED("APPROVED"), 
	ACTIVE("ACTIVE"),
	SECURITY_CHECK_STARTED("SECURITY_CHECK_STARTED"), 
	SECURITY_CHECK_FINISHED("SECURITY_CHECK_FINISHED"),
	WORK_PERMIT_CHECK_STARTED("WORK_PERMIT_CHECK_STARTED"),
	WORK_PERMIT_CHECK_PENDING_VERIFICATION("WORK_PERMIT_CHECK_PENDING_VERIFICATION"),
	WORK_PERMIT_CHECK_FINISHED("WORK_PERMIT_CHECK_FINISHED");

	public String state;

	private State(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}
