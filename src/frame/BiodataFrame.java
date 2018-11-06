
package frame;
import db.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Biodata;


public class BiodataFrame extends JFrame{
    JLabel jLabel1 =new JLabel("Cari");
    JTextField eCari =new JTextField();
    JButton bCari =new JButton("Cari");
    
    String header []={"id","Nik","Nama","Tempat Lahir","Tanggal Lahir","Agama","Jenis Kelamin","Alamat"};
    TableModel tableModel = new DefaultTableModel(header, 0);
    JTable tBiodata = new JTable(tableModel);
    JScrollPane jScrollPane = new JScrollPane(tBiodata);
    
    JButton bTambah =new JButton("ADD");
    JButton bUbah =new JButton("Update");
    JButton bHapus =new JButton("Delete");
    JButton bBatal =new JButton("Cancel");
    JButton bKeluar =new JButton("Exit");
 
 
 Biodata biodata;
    

    public void setKomponen () {

getContentPane().setLayout(null);
getContentPane().add (jLabel1);
getContentPane().add (eCari);
getContentPane().add (jScrollPane);
getContentPane().add (bCari);
getContentPane().add (bTambah);
getContentPane().add (bUbah);
getContentPane().add (bHapus);
getContentPane().add (bBatal);
getContentPane().add (bKeluar);

jLabel1.setBounds(10,10,100,25);
eCari.setBounds(60,10,300,25);
bCari.setBounds(400,10,70,25);
bKeluar.setBounds(500,220,70,25);
bTambah.setBounds(10,220,80,25);
bUbah.setBounds(100,220,80,25);
bHapus.setBounds(195,220,80,25);
bBatal.setBounds(295,220,80,25);
jScrollPane.setBounds(10,45,760,160);
resetTable("");
setListener();
setVisible (true);

}
    public BiodataFrame () {
    setSize (800,500);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setKomponen();


}
    public static void main(String[] args) {
        BiodataFrame biodataFrame =new BiodataFrame();
    
    }
    
     public ArrayList<Biodata> getBiodataList (String keyword) {
ArrayList<Biodata> biodataList =new ArrayList<>();
    Koneksi koneksi =new Koneksi();
    Connection connection = koneksi.getConnection();
    
    String query ="SELECT * FROM tb_biodata  "+keyword;
    Statement statement;
    ResultSet resultSet;
    
    try {
    statement = connection.createStatement();
     resultSet = statement.executeQuery(query);
    while (resultSet.next()){
            biodata = new Biodata (resultSet.getInt("id"),
                    resultSet.getString("nik"),resultSet.getString("nama"),resultSet.getString("tempat_lhr")
            ,resultSet.getString("tgl_lahir"),resultSet.getString("agama"),resultSet.getString("jenis_k")
            ,resultSet.getString("alamat"));
            
            biodataList.add(biodata);
    }
    
    }
    catch (SQLException | NullPointerException ex){
        System.err.println("Koneksi Null Gagal : "+ex);
    
    }
return biodataList;

}
     
     public  final  void selectBiodata (String keyword)  {
        ArrayList<Biodata> list = getBiodataList(keyword);
        DefaultTableModel model = (DefaultTableModel)tBiodata.getModel();
        Object [] row = new Object[8];
        
        for (int i = 0; i < list.size();i++){
            row [0] = list.get(i).getId();
            row [1] = list.get(i).getNik();
            row [2] = list.get(i).getNama();
            row [3] = list.get(i).getTempat_lhr();
            row [4] = list.get(i).getTgl_lahir();
            row [5] = list.get(i).getAgama();
            row [6] = list.get(i).getJenis_k();
            row [7] = list.get(i).getAlamat();
            
            model.addRow(row);
        }
    }
     public  final void resetTable(String keyword) 
    {
        DefaultTableModel model = (DefaultTableModel)tBiodata.getModel();
        model.setRowCount(0);
        selectBiodata(keyword);
    
    }
     public  void setListener (){
        bKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose () ;
            }
        });
        bCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTable(" WHERE nama like '% "+eCari.getText()+"%'");
            }
        });
    bBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTable("");
            }
        });
    bHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tBiodata.getSelectedRow();
                int pilihan = JOptionPane.showConfirmDialog(null, 
                        "Yakin ingin Menghapus   ?",
                        "Konfirmasi Hapus ",
                        JOptionPane.YES_NO_OPTION);
              if (pilihan==0){
                  if(i==0){
                      try {
                          TableModel model =tBiodata.getModel();
                          Koneksi koneksi = new Koneksi();
                          Connection con = koneksi.getConnection();
                          String executeQuery = "delete from tb_biodata where nik = ?";
                          PreparedStatement ps = con.prepareStatement(executeQuery);
                          ps.setString (1, model.getValueAt(i, 1).toString());
                          ps.executeUpdate();
                      
                      }
                  catch (SQLException ex){
                      System.err.println(ex);
                  
                  }
                  } else {
                      JOptionPane.showMessageDialog(null, "Pilih Data yang ingin dihapus ");
                  
                  }
              
              }
                resetTable("");
            }
        });
    bTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TambahBiodataFrame biodataTambahFrame = new TambahBiodataFrame();
            }
        });
    addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowActivated (java.awt.event.WindowEvent evt){
                    resetTable("");
                    
                }
        
        
        });
        bUbah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tBiodata.getSelectedRow();
                if (i>=0){
                    TableModel model = tBiodata.getModel();
                    biodata = new Biodata();
                    biodata.setId(Integer.parseInt(model.getValueAt(i,0).toString() ));
                    biodata.setNik(model.getValueAt(i, 1).toString());
                    biodata.setNama(model.getValueAt(i, 2).toString());
                    biodata.setAlamat(model.getValueAt(i, 7).toString());
                    
                    
                    TambahBiodataFrame tambahBiodataFrame = new TambahBiodataFrame(biodata);
                     tambahBiodataFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih Data yang ingin Dirubah");
                
                }
            }
        });
    
    
     }
     


}