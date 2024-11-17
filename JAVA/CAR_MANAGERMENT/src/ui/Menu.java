
package ui;

import data.Brands;
import java.util.ArrayList;
import tool.Inputter;

/*
Menu la 1 khuon duc ra 1 object chuyen quan ly cac obtion,cac lua chon
,co 1 cai mang chua ca option
method add option
method show menu(show cac option)
method lay lua chon
*/

public class Menu {
    ArrayList<String> optionList=new ArrayList<>();
    public String title;
    
    //constructor

    public Menu(String title) {
        this.title = title;
    }
    
    public void addNewoption(String newoption){
        optionList.add(newoption);
    }
    
    public void showMenu(){
        int count=1;
        System.out.println("++++++++++"+title+"++++++++++");
        for(String item:optionList){
            System.out.println(count+"."+ item);
            count++;
        }
    }
    
    public int getChoice(){
        int choice=Inputter.getAnInterger("input your choice:","Your choice must be between 1 and"+optionList.size(),1,optionList.size());
        return choice;
                
    }
    //ham hien thi danh sach cac brand co danh so cho nguoi dung chon lua
    public Brands ref_getChoice(ArrayList<Brands> brandList){
        int count=1;
        for (Brands brands : brandList) {
            
            System.out.println(count+brands.toString());
            count++;
        }
        int choice=Inputter.getAnInterger("Nhap so thu tu cua brand","between 1 to"+brandList.size());
        return brandList.get(choice-1);
    }
    
    
}
