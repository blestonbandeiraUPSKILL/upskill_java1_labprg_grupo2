package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.DTO;
import com.grupo2.t4j.dto.ObrigatoriedadeDTO;

public enum Obrigatoriedade implements DTO {
    OBRIGATORIA {
        @Override
        public Object toDTO() {
            return ObrigatoriedadeDTO.OBRIGATORIA;
        }

        @Override
        public String toString() {
            return "obrigatoria";
        }},
    OPCIONAL {
        @Override
        public Object toDTO() {
            return ObrigatoriedadeDTO.OPCIONAL;
        }

        @Override
        public String toString() {
            return "opcional";
        }};
}
