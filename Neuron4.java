package deVilliers_214062813.Assignment1;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Neuron4 {
    public BigDecimal[] Z = new BigDecimal[4]; // Input Patterns and last element is bias
    public BigDecimal[] W = new BigDecimal[4]; // Weights and last element is bias weight
    public BigDecimal Tp; //Actual Result

    public boolean bForPrediction;




    @Override
    public String toString() {
        String sw ="";
        for (BigDecimal d : W) {
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
    public Neuron4(datasetBD dsCur, ArrayList<BigDecimal> w, BigDecimal dbias)
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
    public Neuron4(datasetBD dsCur)
    {
        Z[0] = dsCur.getDa();
        Z[1] = dsCur.getDe();
        Z[2] = dsCur.getDrc();
        setTp(dsCur.getDam());
        bForPrediction = false;
    }

    public void setZ(datasetBD dsCur)
    {
        Z[0] = dsCur.getDa();
        Z[1] = dsCur.getDe();
        Z[2] = dsCur.getDrc();
        setTp(dsCur.getDam());
        bForPrediction = false;
    }

    public BigDecimal getTp() {
        return Tp;
    }

    public void setTp(BigDecimal tp) {
        Tp = tp;
    }

    /**
     * @return sum of Zi * Wi for i's
     */
    public BigDecimal net()
    {
        if (Z.length == W.length)//check Z and W same size
        {
            BigDecimal dOut = BigDecimal.ZERO;
            for (int i = 0; i <= Z.length - 1; i++)
            {
                dOut = dOut.add(Z[i].multiply(W[i]));
            }
            dOut = dOut.setScale(64, BigDecimal.ROUND_HALF_UP);
            return dOut;
        }
        else
            return BigDecimal.ZERO;
    }

    /**
     * @return Current y estimate using the Linear Activation function
     */
    public BigDecimal LinearActivationFunction(BigDecimal dLambda)
    {
        BigDecimal yp = BigDecimal.ZERO;
        yp = dLambda.multiply(net().subtract(Z[3]));
        yp= yp.setScale(64, BigDecimal.ROUND_HALF_UP);
        return yp;
    }


    /**
     * @return Current y estimate using the Sigmoid Activation function
     */
    public BigDecimal SigmoidActivationFunction()
    {
       /* BigDecimal dnet = net().multiply(BigDecimal.ONE.negate()) ;
        BigDecimal dDenomin = 1 + Math.exp(dnet);
        BigDecimal dans = 1.0/dDenomin;*/
        return BigDecimal.ZERO;
    }

    public void updateWeights(int ichoice, BigDecimal dEta, BigDecimal dOther)
    {
        // System.out.println("WeightinStart");
        if (bForPrediction == false)
        {
            switch (ichoice) {
                case 0://widrow hoff
                {
                    for (int i = 0; i <= W.length - 1; i++)
                    {
                        BigDecimal negative2 = BigDecimal.ONE.add(BigDecimal.ONE).negate();
                        BigDecimal dWcur = W[i].subtract (dEta.multiply(negative2).multiply(ErrorNotSquared(ichoice, dOther)).multiply(Z[i]));
                        dWcur = dWcur.setScale(64, BigDecimal.ROUND_HALF_UP);
                        W[i] = dWcur;
                    }
                }
                break;
                default:
                {
                    for (int i = 0; i <= W.length - 1; i++)
                    {
                        BigDecimal negative2 = BigDecimal.ONE.add(BigDecimal.ONE).negate();
                        BigDecimal dWcur = W[i].subtract (dEta.multiply(negative2).multiply(ErrorNotSquared(ichoice, dOther)).multiply(Z[i]));
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
    public BigDecimal ErrorNotSquared(int ichoice, BigDecimal dother)
    {
        BigDecimal dError = BigDecimal.ZERO;
        if (bForPrediction == false)// only able to find the error if Tp exists
        {
            switch (ichoice) {
                case 0: {
                    BigDecimal yp = LinearActivationFunction(dother);
                    dError = Tp.subtract(yp);
                }
                 break;
                default:
                {
                    BigDecimal yp = LinearActivationFunction(dother);
                    dError = Tp.subtract(yp);
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
    public BigDecimal Error(int ichoice, BigDecimal dother)
    {
        BigDecimal dError = BigDecimal.ZERO;
        if (bForPrediction == false)// only able to find the error if Tp exists
        {
            switch (ichoice) {
                case 0: {
                    BigDecimal yp = LinearActivationFunction(dother);
                    BigDecimal dc = Tp.subtract(yp);
                    dError = dc.pow(2);
                }
                break;
                default:
                {
                    BigDecimal yp = LinearActivationFunction(dother);
                    BigDecimal dc = Tp.subtract(yp);
                    dError = dc.pow(2);
                }
            }
        }
        dError = dError.setScale(64, BigDecimal.ROUND_HALF_UP);

        return dError;
    }


}
