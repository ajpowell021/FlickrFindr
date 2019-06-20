package com.adam.flickrfindr.model;

import java.util.List;

public class Photos {

    public int page;
    public int pages;
    public int perpage;
    public String total;
    public List<Photo> photo;

    public Photos(
            int page,
            int pages,
            int perpage,
            String total,
            List<Photo> photo) {
        this.page = page;
        this.pages = pages;
        this.perpage = perpage;
        this.total = total;
        this.photo = photo;
    }
}
