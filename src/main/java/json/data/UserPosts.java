package json.data;

import lombok.Data;

@Data
public class UserPosts {
    private int userId;
    private int id;
    private String title;
    private String body;
}
