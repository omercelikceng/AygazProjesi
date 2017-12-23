package app.aygazprojesi;

/**
 * Created by Omer on 29.4.2017.
 */

public class KullaniciBilgileri {

    public String adSoyad;
    public String adres;
    public String tel;
    public boolean yetkiliMi=false;

    public boolean isYetkiliMi() {
        return yetkiliMi;
    }

    public void setYetkiliMi(boolean yetkiliMi) {
        this.yetkiliMi = yetkiliMi;
    }

    public KullaniciBilgileri(){}

    public KullaniciBilgileri(String adSoyad, String adres, String tel,boolean yetki) {
        this.adSoyad = adSoyad;
        this.adres = adres;
        this.tel = tel;
        yetkiliMi=yetki;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getAdres() {
        return adres;
    }

    public String getTel() {
        return tel;
    }
}
