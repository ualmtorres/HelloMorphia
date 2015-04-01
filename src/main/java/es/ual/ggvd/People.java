package es.ual.ggvd;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public class People {
  // A field must be annotated as id
  @Id private ObjectId id;
  
  private String name;
  private int age;
  
  // The instance variable "location" is stored as "country" in the database 
  @Property("country") private String location;
  
  // The instance variable "cars" includes a list of cars
  @Embedded
  private List<Car> cars;

  // A default costruct must be provided
  public People() {
  }

  // People constructor
  /**
   * Creates an instance of People class
   * 
   * @param name      name of the person
   * @param location  location where the person lives
   * @param age       age of the person
   */
  public People(String name, String location, int age) {
    this.name = name;
    this.location = location;
    this.age = age;
  }

  // Getters and Setters
  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public List<Car> getCars() {
    return cars;
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  } 
}
