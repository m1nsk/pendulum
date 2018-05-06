package com.minsk.pendulum.web.device;

import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = DeviceRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceRestController extends AbstractDeviceRestController {
    public static final String REST_URL = "/rest/device";

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto create(@RequestBody DeviceDto deviceDto) {
        return super.create(deviceDto);
    }

    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto update(@RequestBody DeviceDto deviceDto, @PathVariable int id) {
        return super.update(deviceDto, id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto get(@PathVariable int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAll() {
        return super.getAll();
    }
}
