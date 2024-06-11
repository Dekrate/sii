package pl.diakowski.mikolaj.sii.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImplRepository extends JpaRepository<ProductImpl, Long> {

	Optional<ProductImpl> findByName(String name);
}