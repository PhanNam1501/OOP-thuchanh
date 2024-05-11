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
                String enroll_link = article.selectFirst("a").attr("href");
                String title = article.select(".article__title").text();
                Document doc = Jsoup.connect(enroll_link).get();
                String author = doc.select(".author-title").text();
                Elements dateSectionDiv = doc.select("div.fs-14.date-section");
                String time = dateSectionDiv.select("time").attr("datetime");
                Element tagBoxDiv = doc.selectFirst("div.article-tag-box");
                if (tagBoxDiv != null) {
	                String tag = tagBoxDiv.select("a").text(); 
	                Article newArticle = new Article(enroll_link,title, author, time, tag);
	                articleList.add(newArticle);
                }
                else {
	                Article newArticle = new Article(enroll_link,title, author, time);
	                articleList.add(newArticle);
                }
                
               
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
                row1.add("\"" + "Author" + "\"");
                row1.add("\"" + "Time" + "\"");
                row1.add("\"" + "Tag" + "\"");
                printWriter.println(String.join(",", row1));

                // iterating over all quotes
                for (Article quote : articleList) {
                    // converting the quote data into a
                    // list of strings
                    List<String> row = new ArrayList<>();

                    // wrapping each field with between quotes
                    // to make the CSV file more consistent
                    System.out.println(quote.getEnrollLink());
                    System.out.println(quote.getTitle());
                    System.out.println(quote.getAuthor());
                    System.out.println(quote.getTime());
                    System.out.println(quote.getTag());
                   
                 
                    row.add("\"" +quote.getEnrollLink() + "\"");
                    row.add("\"" + quote.getTitle() + "\"");
                    row.add("\"" +quote.getAuthor() + "\"");
                    row.add("\"" +quote.getTime() + "\"");
                    row.add("\"" +quote.getTag() + "\"");
                    

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
        private String enroll_link;
        private String author;
        private String time;
        private String tag;

        public Article(String enroll_link,String title,  String author, String time, String tag) {
            this.title = title;

            this.enroll_link = enroll_link;
            this.author = author;
            this.time = time;
            this.tag = tag;
        }
        public Article(String enroll_link,String title,  String author, String time) {
            this.title = title;

            this.enroll_link = enroll_link;
            this.author = author;
            this.time = time;
            
        }

        public String getTitle() {
            return title;
        }




        public String getEnrollLink() {
            return enroll_link;
        }
        
        public String getAuthor() {
        	return author;
        }
        public String getTime() {
        	return time;
        }
        public String getTag() {
        	return tag;
        }
    }

    // Method to write data to CSV file
    
}
