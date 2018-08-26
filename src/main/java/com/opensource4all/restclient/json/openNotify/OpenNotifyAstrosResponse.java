package com.opensource4all.restclient.json.openNotify;

public class OpenNotifyAstrosResponse {

    private String message;
    private People[] people;
    private int number;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public People[] getPeople() {
        return people;
    }

    public void setPeople(People[] people) {
        this.people = people;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
