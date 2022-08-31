package col.com.grupoasd.app.api.models;

import java.util.List;

import col.com.grupoasd.app.api.models.exceptions.NotFoundException;

public interface IReadonly<T> {

    public List<T> getListOfItems() throws NotFoundException;

    public T getItem(Long key) throws NotFoundException;
}
