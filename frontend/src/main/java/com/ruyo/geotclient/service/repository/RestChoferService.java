package com.ruyo.geotclient.service.repository;

import com.ruyo.geotclient.model.Chofer;
import com.ruyo.geotclient.service.RestChoferDao;
import com.ruyo.geotclient.service.utility.AbstractTemplate;
import com.ruyo.geotclient.service.utility.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestChoferService extends AbstractTemplate implements RestChoferDao {

    protected RestChoferService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Chofer> getChoferes() {
        return Arrays.stream(getRestTemplate().
                getForObject(URL.getCHOFER_ALL(), Chofer[].class)).
                collect(Collectors.toList());
    }

    @Override
    public Chofer getChofer(String id) {
        return getRestTemplate().
                getForObject(URL.getCHOFER_BYID() +id, Chofer.class);
    }
}
