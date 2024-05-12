package com.enigma.majuMundurStore.repository;

import com.enigma.majuMundurStore.entity.TrxOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxOrderDetailRepository extends JpaRepository<TrxOrderDetail, String> {
}
