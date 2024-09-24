package guru.qa.niffler.data.dao;

import guru.qa.niffler.data.entity.SpendEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpendDao {
  SpendEntity create(SpendEntity spend);

  Optional<SpendEntity> findSpendById(UUID id);

  List<SpendEntity> findAllByUsername (String username);

  void deleteSpend(SpendEntity spend) throws SQLException;
}
