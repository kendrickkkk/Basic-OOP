package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import javax.swing.event.CaretListener;
import tool.Inputter;


public class CarsManage {
    ArrayList<Cars> carlist=new ArrayList<>();
    BrandsManage brandList;

    public CarsManage(BrandsManage brandList) {
        this.brandList = brandList;
    }
    
    public boolean loadFromFile(String url){
        File f=new File(url);
        try{
            BufferedReader reader=new BufferedReader(new FileReader(f));
            String line=reader.readLine();
            while(line!=null){
                StringTokenizer st=new StringTokenizer(line,",");
                String cid=st.nextToken().trim();
                String brandID=st.nextToken().trim();
                String color=st.nextToken().trim();
                String fid=st.nextToken().trim();
                String eid=st.nextToken().trim();
                int pos=brandList.searchId(brandID);
                Brands brand= brandList.brandList.get(pos);
                Cars ncar=new Cars(cid, brand, color, fid, eid);
                carlist.add(ncar);                
                line=reader.readLine();
                
            }
            return true;
        }catch(Exception e){
            System.out.println("Car File error");
            return false;
        }        
    }
    public void listCars(){
        if(carlist.isEmpty()){
            System.out.println("Car List have nothing to print");
            return;
        }
        Collections.sort(carlist);
        System.out.println("_CarList_");
        for (Cars cars : carlist) {
            System.out.println(cars.screenString());
        }
    }
    
    public int searchId(String carID){
    for(int i=0;i<=carlist.size()-1;i++ ){
        if(carlist.get(i).getCarId().equals(carID)){
            return i;
        }
            }
    return -1;
        }
    public int searchFrame(String fid){
        for(int i=0;i<=carlist.size()-1;i++ ){
            if(carlist.get(i).getFrameId().equals(fid)){
                return 1;
            }
                }
        return -1;
            }
    public int searchEngine(String eId){
    for(int i=0;i<=carlist.size()-1;i++ ){
        if(carlist.get(i).getEngineID().equals(eId)){
            return 1;
        }
            }
    return -1;
        }
    
    public void addCar(){
        boolean isDup=false;
        String cid;
        do{
            isDup=false;
            cid=Inputter.getString("input cid", "err");
            if(this.searchId(cid)!=-1){
                isDup=true;
                System.out.println("Car id is duplicate");  
            }
        }while(isDup);
        
        String fid;
        do{
            isDup=false;
            fid=Inputter.getString("input fid", "Frame must be match F00000","F\\d{5}");
            if(this.searchFrame(fid)!=-1){
                isDup=true;
                System.out.println("Frame id is duplicate");  
            }
        }while(isDup);
        
        
        String eid;
        do{
            isDup=false;
            eid=Inputter.getString("input engine id", "Engine must be match E00000","E\\d{5}");
            if(this.searchEngine(eid)!=-1){
                isDup=true;
                System.out.println("Engine id is duplicate");  
            }
        }while(isDup);
        
        String color=Inputter.getString("Input color","err");
        
        Brands brand=brandList.getUserChoice();
        
        Cars ncar=new Cars(cid, brand, color, fid, eid);
        carlist.add(ncar);
        System.out.println("Car addind is success");
        
    }
    public void printBasedBrandName(){
        String key=Inputter.getString("Input a part of BrandNAme you wanna find", "That field is required");
        int count=0;
        for (Cars cars : carlist) {
            if(cars.getBrand().getBrandName().contains(key)){
                System.out.println(cars.screenString());
                count++;
            }
        }
        if(count==0){
            System.out.println("No car is detected");
        }
              
    }
    public boolean removeCar(){
     String keyID=Inputter.getString("Input carID you wanna delete","That field is required");
     int pos=this.searchId(keyID);
     if(pos==-1){
         System.out.println("Car is not found");
         return false;
     }else{
         System.out.println("Car before removing");
         System.out.println(carlist.get(pos).screenString());
         carlist.remove(pos);
         System.out.println("removed successfully");
         return true;
     }
    }
    
    public boolean udateCar(){
     String keyID=Inputter.getString("Input carID you wanna udate","That field is required");
     int pos=this.searchId(keyID);
     if(pos==-1){
         System.out.println("Car is not found");
         return false;
     }else{
         System.out.println("Car before udating");
         System.out.println(carlist.get(pos).screenString());
          
         
        String cid;
        boolean isDup=false;
        do{
            isDup=false;
            cid=Inputter.getString("input cid", "err");
            if(this.searchId(cid)!=-1){
                isDup=true;
                System.out.println("Car id is duplicate");  
            }
        }while(isDup);
        
        String fid;
        do{
            isDup=false;
            fid=Inputter.getString("input fid", "Frame must be match F00000","F\\d{5}");
            if(this.searchFrame(fid)!=-1){
                isDup=true;
                System.out.println("Frame id is duplicate");  
            }
        }while(isDup);
        
        
        String eid;
        do{
            isDup=false;
            eid=Inputter.getString("input engine id", "Engine must be match E00000","E\\d{5}");
            if(this.searchEngine(eid)!=-1){
                isDup=true;
                System.out.println("Engine id is duplicate");  
            }
        }while(isDup);
        
        String color=Inputter.getString("Input color","err");
        
        Brands brand=brandList.getUserChoice();
        
        carlist.get(pos).setBrand(brand);
        carlist.get(pos).setColor(color);
        carlist.get(pos).setFrameId(fid);
        carlist.get(pos).setEngineID(eid);
                
        System.out.println("udated successfully");
        return true;
     }   
    }
    public void searchCar(){
        String keyID=Inputter.getString("Input carID you wanna delete","That field is required");
        int pos=this.searchId(keyID);
        if(pos==-1){
            System.out.println("Car is not found");
            
        }else{
            System.out.println("Car Information:");
            System.out.println(carlist.get(pos).screenString());
            carlist.remove(pos);
            
           
        }
       }
    
    public boolean saveToFile(String url){
        File f=new File(url);
        try{
            OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(f));
            for (Cars cars : carlist) {
                writer.write(cars.toString());
                writer.write("\n");
            }
            writer.flush();
            return true;
        }catch(Exception e){
            System.out.println("Save car list err");
            return false;
        }           
    }
}
