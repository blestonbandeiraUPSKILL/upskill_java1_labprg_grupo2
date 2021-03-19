package com.grupo2.t4j.dto;

public enum ObrigatoriedadeDTO {
    OBRIGATORIA {
        @Override
        public String toString() {
            return "obrigatoria";
        }},
    OPCIONAL {
        @Override
        public String toString() {
            return "opcional";
        }};
}
