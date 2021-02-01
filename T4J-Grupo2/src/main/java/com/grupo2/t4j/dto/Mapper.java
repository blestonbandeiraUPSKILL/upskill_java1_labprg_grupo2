package com.grupo2.t4j.dto;

import com.grupo2.t4j.model.Data;

import java.util.ArrayList;

public class Mapper {

    public static DataDTO data2DataDTO(Data data) throws NullPointerException {
        return new DataDTO(data.getDia(), data.getMes(), data.getAno());
    }

    public static Data dataDTO2Data(DataDTO dataDTO) throws NullPointerException {
        return new Data(dataDTO.getDia(), dataDTO.getMes(), dataDTO.getAno());
    }

    /*public static PessoaDTO pessoa2PessoaDTO(Pessoa pessoa) throws NullPointerException {
        return new PessoaDTO(pessoa.getNif(), pessoa.getNome(), data2DataDTO(pessoa.getNascimento()));
    }

    public static Pessoa pessoaDTO2Pessoa(PessoaDTO pessoaDTO) throws NullPointerException {
        return new Pessoa(pessoaDTO.getNif(), pessoaDTO.getNome(), dataDTO2Data(pessoaDTO.getNascimento()));
    }

    public static ListaPessoaDTO listaPessoa2ListaPessoaDTO(ArrayList<Pessoa> pessoas) throws NullPointerException {
        ArrayList<PessoaDTO> pessoasDTO = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            try {
                PessoaDTO pessoaDTO = pessoa2PessoaDTO(pessoa);
                pessoasDTO.add(pessoaDTO);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }
        }

        ListaPessoaDTO listaPessoaDTO = new ListaPessoaDTO();
        listaPessoaDTO.setPessoas(pessoasDTO);
        return listaPessoaDTO;
    }

    public static ArrayList<Pessoa> listaPessoaDTO2ListaPessoa(ListaPessoaDTO listaPessoaDTO) throws NullPointerException{
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        ArrayList<PessoaDTO> pessoasDTO = listaPessoaDTO.getPessoas();

        for (PessoaDTO pessoaDTO: pessoasDTO) {
            try {
                Pessoa pessoa = pessoaDTO2Pessoa(pessoaDTO);
                pessoas.add(pessoa);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }
        }

        return pessoas;
    }

    public static FuncionarioDTO funcionario2FuncionarioDTO(Funcionario funcionario) throws NullPointerException {
        return new FuncionarioDTO(funcionario.getNif(),
                funcionario.getNome(),
                data2DataDTO(funcionario.getNascimento()),
                funcionario.getNumeroFuncionario(),
                funcionario.getCargo());
    }

    public static Funcionario funcionarioDTO2Funcionario(FuncionarioDTO funcionarioDTO) throws NullPointerException {
        return new Funcionario(funcionarioDTO.getNif(),
                funcionarioDTO.getNome(),
                dataDTO2Data(funcionarioDTO.getNascimento()),
                funcionarioDTO.getNumeroFuncionario(),
                funcionarioDTO.getCargo());
    }
    public static ListaFuncionarioDTO listaFuncionario2ListaFuncionarioDTO(ArrayList<Funcionario> funcionarios) throws NullPointerException {
        ArrayList<FuncionarioDTO> funcionariosDTO = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            try {
                FuncionarioDTO funcionarioDTO = funcionario2FuncionarioDTO(funcionario);
                funcionariosDTO.add(funcionarioDTO);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }

        }

        ListaFuncionarioDTO listaFuncionarioDTO = new ListaFuncionarioDTO();
        listaFuncionarioDTO.setFuncionarios(funcionariosDTO);
        return listaFuncionarioDTO;
    }

    public static ArrayList<Funcionario> listaFuncionarioDTO2ListaFuncionario(ListaFuncionarioDTO listaFuncionarioDTO) throws  NullPointerException {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<FuncionarioDTO> funcionarioDTOS = new ArrayList<>();

        for(FuncionarioDTO funcionarioDTO : funcionarioDTOS) {
            try {
                Funcionario funcionario = funcionarioDTO2Funcionario(funcionarioDTO);
                funcionarios.add(funcionario);
            }
            catch (NullPointerException exception) {
                exception.printStackTrace();
                //não adiciona
            }
        }
        return funcionarios;
    }*/
}
