package com.advisor.trip.util.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.advisor.trip.entity.blog.Blog;

/**
 * 2018年7月25日上午10:23:15
 * @author JK_DONG
 * TODO
 */
public class Sort {

	public static List<Blog> bubbleSort(List<Blog> list){
		
//		List<Blog> list_sort = new ArrayList<Blog>();
		
	
		Collections.sort(list, new Comparator<Blog>() {
			@Override
			public int compare(Blog o1, Blog o2) {
				Blog blog_1=(Blog)o1;
				Blog blog_2=(Blog)o2;
				if(blog_1.getPageview()<blog_2.getPageview()){
					return 1;
				}else if(blog_1.getPageview()==blog_2.getPageview()){
					return 0;
				}else{
					return -1;
				}
			}
		
		});
		
		
		
		return list;
		
	}
	
	
	
	
}
