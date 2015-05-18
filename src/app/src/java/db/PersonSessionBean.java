package db;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import model.Person;

/**
 * @author Arun Gupta
 */
@Named
@Stateless
public class PersonSessionBean {

    @Inject
    Person person;

    @EJB
    MongoClientProvider mongoClientProvider;

    MongoCollection personCollection;

    @PostConstruct
    private void initDB() {
        MongoClient mongoClient = mongoClientProvider.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase("personDB");
        personCollection = db.getCollection("persons");
        if (personCollection == null) {
            db.createCollection("persons", null);
            personCollection = db.getCollection("persons");
        }

    }

    public void createPerson() {
        BasicDBObject doc = person.toDBObject();
        personCollection.insertOne(doc);
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList();
        DBCursor cur = (DBCursor) personCollection.find();
        System.out.println("getPersons: Found " + cur.length() + " person(s)");
        for (DBObject dbo : cur.toArray()) {
            persons.add(Person.fromDBObject(dbo));
        }

        return persons;
    }
}
