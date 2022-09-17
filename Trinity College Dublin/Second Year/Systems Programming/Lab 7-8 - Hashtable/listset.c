#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

// include the header files with the declarations of listset
#include "listset.h"

struct listset * listset_new() {
	struct listset * listset = malloc(sizeof(struct listset));
	listset->head = malloc(sizeof(struct listnode));
	listset->head->str = NULL;
	listset->size = 0;
	listset->head->next = malloc(sizeof(struct listnode));
	return listset;
}

/* check to see if an item is in the set
   returns 1 if in the set, 0 if not */
int listset_lookup(struct listset * this, char * item) {
	struct listnode * current = this->head;
	for (int i = 0; i < this->size; i++)
	{
		if (strcmp(current->str, item) == 0)
			return 1;
		else
			current = current->next;
	}
	return 0;
}

void listset_add(struct listset * this, char * item) {
	struct listnode * newnode = malloc(sizeof(struct listnode));
	newnode->str = item;
	if (listset_lookup(this, item) == 0)
	{
		if (this->size == 0)
			this->head = newnode;
		else
		{
			newnode->next = NULL;
			newnode->next = this->head;
			this->head = newnode;
		}
		this->size++;
	}
}

void listset_remove(struct listset * this, char * item) {
	if (listset_lookup(this, item) == 0)
		return;
	if (this->head == NULL)
		printf("List is empty, there is nothing to delete\n");
	else if (this->size == 1)
	{
		this->head = NULL;
		this->size--;
	}
	else {
	        struct listnode * current = this->head;
	        struct listnode * previous;

		while (strcmp(current->str, item) != 0)
		{
			previous = current;
			current = current->next;
		}
		if (current == this->head)
			this->head = this->head->next;
		else if (previous != NULL)
			previous->next = current->next;
		free(current);
		this->size--;
	}
}

// place the union of src1 and src2 into dest
void listset_union(struct listset * dest, struct listset * src1,
  struct listset * src2) {
	struct listnode * current1 = src1->head;
	struct listnode * current2 = src2->head;
	while (current1 != NULL)
	{
		listset_add(dest, current1->str);
		current1 = current1->next;
	}
	while (current2 != NULL)
	{
		if (listset_lookup(dest, current2->str) == 0)
			listset_add(dest, current2->str);
		current2 = current2->next;
	}
}

// place the intersection of src1 and src2 into dest
void listset_intersect(struct listset * dest, struct listset * src1,
  struct listset * src2) {
        struct listnode * current1 = src1->head;
        struct listnode * current2;
        for (int i = 0; i < src1->size; i++)
        {
                current2 = src2->head;
                for (int j = 0; j < src2->size; i++)
                {
                        if (current1 == current2)
                                listset_add(dest, current1->str);
                        current2 = current2->next;
                }
                current1 = current1->next;
        }

}

// return the number of items in the listset
int listset_cardinality(struct listset * this) {
	return this->size;
}

// print the elements of the list set
void listset_print(struct listset * this) {
  struct listnode * p;
  for ( p = this->head; p != NULL; p = p->next ) {
    printf("%s, ", p->str);
  }
  printf("\n");
}
