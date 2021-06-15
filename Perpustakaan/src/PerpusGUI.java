import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class PerpusGUI {

	private JFrame frmPerpus;
	private JTabbedPane tabbedPane;
	private JTextField id;
	private JTextField judul;
	private JTextField penerbit;
	private JTextField tahun;
	private JTable tableBuku;
	private JComboBox<String> status;
	private JTextField cariIdBuku;
	private JTextArea textAreaBuku;
	private JButton simpanBuku;
	private JButton editBuku;
	private JButton hapusBuku;
	private JTextField cariIdPinjam;
	private JTable tablePinjam;
	private JTextField cariIdSedia;
	private JTextField idpinjam;
	private JTextField namaPinjam;
	private JTextField nik;
	private JTextField nohp;
	private JButton btnPinjam;
	private JButton cekSedia;
	private JTextArea textAreaPinjam;
	private JTextField txtKembali;
	private JTextArea textAreaKembali;
	private JTextField txtID;
	private JButton konfirmasi;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerpusGUI window = new PerpusGUI();
					window.frmPerpus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//fungsi untuk menampilkan data dari database ke tabel buku
	//disini mengimplementasikan struktur data array
	//data dari array akan ditampilkan ke tabel
	public void showData() {
		try {
			String query = "select * from buku";
			Connection con = (Connection)JDBC.koneksi();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);
			
			DefaultTableModel model = (DefaultTableModel)tableBuku.getModel();
	        model.setRowCount(0);
	        
	        String [] data = new String [5];
	        
	        while(rs.next()) {
	        	 data[0] = rs.getString("idbuku");
		         data[1] = rs.getString("judul");
		         data[2] = rs.getString("penerbit");
		         data[3] = rs.getString("tahun");
		         data[4] = rs.getString("status");
		         
	             model.addRow(data); 
	        }
	        
	        if (con != null) {
	        	try {
	        		con.close();
				} catch (SQLException e4) {}
	        }
		 
		    if (pst != null) {
	        	try {
	        		pst.close();
				} catch (SQLException e5) {}
	        }
		    
		    if (rs != null) {
	        	try {
	        		rs.close();
				} catch (SQLException e5) {}
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gagal Insert Data");
			System.out.println(e);
		}
	}
	
	//fungsi menampilkan data peminjam dari database ke tabel pinjam
	//disini mengimplementasikan struktur data array
	//data dari array akan ditampilkan ke tabel
	public void showDataPinjam() {
		try {
			String query = "select * from pinjam";
			Connection con = (Connection)JDBC.koneksi();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);
			
			DefaultTableModel model = (DefaultTableModel)tablePinjam.getModel();
	        model.setRowCount(0);
	        
	        String [] pinjam = new String [5];
	        
	        while(rs.next()) {
	        	pinjam[0] = rs.getString("idpinjam");
	        	pinjam[1] = rs.getString("nama");
	        	pinjam[2] = rs.getString("nik");
	        	pinjam[3] = rs.getString("nohp");
	        	pinjam[4] = rs.getString("idbuku");
		         
	             model.addRow(pinjam); 
	        }
	        
	        if (con != null) {
	        	try {
	        		con.close();
				} catch (SQLException e4) {}
	        }
		 
		    if (pst != null) {
	        	try {
	        		pst.close();
				} catch (SQLException e5) {}
	        }
		    
		    if (rs != null) {
	        	try {
	        		rs.close();
				} catch (SQLException e5) {}
	        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gagal Insert Data Peminjam");
			System.out.println(e);
		}
	}
	
	//fungsi untuk mencari data buku
	public class searchFunction{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		public ResultSet find(String s){
			try {
				con = (Connection)JDBC.koneksi();
				ps = con.prepareStatement("select * from buku where idbuku= ?");
				ps.setString(1, s);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			return rs;
		}
	}

	/**
	 * Create the application.
	 */
	public PerpusGUI() {
		initialize();
		showData();
		showDataPinjam();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmPerpus = new JFrame();
		frmPerpus.setIconImage(Toolkit.getDefaultToolkit().getImage(PerpusGUI.class.getResource("/assets/library.png")));
		frmPerpus.setTitle("Perpus");
		frmPerpus.setBounds(200, 80, 960, 600);
		frmPerpus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPerpus.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(160, 82, 45));
		panel.setBounds(0, 454, 944, 107);
		frmPerpus.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton kembali = new JButton("PENGEMBALIAN");
		kembali.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/return.png")));
		kembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		kembali.setFocusable(false);
		kembali.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				kembali.setBackground(new Color(128,0,128));
				kembali.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				kembali.setForeground(Color.BLACK);
				kembali.setBackground(new Color(224, 255, 255));
			}
		});
		kembali.setBackground(new Color(224, 255, 255));
		kembali.setForeground(Color.BLACK);
		kembali.setFont(new Font("Times New Roman", Font.BOLD, 15));
		kembali.setBounds(604, 30, 198, 50);
		panel.add(kembali);
		
		JButton pinjam = new JButton("PINJAM");
		pinjam.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/reading.png")));
		pinjam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		pinjam.setFocusable(false);
		pinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pinjam.setBackground(new Color(128,0,128));
				pinjam.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pinjam.setForeground(Color.BLACK);
				pinjam.setBackground(new Color(224, 255, 255));
			}
		});
		pinjam.setBackground(new Color(224, 255, 255));
		pinjam.setForeground(Color.BLACK);
		pinjam.setFont(new Font("Times New Roman", Font.BOLD, 15));
		pinjam.setBounds(371, 30, 198, 50);
		panel.add(pinjam);
		
		JButton input = new JButton("INPUT");
		input.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/plus.png")));
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		input.setFocusable(false);
		input.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				input.setBackground(new Color(128,0,128));
				input.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				input.setForeground(Color.BLACK);
				input.setBackground(new Color(224, 255, 255));
			}
		});
		input.setBackground(new Color(224, 255, 255));
		input.setForeground(Color.BLACK);
		input.setFont(new Font("Times New Roman", Font.BOLD, 15));
		input.setBounds(142, 30, 198, 50);
		panel.add(input);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(-10, -30, 964, 496);
		frmPerpus.getContentPane().add(tabbedPane);
		
		JPanel panelInput = new JPanel();
		panelInput.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panelInput.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, panelInput, null);
		panelInput.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(128, 21, 189, 33);
		panelInput.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INPUT BUKU BARU");
		lblNewLabel.setBounds(0, 0, 189, 33);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("ID Buku");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(36, 100, 75, 30);
		panelInput.add(lblNewLabel_2);
		
		id = new JTextField();
		id.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		//fungsi agar hanya bisa mengisi angka
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
		id.setBounds(196, 100, 234, 30);
		panelInput.add(id);
		id.setColumns(10);
		
		judul = new JTextField();
		judul.setFont(new Font("Tahoma", Font.BOLD, 14));
		judul.setColumns(10);
		judul.setBounds(196, 153, 234, 30);
		panelInput.add(judul);
		
		JLabel lblNewLabel_2_1 = new JLabel("Judul Buku");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(36, 153, 103, 30);
		panelInput.add(lblNewLabel_2_1);
		
		penerbit = new JTextField();
		penerbit.setFont(new Font("Tahoma", Font.BOLD, 14));
		penerbit.setColumns(10);
		penerbit.setBounds(196, 209, 234, 30);
		panelInput.add(penerbit);
		
		JLabel lblNewLabel_2_2 = new JLabel("Penerbit");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(36, 209, 75, 30);
		panelInput.add(lblNewLabel_2_2);
		
		tahun = new JTextField();
		tahun.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		//hanya bisa mengisi angka
		tahun.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
		tahun.setColumns(10);
		tahun.setBounds(196, 266, 234, 30);
		panelInput.add(tahun);
		
		JLabel lblNewLabel_2_3 = new JLabel("Tahun Terbit");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(36, 266, 103, 30);
		panelInput.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Status Buku");
		lblNewLabel_2_3_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_3_1.setBounds(36, 324, 103, 30);
		panelInput.add(lblNewLabel_2_3_1);
		
		status = new JComboBox<String>();
		status.setFont(new Font("Times New Roman", Font.BOLD, 12));
		status.setModel(new DefaultComboBoxModel(new String[] {"TERSEDIA"}));
		status.setBounds(196, 324, 121, 27);
		panelInput.add(status);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 255, 255), 7));
		panel_2.setBackground(new Color(70, 130, 180));
		panel_2.setBounds(460, 0, 499, 468);
		panelInput.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 260, 471, 190);
		panel_2.add(scrollPane);
		
		tableBuku = new JTable();
		
		//fungsi ketika klik tabel buku maka akan mengirim datanya ke form
		tableBuku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setEditable(false);
				simpanBuku.setEnabled(false);
				editBuku.setEnabled(true);
				hapusBuku.setEnabled(true);
				
				int row = tableBuku.rowAtPoint(e.getPoint());
				
				id.setText(tableBuku.getValueAt(row, 0).toString());
				judul.setText(tableBuku.getValueAt(row, 1).toString());
				penerbit.setText(tableBuku.getValueAt(row, 2).toString());
				tahun.setText(tableBuku.getValueAt(row, 3).toString());
			}
		});
		tableBuku.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Buku", "Judul Buku", "Penerbit", "Tahun Terbit", "Status Buku"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tableBuku);
		
		JLabel lblNewLabel_2_4 = new JLabel("Masukkan ID Buku");
		lblNewLabel_2_4.setForeground(Color.WHITE);
		lblNewLabel_2_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_4.setBounds(10, 21, 158, 30);
		panel_2.add(lblNewLabel_2_4);
		
		cariIdBuku = new JTextField();
		cariIdBuku.setFont(new Font("Tahoma", Font.BOLD, 14));
		cariIdBuku.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
		cariIdBuku.setColumns(10);
		cariIdBuku.setBounds(158, 22, 144, 30);
		panel_2.add(cariIdBuku);
		
		JButton cariBuku = new JButton("CARI");
		
		//fungsi untuk menampilkan deskripsi buku ke text area
		cariBuku.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchFunction sf = new searchFunction();
					ResultSet rs = null;
					
					rs = sf.find(cariIdBuku.getText());
					try {
						if (rs.next()) {
							textAreaBuku.setText("--- Buku ditemukan ---\n\nRincian buku:\n"+"ID Buku\t: "+String.valueOf(rs.getString("idbuku"))+"\nJudul Buku\t: "+rs.getString("judul")+"\nPenerbit\t: "+rs.getString("penerbit")+"\nTahun Terbit\t: "+String.valueOf(rs.getString("tahun"))+"\nStatus Buku\t: "+String.valueOf(rs.getString("status"))+"");
						} else {
							JOptionPane.showMessageDialog(null, "Data buku tidak ditemukan");
							cariIdBuku.setText("");
							textAreaBuku.setText("");
						
		        		    if (rs != null) {
		        	        	try {
		        	        		rs.close();
		        				} catch (SQLException e5) {}
		        	        }
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						cariIdBuku.setText("");
						textAreaBuku.setText("");
					  }
			}
		});
		cariBuku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cariBuku.setBackground(new Color(0,128,128));
				cariBuku.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cariBuku.setForeground(Color.BLACK);
				cariBuku.setBackground(new Color(224, 255, 255));
			}
		});
		cariBuku.setForeground(Color.BLACK);
		cariBuku.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cariBuku.setFocusable(false);
		cariBuku.setBackground(Color.WHITE);
		cariBuku.setActionCommand("Clear");
		cariBuku.setBounds(312, 22, 83, 30);
		panel_2.add(cariBuku);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(97, 91, 298, 158);
		panel_2.add(scrollPane_1);
		
		textAreaBuku = new JTextArea();
		textAreaBuku.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		textAreaBuku.setEditable(false);
		scrollPane_1.setViewportView(textAreaBuku);
		
		JButton clearDesc = new JButton("CLEAR");
		clearDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cariIdBuku.setText("");
				textAreaBuku.setText("");
			}
		});
		clearDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clearDesc.setBackground(new Color(0,128,128));
				clearDesc.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				clearDesc.setForeground(Color.BLACK);
				clearDesc.setBackground(new Color(224, 255, 255));
			}
		});
		clearDesc.setForeground(Color.BLACK);
		clearDesc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		clearDesc.setFocusable(false);
		clearDesc.setBackground(Color.WHITE);
		clearDesc.setActionCommand("Clear");
		clearDesc.setBounds(405, 22, 75, 30);
		panel_2.add(clearDesc);
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("Deskripsi Buku");
		lblNewLabel_2_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblNewLabel_2_3_1_1.setBounds(158, 65, 144, 30);
		panel_2.add(lblNewLabel_2_3_1_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("");
		lblNewLabel_7_1.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/library.jpg")));
		lblNewLabel_7_1.setBounds(0, -65, 678, 533);
		panel_2.add(lblNewLabel_7_1);
		
		simpanBuku = new JButton("SIMPAN");
		
		//fungsi tombol simpan, menyimpan data ke database
		simpanBuku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "insert into buku values('"+Integer.parseInt(id.getText())+"','"+judul.getText()+"','"+penerbit.getText()+"','"+Integer.parseInt(tahun.getText())+"','"+status.getSelectedItem()+"')";
					Connection con = (Connection)JDBC.koneksi();
					PreparedStatement pst = con.prepareStatement(sql);
					if (id.getText().equals("")) {
						JOptionPane.showMessageDialog(simpanBuku, "ID Buku tidak boleh kosong!");
					} else if (judul.getText().equals("")) {
						JOptionPane.showMessageDialog(simpanBuku, "Judul Buku tidak boleh kosong!");
					} else if (penerbit.getText().equals("")) {
						JOptionPane.showMessageDialog(simpanBuku, "Penerbit tidak boleh kosong!");
					} else if (tahun.getText().equals("")) {
						JOptionPane.showMessageDialog(simpanBuku, "Tahun Terbit tidak boleh kosong!");
					} else {
						pst.execute();
						JOptionPane.showMessageDialog(simpanBuku, "Data Buku Tersimpan");
						showData();
						id.setText("");
						judul.setText("");
						penerbit.setText("");
						tahun.setText("");
						
						if (con != null) {
	        	        	try {
	        	        		con.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (pst != null) {
	        	        	try {
	        	        		pst.close();
	        				} catch (SQLException e5) {}
	        	        }
					} 
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(simpanBuku, "Gagal simpan data", "KESALAHAN INPUT!!", 0);
					System.out.println(e1);
				}
			}
		});
		simpanBuku.setFocusable(false);
		simpanBuku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				simpanBuku.setBackground(new Color(50,205,50));
				simpanBuku.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				simpanBuku.setForeground(Color.BLACK);
				simpanBuku.setBackground(new Color(224, 255, 255));
			}
		});
		simpanBuku.setBackground(Color.WHITE);
		simpanBuku.setForeground(Color.BLACK);
		simpanBuku.setFont(new Font("Times New Roman", Font.BOLD, 12));
		simpanBuku.setBounds(66, 388, 93, 42);
		panelInput.add(simpanBuku);
		
		editBuku = new JButton("EDIT");
		editBuku.setEnabled(false);
		
		//fungsi tombol edit, mengedit data buku
		editBuku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(editBuku, "Yakin ingin mengubah data buku?", "Confirm", JOptionPane.YES_NO_OPTION);
				try {
					String query = "update buku set judul='"+judul.getText()+"',penerbit='"+penerbit.getText()+"',tahun='"+Integer.parseInt(tahun.getText())+"' where idbuku='"+id.getText()+"'";
					Connection conn = (Connection)JDBC.koneksi();
					PreparedStatement st = conn.prepareStatement(query);
					if (id.getText().equals("")) {
						JOptionPane.showMessageDialog(editBuku, "ID Buku tidak boleh kosong!");
					} else if (judul.getText().equals("")) {
						JOptionPane.showMessageDialog(editBuku, "Judul Buku tidak boleh kosong!");
					} else if (penerbit.getText().equals("")) {
						JOptionPane.showMessageDialog(editBuku, "Penerbit tidak boleh kosong!");
					} else if (tahun.getText().equals("")) {
						JOptionPane.showMessageDialog(editBuku, "Tahun terbit tidak boleh kosong!");
					}else if (confirm==0) {
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(editBuku, "Edit data buku berhasil");
                        showData();
                        id.setEditable(true);
                        id.setText("");
						judul.setText("");
						penerbit.setText("");
						tahun.setText("");
						hapusBuku.setEnabled(false);
                        editBuku.setEnabled(false);
                        simpanBuku.setEnabled(true);
                        if (conn != null) {
	        	        	try {
	        	        		conn.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (st != null) {
	        	        	try {
	        	        		st.close();
	        				} catch (SQLException e5) {}
	        	        }
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(editBuku, "Edit gagal");
					System.out.println(e2);
				}
			}
		});
		editBuku.setFocusable(false);
		editBuku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				editBuku.setBackground(new Color(255,165,0));
				editBuku.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				editBuku.setForeground(Color.BLACK);
				editBuku.setBackground(new Color(224, 255, 255));
			}
		});
		editBuku.setForeground(Color.BLACK);
		editBuku.setBackground(Color.WHITE);
		editBuku.setFont(new Font("Times New Roman", Font.BOLD, 12));
		editBuku.setBounds(184, 388, 93, 42);
		panelInput.add(editBuku);
		
		hapusBuku = new JButton("HAPUS");
		hapusBuku.setEnabled(false);
		
		//fungsi untuk menghapus data buku
		hapusBuku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(hapusBuku, "Hapus data buku?", "Peringatan!!!.", JOptionPane.YES_NO_OPTION);
				
				try {
					String query = "delete from buku where idbuku='"+id.getText()+"'";
					Connection conn = (Connection)JDBC.koneksi();
					PreparedStatement st = conn.prepareStatement(query);
					
					if (id.getText().equals("")) {
						JOptionPane.showMessageDialog(hapusBuku, "Tidak ada data yang dipilih");
					} else if (confirm==0) {
						st.executeUpdate();
						JOptionPane.showMessageDialog(hapusBuku, "Berhasil menghapus data buku...");
						showData();
						id.setEditable(true);
						id.setText("");
						judul.setText("");
						penerbit.setText("");
						tahun.setText("");
						hapusBuku.setEnabled(false);
                        editBuku.setEnabled(false);
                        simpanBuku.setEnabled(true);
                        
						if (conn != null) {
	        	        	try {
	        	        		conn.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (st != null) {
	        	        	try {
	        	        		st.close();
	        				} catch (SQLException e5) {}
	        	        }
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(hapusBuku, "Gagal Hapus");
					System.out.println(e2);
				}
			}
		});
		hapusBuku.setFocusable(false);
		hapusBuku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hapusBuku.setBackground(new Color(255,69,0));
				hapusBuku.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hapusBuku.setForeground(Color.BLACK);
				hapusBuku.setBackground(new Color(224, 255, 255));
			}
		});
		hapusBuku.setBackground(Color.WHITE);
		hapusBuku.setForeground(Color.BLACK);
		hapusBuku.setFont(new Font("Times New Roman", Font.BOLD, 12));
		hapusBuku.setBounds(302, 388, 93, 42);
		panelInput.add(hapusBuku);
		
		//fungsi tombol reset pada bagian input buku
		JButton clearBuku = new JButton("Reset");
		clearBuku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setEditable(true);
				simpanBuku.setEnabled(true);
				editBuku.setEnabled(false);
				hapusBuku.setEnabled(false);
				id.setText("");
				judul.setText("");
				penerbit.setText("");
				tahun.setText("");
			}
		});
		clearBuku.setFocusable(false);
		clearBuku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clearBuku.setBackground(new Color(255,99,71));
				clearBuku.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				clearBuku.setForeground(Color.BLACK);
				clearBuku.setBackground(new Color(224, 255, 255));
			}
		});
		clearBuku.setForeground(Color.BLACK);
		clearBuku.setBackground(Color.WHITE);
		clearBuku.setActionCommand("Clear");
		clearBuku.setFont(new Font("Times New Roman", Font.BOLD, 12));
		clearBuku.setBounds(355, 67, 75, 23);
		panelInput.add(clearBuku);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/library.jpg")));
		lblNewLabel_7.setBounds(0, 0, 453, 468);
		panelInput.add(lblNewLabel_7);
		
		JPanel panelPinjam = new JPanel();
		panelPinjam.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, panelPinjam, null);
		panelPinjam.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(255, 255, 255));
		panel_2_1.setBounds(459, 0, 500, 468);
		panelPinjam.add(panel_2_1);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Masukkan ID Peminjam");
		lblNewLabel_2_4_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2_4_1.setBounds(21, 23, 138, 30);
		panel_2_1.add(lblNewLabel_2_4_1);
		
		cariIdPinjam = new JTextField();
		cariIdPinjam.setFont(new Font("Tahoma", Font.BOLD, 14));
		cariIdPinjam.setColumns(10);
		cariIdPinjam.setBounds(158, 22, 144, 30);
		panel_2_1.add(cariIdPinjam);
		
		JButton cariPinjam = new JButton("CARI");
		
		//fungsi untuk cari data peminjam dan ditampilkan pada text area
		//disini mengimplementasikan struktur data array
		//dan data dari array akan ditampilkan pada text area
		cariPinjam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String quer = "select * from pinjam join buku on buku.idbuku=pinjam.idbuku where idpinjam='"+String.valueOf(cariIdPinjam.getText())+"'";
					Connection conn = (Connection)JDBC.koneksi();
					PreparedStatement st = conn.prepareStatement(quer);
					ResultSet rs = st.executeQuery();
					
					String [] data = new String[8];
					
					if (rs.next()) {
						data[0] = ("Nama Peminjam: "+rs.getString("nama"));
						data[1] = ("\nNIK Peminjam\t: "+rs.getString("nik"));
						data[2] = ("\nNomor HP\t: "+rs.getString("nohp"));
						data[3] = ("\nID Buku\t: "+rs.getString("idbuku"));
						data[4] = ("\nJudul Buku\t: "+rs.getString("judul"));
						data[5] = ("\nPenerbit Buku\t: "+rs.getString("penerbit"));
						data[6] = ("\nTahun Terbit\t: "+rs.getString("tahun"));
						data[7] = ("\nStatus Buku\t: "+rs.getString("status"));
						
						textAreaPinjam.setText("Data ditemukan....\n"+data[0]+data[1]+data[2]+data[3]+data[4]+data[5]+data[6]+data[7]);
						
						if (conn != null) {
	        	        	try {
	        	        		conn.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (st != null) {
	        	        	try {
	        	        		st.close();
	        				} catch (SQLException e5) {}
	        	        }
	        		    if (rs != null) {
	        	        	try {
	        	        		rs.close();
	        				} catch (SQLException e4) {}
	        	        }
					}
				} catch (Exception e2) {
					
				}
			}
		});
		cariPinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cariPinjam.setBackground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cariPinjam.setForeground(Color.BLACK);
				cariPinjam.setBackground(new Color(224, 255, 255));
			}
		});
		cariPinjam.setForeground(Color.BLACK);
		cariPinjam.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cariPinjam.setFocusable(false);
		cariPinjam.setBackground(Color.WHITE);
		cariPinjam.setActionCommand("Clear");
		cariPinjam.setBounds(312, 22, 83, 30);
		panel_2_1.add(cariPinjam);
		
		//fungsi tombol clear deskripsi
		JButton clearDescPin = new JButton("CLEAR");
		clearDescPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cariIdPinjam.setText("");
				textAreaPinjam.setText("");
			}
		});
		clearDescPin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clearDescPin.setBackground(Color.ORANGE);
				clearDescPin.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				clearDescPin.setForeground(Color.BLACK);
				clearDescPin.setBackground(new Color(224, 255, 255));
			}
		});
		clearDescPin.setForeground(Color.BLACK);
		clearDescPin.setFont(new Font("Times New Roman", Font.BOLD, 12));
		clearDescPin.setFocusable(false);
		clearDescPin.setBackground(Color.WHITE);
		clearDescPin.setActionCommand("Clear");
		clearDescPin.setBounds(405, 22, 75, 30);
		panel_2_1.add(clearDescPin);
		
		JLabel lblNewLabel_2_3_1_1_1 = new JLabel("Deskripsi Peminjam");
		lblNewLabel_2_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblNewLabel_2_3_1_1_1.setBounds(158, 75, 144, 30);
		panel_2_1.add(lblNewLabel_2_3_1_1_1);
		
		JScrollPane scrollPanePinjam = new JScrollPane();
		scrollPanePinjam.setBounds(10, 260, 470, 190);
		panel_2_1.add(scrollPanePinjam);
		
		tablePinjam = new JTable();
		tablePinjam.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Peminjam", "Nama Peminjam", "NIK", "Nomor HP", "ID Buku"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPanePinjam.setViewportView(tablePinjam);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(104, 105, 291, 143);
		panel_2_1.add(scrollPane_2);
		
		textAreaPinjam = new JTextArea();
		textAreaPinjam.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textAreaPinjam.setEditable(false);
		scrollPane_2.setViewportView(textAreaPinjam);
		
		JLabel lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/library.jpg")));
		lblNewLabel_8_1.setBounds(4, -28, 671, 496);
		panel_2_1.add(lblNewLabel_8_1);
		
		cariIdSedia = new JTextField();
		
		//agar hanya bisa mengisi angka
		cariIdSedia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
		cariIdSedia.setFont(new Font("Tahoma", Font.BOLD, 14));
		cariIdSedia.setColumns(10);
		cariIdSedia.setBounds(169, 112, 144, 30);
		panelPinjam.add(cariIdSedia);
		
		cekSedia = new JButton("CEK");
		
		//fungsi untuk mengecek ketersediaan buku sebelum meminjam -tombol cek
		//disini mengimplementasikan struktur data array
		//dan data dari array akan ditampilkan ke text area
		cekSedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFunction sf = new searchFunction();
				ResultSet rs = null;
				
				rs = sf.find(cariIdSedia.getText());
				
				String [] sedia = new String[5];
				try {
					if (rs.next()) {
						sedia[0] = rs.getString("idbuku");
						sedia[1] = rs.getString("judul");
						sedia[2] = rs.getString("penerbit");
						sedia[3] = rs.getString("tahun");
						sedia[4] = rs.getString("status");
						
						if (rs.getString("status").equals("TERSEDIA") || !(rs.getString("status").equals("DIPINJAM"))) {
							JOptionPane.showMessageDialog(cariIdSedia, "BUKU TERSEDIA");
							cariIdSedia.setEditable(false);
							idpinjam.setEditable(true);
							namaPinjam.setEditable(true);
							nik.setEditable(true);
							nohp.setEditable(true);
							btnPinjam.setEnabled(true);
						} else if (!(rs.getString("status").equals("TERSEDIA")) || rs.getString("status").equals("DIPINJAM")) {
							JOptionPane.showMessageDialog(cariIdSedia, "BUKU SUDAH DIPINJAM!!!!");
							cariIdSedia.setText("");
						} else {
							JOptionPane.showMessageDialog(cariIdSedia, "DATA BUKU TIDAK DITEMUKAN!");
							cariIdSedia.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(cariIdSedia, "DATA BUKU TIDAK DITEMUKAN!");
						cariIdSedia.setText("");
						
	        		    if (rs != null) {
	        	        	try {
	        	        		rs.close();
	        				} catch (SQLException e5) {}
	        	        }
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					cariIdBuku.setText("");
					textAreaBuku.setText("");
				  }
			}
		});
		cekSedia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cekSedia.setBackground(Color.BLUE);
				cekSedia.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cekSedia.setForeground(Color.BLACK);
				cekSedia.setBackground(new Color(224, 255, 255));
			}
		});
		cekSedia.setForeground(Color.BLACK);
		cekSedia.setFont(new Font("Times New Roman", Font.BOLD, 12));
		cekSedia.setFocusable(false);
		cekSedia.setBackground(Color.WHITE);
		cekSedia.setActionCommand("Clear");
		cekSedia.setBounds(323, 112, 109, 30);
		panelPinjam.add(cekSedia);
		
		JLabel lblNewLabel_2_4_1_1 = new JLabel("Masukkan ID Buku");
		lblNewLabel_2_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2_4_1_1.setBounds(32, 113, 138, 30);
		panelPinjam.add(lblNewLabel_2_4_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(139, 24, 189, 42);
		panelPinjam.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Peminjaman Buku");
		lblNewLabel_3.setBounds(0, 0, 189, 42);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 21));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(160, 82, 45));
		panel_4.setBounds(20, 92, 429, 70);
		panelPinjam.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2_5 = new JLabel("ID Peminjaman");
		lblNewLabel_2_5.setForeground(Color.WHITE);
		lblNewLabel_2_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_5.setBounds(38, 189, 109, 30);
		panelPinjam.add(lblNewLabel_2_5);
		
		idpinjam = new JTextField();
		idpinjam.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
					e.consume();
				}
			}
		});
		idpinjam.setEditable(false);
		idpinjam.setFont(new Font("Tahoma", Font.BOLD, 14));
		idpinjam.setColumns(10);
		idpinjam.setBounds(198, 189, 234, 30);
		panelPinjam.add(idpinjam);
		
		JLabel lblNewLabel_2_6 = new JLabel("Nama Peminjam");
		lblNewLabel_2_6.setForeground(Color.WHITE);
		lblNewLabel_2_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_6.setBounds(38, 241, 119, 30);
		panelPinjam.add(lblNewLabel_2_6);
		
		namaPinjam = new JTextField();
		namaPinjam.setEditable(false);
		namaPinjam.setFont(new Font("Tahoma", Font.BOLD, 14));
		namaPinjam.setColumns(10);
		namaPinjam.setBounds(198, 241, 234, 30);
		panelPinjam.add(namaPinjam);
		
		JLabel lblNewLabel_2_7 = new JLabel("NIK");
		lblNewLabel_2_7.setForeground(Color.WHITE);
		lblNewLabel_2_7.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_7.setBounds(38, 294, 75, 30);
		panelPinjam.add(lblNewLabel_2_7);
		
		nik = new JTextField();
		
		//agaar hanya bisa mengisi angka
		nik.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
				    e.consume();
				}
			}
		});
		nik.setEditable(false);
		nik.setFont(new Font("Tahoma", Font.BOLD, 14));
		nik.setColumns(10);
		nik.setBounds(198, 294, 234, 30);
		panelPinjam.add(nik);
		
		JLabel lblNewLabel_2_8 = new JLabel("Nomor HP");
		lblNewLabel_2_8.setForeground(Color.WHITE);
		lblNewLabel_2_8.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_8.setBounds(38, 347, 75, 30);
		panelPinjam.add(lblNewLabel_2_8);
		
		nohp = new JTextField();
		
		//agar hanya bisa mengisi angka
		nohp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
				    e.consume();
				}
			}
		});
		nohp.setEditable(false);
		nohp.setFont(new Font("Tahoma", Font.BOLD, 14));
		nohp.setColumns(10);
		nohp.setBounds(198, 347, 234, 30);
		panelPinjam.add(nohp);
		
		btnPinjam = new JButton("PINJAM");
		
		//fungsi untuk tombol pinjam, maka buku otomatis berstatus dipinjam
		btnPinjam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "insert into pinjam values('"+String.valueOf(idpinjam.getText())+"','"+namaPinjam.getText()+"','"+nik.getText()+"','"+nohp.getText()+"','"+Integer.parseInt(cariIdSedia.getText())+"')";
					Connection con = (Connection)JDBC.koneksi();
					PreparedStatement pst = con.prepareStatement(sql);
					
					String que = "update buku set status='DIPINJAM' where idbuku='"+Integer.parseInt(cariIdSedia.getText())+"'";
					Connection conn = (Connection)JDBC.koneksi();
					PreparedStatement pstm = conn.prepareStatement(que);
					if (idpinjam.getText().equals("")) {
						JOptionPane.showMessageDialog(btnPinjam, "ID Buku tidak boleh kosong!");
					} else if (namaPinjam.getText().equals("")) {
						JOptionPane.showMessageDialog(btnPinjam, "Judul Buku tidak boleh kosong!");
					} else if (nik.getText().equals("")) {
						JOptionPane.showMessageDialog(btnPinjam, "Penerbit tidak boleh kosong!");
					} else if (nohp.getText().equals("")) {
						JOptionPane.showMessageDialog(btnPinjam, "Harga Buku tidak boleh kosong!");
					} else if (cariIdSedia.getText().equals("")) {
						JOptionPane.showMessageDialog(btnPinjam, "Stok tidak boleh kosong!");
					} else {
						pst.execute();
						JOptionPane.showMessageDialog(btnPinjam, "Data Peminjam Tersimpan");
						pstm.executeUpdate();
						showDataPinjam();
						showData();
						cariIdSedia.setText("");
						idpinjam.setText("");
						namaPinjam.setText("");
						nik.setText("");
						nohp.setText("");
						cariIdSedia.setEditable(true);
						idpinjam.setEditable(false);
						namaPinjam.setEditable(false);
						nik.setEditable(false);
						nohp.setEditable(false);
						btnPinjam.setEnabled(false);
						
						if (con != null) {
	        	        	try {
	        	        		con.close();
	        				} catch (SQLException e4) {}
	        	        }
	        		 
	        		    if (pst != null) {
	        	        	try {
	        	        		pst.close();
	        				} catch (SQLException e5) {}
	        	        }
					} 
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnPinjam, "Gagal simpan data", "KESALAHAN INPUT!!", 0);
					System.out.println(e1);
				}
			}
		});
		btnPinjam.setEnabled(false);
		btnPinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPinjam.setBackground(new Color(50,205,50));
				btnPinjam.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPinjam.setForeground(Color.BLACK);
				btnPinjam.setBackground(new Color(224, 255, 255));
			}
		});
		btnPinjam.setForeground(Color.BLACK);
		btnPinjam.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPinjam.setFocusable(false);
		btnPinjam.setBackground(Color.WHITE);
		btnPinjam.setActionCommand("");
		btnPinjam.setBounds(294, 399, 138, 42);
		panelPinjam.add(btnPinjam);
		
		JButton resetPinjam = new JButton("Reset");
		
		//fungsi tombol reset pada bagian peminjaman
		resetPinjam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cariIdSedia.setEditable(true);
				idpinjam.setEditable(false);
				namaPinjam.setEditable(false);
				nik.setEditable(false);
				nohp.setEditable(false);
				nohp.setText("");
				cariIdSedia.setText("");
				idpinjam.setText("");
				nik.setText("");
			}
		});
		resetPinjam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				resetPinjam.setBackground(Color.MAGENTA);
				resetPinjam.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				resetPinjam.setForeground(Color.BLACK);
				resetPinjam.setBackground(new Color(224, 255, 255));
			}
		});
		resetPinjam.setForeground(Color.BLACK);
		resetPinjam.setFont(new Font("Times New Roman", Font.BOLD, 12));
		resetPinjam.setFocusable(false);
		resetPinjam.setBackground(Color.WHITE);
		resetPinjam.setActionCommand("Clear");
		resetPinjam.setBounds(374, 58, 75, 23);
		panelPinjam.add(resetPinjam);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/library.jpg")));
		lblNewLabel_8.setBounds(0, 2, 455, 468);
		panelPinjam.add(lblNewLabel_8);
		
		JPanel panelKembali = new JPanel();
		panelKembali.setBackground(new Color(0, 128, 128));
		tabbedPane.addTab("New tab", null, panelKembali, null);
		panelKembali.setLayout(null);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(490, 142, 152, 35);
		panelKembali.add(txtID);
		
		JLabel lblNewLabel_5_1 = new JLabel("ID Buku");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5_1.setBounds(419, 142, 70, 35);
		panelKembali.add(lblNewLabel_5_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(342, 32, 271, 56);
		panelKembali.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("PENGEMBALIAN BUKU");
		lblNewLabel_4.setBounds(0, 0, 271, 56);
		panel_5.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel_4.setForeground(new Color(0, 128, 128));
		
		JLabel lblNewLabel_5 = new JLabel("Masukkan ID Peminjam");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5.setBounds(212, 142, 181, 35);
		panelKembali.add(lblNewLabel_5);
		
		txtKembali = new JTextField();
		
		//agar hanya bisa mengisi angka saja
		txtKembali.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakter = e.getKeyChar();
				if (!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))) {
				    e.consume();
				}
			}
		});
		txtKembali.setBounds(212, 188, 160, 35);
		panelKembali.add(txtKembali);
		txtKembali.setColumns(10);
		
		JButton btnCekDulu = new JButton("CEK DULU");
		
		//fungsi tombol cek dulu data peminjam sebelum konfirmasi pengembalian buku
		//hanya untuk memastikan datanya ada atau tidak
		//disini mengimplementasikan struktur data array untuk menampung data dari database 
		//dan dari array tsb akan ditampilkan pada text area
		btnCekDulu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String quer = "select * from pinjam join buku on buku.idbuku=pinjam.idbuku where idpinjam='"+String.valueOf(txtKembali.getText())+"'";
					Connection conn = (Connection)JDBC.koneksi();
					PreparedStatement st = conn.prepareStatement(quer);
					ResultSet rs = st.executeQuery();
					
					String [] data = new String[8];
					
					if (rs.next()) {
						data[0] = ("Nama Peminjam: "+rs.getString("nama"));
						data[1] = ("\nNIK Peminjam\t: "+rs.getString("nik"));
						data[2] = ("\nNomor HP\t: "+rs.getString("nohp"));
						data[3] = rs.getString("idbuku");
						data[4] = ("\nJudul Buku\t: "+rs.getString("judul"));
						data[5] = ("\nPenerbit Buku\t: "+rs.getString("penerbit"));
						data[6] = ("\nTahun Terbit\t: "+rs.getString("tahun"));
						data[7] = ("\nStatus Buku\t: "+rs.getString("status"));
						
						txtID.setText(data[3]);
						textAreaKembali.setText("Data peminjaman buku....\n\nDengan rincian:\n\n"+data[0]+data[1]+data[2]+data[4]+data[5]+data[6]+data[7]+"\n\nKonfirmasi pengembalian???");
						konfirmasi.setEnabled(true);
					}
					
					if (conn != null) {
        	        	try {
        	        		conn.close();
        				} catch (SQLException e4) {}
        	        }
        		 
        		    if (st != null) {
        	        	try {
        	        		st.close();
        				} catch (SQLException e5) {}
        	        }
        		    
        		    if (rs != null) {
        	        	try {
        	        		rs.close();
        				} catch (SQLException e4) {}
        	        }
				} catch (Exception e2) {
					
				}
				
			}
		});
		btnCekDulu.setFocusable(false);
		btnCekDulu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCekDulu.setBackground(Color.BLUE);
				btnCekDulu.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCekDulu.setForeground(Color.BLACK);
				btnCekDulu.setBackground(new Color(224, 255, 255));
			}
		});
		btnCekDulu.setForeground(new Color(0, 0, 0));
		btnCekDulu.setBackground(new Color(255, 255, 255));
		btnCekDulu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnCekDulu.setBounds(212, 244, 160, 47);
		panelKembali.add(btnCekDulu);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(419, 182, 307, 219);
		panelKembali.add(scrollPane_3);
		
		textAreaKembali = new JTextArea();
		scrollPane_3.setViewportView(textAreaKembali);
		textAreaKembali.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		konfirmasi = new JButton("KONFIRMASI PENGEMBALIAN");
		konfirmasi.setEnabled(false);
		
		//fungsi untuk tombol konfirmasi pengembalian, ketika diklik akan mengupdate data buku menjadi tersedia
		//dan menghapus data peminjam
		konfirmasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(hapusBuku, "Konfirmasi pengembalian?", "Peringatan!!!.", JOptionPane.YES_NO_OPTION);
				try { 
					String update = "update buku set status='TERSEDIA' where idbuku='"+txtID.getText()+"'";
					Connection conne = (Connection)JDBC.koneksi();
					PreparedStatement stm = conne.prepareStatement(update);
						
					String delete = "delete from pinjam where idpinjam='"+txtKembali.getText()+"'";
					Connection connect = (Connection)JDBC.koneksi();
					PreparedStatement pstm = connect.prepareStatement(delete);
					if (confirm == 0) {
						stm.executeUpdate();
						pstm.executeUpdate();
						JOptionPane.showMessageDialog(textAreaKembali, "Data buku dan peminjaman di update");
						showData();
						showDataPinjam();
						txtID.setText("");
						txtKembali.setText("");
						textAreaKembali.setText("");
						konfirmasi.setEnabled(false);
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		konfirmasi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				konfirmasi.setBackground(Color.BLUE);
				konfirmasi.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				konfirmasi.setForeground(Color.BLACK);
				konfirmasi.setBackground(new Color(224, 255, 255));
			}
		});
		konfirmasi.setFocusable(false);
		konfirmasi.setForeground(Color.BLACK);
		konfirmasi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		konfirmasi.setBackground(Color.WHITE);
		konfirmasi.setBounds(445, 409, 250, 35);
		panelKembali.add(konfirmasi);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(PerpusGUI.class.getResource("/assets/library.jpg")));
		lblNewLabel_6.setBounds(0, 0, 959, 468);
		panelKembali.add(lblNewLabel_6);
	}
}
