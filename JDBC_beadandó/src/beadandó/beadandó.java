package beadand�;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class beadand� {
	private static final String URL="jdbc:oracle:thin:@193.6.5.58:1521:XE";
	
	public static void main(String[] args) {
		
try {Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			Connection conn = null;
			Scanner sc= new Scanner(System.in);
			System.out.println("K�rem a felhaszn�l� nevet:");
			String felhasz=sc.next();
			System.out.println("K�rem a jelsz�t:");
			String jelszo=sc.next();
			conn = DriverManager.getConnection(URL, felhasz, jelszo);
			
			int gomb;
		
			
			do {
				System.out.println("Nyomjon 1-est ha aut�t szeretne felvenni!");
				System.out.println("Nyomjon 2-est ha sof�rt szeretne felvenni!");
				System.out.println("Nyomjon 3-ast ha utas szeretne felvenni!");
				System.out.println("Nyomjon 4-est ha t�bl�kat szeretne l�trehozni!");
				System.out.println("Nyomjon 5-ast ha felszeretn� t�lteni a t�bl�kat!");
				System.out.println("Nyomjon 6-est ha az aut� t�bl�ban l�v� Reanult m�rk�j� aut�kat megszeretn� v�ltoztatni!");
				System.out.println("Nyomjon 7-�st ha az aut� t�bl�ban l�v� k�k sz�n� aut�kat t�r�lni akarja!");
				System.out.println("Nyomjon 8-ost ha ki akarja iratni az utasoknak a nev�t, email c�m�t �s azt a helyet ahova utazik!");
				System.out.println("Nyomjon 30-ast ha ki szeretne l�pni a programb�l!");
			gomb=sc.nextInt();
			switch (gomb) {
			case 1: {
				
				System.out.println("K�rem adja meg az aut� sz�n�t:");
				String szin=sc.next();
				System.out.println("K�rem adja meg az aut� �vj�rat�t:");
				int evjarat=sc.nextInt();
				System.out.println("K�rem adja meg az aut� t�pus�t:");
				String tipus=sc.next();
				System.out.println("K�rem adja meg az aut� rendsz�m�t:");
				String rendszam=sc.next();
				System.out.println("K�rem adja meg az aut� id-j�t:");
				int id=sc.nextInt();
				addauto(conn, szin, evjarat, tipus, rendszam, id);
				break;
				}
			case 2: {
				
				System.out.println("K�rem adja meg a sof�r telefonsz�m�t:");
				int telefonsz�m=sc.nextInt();
				System.out.println("K�rem adja meg a sof�r email-j�t:");
				String email=sc.next();
				System.out.println("K�rem adja meg a sof�r nev�t:");
				String n�v=sc.next();
				System.out.println("K�rem adja meg az indul�si id-t:");
				int indul�sid=sc.nextInt();
				System.out.println("K�rem adja meg az �rkez�s id-t:");
				int �rkez�sid=sc.nextInt();
				System.out.println("K�rem adja meg az sof�r id-t:");
				int id=sc.nextInt();
				System.out.println("K�rem adja meg az utas id-t:");
				int utasid=sc.nextInt();
				System.out.println("K�rem adja meg az aut� id-t:");
				int aut�id=sc.nextInt();
				addSofor(conn,telefonsz�m,email,n�v,indul�sid,�rkez�sid,id,utasid,aut�id);
				break;
				}
			case 3: {
	
				System.out.println("K�rem adja meg az utas telefonsz�m�t:");
				int telefonsz�m=sc.nextInt();
				System.out.println("K�rem adja meg az utas email-j�t:");
				String email=sc.next();
				System.out.println("K�rem adja meg az utas nev�t:");
				String n�v=sc.next();
				System.out.println("K�rem adja meg a honnan id-t:");
				int honnanid=sc.nextInt();
				System.out.println("K�rem adja meg az hova id-t:");
				int hovaid=sc.nextInt();
				System.out.println("K�rem adja meg az utas id-j�t:");
				int id=sc.nextInt();
				addUtas(conn,telefonsz�m,email,n�v,honnanid,hovaid,id);
				break;
				}
			
			case 4: {
				createTable(conn);
				break;
			}
			case 5: {
				insert(conn);
				break;
			}
			
			case 6: {
				System.out.println("K�rem adja meg milyen t�pusra konvert�lja �t a Reanult aut�kat:");
				String marka=sc.next();
				setPriceOfCarByColor(conn,marka);
				break;
			}
			
			case 7: {
				deleteauto(conn);
				break;
			}
			case 8: {
				deleteSofor(conn);
				break;
			}
			case 9: {
				deleteUtas(conn);
				break;
			}
			
			case 10: {
				String n�v = sc.next();
				
				kiiratas(getAllPassengers(conn,n�v));
				break;
			}
			
			}}while(gomb==30);
			
			
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public static void createTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE TABLE Utas("
				+ "telefonsz�m int,"
				+ "email varchar(100),"
				+ "n�v varchar(50),"
				+ "honnanid int,"
				+ "hovaid int,"
				+ "id int,"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Honnan(\r\n"
				+ "id int,\r\n"
				+ "d�tum varchar(100),\r\n"
				+ "�ra int,\r\n"
				+ "perc int,\r\n"
				+ "hely varchar(100),\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Hova(\r\n"
				+ "id int,\r\n"
				+ "hely varchar(100),\r\n"
				+ "PRIMARY KEY(id)\r\n"
				+ ")\r\n");
		stmt.execute("CREATE TABLE Sof�r(\r\n"
				+ "telefonsz�m int,\r\n"
				+ "email varchar(100),\r\n"
				+ "n�v varchar(50),\r\n"
				+ "indul�sid int,\r\n"
				+ "erkezesid int,\r\n"
				+ "id int,\r\n"
				+ "utasid int,\r\n"
				+ "autoid int,\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Indulas(\r\n"
				+ "d�tum varchar(100),\r\n"
				+ "�ra int,\r\n"
				+ "perc int,\r\n"
				+ "id int,\r\n"
				+ "hely varchar(50),\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Erkezes(\r\n"
				+ "hely varchar(50),\r\n"
				+ "id int,\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE auto(\r\n"
				+ "sz�n varchar(40),\r\n"
				+ "�vj�rat int,\r\n"
				+ "t�pus varchar(100),\r\n"
				+ "rendsz�m Varchar(7),\r\n"
				+ "id int,\r\n"
				+ "PRIMARY KEY(id))");

		
	}
	
	public static void insert(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		
		conn.setAutoCommit(true);
		String sqlparancs;
		sqlparancs= "INSERT INTO Honnan VALUES(1,'2021.12.14' , 14, 00, 'Tokaj')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(2,'2021.12.22', 17, 30, 'T�tkoml�s')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(3,'2021.12.26' , 15, 45, 'T�rkeve')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(4,'2021.12.21', 15, 45, '�jfeh�rt�')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(5,'2021.12.22', 18, 00, 'Zalakaros')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(6,'2021.12.12', 15, 00, 'V�c')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(7,'2021.12.27', 15, 15, 'Tiszal�k')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(8,'2021.12.22', 13, 45, 'Tiszaf�red')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(9,'2021.12.22', 13, 45, 'Tab')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(10,'2021.12.11', 15, 30, 'Tata')";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();
		//System.out.println("Ki�r valamit?");
		
		
		
		
		
		sqlparancs= "INSERT INTO hova(id, hely) VALUES(1, 'Balatonalm�di')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(2, 'Csongr�d')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(3, 'Derecske')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(4, 'Gyula')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(5, 'Keszthely')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(6, 'Monor')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(7, 'Ny�rb�tor')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(8, 'Nagykanizsa')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(9, 'Si�fok')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(10, 'Tapolca')";
		stmt.addBatch(sqlparancs);
	
		
		
		sqlparancs= "INSERT INTO indulas VALUES('2021.12.21', 15, 45, 1, '�jfeh�rt�')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.14', 14, 00, 2, 'Tokaj')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.27', 15, 00, 3, 'Tiszal�k')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.11', 15, 30, 4, 'Tata')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.21', 15, 45, 5, 'Tisza�jv�ros')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.22', 13, 45, 6, 'Tab')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.12', 15, 00, 7, 'V�c')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.22', 17, 30, 8, 'T�tkoml�s')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.26', 15, 45, 9, 'T�rkeve')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.21', 11, 15, 10, 'Budapest')";
		stmt.addBatch(sqlparancs);
	
		
		
		
		sqlparancs= "INSERT INTO erkezes VALUES('Encs', 1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('G�rdony', 2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes(hely,  id) VALUES('Marcali', 3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Mez�k�vesd', 4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Edel�ny', 5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Gyula', 6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Mak�', 7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Hajd�hadh�z', 8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Kazincbarcika', 9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('G�d�ll�', 10)";
		stmt.addBatch(sqlparancs);
	
		
		
		
		
		sqlparancs= "INSERT INTO utas VALUES ('36701492480', 'pilotbmn@freemail.hu', 'Balogh Ubul', 1, 1, 1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36302790436', 'bolgar.dominik@gmail.com', 'Bolg�r Dominik', 2, 2, 2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36306747376', ' nurgul78kg @gmail.com', 'Kis �d�n', 3, 3, 3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36705756161', ' mikeftw @gmail.com', 'J�n�s Oliv�r', 4, 4, 4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36307587637', ' markravch @hotmail.hu', 'Fazekas Ott�', 5, 5, 5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36707875312', ' fido77 @gmail.com', 'Nagy �bel', 6, 6, 6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36308618343', ' ubemojupavyja @freemail.hu', 'Kov�cs Anna', 7, 7, 7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36302514167', ' sedaeryaman @gmail.com', 'Szab� Anna', 8, 8, 8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36707243452', ' tgolden292 @gmail.com', 'Moln�r Anita', 9, 9, 9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36308731787', ' kireeva2006 @hotmail.hu', 'Balogh T�mea',10, 10, 10)";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();
		
		
		
		sqlparancs= "INSERT INTO auto VALUES('k�k', 2014, 'Skoda', 'NTF-342',1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('sz�rke', 2012, 'Seat', 'RAP-235',2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('z�ld', 2021, 'Mercedes', 'KKD-006',3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('fekete', 2006, 'Renault', 'NLE-048',4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('s�rga', 2018, 'Audi', 'JNM-070',5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('sz�rke', 2011, 'BMW', 'JGD-777',6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('FEKETE', 2013, 'Mazda', 'FBN-987',7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('sz�rke', 2021, 'Skoda', 'VRS-333',8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('barna', 2008, 'BMW', 'LZN-948',9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('feh�r', 2004, 'Ford', 'RAP-235',10)";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();


		sqlparancs= "INSERT INTO sof�r VALUES('06302548137', 'kireeva2006@gmail.com', 'Szab� K�roly', 1, 1, 1, 1, 1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06702846316', 'victorhugomontalvo@freemail.hu', 'Kapolcs Vilmos', 2, 2, 2, 2, 2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06307616512', 'arnootje@hotmail.hu', 'Pint�r Fl�ri�n', 3, 3, 3, 3, 3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06307162158', 'teodor@gmail.com', 'P�l Valentin', 4, 4, 4, 4, 4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06309513547', 'alinamohnach@gmail.com', 'Luk�cs Csan�d', 5, 5, 5, 5, 5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06709513584', 'umopepisdn@gmail.com', 'Szil�gyi Gyula', 6, 6, 6, 6, 6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06308943216', 'stonaige@hotmail.hu', 'Lengyel Gergely', 7, 7, 7, 7, 7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06308913284', 'sue1959@freemail.hu', 'Sz�ts Boldizs�r', 8, 8, 8, 8, 8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06709841651', 'mustek550@gmail.com', 'Csonka Dezs�', 9, 9, 9, 9, 9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sof�r VALUES('06701592174', 'kaprun64@gmail.com', 'Dobos Ram�na', 10, 10, 10, 10, 10)";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();
	

	}
	
	public static void addauto(Connection conn,String sz�n,int �vj�rat, String t�pus,String rendsz�m,int id) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO auto VALUES(?, ?, ?, ?)"
				+ "");

		prstmt.setString(1,sz�n);
		prstmt.setInt(2,�vj�rat);
		prstmt.setString(3,t�pus);
		prstmt.setString(4,rendsz�m);
		
		
		prstmt.executeUpdate();
		
	}
	public static void addSofor(Connection conn,int telefonsz�m,String email, String n�v,int indul�sid,int �rkez�sid,int id, int utasid, int aut�id) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO auto VALUES(?, ?, ?, ?,?,?,?,?)"
				+ "");

		prstmt.setInt(1,telefonsz�m);
		prstmt.setString(2,email);
		prstmt.setString(3,n�v);
		prstmt.setInt(4,indul�sid);
		prstmt.setInt(5, �rkez�sid);
		prstmt.setInt(6, id);
		prstmt.setInt(7, utasid);
		prstmt.setInt(8, aut�id);
		
		prstmt.executeUpdate();
		
	}
	public static void addUtas(Connection conn,int telefonsz�m,String email, String n�v,int honnanid,int hovaid,int id) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO Utas VALUES(?, ?, ?, ?,?, ?)"
				+ "");

		prstmt.setInt(1,telefonsz�m);
		prstmt.setString(2,email);
		prstmt.setString(3,n�v);
		prstmt.setInt(4,honnanid);
		prstmt.setInt(5, hovaid);
		prstmt.setInt(6, id);
		
		prstmt.executeUpdate();
		
	}
	
	
	public static void setPriceOfCarByColor(Connection conn, String t�pus) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update auto set t�pus ='"+t�pus+"' where t�pus ='Renault' ");
	}
	public static void setSoforNamebyId(Connection conn, String n�v,int id) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update sof�r set n�v ='"+n�v+"' where id = "+id);
	}
	public static void setUtasNamebyId(Connection conn, String n�v,int id) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update utas set n�v ='"+n�v+"' where id = "+id);
	}
	
	
	public static ResultSet getAllPassengers(Connection conn,String n�v) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT utas.n�v, utas.email, hova.hely\r\n"
				+ "From utas JOIN hova On utas.hovaid=hova.id where utas.n�v="+n�v);
		
		return rs;
		
		}
	
	public static void kiiratas (ResultSet rs) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

    }
	 
	
	
	public static void deleteauto(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String delete ="DELETE FROM auto WHERE sz�n='k�k'";
		stmt.executeUpdate(delete);
		
		
	}
	
	public static void deleteSofor(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String delete ="DELETE FROM sof�r WHERE n�v='Dobos Ram�na'";
		stmt.executeUpdate(delete);
	}
	
	public static void deleteUtas(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String delete ="DELETE FROM utas WHERE n�v='Bolg�r Dominik'";
		stmt.executeUpdate(delete);
	}

}
