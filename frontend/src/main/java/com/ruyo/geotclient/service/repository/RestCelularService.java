package com.ruyo.geotclient.service.repository;

import com.ruyo.geotclient.model.Celular;
import com.ruyo.geotclient.service.RestCelularDao;
import com.ruyo.geotclient.service.utility.AbstractTemplate;
import com.ruyo.geotclient.service.utility.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestCelularService extends AbstractTemplate implements RestCelularDao {

    protected RestCelularService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Celular> getCelulares() {
        return Arrays.stream(getRestTemplate().
                getForObject(URL.getCELULAR(), Celular[].class)).
                collect(Collectors.toList());
    }

    @Override
    public Celular getCelular(String id) {
        return getRestTemplate().
                getForObject(URL.getCELULAR_BYID(id), Celular.class);
    }

    @Override
    public Celular add(Celular celular) {
        return getRestTemplate().postForObject(URL.getCELULAR(), celular, Celular.class);
    }
}
