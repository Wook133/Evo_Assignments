package deVilliers_214062813.Assignment1;

public class Neuron2
{
    NeuronInputs Z;
    public NeuronDesiredOutput tp;
    NeuronInputWeights W;
    double NeuronBias = 0.0001;//theta

    public NeuronInputWeights getW() {
        return W;
    }

    public void setW(NeuronInputWeights w) {
        W = w;
    }

    public void setNeuron(dataset ds)
    {
        this.Z = new NeuronInputs(ds);
        this.tp = new NeuronDesiredOutput(ds);
    }

    public void setWeights(NeuronInputWeights niv)
    {
        this.W = new NeuronInputWeights(niv.getdWeightArith(), niv.getdWeightElAlg(), niv.getdWeightReadComp());
    }

    public double net()
    {
        double dAns = (Z.getdArith()*W.getdWeightArith()) + (Z.getdElAlg()*W.getdWeightElAlg()) + (Z.getdReadComp()*W.getdWeightReadComp()) - NeuronBias;
        return dAns;
    }

    public double sigmoidYP()
    {
        double dnet = net()*-1.0;
        double dDenomin = 1 + Math.exp(dnet);
        return 1/dDenomin;
    }

    public void updateWeights()
    {

        double yp = sigmoidYP();
        //System.out.println(tp.getdActualMark() + " : " + net());
        double w1 = W.getdWeightArith();

        double dw1 = w1 - ((0.00015)*(-2.0*(tp.getdActualMark()-yp)*yp*(1-yp)*Z.getdArith()));
        W.setdWeightArith(dw1);
        double w2 = W.getdWeightElAlg();
        dw1 = w2 - ((0.00015)*(-2.0*(tp.getdActualMark()-yp)*yp*(1-yp)*Z.getdElAlg()));
        W.setdWeightElAlg(dw1);
        double w3 = W.getdWeightReadComp();
        dw1 = w3 - ((0.00015)*(-2.0*(tp.getdActualMark()-yp)*yp*(1-yp)*Z.getdReadComp()));
        W.setdWeightReadComp(dw1);




    }



}
