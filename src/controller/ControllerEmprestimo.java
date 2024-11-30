package controller;

import dao.EmprestimoDAO;
import java.sql.Connection;
import java.util.Date;

public class ControllerEmprestimo {

    private EmprestimoDAO emprestimoDAO;

    public ControllerEmprestimo(Connection con) {
        emprestimoDAO = new EmprestimoDAO(con);
    }

    public boolean realizarEmprestimo(int usuarioId, int livroId, Date dataDevolucao) {
        System.out.println("Tentando realizar empréstimo:");
        System.out.println("ID Usuário: " + usuarioId);
        System.out.println("ID Livro: " + livroId);
        System.out.println("Data Devolução: " + dataDevolucao);

        java.sql.Date dataDevolucaoSql = new java.sql.Date(dataDevolucao.getTime()); 

        boolean resultado = emprestimoDAO.salvarEmprestimo(usuarioId, livroId, dataDevolucaoSql);
        System.out.println("Resultado da operação de salvarEmprestimo: " + resultado);
        return resultado;
    }

    public boolean atualizarEmprestimo(int idEmprestimo, int usuarioId, int livroId, Date dataDevolucao) {
        java.sql.Date dataDevolucaoSql = new java.sql.Date(dataDevolucao.getTime()); 
        return emprestimoDAO.atualizarEmprestimo(idEmprestimo, usuarioId, livroId, dataDevolucaoSql);
    }


    public boolean excluirEmprestimo(int idEmprestimo) {
        return emprestimoDAO.excluirEmprestimo(idEmprestimo);
    }

    public Object[][] listarEmprestimos() {
        return emprestimoDAO.listarEmprestimos();
    }

    public Object[][] buscarEmprestimo(int idEmprestimo) {
        return emprestimoDAO.buscarEmprestimo(idEmprestimo);
    }
}
