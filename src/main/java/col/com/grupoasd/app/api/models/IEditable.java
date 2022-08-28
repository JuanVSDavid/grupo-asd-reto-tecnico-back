package col.com.grupoasd.app.api.models;

import col.com.grupoasd.app.api.models.exceptions.AlreadyExistsException;
import col.com.grupoasd.app.api.models.exceptions.NotFoundException;

public interface IEditable<T> {

    public T updateItem(Object key, T item) throws NotFoundException;

    public void deleteItem(Object key) throws NotFoundException;

    public T insertItem(T item) throws AlreadyExistsException;

}
