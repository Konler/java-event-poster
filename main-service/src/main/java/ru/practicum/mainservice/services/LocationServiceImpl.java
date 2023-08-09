package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.model.Location;
import ru.practicum.mainservice.repositories.LocationRepository;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Location add(Location location) {
        return locationRepository.save(location);
    }
}
