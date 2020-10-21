package com.example.exemplocrudtenis;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tennis_table")
public class Tennis {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "tennis_model")
    public String tennisModel;

    @ColumnInfo(name = "tennis_price")
    public double tennisPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTennisModel() {
        return tennisModel;
    }

    public void setTennisModel(String tennisModel) {
        this.tennisModel = tennisModel;
    }

    public double getTennisPrice() {
        return tennisPrice;
    }

    public void setTennisPrice(double tennisPrice) {
        this.tennisPrice = tennisPrice;
    }
}
