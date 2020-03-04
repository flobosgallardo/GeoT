package com.ruyo.geotclient.service.repository;

import com.ruyo.geotclient.model.Rol;
import com.ruyo.geotclient.service.RestRolDao;
import com.ruyo.geotclient.service.utility.AbstractTemplate;
import com.ruyo.geotclient.service.utility.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestRolService extends AbstractTemplate implements RestRolDao {

    protected RestRolService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Rol> getRoles() {
        return Arrays.stream(getRestTemplate().
                getForObject(URL.getROL_ALL(), Rol[].class)).
                collect(Collectors.toList());
    }

    @Override
    public Rol getRol(String id) {
        return getRestTemplate().
                getForObject(URL.getROL_BYID() +id, Rol.class);
    }

}
