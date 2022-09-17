; Interrupt Handling Sample
; (c) Mike Brady, 2021.

	area	tcd,code,readonly
	export	__main
__main

; Definitions  -- references to 'UM' are to the User Manual.

; Timer Stuff -- UM, Table 173

T0	equ	0xE0004000		; Timer 0 Base Address
T1	equ	0xE0008000

IR	equ	0			; Add this to a timer's base address to get actual register address
TCR	equ	4
MCR	equ	0x14
MR0	equ	0x18

TimerCommandReset	equ	2
TimerCommandRun	equ	1
TimerModeResetAndInterrupt	equ	3
TimerResetTimer0Interrupt	equ	1
TimerResetAllInterrupts	equ	0xFF

; VIC Stuff -- UM, Table 41
VIC			equ	0xFFFFF000		; VIC Base Address
IntEnable		equ	0x10
VectAddr		equ	0x30
VectAddr0		equ	0x100
VectCtrl0		equ	0x200

Timer0ChannelNumber	equ	4	; UM, Table 63
Timer0Mask		equ	1<<Timer0ChannelNumber	; UM, Table 63
IRQslot_en		equ	5		; UM, Table 58

; initialisation code

; Initialise the VIC
	ldr	r0,=VIC			; looking at you, VIC!

	ldr	r1,=irqhan
	str	r1,[r0,#VectAddr0] 	; associate our interrupt handler with Vectored Interrupt 0

	mov	r1,#Timer0ChannelNumber+(1<<IRQslot_en)
	str	r1,[r0,#VectCtrl0] 	; make Timer 0 interrupts the source of Vectored Interrupt 0

	mov	r1,#Timer0Mask
	str	r1,[r0,#IntEnable]	; enable Timer 0 interrupts to be recognised by the VIC

	mov	r1,#0
	str	r1,[r0,#VectAddr]   	; remove any pending interrupt (may not be needed)

; Initialise Timer 0
	ldr	r0,=T0			; looking at you, Timer 0!

	mov	r1,#TimerCommandReset
	str	r1,[r0,#TCR]

	mov	r1,#TimerResetAllInterrupts
	str	r1,[r0,#IR]

	ldr	r1,=(14745600/1600)-1	 ; 625 us = 1/1600 second
	str	r1,[r0,#MR0]

	mov	r1,#TimerModeResetAndInterrupt
	str	r1,[r0,#MCR]

	mov	r1,#TimerCommandRun
	str	r1,[r0,#TCR]

;from here, initialisation is finished, so it should be the main body of the main program

I01PIN		EQU	0xE0028010
I01DIR		EQU	0xE0028018
I01SET		EQU	0xE0028014
I01CLR		EQU	0xE002801C
	
	
	ldr	r1,=I01DIR		
	ldr	r0,=0x00f00f00		; mask for pins 31-24, 19-12, & 7-0
	str	r0,[r1]			
	ldr	r1,=I01SET
	str	r0,[r1]			; set LEDs to be off
	ldr	r2,=I01CLR
	
loop
	ldr	r4,=isInterupt		; address for boolean isInterupt
	ldr	r5,[r4]			; boolean temp = isInterupt
	cmp	r5,#1			; if (temp == true) 
	bne	loop
	
	ldr	r6,=seconds0		; address for seconds[0]
	ldr	r7,=seconds1		; address for seconds[1]
	ldr	r8,[r7]			; load seconds[1]
	cmp	r8,#9			; if (seconds[1] == 9) 
	bne	lessThanTenSecs		; 
	ldr	r8,[r6]			; x = seconds[0];
	add	r8,r8,#1		; x++;
	cmp	r8,#6			; if (x != 6) 
	beq	incrementMins		; mins++;
	str	r8,[r6]			; seconds[0] = x;
	ldr	r8,=0			; x = 0; 
	str	r8,[r7]			; (reset seconds)
	b	updateTime			
lessThanTenSecs				; if (x < 9)
	add	r8,r8,#1		; x++;
	str	r8,[r7]			; seconds[1] = x;
	b	updateTime

incrementMins
	ldr	r6,=seconds0		; address for seconds[0]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; seconds[0] = 0;
	
	ldr	r6,=minutes0		; address for minutes[0]
	ldr	r7,=minutes1		; address for minutes[1]
	ldr	r8,[r7]			; x = minutes[1];
	cmp	r8,#9			; if (x == 9)
	bne	lessThanTenMins
	ldr	r8,[r6]			; x = minutes[0];
	add	r8,r8,#1		; x++;
	cmp	r8,#6			; if (x != 6)
	beq	incrementHours		
	str	r8,[r6]			; minutes[0] = x
	ldr	r8,=0			; x = 0;
	str	r8,[r7]			; (reset minutes)
	b	updateTime
lessThanTenMins				; if (x < 9)
	add	r8,r8,#1		; x++
	str	r8,[r7]			; minutes[1] = x;
	b	updateTime

incrementHours
	ldr	r6,=seconds0		; address for seconds[0]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; seconds[0] = x;
	ldr	r6,=seconds1		; address for seconds[1]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; seconds[1] = x;
	ldr	r6,=minutes0		; address for minutes[0]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; minutes[0] = x;
	ldr	r6,=minutes1		; address for minutes[1]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; minutes[1] = x;
	
	ldr	r6,=hours0		; address for hours[0]
	ldr	r7,=hours1		; address for hours[1]
	ldr	r8,[r7]			; x = hours[1]
	cmp	r8,#9			; if (x == 9)
	bne	lessThanTenHours
	ldr	r8,[r6]			; x = hours[0]
	add	r8,r8,#1			; x++
	str	r8,[r6]			; hours[0] = x;
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; (reset hours)
	b	updateTime
lessThanTenHours			; if (x < 9)
	add	r8,r8,#1		; x++;
	str	r8,[r7]			; hours[1] = x;
	ldr	r8,[r6]			; x = hours[0];
	ldr	r9,[r7]			; y = hours[1];
	orr	r9,r9,r8,lsl #4		; x || y << 4 (getting hours in form 0x000000hours[0]hours[1])
	cmp	r9,#0x00000024		; if (hours == 24(mask)) i.e. hours[0] == 2 && hours[1] == 4
	beq	resetTime
	b	updateTime
	
resetTime
	ldr	r6,=seconds0		; address for seconds[0]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; seconds[0] = x;
	ldr	r6,=seconds1		; address for seconds[1]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; seconds[1] = x;
	ldr	r6,=minutes0		; address for minutes[0]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; minutes[0] = x;
	ldr	r6,=minutes1		; address for minutes[1]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; minutes[1] = x;
	ldr	r6,=hours0		; address for hours[0]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; hours[0] = x;
	ldr	r6,=hours1		; address for hours[1]
	ldr	r8,=0			; x = 0;
	str	r8,[r6]			; hours[1] = x;	
	b	updateTime

updateTime
	ldr	r1,=I01DIR		
	ldr	r0,=0x00f00f00		; mask for pins 31-24, 19-12, & 7-0
	str	r0,[r1]			;	
; 0xHH:MM:SS
	; 0xH
	ldr	r6,=hours0		; address for hours[0]
	ldr	r7,[r6]			; z = hours[0]
	mov	r5, r7, lsl#28		; store in z & << 28
	; 0xHH
	ldr	r6,=hours1		; address for hours[1]
	ldr	r7,[r6]			; z = hours[1]
	orr	r5, r7, lsl#24		; store in z & << 24	
	; 0xHH:M
	ldr	r6,=hours1		; address for minutes[0]
	ldr	r7,[r6]			; z = minutes[0]
	orr	r5, r7, lsl#16		; store in z & << 16
	; 0xHH:MM
	ldr	r6,=hours1		; address for minutes[1]
	ldr	r7,[r6]			; z = minutes[1]
	orr	r5, r7, lsl#12		; store in z & << 12
	; 0xHH:MM:S
	ldr	r6,=hours1		; address for seconds[0]
	ldr	r7,[r6]			; z = seconds[0]
	orr	r5, r7, lsl#4		; store in z & << 4
	; 0xHH:MM:SS
	ldr	r6,=hours1		; address for seconds[1]
	ldr	r7,[r6]			; z = seconds[1]
	orr	r5, r7, lsl#0		; store in z & << 0
	
	orr	r5,r5,r0		; orr with mask to get 1111 in correct places
	str	r5,[r1]			; DIR reg (so it can display on GPIO1)
	ldr	r5,=isInterupt		; boolean isInterupt
	ldr	r6,=0			; boolean temp = false;
	str	r6,[r5]			; isInterupt = temp;
	b	loop

fin b fin

	AREA	InterruptStuff, CODE, READONLY
irqhan	sub	lr,lr,#4
	stmfd	sp!,{r0-r1,lr}	; the lr will be restored to the pc

;this is the body of the interrupt handler
	ldr	r0,=isInterupt		; address for isInterrupt
	ldr	r1,=1			; boolean temp = true
	str	r1,[r0]			; isInterrupt = temp
;here you'd put the unique part of your interrupt handler
;all the other stuff is "housekeeping" to save registers and acknowledge interrupts

;this is where we stop the timer from making the interrupt request to the VIC
;i.e. we 'acknowledge' the interrupt
	ldr	r0,=T0
	mov	r1,#TimerResetTimer0Interrupt
	str	r1,[r0,#IR]	   	; remove MR0 interrupt request from timer

;here we stop the VIC from making the interrupt request to the CPU:
	ldr	r0,=VIC
	mov	r1,#0
	str	r1,[r0,#VectAddr]	; reset VIC

	ldmfd	sp!,{r0-r1,pc}^	; return from interrupt, restoring pc from lr
				; and also restoring the CPSR

; all the memory for the time addresses
	AREA 	MutableDate, DATA, READWRITE

isInterupt	space 4
hours0		space 4
hours1		space 4
minutes0	space 4
minutes1	space 4
seconds0	space 4
seconds1	space 4


                END
