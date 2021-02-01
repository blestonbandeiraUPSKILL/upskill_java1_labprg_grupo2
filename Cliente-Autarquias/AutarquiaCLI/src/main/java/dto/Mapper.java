/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import model.Data;
import model.Funcionario;
import model.Pessoa;

import java.util.ArrayList;

/**
 *
 * @author blest
 */
public class Mapper {

    public static DataDTO data2DataDTO(Data data) throws NullPointerException {
        return new DataDTO(data.getDia(), data.getMes(), data.getAno());
    }

    public static Data dataDTO2Data(DataDTO dataDTO) throws NullPointerException {
        return new Data(dataDTO.getDia(), dataDTO.getMes(), dataDTO.getAno());
    }

    public static PessoaDTO pessoa2PessoaDTO(Pessoa pessoa) throws NullPointerException {
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

    public static Funcionario funcionario
}
