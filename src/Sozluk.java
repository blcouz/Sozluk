import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.getProperty;

public class Sozluk {
    private final List<KelimeVeCeviri> ceviriListesi = new ArrayList<>();
    private final File trdenEngyeVeriTabani = new File(getProperty("user.dir") + "\\sozluk.txt");
    Locale turkceKelimeALtYapisi = new Locale("tr","TR");
    Sozluk(){
        VeriTabanindanKelimeleriCek();
    }
    public void Ekle(String kelime, String cevirisi, Boolean TrdenEngyemi) {
        KelimeVeCeviri yeniCeviri = new KelimeVeCeviri(kelime, cevirisi);
        KelimeVeCeviri ters = new KelimeVeCeviri(cevirisi, kelime);
        try{
            FileWriter fw = new FileWriter(trdenEngyeVeriTabani,true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (TrdenEngyemi) {
                ceviriListesi.add(yeniCeviri);
                bw.write(kelime+"/"+cevirisi+"\n");
            } else {
                ceviriListesi.add(ters);
                bw.write(cevirisi+"/"+kelime+"\n");
            }
            bw.close();
        }catch (Exception e){
            System.out.println("İşlem gerçekleştirilemedi");
        }


    }

    public void Ara(String aranacakKelime) {
        List<KelimeVeCeviri> trEng = new ArrayList<>();
        List<KelimeVeCeviri> engTr = new ArrayList<>();
        for (KelimeVeCeviri kelimeVeCeviri : ceviriListesi) {
            if (kelimeVeCeviri.getKelime().toLowerCase(turkceKelimeALtYapisi).equals(aranacakKelime.toLowerCase(turkceKelimeALtYapisi))) {
                trEng.add(kelimeVeCeviri);
            }
            if (kelimeVeCeviri.getCevirisi().toLowerCase(turkceKelimeALtYapisi).equals(aranacakKelime.toLowerCase(turkceKelimeALtYapisi))) {
                engTr.add(kelimeVeCeviri.TersineCevir());
            }
        }
        if (trEng.size() > 0 | engTr.size() > 0) {
            if (trEng.size() > 0) {
                System.out.println("----------Türkçe -> İngilizce--------");
                for (KelimeVeCeviri a : trEng) {
                    System.out.println(a.KelimeVeCevirisi());
                }
            }
            if (engTr.size() > 0) {
                System.out.println("-----------İngilizce -> Türkçe--------");
                for (KelimeVeCeviri a : engTr) {
                    System.out.println(a.KelimeVeCevirisi());
                }
            }

        } else {
            System.out.println("Sözlükte " + aranacakKelime + " yok.");
        }
    }

    private void VeriTabanindanKelimeleriCek() {
        try {
            Scanner scanner = new Scanner(trdenEngyeVeriTabani);
            while (scanner.hasNextLine()){
                String satir = scanner.nextLine();
                String[] k_c = satir.split("/");
                KelimeVeCeviri eklenecekCeviri = new KelimeVeCeviri(k_c[0],k_c[1] );
                ceviriListesi.add(eklenecekCeviri);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Veri tabanından veri çekerken hata oluştu.");
        }

    }

    public String getSurum() {
        return "1.0";
    }
}

class KelimeVeCeviri {
    private final String kelime;
    private final String cevirisi;

    KelimeVeCeviri(String kelime, String cevrisi) {
        this.kelime = kelime;
        this.cevirisi = cevrisi;
    }

    public String getCevirisi() {
        return cevirisi;
    }

    public String getKelime() {
        return kelime;
    }

    public KelimeVeCeviri TersineCevir() {
        return new KelimeVeCeviri(cevirisi, kelime);
    }

    public String KelimeVeCevirisi() {
        return kelime + " --> " + cevirisi;
    }
}
