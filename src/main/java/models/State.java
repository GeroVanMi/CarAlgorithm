package models;

import java.util.ArrayList;

public class State {
    private ArrayList<Field> fields;

    public State(ArrayList<Field> fields) {
        this.fields = new ArrayList<>();
        for(Field field : fields) {
            this.fields.add(new Field(field));
        }
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
}
