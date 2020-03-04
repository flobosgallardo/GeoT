package com.ruyo.geotclient.service.repository;

import com.ruyo.geotclient.model.Gps;
import com.ruyo.geotclient.service.RestGpsDao;
import com.ruyo.geotclient.service.utility.AbstractTemplate;
import com.ruyo.geotclient.service.utility.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RestGpsService extends AbstractTemplate implements RestGpsDao {

    protected RestGpsService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Gps> getGpsList() {
        return Arrays.stream(getRestTemplate().
                getForObject(URL.getGPS_ALL(),Gps[].class)).
                collect(Collectors.toList());
    }

    @Override
    public Gps getGps(String id) {
        return getRestTemplate().
                getForObject(URL.getGPS_BYID(id), Gps.class);
    }
}
