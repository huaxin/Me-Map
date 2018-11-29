package io.renren.common.utils;

import java.io.Serializable;

public class Audit implements Serializable, Cloneable, Comparable<Audit>{
    private long id;
    private String name;
    public Audit(){
        this.id = 0;
    }
    public Audit(long id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public void clear() {
        this.id = 0L;
        this.name = null;
    }

    public long getId() {
        return this.id;
    }

    private Audit setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    private Audit setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public int compareTo(Audit other) {
        if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
        } else {
            //int lastComparison = Boolean.valueOf(this.id == 0).compareTo(other.id == 0);
            int lastComparison = TBaseHelper.compareTo(this.id, other.id);
            if (lastComparison != 0) {
                return lastComparison;
            }
            lastComparison = TBaseHelper.compareTo(this.name, other.name);
            if (lastComparison != 0) {
                return lastComparison;
            }
            return 0;
        }
    }
}
