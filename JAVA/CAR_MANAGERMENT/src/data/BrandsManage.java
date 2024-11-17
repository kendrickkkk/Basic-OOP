package data;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;
import tool.Inputter;
import ui.Menu;

public class BrandsManage {
    ArrayList<Brands> brandList=new ArrayList<>();

    public BrandsManage() {
    }
    public int searchId(String id){
        for(int i=0;i<=brandList.size()-1;i++){
            if(brandList.get(i).getBrandID().equals(id)){
                return i;
            }
        }
        return -1;
    }
    public void updateBrand(){
        String keyID=Inputter.getString("Input your id you wanna find:","That Field is required");
        int pos= searchId(keyID);
        if(pos==-1){
            System.out.println("Brand is not exit");
        }else{
            System.out.println("The information of Brand");
            System.out.println(brandList.get(pos));
            System.out.println("Udating");
            String nname= Inputter.getString("Input new Brand Name","That field is required");
        
            String nsound=Inputter.getString("Input new sound name","That firld is required");
        
            double nprice=Inputter.getADouble("Input new double:","Wrong",0,Integer.MAX_VALUE);
            brandList.get(pos).setBrandName(nname);
            brandList.get(pos).setSoundBrand(nsound);
            brandList.get(pos).setPrice(nprice);
            System.out.println("Udating is successful");
        }
    }
    
    public void addBrand(){
        System.out.println("Add new brand");
        boolean isDup=false;
        String id;
        do{
            isDup= false;
            id= Inputter.getString("Innput brrand id", "That field is required");
            for (Brands brands : brandList) {
                if(brands.getBrandID().equals(id)){
                    isDup=true;
                    System.out.println("This id iis dupplicated");
                }
            }
        }while(isDup);
        String name= Inputter.getString("Input Brand Name","That field is required");
        
        String sound=Inputter.getString("Input sound name","That firld is required");
        
        double price=Inputter.getADouble("Input double:","Wrong",0,Integer.MAX_VALUE);
        
        Brands nBrand=new Brands(id, name, sound, price);
        brandList.add(nBrand);
        System.out.println("Adding Brand is successful");
                
    }
    public boolean loadFromFile(String url){
        File f=new File(url);
        try {
            BufferedReader reader=new BufferedReader(new FileReader(f));
            String line =reader.readLine();
            while(line!=null){
                StringTokenizer st=new StringTokenizer(line,",");
                String brandId=st.nextToken().trim();
                String brandName=st.nextToken().trim();
                String str=st.nextToken().trim();
                st = new StringTokenizer(str,":");
                String soundBrand= st.nextToken().trim();
                double price= Double.parseDouble(st.nextToken());
                Brands nBrand=new Brands(brandId, brandName, soundBrand, price);
                brandList.add(nBrand);
                line=reader.readLine();
            }
            return true;
        } catch (Exception e) {
            System.out.println("file lor");
            return false;
        }
    }
    
    public void listBrands(){
        if(brandList.isEmpty()){
            System.out.println("Nothing in Brands Liet to print");
            return;
        }
        System.out.println("___Brands List___");
        for (Brands brands : brandList) {
            System.out.println(brands.toString());
        }
    }
    
    public Brands getUserChoice(){
        Menu menubrand= new Menu("BrandListMenu");
        return menubrand.ref_getChoice(brandList);        
    }

    public void searchBrand(){
        String keyID=Inputter.getString("Input your id you wanna find:","That Field is required");
        int pos= searchId(keyID);
        if(pos==-1){
            System.out.println("Brand is not exit");
        }else{
            System.out.println("The information of Brand");
            System.out.println(brandList.get(pos));
           
        }
    }    
public boolean saveToFile(String url){
        File f=new File(url);
        try{
            OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(f));
            for (Brands brand : brandList) {
                writer.write(brand.toString());
                writer.write("\n");
            }
            writer.flush();
            return true;
        }catch(Exception e){
            System.out.println("Save brand list err");
            return false;
        }           
    }    
    
}
