package com.patondekarjewellers.models;

/**
 * Created by Asmita on 09-08-2016.
 */
public class SideMenuItem
{
    private String title;
    private int imageres,count;

    public SideMenuItem(String sideMenuTitle, int sideMenuIcon) {
        title =sideMenuTitle;
        imageres =sideMenuIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getImageres() {
        return imageres;
    }

    public void setImageres(int imageres) {
        this.imageres = imageres;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }


}
