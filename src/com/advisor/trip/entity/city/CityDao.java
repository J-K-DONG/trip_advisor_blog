package com.advisor.trip.entity.city;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advisor.trip.util.DB.DBconn;
import com.mysql.cj.xdevapi.Result;

/**
 * @author ������
 * TODO ��city�Ļ�����������
 */
public class CityDao {

	/**�����μ�ʱ����city��ļ�¼�����ӣ�����һ���µļ�¼���߸��¼�¼��number��
	 * @param city
	 */
	public static boolean addCity(String city) {
		
		int i;
		int id;
		int number;
		boolean flag = false;
		String sql_update;
		DBconn.init();
		try {
			String sql_search = "select * from city where name='" + city+ "'";
			ResultSet rs = DBconn.selectSql(sql_search);
			
			if(rs.next()) {//�Ѵ��ڸó��еļ�¼��ֻ�������μǵ�������һ
				id = rs.getInt("id");			
				number = rs.getInt("number");
				number = number + 1;
				sql_update = "update city set number='" + number + "' where id=" + id;
			
			}else {//�����ڸó��еļ�¼������ó��еļ�¼ �����������μ�����Ϊ1.
				sql_update = "insert into city (name, number) value('" + city + "', '" + 1 +"')";
			}
			
			i = DBconn.addUpdDel(sql_update);
			if (i != 0) {
				flag = true;
				System.out.println("���³�����Ϣ�ɹ�");
			}else {
				System.out.println("���³�����Ϣʧ��");
			}
		} catch (SQLException e) {
			System.out.println("��ѯ����SQL����쳣");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		
		return flag;
	}
	
	
	
	/**ɾ���μǵ�ʱ���city��ļ�¼����ɾ��
	 * @param city
	 */
	public static Boolean delete(int id) {
		
		boolean flag = false;
		int i;
		int number;
		String sql_update;
		DBconn.init();
		
		try {
			String sql_select = "select * from city where id=" + id;
			ResultSet rs = DBconn.selectSql(sql_select);
			if (rs.next()) {
				
				id = rs.getInt("id");
				number = rs.getInt("number");
				if (number > 1) {//�ó��е��μ���������һ
					number = number - 1;
					sql_update = "update city set number='" + number + "' where id=" + id ;
					
				} else {//�ó���ֻ��һƪ�μ�ʱ  ɾ���ó��еļ�¼
					sql_update = "delete from city where id=" + id;
				}
				
				i = DBconn.addUpdDel(sql_update);
				if(i != 0) {
					flag = true;
					System.out.println("�����μ������޸ĳɹ���");
				}else {
					System.out.println("�����μ������޸�ʧ�ܣ�");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("�޸��μ�����SQL����쳣");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();	
		}
				
		return flag;
	}

	
	/** ���������е�city
	 * @return
	 */
	public static List<City> showAllCity() {
		
		List<City> list = new ArrayList<City>();
		DBconn.init();
		try {
			String sql = "select * from city";
			ResultSet rs = DBconn.selectSql(sql);
			while(rs.next()) {
				City city = new City();
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setNumber(rs.getInt("number"));
				list.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		
		return list;
	}
	
	
	
	public static City getOneCity(int id) {
		
		City city = new City();
		DBconn.init();
		try {
			String sql = "select * from city where id=" + id;
			ResultSet rs = DBconn.selectSql(sql);
			if (rs.next()) {
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setNumber(rs.getInt("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.closeConn();
		}
		
		return city;
		
	}
}


