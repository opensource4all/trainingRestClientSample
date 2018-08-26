package com.opensource4all.restclient.json.openNotify;

public class People {
    private String craft;
    private String name;

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("People{craft='%s', name='%s'}", craft, name);
    }
}
