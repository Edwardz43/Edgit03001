package tw.org.iii.ed;

public class Wow {
	public static void main(String[] args){
		Classes mage = new Mage("Edwardz","Undead", 60, 2000, 1500);
		attack(mage);
		cast(mage);
		getHp(mage);
		System.out.println(mage);
		Classes dk = new DeathKnight("艾德華之殤","Undead", 60, 3500, 100);
		attack(dk);
		cast(dk);
		getHp(dk);
		System.out.println(dk);
	}
	static void getHp(Classes classes){
		System.out.printf("%s HP : %d%n", classes.getName(), classes.getHp());
	}
	public static void toString(Classes classes){
		System.out.println(classes.toString());
	}
	static void attack(Classes classes){
		System.out.print(classes.getName()+",");
		classes.attack();
	}
	static void cast(Classes classes){
		System.out.print(classes.getName()+",");
		classes.cast();
	}
	
}
class  Classes{
	protected String name;
	protected int hp;
	protected int energybar;
	protected int level;
	protected String race;
	
	Classes(String name, String race, int level, int hp, int energybar){
		this.name=name; this.race=race; this.level=level; this.hp=hp; this.energybar=energybar;
	}
	
	int getHp(){
		return hp;
	}
	int getEnergybar(){
		return energybar;
	}
	int getLevel(){
		return level;
	}
	String getRace(){
		return race;
	}
	String getName(){
		return name;
	}
	void attack(){}
	void cast(){}
	public String toString(){
		return String.format("%s, %s, %d, %d, %d", this.name, this.race, this.level, this.hp, this.energybar);
	}
}

class Mage extends Classes{
	public Mage(String name, String race, int level, int hp, int energybar){
		super(name, race, level, hp, energybar);
	}
	public void attack(){
		System.out.println("揮杖攻擊!");
	}
	public void cast(){
		System.out.println("火球術!");
	}
	public String toString(){
		return "Mage, "+super.toString();
	}
}
class DeathKnight extends Classes{
	public DeathKnight(String name, String race, int level, int hp, int energybar){
		super(name, race, level, hp, energybar);
	}
	public void attack(){
		System.out.println("劈砍!");
	}
	public void cast(){
		System.out.println("死亡凋零!");
	}
	public String toString(){
		return "DeathKnight, "+super.toString();
	}
}
