package deVilliers_214062813.Assignment1;

import javafx.util.Pair;

import java.util.ArrayList;

public class Neuron {
    //ArrayList<Pair<Double, Double>> listInputWeightPairs = new ArrayList<>(); //input

    NeuronInputs Z;
    NeuronInputWeights V;
    NeuronDesiredOutput t;
    NeuronDesiredOutput tprev = new NeuronDesiredOutput(0);
    Double dtheta = 0.00000000000001;
    double dbias;//bias or threshold input
    Double dOut;
    double dsigma = 0.000000000000005;

    public NeuronInputWeights getV() {
        return V;
    }

    public Neuron(dataset ds)
    {
        this.Z = new NeuronInputs(ds);
        this.t = new NeuronDesiredOutput(ds);
        this.dtheta     = 0.0;
        this.dbias      = 0.0;
        this.dOut       = 0.0;
    }

    public Neuron()
    {

    }
    public void setNeuron(dataset ds)
    {
        this.Z = new NeuronInputs(ds);
//        tprev.setdActualMark(this.t.getdActualMark());
        this.t = new NeuronDesiredOutput(ds);
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

    public void setWeights(NeuronInputWeights niv)
    {
        this.V = new NeuronInputWeights(niv.getdWeightArith(), niv.getdWeightElAlg(), niv.getdWeightReadComp());
    }

    /* public Neuron(ArrayList<Pair<Double, Double>> listInputWeightPairs) {
        this.listInputWeightPairs = listInputWeightPairs;
        dtheta = 0.0;
        dOut = 0.0;
    }*/

    public double NetSumWeightiXInputi()
    {
      /**  double dAns = 0.0;
        for (int i = 0; i <= listInputWeightPairs.size() - 1; i++)
        {
            double dIni = listInputWeightPairs.get(i).getKey();
            double dWei = listInputWeightPairs.get(i).getValue();
            double dTimes = dIni * dWei;
            dAns = dAns + dTimes;
        }
        return dAns;*/

      double dAns = (Z.getdArith()*V.getdWeightArith()) + (Z.getdElAlg()*V.getdWeightElAlg()) + (Z.getdReadComp()*V.getdWeightReadComp()) + (-1.0 * dtheta);
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

    public double EpStep()
    {
        double dans = this.t.getdActualMark() - StepActivationFunction();
        return Math.pow(dans, 2);
    }

    public void updateWeights(/*double t*/)
    {
        System.out.println("Objective Mark"+this.t.getdActualMark());
        //double tp = BiPolarStepActivationFunction();
        //double tp = NetSumWeightiXInputi();
        //tp = NetMultiplicativeWeightiXInputi();
       /* double dDeriv = (-2.0)*(this.t.getdActualMark() - tp);
        double dNewWRC = V.getdWeightReadComp() * (tp - 1) + (dsigma*dDeriv);
        double dNewWA = V.getdWeightArith() * (tp - 1) + (dsigma*dDeriv);
        double dNewWEA = V.getdWeightElAlg() * (tp - 1) + (dsigma*dDeriv);
        V.setdWeightArith((V.getdWeightArith()*this.t.getdActualMark()) - dNewWA);
        V.setdWeightElAlg((V.getdWeightElAlg()*this.t.getdActualMark()) - dNewWEA);
        V.setdWeightReadComp((V.getdWeightReadComp()*this.t.getdActualMark()) - dNewWRC);*/

        double yp = NetSumWeightiXInputi() ;//*BiPolarStepActivationFunction();
        double tp = this.t.getdActualMark();
        double dfirst = (-2.0)*(tp-yp);
        double derivateWRC = dsigma*Z.getdReadComp();// V.getdWeightReadComp();
        double dwrc = (V.getdWeightReadComp()*tp) - (dfirst * derivateWRC);
        V.setdWeightReadComp(dwrc);

        double derivateWEA = Z.getdElAlg();// V.getdWeightElAlg();
        double dwea = (V.getdWeightElAlg()*tp) - (dfirst * derivateWEA);
        V.setdWeightElAlg(dwea);

        double derivateWA = Z.getdArith();// V.getdWeightArith();
        double dwa = (V.getdWeightArith()*tp) - (dfirst * derivateWA);
        V.setdWeightArith(dwa);

    }

    public void updateWeightsWidrowHoff()
    {
        double yp = NetSumWeightiXInputi() ;
        double first =V.getdWeightReadComp() * (this.tprev.getdActualMark());
        //double second = 2.0* dsigma*(this.t.getdActualMark()-)
        //V.setdWeightReadComp();
    }




}
