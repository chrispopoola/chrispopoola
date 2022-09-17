// code for a huffman coder


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include <string.h>
#include "huff.h"
const int INT_MAX = 2147483647;

// create a new huffcoder structure
struct huffcoder *  huffcoder_new() {
	struct huffcoder * huffcoder = malloc(sizeof(struct huffcoder));
	for (int i = 0; i < NUM_CHARS; i++) {
		huffcoder->freqs[i] = 0;
		huffcoder->code_lengths[i] = 0;
		huffcoder->codes[i] = 0;
	}
	return huffcoder;
}

// count the frequency of characters in a file; set chars with zero
// frequency to one
void huffcoder_count(struct huffcoder * this, char * filename) {
	FILE * file = fopen(filename, "r");
	assert(file != NULL);
	unsigned char c = fgetc(file);

	while (!feof(file)) {
		this->freqs[c]++;
		c = fgetc(file);
	}
	fclose(file);

	for (int i = 0; i < NUM_CHARS; i++) {
		if(this->freqs[i] == 0)
			this->freqs[i] = 1;
	}
}

// using the character frequencies build the tree of compound
// and simple characters that are used to compute the Huffman codes
void huffcoder_build_tree(struct huffcoder * this) {
	struct huffchar * huffchars[NUM_CHARS] ;
	for (int i = 0; i < NUM_CHARS; i++)
		huffchars[i] = malloc(sizeof(struct huffchar));
        int seqno = NUM_CHARS;
	int listsize = NUM_CHARS;
	for (int i = 0; i < NUM_CHARS; i++) {
		huffchars[i]->freq = this->freqs[i];
		huffchars[i]->is_compound = 0;
		huffchars[i]->seqno = i;
		huffchars[i]->u.c = i;
	}
	while (listsize > 1) {
		int smallest = find_min(huffchars, listsize);
		struct huffchar * min1 = huffchars[smallest];
		huffchars[smallest] = huffchars[listsize-1];
		smallest = find_min(huffchars, listsize-1);
		struct huffchar * min2 = huffchars[smallest];
		huffchars[smallest] = huffchars[listsize-2];

		struct huffchar * newCompound;
		newCompound  = new_compound(min1, min2, seqno);
		seqno++;
		listsize--;
		huffchars[listsize-1] = newCompound;
	}
	for(int i = 0; i < NUM_CHARS; i++) {
		if (huffchars[i] != NULL)
			this->tree = huffchars[i];
	}
}

int find_min(struct huffchar ** this, int size) {
	int pos = 0;
	struct huffchar * smallest = malloc(sizeof(struct huffchar));
       	smallest->freq = INT_MAX;
	smallest->seqno = NUM_CHARS;
   	for (int i = 0; i < size; i++){
		if (this[i] != NULL) {
			if (this[i]->freq <= smallest->freq) {
                         	if (this[i]->freq == smallest->freq) {
                                 	if (this[i]->seqno < smallest->seqno) {
                            	                smallest = this[i];
                                                pos = i;
                                        }
                                 }
                                 else {
              	                      smallest = this[i];
                                      pos = i;
                                 }
                        }
               	}
      	}
	return pos;
}

struct huffchar * new_compound(struct huffchar * one, struct huffchar * two, int seqno) {
	struct huffchar * compound = malloc(sizeof(struct huffchar));
	compound->freq = one->freq + two->freq;
	compound->is_compound = 1;
	compound->seqno = seqno;

	if (one->freq < two->freq) {
		compound->u.compound.left = one;
                compound->u.compound.right = two;
	}
        else if (two->freq < one->freq) {
                compound->u.compound.left = two;
                compound->u.compound.right = one;
        }
        else if (one->freq == two->freq) {
		if (one->seqno < two->seqno) {
			compound->u.compound.left = one;
                        compound->u.compound.right = two;
                }
                else {
                   	compound->u.compound.left = two;
                        compound->u.compound.right = one;
                }
	}
	return compound;
}

// using the Huffman tree, build a table of the Huffman codes
// with the huffcoder object
void huffcoder_tree2table(struct huffcoder * this) {
	recursive_tree2table(this, this->tree, "", 0);
}
void recursive_tree2table(struct huffcoder * this, struct huffchar * node, char * code, int length) {
	if (node->is_compound == 1) {
		length++;
		recursive_tree2table(this, node->u.compound.left, strcat(code, "0"), length);
		recursive_tree2table(this, node->u.compound.right, strcat(code, "1"), length);
	}
	else {
		this->code_lengths[node->u.c] = length;
		this->codes[node->u.c] = code;
	}
}
// print the Huffman codes for each character in order;
// you should not modify this function
void huffcoder_print_codes(struct huffcoder * this) {
  for ( int i = 0; i < NUM_CHARS; i++ ) {
    // print the code
    printf("char: %d, freq: %d, code: %s\n", i, this->freqs[i], this->codes[i]);;
  }
}

// encode the input file and write the encoding to the output file
void huffcoder_encode(struct huffcoder * this, char * input_filename, char * output_filename) {

}

// decode the input file and write the decoding to the output file
void huffcoder_decode(struct huffcoder * this, char * input_filename, char * output_filename) {

}
