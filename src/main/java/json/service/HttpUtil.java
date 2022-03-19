package json.service;

import com.google.gson.Gson;
import json.data.Address;
import json.data.Company;
import json.data.Geo;
import json.data.Root;

import java.net.http.HttpClient;

public class HttpUtil {

    public static final Gson GSON = new Gson();
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final String DEFAULT_URI = "https://jsonplaceholder.typicode.com";


    public static Root createNewDefaultUser() {
        Root user = new Root();
        user.setId(1);
        user.setName("Artem");
        user.setUsername("povidlo2010");
        user.setAddress(defaultAddress());
        user.setEmail("notowar@gmail.com");
        user.setPhone("1336-1337");
        user.setWebsite("www.artemshop.com");
        user.setCompany(defaultCompany());
        return user;
    }

    private static Company defaultCompany() {
        Company company = new Company();
        company.setName("ArthasComp");
        company.setCatchPhrase("Zero mistakes");
        company.setBs("Easy easy");
        return company;
    }

    private static Address defaultAddress() {
        Address address = new Address();
        address.setStreet("Pushkina");
        address.setSuite("24");
        address.setCity("Konoha");
        address.setZipcode("1336");
        Geo geo = new Geo();
        geo.setLat("12");
        geo.setLng("34");
        address.setGeo(geo);
        return address;
    }


}
