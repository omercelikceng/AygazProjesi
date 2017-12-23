package app.aygazprojesi;

/**
 * Created by Omer on 11.5.2017.
 */

public class YetkiliSiparisAcilis {
    private String adSoyad;
    private String tarih;

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public YetkiliSiparisAcilis(String adSoyad, String tarih) {
        this.adSoyad = adSoyad;
        this.tarih = tarih;

    }
}
