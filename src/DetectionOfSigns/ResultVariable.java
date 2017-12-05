package DetectionOfSigns;

public class ResultVariable {
	public String name = null;
	public String[] signs = new String[3];
	
	public ResultVariable(String name, String sign) {
		this.name = name;
		switch (sign) {
		case " ":
			signs[0] = " ";
			signs[1] = " ";
			signs[2] = " ";
			break;
		case "-":
			signs[0] = "-";
			signs[1] = " ";
			signs[2] = " ";
			break;
		case "0":
			signs[0] = " ";
			signs[1] = "0";
			signs[2] = " ";
			break;
		case "+":
			signs[0] = " ";
			signs[1] = " ";
			signs[2] = "+";
			break;

		default:
			break;
		}
		
		
		
	}
	
	public String signToString() {
		return ("{" + signs[0] +  "," +signs[1]  +  "," + signs[2]  +"}");
	}
	
	public String print() {
		return (name + ":  " +"{" + signs[0] +  "," +signs[1]  +  "," + signs[2]  +"} \t");
	}
}
