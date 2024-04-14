package com.travel.travtronics.packages.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.travel.travtronics.packages.model.AccountsModel;

public interface AccountsRepository extends PagingAndSortingRepository<AccountsModel, Long> {

	
}
