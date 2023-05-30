g=0
#This line initializes a global variable g and assigns it the value 0. 
#This variable is used to keep track of the number of moves taken to solve the puzzle.

#def print_board(elements):This line defines a function called print_board that takes a list elements as input. 
# It prints the current state of the puzzle board in a 3x3 grid format. If an element is -1, 
# it is represented as an underscore "_".
def print_board(elements):
    for i in range(9):
        if i%3 == 0:
            print()
        if elements[i]==-1:
            print("_", end = " ")
        else:
            print(elements[i], end = " ")
    print()

#def solvable(start): This line defines a function called solvable that takes a list start as input. 
# It checks if the given puzzle configuration is solvable or not.
#  It counts the number of inversions in the list and returns True if the number of inversions is even, 
# indicating that the puzzle is solvable. Otherwise, it returns False.
def solvable(start):
    inv=0

    for i in range(9):
        if start[i] <= 0:
            continue
        for j in range(i+1,9):
            if start[j]==-1:
                continue
            if start[i]>start[j]:
                inv+=1
    if inv%2==0:
        return True
    return False

#def heuristic(start, goal): This line defines a function called heuristic that takes two lists start and goal as input. 
# It calculates the heuristic value (estimated cost) for a given state start based on the goal state goal. 
# The heuristic value is calculated as the sum of the Manhattan distances of each tile from its desired position. 
# The Manhattan distance is calculated as the sum of the horizontal and vertical distances between the current position and the desired position of the tile.
def heuristic(start,goal):
    global g
    h = 0
    for i in range(9):
        for j in range(9):
            if start[i] == goal[j] and start[i] != -1:
                h += (abs(j-i))//3 + (abs(j-i))%3
    return h + g

#def moveleft(start, position): This line defines a function called moveleft that takes a list start and an integer position as input. 
# It swaps the tile at the given position with the tile on its left by modifying the start list.
def moveleft(start,position):
    start[position],start[position-1]= start[position-1],start[position]

def moveright(start,position):
    start[position],start[position+1]= start[position+1],start[position]

def moveup(start,position):
    start[position],start[position-3]= start[position-3],start[position]

def movedown(start,position):
    start[position],start[position+3]= start[position+3],start[position]

#def movetile(start, goal): This line defines a function called movetile that takes two lists start and goal as input.
#  It finds the empty tile in the start list and generates four possible states by moving the empty tile in different directions (left, right, up, down). 
# It calculates the heuristic value for each generated state and selects the state with the minimum heuristic value as the next move to make. 
# It then modifies the start list by moving the empty tile in the chosen direction.
def movetile(start,goal):
    emptyat= start.index(-1)
    row = emptyat//3
    col = emptyat%3
    t1,t2,t3,t4 = start[:],start[:],start[:],start[:]
    f1,f2,f3,f4 = 100,100,100,100

    if col -1 >=0:
        moveleft(t1, emptyat)
        f1 = heuristic(t1, goal)
    if col+1<3:
        moveright(t2, emptyat)
        f2 = heuristic(t2, goal)
    if row + 1 <3:
        movedown(t3, emptyat)
        f3 = heuristic(t3, goal)
    if row-1>=0:
        moveup(t4, emptyat)
        f4 = heuristic(t4, goal)

    min_heuristic = min(f1, f2,f3,f4)

    if f1==min_heuristic:
        moveleft(start, emptyat)
    elif f2==min_heuristic:
        moveright(start, emptyat)
    elif f3==min_heuristic:
        movedown(start, emptyat)
    elif f4 == min_heuristic:
        moveup(start, emptyat)
        
#def solveEight(start, goal): This line defines a function called solveEight that takes two lists start and goal as input. 
# It is a recursive function that attempts to solve the 8-puzzle problem. 
# It calls the movetile function to make a move, prints the current state of the puzzle board, and calculates the heuristic value. 
# If the heuristic value is equal to the number of moves taken (g), it means the puzzle is solved, and the function returns. Otherwise, 
# it recursively calls itself to continue solving the puzzle.

def solveEight(start,goal):
    global g
    g+=1
    movetile(start,goal)
    print_board(start)
    f = heuristic(start,goal)
    if f == g:
        print("Solved in {} moves".format(f))
        return

    solveEight(start,goal)

#def main(): This line defines the main function of the program. 
# It prompts the user to enter the start state and goal state of the puzzle, 
# reads the input from the user, and stores them in the start and goal lists. 
# It then prints the start state of the puzzle board.

def main():
    global g
    start = list()
    goal = list()
    print("Enter the start state:(Enter -1 for empty):")
    for i in range(9):
        start.append(int(input()))

    print("Enter the goal state:(Enter -1 for empty):")
    for i in range(9):
        goal.append(int(input()))

    print_board(start)

    # To check if solvable
    if solvable(start):
        solveEight(start,goal)
        print("Solved in {} moves".format(g))
    else:
        print("Not possible to solve")

#if __name__ == '__main__':: This line checks if the script is being run as the main program. 
# If so, it calls the main function to start the execution of the program.   
if __name__ == '__main__':
    main()
