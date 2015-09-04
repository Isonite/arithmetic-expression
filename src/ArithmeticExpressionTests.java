import com.clcain.arithmeticexpression.ArithmeticExpression;
import com.clcain.arithmeticexpression.Operator;

public class ArithmeticExpressionTests
{
	public static void main(String[] args)
	{
		ArithmeticExpression expression = new ArithmeticExpression();
		System.out.println(expression.isComplete());
		expression.addNumber(1);
		System.out.println(expression.isComplete());
		expression.addOperator(Operator.ADD);
		System.out.println(expression.isComplete());
		expression.addNumber(2);
		expression.addOperator(Operator.MULTIPLY);
		expression.addNumber(3);
		expression.addOperator(Operator.MULTIPLY);
		expression.addNumber(4);
		expression.addOperator(Operator.SUBTRACT);
		expression.addNumber(5);

		System.out.println(expression.toString() + "=" + expression.getSum(Operator.DEFAULT_ORDER));
		System.out.println(expression.toString() + "=" + expression.getSumSequential());

		ArithmeticExpression expression2 = new ArithmeticExpression(expression);
		System.out.println(expression2.isComplete());
		expression2.addOperator(Operator.MULTIPLY);
		System.out.println(expression2.isComplete());
	}
}
