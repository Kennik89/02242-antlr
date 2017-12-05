package DetectionOfSigns;

public class InitVariable {
	public String name;
	public String sign;
	
	public InitVariable(String name, String sign) {
		this.name = name;
		this.sign = sign;
	}
	
	public void init(String newName) {
		name = newName;
		sign = " ";
	}
	
	
}
