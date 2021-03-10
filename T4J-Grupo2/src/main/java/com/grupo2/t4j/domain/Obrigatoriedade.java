package com.grupo2.t4j.domain;

public enum Obrigatoriedade {
    OBRIGATORIA {
        @Override
        public String toString() {
            return "Obrigatória";
        }},
    OPCIONAL {
        @Override
        public String toString() {
            return "Opcional";
        }};
}
