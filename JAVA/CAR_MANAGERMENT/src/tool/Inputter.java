
package tool;

import java.util.Scanner;

public class Inputter {

    public static Scanner sc=new Scanner(System.in);
    //nhap so nguyen
    public static int getAnInterger(String inpMsg,String errMsg){
        System.out.println(inpMsg);
        int number;
        while(true){
            try{
            number =Integer.parseInt(sc.nextLine());
            return number;
            }catch(Exception e){
                System.out.println(errMsg);
                }
            }
    }
    
    public static double getADouble(String inpMsg,String errMsg){
        System.out.println(inpMsg);
        double number;
        while(true){
            try{
            number =Double.parseDouble(sc.nextLine());
            return number;
            }catch(Exception e){
                System.out.println(errMsg);
                }
            }
    }
    
   
    public static int getAnInterger(String inpMsg,String errMsg,int lowerBound,int upperBound){
        if(lowerBound>upperBound){
            int tmp=lowerBound;
            lowerBound=upperBound;
            upperBound=tmp;
        }
        System.out.println(inpMsg);
        int number;
        while(true){
            try{
            number =Integer.parseInt(sc.nextLine());
            if(number>upperBound || number<lowerBound){
                
                throw new Exception();
            }
            return number;
            }catch(Exception e){
                System.out.println(errMsg);
                }
            }
    }
    
    public static double getADouble(String inpMsg,String errMsg,double lowerBound,double upperBound){
        if(lowerBound>upperBound){
            double tmp=lowerBound;
            lowerBound=upperBound;
            upperBound=tmp;
        }
        System.out.println(inpMsg);
        double number;
        while(true){
            try{
            number =Double.parseDouble(sc.nextLine());
            if(number>upperBound || number<lowerBound){
                throw new Exception();
            }
            return number;
            }catch(Exception e){
                System.out.println(errMsg);
                }
            }
    }
    
    public static String getString(String inpMSg,String errMsg){
        System.out.println(inpMSg);
        while(true){
            try{
            String str=sc.nextLine();
            if(str.isEmpty()){
                throw new Exception();
            }
            return str;
        }catch(Exception e){
                System.out.println(errMsg);
            }
        }
        
    }
    
    public static String getString(String inpMSg,String errMsg,String regex){
        System.out.println(inpMSg);
        while(true){
            try{
            String str=sc.nextLine();
            if(str.isEmpty() || !str.matches(regex)){
                throw new Exception();
            }
            return str;
        }catch(Exception e){
                System.out.println(errMsg);
            }
        }
        
    }
}
//ham nhap so thu
//so thuc trong khoioang
//chuoi cam de trong