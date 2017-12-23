package app.aygazprojesi;

/**
 * Created by Omer on 4.5.2017.
 */

public class UrunBilgileri {

    private String urunIsmi;
    private String urunBilgisi;
    private int urunFiyati;
    static int kucukTupSayisi=0;
    static int tombulEvTupuSayisi=0;
    static int uzunEvTupuSayisi=0;
    static int ticariTupSayisi=0;
    static int sanayiTupSayisi=0;

    public String getUrunIsmi() {
        return urunIsmi;
    }

    public String getUrunBilgisi() {
        return urunBilgisi;
    }

    public int getUrunFiyati() {
        return urunFiyati;
    }

    public UrunBilgileri(String urunIsmi){
        this.urunIsmi = urunIsmi;
        if(urunIsmi.equals("KÜÇÜK TÜP")){
            urunBilgisi = "KÜÇÜK TÜP\n" +
                    "2 Kg LPG içermektedir.\n" +
                    "Kolay taşınabilir yapısıyla pratiktir. Açık alanlarda ve evde rahatlıkla kullanılabilir." +
                    "Bu küçük tüp, dar ve geniş çemberli olmak üzere iki farklı seçenek ile sunulmaktadır.";
            urunFiyati = 15;
        }
        else if (urunIsmi.equals("EV TÜPÜ")){
            urunBilgisi ="TOMBUL EV TÜPÜ \n 12 Kg'lık Ev Tüpüdür. \n" +
                    "Tüplerin tümü, Aygaz tesislerinde doldurulduklarının, tüm testlerden geçtiklerinin " +
                    "ve ilk defa kullanıcı tarafından açıldıklarının garantisi olan hologram kapak ile " +
                    "tüketicilere ulaştırılmaktadır.";
            urunFiyati= 80;
        }
        else if (urunIsmi.equals("UZUN EV TÜPÜ")){
            urunBilgisi ="UZUN EV TÜPÜ \n 12 Kg'lık Ev Tüpüdür. \n"+
            "Kullanıcılar tarafından kullanılan tombul ev tüpünden özellik olarak tek farkı dar alanlarda" +
                    "kullanılmak üzere kullanıcılara sunulmasıdır.\n"
            +"Ayrıca tüplerin tümü, Aygaz tesislerinde doldurulduklarının, tüm testlerden geçtiklerinin " +
                    "ve ilk defa kullanıcı tarafından açıldıklarının garantisi olan hologram kapak ile " +
                    "tüketicilere ulaştırılmaktadır.";
            urunFiyati= 80;
        }
        else if (urunIsmi.equals("TİCARİ TÜP")){
            urunBilgisi ="TİCARİ TÜP \n" +
                    "24 Kg LPG içermektedir.\n" +
                    "Bu tür tüplerin evlere oranla işyerlerinde daha çok kullanılmaktadır." +
                    "Kullanıldığı yere uyum sağlaması için 300 mm çaplı uzun ve 360 mm tombul tüp seçenekleri" +
                    " mevcuttur.";
            urunFiyati= 170;
        }
        else if (urunIsmi.equals("SANAYİ TÜPÜ")){
            urunBilgisi ="SANAYİ TÜPÜ\n" +
                    "45 Kg LPG içermektedir.\n" +
                    "LPG tüketiminin yüksek olduğu ticari ve sanayi kuruluşlarında kullanılır. Miks ve propan" +
                    "olmak üzere iki ayrı çeşidi vardır.\n" +
                    "Miks %70 bütan ve %30 propandan oluşmaktadır." +
                    "\nPropan türünde ise sadece propan gazı bulunmaktadır.";
            urunFiyati= 310;
        }

    }
}
