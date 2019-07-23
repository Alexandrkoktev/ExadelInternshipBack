package com.exadelinternship.carpool.services.helpers;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

public class RouteSearchHelper {
    static double TwoDimensionalCalculation(double [] startPoint,double [] endPoint)
    {
        // instantiate the calculator
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        // select a reference elllipsoid
        Ellipsoid reference = Ellipsoid.WGS84;

        GlobalCoordinates start;
        start = new GlobalCoordinates(startPoint[0], startPoint[1]);
        GlobalCoordinates end;
        end = new GlobalCoordinates(endPoint[0],endPoint[1]);

        // calculate the geodetic curve
        GeodeticCurve geoCurve = geoCalc.calculateGeodeticCurve(reference, start, end);
        double ellipseKilometers = geoCurve.getEllipsoidalDistance() / 1000.0;
        return ellipseKilometers;
    }
    static double DistanceFromPointToSegment(double [] point,double [] segmentStart, double [] segmentEnd){
        // instantiate the calculator
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        // select a reference elllipsoid
        Ellipsoid reference = Ellipsoid.WGS84;

        double a=TwoDimensionalCalculation(segmentStart,segmentEnd);
        double b=TwoDimensionalCalculation(point,segmentStart);
        double c=TwoDimensionalCalculation(point,segmentEnd);

        if(b*b-a*a-c*c<=0||c*c-b*b-a*a<=0){
            return Math.min(b,c);
        }
        else{
            double p=(a+b+c)/2;
          return 2*Math.sqrt(p*(p-a)*(p-b)*(p-c))/a;
        }


    }
    public  static boolean isCloseEnough(double [] startPoint,double [] endPoint,double [][] wayPoints){
        boolean flag=false;
        double length=wayPoints.length;
        for(int i=0;i<length-1;i++){
            if(!flag&&DistanceFromPointToSegment(startPoint,wayPoints[i],wayPoints[i+1])<1){
                flag=true;
            }
            if(flag&&DistanceFromPointToSegment(endPoint,wayPoints[i],wayPoints[i+1])<1){
                return true;
            }
        }
        return false;
    }

    static public void main(String[] args)
    {
        System.out.println(isCloseEnough(new double [] {1,2},new double [] {2,3},new double[][]{{1,2},{2,3}}));
        //System.out.println( TwoDimensionalCalculation(new double []{2,3},new double[]{3,4}));
       // System.out.println(DistanceFromPointToSegment(new double []{10,22},new double[]{3,4},new double[]{2,3}));

    }
}
