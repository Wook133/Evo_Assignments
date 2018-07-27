package deVilliers_214062813.Assignment1;

import java.util.ArrayList;
import java.util.Random;

public class runnerAssignment1
{
    public static void main(String[] args) throws Exception {

        Random rnd = new Random();
        ArrayList<dataset> listDS = readCSV.readfile("Streamdata.csv");
        ArrayList<dataset> listIn = new ArrayList<>();
        for (int i = 0; i <= 60; i++)
        {
            listIn.add(listDS.get(i));
        }
        ArrayList<dataset> listLast = new ArrayList<>();
        for (int i = 61; i <= listDS.size() - 1; i++)
        {
            listLast.add(listDS.get(i));
        }

        Double SSE = 0.0;
        Double SSyy = 0.0;
        Double yTotal = 0.0;

        for (dataset dsc : listIn)
        {
            yTotal = yTotal + dsc.getDam();
        }
        System.out.println("Total Y: " + yTotal);
        Double yMean = yTotal/(listIn.size());
        System.out.println("Mean Y: " + yMean);
        Double bestRsquared = 0.627340445703675;
        double rAccept = bestRsquared * 0.95;
        System.out.println(rAccept);
        for (dataset dsc : listIn)
        {
            SSyy = Math.pow((dsc.getDam()-yMean), 2) + SSyy;
        }




        int inum = 0;
        ArrayList<Double> weights = new ArrayList<>();
        weights.add(rnd.nextDouble());
        weights.add(rnd.nextDouble());
        weights.add(rnd.nextDouble());
        weights.add(rnd.nextDouble());
        Neuron3 braincell = new Neuron3(listIn.get(0), weights, 5.0);

        Double dcurR = 0.0;

        //System.out.println(braincell.toString());

        while((dcurR < rAccept) && (inum < 1000000))
        {
            SSE = 0.0;
            for (int i = 0; i <= listIn.size() - 1; i++)
            {
                braincell.setZ(listIn.get(i));
                braincell.updateWeights(0, 0.0000001, 1.0);
                SSE = braincell.Error(0, 1.0) + SSE;
            }
            System.out.println("SSE: " + SSE);
            System.out.println("Weights: " + braincell.W[0] + " : "+ braincell.W[1] + " : " + braincell.W[2] + " : " + braincell.W[3] + " : ");
            dcurR = calculateR(SSE,SSyy);
            System.out.println("R squared : " + dcurR);
            inum = inum + 1;
        }
        System.out.println("Model R squared = " + calculateR(SSE,SSyy) + " Iteration: " + inum);





    }

    public static double calculateR(Double SSE, Double SSyy)
    {
        double r = 0.0;
        r = 1.0 - (SSE/SSyy);
        return r;
    }




}
