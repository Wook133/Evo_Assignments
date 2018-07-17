package deVilliers_214062813.Assignment1;

public class NeuronDesiredOutput {
    double dActualMark;

    public NeuronDesiredOutput(double dActualMark) {
        this.dActualMark = dActualMark;
    }

    public NeuronDesiredOutput(dataset ds) {
        this.dActualMark = ds.getDam();
    }


    public double getdActualMark() {
        return dActualMark;
    }

    public void setdActualMark(double dActualMark) {
        this.dActualMark = dActualMark;
    }
}
