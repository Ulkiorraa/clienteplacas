package br.com.ulkiorra.clienteplacas.DAO;

import br.com.ulkiorra.clienteplacas.model.Placas;
import br.com.ulkiorra.clienteplacas.model.Status;

import java.util.List;

public interface IPlacasDAO {
    Placas create(Placas placas);
    Placas update(Placas placas);
    void delete(Placas id);
    List<Placas>FindAll();
    List<Placas>FindByPlaca(String placa);
    List<Placas>FindByName(String name);
    List<Placas>FindByStatus(Status status);
}
