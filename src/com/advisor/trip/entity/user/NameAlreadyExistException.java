package com.advisor.trip.entity.user;

/**
 * @author 董晋坤
 *用户名已存在异常
 */
public class NameAlreadyExistException extends RuntimeException {

	public NameAlreadyExistException(String message) {
		super(message);
	}
	
}
