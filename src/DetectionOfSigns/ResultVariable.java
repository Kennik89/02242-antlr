package DetectionOfSigns;

public class ResultVariable {
	public String name = null;
	public String[] signs = new String[3];
	
	public ResultVariable(String name) {
		this.name = name;
		signs[0] = " ";
		signs[1] = " ";
		signs[2] = " ";
	}
	
	public String print() {
		return (name + ":  " +"{" + signs[0] +  "," +signs[1]  +  "," + signs[2]  +"} \t");
	}
}
