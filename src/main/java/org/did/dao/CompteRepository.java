package org.did.dao;

import org.did.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository <Compte , String> {

}
