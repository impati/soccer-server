package com.example.soccerdomain.team.domain.formation

import org.springframework.data.jpa.repository.JpaRepository

interface FormationRepository : JpaRepository<Formation, Long> {
}
