package com.clcain.arithmeticexpression;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Object representation for the a set of every possible expression that can be
 * generated from a set of numbers and operators.
 * 
 * @author Chandler Cain (chandler@clcain.com)
 * @version 2015.08.02
 *
 */
public class ExpressionSet
{
	// fields
	private ArrayList<Double> numbers;
	private ArrayList<Operator> operators;
	private LinkedList<ArithmeticExpression> expressions;

	/**
	 * Instantiate a new object.
	 * 
	 */
	public ExpressionSet()
	{
		numbers = new ArrayList<Double>();
		operators = new ArrayList<Operator>();
	}

	/**
	 * Add a number to this expression set.
	 * 
	 * @param number
	 *            the number to be added
	 */
	public void addNumber(double number)
	{
		numbers.add(number);
		expressions = null; // clear expressions
	}

	/**
	 * Add an operator to this expression set.
	 * 
	 * @param operator
	 *            the operator to be added
	 */
	public void addOperator(Operator operator)
	{
		operators.add(operator);
		expressions = null; // clear expressions
	}

	/**
	 * Instantiate a new object with the given lists of numbers and operators.
	 * 
	 * @param numbers
	 *            the list of numbers
	 * @param operators
	 *            the list of operators
	 */
	public ExpressionSet(ArrayList<Double> numbers, ArrayList<Operator> operators)
	{
		this.numbers = numbers;
		this.operators = operators;
	}

	private void generateExpressions(ArrayList<Double> numbers, ArrayList<Operator> operators, int depth,
			ArithmeticExpression expression)
	{
		// base case
		if (depth == 0)
		{
			expressions.add(expression);
		}
		else
		{
			// check if ready for operator
			if (expression.isComplete())
			{
				for (Operator operator : operators)
				{
					// clone expression
					ArithmeticExpression newExpression = new ArithmeticExpression(expression);

					// add an operator
					newExpression.addOperator(operator);

					// continue generating new expression
					generateExpressions(numbers, operators, depth, newExpression);
				}
			}
			else
			{
				for (Double number : numbers)
				{
					// clone expression
					ArithmeticExpression newExpression = new ArithmeticExpression(expression);

					// add a number
					newExpression.addNumber(number);

					// instantiate new list for remaining numbers
					ArrayList<Double> remainingNumbers = new ArrayList<Double>(numbers);

					// remove current number from choices for next run
					remainingNumbers.remove(number);

					// continue generating new expression with remaining numbers
					generateExpressions(remainingNumbers, operators, depth - 1, newExpression);
				}
			}
		}
	}

	/**
	 * Get the set of every possible expression that can be generated from this
	 * object's numbers and operators.
	 * 
	 * @return a list containing each expression
	 */
	public LinkedList<ArithmeticExpression> getExpressions()
	{
		// check if expressions have not yet been generated
		if (expressions == null)
		{
			// start generating expressions
			expressions = new LinkedList<ArithmeticExpression>();
			
			if (numbers.size() != 0)
			{
				int depth = numbers.size();
				ArithmeticExpression expression = new ArithmeticExpression();
				generateExpressions(numbers, operators, depth, expression);
			}
		}

		// return generated expressions
		return expressions;
	}
}
