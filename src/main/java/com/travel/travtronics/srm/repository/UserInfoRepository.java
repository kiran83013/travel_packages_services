package com.travel.travtronics.srm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.srm.model.UserInfoModel;

public interface UserInfoRepository extends JpaRepository<UserInfoModel, Long>{

	@Query(value="SELECT * FROM user_info  WHERE user_id = ?1", nativeQuery = true)
	Optional<UserInfoModel> getAgentInfoById(Integer agentId);
	

}
