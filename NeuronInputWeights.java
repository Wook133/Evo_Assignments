package deVilliers_214062813.Assignment1;

public class NeuronInputWeights {

    double dWeightArith;
    double dWeightElAlg;
    double dWeightReadComp;

    public NeuronInputWeights(double dWeightArith, double dWeightElAlg, double dWeightReadComp) {
        this.dWeightArith = dWeightArith;
        this.dWeightElAlg = dWeightElAlg;
        this.dWeightReadComp = dWeightReadComp;
    }

    public double getdWeightArith() {
        return dWeightArith;
    }

    public void setdWeightArith(double dWeightArith) {
        this.dWeightArith = dWeightArith;
    }

    public double getdWeightElAlg() {
        return dWeightElAlg;
    }

    public void setdWeightElAlg(double dWeightElAlg) {
        this.dWeightElAlg = dWeightElAlg;
    }

    public double getdWeightReadComp() {
        return dWeightReadComp;
    }

    public void setdWeightReadComp(double dWeightReadComp) {
        this.dWeightReadComp = dWeightReadComp;
    }
}
