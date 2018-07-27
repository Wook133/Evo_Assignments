package deVilliers_214062813.Assignment1;

import java.util.ArrayList;

public class Neuron3
{
    //NeuronInputs Z;
    //public NeuronDesiredOutput tp;
    //NeuronInputWeights W;
    //double NeuronBias = 0.00001;//theta

    public ArrayList<Double> Z = new ArrayList<>(); // Input Patterns and last element is bias
    public ArrayList<Double> W = new ArrayList<>(); // Weights and last element is bias weight
    public Double Tp; //Actual Result

    public boolean bForPrediction;

    /**
     * Used for Prediction
     * @param z
     * @param w
     */
    public Neuron3(ArrayList<Double> z, ArrayList<Double> w) {
        if (z.size() == w.size()) {
            Z = z;
            W = w;
            bForPrediction = true;
        }
    }


    /**
     * Used for Training
     * @param dsCur
     * @param w
     */
    public Neuron3(dataset dsCur, ArrayList<Double> w)
    {
        Z.add(dsCur.getDa());
        Z.add(dsCur.getDe());
        Z.add(dsCur.getDrc());
        setTp(dsCur.dam);
        if (Z.size() == w.size()) {
            W = w;
            bForPrediction = false;
        }
    }

    public Double getTp() {
        return Tp;
    }

    public void setTp(Double tp) {
        Tp = tp;
    }

    /**
     * @return sum of Zi * Wi for i's
     */
    public Double net()
    {
        if (Z.size() == W.size())//check Z and W same size
        {
            Double dOut = 0.0;
            for (int i = 0; i <= Z.size() - 1; i++)
            {
                dOut = dOut + Z.get(i)*W.get(i);
            }
            return dOut;
        }
        else
            return 0.0;
    }

    /**
     * @return Current y estimate using the Linear Activation function
     */
    public double LinearActivationFunction(Double dLambda)
    {
        Double yp = 0.0;
        yp = dLambda * (net() - Z.get(Z.size() - 1));
        return yp;
    }

    /**
     * @return Current y estimate using the Sigmoid Activation function
     */
    public double SigmoidActivationFunction()
    {
        double dnet = net()*-1.0;
        double dDenomin = 1 + Math.exp(dnet);
        return 1/dDenomin;
    }

    public double Error(int ichoice)
    {
        Double dError = 0.0;
        if (bForPrediction == false)// only able to find the error if Tp exists
        {

        switch (ichoice) {
            case 0: {

            }
            break;
            case 1: {

            }
            break;
            default: {

            }
        }
    }


        return dError;
    }






}
