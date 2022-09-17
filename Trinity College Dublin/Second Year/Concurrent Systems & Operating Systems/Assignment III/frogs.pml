 int frogs[5]; // create frogs array; pos 0 = empty, 1-4 are occupied by frogs (one each)

 // Initialising & Setting up Mutex
 mtype = { unlocked, locked };
 mtype mutexM = unlocked;
 int middle = 0;

 inline lockMutex(mutex, middle, id) { 
    atomic {
        mutex == (unlocked) -> mutex = locked;
        middle = id;
    }
}
 inline unlockMutex(mutex, midddle, id) { 
    atomic {
         assert(middle == id); 
         mutex = unlocked; 
         middle = 0;
    }
 }

// boolean-style variable to make sure proctypes stay in sync; jump = false (initalised)
int jump = 0;

// Proctype for jumping left
 proctype JumpLeft(int currentFrog) {
     atomic {
        // if its this frogs first time jumping, print start statement
         if
         :: (frogs[currentFrog] == currentFrog) -> printf("START FROG %d AT %d GOING LEFT", currentFrog, frogs[currentFrog]);
         :: else ->
         fi
        // first frog moves from current pos to the left
        printf("MOVE FROG%d FROM %d TO %d\n", currentFrog, frogs[currentFrog] + 1, frogs[0] + 1);

        // swap the current frog pos with empty pos
        int x = frogs[currentFrog];
        frogs[currentFrog] = frogs[0];
        frogs[0] = x;
         
        // when last pos (rightmost) is empty, print frogs that haven't started 
        if
        :: (frogs[0] == 5 && frogs[1] == 0) -> printf("START FROG 1 AT 1 RIGHT\n");
        :: else ->
        fi
         
        if
        :: (frogs[0] == 5 && frogs[2] == 2) -> printf("START FROG 2 AT 3 GOING LEFT\n");
        :: else ->
        fi
         
        if
        :: (frogs[0] == 5 && frogs[3] == 3) -> printf("START FROG 3 AT 4 GOING LEFT\n");
        :: else ->
        fi
         
        // jump = true, continue
        jump = 1;
         
        printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0] + 1,frogs[1] + 1,frogs[2] + 1,frogs[3] + 1,frogs[4] + 1);
    }
 }

 proctype JumpRight()
 {
     atomic
     {
        // if its this frogs first time jumping, print its start statement
        if
        :: (frogs[1] == 0) -> printf("START FROG 1 AT 1 GOING RIGHT");
        :: else ->
        fi
     
        printf("MOVE FROG1 FROM %d TO %d\n", frogs[1] + 1,frogs[0] + 1);
        // swap the current frog pos with empty pos
        int x = frogs[1];
        frogs[1] = frogs[0];
        frogs[0] = x;
         
        // jump = true, continue
        jump = 1;
         
        printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0] + 1,frogs[1] + 1,frogs[2] + 1,frogs[3] + 1,frogs[4] + 1);
     }
 }

 proctype Jump()
 {
     lockMutex(mutexM, middle, _pid);
     
     // if a frog can jump to empty, its up for promela to choose to jump to it
     if
     :: (frogs[0] == frogs[1] + 1 || frogs[0] == frogs[1] + 2) -> run JumpRight();
     :: (frogs[0] == frogs[2] - 1 || frogs[0] == frogs[2] - 2) -> run JumpLeft(2);
     :: (frogs[0] == frogs[3] - 1 || frogs[0] == frogs[3] - 2) -> run JumpLeft(3);
     :: (frogs[0] == frogs[4] - 1 || frogs[0] == frogs[4] - 2) -> run JumpLeft(4);
     :: else -> ;
     fi;
     
     // wait for the jump = true, then set = false;
     jump == 1;
     jump = 0;
     
     unlockMutex(mutexM, middle, _pid);
 }

 init
 {
    // Initialising the frogs array
    frogs[0] = 1;
    frogs[1] = 0;
    frogs[2] = 2;
    frogs[3] = 3;
    frogs[4] = 4;
     
     printf("EMPTY %d, FROG1@%d, FROG2@%d, FROG3@%d, FROG4@%d\n", frogs[0] + 1,frogs[1] + 1,frogs[2] + 1,frogs[3] + 1,frogs[4] + 1);
     
    // run max no. of jumps, (7)
    run Jump();
    run Jump();
    run Jump();
    run Jump();
    run Jump();
    run Jump();
    run Jump();
     
    _nr_pr == 1;
    printf("DONE!");
    assert(frogs[0] == 3 && frogs[1] == 4 && frogs[2] == 0 && frogs[3] == 1 && frogs[4] == 2);
 }
