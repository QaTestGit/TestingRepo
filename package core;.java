package core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

@Parameters(separators = "=")

public class CLO_Calc {
	@Parameter (names = { "-o", "--operator"}, description = "Operator")
	static String operator = null;
	
	@Parameter (names = {"-1", "--operands"}, variableArity = true, description = "List of operands")
	static ArrayList<Double> operands = new ArrayList<Double>();

	public static void main(String[] args) {
		for(String a: args){
			if(a.matches("[@&]")){
				System.err.println("Invalid characters");
				System.exit(2);
			}
		}
		JCommander.newBuilder().addObject(new CLO_Calc()).build().parse(args);
		
		if(operator == null || !operator.matches("[+-/*/]")){
			System.err.println("Please specify the operator = - * /");
		}
		else{
			ArrayList<Double> operands = null;
			Double result = ((ArrayList<Double>) operands).get(0);
			for(int i=1;i<((ArrayList<Double>) operands).size();i++){
				switch (operator){
				case "+": result+=((ArrayList<Double>) operands).get(i); break;
				case "-": result-=((ArrayList<Double>) operands).get(i); break;
				case "*": result*=((ArrayList<Double>) operands).get(i); break;
				case "/": result/=((ArrayList<Double>) operands).get(i); break;
				}
			}
			System.out.println(new BigDecimal(result).setScale(2, RoundingMode.HALF_UP));
		}
		

	}

}
