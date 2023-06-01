package example;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Details {

    // Creating an object of ArrayList
    static ArrayList<Details> Data = new ArrayList<Details>();
    int number;
    String name;
    
    @JsonCreator
    public Details(@JsonProperty("number") int number, @JsonProperty("name") String name)  {
        // This keyword refers to parent instance itself
        this.number = number;
        this.name = name;
    }
}
