package calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculation {
	final static String ERROR_MESSAGE = "入力に誤りがあります";
	
	@SuppressWarnings("deprecation")
	private static String fourArithmeticOperations(String s1, String s2, String symbol) {
		BigDecimal num1 = new BigDecimal(0);
		BigDecimal num2 = new BigDecimal(0);
		BigDecimal result = new BigDecimal(0);
		try {
			num1=new BigDecimal(s1);
			num2=new BigDecimal(s2);
		}catch(Exception e) {
			System.out.println(e);
		}
		switch(symbol) {
		case "×":
			result=num1.multiply(num2);
			break;
		case "÷":
			try {
				result=num1.divide(num2);
			}catch(ArithmeticException e1) {
				try {
					result=num2.divide(num2,10,BigDecimal.ROUND_HALF_UP);
				}catch(ArithmeticException e2) {
					System.out.println(e2);
					return null;
				}
			}
			break;
		case "+":
			result=num1.add(num2);
			break;
		case "-":
			result=num1.subtract(num2);
			break;
		}	
		return result.toString();
	}
	
	public static String[] separate(String[] fArray) {
		String[] sArray=new String[0];
		
		int c=0;
		
		boolean isSymbol=false;
		boolean isPoint=false;
		
		for (int i = 0; i < fArray.length; i++) {
			try {
				Integer.parseInt(fArray[i]);
				if(isSymbol) {
					c++;
					isSymbol=false;
				}
			}catch(Exception e) {
				if(i<=0|| i>=fArray.length-1) {
					return null;
				}
				if(isSymbol) {
					return null;
				}
				switch(fArray[i]) {
				case "×":
				case "÷":
				case "+":
				case "-":
					c++;
					isSymbol=true;
					if(isPoint) {
						isPoint=false;
					}
					break;
				case ".":
					if(isPoint) {
						return null;
					}else {
						isPoint=true;
					}
					break;
				default:
					return null;
				}
			}
			
			try {
				sArray[c]+=fArray[i];
			}catch(ArrayIndexOutOfBoundsException e) {
				ArrayList<String>a=new ArrayList<String>(Arrays.asList(sArray));
				a.add(fArray[i]);
				sArray=a.toArray(new String[0]);
			}
		}
		return sArray;
	}
	
	private static boolean isMultiplicationOrDivisionSymbol(String symbol) {
		if (symbol.equals("×") || symbol.equals("÷")) {
			return true;
		}
		else{
			return false;
		}
	}
	
	private static String[] calculateArrayElements(String[] sArray, int n) {
		String ans = fourArithmeticOperations(sArray[n-1], sArray[n+1], 
		sArray[n]);
		if (ans == null) return null;
		sArray[n-1] = ans;
		for (int j = n; j < sArray.length-2; j++) {
		sArray[j] = sArray[j+2];
		}
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(sArray));
		a.remove(sArray.length-1);
		a.remove(sArray.length-2);
		sArray = a.toArray(new String[0]);
		return sArray;
		}
	
	static String calculate(String formula) {
		String[] sArray = new String[0];
		String[] fArray = formula.split("");
		if (formula.length() == 0) {
			System.out.println("数値が⼊⼒されていません。");
			return ERROR_MESSAGE;
		}
		sArray = separate(fArray);
		if (sArray == null) {
			return ERROR_MESSAGE;
		}
		else if (sArray.length == 1) {
			return sArray[0];
		}
		for (int i = 0; i < sArray.length; i++) {
		if (isMultiplicationOrDivisionSymbol(sArray[i])) {
		sArray = calculateArrayElements(sArray, i);
		if (sArray == null) {
			return ERROR_MESSAGE;
		}
		i = 0;
		}
		}
		while (sArray.length > 1) {
		sArray = calculateArrayElements(sArray, 1);
		if (sArray == null) {
			return ERROR_MESSAGE;
		}
		}
		return sArray[0];
		}
	
}


