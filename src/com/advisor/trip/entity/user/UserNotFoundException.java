package com.advisor.trip.entity.user;

/**
 * @author ������
 * �����û��Ҳ������쳣������
 */
public class UserNotFoundException extends RuntimeException {

	//���ع��췽��
	public UserNotFoundException(String message) {
		super(message);
	}
}
