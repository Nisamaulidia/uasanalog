import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JDBC {
	 /*
	  * 
	  *  Fungsi untuk koneksi ke database
	  */
	 public static Connection koneksi() {
			
		    try {
			    String driver = "com.mysql.cj.jdbc.Driver";
			    String url = "jdbc:mysql://localhost:3306/perpus";
			    String user = "root";
			    String password = "";
			    
			    try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			    
			    return DriverManager.getConnection(url, user, password);
			
		    } catch (SQLException e) {
		    	System.out.println(e);
			    JOptionPane.showMessageDialog(null, "Gagal Koneksi ke Database...", "Pemberitahuan", 0);
		    }
		    
		    return koneksi();
		    
	    }

}
