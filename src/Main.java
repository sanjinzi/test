import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

	public static void main(String[] args) {
		System.out.print("请输入计算题目数量：");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		char[] operator = new char[] { '+', '-', '*', '/' };
		Random random = new Random();
		ArrayList<String> expression = new ArrayList<String>();
		for(int i = 0; i < num; i++){
			int n = random.nextInt(3) + 2;
			int[] number = new int[n + 1];
			String nexpression = new String();
			for (int j = 0; j <= n; j++) {
				number[j] = random.nextInt(100) + 1;
			}
			for (int j = 0; j < n; j++) {
				int fuhao = random.nextInt(4);
				nexpression += String.valueOf(number[j]) + String.valueOf(operator[fuhao]);
				if (fuhao == 3) {
					number[j + 1] = division(number[j], number[j + 1]);
				}
			}
			nexpression += String.valueOf(number[n]);
			expression.add(nexpression);
		}
			calculate(expression);
	}



	
	private static int division(int x, int y) {
		//x=(int)(Math.random()*100);
		y=(int)(Math.random()*100+1);
		if (x % y == 0)
			return y;
		else
			return division(x,y);
	}
	
	


	
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

	private static ArrayList<String> calculate(ArrayList<String> arrayList) {
		ArrayList<String> suanshi = new ArrayList<String>();
		for (String ax : arrayList) {
			try {
				ax = ax + "=" + jse.eval(ax);
				System.out.println(ax);
				suanshi.add(ax);
			} 
			catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			File test = new File("result.txt");
			FileWriter fw = new FileWriter(test);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("201571030123");
			for (String con : suanshi) {
				pw.println(con);

			}

			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suanshi;

	}
}
