import java.util.ArrayList;

import com.clcain.arithmeticexpression.ExpressionSet;
import com.clcain.arithmeticexpression.Operator;

public class ExpressionSetTests
{
	public static void main(String[] args)
	{
		ArrayList<Double> numbers = new ArrayList<Double>();
		ArrayList<Operator> operators = new ArrayList<Operator>();

		numbers.add(1.0);
		numbers.add(2.0);

		operators.add(Operator.ADD);
		operators.add(Operator.SUBTRACT);

		//ExpressionSet set = new ExpressionSet(numbers, operators);
		ExpressionSet set = new ExpressionSet();
		for (Double number : numbers)
		{
			set.addNumber(number);
		}
		for (Operator operator : operators)
		{
			set.addOperator(operator);
		}
		System.out.println(set.getExpressions().toString());
	}
}