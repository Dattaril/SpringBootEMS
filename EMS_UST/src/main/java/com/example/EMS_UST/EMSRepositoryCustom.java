package com.example.EMS_UST;
import java.util.List;

public interface EMSRepositoryCustom {
	
	//we are creating one user defined INterface
	
	
	List<EMS> findAllByeName(String ename);

}
