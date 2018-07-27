package deVilliers_214062813.Assignment1;

public class Neuron2
{
    NeuronInputs Z;
    public NeuronDesiredOutput tp;
    NeuronInputWeights W;
    double NeuronBias = 0.00001;//theta


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
        this.W = new NeuronInputWeights(niv.getdWeightArith(), niv.getdWeightElAlg(), niv.getdWeightReadComp(), niv.getdWeightBias());
    }

    public double net()
    {
        double dAns = (Z.getdArith()*W.getdWeightArith()) + (Z.getdElAlg()*W.getdWeightElAlg()) + (Z.getdReadComp()*W.getdWeightReadComp()) + (NeuronBias*W.getdWeightBias());
        return dAns;
    }

    public double sigmoidYP()
    {
        double dnet = net()*-1.0;
        double dDenomin = 1 + Math.exp(dnet);
        return 1/dDenomin;
    }

    public void updateWeights(double deta)
    {

        double yp = sigmoidYP();
        //System.out.println(tp.getdActualMark() + " : " + net());
        double w1 = W.getdWeightArith();
//0.000015
        //0.00003
        //0.000075
        //0.00014
        double dw1 = w1 - ((deta)*(-2.0*(tp.getdActualMark()-yp)*yp*(1-yp)*Z.getdArith()));
        W.setdWeightArith(dw1);
        double w2 = W.getdWeightElAlg();
        dw1 = w2 - ((deta)*(-2.0*(tp.getdActualMark()-yp)*yp*(1-yp)*Z.getdElAlg()));
        W.setdWeightElAlg(dw1);
        double w3 = W.getdWeightReadComp();
        dw1 = w3 - ((deta)*(-2.0*(tp.getdActualMark()-yp)*yp*(1-yp)*Z.getdReadComp()));
        W.setdWeightReadComp(dw1);
        double w4 = W.getdWeightBias();
        dw1 = w4 - ((deta)*(-2.0*(tp.getdActualMark()-yp)*yp*(1-yp)*this.NeuronBias));
        W.setdWeightBias(dw1);
//13189.120873418888
    }

    /**
     * Error Squared
     * @param ichoice choose which activation function to use
     * @param dother other variable to use if activation function requires it
     * @return Error Squared for SSE
     */
    public double Error(int ichoice, double dother)
    {
        Double dError = 0.0;
        double yp = sigmoidYP();
        dError = Math.pow((tp.getdActualMark() - yp), 2);
        return dError;
    }


    /*
    public static void main(String[] args) throws Exception {
        ArrayList<dataset> listDS = readCSV.readfile("Streamdata.csv");
        Random rnd = new Random();
        ArrayList<dataset> listIn = new ArrayList<>();
        for (int i = 0; i <= 60; i++)
        {

            listIn.add(listDS.get(i));
        }

        double dw1 = rnd.nextDouble()/100000.0;
        double dw2 = rnd.nextDouble()/100000.0;
        double dw3 = rnd.nextDouble()/100000.0;
        NeuronInputWeights niw = new NeuronInputWeights(dw1,dw2,dw3);
        System.out.println(niw.toString());
        Neuron2 singleNeuron = new Neuron2();
        singleNeuron.setWeights(niw);
        ArrayList<Double> derror = new ArrayList<>();
        double dSumError = Double.MAX_VALUE;
        while (dSumError > 10000)
        {
            dSumError = 0.0;
            for (dataset ds : listIn)
            {
                singleNeuron.setNeuron(ds);
                singleNeuron.updateWeights();

                double de = singleNeuron.tp.getdActualMark()  - singleNeuron.net();
                derror.add(Math.pow(de, 2));
            }
            for (Double dcur : derror)
            {
                dSumError = dSumError + dcur;
            }
            System.out.println(singleNeuron.getW().toString());
            System.out.println("SSE: " + dSumError);
            derror = new ArrayList<>();
        }
     */



}
