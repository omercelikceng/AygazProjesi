package app.aygazprojesi;

/**
 * Created by Omer on 10.5.2017.
 */

public class YetkiliSiparisBilgileri {
    private String siparis;
    private String adres;
    private String adSoyad;
    private String tel;
    private String tarih;

    public YetkiliSiparisBilgileri(String siparis, String adres, String adSoyad, String tel,String tarih) {
        this.siparis = siparis;
        this.adres = adres;
        this.tarih = tarih;
        this.adSoyad = adSoyad;
        this.tel = tel;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSiparis() {
        return siparis;
    }

    public void setSiparis(String siparis) {
        this.siparis = siparis;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
