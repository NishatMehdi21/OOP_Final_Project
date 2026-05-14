interface Diagnosable {
    public double calculateDamage();
    public String generateReport();
}

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
    protected double brakes;

    protected Boolean isBroken;
    protected Engine engine;

    public Vehicle(String make, String model, double oilLevel, double airPressure, double tyres, double suspension, int capacity, int health, double brakes){
        this.make = make;
        this.model = model;
        this.oilLevel = oilLevel;
        this.airPressure = airPressure;
        this.tyres = tyres;
        this.suspension = suspension;
        this.brakes = brakes;

        this.isBroken = false;
        this.engine = new Engine(capacity, health);
    }

    // main abstract methods
    public abstract void showHealth();
    public abstract void displayDetails();
    public abstract double CalculateScore();


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

class Car extends Vehicle implements Diagnosable{
    private boolean HasAC;

    Car(String make, String model, double brakes, double oilLevel, double airPressure, double tyres, double suspension, boolean HasAC, int capacity, int health){
        super(make,model,oilLevel, airPressure,tyres, suspension, capacity, health, brakes);
        this.HasAC = HasAC;
    }

    public double calculateDamage(){
        return (100-CalculateScore());
    }

    public String generateReport(){
        return "--------Car Health Report--------\n"+
        "Overall Score: " + CalculateScore()+ "\n"+
        "Total Damage: " + calculateDamage()+ "\n"+
        "Engine Health: "+ engine.getHealth()+ "\n"+
        "Oil Level: " + oilLevel+ "\n"+
        "Tyre Health: " + tyres+ "\n"+
        "Brake Health: "+ brakes +"\n"+
        "Suspension "+ suspension+ "\n"+
        "Has Ac: " + HasAC; 

    }


    public void displayDetails(){
        System.out.println("============================");
        System.out.println("CAR INFORMATION");
        System.out.println("============================");
        System.out.println("Make: "+make);
        System.out.println("Model: "+model);
        System.out.println("Engine Power: "+ engine.getCapacity()+"cc");
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

    public double CalculateScore() {
        return (engine.getHealth() / 100.0 * 50) +  // Max 50
           (oilLevel / 10.0 * 20) +             // Max 20
           (tyres / 10.0 * 10) +                // Max 10
           (suspension / 10.0 * 10) +           // Max 10
           (airPressure / 10.0 * 5)+           // Max 5
           (brakes/10 *5);                      //Max 5
           // Total Max = 100
    }
}

class Truck extends Vehicle implements Diagnosable{
    private double loadCapacity;

    Truck(String make, String model, double brakes, double oilLevel, double airPressure, double tyres, double suspension, int capacity, int health, double loadCapacity){
        super(make,model,oilLevel, airPressure,tyres, suspension, capacity, health, brakes);
        this.loadCapacity = loadCapacity;
    }

    public double calculateDamage(){
        return (100-CalculateScore());
    }

    public String generateReport(){
        return "--------Truck Health Report--------\n"+
        "Overall Score: " + CalculateScore()+ "\n"+
        "Total Damage: " + calculateDamage()+ "\n"+
        "Engine Health: "+ engine.getHealth()+ "\n"+
        "Oil Level: " + oilLevel+ "\n"+
        "Tyre Health: " + tyres+ "\n"+
        "Brake Health: "+ brakes +"\n"+
        "Suspension "+ suspension+ "\n"+
        "Load Capacity: " + loadCapacity; 

    }


    public void displayDetails(){
        System.out.println("============================");
        System.out.println("TRUCK INFORMATION");
        System.out.println("============================");
        System.out.println("Make: "+make);
        System.out.println("Model: "+model);
        System.out.println("Engine Power: "+ engine.getCapacity()+"cc");
        System.out.println("Load Capacity in Kg: "+ loadCapacity);
        System.out.println("---------------------------");
        showHealth();
    }

    public void showHealth(){
        System.out.println("TRUCK HEALTH ANALYTICS");
        System.out.println("------------------------");
        System.out.println("Oil Level: "+oilLevel);
        System.out.println("Air Pressure: "+airPressure);
        System.out.println("Tyre Health: "+ tyres);
        System.out.println("Suspension Health: "+suspension);

    }

    public double CalculateScore() {
        return (engine.getHealth() / 100.0 * 50) +  // Max 50
           (oilLevel / 10.0 * 20) +             // Max 20
           (tyres / 10.0 * 10) +                // Max 10
           (suspension / 10.0 * 10) +           // Max 10
           (airPressure / 10.0 * 5)+           // Max 5
           (brakes/10 *5);                      //Max 5
           // Total Max = 100
    }
}

class Motorcycle extends Vehicle implements Diagnosable{
    private double chainSprocketHealth;

    Motorcycle(String make, String model,double brakes, double oilLevel, double airPressure, double tyres, double suspension, double chainSprocketHealth, int engineCapacity, int EngineHealth){
        super(make, model, oilLevel, airPressure, tyres, suspension, engineCapacity, EngineHealth, brakes);
        this.chainSprocketHealth= chainSprocketHealth;
    }

    public double calculateDamage(){
        return (100-CalculateScore());
    }

    public String generateReport(){
        return "--------Car Health Report--------\n"+
        "Overall Score: " + CalculateScore()+ "\n"+
        "Engine Health: "+ engine.getHealth()+"\n"+
        "Oil Level: " + oilLevel+"\n"+
        "Tyre Health: " + tyres+"\n"+
        "Brake Health: "+ brakes +"\n"+
        "Suspension "+ suspension+"\n"+
        "Chain Sprocket Health: " + chainSprocketHealth; 

    }
    public void displayDetails(){
        System.out.println("============================");
        System.out.println("Motorcycle Information System");
        System.out.println("============================");
        System.out.println("Make: "+make);
        System.out.println("Model: "+model);
        System.out.println("Engine Power: "+ engine.getCapacity()+"cc");
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

    public double CalculateScore() {
        return (engine.getHealth() / 100.0 * 40) +      // Max 40 pointa for Engine
           (oilLevel / 10.0 * 15) +                 // Max 15
           (tyres / 10.0 * 15) +                    // Max 15
           (suspension / 10.0 * 10) +               // Max 10
           (airPressure / 10.0 * 5) +              // Max 5
           (chainSprocketHealth / 10.0 * 10)+       // Max 10
           (brakes /10 * 5);                           //Max 5
           // Total Max = 100
    }
}

class Trip{
    private double distance;
    private String terrain;
    private double load;

    private Vehicle vehicle;

    public Trip(double distance, String terrain, double load, Vehicle vehicle) {
        this.distance = distance;
        this.terrain = terrain;
        this.load = load;
        this.vehicle = vehicle;
    }

    public void showDetails(){
        System.out.println("=========================");
        System.out.println("      TRIP SUMMARY");
        System.out.println("=========================");
        System.out.println("Total Distance: "+distance);
        System.out.println("Terrain: "+terrain);
        System.out.println("Vehicle Used: "+ vehicle.getModel());
        System.out.println();
    }

    public void getAdvice() {
        double score = vehicle.CalculateScore();
        if (score>=90){
            System.out.println("The Overall Vehicle Score is " + score+ "/100.\n Your Vehicle: " + vehicle.getModel()+ " is in good condition and ready for the trip.");
        }
        else if(score>=80){
            System.out.println("The Overall Vehicle Score is " + score+ "/100.\n You are Advised to check necessary parts like engine and oil level before begining you trip");
        }
        else if(score>=70){
            System.out.println("The Overall Vehicle Score is " + score+ "/100. \n you are advised to get a professional inspection before the trip");
        }
        else{
            System.out.println("WARNING! The Overall Vehicle Score is " + score+ "/100.\n You Must Take Your Vehicle to a Workshop for Repairs");
        }
        
             
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public double getDistance() {
        return distance;
    }
    public String getTerrain() {
        return terrain;
    }
    public double getLoad() {
        return load;
    }
}

public class OOP_final_Proj {
    
    public static void main(String[] args) {
        Car c1 = new Car("Honda", "Civic", 10.0,9.0, 8.0, 6.0,7.0,true,1800, 90);
        //c1.displayDetails();
        Motorcycle M1 = new Motorcycle("Yamaha", "YBR-G", 8.0,10,10,10,10,10.0, 125, 90);
        //M1.checkStatus();
        //M1.displayDetails();

        Trip Kashmir = new Trip(500, "Hilly", 100, M1);
        Kashmir.showDetails();
        Kashmir.getAdvice();
    }

}
