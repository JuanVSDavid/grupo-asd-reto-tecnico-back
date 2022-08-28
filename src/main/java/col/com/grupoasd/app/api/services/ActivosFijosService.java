package col.com.grupoasd.app.api.services;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import col.com.grupoasd.app.api.Repository.ActivosFijosRepository;
import col.com.grupoasd.app.api.models.ActivosFijos;
import col.com.grupoasd.app.api.models.IEditable;
import col.com.grupoasd.app.api.models.IReadonly;
import col.com.grupoasd.app.api.models.exceptions.AlreadyExistsException;
import col.com.grupoasd.app.api.models.exceptions.NotFoundException;

@Service
public class ActivosFijosService implements IEditable<ActivosFijos>, IReadonly<ActivosFijos> {

    @Autowired
    private ActivosFijosRepository activosFijosRepository;

    @Override
    public List<ActivosFijos> getListOfItems() {
        return activosFijosRepository.findActivosFijosIsNotDeleted();
    }

    @Override
    public ActivosFijos getItem(Long key) throws NotFoundException {
        ActivosFijos activosFijos = activosFijosRepository.findActivosFijosByIdAndIsNotDeleted(key);
        if (Objects.isNull(activosFijos)) {
            throw new NotFoundException("No se encontro el activo fijo con el id" + key);
        }
        return activosFijos;
    }

    @Override
    public ActivosFijos updateItem(Object key, ActivosFijos item)
            throws NotFoundException {
        ActivosFijos activosFijos = activosFijosRepository.findActivosFijosByIdAndIsNotDeleted((long) key);
        if (Objects.isNull(activosFijos)) {
            throw new NotFoundException("No se encontro el activo fijo con el id" + key);
        }
        return activosFijosRepository.save(activosFijos);
    }

    @Override
    public void deleteItem(Object key) throws NotFoundException {
        ActivosFijos activosFijos = activosFijosRepository.findActivosFijosByIdAndIsNotDeleted((long) key);
        if (Objects.isNull(activosFijos)) {
            throw new NotFoundException("No se encontro el activo fijo con el id " + key);
        }
        activosFijos.setIsDeleted(true);
        activosFijosRepository.save(activosFijos);
    }

    public ActivosFijos getActivosFijosBySerial(String serial) throws NotFoundException {
        ActivosFijos activosFijos = activosFijosRepository.findActivosFijosBySerialAndIsNotDeleted(serial);
        if (Objects.isNull(activosFijos)) {
            throw new NotFoundException("No se encontro el activo fijo con el serial " + serial);
        }
        return activosFijos;
    }

    public List<ActivosFijos> getActivosFijosByTipo(String tipo) throws NotFoundException {
        List<ActivosFijos> activosFijos = activosFijosRepository.findActivosFijosByTipoAndIsNotDeleted(tipo);
        if (activosFijos.size() == 0 || activosFijos.isEmpty()) {
            throw new NotFoundException("No se encontro ningun activo fijo con el tipo " + tipo);
        }
        return activosFijos;
    }

    public List<ActivosFijos> getActivosFijosByFechaDeCompra(Date fechaDeCompra) throws NotFoundException {
        List<ActivosFijos> activosFijos = activosFijosRepository
                .findActivosFijosBySerialAndFechaDeCompraAndIsNotDeleted(fechaDeCompra);
        if (activosFijos.size() == 0 || activosFijos.isEmpty()) {
            throw new NotFoundException(
                    "No se encontro ningun activo fijo con la fecha de compra " + fechaDeCompra.getTime());
        }
        return activosFijos;
    }

    @Override
    public ActivosFijos insertItem(ActivosFijos item) throws AlreadyExistsException {
        if (!Objects.isNull(activosFijosRepository.findActivosFijosBySerialAndIsNotDeleted(item.getSerial()))) {
            throw new AlreadyExistsException("El activo fijo con el serial " + item.getSerial() + " ya existe");
        }
        return activosFijosRepository.save(item);
    }

}
