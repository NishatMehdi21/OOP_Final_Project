class Engine{
    private int capacity;
    private int health;

    public Engine(){
        capacity = 1600;
        health = 100;
    }
    public Engine(int capacity, int health){
        this.capacity = capacity;
        this.health = health;
    }

    public int getCapacity(){
        return capacity;
    }
    public int getHealth(){
        return health;
    }

    // Yeh function health ko wapis restore karnay kay liya hai
    public void restore(){
        health = 100;
    }
}

abstract class Vehicle{
    protected String make;
    protected String model;

    protected double oilLevel;
    protected double airPressure;
    protected double tyres;
    protected double suspension;

    protected Boolean isBroken;
    protected Engine engine;

    public Vehicle(String make, String model, double oilLevel, double airPressure, double tyres, double suspension){
        this.make = make;
        this.model = model;
        this.oilLevel = oilLevel;
        this.airPressure = airPressure;
        this.tyres = tyres;
        this.suspension = suspension;

        this.isBroken = false;
        this.engine = new Engine();
    }

    // main abstract methods
    public abstract void showHealth();
    public abstract void displayDetails();

    // extra method hai for engine health (Rakna howa to rakh lena)
    public void checkStatus() {
        if (engine.getHealth() <= 0) {
            isBroken = true;
            System.out.println("Vehicle is BROKEN"); 
        } else if (engine.getHealth() < 30) {
            System.out.println("WARNING: Engine Low"); 
        } else {
            System.out.println("Engine is Healthy");
        }
    }
    // same here
    public void serviceEngine() {
        engine.restore();
        isBroken = false;
    }

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getOilLevel() {
        return oilLevel;
    }
    public void setOilLevel(double oilLevel) {
        this.oilLevel = oilLevel;
    }
    public double getAirPressure() {
        return airPressure;
    }
    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }
    public double getTyres() {
        return tyres;
    }
    public void setTyres(double tyres) {
        this.tyres = tyres;
    }
    public double getSuspension() {
        return suspension;
    }
    public void setSuspension(double suspension) {
        this.suspension = suspension;
    }
}

class Car extends Vehicle{
    private boolean HasAC;

    Car(String make, String model, double oilLevel, double airPressure, double tyres, double suspension, boolean HasAC){
        super(make,model,oilLevel, airPressure,tyres, suspension);
        this.HasAC = HasAC;
    }

    public void displayDetails(){
        System.out.println("============================");
        System.out.println("CAR INFORMATION");
        System.out.println("============================");
        System.out.println("Make: "+make);
        System.out.println("Model: "+model);
        System.out.println("Air Conditioner Variant: "+ HasAC);
        System.out.println("---------------------------");
        showHealth();
    }

    public void showHealth(){
        System.out.println("CAR HEALTH ANALYTICS");
        System.out.println("------------------------");
        System.out.println("Oil Level: "+oilLevel);
        System.out.println("Air Pressure: "+airPressure);
        System.out.println("Tyre Health: "+ tyres);
        System.out.println("Suspension Health: "+suspension);

    }
}

class Motorcycle extends Vehicle{
    private double chainSprocketHealth;

    Motorcycle(String make, String model, double oilLevel, double airPressure, double tyres, double suspension, double chainSprocketHealth){
        super(make, model, oilLevel, airPressure, tyres, suspension);
        this.chainSprocketHealth= chainSprocketHealth;
    }
    public void displayDetails(){
        System.out.println("============================");
        System.out.println("Motorcycle Information System");
        System.out.println("============================");
        System.out.println("Make: "+make);
        System.out.println("Model: "+model);
        System.out.println("---------------------------");
        showHealth();
    }

    public void showHealth(){
        System.out.println("Motorcycle HEALTH ANALYTICS");
        System.out.println("------------------------");
        System.out.println("Oil Level: "+oilLevel);
        System.out.println("Air Pressure: "+airPressure);
        System.out.println("Tyre Health: "+ tyres);
        System.out.println("Suspension Health: "+suspension);
        System.out.println("Chain-Sprocket Health: "+ chainSprocketHealth);

    }

}

public class OOP_final_Proj {
    
    public static void main(String[] args) {
        Car c1 = new Car("Honda", "Civic", 9.0, 8.0, 6.0,7.0,true);
        c1.displayDetails();
        Motorcycle M1 = new Motorcycle("Yamaha", "YBR-G", 10,9,8,10,8);
        M1.checkStatus();
        M1.displayDetails();
    }
}
