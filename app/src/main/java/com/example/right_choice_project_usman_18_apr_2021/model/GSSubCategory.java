package com.example.right_choice_project_usman_18_apr_2021.model;

public class GSSubCategory{
    String SubCategory;
    int Image;
    String Name;


    public GSSubCategory(String subCategory, int image) {
        SubCategory = subCategory;
        Image = image;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String subCategory) {
        SubCategory = subCategory;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }



}
