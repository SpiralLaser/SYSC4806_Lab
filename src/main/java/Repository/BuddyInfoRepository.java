package Repository.AddressBook;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    List<BuddyInfo> findByName(String lastName);
    List<BuddyInfo> findByAddress(String lastName);
    List<BuddyInfo> findByPhoneNum(long num);

    BuddyInfo findById(long id);
}