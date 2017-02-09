package com.epam.catalog.db;

import java.sql.SQLException;


public class FillDB {
	public static final String NAME_DB = "catalog";
	DBWork db = new DBWork();
	public void create(String url, String user, String password) throws SQLException {
		
		db.update("create database " + NAME_DB);
		db.update("use " + NAME_DB);

		db.update("create table books(id INT(2) ZEROFILL PRIMARY KEY AUTO_INCREMENT,"
				+ "author VARCHAR(100),"
				+ "name VARCHAR(100) NOT NULL UNIQUE,"
				+ "pages INT(4) NOT NULL,"
				+ "price FLOAT(5) ENGINE=INNODB");
		
		db.update("INSERT INTO books (`author`, `name`, `pages`, `price`)  VALUES()");
		}
		/*DaoPlanes dp = new DaoPlanes(db);
		dp.insert(new Planes(300, 6000));
	//	System.out.println(String.valueOf(new Planes(1, 45, 5800)));
		dp.insert(new Planes(100, 2500));
		dp.insert(new Planes(150, 1000));
		dp.insert(new Planes(120, 600));
		dp.insert(new Planes(50, 1000));
		dp.insert(new Planes(350, 10000));

		db.update("create table users(user_id INT(2) ZEROFILL PRIMARY KEY AUTO_INCREMENT,"
				+ "name VARCHAR(255) NOT NULL UNIQUE,"
				+ "pass varchar(20) NOT NULL,"
				+ "role ENUM('admin','pilot','stuart','navigator','radio','null'),"
				+ "block_st INT(1)," + "delete_st INT(1)) ENGINE=INNODB");
		DaoUsers du = new DaoUsers(db);
		du.insert(new Users("Belov Yuriy", "111111", "pilot", 0, 0));
		du.insert(new Users("Ivanov Vasiliy", "222222", "stuart", 0, 0));
		du.insert(new Users("Panina Janna", "aaaaaa", "stuart", 0, 0));
		du.insert(new Users("Sidorov Aleks", "000000", "admin", 0, 0));
		du.insert(new Users("Vinni the Pooh", "333333", "pilot", 0, 0));
		du.insert(new Users("Kupala John", "hhhhhh", "radio", 0, 0));
		du.insert(new Users("Mikki Maus", "888888", "pilot", 0, 0));
		du.insert(new Users("Usmanov Mihail", "255555", "navigator", 0, 0));
		du.insert(new Users("Kot Leopold", "mouse", "stuart", 0, 0));
		du.insert(new Users("Strigenov Petr", "qqqqqq", "pilot", 0, 0));
		du.insert(new Users("Kotov Vasya", "999999", "pilot", 0, 0));
		du.insert(new Users("Sherlock Holms", "233444", "navigator", 0, 0));
		du.insert(new Users("Doctor Watson", "rrrrrr", "pilot", 0, 0));
		du.insert(new Users("Bilov Dmitriy", "444gkk", "radio", 0, 0));
		du.insert(new Users("Koshkin Aleks", "355656", "stuart", 0, 0));
		du.insert(new Users("vlad", "000000", "admin", 0, 0));
		db.update("create table crews(crew_id INT(1) PRIMARY KEY ,pilot INT(2) ZEROFILL,"
				+ "stuart INT(2) ZEROFILL,navi INT(2) ZEROFILL,radio INT(2) ZEROFILL,"
				+ "FOREIGN KEY (stuart) REFERENCES users(user_id)ON DELETE CASCADE ON UPDATE RESTRICT,"
				+ "FOREIGN KEY (navi) REFERENCES users(user_id)ON DELETE CASCADE ON UPDATE RESTRICT,"
				+ "FOREIGN KEY (radio) REFERENCES users(user_id)ON DELETE CASCADE ON UPDATE RESTRICT,"
				+ "FOREIGN KEY (pilot) REFERENCES users(user_id)ON DELETE CASCADE ON UPDATE RESTRICT) ENGINE=INNODB");
		db.update("insert into crews values(1,1,2,8,6)");
		db.update("insert into crews values(2,5,3,12,14)");

		db.update("insert into crews values(3,15,13,8,6)");

		db.update("create table flights(flight_id INT(3) ZEROFILL PRIMARY KEY AUTO_INCREMENT,"
				+ "start_from VARCHAR(25),"
				+ "start_to VARCHAR(25),"
				+ "dist INT(5),"
				+ "block_st INT(1),"
				+ "date VARCHAR(25),"
				+ "plane_id INT(2) ZEROFILL,"
				+ "crew_id INT(1),"
				+ "note VARCHAR(50),"
				+ "INDEX par_ind (plane_id),"
				+ "INDEX par_ind2 (crew_id),"
				+ "FOREIGN KEY (crew_id) REFERENCES crews(crew_id) ON DELETE CASCADE ON UPDATE RESTRICT,"
				+ "FOREIGN KEY (plane_id) REFERENCES planes(plane_id) ON DELETE CASCADE ON UPDATE RESTRICT) ENGINE=INNODB");
		DaoFlights df = new DaoFlights(db);

		df.insert(new Flights("Minsk", "Moscow", 700, 1, "2016-01-08 18:40:00",
				1, 2, " "));
		df.insert(new Flights("Moscow", "Minsk", 700, 1, "2016-01-02 03:40:00",
				1, 2, " "));

		df.insert(new Flights("Minsk", "Amsterdam", 1200, 1,
				"2016-01-08 19:30:00", 3, 2, " "));
		df.insert(new Flights("Amsterdam", "Minsk", 1200, 1,
				"2016-01-09 02:40:00", 3, 2, " "));
		df.insert(new Flights("Minsk", "Oslo", 2100, 1, "2016-01-09 09:10:00",
				2, 1, " "));
		df.insert(new Flights("Oslo", "Minsk", 2100, 1, "2016-01-09 18:10:00",
				2, 1, " "));
		df.insert(new Flights("Minsk", "Berlin", 600, 1, "2016-01-07 10:20:00",
				5, 3, " "));
		df.insert(new Flights("Berlin", "Minsk", 600, 1, "2016-01-07 19:10:00",
				5, 3, " "));
		df.insert(new Flights("Minsk", "Shanghai", 6700, 1,
				"2016-01-10 07:05:00", 6, 2, " "));
		df.insert(new Flights("Shanghai", "Minsk", 6700, 1,
				"2016-01-11 07:15:00", 6, 2, " "));
		df.insert(new Flights("Minsk", "Praha", 2000, 1, "2016-01-11 02:40:00",
				4, 2, " "));
		df.insert(new Flights("Praha", "Minsk", 2000, 1, "2016-01-11 15:20:00",
				4, 2, " "));
		df.insert(new Flights("Minsk", "Buharest", 2500, 1,
				"2016-01-12 18:10:00", 2, 1, " "));
		df.insert(new Flights("Buharest", "Minsk", 2500, 1,
				"2016-01-13 01:10:00", 2, 1, " "));
		df.insert(new Flights("Minsk", "Novosibirsk", 3500, 1,
				"2016-01-12 01:30:00", 6, 3, " "));
		df.insert(new Flights("Novosibirsk", "Minsk", 3500, 1,
				"2016-01-12 16:30:00", 6, 3, " "));

		System.out.println();

		db.showResultSet(db.query("select * from users"));
		System.out.println();
		db.showDatabaseMetaData();
		db.showResultSet(db.query("select * from crews"));
		System.out.println();
		db.showResultSet(db.query("select * from planes"));
		System.out.println();
		db.showResultSet(db.query("select * from flights"));
		db.close();
	}*/

	public  void delete(String url, String user, String password) throws SQLException {
		//DB db = new DB(url, user, password);
		db.update("drop database " + NAME_DB);
		db.close();

	}
}
