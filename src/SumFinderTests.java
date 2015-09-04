import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.clcain.arithmeticexpression.ExpressionSet;
import com.clcain.arithmeticexpression.Operator;
import com.clcain.arithmeticexpression.SumFinder;

public class SumFinderTests
{
	public static void main(String[] args) throws IOException
	{
		PrintWriter writer = new PrintWriter(new FileWriter("log.txt"));
		
		ExpressionSet set = new ExpressionSet();
		set.addNumber(5.0);
		set.addNumber(6.0);
		set.addNumber(7.0);
		set.addNumber(8.0);
		set.addNumber(9.0);

		set.addOperator(Operator.ADD);
		set.addOperator(Operator.SUBTRACT);
		set.addOperator(Operator.MULTIPLY);
		set.addOperator(Operator.DIVIDE);

		writer.println(set.getExpressions().toString());
		SumFinder finder = new SumFinder(set);
		writer.println(finder.getSums());
		writer.println(finder.getMatchingExpressions(24.0));
		writer.close();
	}
}
