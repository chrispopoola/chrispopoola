#include "postfix.h"
#include <ctype.h>
// evaluate expression stored as an array of string tokens
double evaluate_postfix_expression(char ** args, int nargs) 
{
  // Write your code here
	struct double_stack * stack = double_stack_new(nargs);
	for (int i = 0; i < nargs; i++)
	{
		if ((args[i][0] >= '0' && args[i][0] <= '9') || strlen(args[i]) != 1)
			double_stack_push(stack, atof(args[i]));
		else
		{
			double value1 = double_stack_pop(stack);
			double value2 = double_stack_pop(stack);

			switch(args[i][0])
			{
				case '+':
					double_stack_push(stack, value2 + value1);
					break;
				case '-':
					double_stack_push(stack, value2 - value1);
					break;
				case 'X':
					double_stack_push(stack, value2 * value1);
					break;
				case '/':
					double_stack_push(stack, value2 / value1);
 					break;
				case '^':
					double_stack_push(stack, pow(value2, value1));
					break;
			}
		}
	}
	return double_stack_pop(stack);
}
