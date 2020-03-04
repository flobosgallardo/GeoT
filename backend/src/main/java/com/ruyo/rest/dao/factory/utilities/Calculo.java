package com.ruyo.rest.dao.factory.utilities;

public class Calculo {

    protected double getDistance(double lat1, double long1, double lat2, double long2){
        final double R = 6378137; // en metros
        double dLat = rad(lat2 - lat1);
        double dLong = rad(long2 - long1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(rad(lat1)) * Math.cos(rad(lat2)) *
                        Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


    private double rad(double x){
        return x * Math.PI / 180;
    }

}
