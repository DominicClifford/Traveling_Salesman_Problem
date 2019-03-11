import java.util.ArrayList;
import java.util.Random;

public class Hill_Climber 
{
	static private Random rand;
    private ArrayList<Integer> solution = new ArrayList<Integer>();

    public Hill_Climber(ArrayList<Integer> list)
    {
        solution = list;
    }

    public Hill_Climber(int n)
    {
        solution = RandPerm(n);
    }

    //-----------Random Tour-----------//
    public static ArrayList<Integer> RandPerm(int N)
    {
    	//N being number of cities in array list
    	
    	//declare new arraylist
        ArrayList<Integer> P = new ArrayList<Integer>();
        
        //if i is less than the number of cities complete loop
        for (int i = 0; i < N; i++)
        {
            P.add((i));
        }

        //declare new arraylist
        ArrayList<Integer> T = new ArrayList<Integer>();

        while (P.size() > 0)
        {
            int i = UI(0, P.size()-1);
            T.add(P.get(i));
            P.remove(i);
        }
        return T;
    }
    
    //-----------Swap Function-----------//
    public void Swap()
    {
        int i =0;
        int j= 0 ;
        while (i == j)
        {
            i = UI(0,solution.size() -1);
            j = UI(0,solution.size() -1);
        }
        int temp = solution.get(i);
        solution.set(i,solution.get(j));
        solution.set(j,temp);
    }

    //-----------Fitness Function-----------//
    public double fitnessFunction(int N, double[][] D)
    {
    	//N being number of cities in array list
    	//D being the generated arraylist of distances
        double s = 0;
        
        //if i is less than the number of cities -1 complete loop
        for (int i = 0; i < (N-1); i++ )
        {
            int a = solution.get(i);
            int b = solution.get(i+1);
            s = s + D[a][b];
        }
        
        int endcity = solution.get(solution.size()-1);
        int startcity = solution.get(0);
        s = s + D[endcity][startcity];
        return s;
    }

    //-----------GetSol Function-----------//
    public ArrayList<Integer> gSolution()
    {
    	//create clone of array
        ArrayList<Integer> clone = new ArrayList<Integer>(solution.size());
        for (int i = 0; i < solution.size();i++)
        {
            clone.add(solution.get(i));
        }
        return(clone);
    }

    //-----------Random Integer Creater-----------//
    static public int UI(int aa,int bb)
    {
    	//Create a uniformly distributed random integer between aa and bb inclusive
        int a = Math.min(aa,bb);
        int b = Math.max(aa,bb);
        if (rand == null)
        {
            rand = new Random();
            rand.setSeed(System.nanoTime());
        }
        int d = b - a + 1;
        int x = rand.nextInt(d) + a;
        return(x);
    }
     
    //-----------Random Double Creater-----------//
    static public double UR(double a,double b)
    {
    	//Create a uniformly distributed random double between a and b inclusive
        if (rand == null)
        {
            rand = new Random();
            rand.setSeed(System.nanoTime());
        }
        return((b-a)*rand.nextDouble()+a);
    }

}