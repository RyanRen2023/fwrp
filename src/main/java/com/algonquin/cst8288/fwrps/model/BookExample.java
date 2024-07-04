/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrps.model;

/**
 *
 * @author renxihai
 */
public class BookExample {

    private Long id;
    private String title;
    private String author;

    // Constructors
    public BookExample() {
    }

    public BookExample(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
