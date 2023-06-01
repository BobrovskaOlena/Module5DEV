package com.goit.module4;

public class MaxProjectCountClient {
    String name;
    int projectCount;
    public MaxProjectCountClient(String name, int projectCount){
        this.name = name;
        this.projectCount = projectCount;
    }
    @Override
    public String toString(){
        return "MaxProjectCountClient [name=" + name + ", projectCount=" + projectCount + "]";
    }
}
