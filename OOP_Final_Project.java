import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

class LoginPanel extends JPanel {

    LoginPanel(CardLayout cardLayout, JPanel mainPanel, LoginManager loginManager, FileManager fileManager) {

        setLayout(new GridBagLayout());

        // ---- Form container ----
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(60, 60, 140), 1),
                        "MotoMetrics — Login"), BorderFactory.createEmptyBorder(10, 20, 15, 20)));

        form.setPreferredSize(new Dimension(400, 250));
        form.setBackground(Color.WHITE);

        // ---- Title ----
        JLabel title = new JLabel("Vehicle Wear & Tear Simulation");
        title.setFont(new Font("SansSerif", Font.PLAIN, 11));
        title.setForeground(Color.GRAY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ---- Fields ----
        JTextField userField = new JTextField();
        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // ---- Error label ----
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ---- Buttons ----
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnRow.setBackground(Color.WHITE);
        JButton loginBtn = makeButton("Login", new Color(60, 60, 140), Color.WHITE);
        JButton regBtn   = makeButton("Register", Color.LIGHT_GRAY, Color.DARK_GRAY);
        btnRow.add(loginBtn);
        btnRow.add(regBtn);

        // ---- Assemble form ----
        form.add(title);
        form.add(Box.createVerticalStrut(12));
        JLabel userLabel = new JLabel("Username");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(userLabel);
        form.add(Box.createVerticalStrut(4));
        form.add(userField);
        form.add(Box.createVerticalStrut(8));
        JLabel passLabel = new JLabel("Password");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(passLabel);
        form.add(Box.createVerticalStrut(4));
        form.add(passField);
        form.add(Box.createVerticalStrut(6));
        form.add(errorLabel);
        form.add(Box.createVerticalStrut(6));
        form.add(btnRow);

        add(form);

        // ---- Actions ----
        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }

            if (loginManager.login(username, password)) {
                errorLabel.setText(" ");
                userField.setText("");
                passField.setText("");
                // Refresh dashboard before switching
                DashboardPanel dash = (DashboardPanel) mainPanel.getComponent(2);
                dash.refresh();
                cardLayout.show(mainPanel, "DASHBOARD");
            } else {
                errorLabel.setText("Invalid username or password.");
            }
        });

        regBtn.addActionListener(e -> {
            userField.setText("");
            passField.setText("");
            errorLabel.setText(" ");
            cardLayout.show(mainPanel, "REGISTER");
        });

        // Allow Enter key to trigger login
        passField.addActionListener(e -> loginBtn.doClick());
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(100, 30));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

class RegisterPanel extends JPanel {

    RegisterPanel(CardLayout cardLayout, JPanel mainPanel, LoginManager loginManager, FileManager fileManager) {

        setLayout(new GridBagLayout());

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(60, 60, 140), 1),
                        "MotoMetrics — Create Account"
                ),
                BorderFactory.createEmptyBorder(10, 20, 15, 20)
        ));
        form.setPreferredSize(new Dimension(300, 260));
        form.setBackground(Color.WHITE);

        JTextField userField    = new JTextField();
        JPasswordField passField    = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();

        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        confirmField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnRow.setBackground(Color.WHITE);
        JButton regBtn  = makeButton("Register", new Color(34, 122, 34), Color.WHITE);
        JButton backBtn = makeButton("Back",      Color.LIGHT_GRAY,       Color.DARK_GRAY);
        btnRow.add(regBtn);
        btnRow.add(backBtn);

        form.add(new JLabel("Username"));
        form.add(Box.createVerticalStrut(4));
        form.add(userField);
        form.add(Box.createVerticalStrut(8));
        form.add(new JLabel("Password"));
        form.add(Box.createVerticalStrut(4));
        form.add(passField);
        form.add(Box.createVerticalStrut(8));
        form.add(new JLabel("Confirm Password"));
        form.add(Box.createVerticalStrut(4));
        form.add(confirmField);
        form.add(Box.createVerticalStrut(6));
        form.add(errorLabel);
        form.add(Box.createVerticalStrut(6));
        form.add(btnRow);

        add(form);

        regBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();
            String confirm  = new String(confirmField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }
            if (!password.equals(confirm)) {
                errorLabel.setText("Passwords do not match.");
                return;
            }
            if (username.length() < 3) {
                errorLabel.setText("Username must be at least 3 characters.");
                return;
            }

            boolean success = loginManager.register(username, password);
            if (success) {
                fileManager.saveUsers(loginManager.getUsers());
                // Auto-login after register
                loginManager.login(username, password);
                userField.setText(""); passField.setText(""); confirmField.setText("");
                errorLabel.setText(" ");
                DashboardPanel dash = (DashboardPanel) mainPanel.getComponent(2);
                dash.refresh();
                cardLayout.show(mainPanel, "DASHBOARD");
            } else {
                errorLabel.setText("Username already taken. Choose another.");
            }
        });

        backBtn.addActionListener(e -> {
            userField.setText(""); passField.setText(""); confirmField.setText("");
            errorLabel.setText(" ");
            cardLayout.show(mainPanel, "LOGIN");
        });
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(100, 30));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

class DashboardPanel extends JPanel {

    private VehicleManager vehicleManager;
    private LoginManager loginManager;
    private FileManager fileManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JLabel welcomeLabel;
    private DefaultListModel<String> listModel;
    private JList<String> vehicleList;

    DashboardPanel(CardLayout cardLayout, JPanel mainPanel,
                   VehicleManager vehicleManager, LoginManager loginManager,
                   FileManager fileManager) {

        this.cardLayout     = cardLayout;
        this.mainPanel      = mainPanel;
        this.vehicleManager = vehicleManager;
        this.loginManager   = loginManager;
        this.fileManager    = fileManager;

        setLayout(new BorderLayout());

        // ---- TOP NAV BAR ----
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(60, 60, 140));
        topBar.setBorder(new EmptyBorder(8, 14, 8, 14));

        welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel appLabel = new JLabel("MotoMetrics");
        appLabel.setForeground(new Color(200, 200, 255));
        appLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(178, 34, 34));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 11));
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        topBar.add(appLabel,    BorderLayout.WEST);
        topBar.add(welcomeLabel, BorderLayout.CENTER);
        topBar.add(logoutBtn,   BorderLayout.EAST);

        // ---- VEHICLE LIST ----
        listModel   = new DefaultListModel<>();
        vehicleList = new JList<>(listModel);
        vehicleList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        vehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vehicleList.setCellRenderer(new VehicleCellRenderer());
        vehicleList.setFixedCellHeight(52);

        JScrollPane scrollPane = new JScrollPane(vehicleList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("My Vehicles"));

        // ---- SIDE BUTTONS ----
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(150, 0));

        JButton addBtn     = makeSideBtn("+ Add Vehicle", new Color(34, 122, 34));
        JButton detailBtn  = makeSideBtn("View Details",  new Color(60, 60, 140));
        JButton tripBtn    = makeSideBtn("Start Trip →",  new Color(60, 60, 140));
        JButton removeBtn  = makeSideBtn("Remove",        new Color(178, 34, 34));

        sidePanel.add(addBtn);
        sidePanel.add(Box.createVerticalStrut(8));
        sidePanel.add(detailBtn);
        sidePanel.add(Box.createVerticalStrut(8));
        sidePanel.add(tripBtn);
        sidePanel.add(Box.createVerticalStrut(8));
        sidePanel.add(removeBtn);

        // ---- STATUS BAR ----
        JLabel statusBar = new JLabel("  Select a vehicle to view details or start a trip.");
        statusBar.setFont(new Font("SansSerif", Font.PLAIN, 11));
        statusBar.setForeground(Color.GRAY);
        statusBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
                new EmptyBorder(4, 8, 4, 8)
        ));

        // ---- ASSEMBLE ----
        add(topBar,    BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        // ---- ACTIONS ----
        logoutBtn.addActionListener(e -> {
            loginManager.logout();
            cardLayout.show(mainPanel, "LOGIN");
        });

        addBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "ADD_VEHICLE");
        });

        detailBtn.addActionListener(e -> {
            int idx = vehicleList.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(this, "Please select a vehicle first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Vehicle selected = vehicleManager.getVehicles().get(idx);
            VehicleDetailsPanel det = (VehicleDetailsPanel) mainPanel.getComponent(4);
            det.loadVehicle(selected);
            cardLayout.show(mainPanel, "VEHICLE_DETAILS");
        });

        tripBtn.addActionListener(e -> {
            if (vehicleManager.getVehicles().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Add a vehicle first before starting a trip.", "No Vehicles", JOptionPane.WARNING_MESSAGE);
                return;
            }
            TripPanel tp = (TripPanel) mainPanel.getComponent(5);
            tp.refreshVehicleList();
            cardLayout.show(mainPanel, "TRIP");
        });

        removeBtn.addActionListener(e -> {
            int idx = vehicleList.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(this, "Please select a vehicle to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name = vehicleManager.getVehicles().get(idx).getMake() + " " + vehicleManager.getVehicles().get(idx).getModel();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Remove " + name + "?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                vehicleManager.removeVehicle(idx);
                fileManager.saveVehicles(vehicleManager.getVehicles());
                refresh();
            }
        });
    }

    // Called every time Dashboard becomes visible
    public void refresh() {
        if (loginManager.getCurrentUser() != null) {
            welcomeLabel.setText("Welcome, " + loginManager.getCurrentUser().getUsername());
        }
        listModel.clear();
        ArrayList<Vehicle> vehicles = vehicleManager.getVehicles();
        for (Vehicle v : vehicles) {
            String type  = v instanceof Car ? "Car" : v instanceof Motorcycle ? "Motorcycle" : "Truck";
            String score = String.format("%.1f", v.CalculateScore());
            listModel.addElement(v.getMake() + " " + v.getModel() + "|" + type + " · " + v.getEngine().getCapacity() + "cc|" + score);
        }
    }

    private JButton makeSideBtn(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ---- Custom cell renderer for colored score badges ----
    private static class VehicleCellRenderer extends JPanel implements ListCellRenderer<String> {
        private JLabel nameLabel  = new JLabel();
        private JLabel subLabel   = new JLabel();
        private JLabel scoreLabel = new JLabel();

        VehicleCellRenderer() {
            setLayout(new BorderLayout(10, 0));
            setBorder(new EmptyBorder(6, 10, 6, 10));

            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setOpaque(false);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            subLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            subLabel.setForeground(Color.GRAY);
            textPanel.add(nameLabel);
            textPanel.add(subLabel);

            scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel.setOpaque(true);
            scoreLabel.setPreferredSize(new Dimension(70, 28));
            scoreLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            add(textPanel,  BorderLayout.CENTER);
            add(scoreLabel, BorderLayout.EAST);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            String[] parts = value.split("\\|");
            nameLabel.setText(parts[0]);
            subLabel.setText(parts.length > 1 ? parts[1] : "");
            double score = parts.length > 2 ? Double.parseDouble(parts[2]) : 0;
            scoreLabel.setText(String.format("%.1f/100", score));

            if (score >= 80) {
                scoreLabel.setBackground(new Color(200, 240, 200));
                scoreLabel.setForeground(new Color(26, 90, 26));
            } else if (score >= 60) {
                scoreLabel.setBackground(new Color(255, 240, 180));
                scoreLabel.setForeground(new Color(120, 80, 0));
            } else {
                scoreLabel.setBackground(new Color(255, 210, 210));
                scoreLabel.setForeground(new Color(139, 0, 0));
            }

            setBackground(isSelected ? new Color(220, 225, 255) : Color.WHITE);
            nameLabel.setForeground(Color.BLACK);
            return this;
        }
    }
}

class AddVehiclePanel extends JPanel {

    private VehicleManager vehicleManager;
    private FileManager fileManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Shared fields
    private JTextField makeField, modelField, engineCapField, engineHealthField;
    private JTextField oilField, airField, tyresField, suspField, brakesField;

    // Car-specific
    private JPanel acPanel;
    private JComboBox<String> acCombo;

    // Motorcycle-specific
    private JPanel chainPanel;
    private JTextField chainField;

    // Truck-specific
    private JPanel loadPanel;
    private JTextField loadField;

    // Type selector
    private String selectedType = "Car";

    AddVehiclePanel(CardLayout cardLayout, JPanel mainPanel,
                    VehicleManager vehicleManager, FileManager fileManager) {

        this.cardLayout     = cardLayout;
        this.mainPanel      = mainPanel;
        this.vehicleManager = vehicleManager;
        this.fileManager    = fileManager;

        setLayout(new BorderLayout());

        // ---- TOP BAR ----
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(60, 60, 140));
        topBar.setBorder(new EmptyBorder(8, 14, 8, 14));
        JLabel title = new JLabel("Add New Vehicle");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        topBar.add(title, BorderLayout.WEST);

        // ---- TYPE TOGGLE ----
        JPanel typeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        typeRow.setBackground(new Color(240, 240, 250));
        typeRow.add(new JLabel("Vehicle type:"));

        ButtonGroup group = new ButtonGroup();
        JRadioButton carBtn  = new JRadioButton("Car",        true);
        JRadioButton bikeBtn = new JRadioButton("Motorcycle", false);
        JRadioButton truckBtn= new JRadioButton("Truck",      false);
        group.add(carBtn); group.add(bikeBtn); group.add(truckBtn);
        typeRow.add(carBtn); typeRow.add(bikeBtn); typeRow.add(truckBtn);

        // ---- FORM GRID ----
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 6));
        formPanel.setBorder(new EmptyBorder(10, 16, 10, 16));

        makeField        = new JTextField();
        modelField       = new JTextField();
        engineCapField   = new JTextField("1600");
        engineHealthField= new JTextField("100");
        oilField         = new JTextField("10.0");
        airField         = new JTextField("10.0");
        tyresField       = new JTextField("10.0");
        suspField        = new JTextField("10.0");
        brakesField      = new JTextField("10.0");

        formPanel.add(new JLabel("Make:")); formPanel.add(makeField);
        formPanel.add(new JLabel("Model:")); formPanel.add(modelField);
        formPanel.add(new JLabel("Engine Capacity (cc):")); formPanel.add(engineCapField);
        formPanel.add(new JLabel("Engine Health (0–100):")); formPanel.add(engineHealthField);
        formPanel.add(new JLabel("Oil Level (0–10):")); formPanel.add(oilField);
        formPanel.add(new JLabel("Air Pressure (0–10):")); formPanel.add(airField);
        formPanel.add(new JLabel("Tyre Health (0–10):")); formPanel.add(tyresField);
        formPanel.add(new JLabel("Suspension (0–10):")); formPanel.add(suspField);
        formPanel.add(new JLabel("Brake Health (0–10):")); formPanel.add(brakesField);

        // ---- EXTRA FIELDS ----
        // Car: AC
        acCombo = new JComboBox<>(new String[]{"Yes", "No"});
        acPanel = new JPanel(new GridLayout(0, 2, 10, 6));
        acPanel.add(new JLabel("Has AC:")); acPanel.add(acCombo);
        acPanel.setBorder(new EmptyBorder(0, 16, 0, 16));

        // Motorcycle: chain sprocket
        chainField = new JTextField("10.0");
        chainPanel = new JPanel(new GridLayout(0, 2, 10, 6));
        chainPanel.add(new JLabel("Chain-Sprocket Health (0–10):")); chainPanel.add(chainField);
        chainPanel.setBorder(new EmptyBorder(0, 16, 0, 16));
        chainPanel.setVisible(false);

        // Truck: load capacity
        loadField = new JTextField("20000");
        loadPanel = new JPanel(new GridLayout(0, 2, 10, 6));
        loadPanel.add(new JLabel("Load Capacity (kg):")); loadPanel.add(loadField);
        loadPanel.setBorder(new EmptyBorder(0, 16, 0, 16));
        loadPanel.setVisible(false);

        // ---- ERROR + BUTTONS ----
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        errorLabel.setBorder(new EmptyBorder(0, 16, 0, 16));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        JButton saveBtn   = makeBtn("Save Vehicle", new Color(34, 122, 34));
        JButton cancelBtn = makeBtn("Cancel",       Color.GRAY);
        btnRow.add(saveBtn); btnRow.add(cancelBtn);

        // ---- INNER SCROLL CONTENT ----
        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.add(typeRow);
        inner.add(formPanel);
        inner.add(acPanel);
        inner.add(chainPanel);
        inner.add(loadPanel);
        inner.add(errorLabel);
        inner.add(btnRow);

        JScrollPane scroll = new JScrollPane(inner);
        scroll.setBorder(null);

        add(topBar, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // ---- TYPE TOGGLE ACTIONS ----
        carBtn.addActionListener(e -> {
            selectedType = "Car";
            acPanel.setVisible(true);
            chainPanel.setVisible(false);
            loadPanel.setVisible(false);
            revalidate(); repaint();
        });
        bikeBtn.addActionListener(e -> {
            selectedType = "Motorcycle";
            acPanel.setVisible(false);
            chainPanel.setVisible(true);
            loadPanel.setVisible(false);
            revalidate(); repaint();
        });
        truckBtn.addActionListener(e -> {
            selectedType = "Truck";
            acPanel.setVisible(false);
            chainPanel.setVisible(false);
            loadPanel.setVisible(true);
            revalidate(); repaint();
        });

        // ---- SAVE ACTION ----
        saveBtn.addActionListener(e -> {
            errorLabel.setText(" ");

            String make  = makeField.getText().trim();
            String model = modelField.getText().trim();

            if (make.isEmpty() || model.isEmpty()) {
                errorLabel.setText("Make and Model are required.");
                return;
            }

            try {
                int    engCap    = Integer.parseInt(engineCapField.getText().trim());
                int    engHealth = Integer.parseInt(engineHealthField.getText().trim());
                double oil       = Double.parseDouble(oilField.getText().trim());
                double air       = Double.parseDouble(airField.getText().trim());
                double tyres     = Double.parseDouble(tyresField.getText().trim());
                double susp      = Double.parseDouble(suspField.getText().trim());
                double brakes    = Double.parseDouble(brakesField.getText().trim());

                // Validate ranges
                if (engHealth < 0 || engHealth > 100) { errorLabel.setText("Engine health must be 0–100."); return; }
                if (oil < 0 || oil > 10 || air < 0 || air > 10 || tyres < 0 || tyres > 10
                        || susp < 0 || susp > 10 || brakes < 0 || brakes > 10) {
                    errorLabel.setText("Health values must be 0–10.");
                    return;
                }

                Vehicle v;
                if (selectedType.equals("Car")) {
                    boolean hasAC = acCombo.getSelectedItem().equals("Yes");
                    v = new Car(make, model, brakes, oil, air, tyres, susp, hasAC, engCap, engHealth);

                } else if (selectedType.equals("Motorcycle")) {
                    double chain = Double.parseDouble(chainField.getText().trim());
                    if (chain < 0 || chain > 10) { errorLabel.setText("Chain-Sprocket must be 0–10."); return; }
                    v = new Motorcycle(make, model, brakes, oil, air, tyres, susp, chain, engCap, engHealth);

                } else {
                    double load = Double.parseDouble(loadField.getText().trim());
                    v = new Truck(make, model, brakes, oil, air, tyres, susp, engCap, engHealth, load);
                }

                vehicleManager.addVehicle(v);
                fileManager.saveVehicles(vehicleManager.getVehicles());

                // Refresh dashboard and go back
                DashboardPanel dash = (DashboardPanel) mainPanel.getComponent(2);
                dash.refresh();
                clearForm();
                cardLayout.show(mainPanel, "DASHBOARD");

            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter valid numbers in all fields.");
            }
        });

        cancelBtn.addActionListener(e -> {
            clearForm();
            cardLayout.show(mainPanel, "DASHBOARD");
        });
    }

    private void clearForm() {
        makeField.setText(""); modelField.setText("");
        engineCapField.setText("1600"); engineHealthField.setText("100");
        oilField.setText("10.0"); airField.setText("10.0");
        tyresField.setText("10.0"); suspField.setText("10.0");
        brakesField.setText("10.0");
        chainField.setText("10.0"); loadField.setText("20000");
        acCombo.setSelectedIndex(0);
    }

    private JButton makeBtn(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

class VehicleDetailsPanel extends JPanel {

    private VehicleManager vehicleManager;
    private FileManager fileManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Vehicle currentVehicle;

    // Health bars
    private JProgressBar engineBar, oilBar, airBar, tyresBar, suspBar, brakesBar;
    private JProgressBar chainBar;   // Motorcycle only
    private JPanel chainRow;
    private JLabel scoreLabel;
    private JTextArea reportArea;
    private JLabel titleLabel;

    VehicleDetailsPanel(CardLayout cardLayout, JPanel mainPanel, FileManager fileManager, VehicleManager vehicleManager) {
        this.cardLayout  = cardLayout;
        this.mainPanel   = mainPanel;
        this.fileManager = fileManager;
        this.vehicleManager = vehicleManager;

        setLayout(new BorderLayout());

        // ---- TOP BAR ----
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(60, 60, 140));
        topBar.setBorder(new EmptyBorder(8, 14, 8, 14));
        titleLabel = new JLabel("Vehicle Health Report");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        topBar.add(titleLabel, BorderLayout.WEST);

        // ---- SCORE LABEL ----
        scoreLabel = new JLabel("--/100", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        scoreLabel.setBorder(new EmptyBorder(12, 0, 4, 0));

        // ---- HEALTH BARS ----
        engineBar = makeBar(); oilBar  = makeBar();
        airBar    = makeBar(); tyresBar= makeBar();
        suspBar   = makeBar(); brakesBar=makeBar();
        chainBar  = makeBar();

        JPanel barsPanel = new JPanel(new GridLayout(0, 1, 0, 6));
        barsPanel.setBorder(new EmptyBorder(6, 16, 6, 16));
        barsPanel.add(makeBarRow("Engine Health",    engineBar, "0–100"));
        barsPanel.add(makeBarRow("Oil Level",         oilBar,   "0–10"));
        barsPanel.add(makeBarRow("Air Pressure",      airBar,   "0–10"));
        barsPanel.add(makeBarRow("Tyre Health",       tyresBar, "0–10"));
        barsPanel.add(makeBarRow("Suspension",        suspBar,  "0–10"));
        barsPanel.add(makeBarRow("Brake Health",      brakesBar,"0–10"));
        chainRow = makeBarRow("Chain-Sprocket",       chainBar, "0–10");
        barsPanel.add(chainRow);

        // ---- REPORT AREA ----
        reportArea = new JTextArea(8, 30);
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane reportScroll = new JScrollPane(reportArea);
        reportScroll.setBorder(BorderFactory.createTitledBorder("Diagnosable Report"));

        // ---- BUTTONS ----
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton serviceBtn = makeBtn("Service Engine",  new Color(34, 122, 34));
        JButton saveBtn    = makeBtn("Save Report (.txt)", new Color(60, 60, 140));
        JButton backBtn    = makeBtn("← Back",          Color.GRAY);
        JButton resetBtn = new JButton("Reset All Stats");
        resetBtn.setBackground(new Color(178, 34, 34));  // dark red
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        resetBtn.setBorderPainted(false);
        resetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnRow.add(serviceBtn);
        btnRow.add(saveBtn);
        btnRow.add(backBtn);
        btnRow.add(resetBtn);

        // ---- LEFT PANEL (bars + score) ----
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(scoreLabel, BorderLayout.NORTH);
        leftPanel.add(barsPanel,  BorderLayout.CENTER);

        // ---- INNER SPLIT ----
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, reportScroll);
        split.setDividerLocation(340);
        split.setResizeWeight(0.5);

        // ---- ASSEMBLE ----
        JPanel center = new JPanel(new BorderLayout());
        center.add(split,   BorderLayout.CENTER);
        center.add(btnRow,  BorderLayout.SOUTH);

        add(topBar, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        // ---- ACTIONS ----
        serviceBtn.addActionListener(e -> {
            if (currentVehicle != null) {
                currentVehicle.serviceEngine();
                loadVehicle(currentVehicle); // refresh bars
                JOptionPane.showMessageDialog(this, "Engine serviced! Health restored to 100.", "Service Complete", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        saveBtn.addActionListener(e -> {
            if (currentVehicle == null) return;
            String filename = currentVehicle.getMake() + "_" + currentVehicle.getModel() + "_report.txt";
            fileManager.saveReport(reportArea.getText(), filename);
            JOptionPane.showMessageDialog(this, "Report saved as: " + filename, "Saved", JOptionPane.INFORMATION_MESSAGE);
        });

        backBtn.addActionListener(e -> {
            DashboardPanel dash = (DashboardPanel) mainPanel.getComponent(2);
            dash.refresh();
            cardLayout.show(mainPanel, "DASHBOARD");
        });

        resetBtn.addActionListener(e -> {
            if (currentVehicle == null) return;

            // Ask user to confirm — important so they don't reset by accident
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Reset ALL stats for " + currentVehicle.getMake() + " " + currentVehicle.getModel() + " to 10.0?\nThis cannot be undone.",
                    "Confirm Reset",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                currentVehicle.resetStats();               // reset all stats
                fileManager.saveVehicles(vehicleManager.getVehicles()); // save to file
                loadVehicle(currentVehicle);               // refresh the progress bars
                JOptionPane.showMessageDialog(this,
                        "All stats reset to 10.0. Engine restored to 100.",
                        "Reset Complete",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }

    // Called by DashboardPanel before switching to this screen
    public void loadVehicle(Vehicle v) {
        currentVehicle = v;
        titleLabel.setText(v.getMake() + " " + v.getModel() + " — Health Report");

        double score = v.CalculateScore();
        scoreLabel.setText(String.format("%.1f / 100", score));
        if (score >= 80)      scoreLabel.setForeground(new Color(26, 90, 26));
        else if (score >= 60) scoreLabel.setForeground(new Color(150, 100, 0));
        else                  scoreLabel.setForeground(new Color(139, 0, 0));

        // Engine bar is 0–100, rest are 0–10 scaled to 0–100
        setBar(engineBar, v.getEngine().getHealth(), 100);
        setBar(oilBar,    v.getOilLevel(),    10);
        setBar(airBar,    v.getAirPressure(), 10);
        setBar(tyresBar,  v.getTyres(),       10);
        setBar(suspBar,   v.getSuspension(),  10);
        setBar(brakesBar, v.getBrakes(),      10);

        // Show chain bar only for Motorcycle
        if (v instanceof Motorcycle) {
            Motorcycle m = (Motorcycle) v;
            setBar(chainBar, m.getChainSprocketHealth(), 10);
            chainRow.setVisible(true);
        } else {
            chainRow.setVisible(false);
        }

        // Generate Diagnosable report
        if (v instanceof Diagnosable) {
            Diagnosable d = (Diagnosable) v;
            reportArea.setText(d.generateReport());
        } else {
            reportArea.setText("No report available.");
        }
        reportArea.setCaretPosition(0);
    }

    // ---- Helpers ----
    private void setBar(JProgressBar bar, double value, double max) {
        int pct = (int) Math.round((value / max) * 100);
        pct = Math.max(0, Math.min(100, pct));
        bar.setValue(pct);
        bar.setString(String.format("%.1f", value));
        if (pct >= 70)      bar.setForeground(new Color(34, 139, 34));
        else if (pct >= 40) bar.setForeground(new Color(210, 140, 0));
        else                bar.setForeground(new Color(178, 34, 34));
    }

    private JProgressBar makeBar() {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setStringPainted(true);
        bar.setPreferredSize(new Dimension(160, 18));
        return bar;
    }

    private JPanel makeBarRow(String label, JProgressBar bar, String range) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setPreferredSize(new Dimension(130, 18));
        row.add(lbl, BorderLayout.WEST);
        row.add(bar, BorderLayout.CENTER);
        return row;
    }

    private JButton makeBtn(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

class TripPanel extends JPanel {

    private VehicleManager vehicleManager;
    private AdviceManager adviceManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JComboBox<String> vehicleCombo;
    private JTextField distanceField, loadField;
    private JComboBox<String> terrainCombo;

    private JCheckBox hardBrakingBox, overspeedBox, aggressiveBox, longHoursBox;
    private JLabel errorLabel;

    TripPanel(CardLayout cardLayout, JPanel mainPanel,
              VehicleManager vehicleManager, AdviceManager adviceManager) {

        this.cardLayout      = cardLayout;
        this.mainPanel       = mainPanel;
        this.vehicleManager  = vehicleManager;
        this.adviceManager   = adviceManager;

        setLayout(new BorderLayout());

        // ---- TOP BAR ----
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(60, 60, 140));
        topBar.setBorder(new EmptyBorder(8, 14, 8, 14));
        JLabel title = new JLabel("Plan a Trip");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        topBar.add(title, BorderLayout.WEST);

        // ---- LEFT: TRIP DETAILS ----
        JPanel tripDetails = new JPanel(new GridLayout(0, 2, 10, 8));
        tripDetails.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Trip Details"),
                new EmptyBorder(8, 10, 8, 10)
        ));

        vehicleCombo = new JComboBox<>();
        distanceField = new JTextField("500");
        loadField     = new JTextField("0");
        terrainCombo  = new JComboBox<>(new String[]{"City", "Highway", "Hilly", "Off-road", "Mixed"});

        tripDetails.add(new JLabel("Select Vehicle:")); tripDetails.add(vehicleCombo);
        tripDetails.add(new JLabel("Distance (km):"));  tripDetails.add(distanceField);
        tripDetails.add(new JLabel("Load (kg):"));      tripDetails.add(loadField);
        tripDetails.add(new JLabel("Terrain:"));        tripDetails.add(terrainCombo);

        // ---- RIGHT: DRIVER BEHAVIOR ----
        JPanel behaviorPanel = new JPanel();
        behaviorPanel.setLayout(new BoxLayout(behaviorPanel, BoxLayout.Y_AXIS));
        behaviorPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Driver Behavior"),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel hint = new JLabel("Check all that apply to your driving:");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 11));
        hint.setForeground(Color.GRAY);
        hint.setAlignmentX(Component.LEFT_ALIGNMENT);

        hardBrakingBox = new JCheckBox("Hard Braking");
        overspeedBox   = new JCheckBox("Overspeeding");
        aggressiveBox  = new JCheckBox("Aggressive Driving");
        longHoursBox   = new JCheckBox("Long Driving Hours (4+ hrs)");

        for (JCheckBox cb : new JCheckBox[]{hardBrakingBox, overspeedBox, aggressiveBox, longHoursBox}) {
            cb.setFont(new Font("SansSerif", Font.PLAIN, 13));
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        behaviorPanel.add(hint);
        behaviorPanel.add(Box.createVerticalStrut(8));
        behaviorPanel.add(hardBrakingBox);
        behaviorPanel.add(Box.createVerticalStrut(4));
        behaviorPanel.add(overspeedBox);
        behaviorPanel.add(Box.createVerticalStrut(4));
        behaviorPanel.add(aggressiveBox);
        behaviorPanel.add(Box.createVerticalStrut(4));
        behaviorPanel.add(longHoursBox);

        // ---- CENTER ROW ----
        JPanel centerRow = new JPanel(new GridLayout(1, 2, 14, 0));
        centerRow.setBorder(new EmptyBorder(12, 14, 4, 14));
        centerRow.add(tripDetails);
        centerRow.add(behaviorPanel);

        // ---- BUTTONS ----
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        JButton adviceBtn = makeBtn("Get Advice →", new Color(34, 122, 34));
        JButton cancelBtn = makeBtn("Cancel",        Color.GRAY);
        btnRow.add(adviceBtn); btnRow.add(cancelBtn); btnRow.add(errorLabel);

        // ---- ASSEMBLE ----
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(btnRow, BorderLayout.CENTER);

        add(topBar,    BorderLayout.NORTH);
        add(centerRow, BorderLayout.CENTER);
        add(bottom,    BorderLayout.SOUTH);

        // ---- ACTIONS ----
        adviceBtn.addActionListener(e -> {
            errorLabel.setText(" ");

            if (vehicleCombo.getSelectedIndex() < 0 || vehicleManager.getVehicles().isEmpty()) {
                errorLabel.setText("Please select a vehicle.");
                return;
            }

            try {
                double distance = Double.parseDouble(distanceField.getText().trim());
                double load     = Double.parseDouble(loadField.getText().trim());

                if (distance <= 0) { errorLabel.setText("Distance must be greater than 0."); return; }

                Vehicle selected = vehicleManager.getVehicles().get(vehicleCombo.getSelectedIndex());
                String  terrain  = (String) terrainCombo.getSelectedItem();

                DriverBehavior db = new DriverBehavior(
                        hardBrakingBox.isSelected(),
                        overspeedBox.isSelected(),
                        aggressiveBox.isSelected(),
                        longHoursBox.isSelected()
                );

                Trip trip = new Trip(distance, terrain, load, selected, db);

                // Pass trip to AdvicePanel and switch
                AdvicePanel ap = (AdvicePanel) mainPanel.getComponent(6);
                ap.loadAdvice(trip, adviceManager);
                cardLayout.show(mainPanel, "ADVICE");

            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter valid numbers for distance and load.");
            }
        });

        cancelBtn.addActionListener(e -> cardLayout.show(mainPanel, "DASHBOARD"));
    }

    // Called each time TripPanel becomes visible
    public void refreshVehicleList() {
        vehicleCombo.removeAllItems();
        ArrayList<Vehicle> vehicles = vehicleManager.getVehicles();
        for (Vehicle v : vehicles) {
            String type = v instanceof Car ? "Car" : v instanceof Motorcycle ? "Bike" : "Truck";
            vehicleCombo.addItem(v.getMake() + " " + v.getModel() + " (" + type + ")");
        }
        // Reset checkboxes
        hardBrakingBox.setSelected(false);
        overspeedBox.setSelected(false);
        aggressiveBox.setSelected(false);
        longHoursBox.setSelected(false);
    }

    private JButton makeBtn(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

class AdvicePanel extends JPanel {

    private Trip currentTrip;
    private WearManager wearManager = new WearManager();
    private VehicleManager vehicleManager;
    private FileManager fileManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JLabel scoreLabel;
    private JLabel vehicleNameLabel;
    private JLabel tripInfoLabel;
    private JTextArea vehicleAdviceArea;
    private JTextArea behaviorArea;
    private String fullReport = "";

    private JButton completeBtn;

    AdvicePanel(CardLayout cardLayout, JPanel mainPanel, FileManager fileManager, VehicleManager vehicleManager) {
        this.cardLayout  = cardLayout;
        this.mainPanel   = mainPanel;
        this.fileManager = fileManager;
        this.vehicleManager = vehicleManager;

        setLayout(new BorderLayout());

        // ---- TOP BAR ----
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(60, 60, 140));
        topBar.setBorder(new EmptyBorder(8, 14, 8, 14));
        JLabel title = new JLabel("Trip Advice");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        topBar.add(title, BorderLayout.WEST);

        // ---- SCORE HEADER ----
        JPanel scorePanel = new JPanel(new BorderLayout(14, 0));
        scorePanel.setBorder(new EmptyBorder(12, 16, 6, 16));

        scoreLabel = new JLabel("--/100", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        scoreLabel.setPreferredSize(new Dimension(120, 60));
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel infoRight = new JPanel();
        infoRight.setLayout(new BoxLayout(infoRight, BoxLayout.Y_AXIS));
        vehicleNameLabel = new JLabel("--");
        vehicleNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        tripInfoLabel = new JLabel("--");
        tripInfoLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tripInfoLabel.setForeground(Color.GRAY);
        infoRight.add(vehicleNameLabel);
        infoRight.add(Box.createVerticalStrut(4));
        infoRight.add(tripInfoLabel);

        scorePanel.add(scoreLabel,  BorderLayout.WEST);
        scorePanel.add(infoRight,   BorderLayout.CENTER);

        // ---- ADVICE AREAS ----
        vehicleAdviceArea = makeTextArea(new Color(255, 250, 240));
        behaviorArea      = makeTextArea(new Color(255, 245, 245));

        JScrollPane vehicleScroll = new JScrollPane(vehicleAdviceArea);
        vehicleScroll.setBorder(BorderFactory.createTitledBorder("Vehicle Advice"));

        JScrollPane behaviorScroll = new JScrollPane(behaviorArea);
        behaviorScroll.setBorder(BorderFactory.createTitledBorder("Driver Behavior Warnings"));

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, vehicleScroll, behaviorScroll);
        split.setDividerLocation(140);
        split.setResizeWeight(0.5);

        // ---- BUTTONS ----
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton saveBtn  = makeBtn("Save Report (.txt)", new Color(60, 60, 140));
        JButton tripBtn  = makeBtn("New Trip",           new Color(34, 122, 34));
        JButton dashBtn  = makeBtn("Dashboard",          Color.GRAY);
        completeBtn = makeBtn("Complete Trip ✓", new Color(150, 50, 150));

        btnRow.add(saveBtn);
        btnRow.add(tripBtn);
        btnRow.add(dashBtn);
        btnRow.add(completeBtn);


        // ---- ASSEMBLE ----
        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBorder(new EmptyBorder(0, 10, 0, 10));
        centerArea.add(split, BorderLayout.CENTER);

        add(topBar,     BorderLayout.NORTH);
        add(scorePanel, BorderLayout.CENTER);

        JPanel mainCenter = new JPanel(new BorderLayout());
        mainCenter.add(scorePanel,  BorderLayout.NORTH);
        mainCenter.add(centerArea,  BorderLayout.CENTER);
        mainCenter.add(btnRow,      BorderLayout.SOUTH);

        // Replace center
        remove(scorePanel);
        add(mainCenter, BorderLayout.CENTER);

        // ---- ACTIONS ----
        saveBtn.addActionListener(e -> {
            if (fullReport.isEmpty()) return;
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new java.io.File("trip_report.txt"));
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                fileManager.saveReport(fullReport, path);
                JOptionPane.showMessageDialog(this, "Report saved to:\n" + path, "Saved", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        tripBtn.addActionListener(e -> {
            TripPanel tp = (TripPanel) mainPanel.getComponent(5);
            tp.refreshVehicleList();
            cardLayout.show(mainPanel, "TRIP");
        });

        dashBtn.addActionListener(e -> {
            DashboardPanel dash = (DashboardPanel) mainPanel.getComponent(2);
            dash.refresh();
            cardLayout.show(mainPanel, "DASHBOARD");
        });

        completeBtn.addActionListener(e -> {
            if (currentTrip == null) return;

            // Save old values for summary
            Vehicle v       = currentTrip.getVehicle();
            double oldTyre  = v.getTyres();
            double oldSusp  = v.getSuspension();
            double oldBrake = v.getBrakes();
            double oldOil   = v.getOilLevel();
            int    oldEng   = v.getEngine().getHealth();

            // Apply wear
            wearManager.applyWear(currentTrip);

            // Save updated vehicle stats to file
            fileManager.saveVehicles(vehicleManager.getVehicles());

            // Show wear summary in a popup
            String summary = wearManager.getWearSummary(v, oldTyre, oldSusp, oldBrake, oldOil, oldEng);
            JOptionPane.showMessageDialog(this, summary, "Trip Complete — Wear Applied", JOptionPane.INFORMATION_MESSAGE);

            // Disable button so user can't apply twice
            completeBtn.setEnabled(false);

            // Go to dashboard so user sees updated scores
            DashboardPanel dash = (DashboardPanel) mainPanel.getComponent(2);
            dash.refresh();
            cardLayout.show(mainPanel, "DASHBOARD");
        });
    }

    // Called by TripPanel before switching to this screen
    public void loadAdvice(Trip trip, AdviceManager am) {
        this.currentTrip = trip;
        double score = trip.getVehicle().CalculateScore();

        // Score label + color
        scoreLabel.setText(String.format("%.1f/100", score));
        if (score >= 80)      scoreLabel.setForeground(new Color(26, 90, 26));
        else if (score >= 60) scoreLabel.setForeground(new Color(150, 100, 0));
        else                  scoreLabel.setForeground(new Color(139, 0, 0));

        // Vehicle + trip info
        vehicleNameLabel.setText(trip.getVehicle().getMake() + " " + trip.getVehicle().getModel());
        tripInfoLabel.setText(trip.getDistance() + " km · " + trip.getTerrain() + " terrain · Load: " + trip.getLoad() + " kg");

        // Advice text
        vehicleAdviceArea.setText(am.getScoreAdvice(score));
        behaviorArea.setText(am.getBehaviorWarnings(trip.getDriverBehavior()));

        // Build full report for saving
        fullReport = am.getSummaryReport(trip);

        vehicleAdviceArea.setCaretPosition(0);
        behaviorArea.setCaretPosition(0);

        completeBtn.setEnabled(true);
    }

    private JTextArea makeTextArea(Color bg) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("SansSerif", Font.PLAIN, 13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(bg);
        area.setBorder(new EmptyBorder(8, 10, 8, 10));
        return area;
    }

    private JButton makeBtn(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

public class OOP_Final_Project {
    public static void main(String[] args) {

        // ---- Create all shared manager objects (created ONCE, passed everywhere) ----
        VehicleManager vehicleManager = new VehicleManager();
        LoginManager   loginManager   = new LoginManager();
        FileManager    fileManager    = new FileManager();
        AdviceManager  adviceManager  = new AdviceManager();

        // ---- Load saved data on startup ----
        ArrayList<User> savedUsers = fileManager.loadUsers();
        loginManager.setUsers(savedUsers);

        ArrayList<Vehicle> savedVehicles = fileManager.loadVehicles();
        for (Vehicle v : savedVehicles) {
            vehicleManager.addVehicle(v);
        }

        // ---- Build UI on Event Dispatch Thread ----
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("MotoMetrics — Vehicle Wear & Tear Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(780, 540);
            frame.setLocationRelativeTo(null); // center on screen

            // ---- CardLayout — one container, multiple panels ----
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            // ---- Create all panels ----
            // NOTE: Order matters — DashboardPanel uses getComponent(2),
            //       VehicleDetailsPanel uses getComponent(4),
            //       TripPanel uses getComponent(5), AdvicePanel uses getComponent(6)

            LoginPanel         loginPanel   = new LoginPanel(cardLayout, mainPanel, loginManager, fileManager);
            RegisterPanel      registerPanel= new RegisterPanel(cardLayout, mainPanel, loginManager, fileManager);
            DashboardPanel     dashPanel    = new DashboardPanel(cardLayout, mainPanel, vehicleManager, loginManager, fileManager);
            AddVehiclePanel    addPanel     = new AddVehiclePanel(cardLayout, mainPanel, vehicleManager, fileManager);
            VehicleDetailsPanel detailPanel = new VehicleDetailsPanel(cardLayout, mainPanel, fileManager, vehicleManager);            TripPanel          tripPanel    = new TripPanel(cardLayout, mainPanel, vehicleManager, adviceManager);
            AdvicePanel advicePanel = new AdvicePanel(cardLayout, mainPanel, fileManager, vehicleManager);

            // ---- Register panels (index order MUST match getComponent() calls above) ----
            mainPanel.add(loginPanel,    "LOGIN");          // index 0
            mainPanel.add(registerPanel, "REGISTER");       // index 1
            mainPanel.add(dashPanel,     "DASHBOARD");      // index 2
            mainPanel.add(addPanel,      "ADD_VEHICLE");    // index 3
            mainPanel.add(detailPanel,   "VEHICLE_DETAILS");// index 4
            mainPanel.add(tripPanel,     "TRIP");           // index 5
            mainPanel.add(advicePanel,   "ADVICE");         // index 6

            // ---- Start at Login ----
            cardLayout.show(mainPanel, "LOGIN");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
