/**
 * 
 */
package com.zhouzhou.utillib.customloader;

/**
 * @author : zhoujunzhou
 * @date : 2013-8-30
 * @Description :
 */
public class Test {
	private int id;
	private String name;

	public Test() {
	}

	public Test(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
