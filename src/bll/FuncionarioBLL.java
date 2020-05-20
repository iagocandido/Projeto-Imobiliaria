package bll;

import dal.FuncionarioDAL;
import java.util.List;
import java.util.Vector;
import model.Cargo;
import model.Funcionario;
import model.Pessoa;

public class FuncionarioBLL {
    FuncionarioDAL dal;
    
    public FuncionarioBLL() {
        dal = new FuncionarioDAL();
    }
    
    public void salvar(Funcionario funcionario) throws Exception {
        dal.adicionar(funcionario);
    }

    public void editar(Funcionario funcionario) throws Exception {
        dal.alterar(funcionario);
    }

    public void remover(Funcionario funcionario) throws Exception {
        dal.excluir(funcionario.getCodigo());
    }

    public List<Funcionario> consultar() {
        return dal.consultar();
    }

    public Funcionario consultaPorId(int id) {
        return dal.consultaPorId(id);
    }
    
    public Vector<Pessoa> listarPessoas(){
        return dal.listarPessoas();
    }
    
    public Vector<Cargo> listarCargos(){
        return dal.listarCargos();
    }
    
    public boolean verificarFuncionariosIguais(int idPessoa){
        return dal.verificarPessoasIguais(idPessoa);
    }
    
    public boolean verificarPisIgual(String pis){
        return dal.verificarPisIgual(pis);
    }
    
    public boolean verificarNCarteiraIguail(String nCarteiraTrabalho){
        return dal.verificarNCarteiraIgual(nCarteiraTrabalho);
    }
    
    public boolean autenticarLogin(String usuario, String senha){
        return dal.autenticarUsuario(usuario, senha);
    }
    
    public String pegarNome(String cpf){
        return dal.pegarNomeUsuario(cpf);
    }
    
    public String pegarCargo(String cpf){
        return dal.pegarCargoUsuario(cpf);
    }
}
