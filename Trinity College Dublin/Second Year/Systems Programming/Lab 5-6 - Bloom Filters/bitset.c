#include "bitset.h"
#include <stdlib.h>
#include <math.h>
#include <stdio.h>
// create a new, empty bit vector set with a universe of 'size' items
struct bitset * bitset_new(int size) {
	struct bitset * result = malloc(sizeof(struct bitset));
	float word_size = sizeof(uint64_t) * 8;
	int words = ceil(size / word_size);

	result->size_in_words = words;
	result->universe_size = size;
	result->bits = malloc(sizeof(uint64_t) * words);
	for (int i = 0; i < words; i++)
	{
		result->bits[i] = 0;
	}
	return result;
}

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset * this) {
	return this->universe_size;
}

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset * this){
	int count = 0;
	uint64_t mask = 1ul;
	for (int i = 0; i < this->size_in_words; i++)
	{
		for (int j = 0; j < 64; j++)
		{
			int x = this->bits[i] >> j;
			if ((x & mask) == 1)
				count++;
		}
	}
	return count;
}

// check to see if an item is in the set
int bitset_lookup(struct bitset * this, int item){

	int word_size = (sizeof(uint64_t) * 8);
        int word = item/word_size;
        int bit = item % word_size;

        uint64_t temp = this->bits[word];
	temp = temp << (64-bit-1);
	temp = temp >> 63;
	uint64_t mask = 1ul;

	if ((temp&mask) == 1)
		return 1;
	else
		return 0;
}

// add an item, with number 'item' to the set
// has no effect if the item is already in the set
void bitset_add(struct bitset * this, int item) {
	int word_size = (sizeof(uint64_t) * 8);
	int word = item/word_size;
	int bit = item % word_size;

	uint64_t old_value = this->bits[word];
	uint64_t mask = 1ul << bit;
	this->bits[word] = old_value|mask;

}

// remove an item with number 'item' from the set
void bitset_remove(struct bitset * this, int item) {
	int word_size = (sizeof(uint64_t) * 8);
	int word = item/word_size;
	int bit = item % word_size;

	uint64_t original = this->bits[word];
	uint64_t mask = 1ul;
	mask = mask << bit;
	mask = ~mask;
	this->bits[word] = original&mask;
}

// place the union of src1 and src2 into dest;
// all of src1, src2, and dest must have the same size universe
void bitset_union(struct bitset * dest, struct bitset * src1, struct bitset * src2) {
// bitwise OR |
	if ((src1->universe_size == src2->universe_size) && (src1->universe_size == dest->universe_size))
	{
		for (int i = 0; i < src1->size_in_words; i++)
		{
			dest->bits[i] = src1->bits[i]|src2->bits[i];
		}
	}
	else
		printf("Union not possible, universe sizes are not equal");
}

// place the intersection of src1 and src2 into dest
// all of src1, src2, and dest must have the same size universe
void bitset_intersect(struct bitset * dest, struct bitset * src1, struct bitset * src2) {
// bitwise AND &
	if ((src1->universe_size == src2->universe_size) && (src1->universe_size == dest->universe_size))
       	{
		for (int i = 0; i < src1->size_in_words; i++)
               	{
                       	dest->bits[i] = src1->bits[i]&src2->bits[i];
               	}
       	}
       	else
               	printf("Intersect not possible, universe sizes are not equal");
}
