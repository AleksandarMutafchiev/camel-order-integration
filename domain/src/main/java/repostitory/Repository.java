package repostitory;

import java.util.Optional;

public interface Repository<Model, Id> {

  Optional<Model> findById(Id id);

  Model save(Model model);

  Model update(Model model);

  boolean delete(Id id);

  void deleteAll();
}
