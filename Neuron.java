package deVilliers_214062813.Assignment1;

import javafx.util.Pair;

import java.util.ArrayList;

public class Neuron {
    //ArrayList<Pair<Double, Double>> listInputWeightPairs = new ArrayList<>(); //input

    NeuronInputs Z;
    NeuronInputWeights V;
    NeuronDesiredOutput t;
    Double dtheta;
    double dbias;//bias or threshold input
    Double dOut;

    public Neuron(dataset ds)
    {
        this.Z = new NeuronInputs(ds);
        this.t = new NeuronDesiredOutput(ds);
        this.dtheta     = 0.0;
        this.dbias      = 0.0;
        this.dOut       = 0.0;
    }

    public Neuron(dataset ds, Double dtheta, double dbias, Double dOut) {
        this.Z = new NeuronInputs(ds);
        this.t = new NeuronDesiredOutput(ds);
        this.dtheta = dtheta;
        this.dbias = dbias;
        this.dOut = dOut;
    }

    public Neuron(dataset ds, Double dtheta, double dbias, Double dOut, NeuronInputWeights niv) {
        this.Z = new NeuronInputs(ds);
        this.t = new NeuronDesiredOutput(ds);
        this.V = new NeuronInputWeights(niv.getdWeightArith(), niv.getdWeightElAlg(), niv.getdWeightReadComp());
        this.dtheta = dtheta;
        this.dbias = dbias;
        this.dOut = dOut;
    }

    /* public Neuron(ArrayList<Pair<Double, Double>> listInputWeightPairs) {
        this.listInputWeightPairs = listInputWeightPairs;
        dtheta = 0.0;
        dOut = 0.0;
    }*/

    public double NetSumWeightiXInputi()
    {
      /*  double dAns = 0.0;
        for (int i = 0; i <= listInputWeightPairs.size() - 1; i++)
        {
            double dIni = listInputWeightPairs.get(i).getKey();
            double dWei = listInputWeightPairs.get(i).getValue();
            double dTimes = dIni * dWei;
            dAns = dAns + dTimes;
        }
        return dAns;*/

      double dAns = (Z.getdArith()*V.getdWeightArith()) + (Z.getdElAlg()*V.getdWeightElAlg()) + (Z.getdReadComp()*V.getdWeightReadComp());
      return dAns;
    }

    public double NetMultiplicativeWeightiXInputi()
    {

        double dAns = 0.0;

        dAns = Math.pow(Z.getdArith(),V.getdWeightArith()) * Math.pow(Z.getdElAlg(),V.getdWeightElAlg()) + Math.pow(Z.getdReadComp(),V.getdWeightReadComp());
       /* for (int i = 0; i <= listInputWeightPairs.size() - 1; i++)
        {
            double dIni = listInputWeightPairs.get(i).getKey();
            double dWei = listInputWeightPairs.get(i).getValue();
            double dTimes = Math.pow(dIni, dWei);
            dAns = dAns * dTimes;
        }*/
        return dAns;
    }

    public Double getdThreshold() {
        return dtheta;
    }

    public void setdThreshold(Double dThreshold) {
        this.dtheta = dThreshold;
    }

    public Double getdOut() {
        return dOut;
    }

    public void setdOut(Double dOut) {
        this.dOut = dOut;
    }

    public double LinearActivationFunction(double dlambda)
    {
        double dans = 0.0;
        dans = dlambda * (NetSumWeightiXInputi() - dtheta);
        return dans;
    }

    public double StepActivationFunction()
    {
        double d = NetSumWeightiXInputi();
        if (d >= dtheta)
        {
            return 1.0;
        }
        else
            return 0.0;
    }

    public double BiPolarStepActivationFunction()
    {
        double d = NetSumWeightiXInputi();
        if (d >= dtheta)
        {
            return 1.0;
        }
        else
            return -1.0;
    }

    public double RampActivationFunction(double derror)
    {
        double dnet = NetSumWeightiXInputi();
        double dnetMinusTheta = dnet - dtheta;
        double dNegativeError = derror * -1.0;
        if (dnetMinusTheta >= derror)
        {
            return 1.0;
        }

        else if ((dNegativeError < dnetMinusTheta) && (dnetMinusTheta < derror))
        {
            return dnetMinusTheta;
        }
        else
            return -1.0;
    }

    public double SigmoidActivationFunction(double dlambda)
    {
        double dnet = NetSumWeightiXInputi();
        double dnetMinusTheta = dnet - dtheta;
        double dlambdaNET = -1.0*dlambda*dnet;
        double dDenominator = 1 + (Math.exp(dlambdaNET));
        return (1.0/dDenominator);
    }

    public double HyperbolicTangentActivationFunction(double dlambda)
    {
        double dnet = NetSumWeightiXInputi();
        double lambdadnet = dnet - dlambda;
        double negativelambdadnet = lambdadnet *(-1.0);
        double dNum = Math.exp(lambdadnet) - Math.exp(negativelambdadnet);
        double dDenom = Math.exp(lambdadnet) + Math.exp(negativelambdadnet);
        return dNum/dDenom;
    }

    public double GaussianActivationFunction(double std)
    {
        double dnet = NetSumWeightiXInputi();
        double dnetSquared = dnet*dnet;
        double dNegNetS = dnetSquared * -1.0;
        double dvar = std * std;
        double dExp = dNegNetS/dvar;
        double dans = Math.exp(dExp);
        return dans;
    }



}