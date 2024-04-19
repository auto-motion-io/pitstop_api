package org.motion.motion_api.domain.repositories.pitstop;

import org.motion.motion_api.domain.entities.pitstop.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinanceiroRepository extends JpaRepository<Financeiro,Integer> {
}
