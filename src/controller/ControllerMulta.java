package controller;

import model.Multa;
import model.MultaDAO;
import model.MultaDAOImpl;

import java.util.List;

public class ControllerMulta {

    private MultaDAO multaDAO;

    public ControllerMulta() {
        this.multaDAO = new MultaDAOImpl();
    }

    public boolean cadastrarMulta(Multa multa) {
        return multaDAO.cadastrarMulta(multa);
    }

    public boolean excluirMulta(int id) {
        return multaDAO.excluirMulta(id);
    }

    public List<Multa> listarMultas() {
        return multaDAO.listarMultas();
    }

    public void calcularMultas() {
        multaDAO.calcularMultas();
    }
}
