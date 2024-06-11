package pl.diakowski.mikolaj.sii.promocode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeImplRepository extends JpaRepository<PromoCodeImpl, Long> {

}