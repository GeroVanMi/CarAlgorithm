package models;

import java.util.ArrayList;

public class State {
    private ArrayList<Field> fields;

    public State(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
}
