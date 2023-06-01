package com.goit.module4;

public class LongestProject {
    long id;
    int months;
    public LongestProject(long id, int months){
        this.id = id;
        this.months=months;
    }
    @Override
    public String toString(){
        return "Longest Project [ID=" + id + ", months=" + months +"]";
    }
}
