package com.goit.module4;

public class ProjectPrice {
    long id;
    double projectCost;
    public ProjectPrice(long id, double projectCost){
        this.id=id;
        this.projectCost=projectCost;
    }
    @Override
    public String toString(){
        return "[ID=" + id + ", projectCost=" + projectCost + "]";
    }
}
