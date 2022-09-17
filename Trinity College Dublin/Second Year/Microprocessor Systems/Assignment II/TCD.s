; Sample program makes the 4 LEDs P1.16, P1.17, P1.18, P1.19 go on and off in sequence
; (c) Mike Brady, 2020.

	area	tcd,code,readonly
	export	__main
__main
IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C
IO1PIN	EQU	0xE0028010

	ldr	r0,=IO1PIN
	ldr	r1,=IO1DIR
	ldr	r2,=0x0ff00000		; select P1.23--P1.16
	str	r2,[r1]			; make them outputs
	mov	r10,#0x00100000
	
	bl	polling

STOP	b	STOP

polling
	stmfd	SP!,{LR,r3-r4}
	ldr	r3,[r0]			; currentState = IO1PIN
	
loop
	mov	r4, r3			; lastState = currentState
	ldr	r3,[r0]			; currentState = IO1PIN
	
	; check pin 24
	tst	r3,#0x01000000		;
	bne	check25
	tst	r3,#0x01000000		;
	beq	check25
	b	add1
check25
	; check pin 25
	tst	r3,#0x02000000		;
	bne	check26
	tst	r3,#0x02000000
	beq	check26
	b	subtract
check26
	; check pin 26
	tst	r3,#0x04000000		;
	bne	check27
	tst	r3,#0x04000000		;
	beq	check27
	b	shiftLeft
check27
	; check pin 27
	tst	r3,#0x08000000		;
	bne	loop
	tst	r3,#0x08000000
	beq	loop
	b	shiftRight
	
add1
	ldr	r4,[r1]
	add	r4,r10,r4
	str	r4,[r1]
	b	out
subtract
	ldr	r4,[r1]
	sub	r4,r10,r4
	str	r4,[r1]
	b	out
shiftLeft
	ldr	r4,[r1]
	lsl	r4,#1
	str	r4,[r1]
	b	out
shiftRight
	ldr	r4,[r1]
	lsr	r4,#1
	str	r4,[r1]
	b	out
out
	ldmfd	SP!,{LR,r3-r4}
	bx	LR
	
	end