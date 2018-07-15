package com.advisor.trip.util.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * @author ������
 * �������ݿ�������  ʵ����Ŀ�����ݿ������
 */
public class DBcoon {
	static String url = "jdbc:mysql//localhost:3306/trip_advisor_blog?useUnicode=true&characterEncoding = utf-8 & autoReconnect=true & useSSL=false & serverTimezone=GTM";
	static String user = "trip_admin";
	static String password = "admin";
	static Connection conn = null; //����һ�����ӱ���
	static ResultSet rs = null; //����һ�����������
	static PreparedStatement ps = null;
	
	
	//���ݿ��ʼ����������
	public static void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ����������ʼ��ʧ��");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("���ݿ����������ʼ��ʧ��");
			e.printStackTrace();
		}
	}
	
	
	//��ɾ�ķ���
	public static int addUpdDel(String sql) {
		int i = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);//ִ��Ԥ����SQL���
			i = ps.executeUpdate();//���ݲ��� ִ��SQL��� ����ִ�к�ı��˼��м�¼
		} catch (SQLException e) {
			System.out.println("���ݿ���ɾ���쳣��");
			e.printStackTrace();
		}
		return i;
	}
	
	
	//�ر����ӷ���
	public static void closeConn(String sql) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQL���ݿ�ر��쳣��");
			e.printStackTrace();
		}
	}
	
	
	//SQL����ѯ���ݿⷽ��
	public static ResultSet selectSql(String sql) {
		try {
			ps = conn.prepareStatement(sql);//Ԥ����
			rs = ps.executeQuery(sql); //�洢��ѯ���
		} catch (SQLException e) {
			System.out.println("SQL���ݿ��ѯ�쳣��");
			e.printStackTrace();
		}
		return rs;
	}
}
