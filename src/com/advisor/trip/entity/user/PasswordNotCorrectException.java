package com.advisor.trip.entity.user;

/**
 * @author 董晋坤
 *密码不正确异常  符合遇到异常时抛出
 */
public class PasswordNotCorrectException extends RuntimeException {

	public PasswordNotCorrectException(String message) {
		super(message);
	}

}
