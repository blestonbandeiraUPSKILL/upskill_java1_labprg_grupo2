package com.grupo2.t4j.model;

public enum DiaDaSemana {

    DOMINGO {
        @Override
        public String toString() {
            return "Domingo";
        }
    },
    SEGUNDA {
        @Override
        public String toString() {
            return "Segunda-Feira";
        }
    },
    TERCA {
        @Override
        public String toString() {
            return "Terça-Feira";
        }
    },
    QUARTA {
        @Override
        public String toString() {
            return "Quarta-Feira";
        }
    },
    QUINTA {
        @Override
        public String toString() {
            return "Quinta-Feira";
        }
    },
    SEXTA {
        @Override
        public String toString() {
            return "Sexta-Feira";
        }
    },
    SABADO {
        @Override
        public String toString() {
            return "Sábado";
        }
    };

    public static String designacaoDiaDaSemana (int ordemDiaDaSemana) {
        return DiaDaSemana.values()[ordemDiaDaSemana].toString();
    }
}
