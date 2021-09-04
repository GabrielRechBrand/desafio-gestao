package main;

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
