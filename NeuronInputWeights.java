package deVilliers_214062813.Assignment1;

public class NeuronInputWeights {

    double dWeightArith;
    double dWeightElAlg;
    double dWeightReadComp;
    double dWeightBias;
    @Override
    public String toString() {
        return "NIW{" +
                "Arith=" + dWeightArith +
                ", ElAlg=" + dWeightElAlg +
                ", ReadComp=" + dWeightReadComp +
                ", Bias=" + dWeightBias +
                '}';
    }

    public NeuronInputWeights(double dWeightArith, double dWeightElAlg, double dWeightReadComp, double dWeightBias) {
        this.dWeightArith = dWeightArith;
        this.dWeightElAlg = dWeightElAlg;
        this.dWeightReadComp = dWeightReadComp;
        this.dWeightBias = dWeightBias;
    }

    public double getdWeightBias() {
        return dWeightBias;
    }

    public void setdWeightBias(double dWeightBias) {
        this.dWeightBias = dWeightBias;
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
