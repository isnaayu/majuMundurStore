package com.enigma.majuMundurStore.repository;

import com.enigma.majuMundurStore.entity.TrxOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxOrderRepository extends JpaRepository<TrxOrder, String>, JpaSpecificationExecutor<TrxOrder> {
}
