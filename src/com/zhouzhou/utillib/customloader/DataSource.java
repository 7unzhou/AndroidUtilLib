/**
 * 
 */
package com.zhouzhou.utillib.customloader;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author : zhoujunzhou
 * @param <T>
 * @date : 2013-8-30
 * @Description : 
 */
public abstract class DataSource<T> {
	protected SQLiteDatabase mDatabase;
	public DataSource(SQLiteDatabase database) {
		mDatabase = database;
	}
	public abstract boolean insert(T entity);
	public abstract boolean delete(T entity);
	public abstract boolean update(T entity);
	public abstract List read();
	public abstract List read(String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy);
}
