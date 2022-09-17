#include "infix.h"
#include <ctype.h>
int higher_eq_precendence (char x, char y)
{
	if (y != '(' && y != ')')
	{
		if (x == '-' || x == '+')
			return 1;
		if ((x == 'X' || x == '/') && (y != '+' && y != '-'))
			return 1;
		if (x == '^' && y == '^')
			return 1;
	}
	return 0;
}
int is_operator (char item)
{
	if (item == '^' || item == 'X' || item == '/' || item == '-' || item == '+')
		return 1;
	return 0;
}
// evaluate expression stored as an array of string tokens
double evaluate_infix_expression(char ** args, int nargs)
{
	struct double_stack * stack = double_stack_new(nargs);
	char ** postfix = malloc(sizeof(char *)*nargs);
	int item;
	int j = 0;
	for (int i = 0; i < nargs; i++)
	{
		if ((args[i][0] >= '0' && args[i][0] <= '9') || strlen(args[i]) != 1)
		{
			postfix[j] = args[i];
			j++;
			printf("This top should be great: %d\n", stack->top);
		}
		else if (args[i][0] == '(')
		{ 
		       	double_stack_push(stack, i);
		}
		else if (is_operator(args[i][0]) == 1)
		{
			if (stack->top > 0)
			{
				item = double_stack_peek(stack);

				while (stack->top > 0 && higher_eq_precendence(args[i][0], args[item][0]) == 1)
				{
					item = double_stack_pop(stack);
					postfix[j] = args[item];
					j++;
					item = double_stack_peek(stack);
				}
				double_stack_push(stack, i);
			}
			else {
			double_stack_push(stack, i);
			}
		}
		else if (args[i][0] == ')')
		{
			if (stack->top > 0)
			{
				item = double_stack_peek(stack);
				while (stack->top > 0 && args[item][0] != '(')
				{
					item = double_stack_pop(stack);
					postfix[j] = args[item];
					j++;
					item = double_stack_peek(stack);
				}
			}
		}
	}
	while (stack->top > 0)
	{
		int x = double_stack_pop(stack);
		postfix[j] = args[x];
		j++;
	}
	for (int i = 0; i < nargs; i++)
		printf("postfix : %s", postfix[i]);
	return evaluate_postfix_expression(postfix, j);
}
