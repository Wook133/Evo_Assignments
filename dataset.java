package deVilliers_214062813.Assignment1;

import org.apache.commons.lang3.StringUtils;

public class dataset {

    double da;
    double de;
    double drc;
    double dam;

    public dataset(String dArith, String dEAlg, String dReadComp, String actualMark) {
       /* this.dArith = dArith;
        this.dEAlg = dEAlg;
        this.dReadComp = dReadComp;
        this.ActualMark = actualMark;*/
        if (StringUtils.isNumeric(dArith))
        {
            this.da = Double.valueOf(dArith);
        }
        if (StringUtils.isNumeric(dEAlg))
        {
            this.de = Double.valueOf(dEAlg);
        }
        if (StringUtils.isNumeric(dReadComp))
        {
            this.drc = Double.valueOf(dReadComp);
        }
        if (StringUtils.isNumeric(actualMark))
        {
            this.dam = Double.valueOf(actualMark);
        }
    }

    public double getDa() {
        return da;
    }

    public void setDa(double da) {
        this.da = da;
    }

    public double getDe() {
        return de;
    }

    public void setDe(double de) {
        this.de = de;
    }

    public double getDrc() {
        return drc;
    }

    public void setDrc(double drc) {
        this.drc = drc;
    }

    public double getDam() {
        return dam;
    }

    public void setDam(double dam) {
        this.dam = dam;
    }

    @Override
    public String toString()
    {
        return da + "," + de + "," + drc + "," + dam;
    }
}
