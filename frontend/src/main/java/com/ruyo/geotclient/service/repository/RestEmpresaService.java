package com.ruyo.geotclient.service.repository;

import com.ruyo.geotclient.model.Empresa;
import com.ruyo.geotclient.service.RestEmpresaDao;
import com.ruyo.geotclient.service.utility.AbstractTemplate;
import com.ruyo.geotclient.service.utility.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestEmpresaService extends AbstractTemplate implements RestEmpresaDao {

    protected RestEmpresaService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Empresa> getEmpresas() {
        return Arrays.stream(getRestTemplate().
                getForObject(URL.getEMPRESA_ALL(),Empresa[].class)).
                collect(Collectors.toList());
    }

    @Override
    public Empresa getEmpresa(String id) {
        return getRestTemplate().
                getForObject(URL.getEMPRESA_BYID() +id, Empresa.class);
    }

    @Override
    public Empresa add(Empresa empresa) {
        return getRestTemplate().postForObject(URL.getEMPRESA_ALL(), empresa, Empresa.class);
    }


}
