package com.team10.lifeorientedlist;

import java.util.ArrayList;
import java.util.List;

/**
 *  Holds a list of a specific data type along with a name corresponding to the
 *  name of the category.
 *  It's basically a List with a name.
 *
 * @author Michael Hegerhorst
 * @since 4/2/2019
 * @version 1.0
 *
 * @param <T> The type of data being stored in the category
 */
public class Category<T> {

    private List<T> list = new ArrayList<>();
    private String name;

    Category()                          { name = "General"; }
    Category(List<T> list)              { name = "General"; this.list = list; }
    Category(String name)               { this.name = name; }
    Category(String name, List<T> list) { this.name = name; this.list = list; }

    public List<T> getList() { return list; }
    public String getName() { return name; }

    public void setList(List<T> list) { this.list = list; }
    public void setName(String name) { this.name = name; }

    public void add(T item) { list.add(item); }
}