package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import view.app;

public class DB {
	public static final String Connection = "jdbc:sqlserver://LOC:1433;databaseName=topFlap;user=sa;password=loc11012004;encrypt=true;trustServerCertificate=true";
	public static final String USER = "sa";
	public static final String PASSWORD = "loc11012004";
	private ArrayList<Person> person = new ArrayList<Person>();
	private String currentName;
	private Integer currentscores=0;

	public DB(Person ps) {
		super();
		this.currentName = ps.getTen();
		this.currentscores = ps.getDiem();
	}
	

	

	public ArrayList<Person> getPerson() {
		return person;
	}

	public DB() {
	}

	public ArrayList<Person> connectDB() {
		Connection conn = null;
		Statement stmt = null;

		// TODO Auto-generated method stub
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(Connection);
			stmt = conn.createStatement();
			String query = "SELECT * FROM topflappybird order by diem desc";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String Ten = rs.getString("TenNC");
				int Diem = rs.getInt("diem");
//                System.err.println(Ten+","+Diem);
				Person ps = new Person(Ten, Diem);
				person.add(ps);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		System.out.println("succesfuly");
		return person;
	}
    public void addDB() {
    	Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(Connection);
			stmt = conn.createStatement();
//			String query = "insert into topflappybird (TenNC,diem) values ('ao vl',114);";
			String query = "insert into topflappybird (TenNC,diem) values ('"+currentName+"',"+currentscores+");";
		        int i = stmt.executeUpdate(query);
		        if (i > 0) {
		            System.out.println("ROW INSERTED");
		        } else {
		            System.out.println("ROW NOT INSERTED");
		        }

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		deleteDB();
    }
    public void deleteDB() {
    	Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(Connection);
			stmt = conn.createStatement();
//			String query = "insert into topflappybird (TenNC,diem) values ('gamews',34);";
			String query = "delete from topflappybird where TenNC in ( select TenNC from topflappybird where diem = (select min(diem) from topflappybird ));";
			int i = stmt.executeUpdate(query);
	        if (i > 0) {
	            System.out.println("ROW INSERTED");
	        } else {
	            System.out.println("ROW NOT INSERTED");
	        }

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public boolean checkScore() {
    	person = connectDB();
    	for (Person ps : person) {
    		if(currentscores>ps.getDiem()) {
        		return true;
        	}
		}
    	return false;
    }

	public boolean checkPerson() {
		this.connectDB();
		for (Person ps : person) {
			if (currentName.equals(ps.getTen().trim())) {
				return false;
			}
		}
		return true;
	}
    public int checkaddPerson() {
    	int i=0;
    	for (Person ps : person) {
			if (ps.getDiem() >= currentscores) {
				i++;
			}
		}
    	return i;
    }
	public void print(ArrayList<Person> person) {
		for (Person ps : person) {
			System.out.println("name:" + ps.getTen() + " ,diem:" + ps.getDiem());

		}
	}
	public String getCurrentName() {
		return currentName;
	}


	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}


	public Integer getCurrentscores() {
		return currentscores;
	}


	public void setCurrentscores(Integer currentscores) {
		this.currentscores = currentscores;
	}
// public static void main(String[] args) {
//	DB db = new DB();
//	db.addDB();
////	db.deleteDB();
//}
}
