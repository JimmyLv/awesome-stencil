import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository<ProductDetail, String> {
    @Query("select p from ProductDetail p where UPPER(p.productName) like UPPER(?1) or " +
            "UPPER(p.longDescription) like UPPER(?1)")
    List search(String term);
}