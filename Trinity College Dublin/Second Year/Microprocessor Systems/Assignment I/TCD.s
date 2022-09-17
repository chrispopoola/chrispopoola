
	area	tcd, code, readonly
	export	__main
__main
	MOV	R1, #0
	MOV	R2, #0
	LDR	R3, =test_values	; address for test values
	
	LDR	R4, =0x40000000		; address for factorial results
	LDR	R0, [R3], #4		; load 1st test value
	BL	fact
	BL	store_correctly

	LDR	R4, =0x40000018		; address for factorial results
	LDR	R0, [R3], #4		; load 1st test value
	BL	fact
	BL	store_correctly
	
	LDR	R4, =0x40000030		; address for factorial results
	LDR	R0, [R3], #4		; load 1st test value
	BL	fact
	BL	store_correctly
	
	LDR	R4, =0x40000048		; address for factorial results
	LDR	R0, [R3], #4		; load 1st test value
	BL	fact
	BL	store_correctly

STOP	B	STOP

; store values correctly subroutine
store_correctly
	MOV	R5, #3
loop1
	CMP	R5, #0
	BLT	done
	STRB	R0, [R4, R5]		; store MSB
	MOV	R0, R0, LSR #8
	SUB	R5, R5, #1
	B	loop1
done
	MOV	R5, #7
loop2
	CMP	R5, #4
	BLT	donedone
	STRB	R1, [R4, R5] 		; store LSB
	MOV	R1, R1, LSR #8
	SUB	R5, R5, #1
	B	loop2
donedone
	BX	LR

; beginning of subroutine
fact
	STMFD	SP!, {LR, R5-R7}
	MOV	R7, #0x00000000		; for the C bit of CPSR
	
	CMP	R2, #0			; if ( result(LSB) == 0)
	BNE	next			;
	MOV	R2, #1			; 	initialise result(LSB) = 1
next	
	CMP	R0, #0			; if ( N != 0) 
	BEQ	return_result
	SMULL	R2, R5, R0, R2		; R5-R2 = R0 * R2; multiplying 2 32bit values, store MSB=R5, store LSB=R2 (this is LSB)
	SMULL	R1, R6, R0, R1		; R6-R1 = R0 * R1; multiplying 2 32bit values, store MSB=R6, store LSB=R1 (this is MSB)
	ADD	R1, R5, R1		; add the overflow from MSB=R5 (MSB of the main LSB) to LSB=R1 (LSB of the main MSB)
	
	CMP	R6, #0			; if (MSB=R6 (MSB of main MSB) == 0) 
	BNE	factorial_end
	SUB	R0, R0, #1		; N--;
	BL	fact			; call fact
return_result
	CMP	R2, #0			; if (result(LSB) != 0)
	BEQ	factorial_end
	MOV	R0, R1			; move MSB of 64bit into R0
	MOV	R1, R2			; move LSB of 64bit into R1
	MOV	R2, #0			; re-initialise result(LSB) = 0 
factorial_end
	MOV	R7, #0x20000000		; 
	MSR	CPSR_f, R7		; set the C bit
	
	LDMFD	SP!, {LR, R5-R7}	; 
	BX	LR			; 

	area	tcdrodata, data, readonly
	
test_values	dcd	5
		dcd	14
		dcd	20
		dcd	30

	area	tcddata, data, readwrite

result	SPACE	4*8		; 4 elements (8 bytes each)
	
	END