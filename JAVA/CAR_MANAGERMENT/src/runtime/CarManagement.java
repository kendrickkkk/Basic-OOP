
package runtime;

import data.BrandsManage;
import data.CarsManage;
import ui.Menu;

public class CarManagement {


    public static void main(String[] args) {
     String urlBrand="C:\\Users\\84354\\Documents\\NetBeansProjects\\brandslist.txt";
     BrandsManage bl=new BrandsManage();
     bl.loadFromFile(urlBrand);
     
     String urlCars="C:\\Users\\84354\\Documents\\NetBeansProjects\\cars.txt";
     CarsManage cl=new CarsManage(bl);
     cl.loadFromFile(urlCars);
   
     Menu menu=new Menu("_____Menu____");
     menu.addNewoption("List all Brands");
     menu.addNewoption("Add new Brand");
     menu.addNewoption("Search a brand based on its ID");
     menu.addNewoption("Udate by a brand");
     menu.addNewoption("Save brands to the File,named brand.txt");
     menu.addNewoption("List allcar in ascending order of brand name");
     menu.addNewoption("List cars based on apart of brand name");
     menu.addNewoption("add a car");
     menu.addNewoption("Remove car based on id");
     menu.addNewoption("udate car based on id");
     menu.addNewoption("save cars to file,name cars.txt");
     int choice;
     while(true){
         menu.showMenu();
         choice=menu.getChoice();
         switch(choice){
             case 1:{
                 bl.listBrands();
                 break;
             }
             case 2:{
                 bl.addBrand();
                 break;
             }
             case 3:{
                 bl.searchBrand();
                 break;
             }
             case 4:{
                 bl.updateBrand();
                 break;
             }
             case 5:{
                 bl.saveToFile(urlBrand);
                 System.out.println("Saved");
                 break;
             }
             case 6:{
                 cl.listCars();
                 break;
             }
             case 7:{
                 cl.printBasedBrandName();
                 break;
             }
             case 8:{
                 cl.addCar();
                 break;
             }
             case 9:{
                 cl.removeCar();
                 break;
             }
             case 10:{
                 cl.udateCar();
                 break;
             }
             case 11:{
                 cl.saveToFile(urlCars);
                 System.out.println("Saved");
                 break;
             }
         }
     }
        
    }
    
}
