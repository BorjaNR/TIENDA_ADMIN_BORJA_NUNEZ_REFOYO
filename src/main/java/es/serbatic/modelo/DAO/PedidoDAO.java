package es.serbatic.modelo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.serbatic.modelo.VO.PedidoVO;

@Repository
public interface PedidoDAO extends JpaRepository<PedidoVO, Integer>{
	
}
