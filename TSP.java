import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class TSP {

    //-----------Array File Reader-----------//
    @SuppressWarnings("resource")
	static public double[][] ReadArrayFile(String filename,String sep)
    {
    	//This method reads in a text file and parses all of the numbers in it
    	//This method is for reading in a square 2D numeric array from a text file
    	//'sep' is the separator between columns
        double res[][] = null;
        try
        {
            BufferedReader input = null;
            input = new BufferedReader(new FileReader(filename));
            String line = null;
            int ncol = 0;
            int nrow = 0;

            while ((line = input.readLine()) != null)
            {
                ++nrow;
                String[] columns = line.split(sep);
                ncol = Math.max(ncol,columns.length);
            }
            res = new double[nrow][ncol];
            input = new BufferedReader(new FileReader(filename));
            int i=0,j=0;
            while ((line = input.readLine()) != null)
            {

                String[] columns = line.split(sep);
                for(j=0;j<columns.length;++j)
                {
                    res[i][j] = Double.parseDouble(columns[j]);
                }
                ++i;
            }
        }
        catch(Exception E)
        {
            System.out.println("+++ReadArrayFile: "+E.getMessage());
        }
        return(res);
    }
    
    //-----------Integer File Reader-----------//
    static public ArrayList<Integer> ReadIntegerFile(String filename)
    {
    	//This method reads in a text file and parses all of the numbers in it
    	//It takes in as input a string filename and returns an array list of Integers
        ArrayList<Integer> res = new ArrayList<Integer>();
        Reader r;
        try
        {
            r = new BufferedReader(new FileReader(filename));
            StreamTokenizer stok = new StreamTokenizer(r);
            stok.parseNumbers();
            stok.nextToken();
            while (stok.ttype != StreamTokenizer.TT_EOF)
            {
                if (stok.ttype == StreamTokenizer.TT_NUMBER)
                {
                    res.add((int)(stok.nval));
                }
                stok.nextToken();
            }
        }
        catch(Exception E)
        {
            System.out.println("+++ReadIntegerFile: "+E.getMessage());
        }
        return(res);
    }
	
    //-----------2D Array Printer (Not Used)-----------//
    static public void PrintArray(double x[][])
    {
    	//Print a 2D double array to the console Window
        for(int i=0;i<x.length;++i)
        {
            for(int j=0;j<x[i].length;++j)
            {
                System.out.print(x[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}