package persistence;


import org.json.JSONObject;

// Converts object into json object
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
