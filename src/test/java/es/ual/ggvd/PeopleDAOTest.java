package es.ual.ggvd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class PeopleDAOTest {
  
  static PeopleDAO dao;

  /**
   * Setup the environment creating a database name "helloMorphia"
   * First, deletes the collection "People"
   * Then two people are added to the collection "People"
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    
    // Create a DAO for People
    Morphia morphia = new Morphia();
    Datastore ds = morphia.createDatastore(new MongoClient(), "helloMorphia");
    dao = new PeopleDAO(ds);
    
    // Delete the previous data
    dao.deleteAll();
    
    /* 
     * *******************************************
     * Create the first person of the collection
     * ********************************************
     */
    
    // Personal data
    String p1Name = "John";
    String p1Location = "Spain";
    int p1Age = 40;

    // The cars of the first person
    List<Car> cars = new ArrayList<Car>();
    cars.add(new Car("Ford", "GT", "black", 600));
    cars.add(new Car("Mitsubishi", "Lancer Evolution", "red", 287));

    // Create the person
    People p1 = new People(p1Name, p1Location, p1Age);
    p1.setCars(cars);

    // Save the person
    dao.save(p1);
 
    /* 
     * *******************************************
     * Create the second person of the collection
     * ********************************************
     */
    
    // Personal data
    String p2Name = "Patty";
    String p2Location = "France";
    int p2Age = 28;
    
    // The cars of the second person
    cars.clear();   // Clear the cars to initialize Patty's car list
    cars.add(new Car("Audi", "S3", "white", 292));
    
    // Create the person
    People p2 = new People(p2Name, p2Location, p2Age);
    p2.setCars(cars);
    
    // Save the person
    dao.save(p2);

  }

  /**
   * Create a people, store it, retrieve it from the database and test it is the same
   */
  @Test
  public void shouldCreatePeople() {

    String pName = "Mike";
    
    // Create and store the people
    People p = new People();
    p.setName(pName);
    dao.save(p);
    
    // Get the people by name
    List<People> people = dao.findPeopleByName(pName);
    
    // The list is not null, it has one people, and the name matches
    assertNotNull(people);
    assertEquals(1, people.size());
    assertEquals(pName, people.get(0).getName());
    }

  /**
   * People can be loaded from the database and their name, location and age 
   * must match with the expected values
   */
  @Test
  public void shouldLoadPeopleByName() {

    // Personal data of the people to search
    String pName = "John";
    String pLocation = "Spain";
    int pAge = 40;
    int pNumberOfCars = 2;
    
    // Get the people by name
    List<People> people = dao.findPeopleByName(pName);
    
    // Get the first (and only) element
    People pLoaded = people.get(0);
    
    // The list is not null and it has one people
    assertNotNull(people);
    assertEquals(1, people.size());
    
    // The name, location and age of the person loaded matches 
    assertNotNull(pLoaded);
    assertEquals(pLoaded.getName(), pName);
    assertEquals(pLoaded.getLocation(), pLocation);
    assertEquals(pLoaded.getAge(), pAge);
    
    // The quantity of cars must match
    assertEquals(pLoaded.getCars().size(), pNumberOfCars);
  }
  
  /**
   * People can be found from the make and model of the cars they own
   */
  @Test
  public void shouldFindPeopleByCar() {

    // Personal data of the people to search
    String pName = "John";
    String cMake = "Ford";
    String cModel = "GT";
    
    // Get the people by make and model of the cars they own
    List<People> people = dao.findPeopleByCar(cMake, cModel);

    // Get the first (and only) element
    People pLoaded = people.get(0);

    // The quantity of people owning the car must match
    assertNotNull(people);
    assertEquals(1, people.size());   
    
    // The owner of the car must match
    assertEquals(pLoaded.getName(), pName);
  }
  
  /**
   * People can be found by age
   */
  @Test
  public void shouldFindPeopleByAge() {

    int  pAge = 25;
    
    // Get the people older than the age provided
    List<People> people = dao.findPeopleOlderThan(pAge);

    // The quantity of people must match
    assertNotNull(people);
    assertEquals(2, people.size());   
  }

  /**
   * People can be deleted providing their name
   */
  @Test
  public void shouldDeleteByName() {

    String pName = "Mike";
    
    // Delete the people with the name provided
    dao.deleteByName(pName);
    
    // Get the list of people with the name deleted
    List<People> people = dao.findPeopleByName(pName);

    // Check that the list is empty
    assertEquals(people.size(),0);   
  }
}
