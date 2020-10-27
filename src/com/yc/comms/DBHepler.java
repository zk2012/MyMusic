package com.yc.comms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC
 * 源辰信息
 * @author navy
 * @2019年8月1日
 * 步骤：
 * 1、导包
 * 2、加载驱动
 * 3、建立连接
 * 4、创建预编译执行语句块
 * 5、给预编译执行语句块中的占位符赋值
 * 6、执行语句获取结果或结果集
 * 7、处理结果或结果集
 * 8、关闭资源
 */
public class DBHepler {
	
	private Connection con = null;
	private	PreparedStatement pstmt = null;
	private	ResultSet rs = null;
	
	static {
		try {
			Class.forName(ReadPro.getInstance().getProperty("drivername"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接的方法
	 * @return
	 */
	private Connection getConnection() {
		try {
			con = DriverManager.getConnection(ReadPro.getInstance().getProperty("url"), ReadPro.getInstance());
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 给预编译执行语句中的占位符赋值
	 * @param pstmt 要赋值的预编译对象
	 * @param params 对应占位符的值
	 */
	private void setParams(PreparedStatement pstmt, Object ... params) {
		// 说明预编译执行语句块中没有占位符?
		if (params == null || params.length <= 0) {
			return;
		}

		for (int i = 0, len = params.length; i < len; ++ i) {
			try {
				pstmt.setObject(i + 1, params[i]);
			} catch (SQLException e) {
				System.out.println("第 " + (i + 1) + " 个占位符注值失败...");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭资源的方法
	 * @param rs 结果集
	 * @param pstmt 预编译执行语句块
	 * @param con 连接
	 */
	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 执行数据更新的方法（update、delete、insert）
	 * @param sql 要执行的更新语句
	 * @param params 执行的更新语句中，对应占位符的值
	 * @return
	 */
	public int update(String sql, Object ... params) {
		int result = -1;	
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pstmt, con);
		}
		return result;
	}
	
	/**
	 * 事务处理   针对与数据库的‘多条’更新语句操作
	 * @param sqls   多条sql
	 * @param params  每一条sql语句都对应一个list   多个list存在params中
	 * @return
	 * @throws SQLException
	 */
	public int update(List<String> sqls,List<List<Object>> params) throws SQLException{
		int result = -1;
		int j=0;
		try {
			con = this.getConnection();
			//pstmt = con.prepareStatement(sql);
			//将提交方式设置为手动提交
			con.setAutoCommit(false);
			for(int i=0,len=sqls.size();i<len;i++) {
				j=i;
				pstmt = con.prepareStatement(sqls.get(i));
				this.setParams(pstmt, params.get(i)); //sql -> list
				result=pstmt.executeUpdate();
			}
			con.commit();   //事务提交
//			this.setParams(pstmt, params);
//			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(j);
			con.rollback();  //事务回滚
		} finally {
			con.setAutoCommit(true);    //恢复提交方式   ->  自动提交
			this.close(null, pstmt, con);
		}
		return result;
	}
	
	/**
	 * 获取结果集中列的列名
	 * @param rs
	 * @return
	 */
	private String[] getColumnNames(ResultSet rs) {
		String[] colNames = null;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取结果集中列的数量
			int colCount = rsmd.getColumnCount();
			
			// 数组的长度等于查询语句中所查列的数量
			colNames = new String[colCount];
			
			// 获取每一个列的名字，存到这个数组中
			for(int i = 1; i <= colCount; ++i) {
				colNames[i - 1] = rsmd.getColumnName(i).toLowerCase(); // oracle中默认返回的所有列的列名是大写的，那么我们就其全部转成小写
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colNames;
	}
	
	/**
	 * 查询一条数据的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, String> find(String sql, Object ... params) {

		Map<String, String> map = new HashMap<String, String>();
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			
			// 获取结果集中所有列的列名
			String[] colNames = this.getColumnNames(rs);
			if (rs.next()) {
				// 循环所有的列名，根据列名取出每一个列的值
				for (String colName : colNames) {
					// 将查询出来的结果，存到map中。以对应列的列名为键，对应列的值为值
					map.put(colName, rs.getString(colName));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return map;
	}
	
	/**
	 * 查询多条数据的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> finds(String sql, Object ... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			
			// 获取结果集中所有列的列名
			String[] colNames = this.getColumnNames(rs);
			Map<String, String> map = null;
			while (rs.next()) {
				// 每循环一次就是一条记录，一条记录的信息就存到一个map中
				map = new HashMap<String, String>();
				// 循环所有的列名，根据列名取出每一个列的值
				for (String colName : colNames) {
					// 将查询出来的结果，存到map中。以对应列的列名为键，对应列的值为值
					map.put(colName, rs.getString(colName));
				}
				// 当循环结束，说明这一样的数据已经读取完成，那么就存到了这一行数据的map存到list中
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return list;
	}
	
	/**
	 * 获取总记录数的方法    新增方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getTotal(String sql, Object...params) {
	
		int result = 0;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return result;
	}
	
	/**
	 * 获取总记录数的方法    新增方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getTotal(String sql, List<Object> params) {
	
		int result = 0;
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(rs, pstmt, con);
		}
		return result;
	}
	
	
	
	/**
	 * 以对象的方式返回数据
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param c
	 * @return
	 * 
	 */
	public <T>List<T> findMutiple(String sql,List<Object>params,Class c){
		
		List<T> list=new ArrayList<T>();
		
		System.out.println(sql);
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			setParams(pstmt,params);  //参数注入
			rs=pstmt.executeQuery();
			
			//获取所有的列名
			String [] columnNames = this.getColumnNames(rs);
			
			T t=null; //声明一个对象
			Object obj=null; //对应列的值
			String typeName=null; //对应列值的类型
			//通知反射获取所有的methods
			Method[] methods = c.getDeclaredMethods();
			
			//处理结果集
			while(rs.next()) {
				//创建一个对象
				try {
					t=(T)c.newInstance(); //调用无参数的构造方法
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//循环列
				for( String columnName : columnNames ) {
					 
					//获取对应列的值
					obj = rs.getObject(columnName);
					
					//循环所有的方法
					for(Method m : methods) {
						//是否有对应的setxx 方法名  set+columnName
						String name="set"+columnName;
						//判断方法名是否一致
						if(name.equalsIgnoreCase(m.getName())) {
							
							if(null == obj) {
								continue;
							}
							
							//获取对应值的类型
							typeName = obj.getClass().getName();
							
							//判断数据类型
							if("java.math.BigDecimal".equals(typeName)) {
								try {
									m.invoke(t, rs.getInt(columnName));
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else if("java.lang.Integer".equals(typeName)) {
								try {
									m.invoke(t, rs.getInt(columnName));
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else if("java.lang.Double".equals(typeName)) {
								try {
									m.invoke(t, rs.getDouble(columnName));
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else if("java.lang.String".equals(typeName)) {
								try {
									m.invoke(t, rs.getString(columnName));
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else if("java.lang.Date".equals(typeName)) {
								try {
									m.invoke(t, rs.getString(columnName));
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else if("oracle.sql.CLOB".equals(typeName)) {
								
									
									try {
										Reader in= rs.getCharacterStream(columnName);
										BufferedReader br= new BufferedReader(in);
										StringBuffer sb=new StringBuffer();
										String str=br.readLine();
										
										while(null != str) {
											sb.append(str);
											str=br.readLine();
										}
										try {
											m.invoke(t, sb.toString());
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IllegalArgumentException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} //每次读取一行数据
									
								
							}else {
								//TODO 后期自行扩展
							}
							
						}
					}
				}
				
				
				list.add(t);
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			this.close(rs, pstmt, con);
		}	
		
		return list;
		
	}
	
	/**
	 * 给预编译执行语句的占位符赋值
	 * 
	 * 
	 */
	private void setParams(PreparedStatement pstmt,List<Object> params) {
		
		//说明预编译执行语句块中没有占位符？
		if(params == null || params.size() <= 0) {
			return;
		}
		
		for(int i=0,len=params.size() ;i<len;i++) {
			try {
				pstmt.setObject(i+1,params.get(i));
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				System.out.println("第"+(i+1)+"个占位符注册失败...");
				e.printStackTrace();
			}
		}
		
	}
	
	
}
