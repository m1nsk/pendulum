package com.minsk.pendulum.web.device;

import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = DeviceRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceRestController extends AbstractDeviceRestController {
    public static final String REST_URL = "/rest/device";

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Device create(@RequestBody Device device) {
        return super.create(device);
    }

    @Override
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Device update(@RequestBody Device device, @PathVariable int id) {
        return super.update(device, id);
    }

    @Override
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Device get(@PathVariable int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getAll(int userId) {
        return super.getAll(userId);
    }
}
