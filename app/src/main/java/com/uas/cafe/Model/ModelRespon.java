package com.uas.cafe.Model;

import java.util.List;

public class ModelRespon {
    private String kode, pesan;

    private List<ModelCafe> data;

    public String getKode(){
        return kode;
    }
    public String getPesan(){
        return pesan;
    }
    public List<ModelCafe> getData(){
        return data;
    }
}
