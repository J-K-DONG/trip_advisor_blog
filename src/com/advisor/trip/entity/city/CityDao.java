package com.advisor.trip.entity.city;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.advisor.trip.util.DB.DBconn;

public class CityDao {

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
			
			if(rs != null) {//已存在该城市的记录，只将所属游记的数量加一
				id = rs.getInt("id");			
				number = rs.getInt("number");
				number = number + 1;
				sql_update = "update city set number='" + number + "' where id=" + id;
			
			}else {//不存在该城市的记录，插入该城市的记录 并设置所属游记数量为1.
				sql_update = "insert into table city (name, number) value('" + city + "', '" + 1 +"')";
			}
			i = DBconn.addUpdDel(sql_update);
			if (i != 0) {
				flag = true;
				System.out.println("更新城市信息成功");
			}else {
				System.out.println("更新城市信息失败");
			}
		} catch (SQLException e) {
			System.out.println("查询城市SQL语句异常");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();
		}
		
		return flag;
	}
	
	
	
	public static boolean delete(String city) {
		
		boolean flag = false;
		int i;
		int id;
		int number;
		String sql_update;
		DBconn.init();
		
		try {
			String sql_select = "select * from city where name='" + city + "'";
			ResultSet rs = DBconn.selectSql(sql_select);
			id = rs.getInt("id");
			number = rs.getInt("number");
			if (number > 1) {//该城市的游记数量大于一
				number = number - 1;
				sql_update = "update city set number='" + number + "' where id=" + id ;
				
			} else {//该城市只有一篇游记时  删除该城市的记录
				sql_update = "delete from city where id=" + id;
			}

			i = DBconn.addUpdDel(sql_update);
			if(i != 0) {
				flag = true;
				System.out.println("城市游记数量修改成功！");
			}else {
				System.out.println("城市游记数量修改失败！");
			}
		} catch (SQLException e) {
			System.out.println("修改游记数量SQL语句异常");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();	
		}
				
		return flag;
	}
		
}
