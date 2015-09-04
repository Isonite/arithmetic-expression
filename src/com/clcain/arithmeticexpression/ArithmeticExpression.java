package com.clcain.arithmeticexpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Object representation of an arithmetic expression. Arithmetic expressions
 * contain numbers and operators.
 * 
 * @author Chandler Cain (chandler@clcain.com)
 * @version 2015.08.03
 *
 */
public class ArithmeticExpression
{
	// fields
	private ArrayList<Double> numbers; // stores numbers in expression
	private ArrayList<Operator> operators; // stores operators in expression
	private boolean complete; // stores whether the expression is complete

	/**
	 * Instantiate a new object.
	 */
	public ArithmeticExpression()
	{
		numbers = new ArrayList<Double>();
		operators = new ArrayList<Operator>();
	}

	/**
	 * Instantiate a new object as a clone of the given object.
	 * 
	 * @param other
	 *            the object to clone
	 */
	public ArithmeticExpression(ArithmeticExpression other)
	{
		this(new ArrayList<Double>(other.numbers), new ArrayList<Operator>(other.operators));
	}

	/**
	 * Instantiate a new object from the given lists of numbers and operators.
	 * 
	 * @param numbers
	 *            the numbers in the expression, in order
	 * @param operators
	 *            the operators in the expression, in order
	 */
	public ArithmeticExpression(ArrayList<Double> numbers, ArrayList<Operator> operators)
	{
		this.numbers = numbers;
		this.operators = operators;
		// check if there is an operator between each number
		if (numbers.size() - operators.size() == 1)
		{
			complete = true;
		}
		else
		{
			complete = false;
		}
	}

	/**
	 * Add a new number to the expression.
	 * 
	 * @param number
	 *            the number to be added
	 */
	public void addNumber(double number)
	{
		if (!complete)
		{
			numbers.add(number);
			complete = true;
		}
		else
		{
			throw new IllegalStateException("An operator must be added before the next number.");
		}
	}

	/**
	 * Add a new operator to the object.
	 * 
	 * @param operator
	 *            the operator to be added
	 */
	public void addOperator(Operator operator)
	{
		if (complete)
		{
			operators.add(operator);
			complete = false;
		}
		else
		{
			throw new IllegalStateException("A number must be added before the next operator.");
		}
	}

	/**
	 * Get the sum of the expression in its current state.
	 * 
	 * @param operatorOrder
	 *            a list of the order in which operations will be performed
	 * @return the sum of the expression
	 */
	public double getSum(List<Operator> operatorOrder)
	{
		if (complete)
		{
			// clone expression for calculation
			ArrayList<Double> numbers = new ArrayList<Double>(this.numbers);
			ArrayList<Operator> operators = new ArrayList<Operator>(this.operators);

			for (Operator operator : operatorOrder)
			{
				// scan operators for matching type
				for (int i = 0; i < operators.size(); i++)
				{
					// if match is found, perform operation
					if (operators.get(i) == operator)
					{
						numbers.set(i, operator.apply(numbers.get(i), numbers.get(i + 1)));
						numbers.remove(i + 1);
						operators.remove(i);
						i--;
					}
				}
			}

			// return value
			return numbers.get(0);
		}
		else
		{
			throw new IllegalStateException("Expression is not complete.");
		}
	}

	/**
	 * Get the sum of the expression in its current state without applying any
	 * order of operations. Operators will be applied in sequential order from
	 * first to last.
	 * 
	 * @return the sum of the expression
	 */
	public double getSumSequential()
	{
		if (complete)
		{
			// clone expression for calculation
			ArrayList<Double> numbers = new ArrayList<Double>(this.numbers);
			ArrayList<Operator> operators = new ArrayList<Operator>(this.operators);

			// apply operators in sequential order from first to last
			for (int i = 0; i < operators.size(); i++)
			{
				numbers.set(i, operators.get(i).apply(numbers.get(i), numbers.get(i + 1)));
				numbers.remove(i + 1);
				operators.remove(i);
				i--;
			}

			// return result
			return numbers.get(0);
		}
		else
		{
			throw new IllegalStateException("Expression is not complete.");
		}
	}

	@Override
	public String toString()
	{
		if (complete)
		{
			StringBuilder output = new StringBuilder();
			for (int i = 0; i < operators.size(); i++)
			{
				output.append(numbers.get(i));
				output.append(operators.get(i));
			}
			output.append(numbers.get(numbers.size() - 1));
			return output.toString();
		}
		else
		{
			throw new IllegalStateException("Expression is not complete.");
		}
	}

	/**
	 * Check whether the expression is complete in its current state.
	 * 
	 * @return a boolean signifying whether the expression is complete
	 */
	public boolean isComplete()
	{
		return complete;
	}
}