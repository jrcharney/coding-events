package org.launchcode.codingevents.models;

// import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id                 // NOTE: Use the javax.persistence.Id annotation!
    @GeneratedValue
    private int id;
    /* private static int nextId = 1; */
    /* this.id = nextId++; // This will combine this.id = nextId; and nextId++;. */


    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
