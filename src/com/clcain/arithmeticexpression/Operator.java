package com.clcain.arithmeticexpression;
import java.util.Arrays;
import java.util.List;

/**
 * Object representation of a mathematical operator.
 * 
 * @author Chandler Cain (chandler@clcain.com)
 * @version 2015.08.02
 *
 */
public enum Operator
{
	ADD('+')
	{
		@Override
		public double apply(double first, double second)
		{
			return first + second;
		}

	},
	SUBTRACT('-')
	{
		@Override
		public double apply(double first, double second)
		{
			return first - second;
		}

	},
	MULTIPLY('*')
	{
		@Override
		public double apply(double first, double second)
		{
			return first * second;
		}

	},
	DIVIDE('/')
	{
		@Override
		public double apply(double first, double second)
		{
			return first / second;
		}

	};

	/**
	 * The default order of operations.
	 */
	public static final List<Operator> DEFAULT_ORDER = Arrays.asList(Operator.MULTIPLY, Operator.DIVIDE, Operator.ADD,
			Operator.SUBTRACT);

	/**
	 * Apply the operator to two numbers.
	 * 
	 * @param first
	 *            the first number
	 * @param second
	 *            the second number
	 * @return the result of the operator's application on the two numbers
	 */
	public abstract double apply(double first, double second);

	@Override
	public String toString()
	{
		return sign + "";
	}

	private final char sign;

	private Operator(char sign)
	{
		this.sign = sign;
	}
}
