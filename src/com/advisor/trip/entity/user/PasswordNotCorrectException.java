package com.advisor.trip.entity.user;

/**
 * @author JK_DONG
 *���벻��ȷ�쳣  ���������쳣ʱ�׳�
 */
public class PasswordNotCorrectException extends RuntimeException {

	public PasswordNotCorrectException(String message) {
		super(message);
	}

}
