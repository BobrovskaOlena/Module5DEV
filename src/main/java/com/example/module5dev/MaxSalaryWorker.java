package com.goit.module4;

public class MaxSalaryWorker {
    private long ID;
    private String name;
    private String birthday;
    private String levels;
    private int salary;
    public MaxSalaryWorker(long ID, String name, String birthday, String levels, int salary) {
        this.name = name;
        this.birthday = birthday;
        this.levels = levels;
        this.salary = salary;
        this.ID=ID;
    }
    @Override
    public String toString(){
        return "MaxSalaryWorker [ID=" + ID + ", name=" + name + ", birthday=" + birthday + ", levels=" + levels + ", salary=" + salary + "]";
    }
}
