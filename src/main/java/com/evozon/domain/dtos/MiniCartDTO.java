package com.evozon.domain.dtos;

import java.util.ArrayList;

/**
 * Created by mateimihai on 7/22/2016.
 */
public class MiniCartDTO {

    private Double total;

    private ArrayList<EntryDTO> entries;


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ArrayList<EntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<EntryDTO> entries) {
        this.entries = entries;
    }
}
