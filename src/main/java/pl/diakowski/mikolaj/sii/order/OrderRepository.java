package pl.diakowski.mikolaj.sii.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "SELECT currency, SUM(regular_price) AS total_amount, SUM(regular_price - discount_price) AS total_discount, COUNT(*) AS number_of_purchases " +
			"FROM purchase " +
			"GROUP BY currency", nativeQuery = true)
	List<Object[]> getSalesReport();
}