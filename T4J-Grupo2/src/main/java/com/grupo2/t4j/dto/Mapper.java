package com.grupo2.t4j.dto;

import com.grupo2.t4j.model.Data;

public class Mapper {

    public static DataDTO data2DataDTO(Data data) throws NullPointerException {
        return new DataDTO(data.getDia(), data.getMes(), data.getAno());
    }

    public static Data dataDTO2Data(DataDTO dataDTO) throws NullPointerException {
        return new Data(dataDTO.getDia(), dataDTO.getMes(), dataDTO.getAno());
    }
}
