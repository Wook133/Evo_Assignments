package deVilliers_214062813.Assignment1;

import java.math.BigDecimal;
import java.util.Arrays;

public class Experiment {
    String[] InitialWeights = new String[4];
    String[] FinalWeights= new String[4];
    Integer TestSetSize;
    BigDecimal eta;
    String sActivationFunction;
    String sLearningRule;
    BigDecimal Rsquared;
    BigDecimal SSETrainingSet;
    BigDecimal SSETestSet;
    Integer Iterations;
    Long TimeTaken;

    public Experiment(String[] initialWeights, String[] finalWeights, Integer testSetSize, BigDecimal eta, String sActivationFunction, String sLearningRule, BigDecimal rsquared, BigDecimal SSETrainingSet, BigDecimal SSETestSet, Integer iterations, Long timeTaken) {
        InitialWeights = initialWeights;
        FinalWeights = finalWeights;
        TestSetSize = testSetSize;
        this.eta = eta;
        this.sActivationFunction = sActivationFunction;
        this.sLearningRule = sLearningRule;
        Rsquared = rsquared;
        this.SSETrainingSet = SSETrainingSet;
        this.SSETestSet = SSETestSet;
        Iterations = iterations;
        TimeTaken = timeTaken;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "InitialWeights=" + (InitialWeights == null ? null : Arrays.asList(InitialWeights)) +
                ", FinalWeights=" + (FinalWeights == null ? null : Arrays.asList(FinalWeights)) +
                ", TestSetSize=" + TestSetSize +
                ", eta=" + eta +
                ", sActivationFunction='" + sActivationFunction + '\'' +
                ", sLearningRule='" + sLearningRule + '\'' +
                ", Rsquared=" + Rsquared +
                ", SSETrainingSet=" + SSETrainingSet +
                ", SSETestSet=" + SSETestSet +
                ", Iterations=" + Iterations +
                ", TimeTaken=" + TimeTaken +
                '}';
    }

    public String print() {
        return W() + TestSetSize +
                "," + eta +
                "," + sActivationFunction  +
                "," + sLearningRule +
                "," + Rsquared +
                "," + SSETrainingSet +
                "," + SSETestSet +
                "," + Iterations +
                "," + TimeTaken;
    }

    public String W()
    {
        String sout = "";
        sout = InitialWeights[0] + "," + InitialWeights[1] + "," + InitialWeights[2] + "," + InitialWeights[3] + "," +FinalWeights[0] + "," + FinalWeights[1] + "," + FinalWeights[2] + "," + FinalWeights[3] + ",";
        return sout;

    }
}
