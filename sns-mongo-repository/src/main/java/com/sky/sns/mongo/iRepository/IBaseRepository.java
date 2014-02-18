package com.sky.sns.mongo.iRepository;
import java.util.List;

public interface IBaseRepository<T,ID> {
	public List<T> findAll();
	public T findByID(ID id);
	public T insert(T t);
    public void update(T t);
    public void delete(T t);
}
