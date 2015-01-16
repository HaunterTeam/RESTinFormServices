/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author luca
 */
public class BMIUtils {
    
    public static final int ERROR = 0;
    
    public static final int UNDER_WEIGHT = 1;
    public static final int NORMAL_WEIGHT = 2;
    public static final int OVER_WEIGHT = 3;
    public static final int OBESE = 4;
    
    public static final double LVL_1_HIGH_TH = 18.5;
    public static final double LVL_2_HIGH_TH = 24.9;
    public static final double LVL_3_HIGH_TH = 29.9;
    
    public static int getLevel(double weight,double height){
        
        double BMI;
                
        try{
            BMI = weight / (height*height);
        }catch(ArithmeticException e){
            return ERROR;
        }
        
        return getLevelByBmi(BMI);
        
    }
    
    public static int getLevelByBmi(double BMI){
        
        if(BMI < LVL_1_HIGH_TH){
            return UNDER_WEIGHT;
        }else if(BMI < LVL_2_HIGH_TH){
            return NORMAL_WEIGHT;
        }else if(BMI < LVL_3_HIGH_TH){
            return OVER_WEIGHT;
        }else if(BMI > LVL_3_HIGH_TH){
            return OBESE;
        }
        return ERROR;
    }
}
