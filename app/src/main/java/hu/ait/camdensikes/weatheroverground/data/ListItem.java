package hu.ait.camdensikes.weatheroverground.data;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Camden Sikes on 11/29/2016.
 */

public class ListItem extends SugarRecord<ListItem> implements Serializable {
    private String city;

    public ListItem(){}

    public ListItem(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
