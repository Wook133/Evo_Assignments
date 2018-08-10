package deVilliers_214062813.Assignment1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class runnerAssignment1
{
    public static void main(String[] args) throws Exception {

        Random rnd = new Random();
        ArrayList<datasetBD> listDS = readCSV.readfile2("Streamdata.csv");
        ArrayList<datasetBD> listIn = derive(listDS,60);
        ArrayList<datasetBD> listLast = evaluation(listDS, 61);

        BigDecimal SSE = BigDecimal.ZERO;
        BigDecimal SSyy = calculateSSyy(listIn);
        BigDecimal dcurR = BigDecimal.ZERO;

        BigDecimal bestRsquared = new BigDecimal(0.627340445703675);
        BigDecimal rAccept = bestRsquared.multiply(new BigDecimal(0.98));
        System.out.println(rAccept);

        BigDecimal bd5 = new BigDecimal(5.0);
        ArrayList<BigDecimal> listETA = populateEta();


        for (int i = 0; i <= 1; i++)
        {
            System.out.println("Calculating: " + i);
            for (int k = 0; k <= listETA.size()-1; k++)
            {
                System.out.println("ETA = " + listETA.get(k));
                int icounter = 0;
                ArrayList<BigDecimal> weights = new ArrayList<>();
                weights.add(new BigDecimal(0.379462372));
                weights.add(new BigDecimal(0.120427944));
                weights.add(new BigDecimal(0.127188459));
                weights.add(new BigDecimal(0.621912291));

                Long dstart = System.currentTimeMillis();
                Neuron4 braincell = new Neuron4(listIn.get(0), weights, bd5);
                while((dcurR.compareTo(rAccept) < 0 ) && (icounter < 100000))
                {
                    SSE = BigDecimal.ZERO;
                    for (int b = 0; b <= listIn.size() - 1; b++)
                    {
                        braincell.setZ(listIn.get(b));
                        braincell.updateWeights(0, listETA.get(k), BigDecimal.ONE);
                        SSE = braincell.Error(0, BigDecimal.ONE).add(SSE);
                    }
                    System.out.println("Iteration: " + icounter);
                    System.out.println("SSE: " + SSE);
                    dcurR = calculateR(SSE,SSyy);
                    icounter = icounter + 1;

                }
                System.out.println("Model R squared = " + calculateR(SSE,SSyy) + " Iteration: " + icounter);
                System.out.println(listIn.get(listIn.size()-1).toString());
                System.out.println("SSE = " + SSE + " SSyy = " + SSyy);
                Long dend = System.currentTimeMillis();
                String[] initialweights = new String[4];
                initialweights[0] = weights.get(0).toString();
                initialweights[1] = weights.get(1).toString();
                initialweights[2] = weights.get(2).toString();
                initialweights[3] = weights.get(3).toString();
                String[] finalweights = new String[4];

                finalweights[0] = braincell.W[0].toString();
                finalweights[1] = braincell.W[1].toString();
                finalweights[2] = braincell.W[2].toString();
                finalweights[3] = braincell.W[3].toString();

                Integer itestsize = listIn.size();
                BigDecimal seta = listETA.get(k);
                String sActivationFunction = "Linear";
                String sLearningRule = "Widrow-Hoff";
                BigDecimal Rsquared = dcurR;
                BigDecimal SSETrainingSet = SSE;
                BigDecimal SSETestSet = BigDecimal.ZERO;
                Integer Iterations = icounter;
                Long TimeTaken = dend-dstart;
                Experiment e1 = new Experiment(initialweights, finalweights, itestsize, seta, sActivationFunction, sLearningRule, dcurR, SSETrainingSet, SSETestSet, icounter, TimeTaken);
                System.out.println(e1.toString());
                System.out.println(e1.print());
                readCSV.writeCsvFile("ExperimentA1BigDecimalSetInitialWeights4.csv", e1);
                dcurR = BigDecimal.ZERO;
                icounter = 0;
                dend = 0L;
                dstart = 0L;
                icounter = 0;
                System.out.println("_________________________________________________________________________________");
            }
        }
    }

    public static BigDecimal calculateR(BigDecimal SSE, BigDecimal SSyy)
    {
        BigDecimal r = BigDecimal.ZERO;
        r = BigDecimal.ONE.subtract((SSE.divide(SSyy,64, RoundingMode.HALF_UP)));
        return r;
    }

    public static ArrayList<datasetBD> evaluation(ArrayList<datasetBD> listDS, int j) {
        ArrayList<datasetBD> listLast = new ArrayList<>();
        for (int i = j; i <= listDS.size() - 1; i++) {
            listLast.add(listDS.get(i));
        }
        return  listLast;
    }
    public static ArrayList<datasetBD> derive(ArrayList<datasetBD> listDS, int j) {
        ArrayList<datasetBD> listLast = new ArrayList<>();
        for (int i = 0; i <= j; i++) {
            listLast.add(listDS.get(i));
        }
        return  listLast;
    }
    public static BigDecimal calculateMeanY(ArrayList<datasetBD> listIn)
    {
        BigDecimal yTotal = BigDecimal.ZERO;
        for (datasetBD dsc : listIn)
        {
            yTotal = yTotal.add(dsc.getDam());
        }
        BigDecimal dbDivisor = new BigDecimal(listIn.size());
        System.out.println("DBDivision : " + dbDivisor);
        System.out.println("yTotal : " + yTotal);
        BigDecimal bdAns = yTotal.divide(dbDivisor,64, RoundingMode.HALF_UP);
        System.out.println("bdAns : " + bdAns);
        return bdAns;
    }
    public static BigDecimal calculateSSyy(ArrayList<datasetBD> listIn)
    {
        BigDecimal yMean = calculateMeanY(listIn);
        BigDecimal SSyy = BigDecimal.ZERO;

        for (datasetBD dsc : listIn)
        {
            BigDecimal bdInside = dsc.getDam().subtract(yMean);
            BigDecimal bdPow = bdInside.pow(2);

            SSyy = bdPow.add(SSyy);
        }
      //  SSyy = SSyy.setScale(5, BigDecimal.ROUND_HALF_UP);
        return SSyy;
    }
    public static ArrayList<BigDecimal> populateEta() {
        ArrayList<BigDecimal> lout = new ArrayList<>();

        BigDecimal bd = new BigDecimal("0.000005");
        lout.add(bd);
        bd = new BigDecimal("0.00000");
        lout.add(bd);
        /*BigDecimal bd = new BigDecimal("0.0000001");
        lout.add(bd);
        bd = new BigDecimal("0.000005");
        lout.add(bd);
        bd = new BigDecimal("0.0000001");
        lout.add(bd);
        bd = new BigDecimal("0.0000005");
        lout.add(bd);
        bd = new BigDecimal("0.00000001");
        lout.add(bd);
        bd = new BigDecimal("0.00000005");
        lout.add(bd);
        bd = new BigDecimal("0.000000001");
        lout.add(bd);
        bd = new BigDecimal("0.000000005");
        lout.add(bd);
        bd = new BigDecimal("0.0000000001");
        lout.add(bd);
        bd = new BigDecimal("0.0000000005");
        lout.add(bd);
        bd = new BigDecimal("0.00000000001");
        lout.add(bd);
        bd = new BigDecimal("0.00000000005");
        lout.add(bd);
        bd = new BigDecimal("0.000000000001");
        lout.add(bd);
        bd = new BigDecimal("0.000000000005");
        lout.add(bd);*/
        return lout;
    }

}
