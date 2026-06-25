import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;

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

    public void restore(){
        health = 100;
    }

    public void reduce(int amount) {
        health = Math.max(0, health - amount);
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
    
    public void serviceEngine() {
        engine.restore();
        isBroken = false;
    }

    public void resetStats() {
        oilLevel    = 10.0;
        airPressure = 10.0;
        tyres       = 10.0;
        suspension  = 10.0;
        brakes      = 10.0;
        isBroken    = false;
        engine.restore();
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
    public double getBrakes() {
        return brakes;
    }
    public void setBrakes(double brakes) {
        this.brakes = brakes;
    }
    public Engine getEngine() {
        return engine;
    }
    public boolean isBroken() {
        return isBroken;
    }
}

class Car extends Vehicle implements Diagnosable{
    private boolean HasAC;

    Car(String make, String model, double brakes, double oilLevel, double airPressure, double tyres, double suspension, boolean HasAC, int capacity, int health){
        super(make,model,oilLevel, airPressure,tyres, suspension, capacity, health, brakes);
        this.HasAC = HasAC;
    }

    public boolean isHasAC() {
        return HasAC;
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

    public double getLoadCapacity() {
        return loadCapacity;
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

    public double getChainSprocketHealth() {
        return chainSprocketHealth;
    }
    public void setChainSprocketHealth(double chainSprocketHealth) {
        this.chainSprocketHealth = chainSprocketHealth;
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

    @Override
    public void resetStats() {
        super.resetStats();                
        chainSprocketHealth = 10.0;
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

class WearManager {

    // Call this after a trip is confirmed complete
    public void applyWear(Trip trip) {

        Vehicle v        = trip.getVehicle();
        String terrain   = trip.getTerrain();
        double distance  = trip.getDistance();
        double load      = trip.getLoad();
        DriverBehavior b = trip.getDriverBehavior();

        // Base wear per 100km (small amounts)
        double baseTyre       = (distance / 100) * 0.3;
        double baseSusp       = (distance / 100) * 0.2;
        double baseBrake      = (distance / 100) * 0.2;
        double baseOil        = (distance / 100) * 0.3;
        double baseEngine     = (distance / 100) * 2;   // engine is 0-100 scale

        // Terrain multipliers
        double tyreMult = 1.0, suspMult = 1.0, brakeMult = 1.0,
               oilMult  = 1.0, engineMult = 1.0;

        if (terrain.equals("City")) {
            brakeMult  = 1.8;   // lots of stopping
            tyreMult   = 1.3;
        } else if (terrain.equals("Highway")) {
            engineMult = 1.5;   // high RPM sustained
            oilMult    = 1.4;
            brakeMult  = 0.6;   // barely braking
        } else if (terrain.equals("Hilly")) {
            suspMult   = 2.0;   // up and down stress
            brakeMult  = 1.8;   // downhill braking
            engineMult = 1.5;   // climbing effort
        } else if (terrain.equals("Off-road")) {
            tyreMult   = 2.5;   // rough surface
            suspMult   = 2.5;
            brakeMult  = 1.2;
        } else if (terrain.equals("Mixed")) {
            tyreMult   = 1.4;
            suspMult   = 1.4;
            brakeMult  = 1.3;
        }

        // Driver behavior additions
        if (b.isHardBraking()) {
            brakeMult += 0.8;
            tyreMult  += 0.5;
        }
        if (b.isOverspeeding()) {
            engineMult += 0.8;
            oilMult    += 0.5;
        }
        if (b.isAggressiveDriving()) {
            suspMult   += 0.7;
            tyreMult   += 0.4;
        }
        if (b.isLongDrivingHours()) {
            oilMult    += 0.8;
            engineMult += 0.4;
        }

        // Load penalty (mainly for trucks, works for all)
        if (load > 200) {
            suspMult += 0.5;
            tyreMult += 0.5;
        }

        // Calculate final wear amounts
        double tyreDrop   = baseTyre   * tyreMult;
        double suspDrop   = baseSusp   * suspMult;
        double brakeDrop  = baseBrake  * brakeMult;
        double oilDrop    = baseOil    * oilMult;
        double engineDrop = baseEngine * engineMult;

        // Apply wear — clamp to 0 minimum
        v.setTyres(     Math.max(0, v.getTyres()      - tyreDrop));
        v.setSuspension(Math.max(0, v.getSuspension() - suspDrop));
        v.setBrakes(    Math.max(0, v.getBrakes()     - brakeDrop));
        v.setOilLevel(  Math.max(0, v.getOilLevel()   - oilDrop));
        // Engine needs its own reduce method (see Step 2)
        reduceEngineHealth(v, (int) Math.round(engineDrop));

        // Motorcycle-specific: chain sprocket wears on hilly/off-road
        if (v instanceof Motorcycle) {
            Motorcycle m = (Motorcycle) v;
            double chainDrop = baseTyre * suspMult * 0.6;
            m.setChainSprocketHealth(Math.max(0, m.getChainSprocketHealth() - chainDrop));
        }

        // Check if vehicle is now broken
        v.checkStatus();
    }

    // Helper — reduces engine health by given amount
    private void reduceEngineHealth(Vehicle v, int amount) {
        v.getEngine().reduce(amount);
    }

    // Returns a summary of what was reduced (show this to user after trip)
    public String getWearSummary(Vehicle v, double oldTyre, double oldSusp,
                                  double oldBrake, double oldOil, int oldEngine) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== WEAR APPLIED AFTER TRIP ===\n\n");
        sb.append(String.format("Engine Health  : %.0f → %.0f\n", (double)oldEngine, (double)v.getEngine().getHealth()));
        sb.append(String.format("Oil Level      : %.1f → %.1f\n", oldOil,   v.getOilLevel()));
        sb.append(String.format("Tyre Health    : %.1f → %.1f\n", oldTyre,  v.getTyres()));
        sb.append(String.format("Suspension     : %.1f → %.1f\n", oldSusp,  v.getSuspension()));
        sb.append(String.format("Brake Health   : %.1f → %.1f\n", oldBrake, v.getBrakes()));

        if (v instanceof Motorcycle) {
            sb.append("Chain-Sprocket also reduced.\n");
        }

        sb.append("\nNew Overall Score: " + String.format("%.1f", v.CalculateScore()) + "/100");
        return sb.toString();
    }
}

class FileManager {
 
    private static final String USERS_FILE    = "users.txt";
    private static final String VEHICLES_FILE = "vehicles.txt";
 
    // ==================== USERS ====================
 
    public void saveUsers(ArrayList<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) {
                bw.write(u.getUsername() + "," + u.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
 
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        File f = new File(USERS_FILE);
        if (!f.exists()) return users;
 
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }
 
    // ==================== VEHICLES ====================
    // Format for CAR:
    //   CAR,make,model,oilLevel,airPressure,tyres,suspension,brakes,engineCapacity,engineHealth,hasAC
    // Format for MOTORCYCLE:
    //   MOTORCYCLE,make,model,oilLevel,airPressure,tyres,suspension,brakes,engineCapacity,engineHealth,chainSprocketHealth
    // Format for TRUCK:
    //   TRUCK,make,model,oilLevel,airPressure,tyres,suspension,brakes,engineCapacity,engineHealth,loadCapacity
 
    public void saveVehicles(ArrayList<Vehicle> vehicles) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VEHICLES_FILE))) {
            for (Vehicle v : vehicles) {
                String line = "";
 
                if (v instanceof Car) {
                    Car c = (Car) v;
                    line = "CAR," + v.getMake() + "," + v.getModel() + ","
                         + v.getOilLevel() + "," + v.getAirPressure() + ","
                         + v.getTyres() + "," + v.getSuspension() + ","
                         + v.getBrakes() + ","
                         + v.getEngine().getCapacity() + "," + v.getEngine().getHealth() + ","
                         + c.isHasAC();
 
                } else if (v instanceof Motorcycle) {
                    Motorcycle m = (Motorcycle) v;
                    line = "MOTORCYCLE," + v.getMake() + "," + v.getModel() + ","
                         + v.getOilLevel() + "," + v.getAirPressure() + ","
                         + v.getTyres() + "," + v.getSuspension() + ","
                         + v.getBrakes() + ","
                         + v.getEngine().getCapacity() + "," + v.getEngine().getHealth() + ","
                         + m.getChainSprocketHealth();
 
                } else if (v instanceof Truck) {
                    Truck t = (Truck) v;
                    line = "TRUCK," + v.getMake() + "," + v.getModel() + ","
                         + v.getOilLevel() + "," + v.getAirPressure() + ","
                         + v.getTyres() + "," + v.getSuspension() + ","
                         + v.getBrakes() + ","
                         + v.getEngine().getCapacity() + "," + v.getEngine().getHealth() + ","
                         + t.getLoadCapacity();
                }
 
                if (!line.isEmpty()) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }
 
    public ArrayList<Vehicle> loadVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        File f = new File(VEHICLES_FILE);
        if (!f.exists()) return vehicles;
 
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] p = line.split(",");
 
                // p[0]=type, p[1]=make, p[2]=model,
                // p[3]=oil, p[4]=air, p[5]=tyres, p[6]=suspension, p[7]=brakes,
                // p[8]=engineCap, p[9]=engineHealth, p[10]=extraField
 
                String type = p[0];
 
                if (type.equals("CAR") && p.length >= 11) {
                    vehicles.add(new Car(
                        p[1], p[2],
                        Double.parseDouble(p[7]),   // brakes
                        Double.parseDouble(p[3]),   // oil
                        Double.parseDouble(p[4]),   // air
                        Double.parseDouble(p[5]),   // tyres
                        Double.parseDouble(p[6]),   // suspension
                        Boolean.parseBoolean(p[10]),// hasAC
                        Integer.parseInt(p[8]),     // engineCapacity
                        Integer.parseInt(p[9])      // engineHealth
                    ));
 
                } else if (type.equals("MOTORCYCLE") && p.length >= 11) {
                    vehicles.add(new Motorcycle(
                        p[1], p[2],
                        Double.parseDouble(p[7]),   // brakes
                        Double.parseDouble(p[3]),   // oil
                        Double.parseDouble(p[4]),   // air
                        Double.parseDouble(p[5]),   // tyres
                        Double.parseDouble(p[6]),   // suspension
                        Double.parseDouble(p[10]),  // chainSprocketHealth
                        Integer.parseInt(p[8]),     // engineCapacity
                        Integer.parseInt(p[9])      // engineHealth
                    ));
 
                } else if (type.equals("TRUCK") && p.length >= 11) {
                    vehicles.add(new Truck(
                        p[1], p[2],
                        Double.parseDouble(p[7]),   // brakes
                        Double.parseDouble(p[3]),   // oil
                        Double.parseDouble(p[4]),   // air
                        Double.parseDouble(p[5]),   // tyres
                        Double.parseDouble(p[6]),   // suspension
                        Integer.parseInt(p[8]),     // engineCapacity
                        Integer.parseInt(p[9]),     // engineHealth
                        Double.parseDouble(p[10])   // loadCapacity
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading vehicles: " + e.getMessage());
        }
        return vehicles;
    }
 
    // ==================== REPORT ====================
 
    public void saveReport(String content, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(content);
        } catch (IOException e) {
            System.out.println("Error saving report: " + e.getMessage());
        }
    }
}

class Theme {
    static final String BASE       = "#1a1f2e";
    static final String SURFACE    = "#242938";
    static final String CARD       = "#2d3348";
    static final String NAV        = "#1e2436";
    static final String BORDER     = "#3a4060";
    static final String TEXT       = "#e2e8f0";
    static final String MUTED      = "#8892a4";
    static final String ACCENT     = "#6c8ef7";
    static final String GREEN      = "#2d6a4f";
    static final String GREEN_TEXT = "#95d5b2";
    static final String RED        = "#6b1f1f";
    static final String RED_TEXT   = "#ffb3b3";
    static final String PURPLE     = "#3d2d6e";
    static final String PURPLE_TEXT= "#c4b5fd";

    static final String SCORE_GREEN_BG = "#1a3d2b";
    static final String SCORE_GREEN_FG = "#09c457";
    static final String SCORE_AMBER_BG = "#3d2e0a";
    static final String SCORE_AMBER_FG = "#ffcf32";
    static final String SCORE_RED_BG   = "#3d1515";
    static final String SCORE_RED_FG   = "#f35151";

    static final String NAV_STYLE =
        "-fx-background-color: " + NAV + ";" +
        "-fx-padding: 10 16 10 16;" +
        "-fx-border-color: " + BORDER + ";" +
        "-fx-border-width: 0 0 1 0;";

    static final String INPUT_STYLE =
        "-fx-background-color: " + CARD + ";" +
        "-fx-border-color: " + BORDER + ";" +
        "-fx-border-radius: 6;" +
        "-fx-background-radius: 6;" +
        "-fx-text-fill: " + TEXT + ";" +
        "-fx-font-size: 13;" +
        "-fx-prompt-text-fill: " + MUTED + ";";

    static final String LABEL_STYLE =
        "-fx-text-fill: " + TEXT + ";" +
        "-fx-font-size: 13;";

    static String btn(String bg, String fg) {
        return "-fx-background-color: " + bg + ";" +
               "-fx-text-fill: " + fg + ";" +
               "-fx-background-radius: 6;" +
               "-fx-border-radius: 6;" +
               "-fx-cursor: hand;" +
               "-fx-padding: 7 18 7 18;" +
               "-fx-font-size: 13;";
    }

    static String ghostBtn() {
        return "-fx-background-color: " + CARD + ";" +
               "-fx-text-fill: " + MUTED + ";" +
               "-fx-border-color: " + BORDER + ";" +
               "-fx-border-width: 1;" +
               "-fx-border-radius: 6;" +
               "-fx-background-radius: 6;" +
               "-fx-cursor: hand;" +
               "-fx-padding: 7 18 7 18;" +
               "-fx-font-size: 13;";
    }

    static String sectionLabel() {
        return "-fx-font-size: 11;" +
               "-fx-text-fill: " + ACCENT + ";" +
               "-fx-font-weight: bold;";
    }
}

class SceneManager {
    private Stage stage;
    private LoginManager loginManager;
    private VehicleManager vehicleManager;
    private FileManager fileManager;
    private AdviceManager adviceManager;

    SceneManager(Stage stage, LoginManager loginManager,
                 VehicleManager vehicleManager, FileManager fileManager,
                 AdviceManager adviceManager) {
        this.stage         = stage;
        this.loginManager  = loginManager;
        this.vehicleManager= vehicleManager;
        this.fileManager   = fileManager;
        this.adviceManager = adviceManager;
    }

    public void showSplash() {
        SplashScreen splash = new SplashScreen(this);
        stage.setScene(new Scene(splash, 780, 540));
    }
    public void showLogin() {
        LoginPanel panel = new LoginPanel(this, loginManager, fileManager);
        stage.setScene(new Scene(panel, 780, 540));
    }
    public void showRegister() {
        RegisterPanel panel = new RegisterPanel(this, loginManager, fileManager);
        stage.setScene(new Scene(panel, 780, 540));
    }
    public void showDashboard() {
        DashboardPanel panel = new DashboardPanel(this, vehicleManager, loginManager, fileManager);
        stage.setScene(new Scene(panel, 780, 540));
    }
    public void showAddVehicle() {
        AddVehiclePanel panel = new AddVehiclePanel(this, vehicleManager, fileManager);
        stage.setScene(new Scene(panel, 780, 540));
    }
    public void showVehicleDetails(Vehicle v) {
        VehicleDetailsPanel panel = new VehicleDetailsPanel(this, fileManager, vehicleManager);
        panel.loadVehicle(v);
        stage.setScene(new Scene(panel, 780, 540));
    }
    public void showTrip() {
        TripPanel panel = new TripPanel(this, vehicleManager, adviceManager);
        stage.setScene(new Scene(panel, 780, 540));
    }
    public void showAdvice(Trip trip) {
        AdvicePanel panel = new AdvicePanel(this, fileManager, vehicleManager);
        panel.loadAdvice(trip, adviceManager);
        stage.setScene(new Scene(panel, 780, 540));
    }
}

class SplashScreen extends StackPane {
    SplashScreen(SceneManager sceneManager) {
        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        try {
            javafx.scene.image.Image logo = new javafx.scene.image.Image(
                new java.io.FileInputStream("logo.png")
            );
            javafx.scene.image.ImageView logoView = new javafx.scene.image.ImageView(logo);
            logoView.setFitWidth(320);
            logoView.setPreserveRatio(true);

            Label tagline = new Label("Starting up...");
            tagline.setFont(Font.font("SansSerif", 13));
            tagline.setTextFill(javafx.scene.paint.Color.web(Theme.MUTED));

            VBox content = new VBox(16, logoView, tagline);
            content.setAlignment(Pos.CENTER);
            getChildren().add(content);

        } catch (java.io.FileNotFoundException e) {
            Label fallback = new Label("MotoMetrics");
            fallback.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
            fallback.setTextFill(javafx.scene.paint.Color.web(Theme.ACCENT));
            getChildren().add(fallback);
        }

        javafx.animation.PauseTransition pause =
            new javafx.animation.PauseTransition(javafx.util.Duration.seconds(2));
        pause.setOnFinished(e -> sceneManager.showLogin());
        pause.play();
    }
}

class LoginPanel extends VBox {
    LoginPanel(SceneManager sceneManager, LoginManager loginManager, FileManager fileManager) {
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        VBox form = new VBox(10);
        form.setAlignment(Pos.CENTER_LEFT);
        form.setPadding(new Insets(24, 32, 28, 32));
        form.setMaxWidth(400);
        form.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;"
        );

        Label appTitle = new Label("MotoMetrics — Login");
        appTitle.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: " + Theme.TEXT + ";");

        Label subtitle = new Label("Vehicle Wear & Tear Simulation");
        subtitle.setStyle("-fx-font-size: 12; -fx-text-fill: " + Theme.MUTED + ";");

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: " + Theme.BORDER + ";");

        Label userLabel = new Label("Username");
        userLabel.setStyle(Theme.LABEL_STYLE);
        TextField userField = new TextField();
        userField.setPromptText("Enter username");
        userField.setMaxWidth(Double.MAX_VALUE);
        userField.setStyle(Theme.INPUT_STYLE);

        Label passLabel = new Label("Password");
        passLabel.setStyle(Theme.LABEL_STYLE);
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter password");
        passField.setMaxWidth(Double.MAX_VALUE);
        passField.setStyle(Theme.INPUT_STYLE);

        Label errorLabel = new Label(" ");
        errorLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12;");

        Button loginBtn = makeBtn("Login",    Theme.ACCENT, "#ffffff");
        Button regBtn   = makeBtn("Register", Theme.CARD,   Theme.MUTED);
        HBox btnRow = new HBox(10, loginBtn, regBtn);

        form.getChildren().addAll(appTitle, subtitle, sep, userLabel, userField, passLabel, passField, errorLabel, btnRow);
        getChildren().add(form);

        loginBtn.setOnAction(e -> {
            String username = userField.getText().trim();
            String password = passField.getText().trim();
            if (username.isEmpty() || password.isEmpty()) { errorLabel.setText("Please fill in all fields."); return; }
            if (loginManager.login(username, password)) {
                errorLabel.setText(" "); userField.clear(); passField.clear();
                sceneManager.showDashboard();
            } else {
                errorLabel.setText("Invalid username or password.");
            }
        });
        regBtn.setOnAction(e -> { userField.clear(); passField.clear(); errorLabel.setText(" "); sceneManager.showRegister(); });
        passField.setOnAction(e -> loginBtn.fire());
    }

    private Button makeBtn(String text, String bg, String fg) {
        Button btn = new Button(text);
        btn.setStyle(Theme.btn(bg, fg));
        return btn;
    }
}

class RegisterPanel extends VBox {
    RegisterPanel(SceneManager sceneManager, LoginManager loginManager, FileManager fileManager) {
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        VBox form = new VBox(10);
        form.setAlignment(Pos.CENTER_LEFT);
        form.setPadding(new Insets(24, 32, 28, 32));
        form.setMaxWidth(400);
        form.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;"
        );

        Label appTitle = new Label("MotoMetrics — Create Account");
        appTitle.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: " + Theme.TEXT + ";");

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: " + Theme.BORDER + ";");

        Label userLabel = new Label("Username");
        userLabel.setStyle(Theme.LABEL_STYLE);
        TextField userField = new TextField();
        userField.setPromptText("Enter username");
        userField.setMaxWidth(Double.MAX_VALUE);
        userField.setStyle(Theme.INPUT_STYLE);

        Label passLabel = new Label("Password");
        passLabel.setStyle(Theme.LABEL_STYLE);
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter password");
        passField.setMaxWidth(Double.MAX_VALUE);
        passField.setStyle(Theme.INPUT_STYLE);

        Label confirmLabel = new Label("Confirm Password");
        confirmLabel.setStyle(Theme.LABEL_STYLE);
        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Confirm password");
        confirmField.setMaxWidth(Double.MAX_VALUE);
        confirmField.setStyle(Theme.INPUT_STYLE);

        Label errorLabel = new Label(" ");
        errorLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12;");

        Button regBtn  = makeBtn("Register", Theme.GREEN,  Theme.GREEN_TEXT);
        Button backBtn = makeBtn("Back",      Theme.CARD,   Theme.MUTED);
        HBox btnRow = new HBox(10, regBtn, backBtn);

        form.getChildren().addAll(appTitle, sep, userLabel, userField, passLabel, passField, confirmLabel, confirmField, errorLabel, btnRow);
        getChildren().add(form);

        regBtn.setOnAction(e -> {
            String username = userField.getText().trim();
            String password = passField.getText().trim();
            String confirm  = confirmField.getText().trim();
            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) { errorLabel.setText("Please fill in all fields."); return; }
            if (!password.equals(confirm)) { errorLabel.setText("Passwords do not match."); return; }
            if (username.length() < 3) { errorLabel.setText("Username must be at least 3 characters."); return; }
            boolean success = loginManager.register(username, password);
            if (success) {
                fileManager.saveUsers(loginManager.getUsers());
                loginManager.login(username, password);
                userField.clear(); passField.clear(); confirmField.clear();
                sceneManager.showDashboard();
            } else {
                errorLabel.setText("Username already taken. Choose another.");
            }
        });
        backBtn.setOnAction(e -> { userField.clear(); passField.clear(); confirmField.clear(); sceneManager.showLogin(); });
    }

    private Button makeBtn(String text, String bg, String fg) {
        Button btn = new Button(text);
        btn.setStyle(Theme.btn(bg, fg));
        return btn;
    }
}

class DashboardPanel extends BorderPane {

    private VehicleManager vehicleManager;
    private LoginManager loginManager;
    private FileManager fileManager;
    private SceneManager sceneManager;
    private Label welcomeLabel;
    private ListView<String> vehicleList;
    private ObservableList<String> listModel;

    DashboardPanel(SceneManager sceneManager, VehicleManager vehicleManager,
                   LoginManager loginManager, FileManager fileManager) {
        this.sceneManager  = sceneManager;
        this.vehicleManager= vehicleManager;
        this.loginManager  = loginManager;
        this.fileManager   = fileManager;

        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        BorderPane topBar = new BorderPane();
        topBar.setStyle(Theme.NAV_STYLE);
        Label appLabel = new Label("MotoMetrics");
        appLabel.setStyle("-fx-text-fill: " + Theme.ACCENT + "; -fx-font-size: 14; -fx-font-weight: bold;");
        welcomeLabel = new Label("Welcome");
        welcomeLabel.setStyle("-fx-text-fill: " + Theme.TEXT + "; -fx-font-size: 14; -fx-font-weight: bold;");
        Button logoutBtn = makeBtn("Logout", Theme.RED, Theme.RED_TEXT);
        topBar.setLeft(appLabel);
        topBar.setCenter(welcomeLabel);
        topBar.setRight(logoutBtn);
        BorderPane.setAlignment(appLabel, Pos.CENTER_LEFT);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        BorderPane.setAlignment(logoutBtn, Pos.CENTER_RIGHT);

        listModel   = FXCollections.observableArrayList();
        vehicleList = new ListView<>(listModel);
        vehicleList.setStyle(
            "-fx-background-color: " + Theme.BASE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-control-inner-background: " + Theme.BASE + ";"
        );

        vehicleList.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null); setText(null);
                    setStyle("-fx-background-color: " + Theme.BASE + ";");
                    return;
                }
                String[] parts = item.split("\\|");
                String name  = parts[0];
                String sub   = parts.length > 1 ? parts[1] : "";
                double score = parts.length > 2 ? Double.parseDouble(parts[2]) : 0;

                Label nameLabel = new Label(name);
                nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13; -fx-text-fill: " + Theme.TEXT + ";");
                Label subLabel = new Label(sub);
                subLabel.setStyle("-fx-text-fill: " + Theme.MUTED + "; -fx-font-size: 11;");
                VBox textBox = new VBox(2, nameLabel, subLabel);

                String scoreBg, scoreFg;
                if (score >= 80)      { scoreBg = Theme.SCORE_GREEN_BG; scoreFg = Theme.SCORE_GREEN_FG; }
                else if (score >= 60) { scoreBg = Theme.SCORE_AMBER_BG; scoreFg = Theme.SCORE_AMBER_FG; }
                else                  { scoreBg = Theme.SCORE_RED_BG;   scoreFg = Theme.SCORE_RED_FG;   }

                Label scoreLabel = new Label(String.format("%.1f/100", score));
                scoreLabel.setStyle(
                    "-fx-font-size: 12; -fx-font-weight: bold;" +
                    "-fx-padding: 4 10 4 10;" +
                    "-fx-background-radius: 5;" +
                    "-fx-background-color: " + scoreBg + ";" +
                    "-fx-text-fill: " + scoreFg + ";"
                );

                BorderPane row = new BorderPane();
                row.setLeft(textBox);
                row.setRight(scoreLabel);
                row.setPadding(new Insets(8, 10, 8, 10));
                BorderPane.setAlignment(scoreLabel, Pos.CENTER_RIGHT);
                BorderPane.setAlignment(textBox, Pos.CENTER_LEFT);
                row.setStyle("-fx-background-color: transparent;");

                setGraphic(row);
                setText(null);
                setStyle("-fx-background-color: " + (isSelected() ? Theme.CARD : Theme.BASE) + "; -fx-padding: 0;");
            }
        });

        VBox listSection = new VBox();
        listSection.setStyle("-fx-background-color: " + Theme.BASE + ";");
        VBox.setVgrow(vehicleList, javafx.scene.layout.Priority.ALWAYS);
        listSection.getChildren().add(vehicleList);
        listSection.setPadding(new Insets(8));
        VBox.setVgrow(listSection, javafx.scene.layout.Priority.ALWAYS);

        VBox sidePanel = new VBox(8);
        sidePanel.setPadding(new Insets(14));
        sidePanel.setPrefWidth(155);
        sidePanel.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 0 0 0 1;"
        );
        Button addBtn    = makeBtn("+ Add Vehicle", Theme.GREEN,  Theme.GREEN_TEXT);
        Button detailBtn = makeBtn("View Details",  Theme.ACCENT, "#ffffff");
        Button tripBtn   = makeBtn("Start Trip →",  Theme.ACCENT, "#ffffff");
        Button removeBtn = makeBtn("Remove",         Theme.RED,    Theme.RED_TEXT);
        for (Button b : new Button[]{addBtn, detailBtn, tripBtn, removeBtn}) b.setMaxWidth(Double.MAX_VALUE);
        sidePanel.getChildren().addAll(addBtn, detailBtn, tripBtn, removeBtn);

        BorderPane center = new BorderPane();
        center.setCenter(listSection);
        center.setRight(sidePanel);
        center.setStyle("-fx-background-color: " + Theme.BASE + ";");

        Label statusBar = new Label("  Select a vehicle to view details or start a trip.");
        statusBar.setMaxWidth(Double.MAX_VALUE);
        statusBar.setStyle(
            "-fx-font-size: 12; -fx-text-fill: " + Theme.MUTED + ";" +
            "-fx-background-color: " + Theme.NAV + ";" +
            "-fx-padding: 6 12 6 12;" +
            "-fx-border-color: " + Theme.BORDER + "; -fx-border-width: 1 0 0 0;"
        );

        setTop(topBar);
        setCenter(center);
        setBottom(statusBar);
        refresh();

        logoutBtn.setOnAction(e -> { loginManager.logout(); sceneManager.showLogin(); });
        addBtn.setOnAction(e -> sceneManager.showAddVehicle());
        detailBtn.setOnAction(e -> {
            int idx = vehicleList.getSelectionModel().getSelectedIndex();
            if (idx < 0) { showAlert("No Selection", "Please select a vehicle first."); return; }
            sceneManager.showVehicleDetails(vehicleManager.getVehicles().get(idx));
        });
        tripBtn.setOnAction(e -> {
            if (vehicleManager.getVehicles().isEmpty()) { showAlert("No Vehicles", "Add a vehicle first."); return; }
            sceneManager.showTrip();
        });
        removeBtn.setOnAction(e -> {
            int idx = vehicleList.getSelectionModel().getSelectedIndex();
            if (idx < 0) { showAlert("No Selection", "Please select a vehicle to remove."); return; }
            Vehicle v = vehicleManager.getVehicles().get(idx);
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Remove");
            confirm.setContentText("Remove " + v.getMake() + " " + v.getModel() + "?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    vehicleManager.removeVehicle(idx);
                    fileManager.saveVehicles(vehicleManager.getVehicles());
                    refresh();
                }
            });
        });
    }

    public void refresh() {
        if (loginManager.getCurrentUser() != null)
            welcomeLabel.setText("Welcome, " + loginManager.getCurrentUser().getUsername());
        listModel.clear();
        for (Vehicle v : vehicleManager.getVehicles()) {
            String type  = v instanceof Car ? "Car" : v instanceof Motorcycle ? "Motorcycle" : "Truck";
            String score = String.format("%.1f", v.CalculateScore());
            listModel.add(v.getMake() + " " + v.getModel() + "|" + type + " · " + v.getEngine().getCapacity() + "cc|" + score);
        }
    }

    private Button makeBtn(String text, String bg, String fg) {
        Button btn = new Button(text);
        btn.setStyle(Theme.btn(bg, fg));
        return btn;
    }
    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(title); a.setContentText(msg); a.showAndWait();
    }
}

class AddVehiclePanel extends BorderPane {

    private VehicleManager vehicleManager;
    private FileManager fileManager;
    private SceneManager sceneManager;
    private TextField makeField, modelField, engineCapField, engineHealthField;
    private TextField oilField, airField, tyresField, suspField, brakesField;
    private ComboBox<String> acCombo;
    private TextField chainField, loadField;
    private VBox acRow, chainRow, loadRow;
    private String selectedType = "Car";
    private Label errorLabel;

    AddVehiclePanel(SceneManager sceneManager, VehicleManager vehicleManager, FileManager fileManager) {
        this.sceneManager  = sceneManager;
        this.vehicleManager= vehicleManager;
        this.fileManager   = fileManager;

        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        BorderPane topBar = new BorderPane();
        topBar.setStyle(Theme.NAV_STYLE);
        Label title = new Label("Add New Vehicle");
        title.setStyle("-fx-text-fill: " + Theme.TEXT + "; -fx-font-size: 15; -fx-font-weight: bold;");
        topBar.setLeft(title);

        ToggleGroup typeGroup = new ToggleGroup();
        RadioButton carBtn   = new RadioButton("Car");
        RadioButton bikeBtn  = new RadioButton("Motorcycle");
        RadioButton truckBtn = new RadioButton("Truck");
        for (RadioButton rb : new RadioButton[]{carBtn, bikeBtn, truckBtn}) {
            rb.setToggleGroup(typeGroup);
            rb.setStyle("-fx-text-fill: " + Theme.TEXT + "; -fx-font-size: 13;");
        }
        carBtn.setSelected(true);

        Label typeLabel = new Label("Vehicle type:");
        typeLabel.setStyle(Theme.LABEL_STYLE);
        HBox typeRow = new HBox(16, typeLabel, carBtn, bikeBtn, truckBtn);
        typeRow.setAlignment(Pos.CENTER_LEFT);
        typeRow.setPadding(new Insets(10, 16, 10, 16));
        typeRow.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 0 0 1 0;"
        );

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(10);
        form.setPadding(new Insets(14, 16, 10, 16));
        form.setStyle("-fx-background-color: " + Theme.BASE + ";");

        makeField         = new TextField();
        modelField        = new TextField();
        engineCapField    = new TextField("1600");
        engineHealthField = new TextField("100");
        oilField          = new TextField("10.0");
        airField          = new TextField("10.0");
        tyresField        = new TextField("10.0");
        suspField         = new TextField("10.0");
        brakesField       = new TextField("10.0");

        String[] labelTexts = {
            "Make:", "Model:", "Engine Capacity (cc):", "Engine Health (0–100):",
            "Oil Level (0–10):", "Air Pressure (0–10):", "Tyre Health (0–10):",
            "Suspension (0–10):", "Brake Health (0–10):"
        };
        TextField[] fields = {
            makeField, modelField, engineCapField, engineHealthField,
            oilField, airField, tyresField, suspField, brakesField
        };

        for (int i = 0; i < labelTexts.length; i++) {
            Label lbl = new Label(labelTexts[i]);
            lbl.setStyle(Theme.LABEL_STYLE);
            form.add(lbl, 0, i);
            fields[i].setMaxWidth(Double.MAX_VALUE);
            fields[i].setStyle(Theme.INPUT_STYLE);
            form.add(fields[i], 1, i);
        }
        GridPane.setHgrow(makeField, javafx.scene.layout.Priority.ALWAYS);

        acCombo = new ComboBox<>(FXCollections.observableArrayList("Yes", "No"));
        acCombo.setValue("Yes");

        acCombo.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setTextFill(Color.BLACK);
                }
            }
        });

        acCombo.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setTextFill(Color.BLACK);
                    setStyle("-fx-background-color: " + Theme.NAV + ";");
                }
            }
        });

        acRow = makeExtraRow("Has AC:", acCombo);

        chainField = new TextField("10.0");
        chainField.setStyle(Theme.INPUT_STYLE);
        chainRow = makeExtraRow("Chain-Sprocket Health (0–10):", chainField);
        chainRow.setVisible(false); chainRow.setManaged(false);

        loadField = new TextField("20000");
        loadField.setStyle(Theme.INPUT_STYLE);
        loadRow = makeExtraRow("Load Capacity (kg):", loadField);
        loadRow.setVisible(false); loadRow.setManaged(false);

        errorLabel = new Label(" ");
        errorLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12; -fx-padding: 0 16 0 16;");

        Button saveBtn   = makeBtn("Save Vehicle", Theme.GREEN,  Theme.GREEN_TEXT);
        Button cancelBtn = makeBtn("Cancel",        Theme.CARD,   Theme.MUTED);
        HBox btnRow = new HBox(10, saveBtn, cancelBtn);
        btnRow.setPadding(new Insets(8, 16, 10, 16));
        btnRow.setStyle("-fx-background-color: " + Theme.BASE + ";");

        VBox inner = new VBox(typeRow, form, acRow, chainRow, loadRow, errorLabel, btnRow);
        inner.setStyle("-fx-background-color: " + Theme.BASE + ";");
        ScrollPane scroll = new ScrollPane(inner);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: " + Theme.BASE + "; -fx-background: " + Theme.BASE + ";");

        setTop(topBar);
        setCenter(scroll);

        carBtn.setOnAction(e -> {
            selectedType = "Car";
            acRow.setVisible(true);   acRow.setManaged(true);
            chainRow.setVisible(false); chainRow.setManaged(false);
            loadRow.setVisible(false);  loadRow.setManaged(false);
        });
        bikeBtn.setOnAction(e -> {
            selectedType = "Motorcycle";
            acRow.setVisible(false);    acRow.setManaged(false);
            chainRow.setVisible(true);  chainRow.setManaged(true);
            loadRow.setVisible(false);  loadRow.setManaged(false);
        });
        truckBtn.setOnAction(e -> {
            selectedType = "Truck";
            acRow.setVisible(false);    acRow.setManaged(false);
            chainRow.setVisible(false); chainRow.setManaged(false);
            loadRow.setVisible(true);   loadRow.setManaged(true);
        });

        saveBtn.setOnAction(e -> {
            errorLabel.setText(" ");
            String make  = makeField.getText().trim();
            String model = modelField.getText().trim();
            if (make.isEmpty() || model.isEmpty()) { errorLabel.setText("Make and Model are required."); return; }
            try {
                int    engCap    = Integer.parseInt(engineCapField.getText().trim());
                int    engHealth = Integer.parseInt(engineHealthField.getText().trim());
                double oil       = Double.parseDouble(oilField.getText().trim());
                double air       = Double.parseDouble(airField.getText().trim());
                double tyres     = Double.parseDouble(tyresField.getText().trim());
                double susp      = Double.parseDouble(suspField.getText().trim());
                double brakes    = Double.parseDouble(brakesField.getText().trim());

                if (engHealth < 0 || engHealth > 100) { errorLabel.setText("Engine health must be 0–100."); return; }
                if (oil<0||oil>10||air<0||air>10||tyres<0||tyres>10||susp<0||susp>10||brakes<0||brakes>10) {
                    errorLabel.setText("Health values must be 0–10."); return;
                }

                Vehicle v;
                if (selectedType.equals("Car")) {
                    v = new Car(make, model, brakes, oil, air, tyres, susp, acCombo.getValue().equals("Yes"), engCap, engHealth);
                } else if (selectedType.equals("Motorcycle")) {
                    double chain = Double.parseDouble(chainField.getText().trim());
                    if (chain<0||chain>10) { errorLabel.setText("Chain-Sprocket must be 0–10."); return; }
                    v = new Motorcycle(make, model, brakes, oil, air, tyres, susp, chain, engCap, engHealth);
                } else {
                    v = new Truck(make, model, brakes, oil, air, tyres, susp, engCap, engHealth, Double.parseDouble(loadField.getText().trim()));
                }
                vehicleManager.addVehicle(v);
                fileManager.saveVehicles(vehicleManager.getVehicles());
                clearForm();
                sceneManager.showDashboard();
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter valid numbers in all fields.");
            }
        });
        cancelBtn.setOnAction(e -> { clearForm(); sceneManager.showDashboard(); });
    }

    private VBox makeExtraRow(String labelText, javafx.scene.Node field) {
        Label lbl = new Label(labelText);
        lbl.setStyle(Theme.LABEL_STYLE);
        HBox row = new HBox(10, lbl, field);
        row.setPadding(new Insets(4, 16, 0, 16));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-background-color: " + Theme.BASE + ";");
        return new VBox(row);
    }

    private void clearForm() {
        makeField.clear(); modelField.clear();
        engineCapField.setText("1600"); engineHealthField.setText("100");
        oilField.setText("10.0"); airField.setText("10.0");
        tyresField.setText("10.0"); suspField.setText("10.0"); brakesField.setText("10.0");
        chainField.setText("10.0"); loadField.setText("20000");
        acCombo.setValue("Yes");
    }

    private Button makeBtn(String text, String bg, String fg) {
        Button btn = new Button(text);
        btn.setStyle(Theme.btn(bg, fg));
        return btn;
    }
}

class VehicleDetailsPanel extends BorderPane {

    private FileManager fileManager;
    private VehicleManager vehicleManager;
    private SceneManager sceneManager;
    private Vehicle currentVehicle;
    private Label titleLabel, scoreLabel;
    private ProgressBar engineBar, oilBar, airBar, tyresBar, suspBar, brakesBar, chainBar;
    private HBox chainRow;
    private TextArea reportArea;

    VehicleDetailsPanel(SceneManager sceneManager, FileManager fileManager, VehicleManager vehicleManager) {
        this.sceneManager  = sceneManager;
        this.fileManager   = fileManager;
        this.vehicleManager= vehicleManager;

        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        BorderPane topBar = new BorderPane();
        topBar.setStyle(Theme.NAV_STYLE);
        titleLabel = new Label("Vehicle Health Report");
        titleLabel.setStyle("-fx-text-fill: " + Theme.TEXT + "; -fx-font-size: 15; -fx-font-weight: bold;");
        topBar.setLeft(titleLabel);

        scoreLabel = new Label("--/100");
        scoreLabel.setStyle("-fx-font-size: 32; -fx-font-weight: bold; -fx-text-fill: " + Theme.TEXT + ";");
        scoreLabel.setMaxWidth(Double.MAX_VALUE);
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setPadding(new Insets(14, 0, 8, 0));

        engineBar = makeBar(); oilBar    = makeBar();
        airBar    = makeBar(); tyresBar  = makeBar();
        suspBar   = makeBar(); brakesBar = makeBar();
        chainBar  = makeBar();

        VBox barsPanel = new VBox(10);
        barsPanel.setPadding(new Insets(8, 16, 8, 16));
        barsPanel.getChildren().addAll(
            makeBarRow("Engine Health", engineBar),
            makeBarRow("Oil Level",     oilBar),
            makeBarRow("Air Pressure",  airBar),
            makeBarRow("Tyre Health",   tyresBar),
            makeBarRow("Suspension",    suspBar),
            makeBarRow("Brake Health",  brakesBar)
        );
        chainRow = makeBarRow("Chain-Sprocket", chainBar);
        barsPanel.getChildren().add(chainRow);
        barsPanel.setStyle("-fx-background-color: " + Theme.BASE + ";");

        VBox leftPanel = new VBox(scoreLabel, barsPanel);
        leftPanel.setPrefWidth(370);
        leftPanel.setStyle("-fx-background-color: " + Theme.BASE + ";");

        reportArea = new TextArea();
        reportArea.setEditable(false);
        reportArea.setFont(Font.font("Monospaced", 13));
        reportArea.setWrapText(true);
        reportArea.setStyle(
            "-fx-control-inner-background: " + Theme.CARD + ";" +
            "-fx-background-color: " + Theme.CARD + ";" +
            "-fx-text-fill: " + Theme.TEXT + ";" +
            "-fx-font-size: 13;"
        );

        VBox rightPanel = new VBox(6);
        rightPanel.setPadding(new Insets(12));
        rightPanel.setStyle("-fx-background-color: " + Theme.BASE + ";");
        Label reportTitle = new Label("Diagnosable Report");
        reportTitle.setStyle(Theme.sectionLabel());
        VBox.setVgrow(reportArea, javafx.scene.layout.Priority.ALWAYS);
        rightPanel.getChildren().addAll(reportTitle, reportArea);

        SplitPane split = new SplitPane(leftPanel, rightPanel);
        split.setDividerPositions(0.5);
        split.setStyle("-fx-background-color: " + Theme.BASE + ";");

        Button serviceBtn = makeBtn("Service Engine",     Theme.GREEN,  Theme.GREEN_TEXT);
        Button saveBtn    = makeBtn("Save Report (.txt)", Theme.ACCENT, "#ffffff");
        Button resetBtn   = makeBtn("Reset All Stats",    Theme.RED,    Theme.RED_TEXT);
        Button backBtn    = makeBtn("← Back",             Theme.CARD,   Theme.MUTED);

        HBox btnRow = new HBox(10, serviceBtn, saveBtn, resetBtn, backBtn);
        btnRow.setPadding(new Insets(10, 12, 10, 12));
        btnRow.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 1 0 0 0;"
        );

        VBox center = new VBox(split);
        VBox.setVgrow(split, javafx.scene.layout.Priority.ALWAYS);
        setTop(topBar); setCenter(center); setBottom(btnRow);

        serviceBtn.setOnAction(e -> {
            if (currentVehicle != null) { currentVehicle.serviceEngine(); loadVehicle(currentVehicle); showInfo("Service Complete", "Engine restored to 100."); }
        });
        saveBtn.setOnAction(e -> {
            if (currentVehicle == null) return;
            javafx.stage.FileChooser chooser = new javafx.stage.FileChooser();
            chooser.setInitialFileName(currentVehicle.getMake() + "_" + currentVehicle.getModel() + "_report.txt");
            java.io.File file = chooser.showSaveDialog(getScene().getWindow());
            if (file != null) { fileManager.saveReport(reportArea.getText(), file.getAbsolutePath()); showInfo("Saved", "Report saved to:\n" + file.getAbsolutePath()); }
        });
        resetBtn.setOnAction(e -> {
            if (currentVehicle == null) return;
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Reset");
            confirm.setContentText("Reset ALL stats for " + currentVehicle.getMake() + " " + currentVehicle.getModel() + "?");
            confirm.showAndWait().ifPresent(r -> {
                if (r == javafx.scene.control.ButtonType.OK) {
                    currentVehicle.resetStats();
                    fileManager.saveVehicles(vehicleManager.getVehicles());
                    loadVehicle(currentVehicle);
                    showInfo("Reset Complete", "All stats reset to 10.0.");
                }
            });
        });
        backBtn.setOnAction(e -> sceneManager.showDashboard());
    }

    public void loadVehicle(Vehicle v) {
        currentVehicle = v;
        titleLabel.setText(v.getMake() + " " + v.getModel() + " — Health Report");
        double score = v.CalculateScore();
        scoreLabel.setText(String.format("%.1f / 100", score));

        if (score >= 80)
            scoreLabel.setStyle("-fx-font-size: 32; -fx-font-weight: bold; -fx-text-fill: " + Theme.SCORE_GREEN_FG + ";");
        else if (score >= 60)
            scoreLabel.setStyle("-fx-font-size: 32; -fx-font-weight: bold; -fx-text-fill: " + Theme.SCORE_AMBER_FG + ";");
        else
            scoreLabel.setStyle("-fx-font-size: 32; -fx-font-weight: bold; -fx-text-fill: " + Theme.SCORE_RED_FG + ";");

        setBar(engineBar, v.getEngine().getHealth(), 100);
        setBar(oilBar,    v.getOilLevel(),    10);
        setBar(airBar,    v.getAirPressure(), 10);
        setBar(tyresBar,  v.getTyres(),       10);
        setBar(suspBar,   v.getSuspension(),  10);
        setBar(brakesBar, v.getBrakes(),      10);

        if (v instanceof Motorcycle) {
            setBar(chainBar, ((Motorcycle) v).getChainSprocketHealth(), 10);
            chainRow.setVisible(true); chainRow.setManaged(true);
        } else {
            chainRow.setVisible(false); chainRow.setManaged(false);
        }

        reportArea.setText(v instanceof Diagnosable ? ((Diagnosable) v).generateReport() : "No report available.");
        reportArea.setScrollTop(0);
    }

    private void setBar(ProgressBar bar, double value, double max) {
        double pct = Math.max(0, Math.min(1.0, value / max));
        bar.setProgress(pct);
        if (pct >= 0.7)      bar.setStyle("-fx-accent: " + Theme.SCORE_GREEN_FG + ";");
        else if (pct >= 0.4) bar.setStyle("-fx-accent: " + Theme.SCORE_AMBER_FG + ";");
        else                 bar.setStyle("-fx-accent: " + Theme.SCORE_RED_FG   + ";");
    }

    private ProgressBar makeBar() {
        ProgressBar bar = new ProgressBar(0);
        bar.setPrefWidth(200);
        bar.setPrefHeight(20);
        return bar;
    }

    private HBox makeBarRow(String labelText, ProgressBar bar) {
        Label lbl = new Label(labelText);
        lbl.setPrefWidth(130);
        lbl.setStyle("-fx-font-size: 13; -fx-text-fill: " + Theme.TEXT + ";");
        HBox row = new HBox(10, lbl, bar);
        row.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(bar, javafx.scene.layout.Priority.ALWAYS);
        return row;
    }

    private Button makeBtn(String text, String bg, String fg) {
        Button btn = new Button(text);
        btn.setStyle(Theme.btn(bg, fg));
        return btn;
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title); a.setContentText(msg); a.showAndWait();
    }
}

class TripPanel extends BorderPane {

    private VehicleManager vehicleManager;
    private AdviceManager adviceManager;
    private SceneManager sceneManager;
    private ComboBox<String> vehicleCombo;
    private TextField distanceField, loadField;
    private ComboBox<String> terrainCombo;
    private CheckBox hardBrakingBox, overspeedBox, aggressiveBox, longHoursBox;
    private Label errorLabel;

    TripPanel(SceneManager sceneManager, VehicleManager vehicleManager, AdviceManager adviceManager) {
        this.sceneManager   = sceneManager;
        this.vehicleManager = vehicleManager;
        this.adviceManager  = adviceManager;

        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        BorderPane topBar = new BorderPane();
        topBar.setStyle(Theme.NAV_STYLE);
        Label title = new Label("Plan a Trip");
        title.setStyle("-fx-text-fill: " + Theme.TEXT + "; -fx-font-size: 15; -fx-font-weight: bold;");
        topBar.setLeft(title);

        GridPane tripDetails = new GridPane();
        tripDetails.setHgap(12); tripDetails.setVgap(12);
        tripDetails.setPadding(new Insets(12));
        tripDetails.setStyle("-fx-background-color: " + Theme.SURFACE + ";");

        vehicleCombo  = new ComboBox<>();
        distanceField = new TextField("500");
        loadField     = new TextField("0");
        terrainCombo  = new ComboBox<>(FXCollections.observableArrayList("City","Highway","Hilly","Off-road","Mixed"));
        terrainCombo.setValue("City");

        vehicleCombo.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setTextFill(Color.WHITE);
                }
            }
        });

        vehicleCombo.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setTextFill(Color.WHITE);
                    setStyle("-fx-background-color: " + Theme.NAV + ";");
                }
            }
        });

        terrainCombo.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setTextFill(Color.WHITE);
                }
            }
        });

        terrainCombo.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setTextFill(Color.WHITE);
                    setStyle("-fx-background-color: " + Theme.NAV + ";");
                }
            }
        });

        for (javafx.scene.control.Control c : new javafx.scene.control.Control[]{vehicleCombo, distanceField, loadField, terrainCombo}) {
            c.setMaxWidth(Double.MAX_VALUE);
            c.setStyle(Theme.INPUT_STYLE);
        }

        String[] tripLabels = {"Select Vehicle:", "Distance (km):", "Load (kg):", "Terrain:"};
        javafx.scene.Node[] tripControls = {vehicleCombo, distanceField, loadField, terrainCombo};
        for (int i = 0; i < tripLabels.length; i++) {
            Label lbl = new Label(tripLabels[i]);
            lbl.setStyle(Theme.LABEL_STYLE);
            tripDetails.add(lbl, 0, i);
            tripDetails.add(tripControls[i], 1, i);
            GridPane.setHgrow(tripControls[i], javafx.scene.layout.Priority.ALWAYS);
        }

        Label tripTitle = new Label("TRIP DETAILS");
        tripTitle.setStyle(Theme.sectionLabel());
        VBox tripBox = new VBox(8, tripTitle, tripDetails);
        tripBox.setPadding(new Insets(12));
        tripBox.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 6;" +
            "-fx-background-radius: 6;"
        );

        hardBrakingBox = new CheckBox("Hard Braking");
        overspeedBox   = new CheckBox("Overspeeding");
        aggressiveBox  = new CheckBox("Aggressive Driving");
        longHoursBox   = new CheckBox("Long Driving Hours (4+ hrs)");

        for (CheckBox cb : new CheckBox[]{hardBrakingBox, overspeedBox, aggressiveBox, longHoursBox}) {
            cb.setStyle("-fx-font-size: 13; -fx-text-fill: " + Theme.TEXT + ";");
        }

        Label hint = new Label("Check all that apply to your driving:");
        hint.setStyle("-fx-font-size: 12; -fx-text-fill: " + Theme.MUTED + ";");

        Label behaviorTitle = new Label("DRIVER BEHAVIOR");
        behaviorTitle.setStyle(Theme.sectionLabel());

        VBox behaviorBox = new VBox(10, hint, hardBrakingBox, overspeedBox, aggressiveBox, longHoursBox);
        behaviorBox.setPadding(new Insets(12));
        behaviorBox.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 6;" +
            "-fx-background-radius: 6;"
        );
        VBox behaviorSection = new VBox(8, behaviorTitle, behaviorBox);
        behaviorSection.setPadding(new Insets(12));
        behaviorSection.setStyle("-fx-background-color: " + Theme.BASE + ";");

        HBox centerRow = new HBox(14, tripBox, behaviorSection);
        centerRow.setPadding(new Insets(14));
        centerRow.setStyle("-fx-background-color: " + Theme.BASE + ";");
        HBox.setHgrow(tripBox, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(behaviorSection, javafx.scene.layout.Priority.ALWAYS);

        errorLabel = new Label(" ");
        errorLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12;");

        Button adviceBtn = makeBtn("Get Advice →", Theme.GREEN,  Theme.GREEN_TEXT);
        Button cancelBtn = makeBtn("Cancel",         Theme.CARD,   Theme.MUTED);
        HBox btnRow = new HBox(10, adviceBtn, cancelBtn, errorLabel);
        btnRow.setPadding(new Insets(10, 14, 10, 14));
        btnRow.setAlignment(Pos.CENTER_LEFT);
        btnRow.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 1 0 0 0;"
        );

        setTop(topBar); setCenter(centerRow); setBottom(btnRow);
        refreshVehicleList();

        adviceBtn.setOnAction(e -> {
            errorLabel.setText(" ");
            if (vehicleCombo.getSelectionModel().getSelectedIndex() < 0 || vehicleManager.getVehicles().isEmpty()) {
                errorLabel.setText("Please select a vehicle."); return;
            }
            try {
                double distance = Double.parseDouble(distanceField.getText().trim());
                double load     = Double.parseDouble(loadField.getText().trim());
                if (distance <= 0) { errorLabel.setText("Distance must be greater than 0."); return; }
                Vehicle selected = vehicleManager.getVehicles().get(vehicleCombo.getSelectionModel().getSelectedIndex());
                DriverBehavior db = new DriverBehavior(hardBrakingBox.isSelected(), overspeedBox.isSelected(), aggressiveBox.isSelected(), longHoursBox.isSelected());
                sceneManager.showAdvice(new Trip(distance, terrainCombo.getValue(), load, selected, db));
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter valid numbers for distance and load.");
            }
        });
        cancelBtn.setOnAction(e -> sceneManager.showDashboard());
    }

    public void refreshVehicleList() {
        vehicleCombo.getItems().clear();
        for (Vehicle v : vehicleManager.getVehicles()) {
            String type = v instanceof Car ? "Car" : v instanceof Motorcycle ? "Bike" : "Truck";
            vehicleCombo.getItems().add(v.getMake() + " " + v.getModel() + " (" + type + ")");
        }
        hardBrakingBox.setSelected(false); overspeedBox.setSelected(false);
        aggressiveBox.setSelected(false);  longHoursBox.setSelected(false);
    }

    private Button makeBtn(String text, String bg, String fg) {
        Button btn = new Button(text);
        btn.setStyle(Theme.btn(bg, fg));
        return btn;
    }
}

class AdvicePanel extends BorderPane {

    private FileManager fileManager;
    private VehicleManager vehicleManager;
    private SceneManager sceneManager;
    private AdviceManager adviceManager;
    private WearManager wearManager = new WearManager();
    private Trip currentTrip;
    private String fullReport = "";
    private Label scoreLabel, vehicleNameLabel, tripInfoLabel;
    private TextArea vehicleAdviceArea, behaviorArea;
    private Button completeBtn;

    AdvicePanel(SceneManager sceneManager, FileManager fileManager, VehicleManager vehicleManager) {
        this.sceneManager  = sceneManager;
        this.fileManager   = fileManager;
        this.vehicleManager= vehicleManager;

        setStyle("-fx-background-color: " + Theme.BASE + ";");
        setPrefSize(780, 540);

        BorderPane topBar = new BorderPane();
        topBar.setStyle(Theme.NAV_STYLE);
        Label title = new Label("Trip Advice");
        title.setStyle("-fx-text-fill: " + Theme.TEXT + "; -fx-font-size: 15; -fx-font-weight: bold;");
        topBar.setLeft(title);

        scoreLabel = new Label("--/100");
        scoreLabel.setStyle(
            "-fx-font-size: 34; -fx-font-weight: bold; -fx-text-fill: " + Theme.TEXT + ";" +
            "-fx-border-color: " + Theme.BORDER + "; -fx-border-width: 1; -fx-border-radius: 8;" +
            "-fx-background-color: " + Theme.CARD + "; -fx-background-radius: 8;" +
            "-fx-alignment: center;"
        );
        scoreLabel.setPrefSize(120, 64);
        scoreLabel.setAlignment(Pos.CENTER);

        vehicleNameLabel = new Label("--");
        vehicleNameLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: " + Theme.TEXT + ";");
        tripInfoLabel = new Label("--");
        tripInfoLabel.setStyle("-fx-font-size: 12; -fx-text-fill: " + Theme.MUTED + ";");

        VBox infoRight = new VBox(6, vehicleNameLabel, tripInfoLabel);
        infoRight.setAlignment(Pos.CENTER_LEFT);

        HBox scoreHeader = new HBox(16, scoreLabel, infoRight);
        scoreHeader.setAlignment(Pos.CENTER_LEFT);
        scoreHeader.setPadding(new Insets(12, 16, 12, 16));
        // FIX 6: header background is dark blue not white
        scoreHeader.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 0 0 1 0;"
        );

        vehicleAdviceArea = makeTextArea();
        behaviorArea      = makeTextArea();

        Label vTitle = new Label("VEHICLE ADVICE");
        vTitle.setStyle(Theme.sectionLabel());
        VBox vehicleSection = new VBox(6, vTitle, vehicleAdviceArea);
        vehicleSection.setPadding(new Insets(10));
        vehicleSection.setStyle("-fx-background-color: " + Theme.BASE + ";");
        VBox.setVgrow(vehicleAdviceArea, javafx.scene.layout.Priority.ALWAYS);

        Label bTitle = new Label("DRIVER BEHAVIOR WARNINGS");
        bTitle.setStyle(Theme.sectionLabel());
        VBox behaviorSection = new VBox(6, bTitle, behaviorArea);
        behaviorSection.setPadding(new Insets(10));
        behaviorSection.setStyle("-fx-background-color: " + Theme.BASE + ";");
        VBox.setVgrow(behaviorArea, javafx.scene.layout.Priority.ALWAYS);

        SplitPane split = new SplitPane(vehicleSection, behaviorSection);
        split.setOrientation(javafx.geometry.Orientation.VERTICAL);
        split.setDividerPositions(0.5);
        split.setStyle("-fx-background-color: " + Theme.BASE + ";");

        // BUTTONS
        Button saveBtn  = makeBtn("Save Report (.txt)", Theme.ACCENT,  "#ffffff");
        Button tripBtn  = makeBtn("New Trip",            Theme.GREEN,   Theme.GREEN_TEXT);
        Button dashBtn  = makeBtn("Dashboard",           Theme.CARD,    Theme.MUTED);
        completeBtn     = makeBtn("Complete Trip ✓",     Theme.PURPLE,  Theme.PURPLE_TEXT);

        HBox btnRow = new HBox(10, saveBtn, tripBtn, dashBtn, completeBtn);
        btnRow.setPadding(new Insets(10, 12, 10, 12));
        btnRow.setStyle(
            "-fx-background-color: " + Theme.SURFACE + ";" +
            "-fx-border-color: " + Theme.BORDER + ";" +
            "-fx-border-width: 1 0 0 0;"
        );

        VBox centerArea = new VBox(scoreHeader, split);
        VBox.setVgrow(split, javafx.scene.layout.Priority.ALWAYS);
        centerArea.setStyle("-fx-background-color: " + Theme.BASE + ";");

        setTop(topBar); setCenter(centerArea); setBottom(btnRow);

        saveBtn.setOnAction(e -> {
            if (fullReport.isEmpty()) return;
            javafx.stage.FileChooser chooser = new javafx.stage.FileChooser();
            chooser.setInitialFileName("trip_report.txt");
            java.io.File file = chooser.showSaveDialog(getScene().getWindow());
            if (file != null) { fileManager.saveReport(fullReport, file.getAbsolutePath()); showInfo("Saved", "Report saved to:\n" + file.getAbsolutePath()); }
        });
        tripBtn.setOnAction(e -> sceneManager.showTrip());
        dashBtn.setOnAction(e -> sceneManager.showDashboard());
        completeBtn.setOnAction(e -> {
            if (currentTrip == null) return;
            Vehicle v       = currentTrip.getVehicle();
            double oldTyre  = v.getTyres(), oldSusp = v.getSuspension();
            double oldBrake = v.getBrakes(), oldOil = v.getOilLevel();
            int    oldEng   = v.getEngine().getHealth();
            wearManager.applyWear(currentTrip);
            fileManager.saveVehicles(vehicleManager.getVehicles());
            showInfo("Trip Complete — Wear Applied", wearManager.getWearSummary(v, oldTyre, oldSusp, oldBrake, oldOil, oldEng));
            completeBtn.setDisable(true);
            sceneManager.showDashboard();
        });
    }

    public void loadAdvice(Trip trip, AdviceManager am) {
        this.currentTrip  = trip;
        this.adviceManager= am;
        double score = trip.getVehicle().CalculateScore();

        scoreLabel.setText(String.format("%.1f/100", score));
        String scoreColor = score >= 80 ? Theme.SCORE_GREEN_FG : score >= 60 ? Theme.SCORE_AMBER_FG : Theme.SCORE_RED_FG;
        scoreLabel.setStyle(
            "-fx-font-size: 34; -fx-font-weight: bold;" +
            "-fx-text-fill: " + scoreColor + ";" +
            "-fx-border-color: " + Theme.BORDER + "; -fx-border-width: 1; -fx-border-radius: 8;" +
            "-fx-background-color: " + Theme.CARD + "; -fx-background-radius: 8;" +
            "-fx-alignment: center;"
        );

        vehicleNameLabel.setText(trip.getVehicle().getMake() + " " + trip.getVehicle().getModel());
        tripInfoLabel.setText(trip.getDistance() + " km · " + trip.getTerrain() + " terrain · Load: " + trip.getLoad() + " kg");
        vehicleAdviceArea.setText(am.getScoreAdvice(score));
        behaviorArea.setText(am.getBehaviorWarnings(trip.getDriverBehavior()));
        fullReport = am.getSummaryReport(trip);
        vehicleAdviceArea.setScrollTop(0);
        behaviorArea.setScrollTop(0);
        completeBtn.setDisable(false);
    }

    private TextArea makeTextArea() {
        TextArea area = new TextArea();
        area.setEditable(false);
        area.setFont(Font.font("SansSerif", 13));
        area.setWrapText(true);
        area.setStyle(
            "-fx-control-inner-background: " + Theme.CARD + ";" +
            "-fx-background-color: " + Theme.CARD + ";" +
            "-fx-text-fill: " + Theme.TEXT + ";" +
            "-fx-font-size: 13;"
        );
        return area;
    }

    private Button makeBtn(String text, String bg, String fg) {
        Button btn = new Button(text);
        btn.setStyle(Theme.btn(bg, fg));
        return btn;
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title); a.setContentText(msg); a.showAndWait();
    }
}

public class OOP_Final_Project extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        VehicleManager vehicleManager = new VehicleManager();
        LoginManager   loginManager   = new LoginManager();
        FileManager    fileManager    = new FileManager();
        AdviceManager  adviceManager  = new AdviceManager();

        loginManager.setUsers(fileManager.loadUsers());
        for (Vehicle v : fileManager.loadVehicles()) vehicleManager.addVehicle(v);

        stage.setTitle("MotoMetrics — Vehicle Wear & Tear Simulation");
        stage.setResizable(false);

        SceneManager sceneManager = new SceneManager(stage, loginManager, vehicleManager, fileManager, adviceManager);
        sceneManager.showSplash();
        stage.show();
    }
}