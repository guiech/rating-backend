package mobile.app.business;

import java.util.List;

public interface GenericBusiness<T> {

	List<T> getAll();
	
	void deleteAll();
	
	void save(T entity);
	
	void modify(T entity);
}
