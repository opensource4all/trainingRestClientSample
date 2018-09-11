package com.opensource4all.restclient.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Joke {
    @Id
    @GeneratedValue
    public int id;
    public String joke;

    public Joke(String joke) {
        this.joke = joke;
    }
}
