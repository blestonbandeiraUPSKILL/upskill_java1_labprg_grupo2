package com.grupo2.t4j.domain;

public enum Mes {

    JANEIRO(31) {
        @Override
        public String toString() {
            return "Janeiro";
        }
    },
    FEVEREIRO(28) {
        @Override
        public String toString() {
            return "Fevereiro";
        }
    },
    MARCO(31) {
        @Override
        public String toString() {
            return "Março";
        }
    },
    ABRIL(30) {
        @Override
        public String toString() {
            return "Abril";
        }
    },
    MAIO(31) {
        @Override
        public String toString() {
            return "Maio";
        }
    },
    JUNHO(30) {
        @Override
        public String toString() {
            return "Junho";
        }
    },
    JULHO(31) {
        @Override
        public String toString() {
            return "Julho";
        }
    },
    AGOSTO(31) {
        @Override
        public String toString() {
            return "Agosto";
        }
    },
    SETEMBRO(30) {
        @Override
        public String toString() {
            return "Setembro";
        }
    },
    OUTUBRO(31) {
        @Override
        public String toString() {
            return "Outubro";
        }
    },
    NOVEMBRO(30) {
        @Override
        public String toString() {
            return "Novembro";
        }
    },
    DEZEMBRO(31) {
        @Override
        public String toString() {
            return "Dezembro";
        }
    };

    private int numeroDeDias;

    Mes(int numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public int numeroDeDias(int ano) {
        if(ordinal() == 1 && Data.isAnoBissexto(ano)) {
            return numeroDeDias + 1;
        }
        return numeroDeDias;
    }

    public static Mes obterMes(int ordemDoMes) {
        return Mes.values()[ordemDoMes - 1];
    }
}
