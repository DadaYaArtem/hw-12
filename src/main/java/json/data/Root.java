package json.data;

import lombok.Data;

@Data
public class Root {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;


}
