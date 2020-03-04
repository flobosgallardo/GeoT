package old.geot.dao;

import com.ruyo.rest.geot.entity.Chofer;

import java.util.List;

public interface ChoferDao {

    boolean add(Chofer chofer);
    Chofer findOne(Long id);
    boolean delete(Chofer chofer);
    Chofer update(Long id, Chofer chofer);
    List getAllChoferes();
}
