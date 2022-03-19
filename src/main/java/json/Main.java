package json;


import json.service.HttpServiceMethods;

import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Creating new object: ");
        System.out.println(HttpServiceMethods.postNewUser());
        System.out.println("------------------------------");
        System.out.println("Updating an object: ");
        System.out.println(HttpServiceMethods.updateUserWithSpecificId(5));
        System.out.println("------------------------------");
        System.out.println("Deleting an object: ");
        System.out.println(HttpServiceMethods.deletingUserById(6));
        System.out.println("------------------------------");
        System.out.println("All objects: ");
        HttpServiceMethods.getAllUsers().forEach(System.out::println);
        System.out.println("------------------------------");
        System.out.println("Getting object with specific id: ");
        System.out.println(HttpServiceMethods.getUserWithSpecificId(3));
        System.out.println("------------------------------");
        System.out.println("Getting object with specific username: ");
        System.out.println(HttpServiceMethods.getUserWithSpecificUsername("Bret"));
        System.out.println("------------------------------");
        System.out.println("All comments to last post of specific user: (also written to user-userId-post-userLastPost-comments.json)");
        HttpServiceMethods.getAndWriteCommentsToFile(1);
        System.out.println("------------------------------");
        System.out.println("All free tasks for specified user: ");
        HttpServiceMethods.printFreeTasksForUser(1);
    }
}
