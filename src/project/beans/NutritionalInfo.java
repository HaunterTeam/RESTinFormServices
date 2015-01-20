package project.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import project.getflickr.Photo;
import project.getfood.Food;

/**
 * Created by les on 20/01/15.
 */
@XmlRootElement(name="foodsuggestion")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class NutritionalInfo {

    private Food suggestedFood;
    private Photo foodPhoto;

    public NutritionalInfo(){ }

    public Food getSuggestedFood() {
        return suggestedFood;
    }

    public void setSuggestedFood(Food suggestedFood) {
        this.suggestedFood = suggestedFood;
    }

    public Photo getFoodPhoto() {
        return foodPhoto;
    }

    public void setFoodPhoto(Photo foodPhoto) {
        this.foodPhoto = foodPhoto;
    }
}