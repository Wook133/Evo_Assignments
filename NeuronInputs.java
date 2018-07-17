package deVilliers_214062813.Assignment1;

public class NeuronInputs
{
    double dArith;
    double dElAlg;
    double dReadComp;

    public NeuronInputs(double dArith, double dElAlg, double dReadComp) {
        this.dArith = dArith;
        this.dElAlg = dElAlg;
        this.dReadComp = dReadComp;
    }

    public NeuronInputs(dataset ds)
    {
        this.dArith = ds.getDa();
        this.dElAlg = ds.getDe();
        this.dReadComp = ds.getDrc();
    }

    public double getdArith() {
        return dArith;
    }

    public void setdArith(double dArith) {
        this.dArith = dArith;
    }

    public double getdElAlg() {
        return dElAlg;
    }

    public void setdElAlg(double dElAlg) {
        this.dElAlg = dElAlg;
    }

    public double getdReadComp() {
        return dReadComp;
    }

    public void setdReadComp(double dReadComp) {
        this.dReadComp = dReadComp;
    }
}
