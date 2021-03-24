package com.allAboutReviews.responses;

import com.allAboutReviews.models.Place;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceResponse extends Response{

    private Place place;
    private List<Place> placeList;
    private Long placeId;

    public PlaceResponse(String msg,Place place){
        super(msg);
        this.place=place;
    }

    public PlaceResponse(String msg,List<Place> placeList){
        super(msg);
        this.placeList=placeList;
    }

    public PlaceResponse(String msg,Long placeId){
        super(msg);
        this.placeId=placeId;
    }
}
