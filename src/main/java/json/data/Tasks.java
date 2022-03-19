package json.data;

import lombok.Data;

@Data
public class Tasks {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
