package data;


public class Cars implements Comparable<Cars>{
    private String carId;
    private Brands brand;
    private String color;
    private String frameId;    
    private String engineID;

    public Cars() {
    }

    public Cars(String carId, Brands brand, String color, String frameId, String engineID) {
        this.carId = carId;
        this.brand = brand;
        this.color = color;
        this.frameId = frameId;
        this.engineID = engineID;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        String str=String.format("%s, %s, %s, %s, %s",carId,brand.getBrandID()
                ,color,frameId,engineID);
        return str;
    }
    
    public String screenString() {
        String str=String.format("%s\n%s, %s, %s, %s",brand,carId
                ,color,frameId,engineID);
        return str;
    }

    @Override
    public int compareTo(Cars that) {
        String brandnameThis=this.brand.getBrandName();
        String brandnameThat=that.brand.getBrandName();
        if(brandnameThis.compareTo(brandnameThat)>0){
            return 1;
        }else if(brandnameThis.compareTo(brandnameThat)==0){
            if(this.getCarId().compareTo(that.getCarId())>0){
                return 1;
            }
        }
        return -1;
    }
    
}
