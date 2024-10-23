package digital.transaction.challenge.persistence.mappers;

import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.persistence.model.TransactionHistoryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface PersistenceEntityMapper {


    @Mapping(source = "accountFrom", target = "accountFrom.id")
    @Mapping(source = "accountTo", target = "accountTo.id")
    Transaction from(final TransactionHistoryEntity source);

    @Mapping(source = "accountFrom.id", target = "accountFrom")
    @Mapping(source = "accountTo.id", target = "accountTo")
    TransactionHistoryEntity from(final Transaction source);
}
