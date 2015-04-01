package es.ual.ggvd;

public class Car {

  private String make;
  private String model;
  private String colour;
  private int power;
  
  // A default constructor must be provided 
  public Car() {
  }
  
  // Car constructor
  /**
   * Car constructor
   * 
   * @param make    Make of the car
   * @param model   Model of the car
   * @param colour  Colour of the car
   * @param power   Max power in HP
   */
  public Car(String make, String model, String colour, int power) {
    this.make = make;
    this.model = model;
    this.colour = colour;
    this.power = power;
  }

  // Getters and Setters
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

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }
  
  
}
