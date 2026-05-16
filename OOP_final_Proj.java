import java.time.LocalDateTime;
import java.util.ArrayList;

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

class DriverBehavior{

    private boolean hardBraking;
    private boolean overspeeding;
    private boolean aggressiveDriving;
    private boolean longDrivingHours;

    DriverBehavior(boolean hardBraking, boolean overspeeding, boolean aggressiveDriving, boolean longDrivingHours){
        this.hardBraking = hardBraking;
        this.overspeeding = overspeeding;
        this.aggressiveDriving = aggressiveDriving;
        this.longDrivingHours = longDrivingHours;
    }

    public boolean isHardBraking() {
        return hardBraking;
    }
    public boolean isOverspeeding() {
        return overspeeding;
    }
    public boolean isAggressiveDriving() {
        return aggressiveDriving;
    }
    public boolean isLongDrivingHours() {
        return longDrivingHours;
    }
}

class Trip{
    private double distance;
    private String terrain;
    private double load;

    private Vehicle vehicle;
    private DriverBehavior behavior;

    public Trip(double distance, String terrain, double load, Vehicle vehicle, DriverBehavior behavior) {
        this.distance = distance;
        this.terrain = terrain;
        this.load = load;
        this.vehicle = vehicle;
        this.behavior = behavior;
    }

    public void showDetails(){
        System.out.println("=========================");
        System.out.println("      TRIP SUMMARY");
        System.out.println("=========================");
        System.out.println("Total Distance: "+distance);
        System.out.println("Terrain: "+terrain);
        System.out.println("Vehicle Used: "+ vehicle.getModel());
        if (behavior.isAggressiveDriving()){
            System.out.println("Aggressive Driving Detected");
        }
        if (behavior.isHardBraking()){
            System.out.println("Hard Braking Detected");
        }
        if (behavior.isLongDrivingHours()){
            System.out.println("Long Driving Hours Detected");
        }
        if (behavior.isOverspeeding()){
            System.out.println("Overspeeding Detected");
        }
        System.out.println();
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
    public DriverBehavior getDriverBehavior(){
        return behavior;
    }
}

class User{

    private String username;
    private String password;

    User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){ 
        return username;
    }
    public String getPassword(){
        return password;
    }
}

class VehicleManager{
    private ArrayList<Vehicle> vehicles;

    public VehicleManager(){
        vehicles = new ArrayList<Vehicle>(); 
    }

    public void addVehicle(Vehicle vehicleObj){
        vehicles.add(vehicleObj);
    }

    public void removeVehicle(int index){
        try{
            vehicles.remove(index);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid Array Index! Please Enter a Valid Index");
        }
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }    

    public void displayAll(){
        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
        }
    }

}

class LoginManager{

    private ArrayList<User> users;
    private User currentUser;

    LoginManager() {
        users = new ArrayList<>();
        currentUser = null;
    }

    public boolean register(String username, String password){
        for(User u : users){
            if(u.getUsername().equals(username)){
                return false;
            }
        }
        users.add(new User(username, password));
                return true;
    }

    public boolean login(String username, String password){
        for(User u : users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    public void logout(){
        currentUser = null;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}

class AdviceManager{

    public String getScoreAdvice(double score){
        if (score >= 90){
            return "Score: " + score + "/100\n✓ EXCELLENT — Vehicle is in peak condition. Safe for any trip.";
        }
        else if (score >= 80){
            return "Score: " + score + "/100\n✓ GOOD — Minor checks recommended. Verify oil and tyre pressure before departure.";
        }
        else if (score >= 70){
            return "Score: " + score + "/100\n⚠ MODERATE — Professional inspection recommended before this trip.";
        }
        else if (score >= 60){
            return "Score: " + score + "/100\n⚠ CONCERNING — Avoid long trips. Service the vehicle soon.";
        }
        else
            return "Score: " + score + "/100\n🔴 DANGEROUS — Do NOT take this trip. Immediate workshop visit required.";
    }

    public String getBehaviorWarnings(DriverBehavior behavior){

        StringBuilder sb = new StringBuilder();
        if (behavior.isHardBraking()){
            sb.append("⚠ Hard braking: accelerates brake pad and tyre wear. Will reduce brake health over time.\n");
        }
        if (behavior.isOverspeeding()){
            sb.append("⚠ Overspeeding: increases engine strain, fuel consumption, and accident risk on this terrain.\n");
        }
        if (behavior.isAggressiveDriving()){
            sb.append("⚠ Aggressive driving: damages suspension and drivetrain. Reduce on hilly terrain.\n");
        }
        if (behavior.isLongDrivingHours()){
            sb.append("⚠ Long driving hours: vehicle needs rest breaks. Check oil and temperature every 2 hours.\n");
        }
        if (sb.length() == 0){
            sb.append("✓ No driver behavior concerns detected.");
        }
        return sb.toString();
    }

    public String getFullAdvice(Trip trip){

        double score = trip.getVehicle().CalculateScore();
        String vehicleAdvice = getScoreAdvice(score);
        String behaviorAdvice = getBehaviorWarnings(trip.getDriverBehavior());

        return vehicleAdvice + "\n\n" + behaviorAdvice;
    }

    public String getSummaryReport(Trip trip){

        String dateTime = LocalDateTime.now().toString();
        String advice = getFullAdvice(trip);

        StringBuilder sb = new StringBuilder();
        sb.append("\n===== VEHICLE TRIP REPORT =====\n\n");
        sb.append("Generated: "+ dateTime + "\n\n");  
        sb.append("Vehicle: " + trip.getVehicle().getMake() + " " + trip.getVehicle().getModel() + "\n");
        sb.append("Distance: " + trip.getDistance() + "\n");
        sb.append("Terrain: " + trip.getTerrain() + "\n");
        sb.append("Load: " + trip.getLoad() + "\n\n");
        sb.append("===== ADVICE =====\n" + advice);

        return sb.toString();
    }
}

public class OOP_final_Proj {
    
    public static void main(String[] args) {
        Car c1 = new Car("Honda", "Civic", 10.0,9.0, 8.0, 6.0,7.0,true,1800, 90);
        //c1.displayDetails();
        Motorcycle M1 = new Motorcycle("Yamaha", "YBR-G", 8.0,10,10,10,10,10.0, 125, 90);
        //M1.checkStatus();
        //M1.displayDetails();
        Truck t1 = new Truck("Volvo", "FH16 Aero", 7.0, 10.0, 9.5, 10, 10, 10, 17000, 50000);
        //t1.checkStatus();
        //t1.displayDetails();

        DriverBehavior db = new DriverBehavior(false, true, false, false);
        
        Trip Kashmir = new Trip(500, "Hilly", 100, M1, db);
        // Kashmir.showDetails();

        VehicleManager Manager1 = new VehicleManager();
        Manager1.addVehicle(c1);
        Manager1.addVehicle(M1);
        Manager1.addVehicle(t1);
        Manager1.displayAll();
        
        User u1 = new User("abc_123", "123@abc");

        LoginManager LM1 = new LoginManager();
        LM1.register("abc_123", "123@abc");
        LM1.login("abc_123", "123@abc");
        LM1.getCurrentUser();
        LM1.getUsers();
        LM1.logout();

        AdviceManager AM1 =new AdviceManager();
        System.out.println(AM1.getSummaryReport(Kashmir));
    }

}
