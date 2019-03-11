import java.util.ArrayList;

import static java.lang.Math.exp;

public class Lab15 
{
    public static void main(String args[]) 
    {
    	
        double [][] dist;
        double effPrecent;
        int iterations = 100000;
        int restarts = 10;
        
        //import data from .txt file
        //sends file to TSP.java class (ReadArrayFile) to read the 2D array and put into new array (res)
        dist = TSP.ReadArrayFile("J:\\1. Files\\Education\\4. Uni Computer Science\\Year 2\\CS2004 - Algoritms\\Assignment 3\\CS2004 TSP Data (2017-2018)\\TSP_442.txt" , " ");
        

        //-----------Optimal Fitness-----------//
        ArrayList<Integer> optimal_txt;
        
        //import optimal solution files (.txt) to test quality of solution
        //sends file to TSP.java class (ReadIntegerFile) to read in all intergers and put into new array list (res)
        optimal_txt = TSP.ReadIntegerFile("J:\\1. Files\\Education\\4. Uni Computer Science\\Year 2\\CS2004 - Algoritms\\Assignment 3\\CS2004 TSP Data (2017-2018)\\TSP_442_OPT.txt");
        
        
        //start timer
        long optimalTime = System.currentTimeMillis();
        
        //print optiaml path as read from array 
        System.out.println("Optimal path");
        System.out.println(optimal_txt);
        
        //sends array list to HC.java (fitness) to find fitness of solution
        Hill_Climber optimal = new Hill_Climber(optimal_txt);	
        double optimalfitness = optimal.fitnessFunction(dist.length,dist);
        
        //print fitness/Length of tour 
        System.out.println("Fitness = " + optimalfitness);
        
        //end timer and calculate total time
        long optimalEndTime = System.currentTimeMillis() - optimalTime;
		long optimalTotalTime = (long) (optimalEndTime);
		System.out.println("Time to complete = " + optimalTotalTime + (" Milli Seconds"));

        //-----------Simple Hill Climbing-----------//
        System.out.println();
        
        //start timer
        long simpleTime = System.currentTimeMillis();        
        
        //call simpleHill_Climber Method
        System.out.println("Simple Hill Climber solution:");
        Hill_Climber simple = simpleHill_Climber(dist.length, dist, iterations);    //number of cities, distance array, how many iterations
       
        //complete the final iteration, and get the final fitness of tour
        //calculate effeciency % by dividing the optimal fitness by fitness of the simple hill climber
        effPrecent = (optimalfitness / simple.fitnessFunction(dist.length, dist)) * 100;	
        System.out.println("Efficiency: " + effPrecent +"%");
       
        //end timer and calculate total time
        long simpleEndTime = System.currentTimeMillis() - simpleTime;
		long simpleTotalTime = (long) (simpleEndTime);
		System.out.println("Time to complete = " + simpleTotalTime + (" Milli Seconds"));

        //-----------Stochastic Hill Climbing-----------//
        System.out.println();
        
        //start timer
        long stockTime = System.currentTimeMillis(); 
        
        //call stochasticHill_Climber Method
        System.out.println("Stochastic HC solution:");
        Hill_Climber stochastic = stochasticHill_Climber(dist.length, dist, iterations);    //number of cities, distance array, how many iterations
        
        //complete the final iteration, and get the final fitness of tour
        //calculate effeciency % by dividing the optimal fitness by fitness of the simple hill climber
        effPrecent = (optimalfitness / stochastic.fitnessFunction(dist.length, dist)) * 100;
        System.out.println("Efficiency: " + effPrecent +"%");
        
        //end timer and calculate total time
        long stockEndTime = System.currentTimeMillis() - stockTime;
		long stockTotalTime = (long) (stockEndTime);
		System.out.println("Time to complete = " + stockTotalTime + (" Milli Seconds"));

        //-----------Random Restart Hill Climbing-----------//
        System.out.println();
        
        //start timer
        long randResTime = System.currentTimeMillis();
        
        //call restartHill_Climber Method
        System.out.println("Random Restart HC solution:");
        Hill_Climber restart = restartHill_Climber(dist.length, dist, restarts, iterations);     //number of cities, distance array, how many restarts, iterations per restart
        
        //complete the final iteration, and get the final fitness of tour
        //calculate effeciency % by dividing the optimal fitness by fitness of the simple hill climber
        effPrecent = (optimalfitness / restart.fitnessFunction(dist.length, dist)) * 100;
        System.out.println("Efficiency: " + effPrecent +"%");
        
        //end timer and calculate total time
        long randResEndTime = System.currentTimeMillis() - randResTime;
		long randResTotalTime = (long) (randResEndTime);
		System.out.println("Time to complete = " + randResTotalTime + (" Milli Seconds"));

    }
    
    //-----------Simple Hill Climber-----------//
    public static Hill_Climber simpleHill_Climber (int N, double[][] dist,int iterations) 
    //N being number of cities in array list
    //dist being the array list created in TSP.java
    //iterations being number of iterations to be run
    {
    	//call Hill_Climber.java
    	Hill_Climber solu = new Hill_Climber(N);
    	Hill_Climber solu_1;
        double fitness;
        double fitness_1;

        //if i is less than number of iteration continue for loop, else exit loop 
        for (int i=0; i < iterations; i++)
        {
        	//create cloned of array, gSolution method in Hill_Climber.java
            solu_1 = new Hill_Climber (solu.gSolution());
            //get fitness of orignal array
            fitness = solu.fitnessFunction(N,dist);
            //send clone array to Swap method in Hill_Climber.java
            solu_1.Swap();
            //gets fitness of cloned array
            fitness_1 = solu_1.fitnessFunction(N,dist);

            //if cloned array fitness is less than original array, replace orginal array with cloned array
            if (fitness_1 < fitness)
            {
                solu = solu_1;
            }
            //continue until all iterations are completed
        }
        //print path created by simpleHC
        System.out.println("Solution = " + solu.gSolution());
        //print solution of TSP (Fitness/Length of tour)
        System.out.println("Fitness = " + solu.fitnessFunction(dist.length,dist));
        return solu;
    }

    //-----------Stochastic Hill Climber-----------//
    public static Hill_Climber stochasticHill_Climber (int N, double[][] dist,int iterations)
    //N being number of cities in array list
    //dist being the array list created in TSP.java
    //Iterations being number of iterations to be run
    {
    	//call Hill_Climber.java
        Hill_Climber solu = new Hill_Climber(N);
        Hill_Climber solu_1;
        //declare varibles
        double fitness, fitness_1, probability;
        double Tvalue = 0.1;

      //if i is less than number of iteration continue for loop, else exit loop 
        for (int i=0; i < iterations; i++)
        {
        	//create cloned of array, gSolution method in Hill_Climber.java
            solu_1 = new Hill_Climber(solu.gSolution());
            //get fitness of orignal array
            fitness = solu.fitnessFunction(N,dist);
            //send clone array to Swap method in Hill_Climber.java
            solu_1.Swap();
            //gets fitness of cloned array
            fitness_1 = solu_1.fitnessFunction(N,dist);

            //calculate the probability of being accepted
            probability = 1/(1+exp((fitness_1-fitness)/Tvalue));
            //get a generated random double for UR method in Hill_Climber_java
            double rand = Hill_Climber.UR(0,1);

            //if cloned array fitness is less than original array, replace orginal array with cloned array
            if (fitness_1 < fitness)
            {
                solu = solu_1;
            }
            //if random number is less than the probability to be accepted also replace orginal array with cloned array
            else if(rand < probability)
            {
                solu = solu_1;
            }
        }
        //print path created by Stochastic Hill Climber
        System.out.println("Solution = " + solu.gSolution());
        //print solution of TSP (Fitness/Length of tour) 
        System.out.println("Fitness = " + solu.fitnessFunction(dist.length,dist));
        return solu;
    }

    //-----------Random Restart Hill Climber-----------//
    public static Hill_Climber restartHill_Climber (int N, double[][] dist,int restart, int iterations)
    //N being number of cities in array list
    //dist being the array list created in TSP.java
    //rest being the number of restarts
    //Iterations being number of iterations to be run
    {
    	//call Hill_Climber.java
        Hill_Climber best = new Hill_Climber(0);
        double bestFitness = Double.MAX_VALUE;
        Hill_Climber test;
        double testfitness;
        
        //if i is less than restart continue loop and +1 i
        for (int i=0; i <= restart; i++)
        {
        	
        	//call Hill_Climber.java and declare variable solution
            Hill_Climber solu = new Hill_Climber(N);
            Hill_Climber solu_1;
            double fitness, fitness_1;

            //if j is less than number of iteration continue for loop, else exit loop 
            for (int j=0; j < iterations; j++)
            {
            	//create cloned of array, gSolution method in Hill_Climber.java
                solu_1 = new Hill_Climber(solu.gSolution());
                //get fitness of orignal array
                fitness = solu.fitnessFunction(N,dist);
                //send clone array to Swap method in Hill_Climber.java
                solu_1.Swap();
                //gets fitness of cloned array
                fitness_1 = solu_1.fitnessFunction(N,dist);

                //if cloned array fitness is less than original array, replace orginal array with cloned array
                if (fitness_1 < fitness)
                {
                    solu = solu_1;
                }
            }

            //set test array as solution
            test = solu;
            //test the fitness of the array
            testfitness = solu.fitnessFunction(N,dist);
            //if testfitness is greater than the bestfitness change best array to = test array
            if (testfitness < bestFitness)
            {
                best = test;
            }
        }
        //print best path created by Random Restart Hill Climber
        System.out.println("Best Solution = " + best.gSolution());
        //print best solution of TSP (Fitness/Length of tour)
        System.out.println("Fitness = " + best.fitnessFunction(dist.length, dist));
        return best;
    }
}
