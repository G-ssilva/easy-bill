package br.com.alura.oobj.easybill.classe_fiscal.repository;

import br.com.alura.oobj.easybill.classe_fiscal.model.ClasseFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseFiscalRepository extends JpaRepository<ClasseFiscal,Long> {
}
