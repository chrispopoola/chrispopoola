#include "stack.h"

struct double_stack * double_stack_new(int max_size)
{
        struct double_stack * result;
        // allocates space for the stack header
        result = malloc(sizeof(struct double_stack));
        result -> max_size = max_size;
        result -> top = 0;
        // allocates space for the data stored in the stack
        result = malloc(sizeof(struct double_stack));
        result -> items = malloc(sizeof(double)*max_size);
        // returns pointer to newly allocated stack
        return result;

}

// push a value onto the stack
void double_stack_push(struct double_stack * this, double value)
{
        this->top++;
        this->items[this->top] = value;
        printf ("Element pushed onto stack is %f\n ", this->items[this->top]);
}

// pop a value from the stack
double double_stack_pop(struct double_stack * this) 
{
        double value = this -> items[this->top];
        this -> top--;
        return value;
}
int double_stack_peek(struct double_stack * this)
{
        if (this->top > 0)
                return this->items[this->top];
        else
        {
                printf("\n STACK IS EMPTY");
                return 0;
        }
}

