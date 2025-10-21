package br.edu.infnet.alexandrenetoapi.interfaces;
import java.util.ArrayList;

public interface CrudService<T, ID> {
	T create(T entidade);
	ArrayList<T> readAll();
	T update(ID id, T entidade);
	void delete(ID id);
}
