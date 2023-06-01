// Working on this...
package example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AnimalRequest {
    
    private String animal;

    public String getAnimal() {
        return animal;
    }

    @JsonCreator
    public AnimalRequest(@JsonProperty("animal") String animal) {
        this.animal = animal;
    }
}
