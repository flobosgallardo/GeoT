package com.ruyo.geotclient.service.repository;

import com.ruyo.geotclient.model.Vehiculo;
import com.ruyo.geotclient.service.RestVehiculoDao;
import com.ruyo.geotclient.service.utility.AbstractTemplate;
import com.ruyo.geotclient.service.utility.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestVehiculoService extends AbstractTemplate implements RestVehiculoDao {

    protected RestVehiculoService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Vehiculo> getVehiculos() {
        return Arrays.stream(getRestTemplate().
                getForObject(URL.getVEHICULO_ALL(),Vehiculo[].class)).
                collect(Collectors.toList());
    }

    @Override
    public Vehiculo getVehiculo(String id) {
        return getRestTemplate().
                getForObject(URL.getVEHICULO_BYID() +id, Vehiculo.class);
    }

}
