package com.clcain.arithmeticexpression;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Object used to find the ArithmeticExpression within an ExpressionSet that has
 * a sum equal to a given value.
 * 
 * @author Chandler Cain (chandler@clcain.com)
 * @version 2015.08.04
 *
 */
public class SumFinder
{
	private LinkedList<ArithmeticExpression> expressions;
	private ArrayList<Double> sums;
	private LinkedList<ArithmeticExpression> matchingExpressions;
	private ExpressionSet expressionSet;

	/**
	 * Instantiate a new object with the given ExpressionSet.
	 * 
	 * @param expressionSet
	 *            the set on which to base this object
	 */
	public SumFinder(ExpressionSet expressionSet)
	{
		this.expressionSet = expressionSet;
	}

	public ArrayList<Double> getSums()
	{
		expressions = expressionSet.getExpressions();
		
		// check if sums have not yet been generated
		if (sums == null)
		{
			sums = new ArrayList<Double>();
			for (ArithmeticExpression expression : expressions)
			{
				sums.add(expression.getSumSequential());
			}
		}
		return sums;
	}

	/**
	 * Get a list of expressions that have sums equal to the given sum.
	 *
	 * @param sum
	 *            the sum to find
	 * @return a list of expressions that have matching sums
	 */
	public LinkedList<ArithmeticExpression> getMatchingExpressions(double sum)
	{
		// ensure that sums have been calculated
		getSums();

		// check if matching expressions have not yet been generated
		if (matchingExpressions == null)
		{
			matchingExpressions = new LinkedList<ArithmeticExpression>();
			for (int i = 0; i < sums.size(); i++)
			{
				if (sums.get(i) == sum)
				{
					matchingExpressions.add(expressions.get(i));
				}
			}
		}
		return matchingExpressions;
	}
}
