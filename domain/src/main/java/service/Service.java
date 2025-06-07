package service;

import java.util.List;

public interface Service<Model, ID> {

  Model get(ID id);

  List<Model> getAll();

  void delete(ID id);

  Model create(Model model);

  Model update(Model model);
}
