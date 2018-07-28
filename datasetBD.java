package deVilliers_214062813.Assignment1;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class datasetBD {
    BigDecimal da;
    BigDecimal de;
    BigDecimal drc;
    BigDecimal dam;

    public datasetBD(String dArith, String dEAlg, String dReadComp, String actualMark) {
       /* this.dArith = dArith;
        this.dEAlg = dEAlg;
        this.dReadComp = dReadComp;
        this.ActualMark = actualMark;*/
        if (StringUtils.isNumeric(dArith))
        {
            this.da = new BigDecimal(dArith);
        }
        if (StringUtils.isNumeric(dEAlg))
        {
            this.de = new BigDecimal(dEAlg);
        }
        if (StringUtils.isNumeric(dReadComp))
        {
            this.drc = new BigDecimal(dReadComp);
        }
        if (StringUtils.isNumeric(actualMark))
        {
            this.dam = new BigDecimal(actualMark);
        }
    }

    public BigDecimal getDa() {
        return da;
    }

    public void setDa(BigDecimal da) {
        this.da = da;
    }

    public BigDecimal getDe() {
        return de;
    }

    public void setDe(BigDecimal de) {
        this.de = de;
    }

    public BigDecimal getDrc() {
        return drc;
    }

    public void setDrc(BigDecimal drc) {
        this.drc = drc;
    }

    public BigDecimal getDam() {
        return dam;
    }

    public void setDam(BigDecimal dam) {
        this.dam = dam;
    }

    @Override
    public String toString()
    {
        return da + "," + de + "," + drc + "," + dam;
    }
}
