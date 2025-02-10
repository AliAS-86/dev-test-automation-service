//package com.restaurama.db;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.bson.Document;
//import org.bson.json.JsonWriterSettings;
//
//// Java utils
//import java.util.ArrayList;
//import java.util.List;
//
//public class mongodbConn {
//    public static void main(String[] args) {
//        MongoClient mongoClient = getMongoClient();
//        mongoClient.close();
//    }
//    public static MongoClient getMongoClient() {
//        String mongodb_uri = System.getenv("MONGODB_URI");
//        try (MongoClient mongoClient = MongoClients.create(mongodb_uri)) {
//            System.out.println("=> Connection successful: " + connectionTest(mongoClient));
//            return mongoClient;
//        }
//    }
//    static boolean connectionTest(MongoClient mongoClient) {
//        Document pingCommand = new Document("ping", 1);
//        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
//        System.out.println("=> Print result of the '{ping: 1}' command.");
//        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
//        return response.get("ok", Number.class).intValue() == 1;
//    }
//}