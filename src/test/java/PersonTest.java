import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class PersonTest {

  @Rule
public DatabaseRule database = new DatabaseRule();

 // save Persons in a database
  @Test
   public void save_insertsObjectIntoDatabase_Person() {
     Person testPerson = new Person("Henry", "[email protected]");
     testPerson.save();
     assertTrue(Person.all().get(0).equals(testPerson));
   }

  @Test
  public void person_instantiatesCorrectly_true() {
    Person testPerson = new Person("Henry", "[email protected]");
    assertEquals(true, testPerson instanceof Person);
  }

  @Test
  public void getName_personInstantiatesWithName_Henry() {
    Person testPerson = new Person("Henry", "[email protected]");
    assertEquals("Henry", testPerson.getName());
  }

  @Test
  public void getEmail_personInstantiatesWithEmail_String() {
    Person testPerson = new Person("Henry", "[email protected]");
    assertEquals("[email protected]", testPerson.getEmail());
  }

  @Test
 public void equals_returnsTrueIfNameAndEmailAreSame_true() {
   Person firstPerson = new Person("Henry", "[email protected]");
   Person anotherPerson = new Person("Henry", "[email protected]");
   assertTrue(firstPerson.equals(anotherPerson));
 }

 @Test
 public void all_returnsAllInstancesOfPerson_true() {
   Person firstPerson = new Person("Henry", "henry@henry.com");
   firstPerson.save();
   Person secondPerson = new Person("Harriet", "harriet@harriet.com");
   secondPerson.save();
   assertEquals(true, Person.all().get(0).equals(firstPerson));
   assertEquals(true, Person.all().get(1).equals(secondPerson));
 }

// gather id values assigned by the database and associate them with the corresponding Person object:
@Test
public void save_assignsIdToObject() {
  Person testPerson = new Person("Henry", "henry@henry.com");
  testPerson.save();
  Person savedPerson = Person.all().get(0);
  assertEquals(testPerson.getId(), savedPerson.getId());
}

// test to finding persons based on their ids
@Test
public void find_returnsPersonWithSameId_secondPerson() {
  Person firstPerson = new Person("Henry", "henry@henry.com");
  firstPerson.save();
  Person secondPerson = new Person("Harriet", "harriet@harriet.com");
  secondPerson.save();
  assertEquals(Person.find(secondPerson.getId()), secondPerson);
}

// add functionality to return all Monster objects belonging to a Person
@Test
 public void getMonsters_retrievesAllMonstersFromDatabase_monstersList() {
   Person testPerson = new Person("Henry", "henry@henry.com");
   testPerson.save();
   Monster firstMonster = new Monster("Bubbles", testPerson.getId());
   firstMonster.save();
   Monster secondMonster = new Monster("Spud", testPerson.getId());
   secondMonster.save();
   Monster[] monsters = new Monster[] { firstMonster, secondMonster };
   assertTrue(testPerson.getMonsters().containsAll(Arrays.asList(monsters)));
 }
}
