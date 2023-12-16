import java.util.Arrays;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + "tüm düşmanları yendiniz !");

            if (this.getName().equals("Coal")) {
                System.out.println("Maden bölgesinde silah, zırh, para bulabilirsin veya hiçbirşey bulamayabilirsin.");
            } else {
                System.out.println("Alınan ödül : " + this.getAward());
            }

            if (this.getAward().equals("Food")) {
                addAwards(this.getAward(), 0);
                System.out.println("-------------------------------");
                System.out.println(Arrays.toString(zaa));
                System.out.println("-------------------------------");

            } else if (this.getAward().equals("Firewood")) {
                addAwards(this.getAward(), 1);
                System.out.println("-------------------------------");
                System.out.println(Arrays.toString(zaa));
                System.out.println("-------------------------------");

            } else if (this.getAward().equals("Water")) {
                addAwards(this.getAward(), 2);
                System.out.println("-------------------------------");
                System.out.println(Arrays.toString(zaa));
                System.out.println("-------------------------------");
            }
            if (checkAward()) {
                System.out.println("-------------------------------");
                System.out.println("Bölgedeki bütün ödülleri topladınız.");
                System.out.println("Kazandınız !");
                return false;
            }
            return true;

        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz !");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);

            Random r = new Random();
            int upperBound = 2;
            int int_random = r.nextInt(upperBound);

            if (int_random == 0) {
                System.out.println("Önce sen vuracaksın !");

            } else {
                System.out.println("Önce canavar vuracak !");
            }

            if (getObstacle().getName().equals("Yılan")) {

                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.println("<V>ur veya <K>aç : ");
                    String selectCombat = input.nextLine().toUpperCase();

                    if (int_random == 0) {
                        if (selectCombat.equals("V")) {
                            System.out.println("Sen vurdun !");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                            if (this.getObstacle().getHealth() > 0) {
                                System.out.println();
                                System.out.println(this.getObstacle().getName() + " vurdu !");

                                int snakeDamage = this.randomSnakeDamages() - this.getPlayer().getInventory().getArmor().getBlock();

                                if (snakeDamage < 0) {
                                    snakeDamage = 0;
                                }
                                this.getPlayer().setHealth(this.getPlayer().getHealth() - snakeDamage);
                                afterHit();
                            }
                        } else {
                            return false;
                        }
                    } else {
                        if (selectCombat.equals("V")) {
                            System.out.println(this.getObstacle().getName() + " vurdu !");
                            int snakeDamage = this.randomSnakeDamages() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (snakeDamage < 0) {
                                snakeDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - snakeDamage);
                            afterHit();

                            if (this.getPlayer().getHealth() > 0) {
                                System.out.println();
                                System.out.println("Sen vurdun !");
                                this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                                afterHit();
                            }
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.println("<V>ur veya <K>aç : ");
                    String selectCombat = input.nextLine().toUpperCase();

                    if (int_random == 0) {
                        if (selectCombat.equals("V")) {
                            System.out.println("Sen vurdun !");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                            if (this.getObstacle().getHealth() > 0) {
                                System.out.println();
                                System.out.println(this.getObstacle().getName() + " vurdu !");
                                int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                                if (obstacleDamage < 0) {
                                    obstacleDamage = 0;
                                }
                                this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                                afterHit();
                            }
                        } else {
                            return false;
                        }
                    } else {
                        if (selectCombat.equals("V")) {
                            System.out.println(this.getObstacle().getName() + " vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();

                            if (this.getPlayer().getHealth() > 0) {
                                System.out.println();
                                System.out.println("Sen vurdun !");
                                this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                                afterHit();
                        }
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        if (this.getObstacle().getId() == 4 && (this.getObstacle().getHealth() < this.getPlayer().getHealth())) {
            System.out.println(this.getObstacle().getName() + " yenildi !");
            System.out.println("Yılandan kazandıkların : ");

            int yy = randomItemOrMoney();

            if (yy >= 0 && yy <= 14) {
                System.out.println("Silah itemi kazandın.");
                int zz = InItem();
                if (zz >= 0 && zz <= 19){

                    int selectWeaponID = 3;
                    Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("kazanılan silah : " + this.getPlayer().getInventory().getWeapon().getName());
                }
                else if (zz >= 20 && zz <= 49) {

                    int selectWeaponID = 2;
                    Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Kazanılan silah : " + this.getPlayer().getInventory().getWeapon().getName());
                } else {

                    int selectWeaponID = 1;
                    Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Kazanılan silah : " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
            else if (yy >= 15 && yy <= 29) {
                System.out.println("Zırh kazandınız.");
                int zz = InItem();
                if (zz >= 0 && zz <= 19) {

                    int selectArmorID = 3;
                    Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kazanılan zırh : " + this.getPlayer().getInventory().getArmor().getName());
                }
                else if (zz >= 20 && zz <= 49) {

                    int selectArmorID = 2;
                    Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kazanılan zırh : " + this.getPlayer().getInventory().getArmor().getName());
                }
                else {

                    int selectArmorID = 1;
                    Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kazanılan zırh : " + this.getPlayer().getInventory().getArmor().getName());
                }
            }
            else if (yy >= 30 && yy <= 54) {
                System.out.println("Para kazandınız.");
                int zz = InItem();
                if (zz >= 0 && zz <= 19) {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                    System.out.println("Kazanılan para : 10");
                }
                else if (zz >= 20 && zz <= 49){
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                    System.out.println("Kazanılan para : 5");
                }
                else{
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                    System.out.println("Kazanılan para : 1");
                }
            }
            else {
                System.out.println("Hiçbirşey kazanamadınız.");
            }
        }
        else if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
            System.out.println(this.getObstacle().getName() + " yendiniz !");
            System.out.println(this.getObstacle().getAward() + " para kazandınız ! ");
            this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
            System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
        }
        else {
            return false;
        }
    }
    return true;

}

public void afterHit(){
    System.out.println("Kalan canın : " + this.getPlayer().getHealth());
    System.out.println(this.getObstacle().getName() + "kalan can : " + this.getObstacle().getHealth());
    System.out.println("-----------------");
}

public void obstacleStats(int i) {

    System.out.println(i + ". " + this.getObstacle().getName() + " Değerleri");
    System.out.println("--------------------------------------");
    System.out.println("Sağlık : " + this.getObstacle().getHealth());
    System.out.println("Hasar : " + this.getObstacle().getDamage());
    System.out.println("Ödül : " + this.getObstacle().getAward());
    System.out.println();
}

public void playerStats() {
    System.out.println("Oyuncu Değerleri");
    System.out.println("--------------------------------------");
    System.out.println("Sağlık : " + this.getPlayer().getHealth());
    System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
    System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
    System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
    System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
    System.out.println("Para : " + this.getPlayer().getMoney());
    System.out.println();
}

String[] bag = {"Food", "Firewood", "Water"};
public boolean checkAward() {
    int count = 0;
    for (int i = 0; i < zaa.length; i++) {
        if (zaa[i].equals(bag[i])) {
            count++;
            if (count == 3) {
                return true;
            }
        }
    }
    return false;
}

public static String[] zaa = {"-", "-", "-"};
public void addAwards(String str, int i) {
    zaa[i] = str;
}

public int randomObstacleNumber() {
    Random r = new Random();
    return r.nextInt(this.getMaxObstacle()) + 1;
}

public int randomSnakeDamages() {
    Random ra = new Random();
    int x = ra.nextInt(this.getObstacle().getDamage() + 1);

    if (x <= 3) {
        return 3;
    }
    return x;
}

public int randomItemOrMoney() {
    Random rand = new Random();
    int y = rand.nextInt(100);
    return y;
}

public int InItem() {
    Random rando = new Random();
    int z = rando.nextInt(100);
    return z;
}

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

}

