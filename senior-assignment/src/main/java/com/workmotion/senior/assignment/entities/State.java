package com.workmotion.senior.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.workmotion.senior.assignment.enums.StateEnum;

@Entity
@Table(name = "state")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean securityCheckStarted;
	private boolean workPermitCheckStarted;
	private boolean workPermitPendingVerificationDone;
	private boolean securityCheckDone;
	private boolean workPermitCheckDone;
	private StateEnum state;
    @OneToOne(mappedBy = "state")
	private Employee employee;
	
	public State() {
		this.securityCheckStarted = false;
		this.workPermitCheckStarted = false;
		this.workPermitPendingVerificationDone = false;
		this.securityCheckDone = false;
		this.workPermitCheckDone = false;
		this.state = StateEnum.ADDED;
	}

	public State(boolean securityCheckStarted, boolean workPermitCheckStarted,
			boolean workPermitPendingVerificationDone, boolean securityCheckDone, boolean workPermitCheckDone,
			StateEnum state) {
		this.securityCheckStarted = securityCheckStarted;
		this.workPermitCheckStarted = workPermitCheckStarted;
		this.workPermitPendingVerificationDone = workPermitPendingVerificationDone;
		this.securityCheckDone = securityCheckDone;
		this.workPermitCheckDone = workPermitCheckDone;
		this.state = state;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSecurityCheckStarted() {
		return securityCheckStarted;
	}

	public void setSecurityCheckStarted(boolean securityCheckStarted) {
		this.securityCheckStarted = securityCheckStarted;
	}

	public boolean isWorkPermitCheckStarted() {
		return workPermitCheckStarted;
	}

	public void setWorkPermitCheckStarted(boolean workPermitCheckStarted) {
		this.workPermitCheckStarted = workPermitCheckStarted;
	}

	public boolean isWorkPermitPendingVerificationDone() {
		return workPermitPendingVerificationDone;
	}

	public void setWorkPermitPendingVerificationDone(boolean workPermitPendingVerificationDone) {
		this.workPermitPendingVerificationDone = workPermitPendingVerificationDone;
	}

	public boolean isSecurityCheckDone() {
		return securityCheckDone;
	}

	public void setSecurityCheckDone(boolean securityCheckDone) {
		this.securityCheckDone = securityCheckDone;
	}

	public boolean isWorkPermitCheckDone() {
		return workPermitCheckDone;
	}

	public void setWorkPermitCheckDone(boolean workPermitCheckDone) {
		this.workPermitCheckDone = workPermitCheckDone;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}
}
