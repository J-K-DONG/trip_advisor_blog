package com.advisor.trip.entity.user;

/**
 * @author JK_DONG
 * 定义用户找不到的异常处理类
 */
public class UserNotFoundException extends RuntimeException {

	//重载构造方法
	public UserNotFoundException(String message) {
		super(message);
	}
}
