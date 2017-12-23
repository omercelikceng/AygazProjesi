package app.aygazprojesi;

/**
 * Created by Omer on 9.5.2017.
 */

public class SepetBilgileri {
    private int kucukTup;
    private int evTupu;
    private int uzunEvTupu;
    private int ticariTup;
    private int sanayiTupu;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKucukTup() {
        return kucukTup;
    }

    public void setKucukTup(int kucukTup) {
        this.kucukTup = kucukTup;
    }

    public int getEvTupu() {
        return evTupu;
    }

    public void setEvTupu(int evTupu) {
        this.evTupu = evTupu;
    }

    public int getUzunEvTupu() {
        return uzunEvTupu;
    }

    public void setUzunEvTupu(int uzunEvTupu) {
        this.uzunEvTupu = uzunEvTupu;
    }

    public int getTicariTup() {
        return ticariTup;
    }

    public void setTicariTup(int ticariTup) {
        this.ticariTup = ticariTup;
    }

    public int getSanayiTupu() {
        return sanayiTupu;
    }

    public void setSanayiTupu(int sanayiTupu) {
        this.sanayiTupu = sanayiTupu;
    }

    public SepetBilgileri(String id,int kucukTup, int evTupu, int uzunEvTupu, int ticariTup, int sanayiTupu) {
        this.id=id;
        this.kucukTup = kucukTup;
        this.evTupu = evTupu;
        this.uzunEvTupu = uzunEvTupu;
        this.ticariTup = ticariTup;
        this.sanayiTupu = sanayiTupu;

    }
}
