package deVilliers_214062813.Assignment1;

import java.util.ArrayList;

public class Neuron3
{
    //NeuronInputs Z;
    //public NeuronDesiredOutput tp;
    //NeuronInputWeights W;
    //double NeuronBias = 0.00001;//theta

    public Double[] Z = new Double[4]; // Input Patterns and last element is bias
    public Double[] W = new Double[4]; // Weights and last element is bias weight
    public Double Tp; //Actual Result

    public boolean bForPrediction;

    /**
     * Used for Prediction
     * @param z
     * @param w
     */
  /*  public Neuron3(ArrayList<Double> z, ArrayList<Double> w) {
        if (z.size() == w.size()) {
            Z = z;
            W = w;
            bForPrediction = true;
        }
    }*/

    @Override
    public String toString() {
        String sw ="";
        for (Double d : W) {
            System.out.println(d);
            sw = sw + " : " + d;
        }

            return "Neuron3{" +
                "Z=" + Z +
                ", W=" + sw +
                ", Tp=" + Tp +
                ", bForPrediction=" + bForPrediction +
                '}';
    }

    /**
     * Used for Training, first
     * @param dsCur
     * @param w
     */
    public Neuron3(dataset dsCur, ArrayList<Double> w, Double dbias)
    {
        Z[0] = dsCur.getDa();
        Z[1] = dsCur.getDe();
        Z[2] = dsCur.getDrc();
        Z[3] = dbias;

        this.Tp = dsCur.getDam();



        if (Z.length == w.size()) {
            for (int i = 0; i <= w.size() - 1; i++)
            {
                W[i] = w.get(i);
            }
            bForPrediction = false;
        }
    }
    /**
     * Used for Training, Rest, Same Neuron, updating Zs
     * @param dsCur
     */
    public Neuron3(dataset dsCur)
    {
        Z[0] = dsCur.getDa();
        Z[1] = dsCur.getDe();
        Z[2] = dsCur.getDrc();
        setTp(dsCur.getDam());
        bForPrediction = false;
    }

    public void setZ(dataset dsCur)
    {
        Z[0] = dsCur.getDa();
        Z[1] = dsCur.getDe();
        Z[2] = dsCur.getDrc();
        setTp(dsCur.getDam());
        bForPrediction = false;
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
        //System.out.println("Z length: " + Z.length + " : " + W.length);
        //String sout = "";
        if (Z.length == W.length)//check Z and W same size
        {
            Double dOut = 0.0;
            for (int i = 0; i <= Z.length - 1; i++)
            {
              //  sout = sout + Z[i] + " * " + W[i] + " + ";
                dOut = dOut + Z[i]*W[i];
            }
         //   System.out.println(sout + " = " + dOut);
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
        yp = dLambda * (net() - Z[3]);
        return yp;
    }


    /**
     * @return Current y estimate using the Sigmoid Activation function
     */
    public double SigmoidActivationFunction()
    {
        /*double dnet = net()*-1.0;
        System.out.println("dnet = "+ dnet);
        double dDenomin = 1 + Math.exp(dnet);
        System.out.println("dDenomin = "+ dDenomin);
        double dans = 1.0/dDenomin;*/
        double dnet = net()*-1.0;
        //System.out.println("dnet = "+ dnet);
        double dDenomin = 1 + Math.exp(dnet);
        //System.out.println("dDenomin = "+ dDenomin);
        double dans = 1.0/dDenomin;
        //System.out.println("dans = "+ dans);


        return dans;
    }

    public void updateWeights(int ichoice, double dEta, double dOther)
    {
       // System.out.println("WeightinStart");
        if (bForPrediction == false)
        {
            switch (ichoice) {
                case 0://widrow hoff
                {
                    for (int i = 0; i <= W.length - 1; i++)
                    {
                        double dWcur = W[i] - (dEta*(-2.0)*ErrorNotSquared(ichoice, dOther)*Z[i]);
                        W[i] = dWcur;
                    }
                }
                break;
                case 1://Generalized Delta
                {
                 //   System.out.println("```````````````````````````````````````````````````````````````````" + W.size());
                    for (int i = 0; i <= W.length - 1; i++)
                    {

                        double yp = SigmoidActivationFunction();
                       // System.out.println("YP: " + yp);
                        double dWcur = W[i] - (dEta*(-2.0)*ErrorNotSquared(ichoice, dOther)*Z[i]*(1-yp)*yp);
                       // System.out.println("Weight: " + dWcur);
                        W[i] = dWcur;
                    }
                   /* for (Double w : W)
                    {
                        System.out.print(w + " * ");
                    }
                    System.out.println();*/
                }
                break;
                default:
                {
                    for (int i = 0; i <= W.length - 1; i++)
                    {
                        double yp = SigmoidActivationFunction();
                        double dWcur = W[i] - (dEta*(-2.0)*ErrorNotSquared(ichoice, dOther)*Z[i]*(1-yp)*yp);
                        W[i] = dWcur;
                    }
                }
            }
        }

    }


    /**
     * Error Squared
     * @param ichoice choose which activation function to use
     * @param dother other variable to use if activation function requires it
     * @return Error Squared for SSE
     */
    public double ErrorNotSquared(int ichoice, double dother)
    {
        Double dError = 0.0;
        if (bForPrediction == false)// only able to find the error if Tp exists
        {
            switch (ichoice) {
                case 0: {
                    double yp = LinearActivationFunction(dother);
                    dError = Tp - yp;
                }
                break;
                case 1: {
                    double yp = SigmoidActivationFunction();
                    dError = Tp - yp;
                }
                break;
                default:
                    {
                        double yp = SigmoidActivationFunction();
                        dError = Tp - yp;
                    }
            }
        }
        return dError;
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
        if (bForPrediction == false)// only able to find the error if Tp exists
        {
            switch (ichoice) {
                case 0: {
                    double yp = LinearActivationFunction(dother);
                    Double dc = Tp - yp;
                    dError = Math.pow(dc, 2);
                }
                break;
                case 1: {
                    double yp = SigmoidActivationFunction();

                    Double dc = Tp - yp;
                    //System.out.println("Yp = " + yp + " |||||||||| " + "TP = " + Tp);
                    dError = Math.pow(dc, 2);

                }
                break;
                default:
                {
                    double yp = SigmoidActivationFunction();
                    Double dc = Tp - yp;
                    dError = Math.pow(dc, 2);
                }
            }
        }

        return dError;
    }








}
