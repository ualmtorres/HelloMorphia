package es.ual.ggvd;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class PeopleDAO extends BasicDAO<People, ObjectId> {

  // A constructor is required
  // super is called passing a datastore to enable DAO creating, reading, 
  // updating and deleting objects in the datastore
  /**
   * This constructor enables creating, reading, updating and deleting 
   * objects in the datastore
   * 
   * @param ds  The datastore associated to a MongoDB database
   */
  protected PeopleDAO(Datastore ds) {
    super(ds);
  }
  
  /**
   * Find people by name
   * 
   * @param name  The name of the people to find
   * @return      A List<People> with "name" matching the parameter
   */
  public List<People> findPeopleByName(String name) {
    return find(createQuery().filter("name =", name))
        .asList();
  }
  
  /**
   * Find people older than an age
   * 
   * @param age   The minimum age of the people to find
   * @return      A List<People> with an age greater than the parameter
   */
  public List<People> findPeopleOlderThan(int age) {
    return find(createQuery().filter("age >=", age))
        .asList();
  }

  /**
   * Find people who owns a car with the make and model provided
   * 
   * @param make  The make of the car to search
   * @param model The model of the car to search
   * @return      A List<People> who owns a car matching the parameters provided
   */
  public List<People> findPeopleByCar(String make, String model) {
    return find(createQuery().filter("cars.make", make).filter("cars.model", model))
        .asList();
  }
  
  /**
   * Delete people
   * @param name  The name of the people to be deleted
   */
  public void deleteByName(String name) {
    deleteByQuery(createQuery().filter("name", name));
  }
  
  /**
   * Delete all the people
   */
  public void deleteAll() {
    deleteByQuery(createQuery());
  }

}
