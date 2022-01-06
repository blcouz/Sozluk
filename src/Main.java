import java.util.Scanner;

public class Main {
    static Sozluk sozluk = new Sozluk();

    public static void main(String[] args) {
        AnaMenu();
    }

    static void AnaMenu() {
        BoslukSpam();
        System.out.println("Uygulama Versiyonu : " + sozluk.getSurum());
        System.out.println("1----------> Ara");
        System.out.println("2----------> Kelime Ekle");
        System.out.println("3----------> Çıkış");
        System.out.print("-> ");
        Scanner scn = new Scanner(System.in);
        String a = scn.nextLine();
        switch (a) {
            default -> {
                AnaMenu();
            }
            case "1" -> {
                System.out.print("Aranacak Kelime : ");
                a = scn.nextLine();
                sozluk.Ara(a);
                EnterBekle();
                AnaMenu();
            }
            case "2" -> {
                EklemeMenusu();
            }
            case "3" -> System.exit(0);
        }
    }

    static void EklemeMenusu() {
        BoslukSpam();
        Scanner scn = new Scanner(System.in);
        String a;
        System.out.println("1 -) Tr -> Eng");
        System.out.println("2 -) Eng -> Tr");
        System.out.print("------>");
        String kelime, ceviri;
        a = scn.nextLine();
        switch (a) {
            default -> {
                scn.close();
                System.out.println("\n".repeat(50));
            }
            case "1" -> {
                System.out.print("Kelime : ");
                kelime = scn.nextLine();
                System.out.print("Çevirisi : ");
                ceviri = scn.nextLine();
                sozluk.Ekle(kelime, ceviri, true);
                System.out.println("Eklendi.");
                EnterBekle();
                AnaMenu();
            }
            case "2" -> {
                System.out.print("Kelime : ");
                kelime = scn.nextLine();
                System.out.print("Çevirisi : ");
                ceviri = scn.nextLine();
                sozluk.Ekle(kelime, ceviri, false);
                EnterBekle();
                AnaMenu();
            }
        }
    }

    static void EnterBekle() {
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    static void BoslukSpam() {
        System.out.println("\n".repeat(50));
    }
}

