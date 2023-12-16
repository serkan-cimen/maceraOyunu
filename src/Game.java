import java.util.Scanner;
public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz : ");
        boolean win = false;

        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hoşgeldiniz ! Burada yaşananların hepsi gerçektir !");
        System.out.println("Lütfen bir karakter seçiniz !");
        System.out.println("-------------------------------------------------------");
        player.selectChar();

        Location location = null;

        while (true) {
            player.printInfo();
            printMenu();
            int selectLoc = input.nextInt();
            if (selectLoc == 3 && player.getInventory().getFood() == 1) {
                selectLoc = 0;
                System.out.println("Görev Tamamlandı !");
                System.out.println();
                System.out.println("Güvenli eve gidiniz.");
            }
            if (selectLoc == 4 && player.getInventory().getFirewood() == 1) {
                selectLoc = 0;
                System.out.println("Görev Tamamlandı !");
                System.out.println();
                System.out.println("Güvenli eve gidiniz.");
            }
            if (selectLoc == 5 && player.getInventory().getWater() == 1) {
                selectLoc = 0;
                System.out.println("Görev Tamamlandı !");
                System.out.println();
                System.out.println("Güvenli eve gidiniz.");
            }


            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Coal(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz ! ");
            }

            if (location == null) {
                System.out.println("Bu karanlık ve sisli adadan çabuk vazgeçtin !");
                break;
            }

            if (!location.onLocation()) {
                System.out.println("GAME OVER!");
                break;
            }

        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println("########### Bölgeler ##########");
        System.out.println();
        System.out.println("1 - Güvenli Ev --> Burası sizin için güvenli bir ev, düşman yok !");
        System.out.println("2 - Dükkan --> Silah veya Zırh satın alabilirsiniz !");
        System.out.println("3 - Mağara --> Ödül <Yemek>, dikkatli ol karşına zombi çıkabilir !");
        System.out.println("4 - Orman --> Ödül <Odun>, dikkatli ol karşına vampir çıkabilir !");
        System.out.println("5 - Nehir --> Ödül <Su>, dikkatli ol karşına ayı çıkabilir !");
        System.out.println("6 - Maden --> Ödül <Hazine> dikkatli ol karşına yılan çıkabilir !");
        System.out.println("0 - Çıkış Yap --> Oyunu sonlandır.");

        System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
    }
}