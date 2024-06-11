package pl.diakowski.mikolaj.sii.promocode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCodeImpl, Long> {

	Optional<PromoCodeImpl> findByCode(String code);
}