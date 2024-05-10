package Selenium;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Scrapping {
    public static void main(String[] args) {
        

        try {
        	
            //Document document = Jsoup.connect(url).get();
        	File input = new File("output.html");
        	Document document = Jsoup.parse(input, "UTF-8", "");
            System.out.println("Success connected");
            Elements articles = document.select("article.mb-30");
            int i = 0;

            // Create a list to store the article data
            List<Article> articleList = new ArrayList<>();

            // Iterate through the articles and extract the required data
            for (Element article : articles) {
                String title = article.selectFirst("a").attr("href");
                String time = article.select(".article__title").text();
                String enroll_link = article.select("a.article__badge").text();
                

                // Create a new Article object and add it to the list)
                Article newArticle = new Article(title, time, enroll_link);
                articleList.add(newArticle);
              
            }

            // Write data to CSV file
            File csvFile = new File("article.csv");
            // using the try-with-resources to handle the
            // release of the unused resources when the writing process ends
            try (PrintWriter printWriter = new PrintWriter(csvFile, StandardCharsets.UTF_8)) {
                // to handle BOM
                printWriter.write('\ufeff');
                List<String> row1 = new ArrayList<>();
                row1.add("\"" + "Link" + "\"");
                row1.add("\"" + "Title" + "\"");
                row1.add("\"" + "Badge" + "\"");
                printWriter.println(String.join(",", row1));

                // iterating over all quotes
                for (Article quote : articleList) {
                    // converting the quote data into a
                    // list of strings
                    List<String> row = new ArrayList<>();

                    // wrapping each field with between quotes
                    // to make the CSV file more consistent
                    System.out.println(quote.getTitle());
                    System.out.println(quote.getTime());
                    System.out.println(quote.getEnrollLink());
                 
                    
                    row.add("\"" + quote.getTitle() + "\"");
                    row.add("\"" +quote.getTime() + "\"");
                    row.add("\"" +quote.getEnrollLink() + "\"");

                    // printing a CSV line
                    printWriter.println(String.join(",", row));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create a class to store the article data
    static class Article {
        private String title;
        private String time;

        private String enroll_link;

        public Article(String title, String time, String enroll_link) {
            this.title = title;
            this.time = time;

            this.enroll_link = enroll_link;
        }

        public String getTitle() {
            return title;
        }

        public String getTime() {
            return time;
        }



        public String getEnrollLink() {
            return enroll_link;
        }
    }

    // Method to write data to CSV file
    
}