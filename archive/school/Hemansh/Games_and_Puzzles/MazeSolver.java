package Games_and_Puzzles;

class MazeSolver 
{
    /*the below variables are the "INPUT" variables*/
    static int numrows;		static 	int numcols;
    static int startrow;		static int startcol;
    static int endrow;		static int endcol;
    static 	int myMaze[][];
    /*these two variables keep track of the shortest path found so far*/
    static 	int shortestpath[];
    static 	int shortestlength;	
    boolean beenhere(int row, int col, int pathsofar[], int lengthsofar){
        /*this private boolean function tells if this spot (row,col) has
        been visited before*/
        int x;
        int target = row*numcols+col;  /*this computation gives a unique
        id to each maze square*/
        for (x=0;x<lengthsofar;x++)
            if (pathsofar[x]==target) return true;
        return false;
    }	
    public void showmypath(int mypath[], int mylength){
        /*this function prints out the maze and the path traveled so
        far.*/
        int r,c;
        for (r=0;r<numrows;r++){
            for(c=0;c<numcols;c++){
                if (myMaze[r][c]==1)
                    System.out.print("\t|");			     /*  | for walls   */
                else if (r==startrow && c==startcol)
                    System.out.print("\tS");			     /*  S for start   */
                else if (r==endrow && c==endcol)
                    System.out.print("\tE");			     /*  E for exit   */
                else if (beenhere(r,c,mypath,mylength))
                    System.out.print("\t•");			     /*  • for traveled   */
                else
                    System.out.print("\t");			     /*    empty space  */
            }
            System.out.println("");
        }
    }
    public void findpath(int row, int col, int pathsofar[], int lengthsofar){
        /*This is the recursive function that finds the paths.  When it
        does find a valid path, it outputs it then stores it into
        shortestpath if is is shorter that what is currently held*/
        /*These 3 statements are the termination conditions:
        out of bounds,  wall, and previously visited, respectively*/
        if (row<0 || col<0 || row>=numrows || col>=numcols)
            return;
        if (myMaze[row][col]==1) return ;
        if (beenhere(row, col, pathsofar, lengthsofar)) return;
        int mypath[]=new int[lengthsofar+1];
        System.arraycopy(pathsofar, 0, mypath, 0, lengthsofar);  
        /*for local copy for proper backtracking*/		
        mypath[lengthsofar++]=row*numcols+col;  /*adds this square to
        traveled path*/
        if (row==endrow && col==endcol){		
            /*Reached the end, thus finding a valid path*/
            System.out.println("Found path of length "+lengthsofar+"!:");
            showmypath(mypath, lengthsofar);
            if (lengthsofar<=shortestlength){ /*New shortest path?*/
                shortestlength=lengthsofar;
                System.arraycopy(mypath, 0, shortestpath, 0, lengthsofar);
                System.out.println(" (New shortest path of length " + lengthsofar + ")");
            }
            System.out.println("");
            return;
        }
        /*Below, recursively call to go to other squares*/
        findpath(row-1, col, mypath, lengthsofar);
        findpath(row, col-1, mypath, lengthsofar);
        findpath(row, col+1, mypath, lengthsofar);
        findpath(row+1, col, mypath, lengthsofar);
    }			
    public static void main(int a[][], int sr, int sc, int er,int ec) 
    {
        /* The main function initializes appropriate variables,
        outputs the initial maze, finds the path, and outputs it*/
        numrows=a.length;		numcols=a[0].length;
        startrow=sr;		startcol=sc;
        endrow=er;		endcol=ec;
        myMaze=a;
        shortestpath=new int[numrows*numcols];
        int r,c,x;				/*various counters*/
        int pathsofar[];		/*Path to get started with*/
        int lengthsofar;		/*length to get started with*/
        MazeSolver daMaze=new MazeSolver();	  /*the maze object*/
        pathsofar=new int[daMaze.numrows*daMaze.numcols];  /*for max size*/		
        for (x=0;x<daMaze.numrows*daMaze.numcols;x++){
            daMaze.shortestpath[x]=-1;  /* initializing the path arrays*/
            pathsofar[x]=-1;
        }
        /*initial lengths*/
        daMaze.shortestlength=daMaze.numrows*daMaze.numcols+1;
        lengthsofar=0;
        System.out.println("Here's the maze:");
        for (r=0;r<daMaze.numrows;r++){
            for (c=0;c<daMaze.numcols;c++){
                if (r==startrow && c==startcol)		/*outputing the initial*/
                    System.out.print("\tS");			/* maze state*/
                else if (r==endrow && c==endcol)
                    System.out.print("\tE");
                else if (daMaze.myMaze[r][c]==0)
                    System.out.print("\t");
                else System.out.print("\t|");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Finding Paths...");
        daMaze.findpath(startrow, startcol, pathsofar, lengthsofar);
        System.out.println("");
        System.out.println("The shortest path found was the following of length "+ daMaze.shortestlength);
        daMaze.showmypath(daMaze.shortestpath, daMaze.shortestlength);
    }
}
/*

Here's the maze:
	|	|	|	|	|	|	|	|	|	|	|	|	|
	|	S	|		|		|						|
	|		|				|		|	|	|		|
	|				|	|	|						|
	|		|						|	|	|		|
	|		|		|	|	|		|				|
	|		|		|				|	|	|		|
	|		|		|	|	|		|		|		|
	|									E	|		|
	|	|	|	|	|	|	|	|	|	|	|	|	|

Finding Paths...
Found path of length 16!:
	|	|	|	|	|	|	|	|	|	|	|	|	|
	|	S	|		|		|						|
	|	•	|				|		|	|	|		|
	|	•	•	•	|	|	|						|
	|		|	•	•	•	•	•	|	|	|		|
	|		|		|	|	|	•	|				|
	|		|		|			•	|	|	|		|
	|		|		|	|	|	•	|		|		|
	|							•	•	E	|		|
	|	|	|	|	|	|	|	|	|	|	|	|	|
 (New shortest path of length 16)

Found path of length 16!:
	|	|	|	|	|	|	|	|	|	|	|	|	|
	|	S	|		|		|						|
	|	•	|				|		|	|	|		|
	|	•	•	•	|	|	|						|
	|		|	•					|	|	|		|
	|		|	•	|	|	|		|				|
	|		|	•	|				|	|	|		|
	|		|	•	|	|	|		|		|		|
	|			•	•	•	•	•	•	E	|		|
	|	|	|	|	|	|	|	|	|	|	|	|	|
 (New shortest path of length 16)

Found path of length 24!:
	|	|	|	|	|	|	|	|	|	|	|	|	|
	|	S	|		|		|						|
	|	•	|				|		|	|	|		|
	|	•			|	|	|						|
	|	•	|	•	•	•	•	•	|	|	|		|
	|	•	|	•	|	|	|	•	|				|
	|	•	|	•	|			•	|	|	|		|
	|	•	|	•	|	|	|	•	|		|		|
	|	•	•	•				•	•	E	|		|
	|	|	|	|	|	|	|	|	|	|	|	|	|

Found path of length 16!:
	|	|	|	|	|	|	|	|	|	|	|	|	|
	|	S	|		|		|						|
	|	•	|				|		|	|	|		|
	|	•			|	|	|						|
	|	•	|						|	|	|		|
	|	•	|		|	|	|		|				|
	|	•	|		|				|	|	|		|
	|	•	|		|	|	|		|		|		|
	|	•	•	•	•	•	•	•	•	E	|		|
	|	|	|	|	|	|	|	|	|	|	|	|	|
 (New shortest path of length 16)


The shortest path found was the following of length 16
	|	|	|	|	|	|	|	|	|	|	|	|	|
	|	S	|		|		|						|
	|	•	|				|		|	|	|		|
	|	•			|	|	|						|
	|	•	|						|	|	|		|
	|	•	|		|	|	|		|				|
	|	•	|		|				|	|	|		|
	|	•	|		|	|	|		|		|		|
	|	•	•	•	•	•	•	•	•	E	|		|
	|	|	|	|	|	|	|	|	|	|	|	|	|

Here's the maze:
	S		
		|	
			|
	|		E

Finding Paths...
Found path of length 6!:
	S		
	•	|	
	•	•	|
	|	•	E
 (New shortest path of length 6)


The shortest path found was the following of length 6
	S		
	•	|	
	•	•	|
	|	•	E

 */