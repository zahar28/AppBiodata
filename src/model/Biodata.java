
package model;


public class Biodata {
    private int id ;
    private String nik;
     private String nama;
      private String tempat_lhr;
       private String tgl_lahir;
        private String agama;
         private String jenis_k;
          private String alamat;

    public Biodata() {
    
    
    }

          
    public Biodata(int id, String nik, String nama, String tempat_lhr, String tgl_lahir, String agama, String jenis_k, String alamat) {
        this.id = id;
        this.nik = nik;
        this.nama = nama;
        this.tempat_lhr = tempat_lhr;
        this.tgl_lahir = tgl_lahir;
        this.agama = agama;
        this.jenis_k = jenis_k;
        this.alamat = alamat;
    }

    public Biodata(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Biodata(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lhr() {
        return tempat_lhr;
    }

    public void setTempat_lhr(String tempat_lhr) {
        this.tempat_lhr = tempat_lhr;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getJenis_k() {
        return jenis_k;
    }

    public void setJenis_k(String jenis_k) {
        this.jenis_k = jenis_k;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
          
}
