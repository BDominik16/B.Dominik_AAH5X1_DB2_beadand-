package beadandó;

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


public class beadandó {
	private static final String URL="jdbc:oracle:thin:@193.6.5.58:1521:XE";
	
	public static void main(String[] args) {
		
try {Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			Connection conn = null;
			Scanner sc= new Scanner(System.in);
			System.out.println("Kérem a felhasználó nevet:");
			String felhasz=sc.next();
			System.out.println("Kérem a jelszót:");
			String jelszo=sc.next();
			conn = DriverManager.getConnection(URL, felhasz, jelszo);
			
			int gomb;
		
			
			do {
				System.out.println("Nyomjon 1-est ha autót szeretne felvenni!");
				System.out.println("Nyomjon 2-est ha sofõrt szeretne felvenni!");
				System.out.println("Nyomjon 3-ast ha utas szeretne felvenni!");
				System.out.println("Nyomjon 4-est ha táblákat szeretne létrehozni!");
				System.out.println("Nyomjon 5-ast ha felszeretné tölteni a táblákat!");
				System.out.println("Nyomjon 6-est ha az autó táblában lévõ Reanult márkájú autókat megszeretné változtatni!");
				System.out.println("Nyomjon 7-öst ha az autó táblában lévõ kék színû autókat törölni akarja!");
				System.out.println("Nyomjon 8-ost ha ki akarja iratni az utasoknak a nevét, email címét és azt a helyet ahova utazik!");
				System.out.println("Nyomjon 30-ast ha ki szeretne lépni a programból!");
			gomb=sc.nextInt();
			switch (gomb) {
			case 1: {
				
				System.out.println("Kérem adja meg az autó színét:");
				String szin=sc.next();
				System.out.println("Kérem adja meg az autó évjáratát:");
				int evjarat=sc.nextInt();
				System.out.println("Kérem adja meg az autó típusát:");
				String tipus=sc.next();
				System.out.println("Kérem adja meg az autó rendszámát:");
				String rendszam=sc.next();
				System.out.println("Kérem adja meg az autó id-ját:");
				int id=sc.nextInt();
				addauto(conn, szin, evjarat, tipus, rendszam, id);
				break;
				}
			case 2: {
				
				System.out.println("Kérem adja meg a sofõr telefonszámát:");
				int telefonszám=sc.nextInt();
				System.out.println("Kérem adja meg a sofõr email-ját:");
				String email=sc.next();
				System.out.println("Kérem adja meg a sofõr nevét:");
				String név=sc.next();
				System.out.println("Kérem adja meg az indulási id-t:");
				int indulásid=sc.nextInt();
				System.out.println("Kérem adja meg az érkezés id-t:");
				int érkezésid=sc.nextInt();
				System.out.println("Kérem adja meg az sofõr id-t:");
				int id=sc.nextInt();
				System.out.println("Kérem adja meg az utas id-t:");
				int utasid=sc.nextInt();
				System.out.println("Kérem adja meg az autó id-t:");
				int autóid=sc.nextInt();
				addSofor(conn,telefonszám,email,név,indulásid,érkezésid,id,utasid,autóid);
				break;
				}
			case 3: {
	
				System.out.println("Kérem adja meg az utas telefonszámát:");
				int telefonszám=sc.nextInt();
				System.out.println("Kérem adja meg az utas email-ját:");
				String email=sc.next();
				System.out.println("Kérem adja meg az utas nevét:");
				String név=sc.next();
				System.out.println("Kérem adja meg a honnan id-t:");
				int honnanid=sc.nextInt();
				System.out.println("Kérem adja meg az hova id-t:");
				int hovaid=sc.nextInt();
				System.out.println("Kérem adja meg az utas id-ját:");
				int id=sc.nextInt();
				addUtas(conn,telefonszám,email,név,honnanid,hovaid,id);
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
				System.out.println("Kérem adja meg milyen típusra konvertálja át a Reanult autókat:");
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
				String név = sc.next();
				
				kiiratas(getAllPassengers(conn,név));
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
				+ "telefonszám int,"
				+ "email varchar(100),"
				+ "név varchar(50),"
				+ "honnanid int,"
				+ "hovaid int,"
				+ "id int,"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Honnan(\r\n"
				+ "id int,\r\n"
				+ "dátum varchar(100),\r\n"
				+ "óra int,\r\n"
				+ "perc int,\r\n"
				+ "hely varchar(100),\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Hova(\r\n"
				+ "id int,\r\n"
				+ "hely varchar(100),\r\n"
				+ "PRIMARY KEY(id)\r\n"
				+ ")\r\n");
		stmt.execute("CREATE TABLE Sofõr(\r\n"
				+ "telefonszám int,\r\n"
				+ "email varchar(100),\r\n"
				+ "név varchar(50),\r\n"
				+ "indulásid int,\r\n"
				+ "erkezesid int,\r\n"
				+ "id int,\r\n"
				+ "utasid int,\r\n"
				+ "autoid int,\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Indulas(\r\n"
				+ "dátum varchar(100),\r\n"
				+ "óra int,\r\n"
				+ "perc int,\r\n"
				+ "id int,\r\n"
				+ "hely varchar(50),\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE Erkezes(\r\n"
				+ "hely varchar(50),\r\n"
				+ "id int,\r\n"
				+ "PRIMARY KEY(id))");
		
		stmt.execute("CREATE TABLE auto(\r\n"
				+ "szín varchar(40),\r\n"
				+ "évjárat int,\r\n"
				+ "típus varchar(100),\r\n"
				+ "rendszám Varchar(7),\r\n"
				+ "id int,\r\n"
				+ "PRIMARY KEY(id))");

		
	}
	
	public static void insert(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		
		conn.setAutoCommit(true);
		String sqlparancs;
		sqlparancs= "INSERT INTO Honnan VALUES(1,'2021.12.14' , 14, 00, 'Tokaj')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(2,'2021.12.22', 17, 30, 'Tótkomlós')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(3,'2021.12.26' , 15, 45, 'Túrkeve')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(4,'2021.12.21', 15, 45, 'Újfehértó')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(5,'2021.12.22', 18, 00, 'Zalakaros')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(6,'2021.12.12', 15, 00, 'Vác')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(7,'2021.12.27', 15, 15, 'Tiszalök')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(8,'2021.12.22', 13, 45, 'Tiszafüred')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(9,'2021.12.22', 13, 45, 'Tab')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO Honnan VALUES(10,'2021.12.11', 15, 30, 'Tata')";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();
		//System.out.println("Kiír valamit?");
		
		
		
		
		
		sqlparancs= "INSERT INTO hova(id, hely) VALUES(1, 'Balatonalmádi')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(2, 'Csongrád')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(3, 'Derecske')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(4, 'Gyula')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(5, 'Keszthely')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(6, 'Monor')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(7, 'Nyírbátor')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(8, 'Nagykanizsa')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(9, 'Siófok')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO hova(id, hely) VALUES(10, 'Tapolca')";
		stmt.addBatch(sqlparancs);
	
		
		
		sqlparancs= "INSERT INTO indulas VALUES('2021.12.21', 15, 45, 1, 'Újfehértó')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.14', 14, 00, 2, 'Tokaj')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.27', 15, 00, 3, 'Tiszalök')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.11', 15, 30, 4, 'Tata')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.21', 15, 45, 5, 'Tiszaújváros')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.22', 13, 45, 6, 'Tab')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.12', 15, 00, 7, 'Vác')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.22', 17, 30, 8, 'Tótkomlós')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.26', 15, 45, 9, 'Túrkeve')";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO indulas VALUES('2021.12.21', 11, 15, 10, 'Budapest')";
		stmt.addBatch(sqlparancs);
	
		
		
		
		sqlparancs= "INSERT INTO erkezes VALUES('Encs', 1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Gárdony', 2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes(hely,  id) VALUES('Marcali', 3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Mezõkövesd', 4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Edelény', 5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Gyula', 6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Makó', 7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Hajdúhadház', 8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Kazincbarcika', 9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO erkezes VALUES('Gödöllõ', 10)";
		stmt.addBatch(sqlparancs);
	
		
		
		
		
		sqlparancs= "INSERT INTO utas VALUES ('36701492480', 'pilotbmn@freemail.hu', 'Balogh Ubul', 1, 1, 1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36302790436', 'bolgar.dominik@gmail.com', 'Bolgár Dominik', 2, 2, 2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36306747376', ' nurgul78kg @gmail.com', 'Kis Ödön', 3, 3, 3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36705756161', ' mikeftw @gmail.com', 'Jónás Olivér', 4, 4, 4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36307587637', ' markravch @hotmail.hu', 'Fazekas Ottó', 5, 5, 5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36707875312', ' fido77 @gmail.com', 'Nagy Ábel', 6, 6, 6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36308618343', ' ubemojupavyja @freemail.hu', 'Kovács Anna', 7, 7, 7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36302514167', ' sedaeryaman @gmail.com', 'Szabó Anna', 8, 8, 8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36707243452', ' tgolden292 @gmail.com', 'Molnár Anita', 9, 9, 9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO utas VALUES ('36308731787', ' kireeva2006 @hotmail.hu', 'Balogh Tímea',10, 10, 10)";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();
		
		
		
		sqlparancs= "INSERT INTO auto VALUES('kék', 2014, 'Skoda', 'NTF-342',1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('szürke', 2012, 'Seat', 'RAP-235',2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('zöld', 2021, 'Mercedes', 'KKD-006',3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('fekete', 2006, 'Renault', 'NLE-048',4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('sárga', 2018, 'Audi', 'JNM-070',5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('szürke', 2011, 'BMW', 'JGD-777',6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('FEKETE', 2013, 'Mazda', 'FBN-987',7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('szürke', 2021, 'Skoda', 'VRS-333',8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('barna', 2008, 'BMW', 'LZN-948',9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO auto VALUES('fehér', 2004, 'Ford', 'RAP-235',10)";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();


		sqlparancs= "INSERT INTO sofõr VALUES('06302548137', 'kireeva2006@gmail.com', 'Szabó Károly', 1, 1, 1, 1, 1)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06702846316', 'victorhugomontalvo@freemail.hu', 'Kapolcs Vilmos', 2, 2, 2, 2, 2)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06307616512', 'arnootje@hotmail.hu', 'Pintér Flórián', 3, 3, 3, 3, 3)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06307162158', 'teodor@gmail.com', 'Pál Valentin', 4, 4, 4, 4, 4)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06309513547', 'alinamohnach@gmail.com', 'Lukács Csanád', 5, 5, 5, 5, 5)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06709513584', 'umopepisdn@gmail.com', 'Szilágyi Gyula', 6, 6, 6, 6, 6)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06308943216', 'stonaige@hotmail.hu', 'Lengyel Gergely', 7, 7, 7, 7, 7)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06308913284', 'sue1959@freemail.hu', 'Szûts Boldizsár', 8, 8, 8, 8, 8)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06709841651', 'mustek550@gmail.com', 'Csonka Dezsõ', 9, 9, 9, 9, 9)";
		stmt.addBatch(sqlparancs);
		sqlparancs="INSERT INTO sofõr VALUES('06701592174', 'kaprun64@gmail.com', 'Dobos Ramóna', 10, 10, 10, 10, 10)";
		stmt.addBatch(sqlparancs);
		stmt.executeBatch();
	

	}
	
	public static void addauto(Connection conn,String szín,int évjárat, String típus,String rendszám,int id) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO auto VALUES(?, ?, ?, ?)"
				+ "");

		prstmt.setString(1,szín);
		prstmt.setInt(2,évjárat);
		prstmt.setString(3,típus);
		prstmt.setString(4,rendszám);
		
		
		prstmt.executeUpdate();
		
	}
	public static void addSofor(Connection conn,int telefonszám,String email, String név,int indulásid,int érkezésid,int id, int utasid, int autóid) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO auto VALUES(?, ?, ?, ?,?,?,?,?)"
				+ "");

		prstmt.setInt(1,telefonszám);
		prstmt.setString(2,email);
		prstmt.setString(3,név);
		prstmt.setInt(4,indulásid);
		prstmt.setInt(5, érkezésid);
		prstmt.setInt(6, id);
		prstmt.setInt(7, utasid);
		prstmt.setInt(8, autóid);
		
		prstmt.executeUpdate();
		
	}
	public static void addUtas(Connection conn,int telefonszám,String email, String név,int honnanid,int hovaid,int id) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("INSERT INTO Utas VALUES(?, ?, ?, ?,?, ?)"
				+ "");

		prstmt.setInt(1,telefonszám);
		prstmt.setString(2,email);
		prstmt.setString(3,név);
		prstmt.setInt(4,honnanid);
		prstmt.setInt(5, hovaid);
		prstmt.setInt(6, id);
		
		prstmt.executeUpdate();
		
	}
	
	
	public static void setPriceOfCarByColor(Connection conn, String típus) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update auto set típus ='"+típus+"' where típus ='Renault' ");
	}
	public static void setSoforNamebyId(Connection conn, String név,int id) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update sofõr set név ='"+név+"' where id = "+id);
	}
	public static void setUtasNamebyId(Connection conn, String név,int id) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("update utas set név ='"+név+"' where id = "+id);
	}
	
	
	public static ResultSet getAllPassengers(Connection conn,String név) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT utas.név, utas.email, hova.hely\r\n"
				+ "From utas JOIN hova On utas.hovaid=hova.id where utas.név="+név);
		
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
		String delete ="DELETE FROM auto WHERE szín='kék'";
		stmt.executeUpdate(delete);
		
		
	}
	
	public static void deleteSofor(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String delete ="DELETE FROM sofõr WHERE név='Dobos Ramóna'";
		stmt.executeUpdate(delete);
	}
	
	public static void deleteUtas(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String delete ="DELETE FROM utas WHERE név='Bolgár Dominik'";
		stmt.executeUpdate(delete);
	}

}
