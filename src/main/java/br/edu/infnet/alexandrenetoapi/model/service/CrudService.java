package br.edu.infnet.alexandrenetoapi.model.service;
import java.util.ArrayList;

public interface CrudService<T, ID> {
	T create(T entidade);
	ArrayList<T> readAll();
	T readById(ID id);
	T update(ID id, T entidade);
	void delete(ID id);
}
