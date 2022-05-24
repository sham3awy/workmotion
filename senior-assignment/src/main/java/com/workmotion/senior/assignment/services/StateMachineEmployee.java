package com.workmotion.senior.assignment.services;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.workmotion.senior.assignment.enums.StateEnum;
import com.workmotion.senior.assignment.models.orm.State;

class StateMachineEmployee {

	StateMachineConfig<StateEnum, StateEnum> config = stateMachineConfig();
	StateMachine<StateEnum, StateEnum> stateMachine;
	State currentState;
	
	public State changeState(State current, String next) {
		StateEnum nextState = StateEnum.valueOf(next);
		this.currentState = current;
		stateMachine = new StateMachine<>(current.getState(), config);
		if ((currentState.isSecurityCheckDone() && nextState.equals(StateEnum.WORK_PERMIT_CHECK_FINISHED)) || (currentState.isWorkPermitCheckDone() && nextState.equals(StateEnum.SECURITY_CHECK_FINISHED)) ) {
			stateMachine.fire(nextState);
			stateMachine.fire(StateEnum.APPROVED);
		}
		else {
			stateMachine.fire(nextState);
		}
		State state = new State(currentState.isSecurityCheckStarted(), currentState.isWorkPermitCheckStarted(), currentState.isWorkPermitPendingVerificationDone(), currentState.isSecurityCheckDone(), currentState.isWorkPermitCheckDone(), stateMachine.getState());
		return state;
	}

	public StateMachineConfig<StateEnum, StateEnum> stateMachineConfig() {
		StateMachineConfig<StateEnum, StateEnum> stateMachineConfig = new StateMachineConfig<>();

		stateMachineConfig.configure(StateEnum.ADDED).permit(StateEnum.IN_CHECK, StateEnum.IN_CHECK);

		stateMachineConfig.configure(StateEnum.IN_CHECK).permit(StateEnum.SECURITY_CHECK_STARTED, StateEnum.SECURITY_CHECK_STARTED);
		stateMachineConfig.configure(StateEnum.IN_CHECK).permit(StateEnum.WORK_PERMIT_CHECK_STARTED, StateEnum.WORK_PERMIT_CHECK_STARTED);

		stateMachineConfig.configure(StateEnum.SECURITY_CHECK_STARTED).substateOf(StateEnum.IN_CHECK).permit(StateEnum.SECURITY_CHECK_FINISHED, StateEnum.SECURITY_CHECK_FINISHED);

		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_STARTED).substateOf(StateEnum.IN_CHECK).permit(StateEnum.SECURITY_CHECK_FINISHED, StateEnum.SECURITY_CHECK_FINISHED);
		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_STARTED).substateOf(StateEnum.IN_CHECK).permit(StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION, StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION);

		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION).permit(StateEnum.WORK_PERMIT_CHECK_FINISHED, StateEnum.WORK_PERMIT_CHECK_FINISHED);
		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION).permit(StateEnum.SECURITY_CHECK_FINISHED, StateEnum.SECURITY_CHECK_FINISHED);

		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_FINISHED).permit(StateEnum.SECURITY_CHECK_FINISHED, StateEnum.SECURITY_CHECK_FINISHED);

		stateMachineConfig.configure(StateEnum.SECURITY_CHECK_FINISHED).permit(StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION, StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION);
		stateMachineConfig.configure(StateEnum.SECURITY_CHECK_FINISHED).permitIf(StateEnum.WORK_PERMIT_CHECK_FINISHED, StateEnum.WORK_PERMIT_CHECK_FINISHED, () -> currentState.isWorkPermitPendingVerificationDone());

		stateMachineConfig.configure(StateEnum.SECURITY_CHECK_FINISHED).permitIf(StateEnum.APPROVED, StateEnum.APPROVED, () -> currentState.isSecurityCheckDone() && currentState.isWorkPermitCheckDone());

		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_FINISHED).permitIf(StateEnum.APPROVED, StateEnum.APPROVED, () -> currentState.isSecurityCheckDone() && currentState.isWorkPermitCheckDone());

		stateMachineConfig.configure(StateEnum.APPROVED).permit(StateEnum.ACTIVE, StateEnum.ACTIVE);

		stateMachineConfig.configure(StateEnum.IN_CHECK).onEntry(() -> {
			stateMachine.fire(StateEnum.SECURITY_CHECK_STARTED);
			stateMachine.fire(StateEnum.WORK_PERMIT_CHECK_STARTED);
		});

		stateMachineConfig.configure(StateEnum.SECURITY_CHECK_STARTED).onEntry(() -> {
			currentState.setSecurityCheckStarted(true);
		});

		stateMachineConfig.configure(StateEnum.SECURITY_CHECK_FINISHED).onEntry(() -> {
			currentState.setSecurityCheckDone(true);
		});

		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_STARTED).onEntry(() -> {
			currentState.setWorkPermitCheckStarted(true);
		});

		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION).onEntry(() -> {
			currentState.setWorkPermitPendingVerificationDone(true);
		});

		stateMachineConfig.configure(StateEnum.WORK_PERMIT_CHECK_FINISHED).onEntry(() -> {
			currentState.setWorkPermitCheckDone(true);
		});

		return stateMachineConfig;
	}
}