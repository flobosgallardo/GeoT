package com.ruyo.rest.restcontroller.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoCombustible {

    protected int getCalComb(double distancia, double rendimiento, double pCombustible){
        return new BigDecimal((distancia / rendimiento) * pCombustible).
                setScale(0, RoundingMode.UP).intValue();

    }
}
