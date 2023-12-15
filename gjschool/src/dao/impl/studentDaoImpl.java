package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import dao.DbConnection;
import dao.studentDao;
import model.student;

public class studentDaoImpl implements studentDao {

	public static void main(String[] args) {
		//System.out.println(new studentDaoImpl().queryAll2());
		
		List<student> l=new studentDaoImpl().queryAll2();
		int sum=0;
		for(student o:l)
		{
			System.out.println("id:"+o.getId()+
					"\tname:"+o.getName()+
					"\tchi:"+o.getChi()+
					"\teng:"+o.getEng()+"\t總分:"+(o.getChi()+o.getEng()));
			
			sum=sum+o.getChi()+o.getEng();
		}
		
		System.out.println("合計:"+sum);
		
	}


	@Override
	public void add(student s) {
		Connection conn=DbConnection.getDb();
		String SQL="insert into student(name,chi,eng) values(?,?,?)";
		
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1,s.getName());
			ps.setInt(2, s.getChi());
			ps.setInt(3,s.getEng());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String queryAll1() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from student";
		String show="";
		
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				show=show+"id:"+rs.getInt("id")+
						"\t名:"+rs.getString("name")+
						"\t國文:"+rs.getInt("chi")+
						"\t英文:"+rs.getInt("eng")+"\n";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return show;
	}

	@Override
	public List<student> queryAll2() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from student";
		List<student> l=new ArrayList();
		
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				student s=new student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setChi(rs.getInt("chi"));
				s.setEng(rs.getInt("eng"));

				l.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return l;
	}

}
