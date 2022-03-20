package json.service;

import com.google.gson.reflect.TypeToken;
import json.data.Comments;
import json.data.Root;
import json.data.Tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static json.service.HttpUtil.*;

public class HttpServiceMethods {
    public static List<Root> getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(DEFAULT_URI + "/users"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        Type typeToken = TypeToken
                .getParameterized(List.class, Root.class)
                .getType();

        List<Root> list = GSON.fromJson(body, typeToken);
        return list;
    }

    public static Root updateUserWithSpecificId(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(DEFAULT_URI+"/users/"+id))
                .PUT(HttpRequest.BodyPublishers.ofString(GSON.toJson(createNewDefaultUser())))
                .headers("Content-type", "application/json; charset=UTF-8")
                .build();

        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), Root.class);
    }

    public static Root postNewUser() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(DEFAULT_URI + "/users"))
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(createNewDefaultUser())))
                .headers("Content-type", "application/json; charset=UTF-8")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), Root.class);
    }

    public static int deletingUserById(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(DEFAULT_URI + "/users/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public static Root getUserWithSpecificId(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(DEFAULT_URI + "/users/" + id))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return GSON.fromJson(response.body(), Root.class);
    }

    public static Root getUserWithSpecificUsername(String username) throws IOException, InterruptedException {
        String url = DEFAULT_URI +"/users?username="+username;
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());;
        String body = response.body();


        Type typeToken = TypeToken
                .getParameterized(List.class, Root.class)
                .getType();

        List<Root> list = GSON.fromJson(body, typeToken);

        return list.get(0);
    }

    public static void getAndWriteCommentsToFile(int userId) throws IOException, InterruptedException {
        String url = DEFAULT_URI + "/posts/" + userId * 10 + "/comments";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());

        String body = response.body();


        Type typeToken = TypeToken
                .getParameterized(List.class, Comments.class)
                .getType();

        List<Comments> list = GSON.fromJson(body, typeToken);
        list.forEach(System.out::println);


        File file = new File("user-"+userId+"-post-"+userId * 10+"-comments.json");
        try (FileWriter writer = new FileWriter(file)){
            writer.write(response.body());
        }catch (IOException io){
            System.out.println("Error");
        }
    }

    public static void printFreeTasksForUser(int userId) throws IOException, InterruptedException {
        String url = DEFAULT_URI + "/users/" + userId + "/todos";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        String body = response.body();


        Type typeToken = TypeToken
                .getParameterized(List.class, Tasks.class)
                .getType();

        List<Tasks> list = GSON.fromJson(body, typeToken);
        for (Tasks tasks : list) {
            if (tasks.isCompleted()){
                System.out.println(tasks);
            }
        }
    }
}
