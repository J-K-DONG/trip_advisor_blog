package com.advisor.trip.entity.city;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advisor.trip.util.DB.DBconn;
import com.mysql.cj.xdevapi.Result;

/**
 * @author 董晋坤
 * TODO 对city的基本操作方法
 */
public class CityDao {

	/**发布游记时进行city表的记录的增加（插入一条新的记录或者更新记录的number）
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
			
			if(rs.next()) {//已存在该城市的记录，只将所属游记的数量加一
				id = rs.getInt("id");			
				number = rs.getInt("number");
				number = number + 1;
				sql_update = "update city set number='" + number + "' where id=" + id;
			
			}else {//不存在该城市的记录，插入该城市的记录 并设置所属游记数量为1.
				sql_update = "insert into city (name, number) value('" + city + "', '" + 1 +"')";
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
	
	
	
	/**删除游记的时候对city表的记录进行删减
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
			}
			
		} catch (SQLException e) {
			System.out.println("修改游记数量SQL语句异常");
			e.printStackTrace();
		}finally {
			DBconn.closeConn();	
		}
				
		return flag;
	}

	
	/** 搜索出所有的city
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


