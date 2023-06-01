package com.goit.module4;

public class YoungerAndOldestWorker {
    String type;
    String name;
    String birthday;
    public YoungerAndOldestWorker(String type, String name, String birthday){
        this.type=type;
        this.name=name;
        this.birthday=birthday;
    }
    @Override
    public String toString(){
        return "[type=" + type + ", name=" + name + ", birthday=" + birthday + "]";
    }
}
