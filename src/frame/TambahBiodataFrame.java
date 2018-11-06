
package frame;
import com.mysql.jdbc.PreparedStatement;
import db.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.freixas.jcalendar.JCalendarCombo;

import model.Biodata;


public class TambahBiodataFrame extends JFrame{
    
    int status;
    
    private final int SEDANG_TAMBAH = 101;
    private final int SEDANG_UBAH = 102;
    
    JLabel jLabel1 = new JLabel("id");
    JLabel jLabel2 = new JLabel("Nik");
    JLabel jLabel3 = new JLabel("Nama");
    JLabel jLabel4 = new JLabel("Tempat Lahir");
    JLabel jLabel5 = new JLabel("Tanggal Lahir");
    JLabel jLabel6 = new JLabel("Agama");
    JLabel jLabel7 = new JLabel("Jenis Kelamin");
    JLabel jLabel8 = new JLabel("Alamat");
    
    JTextField eid = new JTextField();
    JTextField enik = new JTextField();
    JTextField enama = new JTextField();
    JTextField etempat_lhr = new JTextField();
    JTextField etgl_lahir = new JTextField();
        
     String[] ja={"Pilih","Islam","Kristen","Budha","Hindu","Keharingan"};
     JComboBox cbja=new JComboBox(ja);
 
    
    JRadioButton lk=new JRadioButton("Laki-laki");
    JRadioButton pr=new JRadioButton("Perempuan");
    ButtonGroup grupjk=new ButtonGroup();
 
    JTextField ealamat = new JTextField();
    JButton bSimpan = new JButton("Save");
    JButton bBatal = new JButton ("Cancel");
    
    public void setKomponen (){
    getContentPane().setLayout(null);
    getContentPane().add (jLabel1);
    getContentPane().add (jLabel2);
    getContentPane().add (jLabel3);
    getContentPane().add (jLabel4);
    getContentPane().add (jLabel5);
    getContentPane().add (jLabel6);
    getContentPane().add (jLabel7);
    getContentPane().add (jLabel8);
    getContentPane().add (eid);
    getContentPane().add (enik);
    getContentPane().add (enama);
    getContentPane().add (etempat_lhr);
    getContentPane().add (etgl_lahir);
    getContentPane().add (ealamat);
    getContentPane().add (bSimpan);
    getContentPane().add (bBatal);
    
    jLabel1.setBounds(30,10,50,25);
    jLabel2.setBounds(30,40,50,25);
    jLabel3.setBounds(30,70,110,25);
    jLabel4.setBounds(30,100,110,25);
    jLabel5.setBounds(30,130,110,25);
    jLabel6.setBounds(30,170,110,25);
    jLabel7.setBounds(30,200,110,25);
    jLabel8.setBounds(30,230,110,25);
    
    eid.setBounds(160,10,50,25);
    enik.setBounds(160,40,270,25);
    enama.setBounds(160,70,270,25);
    etempat_lhr.setBounds(160,100,270,25);
    etgl_lahir.setBounds(160,130,270,25);
    cbja.setBounds(160,170,100,20);
    getContentPane().add(cbja);
    cbja.setBounds(160,170,100,20);
    
    getContentPane().add(lk);
    lk.setBounds(160,200,80,20);
    getContentPane().add(pr);
    pr.setBounds(260,200,130,20);
    grupjk.add(lk);
    grupjk.add(pr);
    ealamat.setBounds(160,230,270,25);
    
    bSimpan.setBounds(160,260,100,25);
    bBatal.setBounds(270,260,100,25);
    
    eid.setEditable(false);
    setVisible(true);
    enik.requestFocus();
    setListener();







}

    public TambahBiodataFrame() {
        status = SEDANG_TAMBAH;
    setSize (800,400);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setKomponen();
    }
    public TambahBiodataFrame(Biodata biodata) {
    status = SEDANG_UBAH;
    setSize (800,400);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    eid.setText(String.valueOf(biodata.getId()));
    enik.setText(biodata.getNik());
    enama.setText(biodata.getNama());
    etempat_lhr.setText(biodata.getTempat_lhr());
    etgl_lahir.setText(biodata.getTgl_lahir());
    ealamat.setText(biodata.getAlamat());
    setKomponen();
    
    }
    public void setListener (){
        bBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            
            }
        });
    
    bSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                
                try{
                    Koneksi koneksi = new Koneksi();
                    Connection con = koneksi.getConnection();
                    PreparedStatement ps;
                    if (status== SEDANG_TAMBAH){
                        String executeQuery = "insert into tb_biodata "+
                                " (nik,nama,tempat_lhr,tgl_lahir,agama,jenis_k,alamat) value (?,?,?,?,?,?,?)";;
                        ps = (PreparedStatement) con.prepareStatement(executeQuery);
                        ps.setString(1, enik.getText());
                        ps.setString(2, enama.getText());
                        ps.setString(3, etempat_lhr.getText());
                        ps.setString(4, etgl_lahir.getText());
                        ps.setString(5, (String) cbja.getSelectedItem());
                        if (lk.isSelected()==true){
                                ps.setString(6, lk.getText());
                            
                        }else{  
                                ps.setString(6, pr.getText());
                        }
                        ps.setString(7, ealamat.getText());
}
                    else {
                        String executeQuery = "update tb_biodata set"
                                +"nik=?,nama=?,tempat_lhr =?,tgl_lahir=?,agama=?,jenis_k=?,alamat=? where id =?";
                        ps= (PreparedStatement) con.prepareStatement(executeQuery);
                       
                        ps.setString(1, enik.getText());
                        ps.setString(2, enama.getText());
                        ps.setString(3, etempat_lhr.getText());
                        ps.setString(4, etgl_lahir.getText());
                        ps.setString(5, (String) cbja.getSelectedItem());
                        if (lk.isSelected()==true){
                                ps.setString(6, lk.getText());
                            
                        }else{  
                                ps.setString(6, pr.getText());
                        }
                        ps.setString(7, ealamat.getText());
                       
                        
                        
                    }
                ps.executeUpdate();
                }
                catch (SQLException ex){
                System.err.println(ex);
            }
            dispose();
            }
    });
    }

}
            
            
                        
   
    

