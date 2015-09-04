

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

import org.ini4j.Wini;

import com.clcain.arithmeticexpression.ExpressionSet;
import com.clcain.arithmeticexpression.Operator;
import com.clcain.arithmeticexpression.SumFinder;

public class CombinationFinder
{
	public static void main(String[] args)
	{
		try
		{
			// variables for settings
			boolean showAllExpressions;
			boolean showAllSums;

			// read data from .ini
			Wini ini = new Wini(new File("config.ini"));
			String[] numbers = ini.get("parameters", "numbers", String.class).split(",");
			String[] operators = ini.get("parameters", "operators", String.class).split(",");
			Double sum = ini.get("parameters", "sum", Double.class);
			showAllExpressions = ini.get("options", "show_all_expressions", Boolean.class);
			showAllSums = ini.get("options", "show_all_sums", Boolean.class);

			// initialize objects
			ExpressionSet set = new ExpressionSet();
			SumFinder finder = new SumFinder(set);

			// process data read from .ini
			for (String number : numbers)
			{
				System.out.println("Adding number: " + number);
				set.addNumber(Double.parseDouble(number));
			}
			for (String operator : operators)
			{
				operator.trim();
				for (Operator possibleOperator : Operator.DEFAULT_ORDER)
				{
					if (possibleOperator.toString().equals(operator))
					{
						System.out.println("Adding operator: " + operator);
						set.addOperator(possibleOperator);
					}
				}
			}

			// send output to file
			PrintWriter output = new PrintWriter(new FileWriter("output.txt"));
			System.out.println("Computing expressions matching sum of " + sum + ".");
			if (showAllExpressions)
			{
				System.out.println("Showing all expressions.");
				output.println(set.getExpressions());
			}
			if (showAllSums)
			{
				System.out.println("Showing all sums.");
				output.println(finder.getSums());
			}
			output.print(finder.getMatchingExpressions(sum));
			output.close();

			System.out.println("Operation completed successfully.\nResults sent to output.txt.");
		}
		catch (Exception e)
		{
			System.out.println("Invalid config.ini.");
		}
	}

}
