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
    public String checkStatus() {
        if (engine.getHealth() <= 0) {
            isBroken = true;
            return "Vehicle is BROKEN";
        } else if (engine.getHealth() < 30) {
            return "WARNING: Engine Low";
        } else {
            return "Vehicle is Healthy";
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

public class OOP_final_Proj {
    
}
