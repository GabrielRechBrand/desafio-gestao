package main.java.br.com.desafiogestao.controller;

import main.java.br.com.desafiogestao.model.EmpresaDbRepository;
import main.java.br.com.desafiogestao.model.Empresa;

import java.util.List;

public class EmpresaService {

    private final EmpresaDbRepository empresaDbRepository = new EmpresaDbRepository();

    public void salvarEmpresa(Empresa empresa) {
        empresaDbRepository.salvarEmpresa(empresa);
    }

    public Empresa getEmpresaById(int id) {

        return empresaDbRepository.getEmpresaById(id);

    }

    public void excluirEmpresa(int id) {

        empresaDbRepository.excluirEmpresa(id);

    }

    public List<Empresa> listarTodasEmpresas() {

        return empresaDbRepository.listarTodasEmpresas();

    }

}
